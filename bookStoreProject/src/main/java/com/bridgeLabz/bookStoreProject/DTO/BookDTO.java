package com.bridgeLabz.bookStoreProject.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {

	
	private String bookName;
	private String autherName;
	private String bookDescription;
	private String bookImg;  
	private int price;
	private int quantity;
}
