package kodlamaio.hrms.Cloudinary;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;

@Service
public class CloudinaryManager {

	private Cloudinary cloudinary;

	public CloudinaryManager() {
	
		this.cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "spacenes",
				  "api_key", "199874682215328",
				  "api_secret", "LiejxSmsjLFvElV9h_7pzg7CWv8"));
	}
	
	@SuppressWarnings("rawtypes")
	public DataResult<Map> imageUploadFile(MultipartFile imageFile){
		try {
			
			@SuppressWarnings("unchecked")
			Map<String, String> uploadResult = (Map<String, String>) cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
			
			return new SuccessDataResult<>(uploadResult);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return new ErrorDataResult<>();
	}
}
