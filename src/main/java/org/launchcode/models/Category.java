package org.launchcode.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity  //this annotation will ensure that the class is mapped to a relational database table.
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn(name="Category_id")
    private List<Cheese> cheeses = new ArrayList<>(); //this list will represent the list of all items in a given category.

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*constructors*/


    public Category(){

    }

    public Category(String name){
        this.name=name;
    }


}
