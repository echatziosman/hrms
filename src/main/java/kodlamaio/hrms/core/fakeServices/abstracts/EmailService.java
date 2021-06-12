package kodlamaio.hrms.core.fakeServices.abstracts;

import kodlamaio.hrms.entities.concretes.User;

public interface EmailService {

	boolean linkToVerify(User user);
}
