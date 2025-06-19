package com.hmg.as.test.hmg_test.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.entity.PartMst;
import com.hmg.as.test.hmg_test.entity.RoCrud;
import com.hmg.as.test.hmg_test.entity.RoInfo;
import com.hmg.as.test.hmg_test.entity.RoInfoPtCnt;
import com.hmg.as.test.hmg_test.entity.RoNoPk;
import com.hmg.as.test.hmg_test.entity.RoPtInfo;
import com.hmg.as.test.hmg_test.mapper.RoInfoMapper;
import com.hmg.as.test.hmg_test.repository.RoInfoRepository;
import com.hmg.as.test.hmg_test.service.RoInfoService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.transaction.Transactional;

@Service
public class RoInfoServiceImpl implements RoInfoService {

    @PersistenceContext
    private EntityManager em;

    // JPA
    @Autowired
    private RoInfoRepository roInfoRepository;

    // MyBatis
    @Autowired
    private RoInfoMapper roInfoMapper;

    @Override
    @Transactional
    public RoInfo getByRoInfo(String asnNo, String roNo) {

        RoNoPk roNoPk = new RoNoPk(asnNo, roNo);

        RoInfo roInfo = em.find(RoInfo.class, roNoPk);

        return roInfo;
    }

    @Override
    @Transactional
    public List<RoInfo> getByRoInfoKeyList(List<RoNoPk> roNoPkList) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<RoInfo> cq = cb.createQuery(RoInfo.class);

        Root<RoInfo> root = cq.from(RoInfo.class);

        cq.select(root).where(root.get("id").in(roNoPkList));

        return em.createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public List<RoInfo> getList(String asnNo, int page) {

        int size = 3;

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<RoInfo> cq = cb.createQuery(RoInfo.class);

        Root<RoInfo> rootEntry = cq.from(RoInfo.class);

        // 1:N 문제 FETCH 명시
        rootEntry.fetch("partMst", JoinType.LEFT);

        // order by
        cq.orderBy(cb.asc(rootEntry.get("id")));

        CriteriaQuery<RoInfo> all = cq.select(rootEntry).distinct(true);

        // Paging
        TypedQuery<RoInfo> allQuery = em.createQuery(all).setFirstResult(page * size).setMaxResults(size);

        List<RoInfo> roList = allQuery.getResultList();

        return roList;
    }

    public List<RoInfo> getListMybatis(String asnNo, int page) {
        return roInfoMapper.selectList(asnNo, page);
    }

    @Override
    @Transactional
    public List<RoInfoPtCnt> getRoListPtCnt(String asnNo) {

        /***********************************************
         * SELECT A.ASN_NO
         *      , A.RO_NO
         *      , A.VIN
         *      , B.PT_NO
         *      , B.PT_NM
         *      , ( SELECT COUNT(*)
         *            FROM T_RO_PART_INFO C
         *           WHERE A.ASN_NO = C.ASN_NO
         *             AND A.RO_NO  = C.RO_NO
         *        ) AS PART_CNT
         *   FROM T_RO_INFO A
         *   LEFT OUTER JOIN T_PART_MST B
         *     ON A.PT_NO = B.PT_NO
         ***********************************************/

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<RoInfoPtCnt> cq = cb.createQuery(RoInfoPtCnt.class);

        Root<RoInfo> roInfo = cq.from(RoInfo.class);

        // Join to PartMst
        Join<RoInfo, PartMst> partMst = roInfo.join("partMst", JoinType.LEFT);

        // 상관 서브쿼리: roPtInfo 개수
        Subquery<Long> subquery = cq.subquery(Long.class);

        Root<RoPtInfo> roPtInfo = subquery.from(RoPtInfo.class);

        subquery.select(cb.count(roPtInfo));
        subquery.where(
                cb.equal(roPtInfo.get("asnNo"), roInfo.get("id").get("asnNo")),
                cb.equal(roPtInfo.get("roNo"), roInfo.get("id").get("roNo"))
        );

        // 최종 select
        cq.select(cb.construct(
                RoInfoPtCnt.class,
                roInfo.get("id").get("asnNo"),
                roInfo.get("id").get("roNo"),
                roInfo.get("vin"),
                partMst.get("ptNo"),
                partMst.get("ptNm"),
                subquery.getSelection()
        ));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public RoCrud insert(RoCrud roCrud) {
        return roInfoRepository.save(roCrud);
    }

    @Override
    public RoCrud update(RoCrud roCrud) {
        return roInfoRepository.save(roCrud);
    }

    @Override
    @Transactional
    public RoCrud updateByColumn(RoCrud roCrud) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaUpdate<RoCrud> criteriaUpdate = cb.createCriteriaUpdate(RoCrud.class);

        Root<RoCrud> root = criteriaUpdate.from(RoCrud.class);

        /********************************************************/
        // 필수 업데이트 SET 할 필드 추가
        criteriaUpdate.set(root.get("ptNo"), roCrud.getPtNo());

        // 선택적으로 SET 할 필드 추가
        if (StringUtils.isNotBlank(roCrud.getVin())) {
            criteriaUpdate.set(root.get("vin"), roCrud.getVin());
        }
        /********************************************************/

        em.createQuery(criteriaUpdate).executeUpdate();

        // 후처리
        return roCrud;
    }

    @Override
    public String delete(RoNoPk roNoPk) {
        String result = "";
        try {
            roInfoRepository.deleteById(roNoPk);
            result = "정상 삭제 성공";
        } catch (Exception e) {
            result = "삭제 실패";
        }
        return result;
    }

}
