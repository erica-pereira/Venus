package org.academiadecodigo.tailormoons.placeholder.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"name", "serving_size_g"})
public class Nutrition extends AbstractModel implements Serializable {

    private float sugar_g;
    private float fiber_g;
    private float sodium_mg;
    private float potassium_mg;
    private float fat_saturated_g;
    private float fat_total_g;
    private float calories;
    private float cholesterol_mg;
    private float protein_g;
    private float carbohydrates_total_g;

    @OneToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    public float getSugar_g() {
        return sugar_g;
    }

    public void setSugar_g(float sugar_g) {
        this.sugar_g = sugar_g;
    }

    public float getFiber_g() {
        return fiber_g;
    }

    public void setFiber_g(float fiber_g) {
        this.fiber_g = fiber_g;
    }

    public float getSodium_mg() {
        return sodium_mg;
    }

    public void setSodium_mg(float sodium_mg) {
        this.sodium_mg = sodium_mg;
    }

    public float getPotassium_mg() {
        return potassium_mg;
    }

    public void setPotassium_mg(float potassium_mg) {
        this.potassium_mg = potassium_mg;
    }

    public float getFat_saturated_g() {
        return fat_saturated_g;
    }

    public void setFat_saturated_g(float fat_saturated_g) {
        this.fat_saturated_g = fat_saturated_g;
    }

    public float getFat_total_g() {
        return fat_total_g;
    }

    public void setFat_total_g(float fat_total_g) {
        this.fat_total_g = fat_total_g;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getCholesterol_mg() {
        return cholesterol_mg;
    }

    public void setCholesterol_mg(float cholesterol_mg) {
        this.cholesterol_mg = cholesterol_mg;
    }

    public float getProtein_g() {
        return protein_g;
    }

    public void setProtein_g(float protein_g) {
        this.protein_g = protein_g;
    }

    public float getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }

    public void setCarbohydrates_total_g(float carbohydrates_total_g) {
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "sugar_g=" + sugar_g +
                ", fiber_g=" + fiber_g +
                ", sodium_mg=" + sodium_mg +
                ", potassium_mg=" + potassium_mg +
                ", fat_saturated_g=" + fat_saturated_g +
                ", fat_total_g=" + fat_total_g +
                ", calories=" + calories +
                ", cholesterol_mg=" + cholesterol_mg +
                ", protein_g=" + protein_g +
                ", carbohydrates_total_g=" + carbohydrates_total_g +
                ", recipe=" + recipe +
                '}';
    }
}

