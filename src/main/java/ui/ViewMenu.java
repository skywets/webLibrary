package ui;

import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class ViewMenu {
    Scanner sc;
    ViewEducation viewEducation;
    ViewCatalog viewCatalog;
    ViewBook viewBook;
    ViewUser viewUser;
    ViewReview viewReview;

    public ViewMenu(ViewEducation viewEducation, ViewCatalog viewCatalog, ViewBook viewBook, ViewUser viewUser, ViewReview viewReview) {
        this.viewEducation = viewEducation;
        this.viewCatalog = viewCatalog;
        this.viewBook = viewBook;
        this.viewUser = viewUser;
        this.viewReview = viewReview;
    }

    public void showMainMenu() {
        sc = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. ViewBooks");
            System.out.println("2. ViewUser");
            System.out.println("3. ViewCatalog");
            System.out.println("4. ViewReview");
            System.out.println("5. ViewEducation");
            System.out.println("0. Exit");
            int answer = sc.nextInt();
            try {
                switch (answer) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        viewBook.showBook();
                        break;
                    case 2:
                        viewUser.showUser();
                        break;
                    case 3:
                        viewCatalog.showCatalog();
                        break;
                    case 4:
                        viewReview.showReview();
                        break;
                    case 5:
                        viewEducation.showEducation();
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


}
