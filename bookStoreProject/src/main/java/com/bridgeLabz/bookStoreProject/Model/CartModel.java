package com.bridgeLabz.bookStoreProject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table
public class CartModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId")
	private int cartID;
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookModel book;
	private int quantity;

	public CartModel(UserModel userId, BookModel bookId, int quantity ) {
		this.user = userId;
		this.book = bookId;
		this.quantity = quantity;
		
	}
	
	
}
