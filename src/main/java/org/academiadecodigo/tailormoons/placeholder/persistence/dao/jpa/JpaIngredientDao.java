package org.academiadecodigo.tailormoons.placeholder.persistence.dao.jpa;

import org.academiadecodigo.tailormoons.placeholder.persistence.model.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public class JpaIngredientDao extends GenericJpaDao<Ingredient> {

    public JpaIngredientDao() {
        super(Ingredient.class);
    }
}
