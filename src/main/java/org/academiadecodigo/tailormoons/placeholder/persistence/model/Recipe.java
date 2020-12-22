package org.academiadecodigo.tailormoons.placeholder.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Recipe extends AbstractModel{

    private String name;
    private String category;
    private Integer servings;
    private String imgUrl;

    @OneToOne(mappedBy = "recipe",
    cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Nutrition nutrition;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "recipe",
            fetch = FetchType.EAGER
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "favourites"
    )
    private List<Customer> customers = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void addIngredients(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
