/**
 * @author Andrew Kim
 * @version 1.0.0
 * @since 19 May 2024
 * 
 * Driver class for ChefGPT
 * Handles user-program dialogue
 */

public class ChefDriver {
    public static void main(String[] args) {
        Recipe[] recipes = RecipeRetriever.recipeFromIngredient("paprika");
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        
    }
}
