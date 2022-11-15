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

import com.bridgeLabz.bookStoreProject.DTO.CartDTO;
import com.bridgeLabz.bookStoreProject.DTO.OrderDTO;
import com.bridgeLabz.bookStoreProject.DTO.ResponseDTO;
import com.bridgeLabz.bookStoreProject.Model.CartModel;
import com.bridgeLabz.bookStoreProject.Model.OrderModel;
import com.bridgeLabz.bookStoreProject.Service.IOrderService;

@RestController
public class OrderController {

	@Autowired
	IOrderService orderService;

	// Adding order
	@PostMapping("/insertOrder")
	public ResponseEntity<ResponseDTO> addOrderData(@RequestBody OrderDTO orderData) {
		OrderModel response = orderService.addOrderDataDb(orderData);
		ResponseDTO responseDto = new ResponseDTO("order is placed successfully", response);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
	
	//get all order
	@GetMapping("/getAllOrder")
	public ResponseEntity<ResponseDTO> getAllOrder() {
		List<OrderModel> response = orderService.getAllOrders();
		ResponseDTO responseDto = new ResponseDTO("Retrived all oders", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Get order by id
	@GetMapping("/getOrderById/{id}")
	public ResponseEntity<ResponseDTO> getOrderDetailDbId(@PathVariable int id) {
		OrderModel response = orderService.gettingOrderById(id);
		ResponseDTO responseDto = new ResponseDTO("order is found", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//Delete order by Id
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<ResponseDTO> deleteOrderById(@PathVariable int id) {
		Integer response = orderService.deleteOrderdetailsById(id);
		ResponseDTO responseDTO = new ResponseDTO("order is deleted!", "Deleted id : " + response);
		return new ResponseEntity<>(responseDTO, HttpStatus.GONE);
	}
	
	//Update order by Id
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<ResponseDTO> updateOrderById(@RequestBody OrderDTO data, @PathVariable int id) {
		OrderModel response = orderService.updatingOrderById(data, id);
		ResponseDTO responseDto = new ResponseDTO("successfully updated order", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
