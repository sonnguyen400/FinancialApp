package com.sonnguyen.individual.nhs.Model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "assets", length = 45)
    private String assets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

}