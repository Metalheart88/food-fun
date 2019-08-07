package presentation;

import domain.Food;
import repository.FoodRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class TestFood {

    public static void main(String[] args) {

        FoodRepository fr = new FoodRepository();
        String id = UUID.randomUUID().toString();
        String ingrId = UUID.randomUUID().toString();
        String recipe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lobortis non risus id bibendum. " +
                "Vestibulum tempor eros nec lectus suscipit pharetra. Morbi interdum consequat urna. Integer enim libero, " +
                "condimentum non ipsum et, fringilla commodo risus. Maecenas id gravida lectus. Nullam viverra diam ultricies, " +
                "tristique mi eget, dapibus diam. In at dapibus neque, at blandit dolor. Sed vestibulum lorem ut turpis efficitur," +
                " in pretium sapien elementum. Duis lobortis accumsan vehicula.";

        //ADD FOOD ---- TEST OK
//        Food food = new Food(id, "Lasagna", recipe, 12.99f, ingrId, "dough, tomato paste, ground beef", 5, "https://www.thewholesomedish.com/wp-content/uploads/2018/07/Best-Lasagna-550-500x500.jpg");
//        fr.add(food);

        //UPDATE FOOD ---- TEST OK
//        Food food = new Food(id, "asdf", recipe, 5.99f, ingrId,"asdf, asdf", 3);
//        fr.add(food);

//        Food food = fr.getById("f4702f19-20e8-4878-93f7-336b00d118d0");
//        food.setName("Fried eggs");
//        food.setPrice(4.35f);
//        food.setRecipe("Crack eggs and fry them");
//        food.setIngredient("eggs, bacon");
//        food.setIngredientQty(3);
//        fr.update(food);

        //DELETE FOOD ---- TEST OK
//        Food food = new Food(id, "Pelmeni", recipe, 5.99f, ingrId, "dough, meat", 3);
//        fr.add(food);

//        Food food = fr.getById("18891458-3681-40c6-9d6c-b7f8a7385ae5");
//        fr.delete(food);

        //FIND BY ID ---- TEST OK
//        Food food = fr.getById("0100f1b7-de46-43b1-a09e-ecb8f22d3ac1");
//        System.out.println("ID: "+food.getId()+" Name: "+food.getName()+" Price: "+food.getPrice());

        //FIND ALL ---- TEST OK
//        List<Food> food = fr.getAll();
    }

}
