package ui;


import controller.BookMenu;
import entity.Book;
import service.BookService;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class ViewBook {

    Scanner sc = new Scanner(System.in);
    BookMenu bookMenu;

    public ViewBook(BookService bookService, BookMenu bookMenu) {
        this.bookMenu = bookMenu;
    }

    public void showBook() {
        sc = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Show Books");
            System.out.println("2. Add Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Show All Books");
            System.out.println("0. Exit");
            int answer = sc.nextInt();
            if (answer == 0)
                break;
            try {
                switch (answer) {
                    case 1:
                        System.out.println("Enter Book id which you want see!");

                        try {
                            System.out.println(bookMenu.getItem(sc.nextLong()));
                        } catch (Exception e) {
                            System.out.println("Element with such id does not exist");
                        }
                        break;
                    case 2:
                        bookMenu.showAddBook();
                        break;
                    case 3:
                        bookMenu.showEditBook();
                        break;
                    case 4:
                        System.out.println("Enter Book id which you want delete!");
                        bookMenu.showDelete(sc.nextLong());
                        break;
                    case 5:
                        printBooks(bookMenu.getAll());
                    default:
                        System.out.println("There is no such command! Try again!");
                        break;
                }
            } catch (MissingFormatArgumentException e) {
                System.out.println("There is no such command! Try again!");
            }
        }
    }

    public void printBooks(List<Book> bookList) {
        int i = 1;
        for (Book b : bookList) {
            System.out.println(String.format("%d. %s", i++, printBook(b)));
        }
    }
    public String printBook(Book book) {
        return book.toString();
    }
}
