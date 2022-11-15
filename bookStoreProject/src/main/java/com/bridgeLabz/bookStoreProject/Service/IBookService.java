package com.bridgeLabz.bookStoreProject.Service;

import java.util.List;

import com.bridgeLabz.bookStoreProject.DTO.BookDTO;
import com.bridgeLabz.bookStoreProject.Model.BookModel;

public interface IBookService {

	BookModel addDataDb(BookDTO bookData);

	List<BookModel> getDataDb(int id);

	BookModel getdetailById(int id);

	Integer deletedetailsById(int id);

	BookModel getdetailByBookName(String bookName);

	BookModel updateBookById(BookDTO data, int id);

	String updateBookQuantityById(int id, int quantity);

	List<BookModel> sortBookByPriceAsc(int id);

	List<BookModel> sortBookByPriceDsc(int id);

}
