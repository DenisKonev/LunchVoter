INSERT INTO users (username, password, role, created_at)
VALUES ('Admin', 'admin', 'ROLE_ADMIN', now()),
       ('John Doe', 'john.doe', 'ROLE_USER', now()),
       ('Jane Smith', 'jane.smith', 'ROLE_USER', now());

INSERT INTO restaurants (name, created_at)
VALUES ('Pizza Place', now()),
       ('Sushi House', now()),
       ('Burger Joint', now());

INSERT INTO menus (restaurant_id, created_at)
VALUES (1, now()),
       (2, now()),
       (3, now());

INSERT INTO menu_items (name, price, menu_id, created_at)
VALUES ('Margherita Pizza', 8.99, 1, now()),
       ('Pepperoni Pizza', 9.99, 1, now()),
       ('Spicy Tuna Roll', 12.99, 2, now()),
       ('California Roll', 10.99, 2, now()),
       ('Cheeseburger', 7.99, 3, now()),
       ('Veggie Burger', 6.99, 3, now());

INSERT INTO votes (user_id, restaurant_id, created_at)
VALUES (2, 1, now()),
       (3, 2, now());