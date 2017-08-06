package com.ashish.notifyapp.api.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DEPARTMENT")
@XmlRootElement
public class Department implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    public long getId() {
            return id;
    }

    public void setId(long id) {
            this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

}
