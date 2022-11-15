package com.bridgeLabz.bookStoreProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.bookStoreProject.DTO.BookDTO;
import com.bridgeLabz.bookStoreProject.DTO.ResponseDTO;
import com.bridgeLabz.bookStoreProject.Model.BookModel;
import com.bridgeLabz.bookStoreProject.Service.IBookService;

@RestController
public class BookController {

	@Autowired
	IBookService bookService;

	//Adding book
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> addData(@RequestBody BookDTO bookData) {
		BookModel response = bookService.addDataDb(bookData);
		ResponseDTO responseDto = new ResponseDTO("Book is added successfully", response);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	//get all book
	@GetMapping("/GetAll/{id}")
	public ResponseEntity<ResponseDTO> addAllData(@PathVariable int id) {
		List<BookModel> response = bookService.getDataDb(id);
		ResponseDTO responseDto = new ResponseDTO("Retrived all book", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Get book by id
	@GetMapping("/GetByID/{id}")
	public ResponseEntity<ResponseDTO> getBookDetailDbId(@PathVariable int id) {
		BookModel response = bookService.getdetailById(id);
		ResponseDTO responseDto = new ResponseDTO("Book is found", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
		
	//Delete book by Id
	@DeleteMapping("/Delete/{id}")
	public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable int id) {
		Integer response = bookService.deletedetailsById(id);
		ResponseDTO responseDTO = new ResponseDTO("book is deleted!", "Deleted id : " + response);
		return new ResponseEntity<>(responseDTO, HttpStatus.GONE);
	}
	
	//Get book by name
	@GetMapping("/GetByName/{bookName}")
	public ResponseEntity<ResponseDTO> getBookDetailDbName(@PathVariable String bookName) {
		BookModel response = bookService.getdetailByBookName(bookName);
		ResponseDTO responseDto = new ResponseDTO("book is found", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Update book by Id
	@PutMapping("/Update/{id}")
	public ResponseEntity<ResponseDTO> updateById(@RequestBody BookDTO data, @PathVariable int id) {
		BookModel response = bookService.updateBookById(data, id);
		ResponseDTO responseDto = new ResponseDTO("successfully updated book name", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Update book by quantity
	@PutMapping("/UpdateQuantity/{id}")
	public ResponseEntity<ResponseDTO> updateQuantityById(@PathVariable int id, @RequestHeader int quantity) {
		String response = bookService.updateBookQuantityById(id, quantity);
		ResponseDTO responseDto = new ResponseDTO("successfully updated quantity", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//sorting book in ascending order by price
	@GetMapping("/SortingAsc/{id}")
	public ResponseEntity<ResponseDTO> sortByPriceAsc(@PathVariable int id) {
		List<BookModel> response = bookService.sortBookByPriceAsc(id);
		ResponseDTO responseDto = new ResponseDTO("Sorting book by price in ascending order", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//sorting book in descending order by price
		@GetMapping("/SortingDsc/{id}")
		public ResponseEntity<ResponseDTO> sortByPriceDsc(@PathVariable int id) {
			List<BookModel> response = bookService.sortBookByPriceDsc(id);
			ResponseDTO responseDto = new ResponseDTO("Sorting book by price in descing order", response);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}
	
	
}