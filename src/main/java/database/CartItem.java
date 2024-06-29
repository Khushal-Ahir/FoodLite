package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private String name;
	private double price;
	private int quantity;
	private String type;
	@Lob
	private byte[] image;

	public CartItem(String name, double price, int quantity, String type, byte[] image) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.image = image;
	}

	public CartItem() {
		super();
	}

}