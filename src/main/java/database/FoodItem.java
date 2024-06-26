package database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private String name;
	private double price;
	private String type;
	private int stock;
	
	@Lob
	private byte[] image;
	
	@ManyToOne
	Hotel hotel;

	public FoodItem(String name, double price, String type, int stock, byte[] image, Hotel hotel) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = stock;
		this.image = image;
		this.hotel = hotel;
	}

	public FoodItem() {
		super();
	}
}
