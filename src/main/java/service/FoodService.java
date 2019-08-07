package service;

import domain.Food;
import exception.EntityNotFoundException;
import repository.FoodRepository;

import java.util.List;

public class FoodService {

    private FoodRepository foodRepository;

    public FoodService() {
        foodRepository = new FoodRepository();
    }

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void add(Food food) {
        foodRepository.add(food);
    }

    public void update(Food food) {
        Food foodDB = foodRepository.getById(food.getId());
        if (foodDB != null) {
            foodRepository.getById(food.getId());
        } else {
            throw new EntityNotFoundException("Food with id " + food.getId() + " was not found!");
        }
    }

    public void delete(String id) {
        Food foodDB = foodRepository.getById(id);
        if (foodDB != null) {
            foodRepository.delete(foodDB);
        } else {
            throw new EntityNotFoundException("Food with id " + id + " was not found!");
        }
    }

    public Food getById(String id) {
        Food foodDB = foodRepository.getById(id);

        if (foodDB == null) throw new EntityNotFoundException("Food with id " + id + " was not found!");
        return foodDB;
    }

    public List<Food> getAll() {
        return foodRepository.getAll();
    }

}
