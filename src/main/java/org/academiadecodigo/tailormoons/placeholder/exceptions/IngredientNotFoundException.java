package org.academiadecodigo.tailormoons.placeholder.exceptions;

import org.academiadecodigo.tailormoons.placeholder.errors.ErrorMessage;

public class IngredientNotFoundException extends Exception {

    public IngredientNotFoundException() {
        super(ErrorMessage.INGREDIENT_NOT_FOUND);
    }
}
