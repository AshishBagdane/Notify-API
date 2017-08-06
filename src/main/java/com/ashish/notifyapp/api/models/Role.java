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
@Table(name = "ROLE")
@XmlRootElement
public class Role implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "TYPE", length = 50, nullable = false, unique = true)
    private byte type;
    // 1: Student
    // 2: Department Head
    // 3: TPO Head

    public long getId() {
            return id;
    }

    public void setId(long id) {
            this.id = id;
    }

    public byte getRole() {
            return type;
    }

    public void setRole(byte type) {
            this.type = type;
    }

}
