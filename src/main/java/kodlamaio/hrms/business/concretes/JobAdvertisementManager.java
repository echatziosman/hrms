package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementDao jobAdvertisementDao;
	
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}
	
	
	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("Job Advertisement saved");
	}




	@Override
	public DataResult<List<JobAdvertisement>> findByIsActive(boolean isActive) {
		
		if(isActive == true) {
			
			return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActive(isActive), "Active Job Advertisements");
			
		}
		else {
			return new ErrorDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActive(false),"Job Advertisements are passive");
		}
			
	
	}


	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreatedAtDesc() {
		
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueOrderByCreatedAtDesc(), "Aktif İş ilanları tarihe göre sıralandı");
	}


	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueAndEmployer_CompanyNameIs(String companyName) {
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueAndEmployer_CompanyNameIs(companyName), "Company's job advertisements");
	}


	@Override
	public Result isActive(int id,boolean isActive) {
		
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.findById(id);
		
		if(this.jobAdvertisementDao.findById(id) != null) //Boyle bir ilan varsa
		{
			if(jobAdvertisement.isActive() == true && !isActive) {
				
				jobAdvertisement.setActive(isActive); // isActive == false
				this.jobAdvertisementDao.save(jobAdvertisement); //Degisikligi Veritabanina kaydet
				
				return new SuccessResult("Job advertisement changed to passive");
			}
			else if(!jobAdvertisement.isActive() && !isActive){
				
				return new ErrorResult(false, "Job Advertisement is already passive");
				
			}
			else {
				return new ErrorResult(false, "Please check your input");
			}
	
		}
		
			return new ErrorResult(false, "Job advertisement is not found");
			
		
	
		
	}

}
