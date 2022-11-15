package com.bridgeLabz.bookStoreProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgeLabz.bookStoreProject.Model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {

	@Query(value = "select * from new_database.book_model where book_model.book_name = :bookName", nativeQuery = true)
	BookModel getBookByBookName(String bookName);
	
}
