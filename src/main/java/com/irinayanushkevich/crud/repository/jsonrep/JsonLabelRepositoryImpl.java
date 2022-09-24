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
    private List<Label> labels = new ArrayList<>();


   /* public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }*/

    public long generateId() {
        long id = 0;
        labels = readFile();

        if (labels == null) {
            labels = new ArrayList<>();
        }
        Optional<Label> l = labels.stream().max(Comparator.comparing(Label::getId));
        if (l.isPresent()) {
            id = l.get().getId() + 1;
        }
        return id;
    }

    private List<Label> readFile() {
        List<Label> l = null;

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Label>>() {
            }.getType();
            l = new Gson().fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
        return l;
    }

    private void writeFile(List<Label> labels) {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(labels, writer);
        } catch (IOException e) {
            System.out.println("Writing file  error: " + e);
        }
    }

    public Label create(Label entity) {
        labels.add(entity);
        writeFile(labels);
        return entity;
    }


    public boolean checkId(long id) {
        boolean matching;
        labels = readFile();

        if (labels == null) {
            matching = false;
        } else {
            matching = labels.stream().anyMatch(label -> label.getId().equals(id));
        }
        return matching;
    }

    public boolean checkName(String name) {
        boolean matching;
        labels = readFile();

        if (labels == null) {
            matching = false;
        } else {
            matching = labels.stream().anyMatch(label -> label.getName().equals(name));
        }
        return matching;
    }

    public Label getById(Long id) {
        Label l;
        long finalId = id;
        labels = readFile();

        if (labels == null) {
            l = null;
        } else {
            l = labels.stream().filter(label -> label.getId().equals(finalId)).findFirst().orElse(null);
        }
        return l;
    }


    public Label edit(Label entity) {
        Label lOld = getById(entity.getId());
        lOld.setName(entity.getName());
        writeFile(labels);
        return lOld;
    }

    public Label delete(Long id) {
        long lId = id;
        Label lDel = getById(id);
        labels.remove(labels.get((int) lId));
        writeFile(labels);
        return lDel;
    }

    public List<Label> getAll() {
        return readFile();
    }
}
