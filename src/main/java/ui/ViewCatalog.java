package ui;


import controller.CatalogMenu;
import entity.Catalog;
import service.CatalogService;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class ViewCatalog {
    Scanner sc = new Scanner(System.in);
    CatalogMenu catalogMenu;

    public ViewCatalog(CatalogService catalogService, CatalogMenu catalogMenu) {
        this.catalogMenu = catalogMenu;
    }

    public void showCatalog() {
        sc = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Show Catalog");
            System.out.println("2. Add Catalog");
            System.out.println("3. Edit Catalog");
            System.out.println("4. Delete Catalog");
            System.out.println("5. Show All Catalogs");
            System.out.println("0. Exit");
            int answer = sc.nextInt();
            if (answer == 0)
                break;
            try {
                switch (answer) {
                    case 1:
                        System.out.println("Enter Book id which you want see!");
                        try {
                            System.out.println(catalogMenu.getItem(sc.nextLong()));
                        } catch (Exception e) {
                            System.out.println("Element with such id does not exist");
                        }
                        break;
                    case 2:
                        catalogMenu.showAddCatalog();
                        break;
                    case 3:
                        catalogMenu.showEditCatalog();
                        break;
                    case 4:
                        System.out.println("Enter Book id which you want delete!");
                        catalogMenu.showDeleteCatalog(sc.nextLong());
                        break;
                    case 5:
                        printCatalogs(catalogMenu.getAll());
                    default:
                        System.out.println("There is no such command! Try again!");
                        break;
                }
            } catch (MissingFormatArgumentException e) {
                System.out.println("There is no such command! Try again!");
            }
        }
    }

    public void printCatalogs(List<Catalog> catalogList) {
        int i = 1;
        for (Catalog c : catalogList) {
            System.out.println(String.format("%d. %s", i++, printCatlog(c)));
        }
    }

    public String printCatlog(Catalog catalog) {
        return catalog.toString();
    }
}
