package ru.topjava.lunchvoter;

import lombok.experimental.UtilityClass;
import ru.topjava.lunchvoter.model.*;

import java.util.List;

@UtilityClass
public class TestData {
    public static final Integer MENU_ID = 1;
    public static final Integer MENU_ITEM_ID_1 = 1;
    public static final Integer MENU_ITEM_ID_2 = 2;
    public static final Integer RESTAURANT_ID_1 = 1;
    public static final Integer RESTAURANT_ID_2 = 2;
    public static final Integer USER_ID_1 = 1;
    public static final Integer USER_ID_2 = 2;
    public static final Integer VOTE_ID_1 = 1;
    public static final Integer VOTE_ID_2 = 2;

    public static Restaurant createRestaurant1() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(RESTAURANT_ID_1);
        restaurant.setName("Italian Bistro");
        return restaurant;
    }

    public static Restaurant createRestaurant2() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(RESTAURANT_ID_2);
        restaurant.setName("Sushi House");
        return restaurant;
    }

    public static List<Restaurant> createRestaurants() {
        return List.of(createRestaurant1(), createRestaurant2());
    }

    public static User createUser1() {
        User user = new User();
        user.setId(USER_ID_1);
        user.setUsername("john_doe");
        return user;
    }

    public static User createUser2() {
        User user = new User();
        user.setId(USER_ID_2);
        user.setUsername("jane_doe");
        return user;
    }

    public static List<User> createUsers() {
        return List.of(createUser1(), createUser2());
    }

    public static Restaurant createTestRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(RESTAURANT_ID_1);
        return restaurant;
    }

    public static Menu createTestMenu() {
        Menu menu = new Menu();
        menu.setId(MENU_ID);
        menu.setRestaurant(createTestRestaurant());
        return menu;
    }

    public static List<Menu> createTestMenus() {
        Menu menu1 = createTestMenu();
        menu1.setId(1);
        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setRestaurant(createTestRestaurant());
        return List.of(menu1, menu2);
    }

    public static MenuItem createMenuItem1() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(MENU_ITEM_ID_1);
        menuItem.setName("Pizza");
        menuItem.setMenu(createTestMenu());
        return menuItem;
    }

    public static MenuItem createMenuItem2() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(MENU_ITEM_ID_2);
        menuItem.setName("Pasta");
        menuItem.setMenu(createTestMenu());
        return menuItem;
    }

    public static List<MenuItem> createMenuItems() {
        return List.of(createMenuItem1(), createMenuItem2());
    }

    public static Vote createVote1() {
        Vote vote = new Vote();
        vote.setId(VOTE_ID_1);
        vote.setUser(createUser1());
        return vote;
    }

    public static Vote createVote2() {
        Vote vote = new Vote();
        vote.setId(VOTE_ID_2);
        vote.setUser(createUser2());
        return vote;
    }

    public static List<Vote> createVotes() {
        return List.of(createVote1(), createVote2());
    }
}
