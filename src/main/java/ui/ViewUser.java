package ui;


import controller.UserMenu;
import entity.User;
import service.UserService;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class ViewUser {
    Scanner sc = new Scanner(System.in);
    UserMenu userMenu;
    UserService userService;

    public ViewUser(UserService userService, UserMenu userMenu) {
        this.userMenu = userMenu;
        this.userService = userService;
    }


    public void showUser() {
        sc = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Show Users");
            System.out.println("2. Add User");
            System.out.println("3. Edit User");
            System.out.println("4. Delete User");
            System.out.println("5. Show All User");
            System.out.println("0. Exit");
            int answer = sc.nextInt();
            if (answer == 0)
                break;
            try {
                switch (answer) {
                    case 1:
                        System.out.println("Enter user id which you want see!");
                        try {
                            System.out.println(userMenu.getItem(sc.nextLong()));
                        }catch (Exception e){
                            System.out.println("Element with such id does not exist");
                        }
                        break;
                    case 2:
                        userMenu.showAddUser();
                        break;
                    case 3:
                        userMenu.showEditUser();
                        break;
                    case 4:
                        System.out.println("Enter user id which you want delete!");
                        userMenu.showDelete(sc.nextLong());
                        break;
                    case 5:
                        printUsers(userService.getAll());
                    default:
                        System.out.println("There is no such command! Try again!");
                        break;
                }
            } catch (MissingFormatArgumentException e) {
                System.out.println("There is no such command! Try again!");
            }
        }
    }
    public void printUsers(List<User> userList) {
        int  i = 1;
        for (User u: userList) {
            System.out.println(String.format("%d. %s", i++, printUser(u)));
        }
    }
    public String printUser(User user) {
        return user.toString();
    }
}
