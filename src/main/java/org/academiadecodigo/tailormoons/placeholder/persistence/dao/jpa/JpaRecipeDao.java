package org.academiadecodigo.tailormoons.placeholder.persistence.dao.jpa;

import org.academiadecodigo.tailormoons.placeholder.persistence.model.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRecipeDao extends GenericJpaDao<Recipe>{

    public JpaRecipeDao() {
        super(Recipe.class);
    }
}
