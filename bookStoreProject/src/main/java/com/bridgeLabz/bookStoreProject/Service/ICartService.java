package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;

import com.bridgeLabz.bookStoreProject.DTO.CartDTO;
import com.bridgeLabz.bookStoreProject.Model.CartModel;

public interface ICartService {

	CartModel addCartDataDb(CartDTO cartData);

	List<CartModel> getAllCarts();

	CartModel gettingCartById(int id);

	Integer deleteCartdetailsById(int id);

	CartModel updatingCartById(CartDTO data, int id);

}
