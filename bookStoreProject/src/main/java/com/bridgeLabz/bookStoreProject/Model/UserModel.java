package com.bridgeLabz.bookStoreProject.Model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.bridgeLabz.bookStoreProject.DTO.LoginDTO;
import com.bridgeLabz.bookStoreProject.DTO.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String password;
	private String email;
	
//	@ElementCollection
//	@CollectionTable(name = "User_email", joinColumns = @JoinColumn(name = "id"))
//	@Column(name = "email")
//	private List<String> email;

	public UserModel(UserDTO userDto) {

		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.email = userDto.getEmail();
		this.address = userDto.getAddress();
		this.birthDate = userDto.getBirthDate();
		this.password = userDto.getPassword();

	}
	
	public UserModel(LoginDTO loginDto) {
		this.email = loginDto.getEmail();
		this.password = loginDto.getPassword();
	}

}
