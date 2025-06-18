package com.hmg.as.test.hmg_test.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.entity.RoInfo;
import com.hmg.as.test.hmg_test.entity.RoNoPk;
import com.hmg.as.test.hmg_test.entity.RoPtInfo;
import com.hmg.as.test.hmg_test.repository.RoInfoRepository;
import com.hmg.as.test.hmg_test.service.RoInfoService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class RoInfoServiceImpl implements RoInfoService {

	@PersistenceContext
	private EntityManager em;

	private final RoInfoRepository roInfoRepository;

	public RoInfoServiceImpl(RoInfoRepository roInfoRepository) {
		this.roInfoRepository = roInfoRepository;
	}

//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hmg-test");
//	EntityManager em = emf.createEntityManager();
//	EntityTransaction tx = em.getTransaction();
//	tx.begin();

//********************************************************************
//  JPQL 기본 조회
//	List<Object[]> result = em.createQuery("select info.roNo, info.asnNo, info.vin, info.ptNo, mst.ptNm from EnRoInfo info left outer join EnPartMst mst on info.ptNo = mst.ptNo", Object[].class).getResultList();
//
//	int rnum= 1;
//	for (Object[] row : result) {
//	    System.out.println("--------------------------");
//	    System.out.println("RNUM  = " +rnum);
//	    System.out.println("roNo  = " + (String) row[0]);
//	    System.out.println("asnNo = " + (String) row[1]);
//	    System.out.println("vin   = " + (String) row[2]);
//	    System.out.println("ptNo  = " + (String) row[3]);
//	    System.out.println("ptNm  = " + (String) row[4]);
//	}
//	System.out.println("--------------------------");
//********************************************************************

//	tx.commit();
//	em.close();
//	emf.close();

	@Override
	@Transactional
	public RoInfo getByRoInfo(String asnNo, String roNo) {

		RoNoPk roNoPk = new RoNoPk(asnNo, roNo);

		RoInfo roInfo = em.find(RoInfo.class, roNoPk);

		System.out.println("--------- RO --------");
		System.out.println("ASN_NO = " + roInfo.getId().getAsnNo());
		System.out.println("RO_NO  = " + roInfo.getId().getRoNo());
		System.out.println("VIN    = " + roInfo.getVin());
		System.out.println("PTNO   = " + roInfo.getPtNo());

		List<RoPtInfo> list = roInfo.getRoPtInfo();

//		System.out.println("-------- PART LIST ---------");
//		for (RoPtInfo en : list) {
//			System.out.println("-----------------");
//			System.out.println(en.toString());
//		}
		return roInfo;
	}

	@Override
	@Transactional
	public List<RoInfo> getList(String asnNo, int page) {

		int size = 3;

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<RoInfo> cq = cb.createQuery(RoInfo.class);

		Root<RoInfo> rootEntry = cq.from(RoInfo.class);

		// 1:N 문제 FETCH 해결
		rootEntry.fetch("partMst", JoinType.LEFT);

		// order by
		cq.orderBy(cb.asc(rootEntry.get("id")));

		CriteriaQuery<RoInfo> all = cq.select(rootEntry).distinct(true);

		// Paging
		TypedQuery<RoInfo> allQuery = em.createQuery(all).setFirstResult(page * size).setMaxResults(size);

		List<RoInfo> roList = allQuery.getResultList();

//		for (RoInfo en : roList) {
//			System.out.println("-----------------");
//			System.out.println(en.toString());
//		}
		return roList;
	}

}
