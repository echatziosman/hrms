package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CoverLetterDao;
import kodlamaio.hrms.entities.concretes.CoverLetter;

@Service
public class CoverLetterManager implements CoverLetterService{

	private CoverLetterDao coverLetterDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public CoverLetterManager(CoverLetterDao coverLetterDao, CandidateDao candidateDao) {
		super();
		this.coverLetterDao = coverLetterDao;
		this.candidateDao = candidateDao;
	}


	@Override
	public Result add(CoverLetter coverLetter) {
		
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Cover Letter Saved");
	}

	@Override
	public DataResult<CoverLetter> findByCandidateId(int candidateId) {
		
		if(this.candidateDao.findById(candidateId) != null) {
			
			return new SuccessDataResult<CoverLetter>(this.coverLetterDao.findByCandidateId(candidateId),"Ozet Listelendi");
		}
		
		return new ErrorDataResult<CoverLetter>("User Not found");
				
	}

}
