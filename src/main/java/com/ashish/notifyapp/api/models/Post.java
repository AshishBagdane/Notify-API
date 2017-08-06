package com.ashish.notifyapp.api.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "POST")
@XmlRootElement
public class Post implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;
    @Column(name = "BODY", length = 1000, nullable = false)
    private String body;
    @Column(name = "IMGURL", length = 500, nullable = true)
    private String imgURL;

    public long getId() {
            return id;
    }

    public void setId(long id) {
            this.id = id;
    }

    public String getTitle() {
            return title;
    }

    public void setTitle(String title) {
            this.title = title;
    }

    public String getBody() {
            return body;
    }

    public void setBody(String body) {
            this.body = body;
    }

    @XmlTransient
    public String getImgURL() {
            return imgURL;
    }

    public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
    }

}
