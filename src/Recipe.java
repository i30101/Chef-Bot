/**
 * @author Andrew Kim
 * @version 1.0.0
 * @since 19 May 2024
 * 
 * Recipe object to simplify recipe storing
 */

import com.fasterxml.jackson.databind.JsonNode;;

public class Recipe {
    private String name;
    private String ingredients;
    private String servings;
    private String instructions;
    

    /**
     * Creates a recipe based on JsonNode object
     * @param jsonNode JSON object
     */
    public Recipe(JsonNode jsonNode) {
        System.out.println(jsonNode);
        name = jsonNode.get("title").toString().replace("\"", "");
        ingredients = jsonNode.get("ingredients").toString().replace("\"", "");
        servings = jsonNode.get("servings").toString().replace("\"", "");
        instructions = jsonNode.get("instructions").toString().replace("\"", "");        
    }

    /**
     * Creates a recipe based on String inputs
     * @param nameString name
     * @param ingredString ingredients
     * @param servsString servings
     * @param insString instructions
     */
    public Recipe(String nameString, String ingredString, String servsString, String insString) {
        name = nameString;
        ingredients = ingredString;
        servings = servsString;
        instructions = insString;
    }
    

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getServings() {
        return servings;
    }

    public String getInstructions() {
        return instructions;
    }


    @Override
    public String toString() {
        return name;
    }
}
