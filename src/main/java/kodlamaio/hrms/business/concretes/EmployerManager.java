package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.fakeServices.abstracts.EmailService;
import kodlamaio.hrms.core.fakeServices.abstracts.EmployerVerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private UserDao userDao;
	private EmailService emailService;
	private EmployerVerificationService employerVerificationService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, UserDao userDao, EmailService emailService,
			EmployerVerificationService employerVerificationService) {
		super();
		this.employerDao = employerDao;
		this.userDao = userDao;
		this.emailService = emailService;
		this.employerVerificationService = employerVerificationService;
	}




	@Override
	public Result register(Employer employer) {
		
      if(this.userDao.findByEmail(employer.getEmail()) != null) {
			
			return new ErrorResult(false, "User already exists");
		}	
      
      if(!this.emailService.linkToVerify(employer))
		{
			return new ErrorResult(false, "We have sent your verification codes to your email address");
		}
      
      if(!this.employerVerificationService.employerVerificationByEmployee(employer)) {
    	  
    	  return new ErrorResult(false, "Kaydiniz admin tarafindan dogrulanmadi");
      }
       
      this.employerDao.save(employer);
      return new SuccessResult("Employer saved");
		
              
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers Listed");
	}

}
