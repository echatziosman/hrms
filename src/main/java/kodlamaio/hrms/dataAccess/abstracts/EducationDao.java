package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer>{

	Education findById(int id);
	
	List<Education> findAllByCandidateId(int id);
	
	List<Education> findAllByCandidateIdOrderByFromDateDesc(int id);
}
