package org.academiadecodigo.tailormoons.placeholder.controllers.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.academiadecodigo.tailormoons.placeholder.persistence.model.Nutrition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class RestNutritionController {

    private String key = "ZeutG1aNQrfe4ToTiVnhIQ==1M1MhB0LCglt8F9b";


    public Nutrition getNutrition(String string) throws IOException, InterruptedException {

        String uri = "https://api.calorieninjas.com/v1/nutrition?query=" + string;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-Api-Key", key)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String[] nutritionElements = response.body().split(", \\{");

        return addIngredients(recipeNutrients(nutritionElements));

    }


    private ArrayList<Nutrition> recipeNutrients(String[] str) throws IOException {

        ArrayList<Nutrition> ingredients = new ArrayList<>();

        for (String element : str) {
            String result = "{" + element.replace("{\"items\": [{", "")
                    .replaceAll("\\]", "");
            ingredients.add(stringToNutrients(result));

        }

        return ingredients;
    }


    private Nutrition stringToNutrients(String str) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Nutrition n = mapper.readValue(str, Nutrition.class);
        mapper = null;
        return n;
    }


    private Nutrition addIngredients(ArrayList<Nutrition> ingredients) {

        float sugar_g = 0f;
        float fiber_g = 0f;
        float sodium_mg = 0f;
        float potassium_mg = 0f;
        float fat_saturated_g = 0f;
        float fat_total_g = 0f;
        float calories = 0f;
        float cholesterol_mg = 0f;
        float protein_g = 0f;
        float carbohydrates_total_g = 0f;

        for (Nutrition nutrient : ingredients) {
            sugar_g += nutrient.getSugar_g();
            fiber_g += nutrient.getFiber_g();
            sodium_mg += nutrient.getSodium_mg();
            potassium_mg += nutrient.getPotassium_mg();
            fat_saturated_g += nutrient.getFat_saturated_g();
            fat_total_g += nutrient.getFat_total_g();
            calories += nutrient.getCalories();
            cholesterol_mg += nutrient.getCholesterol_mg();
            protein_g += nutrient.getProtein_g();
            carbohydrates_total_g += nutrient.getCarbohydrates_total_g();
        }

        Nutrition recipeNutrition = new Nutrition();
        recipeNutrition.setSugar_g(sugar_g);
        recipeNutrition.setFiber_g(fiber_g);
        recipeNutrition.setSodium_mg(sodium_mg);
        recipeNutrition.setPotassium_mg(potassium_mg);
        recipeNutrition.setFat_saturated_g(fat_saturated_g);
        recipeNutrition.setFat_total_g(fat_total_g);
        recipeNutrition.setCalories(calories);
        recipeNutrition.setCholesterol_mg(cholesterol_mg);
        recipeNutrition.setProtein_g(protein_g);
        recipeNutrition.setCarbohydrates_total_g(carbohydrates_total_g);

        return recipeNutrition;

    }
}
