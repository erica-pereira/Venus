package org.academiadecodigo.tailormoons.placeholder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class RecipeDto {

    private Integer id;

    @NotNull(message = "Recipe name is mandatory")
    @NotBlank(message = "Recipe name is mandatory")
    @Size(min = 3, max = 64)
    private String name;

    @NotNull(message = "Recipe category is mandatory")
    @NotBlank(message = "Recipe category is mandatory")
    @Size(min = 3, max = 64)
    private String category;

    @NotNull(message = "Number of servings is mandatory")
    //@NotBlank(message = "Number of servings is mandatory")
    //@Size(max = 2)
    private Integer servings;

    private String imgUrl;

    List<IngredientDto> ingredients;

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

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


    @Override
    public String toString() {
        return "RecipeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", servings=" + servings +
                ", ingredients=" + ingredients +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
