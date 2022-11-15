package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;

import com.bridgeLabz.bookStoreProject.DTO.OrderDTO;
import com.bridgeLabz.bookStoreProject.Model.OrderModel;

public interface IOrderService {

	OrderModel addOrderDataDb(OrderDTO orderData);

	List<OrderModel> getAllOrders();

	OrderModel gettingOrderById(int id);

	Integer deleteOrderdetailsById(int id);

	OrderModel updatingOrderById(OrderDTO data, int id);

}
