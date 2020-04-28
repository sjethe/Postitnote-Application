package com.backendexample.postitnotes.dao.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="POSTIT")
public class Postit {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name="TEXT")
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
