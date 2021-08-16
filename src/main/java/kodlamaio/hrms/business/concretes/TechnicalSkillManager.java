package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TechnicalSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.TechnicalSkillDao;
import kodlamaio.hrms.entities.concretes.TechnicalSkill;

@Service
public class TechnicalSkillManager implements TechnicalSkillService{

	private TechnicalSkillDao technicalSkillDao;
	private CandidateDao candidatedao;
	
	@Autowired
	public TechnicalSkillManager(TechnicalSkillDao technicalSkillDao, CandidateDao candidatedao) {
		super();
		this.technicalSkillDao = technicalSkillDao;
		this.candidatedao = candidatedao;
	}


	@Override
	public Result add(TechnicalSkill technicalSkill) {
		
		this.technicalSkillDao.save(technicalSkill);
		return new SuccessResult("Teknik Beceri Eklendi");
	}

	@Override
	public DataResult<List<TechnicalSkill>> findAllByCandidateId(int candidateId) {
		
		if(this.candidatedao.findById(candidateId) != null) {
			
			return new SuccessDataResult<List<TechnicalSkill>>(this.technicalSkillDao.findAllByCandidateId(candidateId), "Teknik beceriler listelendi");
		}
		
		return new ErrorDataResult<List<TechnicalSkill>>("User not found");
	}

}
