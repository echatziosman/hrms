package kodlamaio.hrms.business.concretes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.core.fakeServices.abstracts.ImageUploadServices;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ImageDao;
import kodlamaio.hrms.entities.concretes.Image;

@Service
public class ImageManager implements ImageService{

	private ImageDao imageDao;
	private ImageUploadServices imageUploadServices;
	
	@Autowired
	public ImageManager(ImageDao imageDao, ImageUploadServices imageUploadServices) {
		super();
		this.imageDao = imageDao;
		this.imageUploadServices = imageUploadServices;
	}

	@Override
	public Result add(Image image, MultipartFile imageFile) {
		
		@SuppressWarnings("unchecked")
		
		Map<String, String> imageUpload = this.imageUploadServices.imageUploadFileToSystem(imageFile).getData();
		
		image.setUrl(imageUpload.get("url"));
		this.imageDao.save(image);
		
		return new SuccessResult("Image saved");
	}

	@Override
	public Result update(Image image) {
		
		this.imageDao.save(image);
		return new SuccessResult("Image updated");
	}

	@Override
	public Result delete(int id) {
		
		this.imageDao.deleteById(id);
		
		return new SuccessResult("Image Deleted");
	}

	@Override
	public DataResult<Image> findByCandidateId(int candidateId) {
		
		if(this.imageDao.findByCandidateId(candidateId) != null) {
			
			return new SuccessDataResult<Image>(this.imageDao.findByCandidateId(candidateId),"Candidate's image");
		}
		return new ErrorDataResult<Image>("User does not have an image");
		
	}

}
