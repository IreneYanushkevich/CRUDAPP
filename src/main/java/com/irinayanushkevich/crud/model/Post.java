package com.irinayanushkevich.crud.model;

import java.time.LocalDate;
import java.util.*;

public class Post {

    private Long id;
    private String content;
    private LocalDate created;
    private LocalDate updated;
    private List<Label> labels;

    public Post(Long id, String content, LocalDate created, LocalDate updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        labels = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate isCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate isUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    /*public void addLabel(Label... label) {
        labels.addAll(Arrays.stream(label).toList());
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Post p = (Post) o;
        if (content == null) {
            if (p.content != null) {
                return false;
            }
        } else if (!content.equals(p.content)) {
            return false;
        }
        if (labels == null) {
            if (p.labels != null) {
                return false;
            }
        } else if (!labels.equals(p.labels)) {
            return false;
        }
        return id.intValue() == p.id.intValue() && created == p.created && updated == p.updated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((labels == null) ? 0 : labels.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((updated == null) ? 0 : updated.hashCode());
        result = prime * result + id.intValue();
        return result;
    }

    @Override
    public String toString() {
        return "Post id:" + id + ", created: " + created + ", updated: " + updated +
                ",\nlabels: " + labels + "\ncontent:\n" + content;
    }
}
