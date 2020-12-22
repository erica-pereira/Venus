package org.academiadecodigo.tailormoons.placeholder.converters;

import org.academiadecodigo.tailormoons.placeholder.dto.RecipeDto;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeDto extends AbstractConverter<Recipe, RecipeDto>{

    @Override
    public RecipeDto convert(Recipe recipe) {

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setCategory(recipe.getCategory());
        recipeDto.setName(recipe.getName());
        recipeDto.setServings(recipe.getServings());
        recipeDto.setId(recipe.getId());
        recipeDto.setImgUrl(recipeDto.getImgUrl());

        return recipeDto;
    }
}
