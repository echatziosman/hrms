package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.business.abstracts.TechnicalSkillService;
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
import kodlamaio.hrms.entities.dtos.CvDto;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private EmailService emailService;
	private MernisService mernisService;
	private UserDao userDao;
	private ImageService imageService;
	private CoverLetterService coverLetterService;
	private ExperienceService experienceService;
	private EducationService educationService;
	private TechnicalSkillService technicalSkillService;
	private LanguageService languageService;
	private SocialMediaService socialMediaService;
	
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, EmailService emailService, MernisService mernisService,
			UserDao userDao, ImageService imageService, CoverLetterService coverLetterService,
			ExperienceService experienceService, EducationService educationService,
			TechnicalSkillService technicalSkillService, LanguageService languageService,
			SocialMediaService socialMediaService) {
		super();
		this.candidateDao = candidateDao;
		this.emailService = emailService;
		this.mernisService = mernisService;
		this.userDao = userDao;
		this.imageService = imageService;
		this.coverLetterService = coverLetterService;
		this.experienceService = experienceService;
		this.educationService = educationService;
		this.technicalSkillService = technicalSkillService;
		this.languageService = languageService;
		this.socialMediaService = socialMediaService;
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




	@Override
	public DataResult<Candidate> findById(int candidateId) {
		
		return new SuccessDataResult<Candidate>(this.candidateDao.findById(candidateId), "Candidate is here");
	}




	@Override
	public DataResult<CvDto> getCandidateCv(int candidateId) {
		
		CvDto cv = new CvDto();
		cv.setCandidate(this.findById(candidateId).getData());
		cv.setImage(this.imageService.findByCandidateId(candidateId).getData());
		cv.setCoverLetter(this.coverLetterService.findByCandidateId(candidateId).getData());
		cv.setExperiences(this.experienceService.findAllByCandidateIdOrderByFromDateDesc(candidateId).getData());
		cv.setEducations(this.educationService.findAllByCandidateIdOrderByFromDesc(candidateId).getData());
		cv.setTechnicalSkills(this.technicalSkillService.findAllByCandidateId(candidateId).getData());
		cv.setLanguages(this.languageService.findAllByCandidateId(candidateId).getData());
		cv.setSocialMedias(this.socialMediaService.findAllByCandidateId(candidateId).getData());

		
		return new SuccessDataResult<CvDto>(cv, "Cv listelendi");
	}



	@Override
	public Result deleteById(int candidateId) {
		
		this.candidateDao.deleteById(candidateId);
		return new SuccessResult("Candidate deleted");
	}

}
