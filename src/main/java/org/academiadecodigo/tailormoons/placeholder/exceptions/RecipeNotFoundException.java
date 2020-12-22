package org.academiadecodigo.tailormoons.placeholder.exceptions;

import org.academiadecodigo.tailormoons.placeholder.errors.ErrorMessage;

public class RecipeNotFoundException extends Exception {

    public RecipeNotFoundException() {
        super(ErrorMessage.RECIPE_NOT_FOUND);
    }
}