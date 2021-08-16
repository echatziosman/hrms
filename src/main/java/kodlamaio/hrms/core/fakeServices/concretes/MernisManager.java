package kodlamaio.hrms.core.fakeServices.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.fakeServices.abstracts.MernisService;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class MernisManager implements MernisService{

	@Override
	public boolean userVerification(User user) {
		
		return true;
		
	}

}
