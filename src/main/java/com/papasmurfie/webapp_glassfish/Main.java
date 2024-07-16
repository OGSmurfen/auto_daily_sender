package com.papasmurfie.webapp_glassfish;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {//Main class used for DB connecting quickly. Not building and deploying everything (laptop slow)
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = new User();
        //user.setId(Long.parseLong("3"));
        user.setPassword("1");
        user.setUsername("user");
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
