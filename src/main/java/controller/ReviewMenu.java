package controller;


import entity.Book;
import entity.Review;
import entity.User;
import service.ReviewService;

import java.util.List;
import java.util.Scanner;

public class ReviewMenu {
    Scanner sc = new Scanner(System.in);
    private long id;
    private long userId;
    private long bookId;
    private String text;
    ReviewService reviewService;
    UserMenu userMenu;
    BookMenu bookMenu;
    Review review;
    User user;
    Book book;

    public ReviewMenu(ReviewService reviewService, UserMenu userMenu, BookMenu bookMenu, Review review, User user, Book book) {
        this.reviewService = reviewService;
        this.userMenu = userMenu;
        this.bookMenu = bookMenu;
        this.review = review;
        this.user = user;
        this.book = book;
    }

    public void showAddReview() {
        System.out.println("Enter userId");
        userId = sc.nextLong();
        System.out.println("Enter bookId");
        bookId = sc.nextLong();
        System.out.println("Enter text");
        text = sc.next();
        reviewService.createReview(new Review(userId, bookId, text));
    }

    public void showEditReview() {
        System.out.println("Enter review id");
        id = sc.nextLong();
        System.out.println("Enter userId");
        userId = sc.nextLong();
        System.out.println("Enter bookId");
        bookId = sc.nextLong();
        System.out.println("Enter text");
        text = sc.next();
        reviewService.editReview(id, new Review(userId, bookId, text));
    }

    public Review getItem(long id) {
        return reviewService.getItem(id);
    }

    public List<Review> getAll() {
        return reviewService.getAll();
    }

    public void showDeleteReview(long id) {
        review.setId(id);
        reviewService.deleteReview(review);
    }
}
