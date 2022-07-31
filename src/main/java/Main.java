import controller.*;
import dao.*;
import dao.connection.ConnectionBulder;
import dao.connection.SimpleConnectionBuilder;
import entity.*;
import service.*;
import ui.*;

public class Main {
    public int i=2;
    public static void main(String[] args) {
        ConnectionBulder connectionBulder = new SimpleConnectionBuilder();
        EducationDao educationDAO = new EducationDao(connectionBulder);
        CatalogDao catalogDao = new CatalogDao(connectionBulder);
        BookDao bookDao = new BookDao(connectionBulder);
        UserDao userDao = new UserDao(connectionBulder);
        ReviewDao reviewDao = new ReviewDao(connectionBulder);

        EducationService educationService = EducationService.builder().educationDao(educationDAO).build();
        CatalogService catalogService = CatalogService.builder().catalogDao(catalogDao).build();
        BookService bookService = BookService.builder().bookDao(bookDao).build();
        UserService userService = UserService.builder().userDao(userDao).build();
        ReviewService reviewService = ReviewService.builder().reviewDao(reviewDao).build();

        var education = new Education();
        Catalog catalog = new Catalog();
        Book book = new Book();
        User user =new User();
        Review review = new Review();

        EducationMenu educationMenu = new EducationMenu(educationService, education);
        CatalogMenu catalogMenu = new CatalogMenu(catalogService, catalog);
        BookMenu bookMenu = new BookMenu(bookService,book, catalogMenu);
        UserMenu userMenu = new UserMenu(educationMenu, userService,user);
        ReviewMenu reviewMenu = new ReviewMenu(reviewService, userMenu, bookMenu, review, user, book);

        ViewEducation viewEducation = new ViewEducation(educationService, educationMenu);
        ViewCatalog viewCatalog = new ViewCatalog(catalogService, catalogMenu);
        ViewBook viewBook =new ViewBook(bookService,bookMenu);
        ViewUser viewUser =new ViewUser(userService, userMenu);
        ViewReview viewReview = new ViewReview(reviewService, reviewMenu);
        ViewMenu viewMenu = new ViewMenu(viewEducation, viewCatalog, viewBook, viewUser, viewReview);
        viewMenu.showMainMenu();

    }
}