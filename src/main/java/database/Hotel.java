package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private String name;
	private String hotel_name;
	private String email;
	private String password;
	private String address;

	public Hotel(String name, String email, String password, String hotel_name, String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.hotel_name = hotel_name;
		this.address = address;
	}

	public Hotel() {
		super();
	}
}
