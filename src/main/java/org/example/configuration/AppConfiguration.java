package org.example.configuration;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//nie można zaimportować 2 bibliotek o tej samej nazwie
@org.springframework.context.annotation.Configuration
@ComponentScan("org.example")
public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }

//    @Bean
//    public Connection connection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/bookstore",
//                    "root",
//                    "");
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /*@Bean
    public IBookDAO bookDAO(IBookIdSequence bookIdSequence) {
        return new BookDB(bookIdSequence);
    }
    @Bean
    public IBookIdSequence bookIdSequence() {
        return new BookIdSequence();
    }*/
}
