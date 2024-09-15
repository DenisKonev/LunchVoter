package ru.topjava.lunchvoter;

import lombok.experimental.UtilityClass;
import ru.topjava.lunchvoter.model.*;

import java.util.List;

@UtilityClass
public class TestData {
    public static final Integer MENU_ID_1 = 1;
    public static final Integer MENU_ID_2 = 2;
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

    public static Restaurant createRestaurantWithoutId() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Burger Queen");
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

    public static User createUserWithoutId() {
        User user = new User();
        user.setUsername("jake_smith");
        return user;
    }

    public static List<User> createUsers() {
        return List.of(createUser1(), createUser2());
    }

    public static Menu createMenu1() {
        Menu menu = new Menu();
        menu.setId(MENU_ID_1);
        menu.setRestaurant(createRestaurant1());
        return menu;
    }

    public static Menu createMenu2() {
        Menu menu = new Menu();
        menu.setId(MENU_ID_2);
        menu.setRestaurant(createRestaurant2());
        return menu;
    }

    public static List<Menu> createMenus() {
        Menu menu1 = createMenu1();
        menu1.setId(1);
        Menu menu2 = createMenu2();
        menu2.setId(2);
        menu2.setRestaurant(createRestaurant1());
        return List.of(menu1, menu2);
    }

    public static MenuItem createMenuItem1() {
        return createMenuItemWithId(MENU_ITEM_ID_1, "Pizza", createMenu1());
    }

    public static MenuItem createMenuItem2() {
        return createMenuItemWithId(MENU_ITEM_ID_2, "Pasta", createMenu2());
    }

    public static MenuItem createMenuItemWithoutId() {
        return createMenuItem(null, "Salad", createMenu1());
    }

    private static MenuItem createMenuItemWithId(Integer id, String name, Menu menu) {
        return createMenuItem(id, name, menu);
    }

    private static MenuItem createMenuItem(Integer id, String name, Menu menu) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        menuItem.setName(name);
        menuItem.setMenu(menu);
        return menuItem;
    }

    public static List<MenuItem> createMenuItems() {
        return List.of(createMenuItem1(), createMenuItem2());
    }

    public static Vote createVote1() {
        return createVoteWithId(VOTE_ID_1, createUser1());
    }

    public static Vote createVote2() {
        return createVoteWithId(VOTE_ID_2, createUser2());
    }

    public static Vote createVoteWithoutId() {
        return createVote(null, createUser1());
    }

    private static Vote createVoteWithId(Integer id, User user) {
        return createVote(id, user);
    }

    private static Vote createVote(Integer id, User user) {
        Vote vote = new Vote();
        vote.setId(id);
        vote.setUser(user);
        return vote;
    }

    public static List<Vote> createVotes() {
        return List.of(createVote1(), createVote2());
    }
}