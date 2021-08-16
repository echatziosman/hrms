package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.TechnicalSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.TechnicalSkill;

@RestController
@RequestMapping("/api/technicalskills")
public class TechnicalSkillsController {

	private TechnicalSkillService technicalSkillService;

	@Autowired
	public TechnicalSkillsController(TechnicalSkillService technicalSkillService) {
		super();
		this.technicalSkillService = technicalSkillService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody TechnicalSkill technicalSkill) {
		
		return this.technicalSkillService.add(technicalSkill);
		
	}
	
	@GetMapping("/findAllByCandidateId")
	public DataResult<List<TechnicalSkill>> findAllByCandidateId(@RequestParam int candidateId){
		
		return this.technicalSkillService.findAllByCandidateId(candidateId);
	}
}
