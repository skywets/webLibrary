package service;


import dao.BookDao;
import entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookService {
    private BookDao bookDao;

    public List<Book> getAll() {
        return bookDao.getAll();
    }
    public Book getItem(long id) {
        return bookDao.get(id).orElseThrow();
    }

    public void createBook(Book book) {
        bookDao.create(book);
    }

    public void editBook(long id, Book book) {
        book.setId(id);
        bookDao.update(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }
}
