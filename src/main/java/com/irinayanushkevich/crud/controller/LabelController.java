package com.irinayanushkevich.crud.controller;

import com.irinayanushkevich.crud.model.Label;
import com.irinayanushkevich.crud.repository.LabelRepository;
import com.irinayanushkevich.crud.repository.jsonrep.JsonLabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private final LabelRepository jsonRep = new JsonLabelRepositoryImpl();

    public Label create(String name) {
        Long id = null;
        Label l = new Label(id, name);
        return jsonRep.create(l);
    }

    public Label getById(Long id) {
        return jsonRep.getById(id);
    }

    public Label edit(Label label) {
        return jsonRep.edit(label);
    }

    public Label delete(Long id) {
        Label result = null;
        Label delLabel = jsonRep.getById(id);
        if (jsonRep.delete(id)) {
            result = delLabel;
        }
        return result;
    }

    public List<Label> getAll() {
        return jsonRep.getAll();
    }
}
