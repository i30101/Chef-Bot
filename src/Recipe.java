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
        name = jsonNode.get("title").toString();
        ingredients = jsonNode.get("ingredients").toString();
        servings = jsonNode.get("servings").toString();
        instructions = jsonNode.get("instructions").toString();        
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


    @Override
    public String toString() {
        return name;
    }
}
