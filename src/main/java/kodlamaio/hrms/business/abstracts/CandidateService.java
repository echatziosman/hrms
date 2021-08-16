package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.CvDto;

public interface CandidateService {

	Result register(Candidate candidate);
	
	DataResult<Candidate> findById(int candidateId);
	
	Result deleteById(int candidateId);
	
	DataResult<List<Candidate>> getall();
	
	DataResult<CvDto> getCandidateCv(int candidateId);
}
