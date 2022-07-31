package ui;


import controller.ReviewMenu;
import entity.Review;
import service.ReviewService;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class ViewReview {
    Scanner sc;
    ReviewMenu reviewMenu;

    public ViewReview(ReviewService reviewService, ReviewMenu reviewMenu) {
        this.reviewMenu = reviewMenu;
    }

    public void showReview() {
        sc = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Show Review");
            System.out.println("2. Add Review");
            System.out.println("3. Edit Review");
            System.out.println("4. Delete Review");
            System.out.println("5. Show All Reviews");
            System.out.println("0. Exit");
            int answer = sc.nextInt();
            if (answer == 0)
                break;
            try {
                switch (answer) {
                    case 1:
                        System.out.println("Enter Review id which you want see!");
                        try {
                            System.out.println(reviewMenu.getItem(sc.nextLong()));
                        } catch (Exception e) {
                            System.out.println("Element with such id does not exist");
                        }
                        break;
                    case 2:
                        reviewMenu.showAddReview();
                        break;
                    case 3:
                        reviewMenu.showEditReview();
                        break;
                    case 4:
                        System.out.println("Enter Review id which you want delete!");
                        reviewMenu.showDeleteReview(sc.nextLong());
                        break;
                    case 5:
                        printReviews(reviewMenu.getAll());
                        break;
                    default:
                        System.out.println("There is no such command! Try again!");
                        break;
                }
            } catch (MissingFormatArgumentException e) {
                System.out.println("There is no such command! Try again!");
            }
        }
    }

    public void printReviews(List<Review> reviewListt) {
        int i = 1;
        for (Review r : reviewListt) {
            System.out.println(String.format("%d. %s", i++, printReview(r)));
        }
    }
    public String printReview(Review review) {
        return review.toString();
    }
}
