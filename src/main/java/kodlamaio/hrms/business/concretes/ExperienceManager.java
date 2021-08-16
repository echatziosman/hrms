package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.ExperienceDao;
import kodlamaio.hrms.entities.concretes.Experience;

@Service
public class ExperienceManager implements ExperienceService {

	private ExperienceDao experienceDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public ExperienceManager(ExperienceDao experienceDao, CandidateDao candidateDao) {
		super();
		this.experienceDao = experienceDao;
		this.candidateDao = candidateDao;
	}

	
	@Override
	public Result add(Experience experience) {
		
		this.experienceDao.save(experience);
		return new SuccessResult("Experience Saved");
	}


	@Override
	public DataResult<List<Experience>> findAllByCandidateId(int id) {
		
		if(this.candidateDao.findById(id) != null) {
			
			return new SuccessDataResult<List<Experience>>(this.experienceDao.findAllByCandidateId(id), "Experience Listelendi");
		}
		
		return new ErrorDataResult<List<Experience>>("Candidate not found");
	}

	@Override
	public DataResult<List<Experience>> findAllByCandidateIdOrderByFromDateDesc(int id) {
		
        if(this.candidateDao.findById(id) != null) {
			
			return new SuccessDataResult<List<Experience>>(this.experienceDao.findAllByCandidateId(id), "Experience Listelendi");
		}
		
		return new ErrorDataResult<List<Experience>>("Candidate not found");
	}

}
