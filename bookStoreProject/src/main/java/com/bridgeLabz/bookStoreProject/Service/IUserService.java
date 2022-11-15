package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;

import com.bridgeLabz.bookStoreProject.DTO.LoginDTO;
import com.bridgeLabz.bookStoreProject.DTO.UserDTO;
import com.bridgeLabz.bookStoreProject.Model.UserModel;

public interface IUserService {

	String registering(UserDTO userInfo);

	List<UserModel> getDbDetail(String token);
	
	UserModel getdetailById(String token);

	UserModel getContactByemailId(String email);

	String forgotPassword(LoginDTO data, String email);

	String loginMail(LoginDTO data);

	String passwordChange(LoginDTO data, String email);

	String updatingUserName(String email, String name);

}
