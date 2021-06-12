package kodlamaio.hrms.core.fakeServices.abstracts;

import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerVerificationService {

	boolean employerVerificationByEmployee(Employer employer);
}
