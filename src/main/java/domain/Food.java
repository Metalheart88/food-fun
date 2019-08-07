package domain;

import java.util.UUID;

public class Food {

    private String id;
    private String name;
    private String recipe;
    private float price;
    private String ingredientId;
    private String ingredient;
    private int ingredientQty;
    private String image;

    public Food(String id, String name, String recipe, float price, String ingredientId, String ingredient, int ingredientQty, String image) {

        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.price = price;
        this.ingredientId = ingredientId;
        this.ingredient = ingredient;
        this.ingredientQty = ingredientQty;
        this.image = image;

    }

    public Food(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientQty() {
        return ingredientQty;
    }

    public void setIngredientQty(int ingredientQty) {
        this.ingredientQty = ingredientQty;
    }

    public String getImage() {
        return image;
    }
}
