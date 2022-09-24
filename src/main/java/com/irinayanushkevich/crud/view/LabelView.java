package com.irinayanushkevich.crud.view;

public class LabelView extends ParentView {

    @Override
    public boolean workWithActions(int act) {
        switch (act) {
            case 1 -> lc.create();
            case 2 -> lc.getById();
            case 3 -> lc.edit();
            case 4 -> lc.delete();
            case 5 -> lc.getAll();
            case 0 -> {
                return true;
            }
        }
        return false;
    }
}
