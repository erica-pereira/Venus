package org.academiadecodigo.tailormoons.placeholder.converters;

import org.academiadecodigo.tailormoons.placeholder.dto.RecipeDto;
import org.academiadecodigo.tailormoons.placeholder.exceptions.RecipeNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.exceptions.UserNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Recipe;
import org.academiadecodigo.tailormoons.placeholder.services.CustomerService;
import org.academiadecodigo.tailormoons.placeholder.services.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeDtoToRecipe {

    private CustomerService customerService;
    private RecipeServiceImpl recipeService;

    @Autowired
    public void setRecipeService(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    public Recipe convert(RecipeDto recipeDto) throws UserNotFoundException, RecipeNotFoundException {

        Recipe recipe = (recipeDto.getId() != null ? recipeService.getById(recipeDto.getId()) : new Recipe());
        recipe.setCategory(recipeDto.getCategory());
        recipe.setName(recipeDto.getName());
        recipe.setServings(recipeDto.getServings());
        recipe.setImgUrl(recipeDto.getImgUrl());

        return recipe;
    }
}
