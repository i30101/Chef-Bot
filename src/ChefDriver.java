/**
 * @author Andrew Kim
 * @version 1.0.0
 * @since 19 May 2024
 * 
 * Driver class for ChefGPT
 * Handles user-program dialogue
 */

import java.util.*;

public class ChefDriver {
    private static Scanner scanner;
    
    private static void clear() {
        System.out.print("\033\143");
    }

    private static String printMainOptions() {
        return (
            "(1) View saved ingredients \n" +
            "(2) View saved recipes \n" + 
            "(3) Get a recipe \n\n" + 
            "(0) Leave \n\n" + 
            "Enter a number: "
        );
    }

    /**
     * Gets user input from an ordered list of options
     * @param max maximum option number, inclusive
     * @return
     */
    private static int getOption(String prompt, int max) {
        while (true) {
            System.out.print(prompt);
            String input  = scanner.nextLine();
            try {
                int option = Integer.parseInt(input);
                if (option >= 0 && option <= max) {
                    return option;
                }
                System.out.println("Invalid option, please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static void main(String[] args) {
        // Recipe[] recipes = RecipeRetriever.recipeFromIngredient("tomato");
        // for (Recipe recipe : recipes) {
        //     System.out.println(recipe);
        // }
        scanner = new Scanner(System.in);
        
        clear();
        System.out.println("******** Welcome to ChefGPT! ******** \n");
        while (true) {
            int userOption = getOption(printMainOptions(), 3);
            switch (userOption) {
                // user wants to view ingredients
                case 1:
                    
                    break;
                
                // user wants to view recipes
                case 2: 

                    break;

                // user wants to get recipes
                case 3: 

                default:
                    break;
            }
        }
    }
}
