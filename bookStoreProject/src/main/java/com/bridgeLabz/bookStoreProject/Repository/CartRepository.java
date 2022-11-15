package com.bridgeLabz.bookStoreProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgeLabz.bookStoreProject.Model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {

}
