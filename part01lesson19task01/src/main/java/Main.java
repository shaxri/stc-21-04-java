import DB.*;
import DTO.Order;
import DTO.OrderStatus;
import DTO.Product;
import DTO.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
* Задание 1. Взять за основу предметную область выбранную на занятии по UML:

 Спроектировать базу данных для выбранной предметной области (минимум три таблицы).
 Типы и состав полей в таблицах на ваше усмотрение.
 Связи между таблицами делать не обязательно.
Задание 2. Через JDBC интерфейс описать CRUD операции с созданными таблицами:

1)Применить параметризованный запрос.
2)Применить батчинг.
3)Использовать ручное управление транзакциями.
4)Предусмотреть использование savepoint при выполнении логики из нескольких запросов.
5)Предусмотреть rollback операций при ошибках.
6)Желательно предусмотреть метод сброса и инициализации базы данных.*/
public class Main {

    private static List<User> users;
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionManager.getConnection();
        logger.debug("SetAutoCommit = false");
        connection.setAutoCommit(false);
        DatabaseUtils databaseUtils = new DatabaseUtils(connection);
        databaseUtils.renewDatabase();


        logger.debug("Generate data");
        generateData();
        DatabaseInsert databaseInsert = new DatabaseInsert(connection);
        for (User user : users) {
            databaseInsert.addInTableUser(user);
        }
        for (Product product : products) {
            databaseInsert.addInTableProduct(product);
        }
        for (Order order : orders) {
            databaseInsert.addInTableOrder(order);
        }

        DatabaseUpdate databaseUpdate = new DatabaseUpdate(connection);
        databaseUpdate.updateProductPrice(Arrays.asList(2, 3));
        DatabaseSelect databaseSelect = new DatabaseSelect(connection);
        databaseSelect.outputTableUser();
        databaseSelect.outputTableProduct();
        databaseUtils.doSomeTransactions();
        databaseSelect.outputTableProduct();
        databaseSelect.outputTableOrder();
        logger.debug("SetAutoCommit = true");
        connection.setAutoCommit(true);
}

    private static void generateData() {
        logger.info("Generate users");
        users = new ArrayList<>();
        users.add(User.builder()
                .id(1)
                .login("shaxri")
                .password("12345678")
                .name("ergashevs")
                .email("shahriyor.ergashev@gmail.com")
                .build());
        users.add(User.builder()
                .id(2)
                .login("alexey")
                .password("12345678")
                .name("alexey")
                .email("alexey@gmail.com")
                .build());
        logger.info("Generate products");
        products = new ArrayList<>();
        products.add(new Product(1, "table top", 34.5, "Classic Wood Plastic"));
        products.add(new Product(2, "arm chair", 21.9, "El Porta"));
        products.add(new Product(3, "mug", 1.7, "IKEA"));
        products.add(new Product(4, "tea pot", 0.3, "Tefal"));

        logger.info("Generate orders");
        orders = new ArrayList<>();
        orders.add(new Order(1, 1, users.get(1), products.get(0), OrderStatus.CREATED,
                new Date(121, 3, 9), "Tashkent"));
        orders.add(new Order(2, 2, users.get(1), products.get(1), OrderStatus.CREATED,
                new Date(121, 3, 10), "Navoi"));
        orders.add(new Order(3, 2, users.get(0), products.get(2), OrderStatus.CREATED,
                new Date(121, 3, 11), "Innopolis"));
        orders.add(new Order(4, 1, users.get(0), products.get(3), OrderStatus.CREATED,
                new Date(121, 3, 12), "Innopolis"));
    }
}
