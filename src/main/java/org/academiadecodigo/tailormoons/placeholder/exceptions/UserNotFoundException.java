package org.academiadecodigo.tailormoons.placeholder.exceptions;

import org.academiadecodigo.tailormoons.placeholder.errors.ErrorMessage;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
