package kodlamaio.hrms.core.fakeServices.concretes;



import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.Cloudinary.CloudinaryManager;
import kodlamaio.hrms.core.fakeServices.abstracts.ImageUploadServices;
import kodlamaio.hrms.core.utilities.results.DataResult;

@Service
public class CloudinaryManagerAdapter implements ImageUploadServices{

	@Override
	@SuppressWarnings("rawtypes")
	public DataResult<Map> imageUploadFileToSystem(MultipartFile imageFile) {
		
		CloudinaryManager cloudinaryManager = new CloudinaryManager();
	    DataResult<Map> uploadMap = cloudinaryManager.imageUploadFile(imageFile);
		
		return uploadMap;		
	}

}
