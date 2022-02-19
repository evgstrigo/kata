package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {

    // Connect to MySQL by JDBC
    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "kata_db";
        String userName = "root";
        String password = "123456";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName,
                password);
        System.out.println("Connection opened");
        return connection;
    }



    // Connect to MySQL by HIBERNATE
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;


    static {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        Map<String, String> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/kata_db");
        dbSettings.put(Environment.USER, "root");
        dbSettings.put(Environment.PASS, "123456");
        dbSettings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        dbSettings.put(Environment.AUTOCOMMIT, "true");
        dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        registryBuilder.applySettings(dbSettings);

        standardServiceRegistry = registryBuilder.build();
        MetadataSources sources = new MetadataSources(standardServiceRegistry);
        Metadata metadata = sources
                .addAnnotatedClass(User.class)
                .getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }






}
