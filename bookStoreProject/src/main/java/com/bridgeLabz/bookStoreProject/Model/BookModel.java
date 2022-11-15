package com.bridgeLabz.bookStoreProject.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bridgeLabz.bookStoreProject.DTO.BookDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String bookName;
	private String autherName;
	private String bookDescription;
	private String bookImg;  
	private int price;
	private int quantity;
	
	public BookModel(BookDTO bookData) {
		
		this.bookName = bookData.getBookName();
		this.autherName = bookData.getAutherName();
		this.bookDescription = bookData.getBookDescription();
		this.bookImg = bookData.getBookImg();
		this.price = bookData.getPrice();
		this.quantity = bookData.getQuantity();
		
	}
}
