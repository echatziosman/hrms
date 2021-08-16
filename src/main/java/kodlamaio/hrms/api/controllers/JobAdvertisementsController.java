package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("/findByIsActive")
	public DataResult<List<JobAdvertisement>> findByIsActive(@RequestParam boolean isActive){
		
		return this.jobAdvertisementService.findByIsActive(isActive);
	}
	
	@GetMapping("/findByIsActiveTrueOrderByCreatedAtDesc")
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreatedAtDesc(){
		
		return this.jobAdvertisementService.findByIsActiveTrueOrderByCreatedAtDesc();
	}
	
	@GetMapping("/findByIsActiveTrueAndEmployer_CompanyNameIs")
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueAndEmployer_CompanyNameIs(@RequestParam String companyName){
		
		return this.jobAdvertisementService.findByIsActiveTrueAndEmployer_CompanyNameIs(companyName);
	}
	
	@PostMapping("/isActive")
	public Result isActive(@RequestParam int id,@RequestParam boolean isActive) {
		
		return this.jobAdvertisementService.isActive(id, isActive);
	}
}
