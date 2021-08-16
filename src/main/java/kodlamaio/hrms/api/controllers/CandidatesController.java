package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

	private CandidateService candidateService;

	@Autowired
	public CandidatesController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody Candidate candidate) {
		return ResponseEntity.ok(this.candidateService.register(candidate));
	}
	
	@PostMapping("/delete")
	public Result deleteById(@RequestParam int candidateId) {
		
		return this.candidateService.deleteById(candidateId);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Candidate>> getall(){
		return this.candidateService.getall();
	}
	
	@GetMapping("/cv")
	public ResponseEntity<?> getCv(@RequestParam int candidateId){
		
		return ResponseEntity.ok(this.candidateService.getCandidateCv(candidateId));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		
        Map<String, String> validationErrors = new HashMap<String, String>();
		
		for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		//Burada hatalari bizim formata donusturuyoruz
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Dogrulama Hatalari");
		
		return errors;      
	}
	
}
