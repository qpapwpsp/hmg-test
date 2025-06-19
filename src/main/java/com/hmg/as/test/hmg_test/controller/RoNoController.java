package com.hmg.as.test.hmg_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hmg.as.test.hmg_test.entity.RoCrud;
import com.hmg.as.test.hmg_test.entity.RoInfo;
import com.hmg.as.test.hmg_test.entity.RoInfoPtCnt;
import com.hmg.as.test.hmg_test.entity.RoNoPk;
import com.hmg.as.test.hmg_test.service.RoInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/roInfo")
@Tag(name = "RO 정보 API", description = "RO 정보 API")
public class RoNoController {

    @Autowired
    private RoInfoService roInfoService;

    @Operation(summary = "RO 정보 조회 API(단건 KEY 조회)", description = "RO 정보 조회 API 입니다.(단건 KEY 조회)")
    @GetMapping("/search")
    public RoInfo searchByRoNo(@RequestParam("asnNo") String asnNo, @RequestParam("roNo") String roNo, Model model) {
        return roInfoService.getByRoInfo(asnNo, roNo);
    }

    @Operation(summary = "RO 정보 조회 API(다건 LIST<KEY> 조회)", description = "RO 정보 조회 API 입니다.(다건 LIST<KEY> 조회)")
    @PostMapping("/searchByKeyList")
    public List<RoInfo> searchByRoNo(@RequestBody List<RoNoPk> roNoPkList, Model model) {
        return roInfoService.getByRoInfoKeyList(roNoPkList);
    }

    @Operation(summary = "RO 목록 조회 API", description = "RO 목록 조회 API 입니다.")
    @GetMapping("/searchList")
    public List<RoInfo> searchList(@RequestParam("asnNo") String asnNo, int page, Model model) {
        return roInfoService.getList(asnNo, page);
    }

    @Operation(summary = "RO 목록 조회 API(MyBatis)", description = "RO 목록 조회 API 입니다.(MyBatis)")
    @GetMapping("/searchListMybatis")
    public List<RoInfo> searchListMybatis(@RequestParam("asnNo") String asnNo, int page, Model model) {
        return roInfoService.getListMybatis(asnNo, page);
    }

    @Operation(summary = "RO 목록 조회 API(PART COUNT)", description = "RO 목록 조회 API 입니다.(PART COUNT)")
    @GetMapping("/searchListPtCnt")
    public List<RoInfoPtCnt> searchListPtCnt(@RequestParam("asnNo") String asnNo, Model model) {
        return roInfoService.getRoListPtCnt(asnNo);
    }

    @Operation(summary = "RO 저장 API", description = "RO 저장 API 입니다.")
    @PostMapping("/insert")
    public RoCrud insert(@RequestBody RoCrud roCrud, Model model) {
        return roInfoService.insert(roCrud);
    }

    @Operation(summary = "RO 수정 API", description = "RO 수정 API 입니다.")
    @PostMapping("/update")
    public RoCrud update(@RequestBody RoCrud roCrud) {
        return roInfoService.update(roCrud);
    }

    @Operation(summary = "RO 수정 컬럼만 업데이트 API", description = "RO 수정 컬럼만 업데이트 하는 API 입니다.")
    @PostMapping("/updateByColumn")
    public RoCrud updateByColumn(@RequestBody RoCrud roCrud) {
        return roInfoService.update(roCrud);
    }

    @Operation(summary = "RO 삭제 API", description = "RO 삭제 API 입니다.")
    @DeleteMapping("/delete")
    public String delete(@RequestBody RoNoPk roNoPk, Model model) {
        return roInfoService.delete(roNoPk);
    }
}