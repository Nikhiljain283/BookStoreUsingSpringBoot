package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgeLabz.bookStoreProject.DTO.BookDTO;
import com.bridgeLabz.bookStoreProject.Exception.CustomException;
import com.bridgeLabz.bookStoreProject.Model.BookModel;
import com.bridgeLabz.bookStoreProject.Repository.BookRepository;
import com.bridgeLabz.bookStoreProject.Util.EmailSenderService;
import com.bridgeLabz.bookStoreProject.Util.TokenUtil;

@Service
public class BookService implements IBookService {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	EmailSenderService emailSenderService;

	public BookModel addDataDb(BookDTO bookData) {
		BookModel model = new BookModel(bookData);
		bookRepo.save(model);
		return model;
	}

	public List<BookModel> getDataDb(int id) {
		if (bookRepo.findById(id).isPresent()) {
			return bookRepo.findAll();
		} else {
			throw new CustomException("This id does not exist");
		}
	}

	public BookModel getdetailById(int id) {
	//	int id = tokenUtil.decodeToken(token);
		if (bookRepo.findById(id).isPresent()) {
			return bookRepo.findById(id).get();
		} else {
			throw new CustomException("Book with " + id + " id is not found in Book store");
		}
	}
	
	@Override
	public Integer deletedetailsById(int id) {
		if (bookRepo.findById(id).isPresent()) {
			bookRepo.deleteById(id);
			return id;
		} else {
			throw new CustomException("Book with " + id + " id is not found");
		}

	}
	
	@Override
	public BookModel getdetailByBookName(String bookName) {
		if (bookRepo.getBookByBookName(bookName) != null) {
			return bookRepo.getBookByBookName(bookName);
		} else {
			throw new CustomException("book with name is not found");
		}

	}

	@Override
	public BookModel updateBookById(BookDTO data, int id) {

		if (bookRepo.findById(id).isPresent()) {
			BookModel modelBook = bookRepo.findById(id).get();
			modelBook.setBookName(data.getBookName());
			modelBook.setAutherName(data.getAutherName());
			modelBook.setBookDescription(data.getBookDescription());
			modelBook.setBookImg(data.getBookImg());
			modelBook.setPrice(data.getPrice());
			modelBook.setQuantity(data.getQuantity());
			bookRepo.save(modelBook);
			return modelBook;
		} else {
			throw new CustomException("Book with " + id + " id is not found in Book store");
		}

	}

	@Override
	public String updateBookQuantityById(int id, int quantity) {
		if (bookRepo.findById(id).isPresent()) {
			BookModel existingData = bookRepo.findById(id).get();
			existingData.setQuantity(quantity);
			;
			BookModel res = bookRepo.save(existingData);
			return "Book quantity is updated successfully";
		} else {
			throw new CustomException("Book with " + id + " id is not found in Book store");
		}

	}

	public List<BookModel> sortBookByPriceAsc(int id) {
		if (bookRepo.findById(id).isPresent()) {
			List<BookModel> res = bookRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
			return res;
		} else {
			throw new CustomException("This id does not exist");
		}

	}

	public List<BookModel> sortBookByPriceDsc(int id) {
		if (bookRepo.findById(id).isPresent()) {
			List<BookModel> res = bookRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
			return res;
		} else {
			throw new CustomException("This id does not exist");
		}

	}

}
