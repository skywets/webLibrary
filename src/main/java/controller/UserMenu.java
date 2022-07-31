package controller;


import entity.User;
import lombok.Getter;
import lombok.Setter;
import service.UserService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class UserMenu {

    Scanner sc = new Scanner(System.in);
    EducationMenu educationMenu;
    UserService userService;
    User user;
    private long id;
    private String login;
    private String password;
    //  private LocalDateTime registrydate;
    private long educationId;
    private String name;
    private String country;
    private String language;
    private Date birthday;

    public UserMenu(EducationMenu educationMenu, UserService userService, User user) {
        this.educationMenu = educationMenu;
        this.userService = userService;
        this.user = user;
    }

    public void showAddUser() {
        System.out.println("Enter user login: ");
        login = sc.next();
        System.out.println("Enter user password: ");
        password = sc.next();
        //  registrydate = getRegistrydate();
        System.out.println("Enter user name: ");
        name = sc.next();
        System.out.println("Enter user country: ");
        country = sc.next();
        System.out.println("Enter user language: ");
        language = sc.next();
        System.out.println("Enter user education: ");
        educationId = educationMenu.showAddEducation();
        System.out.println("Enter user birthday: ");
        birthday = Date.valueOf(sc.next());
        userService.createUser(new User(login, password, educationId, name, country, language, birthday));
    }

    public void showEditUser() {
        System.out.println("Enter user id: ");
        id = sc.nextLong();
        System.out.println("Enter user login: ");
        login = sc.next();
        System.out.println("Enter user password: ");
        password = sc.next();
        System.out.println("Enter user education: ");
        educationId = educationMenu.showEditEducation();
        System.out.println("Enter user name: ");
        name = sc.next();
        System.out.println("Enter user country: ");
        country = sc.next();
        System.out.println("Enter user language: ");
        language = sc.next();
        System.out.println("Enter user birthday: ");
        // birthday = LocalDate.parse(sc.next());
        birthday = Date.valueOf(sc.next());
         userService.editUser(id, new User(login, password, educationId, name, country, language, birthday));

    }

    public User getItem(long id) {
        return userService.getItem(id);
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public void showDelete(long id) {
        user.setId(id);
        userService.deleteBook(user);
    }


}
