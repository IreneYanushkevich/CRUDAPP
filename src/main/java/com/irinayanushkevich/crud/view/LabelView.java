package com.irinayanushkevich.crud.view;

import com.irinayanushkevich.crud.controller.LabelController;
import com.irinayanushkevich.crud.model.Label;

import java.util.List;

public class LabelView {

    private final CommonView cv;

    public LabelView(CommonView cv) {
        this.cv = cv;
    }

    private final LabelController lc = new LabelController();

    public boolean workWithActions(int act) {
        switch (act) {
            case 1 -> {
                String name = cv.askName();
                Label label = lc.create(name);
                if (label == null) {
                    System.out.println("Label with that name already exists.");
                } else {
                    System.out.println("You created a new label: " + label);
                }
            }
            case 2 -> {
                Long id = cv.askId();
                Label label = lc.getById(id);
                printResult(label);
            }
            case 3 -> {
                Long id = cv.askId();
                String name = cv.askName();
                Label label = lc.edit(new Label(id, name));
                printResult(label);
            }
            case 4 -> {
                Long id = cv.askId();
                Label label = lc.delete(id);
                printResult(label);
            }
            case 5 -> {
                List<Label> labels = lc.getAll();
                if (labels.size() == 0) {
                    System.out.println("File is empty or doesn't exist.");
                } else {
                    labels.forEach(System.out::println);
                }
            }
            case 0 -> {
                return true;
            }
        }
        return false;
    }

    private void printResult(Label label) {
        if (label == null) {
            System.out.println("A label with this index doesn't exist.");
        } else {
            System.out.println("Done! Work with the next object is completed: " + label);
        }
    }
}
