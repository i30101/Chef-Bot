/**
 * @author Andrew Kim
 * @version 1.0.0
 * @since 19 May 2025
 * 
 * Recipe handler for Recipe Bot
 * Obtains recipes and instructions using API Ninja
 */


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;


public class RecipeRetriever {
    private static final String RECIPE_URL = "https://api.api-ninjas.com/v1/recipe?query=";
    private static final String API_KEY = "eRKKQJMvsKi8eZujaeWgcA==KXbzpW6Kq47ozSLX";

    
    /**
     * Gets a response given URL
     * @param requestURL
     * @return
     */
    private static JsonNode getReponse(String requestURL) {
        try {
            System.out.println(requestURL);
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-API-Key", API_KEY);

            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            return root;
        } catch (IOException e) {
            System.out.println("weird error");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 
     * @param ingredient Ingredient that should be used in 
     * @return a list of recipes
     */
    public static Recipe[] recipeFromIngredient(String ingredient) {
        JsonNode recipesJson = getReponse(RECIPE_URL + ingredient);
        // JsonNode recipe = recipes.get(0);
        Recipe[] recipes = new Recipe[recipesJson.size()];
        for (int i = 0; i < recipesJson.size(); i++) {
            recipes[i] = new Recipe(recipesJson.get(i));
        }
        return recipes;
    }

}
