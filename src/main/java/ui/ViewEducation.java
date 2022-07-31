package ui;

import controller.EducationMenu;
import entity.Education;
import service.EducationService;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class ViewEducation {
    Scanner sc;
    EducationService educationService;
    EducationMenu educationMenu;

    public ViewEducation(EducationService educationService, EducationMenu educationMenu) {
        this.educationService = educationService;
        this.educationMenu = educationMenu;
    }

    public void showEducation() {
        sc = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Show Education");
            System.out.println("2. Add Education");
            System.out.println("3. Edit Education");
            System.out.println("4. Delete Education");
            System.out.println("5. Show ALL Education");
            System.out.println("0. Exit");
            int answer = sc.nextInt();
            if (answer == 0)
                break;
            try {
                switch (answer) {
                    case 1:
                        System.out.println("Enter Education id which you want see!");
                        try {
                            System.out.println(educationMenu.getItem(sc.nextLong()));
                        } catch (Exception e) {
                            System.out.println("Element with such id does not exist");
                        }
                        break;
                    case 2:
                        educationMenu.showAddEducation();
                        break;
                    case 3:
                        educationMenu.showEditEducation();
                        break;
                    case 4:
                        System.out.println("Enter Education id which you want delete!");
                        educationMenu.showDeleteEducation(sc.nextLong());
                        break;
                    case 5:
                        printEducations(educationMenu.getAll());
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
    public void printEducations(List<Education> educationList) {
        int i = 1;
        for (Education e : educationList) {
            System.out.println(String.format("%d. %s", i++, printEducation(e)));
        }
    }
    public String printEducation(Education education) {
        return education.toString();
    }
}

