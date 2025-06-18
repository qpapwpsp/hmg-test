package com.hmg.as.test.hmg_test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmg.as.test.hmg_test.entity.RoInfo;
import com.hmg.as.test.hmg_test.service.RoInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/roInfo")
@Tag(name = "RO 정보 API", description = "RO 정보 API")
public class RoNoController {

	private final RoInfoService roInfoService;

	public RoNoController(RoInfoService roNoService) {
		this.roInfoService = roNoService;
	}

	@Operation(summary = "RO 정보 조회 API", description = "RO 정보 조회 API 입니다.")
	@GetMapping("/search")
	public RoInfo searchByRoNo(@RequestParam("asnNo") String asnNo, @RequestParam("roNo") String roNo, Model model) {
		RoInfo roInfo = roInfoService.getByRoInfo(asnNo, roNo);
		return roInfo;
	}

	@Operation(summary = "RO 리스트 조회 API", description = "RO 정보 리스트 API 입니다.")
	@GetMapping("/searchList")
	public List<RoInfo> searchList(@RequestParam("asnNo") String asnNo, int page, Model model) {
		List<RoInfo> roList = roInfoService.getList(asnNo, page);
		return roList;
	}
}