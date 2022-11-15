package com.bridgeLabz.bookStoreProject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.bookStoreProject.DTO.LoginDTO;
import com.bridgeLabz.bookStoreProject.DTO.ResponseDTO;
import com.bridgeLabz.bookStoreProject.DTO.UserDTO;
import com.bridgeLabz.bookStoreProject.Model.UserModel;
import com.bridgeLabz.bookStoreProject.Service.IUserService;

@RestController
public class UserContoller {
	
	@Autowired
	IUserService userService;

	
	// registering the user
		@PostMapping("/adding")
		public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserDTO userInfo) {
			String user = userService.registering(userInfo);
			ResponseDTO responseDto = new ResponseDTO("User is registered", user);
			return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
			
		}
	
	// get all user data
	@GetMapping("/getAll/{token}")
	public ResponseEntity<ResponseDTO> getAllDetail(@PathVariable String token ) {
		List<UserModel> user = userService.getDbDetail(token);
		ResponseDTO responseDTO = new ResponseDTO("All User details found!", user);
		return new ResponseEntity(responseDTO, HttpStatus.OK);
	}
	
	//get data by id
	@GetMapping("/get/{token}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable String token) {
		UserModel response = userService.getdetailById(token);
		ResponseDTO responseDto = new ResponseDTO("Id is found", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	//get data by email
	@GetMapping("/getemail/{email}")
	public ResponseEntity<ResponseDTO> getContactDetailByEmail(@PathVariable String email) {
		UserModel res = userService.getContactByemailId(email);
		ResponseDTO responsedto = new ResponseDTO("Get call by state is successfull", res);
		return new ResponseEntity(responsedto, HttpStatus.OK);
	}
	
	//forgot the password
	@PutMapping("/ForgetPassword/{email}")
	public ResponseEntity<ResponseDTO> forgetPassword(@RequestBody LoginDTO data, @PathVariable String email){
		String response = userService.forgotPassword(data, email);
		ResponseDTO responseDto = new ResponseDTO("password added successfully", response);
		return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
		
	}
	
	//login
	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> loggingEmail(@RequestBody LoginDTO data ){
		String response = userService.loginMail(data);
		ResponseDTO responseDto = new ResponseDTO("login is successful for user", response);
		return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
		
	}
	
	//change password
	@PutMapping("/changePass/{newPassword}")
	public ResponseEntity<ResponseDTO> changePassword(@RequestBody LoginDTO data, @PathVariable String newPassword ){
		String response = userService.passwordChange(data, newPassword);
		ResponseDTO responseDto = new ResponseDTO("password change has is successful for user", response);
		return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
		
	}

	//updating user by email
	@PutMapping("/updateName/{email}")
	public ResponseEntity<ResponseDTO> updateUserName(@PathVariable String email, @RequestHeader String name){
		String response = userService.updatingUserName(email, name);
		ResponseDTO responseDto = new ResponseDTO("Name is updated successful for user", response);
		return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
		
	}
		
}
