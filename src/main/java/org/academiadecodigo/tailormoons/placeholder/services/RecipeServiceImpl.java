package org.academiadecodigo.tailormoons.placeholder.services;

import org.academiadecodigo.tailormoons.placeholder.exceptions.RecipeNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.dao.jpa.JpaRecipeDao;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl {

    private JpaRecipeDao jpaRecipeDao;

    @Autowired
    public void setJpaRecipeDao(JpaRecipeDao jpaRecipeDao) {
        this.jpaRecipeDao = jpaRecipeDao;
    }

    @Transactional
    public Recipe getByName(String name) throws RecipeNotFoundException{

        List<Recipe> recipes = jpaRecipeDao.findAll();

        for(Recipe recipe : recipes){
            if (recipe.getName().equals(name)){
                return recipe;
            }
        }

        throw new RecipeNotFoundException();
    }


    @Transactional
    public Recipe getById(Integer id)  throws RecipeNotFoundException{

        Recipe recipe = jpaRecipeDao.findById(id);

        if(recipe == null){
            throw new RecipeNotFoundException();
        }

        return recipe;
    }


    @Transactional
    public Recipe save(Recipe recipe){
        return jpaRecipeDao.saveOrUpdate(recipe);
    }


    @Transactional
    public void delete(Integer id) throws RecipeNotFoundException{

        Recipe recipe = jpaRecipeDao.findById(id);

        if(recipe == null){
            throw new RecipeNotFoundException();
        }

        jpaRecipeDao.delete(id);
    }


    @Transactional
    public List<Recipe> findAll(){
        return jpaRecipeDao.findAll();
    }


}
