package kodlamaio.hrms.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "user_id",referencedColumnName = "id")
@Table(name = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","educations","experiences","languages","socialMedias","technicalSkills","coverLetters","images"})
public class Candidate extends User{
    
	@NotNull
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank
	@NotNull
	@Column(name = "national_identity")
	private String nationalIdentity;
	
	@Column(name = "birthdate")
	private Date birthdate;
	
	@OneToMany(mappedBy = "candidate")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "candidate")
	private List<Experience> experiences;
	
	@OneToMany(mappedBy = "candidate")
	private List<Language> languages;
	
	@OneToMany(mappedBy = "candidate")
	private List<SocialMedia> socialMedias;
	
	@OneToMany(mappedBy = "candidate")
	private List<TechnicalSkill> technicalSkills;
	
	@OneToMany(mappedBy = "candidate")
	private List<CoverLetter> coverLetters;
	
	@OneToMany(mappedBy = "candidate")
	private List<Image> images;
	
}
