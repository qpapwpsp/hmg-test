package com.hmg.as.test.hmg_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HmgTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(HmgTestApplication.class, args);

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hmg-test");
//
//		EntityManager em = emf.createEntityManager();
//		
//		EntityTransaction tx = em.getTransaction();
//
//		tx.begin();

//********************************************************************
//      JPQL 기본 조회
//		List<Object[]> result = em.createQuery("select info.roNo, info.asnNo, info.vin, info.ptNo, mst.ptNm from EnRoInfo info left outer join EnPartMst mst on info.ptNo = mst.ptNo", Object[].class).getResultList();
//
//		int rnum= 1;
//		for (Object[] row : result) {
//		    System.out.println("--------------------------");
//		    System.out.println("RNUM  = " +rnum);
//		    System.out.println("roNo  = " + (String) row[0]);
//		    System.out.println("asnNo = " + (String) row[1]);
//		    System.out.println("vin   = " + (String) row[2]);
//		    System.out.println("ptNo  = " + (String) row[3]);
//		    System.out.println("ptNm  = " + (String) row[4]);
//		}
//		System.out.println("--------------------------");
//********************************************************************

//		tx.commit();
//
//		em.close();
//
//		emf.close();
	}

}
