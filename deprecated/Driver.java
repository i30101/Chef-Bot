import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Driver extends JFrame {
    private DefaultListModel<String> ingredientListModel;
    private DefaultListModel<String> recipeListModel;

    public Driver() {
        setTitle("Recipe Manager");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Viewing and managing ingredients
        JPanel ingredientsPanel = new JPanel(new BorderLayout());
        ingredientListModel = new DefaultListModel<>();
        JList<String> ingredientList = new JList<>(ingredientListModel);
        ingredientsPanel.add(new JScrollPane(ingredientList), BorderLayout.CENTER);
        
        JPanel ingredientButtonsPanel = new JPanel();
        JTextField ingredientField = new JTextField(15);
        JButton addIngredientButton = new JButton("Add Ingredient");
        JButton removeIngredientButton = new JButton("Remove Ingredient");
        
        ingredientButtonsPanel.add(ingredientField);
        ingredientButtonsPanel.add(addIngredientButton);
        ingredientButtonsPanel.add(removeIngredientButton);
        ingredientsPanel.add(ingredientButtonsPanel, BorderLayout.SOUTH);

        addIngredientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ingredient = ingredientField.getText();
                if (!ingredient.isEmpty()) {
                    ingredientListModel.addElement(ingredient);
                    ingredientField.setText("");
                }
            }
        });

        removeIngredientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedIngredient = ingredientList.getSelectedValue();
                if (selectedIngredient != null) {
                    ingredientListModel.removeElement(selectedIngredient);
                }
            }
        });

        tabbedPane.addTab("View Ingredients", ingredientsPanel);

        // Tab 2: Viewing and managing saved recipes
        JPanel recipesPanel = new JPanel(new BorderLayout());
        recipeListModel = new DefaultListModel<>();
        JList<String> recipeList = new JList<>(recipeListModel);
        recipesPanel.add(new JScrollPane(recipeList), BorderLayout.CENTER);

        JPanel recipeButtonsPanel = new JPanel();
        JTextField recipeField = new JTextField(15);
        JButton addRecipeButton = new JButton("Add Recipe");
        JButton removeRecipeButton = new JButton("Remove Recipe");

        recipeButtonsPanel.add(recipeField);
        recipeButtonsPanel.add(addRecipeButton);
        recipeButtonsPanel.add(removeRecipeButton);
        recipesPanel.add(recipeButtonsPanel, BorderLayout.SOUTH);

        addRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recipe = recipeField.getText();
                if (!recipe.isEmpty()) {
                    recipeListModel.addElement(recipe);
                    recipeField.setText("");
                }
            }
        });

        removeRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRecipe = recipeList.getSelectedValue();
                if (selectedRecipe != null) {
                    recipeListModel.removeElement(selectedRecipe);
                }
            }
        });

        tabbedPane.addTab("View Recipes", recipesPanel);

        // Tab 3: Searching for recipes
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JTextArea searchResultsArea = new JTextArea();
        searchResultsArea.setEditable(false);

        JPanel searchControlsPanel = new JPanel();
        searchControlsPanel.add(searchField);
        searchControlsPanel.add(searchButton);
        searchPanel.add(searchControlsPanel, BorderLayout.NORTH);
        searchPanel.add(new JScrollPane(searchResultsArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                if (!query.isEmpty()) {
                    // Simulating search results (in a real application, you would perform a search in a database or API)
                    searchResultsArea.setText("Search results for \"" + query + "\":\n");
                    searchResultsArea.append("- Example Recipe 1\n");
                    searchResultsArea.append("- Example Recipe 2\n");
                    searchResultsArea.append("- Example Recipe 3\n");
                }
            }
        });

        tabbedPane.addTab("Search Recipes", searchPanel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Driver().setVisible(true);
            }
        });
    }
}
