package com.bridgeLabz.bookStoreProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgeLabz.bookStoreProject.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	@Query(value = "select * from new_database.user_model where user_model.email = :email", nativeQuery = true)
	UserModel findDataByEmailId(String email);
}
