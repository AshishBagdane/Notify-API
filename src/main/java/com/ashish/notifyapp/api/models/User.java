package com.ashish.notifyapp.api.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROFILE")
@XmlRootElement
public class User implements Serializable {

    @Id
    @Column(name = "USERNAME", length = 50, nullable = false)
    private String username;
    @Column(name = "FNAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LNAME", length = 50, nullable = false)
    private String lastName;
    @Column(name = "EMAIL", length = 50, nullable = false)
    private String eMail;
    @Column(name = "PASSWORD", length = 50, nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
}
