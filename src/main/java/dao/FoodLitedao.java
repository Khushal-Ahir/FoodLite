package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import database.Customer;
import database.FoodItem;
import database.Hotel;
import foodlite.CSignUp;

public class FoodLitedao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	
//	SAVE OPRATION
	
	public void saveCustomer(Customer customer) {
		transaction.begin();
		manager.persist(customer);
		transaction.commit();
	}

	public void saveFoodItem(FoodItem fooditem) {
		transaction.begin();
		manager.persist(fooditem);
		transaction.commit();
	}
	
	public void saveHotel(Hotel hotel) {
		transaction.begin();
		manager.persist(hotel);
		transaction.commit();
	}

//	GET OR FETCH OPRATION
	
	public List<Customer> custByEmail(String email) {
		return manager.createQuery("select mail from Customer mail where email = ?1").setParameter(1, email)
				.getResultList();
	}

	public List<Hotel> hotelByEmail(String email) {
		return manager.createQuery("select mail from Hotel mail where email = ?1").setParameter(1, email)
				.getResultList();
	}

	public List<FoodItem> fetchFoodByHotel(int id){
		return manager.createQuery("select x from FoodItem x where hotel_id = ?1").setParameter(1, id).getResultList();
	}
	
	public List<FoodItem> fetchFoodByCustomer (int id){
		return manager.createQuery("select x from FoodItem x").getResultList();
	}
	
	public List<Hotel> fetchHotel(){
		return manager.createQuery("select x from Hotel x").getResultList();
	}
	
	public FoodItem fetchFoodById(int id) {
		return manager.find(FoodItem.class, id);
	}
	
//	DELETE OPRATION
	
	public void deleteFoodItem(FoodItem foodItem) {
		transaction.begin();
		manager.remove(foodItem);
		transaction.commit();
	}

//	UPDATE OPRATION
	
	public void updateFoodItem(FoodItem foodItem) {
		transaction.begin();
		manager.merge(foodItem);
		transaction.commit();
	}
}
