package br.edu.ufcg.cccpharma.category;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category {
	
	private String name;
	private double discount;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
