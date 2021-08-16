package kodlamaio.hrms.core.fakeServices.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.fakeServices.abstracts.EmailService;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmailManager implements EmailService {

	@Override
	public boolean linkToVerify(User user) {
		
		return true;
	}

}
