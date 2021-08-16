package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService{

	private LanguageDao languageDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao, CandidateDao candidateDao) {
		super();
		this.languageDao = languageDao;
		this.candidateDao = candidateDao;
	}


	@Override
	public Result add(Language language) {
		
		this.languageDao.save(language);
		return new SuccessResult("Language Saved");
	}

	@Override
	public DataResult<List<Language>> findAllByCandidateId(int candidateId) {
		
		if(this.candidateDao.findById(candidateId) != null) {
			
			return new SuccessDataResult<List<Language>>(this.languageDao.findAllByCandidateId(candidateId), "Yabanci Diller listelendi");
		}
		return new ErrorDataResult<List<Language>>("User not found");
	}

}
