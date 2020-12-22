package org.academiadecodigo.tailormoons.placeholder.services;

import org.academiadecodigo.tailormoons.placeholder.exceptions.IngredientNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.dao.jpa.JpaIngredientDao;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class IngredientServiceImpl {

    private JpaIngredientDao jpaIngredientDao;

    @Autowired
    public void setJpaIngredientDao(JpaIngredientDao jpaIngredientDao) {
        this.jpaIngredientDao = jpaIngredientDao;
    }


    @Transactional
    public Ingredient getById(Integer id) throws IngredientNotFoundException{

        Ingredient ingredient = jpaIngredientDao.findById(id);

        if(ingredient == null){
            throw new IngredientNotFoundException();
        }

        return ingredient;
    }


}
