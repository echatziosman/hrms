package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.fakeServices.abstracts.EmailService;
import kodlamaio.hrms.core.fakeServices.abstracts.MernisService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private EmailService emailService;
	private MernisService mernisService;
	private UserDao userDao;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, EmailService emailService, MernisService mernisService,
			UserDao userDao) {
		super();
		this.candidateDao = candidateDao;
		this.emailService = emailService;
		this.mernisService = mernisService;
		this.userDao = userDao;
	}


	
	
	@Override
	public Result register(Candidate candidate) {
		
		if(!this.mernisService.userVerification(candidate))
		{
			return new ErrorResult(false, "Kisi Dogrulanamadi");
		}
		
		if(this.userDao.findByEmail(candidate.getEmail()) != null) {
			
			return new ErrorResult(false, "User already exists");
		}
		
		if(!this.emailService.linkToVerify(candidate))
		{
			return new ErrorResult(false, "We have sent your verification codes to your email address");
		}
		
		
		this.candidateDao.save(candidate);
		return new SuccessResult("Candidate saved");
	}
	
	
	
	@Override
	public DataResult<List<Candidate>> getall() {
		
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Candidates listed");
	}

}
