package ru.geekbrains.homework6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class PrepareData {

    private SessionFactory factory;

    @Autowired
    public PrepareData(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void init() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("src/main/resources/sql/full.sql")).collect(Collectors.joining(""));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void shutdown(){
        factory.close();
    }
}
