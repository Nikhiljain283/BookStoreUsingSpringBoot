package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.bookStoreProject.DTO.CartDTO;
import com.bridgeLabz.bookStoreProject.Exception.CustomException;
import com.bridgeLabz.bookStoreProject.Model.BookModel;
import com.bridgeLabz.bookStoreProject.Model.CartModel;
import com.bridgeLabz.bookStoreProject.Model.UserModel;
import com.bridgeLabz.bookStoreProject.Repository.BookRepository;
import com.bridgeLabz.bookStoreProject.Repository.CartRepository;
import com.bridgeLabz.bookStoreProject.Repository.UserRepository;

@Service
public class CartService implements ICartService {

	@Autowired
	CartRepository cartRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	BookRepository bookRepo;

	public CartModel addCartDataDb(CartDTO cartDto) {
		Optional<UserModel> userModel = userRepo.findById(cartDto.getUserId());
		Optional<BookModel> bookModel = bookRepo.findById(cartDto.getBookId());
		if (userModel.isPresent() && bookModel.isPresent()) {
			if (cartDto.getQuantity() <= bookModel.get().getQuantity()) {
				CartModel cartModel = new CartModel(userModel.get(), bookModel.get(), cartDto.getQuantity());
				cartRepo.save(cartModel);
				return cartModel;
			} else {
				throw new CustomException("Cart quantity is exceeding");
			}
		} else {
			throw new CustomException("Book is not present");
		}
	}

	public List<CartModel> getAllCarts() {
		if (cartRepo != null) {
			return cartRepo.findAll();
		} else {
			throw new CustomException("Cart is empty");
		}

	}

	public CartModel gettingCartById(int id) {
		if (cartRepo.findById(id).isPresent()) {
			return cartRepo.findById(id).get();
		} else {
			throw new CustomException("Cart id does not exist");
		}
	}

	@Override
	public Integer deleteCartdetailsById(int id) {
		if (cartRepo.findById(id).isPresent()) {
			cartRepo.deleteById(id);
			return id;
		} else {
			throw new CustomException("Cart with " + id + " id is not found");
		}
	}

	@Override
	public CartModel updatingCartById(CartDTO cartDto, int id) {
		Optional<UserModel> userModel = userRepo.findById(cartDto.getUserId());
		Optional<BookModel> bookModel = bookRepo.findById(cartDto.getBookId());
		if (cartRepo.findById(id).isPresent()) {
			CartModel cartModel = cartRepo.findById(id).get();
			cartModel.setUser(userModel.get());
			cartModel.setBook(bookModel.get());
			cartModel.setQuantity(cartDto.getQuantity());
			cartRepo.save(cartModel);
			return cartModel;
		} else {
			throw new CustomException("Book with " + id + " id is not found in Book store");
		}
	}

}
