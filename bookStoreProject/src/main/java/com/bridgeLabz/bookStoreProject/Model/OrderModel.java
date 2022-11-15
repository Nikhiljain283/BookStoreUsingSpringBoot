package com.bridgeLabz.bookStoreProject.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bridgeLabz.bookStoreProject.DTO.OrderDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private int orderID;
	private LocalDate date = LocalDate.now();
	private int price;
	private int quantity;
	private String address;
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookModel book;
	private boolean cancel;
	

	public OrderModel(int price, int quantity, String address, UserModel userId, BookModel bookId, boolean cancel ) {
		
		this.price = price;
		this.quantity = quantity;
		this.address = address;
		this.user = userId;
		this.book = bookId;
		this.cancel = cancel;
		
	}

}
