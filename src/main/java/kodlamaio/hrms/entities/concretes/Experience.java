package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experiences")
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_id")
	private int id;
	
	@Column(name = "company")
	private String company;
	
	@Column(name="position")
	private String position;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name = "date_to" , nullable = true)
	private Date dateTo;
	
	@Column(name="current" , nullable = true)
	private String current;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Candidate candidate;
}
