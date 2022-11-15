package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.bookStoreProject.DTO.LoginDTO;
import com.bridgeLabz.bookStoreProject.DTO.UserDTO;
import com.bridgeLabz.bookStoreProject.Exception.CustomException;
import com.bridgeLabz.bookStoreProject.Model.UserModel;
import com.bridgeLabz.bookStoreProject.Repository.UserRepository;
import com.bridgeLabz.bookStoreProject.Util.EmailSenderService;
import com.bridgeLabz.bookStoreProject.Util.TokenUtil;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository repo;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	EmailSenderService emailSenderService;

	public String registering(UserDTO userInfo) {
		UserModel model = new UserModel(userInfo);
		repo.save(model);
		String token = tokenUtil.createToken(model.getUserId());
		emailSenderService.sendEmail(model.getEmail(), "checking email sender",
				"hii " + model.getFirstName() + "  Token for UserData : "+token);

		return token;
	}

	public List<UserModel> getDbDetail(String token) {

		int id = tokenUtil.decodeToken(token);
		if (repo.findById(id).isPresent()) {
			return repo.findAll();
		} else {
			throw new CustomException("Id is not present in table");
		}
	}

	public UserModel getdetailById(String token) {
		int id = tokenUtil.decodeToken(token);
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		} else {
			throw new CustomException("Contact with " + id + " id is not found.");
		}

	}

	public UserModel getContactByemailId(String email) {
		return repo.findDataByEmailId(email);
	}

	@Override
	public String forgotPassword(LoginDTO data, String email) {
		UserModel model = repo.findDataByEmailId(email);
		if (model != null) {
			model.setPassword(data.getPassword());
			repo.save(model);
			return "Password is updated";
		} else {
			throw new CustomException("Email does not exist.");
		}
	}

	@Override
	public String loginMail(LoginDTO data) {
		Optional<UserModel> model = Optional.ofNullable(repo.findDataByEmailId(data.getEmail()));
		// UserModel model = repo.findDataByEmailId(data.getEmail());
		if (model.isPresent() && model.get().getPassword().equals(data.getPassword())) {
			return "user login successful";
		} else {
			throw new CustomException("longin error");
		}
	}

	public String passwordChange(LoginDTO data, String newPassword) {
		UserModel model = repo.findDataByEmailId(data.getEmail());
		if (model != null && model.getPassword().equals(data.getPassword())) {
			model.setPassword(newPassword);
			repo.save(model);
			return "password changed successfully";

		} else {
			throw new CustomException("Password is not changed");
		}

	}

	@Override
	public String updatingUserName(String email, String name) {
		UserModel model = repo.findDataByEmailId(email);
		if (model != null) {
			model.setFirstName(name);
			repo.save(model);
			return "Updated user name";
		} else {
			throw new CustomException("Email does not exist");
		}
	}

}
