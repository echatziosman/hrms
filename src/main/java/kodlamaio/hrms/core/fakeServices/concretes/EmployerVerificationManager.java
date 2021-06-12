package kodlamaio.hrms.core.fakeServices.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.fakeServices.abstracts.EmployerVerificationService;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerVerificationManager implements EmployerVerificationService{

	@Override
	public boolean employerVerificationByEmployee(Employer employer) {
		
		return true;
	}

}
