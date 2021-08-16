package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Image;

@RestController
@RequestMapping("/api/photos")
public class ImagesController {

	private CandidateService candidateService;
	private ImageService imageService;
	
	@Autowired
	public ImagesController(CandidateService candidateService, ImageService imageService) {
		super();
		this.candidateService = candidateService;
		this.imageService = imageService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestParam int candidateId,@RequestParam MultipartFile imageFile) {
		
		Candidate candidate = this.candidateService.findById(candidateId).getData();
		
		Image image = new Image();
		image.setCandidate(candidate);
		return this.imageService.add(image, imageFile);
		
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestParam int imageId) {
		
		return this.imageService.delete(imageId);
	}
	
	@GetMapping("/findByCandidateId")
	public DataResult<Image> findByCandidateId(int candidateId){
		
		return this.imageService.findByCandidateId(candidateId);
	}
}
