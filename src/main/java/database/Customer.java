package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer {
	private String username;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private String email;
	private String password;
	private String cnf_password;
	private String address;
	public Customer(String username, String email, String password, String cnf_password, String address) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.cnf_password = cnf_password;
		this.address = address;
	}
	public Customer() {
		super();
	}
	
	
}

