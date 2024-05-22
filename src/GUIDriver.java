/**
 * @author Andrew Kim
 * @version 2.0.0
 * @since 20 May 2024
 * 
 * GUI driver for Recipe Bot
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUIDriver extends JFrame {
    
    private DefaultListModel<String> ingredListModel;
    private DefaultListModel<Recipe> recipeListModel;
    private DefaultListModel<Recipe> searchListModel;

    public GUIDriver(DefaultListModel<String> savedIngredients, DefaultListModel<Recipe> savedRecipes) {
        setTitle("ChefGPT");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // tab 1: saved ingredients
        JPanel ingredientsPanel = new JPanel(new BorderLayout());
        // ingredListModel = new DefaultListModel<>();
        ingredListModel = savedIngredients;
        JList<String> ingredientList = new JList<>(ingredListModel);
        ingredientsPanel.add(new JScrollPane(ingredientList), BorderLayout.CENTER);

        JPanel ingredientButtonsPanel = new JPanel();
        JTextField ingredientField = new JTextField(15);
        JButton addIngredButton = new JButton("Add Ingredient");
        JButton removeIngredButton = new JButton("Remove Ingredient");

        ingredientButtonsPanel.add(ingredientField);
        ingredientButtonsPanel.add(addIngredButton);
        ingredientButtonsPanel.add(removeIngredButton);
        ingredientsPanel.add(ingredientButtonsPanel, BorderLayout.SOUTH);

        // user wants to add an ingredient
        addIngredButton.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String ingredient = ingredientField.getText();
                if (!ingredient.isEmpty()) {
                    ingredListModel.addElement(ingredient);
                    ingredientField.setText("");
                    FileManager.writeIngredients(ingredListModel);
                }
            }
        });

        // user wants to remove an ingredient
        removeIngredButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedIngredient = ingredientList.getSelectedValue();
                if (selectedIngredient != null) {
                    ingredListModel.removeElement(selectedIngredient);
                    FileManager.writeIngredients(ingredListModel);
                }
            }
        });

        tabbedPane.add("View Ingredients", ingredientsPanel);



        // tab 2: saved recipes
        JPanel recipesPanel = new JPanel(new BorderLayout());
        // recipeListModel = new DefaultListModel<>();
        recipeListModel = savedRecipes;
        JList<Recipe> recipeList = new JList<>(recipeListModel);
        recipesPanel.add(new JScrollPane(recipeList), BorderLayout.CENTER);
        
        JPanel recipeButtonsPanel = new JPanel();
        // JTextField recipeField = new JTextField(15);
        JButton removeRecipeButton = new JButton("Remove Recipe");
        
        // recipeButtonsPanel.add(recipeField);
        recipeButtonsPanel.add(removeRecipeButton);
        recipesPanel.add(recipeButtonsPanel, BorderLayout.SOUTH);

        // user wants to remove recipe
        removeRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Recipe selectedRecipe = recipeList.getSelectedValue();
                if (selectedRecipe != null) {
                    recipeListModel.removeElement(selectedRecipe);
                    FileManager.writeRecipes(recipeListModel);
                }
            }
        });

        // show more details about recipe
        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    Recipe selectedRecipe = (Recipe) list.getModel().getElementAt(index);
                    showRecipeDetails(selectedRecipe);
                }
            }
        });

        tabbedPane.addTab("View Recipes", recipesPanel);



        // tab 3: searching for recipes
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchListModel = new DefaultListModel<>();
        JList<Recipe> searchList = new JList<>(searchListModel);
        searchPanel.add(new JScrollPane(searchList), BorderLayout.CENTER);
        
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add Recipe");


        JPanel searchControlsPanel = new JPanel();
        searchControlsPanel.add(searchField);
        searchControlsPanel.add(searchButton);
        searchControlsPanel.add(addButton);
        searchPanel.add(searchControlsPanel, BorderLayout.NORTH);


        // user is searching for recipes through search bar
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                if (!query.isEmpty()) {
                    searchListModel.removeAllElements();
                    Recipe[] searchedRecipes = RecipeRetriever.getRecipeList(query);
                    for (Recipe recipe : searchedRecipes) {
                        searchListModel.addElement(recipe);
                    }
                    searchField.setText("");
                }
            }
        });

        // user wants to add a recipe in search results
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Recipe selectedRecipe = searchList.getSelectedValue();
                if (selectedRecipe != null) {
                    recipeListModel.addElement(selectedRecipe);
                    FileManager.writeRecipes(recipeListModel);
                }
            }
        });

        searchList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    Recipe selectedRecipe = (Recipe) list.getModel().getElementAt(index);
                    showRecipeDetails(selectedRecipe);
                }
            }
        });

        tabbedPane.addTab("Search Recipes", searchPanel);

        add(tabbedPane);
    }

    private void showRecipeDetails(Recipe recipe) {
        JTextArea textArea = new JTextArea(25, 100);
        textArea.setLineWrap(true);
        textArea.setText("Ingredients: \n" + recipe.getIngredients().replace("|", "\n"));
        textArea.append("\n\nInstructions: " + recipe.getInstructions());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, recipe.getName(), JOptionPane.INFORMATION_MESSAGE);
    }

    class RecipeListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Recipe) {
                value = ((Recipe)value).getName() + " - " + ((Recipe)value).getServings() + " servings";
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIDriver(FileManager.readIngredients(), FileManager.readRecipes()).setVisible(true);
            }
        });
    }
}
