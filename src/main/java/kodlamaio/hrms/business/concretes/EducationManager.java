package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.entities.concretes.Education;

@Service
public class EducationManager implements EducationService{

	private EducationDao educationDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public EducationManager(EducationDao educationDao, CandidateDao candidateDao) {
		super();
		this.educationDao = educationDao;
		this.candidateDao = candidateDao;
	}


	@Override
	public Result add(Education education) {
		
		this.educationDao.save(education);
		return new SuccessResult("Education saved");
	}

	@Override
	public DataResult<List<Education>> findAllByCandidateId(int id) {
		
		if(this.candidateDao.findById(id) != null)
		{
			
			return new SuccessDataResult<List<Education>>(this.educationDao.findAllByCandidateId(id), "Egitimler listelendi");
		}
		return new ErrorDataResult<List<Education>>("User not found");
	}

	@Override
	public DataResult<List<Education>> findAllByCandidateIdOrderByFromDesc(int id) {
		
		if(this.candidateDao.findById(id) != null)
		{
			return new SuccessDataResult<List<Education>>(this.educationDao.findAllByCandidateIdOrderByFromDateDesc(id), "Egitimler listelendi");
		}
		return new ErrorDataResult<List<Education>>("User not found");
	}


}
