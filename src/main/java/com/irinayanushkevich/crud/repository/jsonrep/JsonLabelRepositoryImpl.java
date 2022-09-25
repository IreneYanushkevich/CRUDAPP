package com.irinayanushkevich.crud.repository.jsonrep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.irinayanushkevich.crud.model.Label;
import com.irinayanushkevich.crud.repository.LabelRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JsonLabelRepositoryImpl implements LabelRepository {

    private final File file = new File("C:\\Users\\Irene\\IdeaProjects\\CRUDAPP\\src\\main\\resources\\labels.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private long generateId() {
        List<Label> labels = readFile();
        long id = 0;
        Optional<Label> l = labels.stream().max(Comparator.comparing(Label::getId));
        if (l.isPresent()) {
            id = l.get().getId() + 1;
        }
        return id;
    }

    private List<Label> readFile() {
        List<Label> l = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Label>>() {
            }.getType();
            l = new Gson().fromJson(reader, type);
            if (l == null) {
                l = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
        return l;
    }

    private void writeFile(List<Label> labels) {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(labels, writer);
        } catch (IOException e) {
            System.out.println("Writing file error: " + e);
        }
    }

    public Label create(Label entity) {
        Label newLabel;
        List<Label> labels = readFile();
        long id = generateId();
        if (checkName(entity.getName())) {
            newLabel = null;
        } else {
            newLabel = new Label(id, entity.getName());
            labels.add(newLabel);
            writeFile(labels);
        }
        return newLabel;
    }

    private boolean checkName(String name) {
        List<Label> labels = readFile();
        return labels.stream().anyMatch(label -> label.getName().equals(name));
    }

    public Label getById(Long id) {
        List<Label> labels = readFile();
        long finalId = id;
        return labels.stream().filter(label -> label.getId().equals(finalId)).findFirst().orElse(null);
    }

    private boolean checkId(long id) {
        List<Label> labels = readFile();
        return labels.stream().anyMatch(label -> label.getId().equals(id));
    }

    public Label edit(Label entity) {
        List<Label> labels = readFile();
        long id = entity.getId();
        String name = entity.getName();
        Label editLabel = getById(id);
        if (editLabel != null) {
            if (!checkName(name)) {
                labels.remove(editLabel);
                labels.add(entity);
                writeFile(labels);
                editLabel = entity;
            }
        }
        return editLabel;
    }

    public Label delete(Long id) {
        List<Label> labels = readFile();
        Label lDel = null;
        long lId = id;
        if (checkId(lId)) {
            lDel = getById(id);
            labels.remove(labels.get((int) lId));
            writeFile(labels);
        }
        return lDel;
    }

    public List<Label> getAll() {
        return readFile();
    }
}
