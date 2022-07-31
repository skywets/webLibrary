package controller;


import entity.Book;
import service.BookService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class BookMenu {
    Scanner sc = new Scanner(System.in);
    BookService bookService;
    CatalogMenu catalogMenu;
    Book book;
    private long id;
    private long catalogId;
    private String title;
    private String author;
    private String textBook;
    private int countOfPages;
    private Date publishingDate;
    private boolean popular;
    private boolean newRelease;
    private String genre;

    public BookMenu(BookService bookService, Book book, CatalogMenu catalogMenu) {
        this.bookService = bookService;
        this.book = book;
        this.catalogMenu = catalogMenu;
    }

    public void showAddBook() {
        System.out.println("Enter catalogId id: ");
        //ПРОБЛЕМЫ
        catalogId = catalogMenu.showAddCatalog();
        System.out.println("Enter nameBook: ");
        title = sc.next();
        System.out.println("Enter author: ");
        author = sc.next();
        System.out.println("Enter textBook: ");
        textBook = sc.next();
        System.out.println("Enter countOfPages:");
        countOfPages = sc.nextInt();
        System.out.println("Enter publishingDate: ");
        publishingDate = Date.valueOf((sc.next()));
        System.out.println("Choose popular: ");
        popular = sc.nextBoolean();
        System.out.println("Choose newRelease: ");
        newRelease = sc.nextBoolean();
        System.out.println("Enter genre: ");
        genre = sc.next();
        bookService.createBook(new Book(id, catalogId, title, author, textBook,
                countOfPages, publishingDate, popular, newRelease, genre));
    }

    public void showEditBook() {
        System.out.println("Enter user id: ");
        id = sc.nextLong();
        System.out.println("Enter catalogId id: ");
        catalogId = catalogMenu.showAddCatalog();
        System.out.println("Enter nameBook: ");
        title = sc.next();
        System.out.println("Enter author: ");
        author = sc.next();
        System.out.println("Enter textBook: ");
        textBook = sc.next();
        System.out.println("Enter countOfPages:");
        countOfPages = sc.nextInt();
        System.out.println("Enter publishingDate: ");
        publishingDate = Date.valueOf(sc.next());
        System.out.println("Choose popular: ");
        popular = sc.nextBoolean();
        System.out.println("Choose newRelease: ");
        newRelease = sc.nextBoolean();
        System.out.println("Enter genre: ");
        genre = sc.next();
        bookService.editBook(id, new Book(id, catalogId, title, author, textBook,
                countOfPages, publishingDate, popular, newRelease, genre));
    }

    public Book getItem(long id) {
        return bookService.getItem(id);
    }
    public List<Book> getAll() {
        return bookService.getAll();
    }

    public void showDelete(long id) {
        book.setId(id);
        bookService.deleteBook(book);
    }
}
