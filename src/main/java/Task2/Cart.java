package Task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    /*public void cardBalancing()
    {
        boolean proteins = false;
        boolean fats = false;
        boolean carbohydrates = false;

        for (var food : foodstuffs)
        {
            if (!proteins && food.getProteins())
                proteins = true;
            else
            if (!fats && food.getFats())
                fats = true;
            else
            if (!carbohydrates && food.getCarbohydrates())
                carbohydrates = true;
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        for (var thing : market.getThings(clazz))
        {
            if (!proteins && thing.getProteins())
            {
                proteins = true;
                foodstuffs.add(thing);
            }
            else if (!fats && thing.getFats())
            {
                fats = true;
                foodstuffs.add(thing);
            }
            else if (!carbohydrates && thing.getCarbohydrates())
            {
                carbohydrates = true;
                foodstuffs.add(thing);
            }
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }*/

    public void cardBalancing() {
        // Создаем множество, которое хранит типы питательных веществ из наличия в корзине
        Set<String> nutrients = new HashSet<>();

        // Добавляем в множество типы питательных веществ, которые есть в продуктах из корзины
        foodstuffs.stream()
                .filter(food -> food.getProteins() || food.getFats() || food.getCarbohydrates())
                .forEach(food -> {
                    if (food.getProteins()) nutrients.add("proteins");
                    if (food.getFats()) nutrients.add("fats");
                    if (food.getCarbohydrates()) nutrients.add("carbohydrates");
                });

        // Проверяем, есть ли в корзине все три типа питательных веществ
        if (nutrients.size() == 3) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        // Если нет, то ищем в маркете продукты, которые дополнят недостающие типы питательных веществ
        market.getThings(clazz).stream()
                .filter(thing -> thing.getProteins() || thing.getFats() || thing.getCarbohydrates())
                .forEach(thing -> {
                    if (!nutrients.contains("proteins") && thing.getProteins()) {
                        nutrients.add("proteins");
                        foodstuffs.add(thing);
                    } else if (!nutrients.contains("fats") && thing.getFats()) {
                        nutrients.add("fats");
                        foodstuffs.add(thing);
                    } else if (!nutrients.contains("carbohydrates") && thing.getCarbohydrates()) {
                        nutrients.add("carbohydrates");
                        foodstuffs.add(thing);
                    }
                });

        // Снова проверяем, есть ли в корзине все три типа питательных веществ
        if (nutrients.size() == 3) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

    //endregion

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    public void printFoodstuffs()
    {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/


        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
