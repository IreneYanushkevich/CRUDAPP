package com.irinayanushkevich.crud.view;

import com.irinayanushkevich.crud.controller.LabelController;
import com.irinayanushkevich.crud.model.Label;

import java.util.List;

public class LabelView {

    private final LabelController lc = new LabelController();

    public boolean workWithActions(int act) {
        switch (act) {
            case 1 -> {
                System.out.println("Set a name of the label >>>");
                Label label = lc.create();
                if (label == null) {
                    System.out.println("Label with that name already exists.");
                } else {
                    System.out.println("You created a new label: " + label);
                }
            }
            case 2 -> {
                System.out.println("Enter the id >>>");
                Label label = lc.getById();
                if (checkNull(label)) {
                    System.out.println("Label what you looked for: " + label);
                }
            }
            case 3 -> {
                System.out.println("Input id + 'Enter', then input new name for this position + 'Enter' >>>");
                Label label = lc.edit();
                if (checkNull(label)) {
                    System.out.println("Edited position (if new name wasn't use before): " + label);
                }
            }
            case 4 -> {
                System.out.println("Enter the index of the position you want to delete >>>");
                Label label = lc.delete();
                if (checkNull(label)) {
                    System.out.println("Deleted position: " + label);
                }
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

    private boolean checkNull(Label label) {
        boolean result = true;
        if (label == null) {
            result = false;
            System.out.println("A label with this index doesn't exist.");
        }
        return result;
    }
}
