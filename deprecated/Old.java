/**
 * @author Andrew Kim
 * @version 1.0.0
 * @since 19 May 2024
 * 
 * Driver class for ChefGPT
 * Handles user-program dialogue
 */

import java.util.*;

public class Old {
    private static Scanner scanner;

    private static final String MAIN_OPTIONS = (
        "(1) View saved ingredients \n" +
        "(2) View saved recipes \n" + 
        "(3) Get a recipe "
    );

    private static final String RECIPE_OPTIONS = (
        "(1) Search for a recipe \n" + 
        "(2) Get recipes based on your ingredients \n" + 
        "(3) Get a random recipe"
    );


    /**
     * Clears terminal of all contents
     */
    private static void clear() {
        System.out.print("\033\143");
    }


    /**
     * Gets user input from an ordered list of options
     * @param prompt options the user can see
     * @param max maximum option number, inclusive
     * @param isMain if the user is currently on the main menu
     * @return valid option chosen by user
     */
    private static int getOption(String prompt, int max, boolean isMain) {
        if (isMain) {
            System.out.println("(0) Quit");
        } else {
            System.out.println("(0) Back");
        }
        System.out.println(prompt);
        while (true) {
            System.out.print("\nEnter a number: ");
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

    private static void enterToContinue() {
        System.out.print("Press enter to continue: ");
        scanner.nextLine();
    }


    /**
     * Gets user input when not at main menu
     * @param prompt options the user can see
     * @param max maximum option number, inclusive
     * @return valid option chosen by user
     */
    private static int getOption(String prompt, int max) {
        return getOption(prompt, max, false);
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        int userOption = 1;
        while (userOption != 0) {
            clear();
            System.out.println("******** ChefGPT ******** \n");
            System.out.println(
                "View ingredients\n" + 
                "Remove ingredient\n" + 
                "View recipes"
            );
        }

        // clear();
        // // System.out.println("******** Welcome to ChefGPT! ******** \n");
        // int userOption = 1;
        // while (true) {
        //     clear();
        //     System.out.println("******** ChefGPT ******** \n");
        //     userOption = getOption(MAIN_OPTIONS, 3, true);

        //     clear();
        //     System.out.println("******** ChefGPT ******** \n");

        //     // view saved ingredients
        //     if (userOption == 1) {
        //         // clear();
        //         // System.out.println("******** ChefGPT ******** \n");
        //         System.out.println("Your ingredients: \n");
        //         int ingredientOption = getOption(
        //             "(1) Add an ingredient \n" + 
        //             "(2) Remove an ingredient", 2
        //         );
        //         if (ingredientOption == 1) {
        //             System.out.println("getting input and adding recipe");
        //         } else if (ingredientOption == 2) {
        //             // get input from user
        //             // ingredient is in list: remove it
        //             // otherwise, show that it is not valid
        //             System.out.println("removing ingredient based on input");
        //         } else {
        //             continue;
        //         }
        //         enterToContinue();
        //         continue;
            
        //     // view saved recipes
        //     } else if (userOption == 2) {
        //         // clear();
        //         // System.out.println("******** ChefGPT ******** \n");
        //         System.out.println("Your saved recipes: \n");
        //         int recipeOption = getOption(
        //             "(1) Remove a recipe", 1
        //         );
        //     }

        //     // get additional recipes
        //     else if (userOption == 3) {
        //         // clear();
        //         // System.out.println("******** ChefGPT ******** \n");
        //         System.out.println("Find a recipe: \n");
        //         int recipeOption = getOption(
        //             "(1) Search for a recipe \n" + 
        //             "(2) Get recipes based on your ingredients \n" + 
        //             "(3) Get a random recipe", 3
        //             );
        //         if (recipeOption == 1) {
        //             System.out.println("searching for recipes");
        //         } else if (recipeOption == 2) {
        //             System.out.println("getting recipes based on ingredients");
        //         } else if (recipeOption == 3) {
        //             System.out.println("getting a random recipe");
        //         } else {
        //             continue;
        //         }
        //         enterToContinue();
        //         continue;
        //     } else {
        //         break;
        //     }
        // }
        // System.out.println("Data saved. Goodbye!");
    }
}
