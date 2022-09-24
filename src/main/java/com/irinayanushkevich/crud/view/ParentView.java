package com.irinayanushkevich.crud.view;

import com.irinayanushkevich.crud.controller.LabelController;

import java.util.Scanner;

public abstract class ParentView {

    private final Scanner scanner = new Scanner(System.in);
    final LabelController lc = new LabelController();

    public void run(String category) {

        int act;
        boolean isFinish = false;

        while (!isFinish) {
            printActions(category);
            act = chooseAction();
            isFinish = workWithActions(act);
        }
    }

    public void printActions(String category) {
        System.out.println("""
                Choose what you want to do with\t""" + category + """
                \nPrint 1, 2, 3, 4 or 0:
                1 - Create
                2 - Find by index
                3 - Edit
                4 - Delete
                5 - See the whole list
                0 - Exit""");
    }

    public int chooseAction() {

        int act;

        try {
            act = Integer.parseInt(scanner.nextLine());
            if (act < 0 || act > 5) {
                System.out.println("You can choose only 1, 2, 3, 4, 5 or 0!");
            } else {
                return act;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
        return -1;
    }

    public abstract boolean workWithActions(int act);
}
