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
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.bookStoreProject.DTO.BookDTO;
import com.bridgeLabz.bookStoreProject.DTO.CartDTO;
import com.bridgeLabz.bookStoreProject.DTO.ResponseDTO;
import com.bridgeLabz.bookStoreProject.Model.BookModel;
import com.bridgeLabz.bookStoreProject.Model.CartModel;
import com.bridgeLabz.bookStoreProject.Service.ICartService;

@RestController
public class CartController {

	@Autowired
	ICartService cartService;

	// Adding cart
	@PostMapping("/insertCart")
	public ResponseEntity<ResponseDTO> addCartData(@RequestBody CartDTO cartData) {
		CartModel response = cartService.addCartDataDb(cartData);
		ResponseDTO responseDto = new ResponseDTO("Book is added successfully", response);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	//get all cart
	@GetMapping("/getAllCart")
	public ResponseEntity<ResponseDTO> getAllCart() {
		List<CartModel> response = cartService.getAllCarts();
		ResponseDTO responseDto = new ResponseDTO("Retrived all carts", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Get cart by id
	@GetMapping("/getCartById/{id}")
	public ResponseEntity<ResponseDTO> getCartDetailDbId(@PathVariable int id) {
		CartModel response = cartService.gettingCartById(id);
		ResponseDTO responseDto = new ResponseDTO("Cart is found", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Delete cart by Id
	@DeleteMapping("/deleteCart/{id}")
	public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable int id) {
		Integer response = cartService.deleteCartdetailsById(id);
		ResponseDTO responseDTO = new ResponseDTO("Cart is deleted!", "Deleted id : " + response);
		return new ResponseEntity<>(responseDTO, HttpStatus.GONE);
	}
	
	//Update cart by Id
	@PutMapping("/updateCart/{id}")
	public ResponseEntity<ResponseDTO> updateCartById(@RequestBody CartDTO data, @PathVariable int id) {
		CartModel response = cartService.updatingCartById(data, id);
		ResponseDTO responseDto = new ResponseDTO("successfully updated cart", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	

}
