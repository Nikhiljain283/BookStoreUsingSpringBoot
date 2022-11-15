package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.bookStoreProject.DTO.OrderDTO;
import com.bridgeLabz.bookStoreProject.Exception.CustomException;
import com.bridgeLabz.bookStoreProject.Model.BookModel;
import com.bridgeLabz.bookStoreProject.Model.CartModel;
import com.bridgeLabz.bookStoreProject.Model.OrderModel;
import com.bridgeLabz.bookStoreProject.Model.UserModel;
import com.bridgeLabz.bookStoreProject.Repository.BookRepository;
import com.bridgeLabz.bookStoreProject.Repository.OrderRepository;
import com.bridgeLabz.bookStoreProject.Repository.UserRepository;
import com.bridgeLabz.bookStoreProject.Util.EmailSenderService;
import com.bridgeLabz.bookStoreProject.Util.TokenUtil;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	BookRepository bookRepo;
	

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	EmailSenderService emailSenderService;

	@Override
	public OrderModel addOrderDataDb(OrderDTO orderData) {
		Optional<UserModel> userModel = userRepo.findById(orderData.getUserId());
		Optional<BookModel> bookModel = bookRepo.findById(orderData.getBookId());
		if (userModel.isPresent() && bookModel.isPresent()) {
			OrderModel orderModel = new OrderModel(orderData.getPrice(), orderData.getQuantity(),
					orderData.getAddress(), userModel.get(), bookModel.get(), orderData.isCancel());
			orderRepo.save(orderModel);
			String token = tokenUtil.createToken(orderModel.getOrderID());
			emailSenderService.sendEmail(userModel.get().getEmail(), "order id is as follow ",
					"hii " + userModel.get().getFirstName() + "  Token for Order Data : "+token);
			return orderModel;
		} else {
			throw new CustomException("Order is not added");
		}
	}

	@Override
	public List<OrderModel> getAllOrders() {
		if (orderRepo != null) {
			return orderRepo.findAll();
		} else {
			throw new CustomException("Order is empty");
		}

	}

	@Override
	public OrderModel gettingOrderById(int id) {

		if (orderRepo.findById(id).isPresent()) {
			return orderRepo.findById(id).get();
		} else {
			throw new CustomException("Order is empty");
		}

	}

	@Override
	public Integer deleteOrderdetailsById(int id) {
		if (orderRepo.findById(id).isPresent()) {
			orderRepo.deleteById(id);
			Optional<UserModel> userModel = userRepo.findById(id);
			orderRepo.deleteById(id);
			emailSenderService.sendEmail(userModel.get().getEmail(), "Deleting order ",
					"hii " + userModel.get().getFirstName() + "order is deleted  ");
			return id;
		} else {
			throw new CustomException("order with " + id + " id is not found");
		}

	}

	@Override
	public OrderModel updatingOrderById(OrderDTO data, int id) {
		Optional<UserModel> userModel = userRepo.findById(data.getUserId());
		Optional<BookModel> bookModel = bookRepo.findById(data.getBookId());
		if (orderRepo.findById(id).isPresent()) {
			OrderModel orderModel = orderRepo.findById(id).get();
			orderModel.setUser(userModel.get());
			orderModel.setBook(bookModel.get());
			orderModel.setQuantity(data.getQuantity());
			orderModel.setAddress(data.getAddress());
			orderModel.setPrice(data.getPrice());
			orderModel.setCancel(data.isCancel());
			orderRepo.save(orderModel);
			return orderModel;
		} else {
			throw new CustomException("Order with " + id + " id is not found.");
		}
	}

}
