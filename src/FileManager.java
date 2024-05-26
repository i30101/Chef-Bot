/**
 * @author Andrew Kim
 * @version 1.0.0
 * @since 19 May 2024
 *  
 * Saves recipes, instructions, and ingredients for Recipe Bot
 */

import java.util.*;
import java.io.*;
import javax.swing.DefaultListModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;



public class FileManager {
    private static final String INGREDIENTS_PATH = "./data/ingredients.txt";
    private static final String RECIPES_PATH = "./data/recipes.json";

    /**
     * Reads saved ingredients
     * @return list of ingredients that were saved
     */
    public static DefaultListModel<String> readIngredients() {
        DefaultListModel<String> ingredients = new DefaultListModel<>();
        try {
            Scanner scanner = new Scanner(new File(INGREDIENTS_PATH));
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                ingredients.addElement(scanner.next());
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Could not read ingredients file");
        }
        
        return ingredients;
    }

    /**
     * Saves ingredients to text file
     * @param ingredients what is to be written
     */
    public static void writeIngredients(DefaultListModel<String> ingredients) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(INGREDIENTS_PATH);

            // truncate file before writing
            writer.flush();

            String writeString = "";
            for (int i = 0; i < ingredients.size(); i++) {
                writeString += ingredients.getElementAt(i) + "\n";
            }

            writer.write(writeString);
            
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing ingredients file");
        }
    }

    /**
     * Reads saved recipes
     * @return list of recipes that were saved
     */
    public static DefaultListModel<Recipe> readRecipes() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(new File(RECIPES_PATH));
            DefaultListModel<Recipe> recipes = new DefaultListModel<>();
            for (int i = 0; i < jsonNode.size(); i++) {
                recipes.addElement(new Recipe(jsonNode.get(i)));
            }
            return recipes;
        } catch (Exception e) {
            System.out.println("Error reading recipes file");
        }
        return null;
    }

    /**
     * Saves recipes to JSON file
     * @param recipes recipes to be saved
     */
    public static void writeRecipes(DefaultListModel<Recipe> recipes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            ArrayList<ObjectNode> jsonList = new ArrayList<ObjectNode>();
            for (int i = 0; i < recipes.size(); i++) {
                ObjectNode jsonNode = mapper.createObjectNode();
                Recipe elementAt = recipes.elementAt(i);
                jsonNode.put("title", elementAt.getName());
                jsonNode.put("ingredients", elementAt.getIngredients());
                jsonNode.put("servings", elementAt.getServings());
                jsonNode.put("instructions", elementAt.getInstructions());
                jsonList.add(jsonNode);
            }
            mapper.writeValue(new File(RECIPES_PATH), jsonList);
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }
}