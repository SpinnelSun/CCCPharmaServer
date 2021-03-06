package br.edu.ufcg.cccpharma.sale;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ufcg.cccpharma.user.User;
import br.edu.ufcg.cccpharma.soldProduct.SoldProduct;

/**
 * A Sale object models the real sales of the CCCPharma system. A sale is unique
 * identified by an autogenerated ID number. Each Sale object has a set of its
 * SoldProducts and the User who purchased them as attributes. Also, there is a
 * cost attribute which value is calculated based on the price of all sold
 * products of the set. The sales are described in one of our database tables.
 * 
 * @author Douglas Pereira de Lima
 * @author Fanny Batista Vieira
 * @author Mateus de Lima Oliveira
 * @author Matheus Alves dos Santos
 * 
 * @since 2018-11-12
 * @version 1.0
 * 
 */
@Entity
@Table(name = "tb_sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
	@JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
	private User user;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "sold_product_id")
	private Set<SoldProduct> soldProducts;

	private double cost;

	/**
	 * Creates a Sale object with nulled attributes.
	 */
	public Sale() {}

	/**
	 * Creates a Sale object based on the purchaser User and on a set of
	 * SoldProducts.
	 * 
	 * @param user         The User related to the purchase.
	 * @param soldProducts A set of all the SoldProducts that have been purchased.
	 * 
	 */
	public Sale(User user, Set<SoldProduct> soldProducts) {
		this.user = user;
		this.soldProducts = soldProducts;
		this.cost = this.calculateCost(soldProducts);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}
	
	public void setCost() {
		this.cost = calculateCost(getSoldProducts());
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Set<SoldProduct> getSoldProducts() {
		return this.soldProducts;
	}

	public void setSoldProducts(Set<SoldProduct> soldProducts) {
		this.soldProducts = soldProducts;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@JsonIgnore
	public String getEmail() {
		return user.getEmail();
	}


	/**
	 * Generates a hash code for the Sale object based on its ID number.
	 * 
	 * @return The generated hash code for the Sale object.
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Verifies equality between the Sale object and the Object given as parameter.
	 * This equality will only exists if the given Object is also an Sale object and
	 * possesses the same ID number.
	 * 
	 * @param obj An object to be compared to the Sale.
	 * 
	 * @return A boolean that represents the comparison result.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Generates a textual representation of the Sale object based on its ID number
	 * and its total cost.
	 * 
	 * @return The textual representation of the Sale.
	 * 
	 */
	@Override
	public String toString() {
		return "Sale ID: " + this.getId() + " - Cost: " + this.getCost();
	}

	/**
	 * Calculates the total cost of the given set of SoldProducts and returns it. It
	 * uses the price and the quantity of each one of the products in the set.
	 * 
	 * @return The total cost of the given SoldProducts.
	 * 
	 */
	private double calculateCost(Set<SoldProduct> soldProducts) {
		double totalCost = 0.0;
		for (SoldProduct soldProduct : soldProducts) {
			totalCost += (soldProduct.getQuantity() * soldProduct.getProduct().getPrice());
		}

		return totalCost;
	}
	
	/**
	 * 
	 * Increment the quantity of each product in one Sale. It gets the current amount 
	 * of a product and increment the quantity of that product that was in this sale.
	 * 
	 */
	public void incrementProductQuantity() {
		for (SoldProduct soldProduct : soldProducts) {
			int amount = soldProduct.getProduct().getAmount() + soldProduct.getQuantity();
			soldProduct.getProduct().setAmount(amount);
		}
	}

}
