package com.hmg.as.test.hmg_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hmg.as.test.hmg_test.entity.EnRoInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class HmgTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(HmgTestApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hmg-test");

		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		EnRoInfo roInfo = new EnRoInfo("203000", "202506W0001");
		
		em.persist(roInfo);

		tx.commit();

		em.close();

		emf.close();
	}

}
