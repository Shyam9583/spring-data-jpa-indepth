package online.technotap.springdatajpaindepth;


import org.testcontainers.containers.MySQLContainer;

public class BaseTest {

    public static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.27")
            .withDatabaseName("test-db")
            .withUsername("tester")
            .withPassword("password");

    static {
        MY_SQL_CONTAINER.start();
    }
}
