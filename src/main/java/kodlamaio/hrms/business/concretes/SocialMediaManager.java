package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaDao;
import kodlamaio.hrms.entities.concretes.SocialMedia;

@Service
public class SocialMediaManager implements SocialMediaService{

	private SocialMediaDao socialMediaDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public SocialMediaManager(SocialMediaDao socialMediaDao, CandidateDao candidateDao) {
		super();
		this.socialMediaDao = socialMediaDao;
		this.candidateDao = candidateDao;
	}


	@Override
	public Result add(SocialMedia socialMedia) {
		
		this.socialMediaDao.save(socialMedia);
		return new SuccessResult("Your Social Media Address saved");
	}

	@Override
	public DataResult<List<SocialMedia>> findAllByCandidateId(int candidateId) {
		
		if(this.candidateDao.findById(candidateId) != null) {
			
			return new SuccessDataResult<List<SocialMedia>>(this.socialMediaDao.findAllByCandidateId(candidateId), "Social media Addresses listed");
		}
		return new ErrorDataResult<List<SocialMedia>>("User Not found");
		
	}

}
