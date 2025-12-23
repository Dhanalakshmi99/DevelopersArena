-- Users
INSERT INTO users (email, password, name, role) VALUES
('admin@example.com', 'admin123', 'Admin', 'ADMIN'),
('user1@example.com', 'user123', 'User One', 'USER');

-- Categories
INSERT INTO categories (name, description) VALUES
('Electronics', 'Electronic gadgets and devices'),
('Books', 'Books and stationery');

-- Products
INSERT INTO products (name, description, price, stock, category_id) VALUES
('Wireless Headphones', 'High quality wireless headphones', 99.99, 100, 1),
('USB-C Cable', 'Durable USB-C charging cable', 19.99, 50, 1),
('Spring Boot Guide', 'Comprehensive Spring Boot book', 29.99, 200, 2);
