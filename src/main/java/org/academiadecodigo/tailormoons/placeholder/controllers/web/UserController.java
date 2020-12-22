package org.academiadecodigo.tailormoons.placeholder.controllers.web;

import org.academiadecodigo.tailormoons.placeholder.controllers.rest.RestNutritionController;
import org.academiadecodigo.tailormoons.placeholder.converters.CustomerDtoToCustomer;
import org.academiadecodigo.tailormoons.placeholder.converters.CustomertoCustomerDto;
import org.academiadecodigo.tailormoons.placeholder.converters.RecipeDtoToRecipe;
import org.academiadecodigo.tailormoons.placeholder.converters.RecipeToRecipeDto;
import org.academiadecodigo.tailormoons.placeholder.dto.CustomerDto;
import org.academiadecodigo.tailormoons.placeholder.dto.IngredientDto;
import org.academiadecodigo.tailormoons.placeholder.dto.RecipeDto;
import org.academiadecodigo.tailormoons.placeholder.exceptions.RecipeNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.exceptions.UserNotFoundException;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Nutrition;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Recipe;
import org.academiadecodigo.tailormoons.placeholder.services.CustomerService;
import org.academiadecodigo.tailormoons.placeholder.services.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    private CustomerService customerService;
    private RecipeServiceImpl recipeService;
    //DTOs

    private CustomerDtoToCustomer customerDtoToCustomer;
    private CustomertoCustomerDto customertoCustomerDto;
    private RecipeToRecipeDto recipeToRecipeDto;
    private RecipeDtoToRecipe recipeDtoToRecipe;
    private RestNutritionController restNutritionController;

    @Autowired
    public void setRestNutritionController(RestNutritionController restNutritionController) {
        this.restNutritionController = restNutritionController;
    }

    @Autowired
    public void setRecipeToRecipeDto(RecipeToRecipeDto recipeToRecipeDto) {
        this.recipeToRecipeDto = recipeToRecipeDto;
    }

    @Autowired
    public void setRecipeDtoToRecipe(RecipeDtoToRecipe recipeDtoToRecipe) {
        this.recipeDtoToRecipe = recipeDtoToRecipe;
    }

    @Autowired
    public void setRecipeService(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Autowired
    public void setCustomertoCustomerDto(CustomertoCustomerDto customertoCustomerDto) {
        this.customertoCustomerDto = customertoCustomerDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}")
    public String showUser(@PathVariable Integer cid, Model model) throws UserNotFoundException {

        Customer customer = customerService.get(cid);

        //testing purposes
        List<Recipe> recipes = recipeService.findAll();
        for (Recipe rec : recipes) {
            customer.addRecipe(rec);
        }

        CustomerDto customerDto = customertoCustomerDto.convert(customer);

        model.addAttribute("user", customerDto);

        return "profile";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/recipes")
    public String showRecipes(Model model) {

        model.addAttribute("recipes", recipeService.findAll());

        return "recipe";
    }

    @RequestMapping(path = "/menu")
    public String showMenu() {
        return "menu";
    }

    @RequestMapping(path = "/{rid}/menu")
    public String showRecipe(@PathVariable Integer rid) throws UserNotFoundException, RecipeNotFoundException {
        Recipe recipe = recipeService.getById(rid);
        return "recipe";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/recipe")
    public String showSpecificRecipe(@PathVariable Integer id, Model model) throws RecipeNotFoundException {
        Recipe recipe = recipeService.getById(id);

        model.addAttribute("recipe", recipe);

        return "recipe";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/addrecipe")
    public String addRecipe(@PathVariable Integer cid, Model model) {

        model.addAttribute("recipe", new RecipeDto());
        model.addAttribute("ingredient1", new IngredientDto());
        model.addAttribute("ingredient2", new IngredientDto());
        model.addAttribute("ingredient3", new IngredientDto());
        model.addAttribute("ingredient4", new IngredientDto());
        model.addAttribute("ingredient5", new IngredientDto());
        return "addrecipe";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{cid}/recipe/submit")
    public String saveRecipe(@PathVariable Integer cid, @Valid @ModelAttribute("recipe") RecipeDto recipeDto,
                             @ModelAttribute("ingredient1") IngredientDto ingredientDto1,
                             @ModelAttribute("ingredient2") IngredientDto ingredientDto2,
                             @ModelAttribute("ingredient3") IngredientDto ingredientDto3,
                             @ModelAttribute("ingredient4") IngredientDto ingredientDto4,
                             @ModelAttribute("ingredient5") IngredientDto ingredientDto5,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws UserNotFoundException, RecipeNotFoundException, IOException, InterruptedException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("lastAction", "Failed to save the recipe.");
            return "index";
        }

        List<IngredientDto> list = new LinkedList<>();
        list.add(ingredientDto1);
        list.add(ingredientDto2);
        list.add(ingredientDto3);
        list.add(ingredientDto4);
        list.add(ingredientDto5);

        Customer customer = customerService.get(cid);
        Recipe recipe = recipeDtoToRecipe.convert(recipeDto);

        String str = makeString(list);
        if (str == null || str == "") {
            return "redirect:/user/" + customer.getId();
        }
     //   Nutrition nutrition = restNutritionController.getNutrition(str);


        customer.addRecipe(recipe);
        recipe.addCustomer(customer);

        //recipe.setNutrition(nutrition);
        customerService.save(customer);

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + recipe.getName() + " to your recipe list");

        return "redirect:/user/" + customer.getId();
    }


    private String makeString(List<IngredientDto> list) {
        String result = "";
        for (IngredientDto ingr : list) {
            if (ingr != null) {
                result += ingr.getQuantity() + "+" + ingr.getName();
            }
        }

        return result;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/makemenu")
    public String makeMenu(@PathVariable Integer cid, Model model) throws UserNotFoundException {

        Customer customer = customerService.get(cid);

        //testing purposes
        List<Recipe> recipes = recipeService.findAll();
        for (Recipe rec : recipes) {
            customer.addRecipe(rec);
        }

        model.addAttribute("user", customertoCustomerDto.convert(customer));
        model.addAttribute("recipeList", new HashMap<Integer, Integer>());

        return "makemenu";

    }

}
