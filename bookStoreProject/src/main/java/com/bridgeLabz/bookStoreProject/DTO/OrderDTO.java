package com.bridgeLabz.bookStoreProject.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

	
	private int price;
	private int quantity;
	private String address;
	private int userId;
	private int bookId;
	private boolean cancel;
}
