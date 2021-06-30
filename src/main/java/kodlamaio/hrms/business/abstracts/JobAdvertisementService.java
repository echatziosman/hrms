package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {

	Result add(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> findByIsActive(boolean isActive);
	
	DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreatedAtDesc();

	DataResult<List<JobAdvertisement>> findByIsActiveTrueAndEmployer_CompanyNameIs(String companyName);
	
	Result isActive(int id,boolean isActive);
}
