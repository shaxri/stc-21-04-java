package DB;

public class CrudOperations {
    public static final String INSERT_INTO_ORDERS = "INSERT INTO Orders(number , user_id, product_id, status, " +
            "order_date, delivery_information) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String INSERT_INTO_USERS = "INSERT INTO Users(login, password, name, email) VALUES (?, ?, ?, ?);";
    public static final String INSERT_INTO_PRODUCTS = "INSERT INTO Products(name, price, manufacturer)" +
            " VALUES (?, ?, ?);";
    public static final String SELECT_FROM_USERS = "select * from Users order by id";
    public static final String SELECT_FROM_PRODUCTS = "select * from Products order by id";
    public static final String SELECT_FROM_ORDERS = "select * from Orders order by id";
    public static final String UPDATE_PRODUCTS = "update Products set price= 12.5 where id = ?";
    public static final String RENEW_DATABASE = "DROP TABLE IF EXISTS Orders;\n" +
            "DROP TABLE IF EXISTS Products;\n" +
            "DROP TABLE IF EXISTS Users;\n" +
            "DROP TABLE IF EXISTS APP_LOGS;\n" +
            "\n" +
            "CREATE TABLE Users (\n " +
            "id bigserial primary key, \n" +
            "login varchar(100) UNIQUE NOT NULL, \n" +
            "password varchar(100) NOT NULL, \n" +
            "name varchar(100) NOT NULL,\n" +
            "email varchar(100) UNIQUE NOT NULL);\n" +
            "\n" +
            "CREATE TABLE Products (\n " +
            "id bigserial primary key, \n" +
            "name varchar(100) NOT NULL, \n" +
            "price real NOT NULL, \n" +
            "manufacturer varchar(100) NOT NULL);\n" +
            "\n" +
            "DROP TYPE IF EXISTS order_state;\n" +
            "CREATE TYPE order_state as enum ('created', 'approved', 'finished');" +
            "\n" +
            "CREATE TABLE Orders (\n " +
            "id bigserial primary key, \n" +
            "number integer, \n" +
            "user_id integer NOT NULL, \n" +
            "product_id integer NOT NULL, \n" +
            "status order_state DEFAULT 'created',\n" +
            "order_date timestamp NOT NULL,\n" +
            "delivery_information varchar(100) NOT NULL,\n" +
            "foreign key (user_id) REFERENCES Users(id) ON DELETE CASCADE,\n" +
            "foreign key (product_id) REFERENCES Products(id) ON DELETE CASCADE);\n" +
            "\n" +

            "create table APP_LOGS( \n " +
            "LOG_ID varchar(100) primary key,\n" +
            "ENTRY_DATE timestamp,\n" +
            "LOGGER varchar(100),\n" +
            "LOG_LEVEL varchar(100),\n" +
            "MESSAGE TEXT,\n" +
            "EXCEPTION TEXT);";
}
