package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Education;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {

	private EducationService educationService;

	@Autowired
	public EducationsController(EducationService educationService) {
		super();
		this.educationService = educationService;
	}
	
	@PostMapping("/add")
	Result add(@RequestBody Education education) {
		
		return this.educationService.add(education);
	}
	

	
	@GetMapping("/findAllByCandidateId")
	public DataResult<List<Education>> findAllByCandidateId(@RequestParam int id){
		
		return this.educationService.findAllByCandidateId(id);
	}
	
	@GetMapping("/findAllByCandidateIdOrderByFromDesc")
	public DataResult<List<Education>> findAllByCandidateIdOrderByFromDesc(@RequestParam int id){
		
		return this.educationService.findAllByCandidateIdOrderByFromDesc(id);
	}
	
}
