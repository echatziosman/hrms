package kodlamaio.hrms.core.fakeServices.abstracts;



import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;


public interface ImageUploadServices {

	@SuppressWarnings("rawtypes")
	DataResult<Map> imageUploadFileToSystem(MultipartFile imageFile);
}
