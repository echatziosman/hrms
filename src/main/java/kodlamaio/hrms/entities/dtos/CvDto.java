package kodlamaio.hrms.entities.dtos;


import java.util.List;


import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CoverLetter;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.Image;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.SocialMedia;
import kodlamaio.hrms.entities.concretes.TechnicalSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CvDto {

	private Candidate candidate;
	private Image image;
	private CoverLetter coverLetter;
	private List<Experience> experiences;
	private List<Education> educations;
	private List<TechnicalSkill> technicalSkills;
	private List<Language> languages;
	private List<SocialMedia> socialMedias;
	
	
	
	
}
