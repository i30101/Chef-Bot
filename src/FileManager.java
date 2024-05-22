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


public class FileManager {
    private static final String INGREDIENTS_PATH = "./data/ingredients.txt";


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
        
        return null;
    }

    /**
     * Writes ingredients to text file
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
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error closing writer: " + e);
            }
        }
    }
}