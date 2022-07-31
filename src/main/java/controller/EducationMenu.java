package controller;

import entity.Education;
import service.EducationService;

import java.util.List;
import java.util.Scanner;

public class EducationMenu {
    Scanner sc = new Scanner(System.in);
    EducationService educationService;
    Education education;
    private long id;
    private boolean elematrySchool;
    private boolean secondrySchool;
    private boolean higherEducation;

    public EducationMenu(EducationService educationService, Education education) {
        this.educationService = educationService;
        this.education = education;
    }

    public Long showAddEducation() {
        System.out.println("Choose elematrySchool");
        elematrySchool = sc.nextBoolean();
        System.out.println("Choose secondrySchool");
        secondrySchool = sc.nextBoolean();
        System.out.println("Choose higherEducation");
        higherEducation = sc.nextBoolean();
        return educationService.createEducation(new Education(id, elematrySchool, secondrySchool,
                higherEducation));
    }

    public Long showEditEducation() {
        System.out.println("Enter education id");
        id = sc.nextLong();
        System.out.println("Enter elematrySchool");
        elematrySchool = sc.nextBoolean();
        System.out.println("Enter secondrySchool");
        secondrySchool = sc.nextBoolean();
        System.out.println("Enter higherEducation");
        higherEducation = sc.nextBoolean();
        return educationService.editEducation(id, new Education(id, elematrySchool, secondrySchool,
                higherEducation));
    }

    public Education getItem(long id) {
        return educationService.getItem(id);
    }

    public List<Education> getAll() {
        return educationService.getAll();
    }

    public void showDeleteEducation(long id) {
        education.setId(id);
        educationService.deleteEducation(education);
    }

}
