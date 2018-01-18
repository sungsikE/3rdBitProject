package com.bit.daNaOutlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.daNaOutlet.model.entity.DpgVo;
import com.bit.daNaOutlet.model.entity.HotDealVo;
import com.bit.daNaOutlet.model.entity.MemberVo;
import com.bit.daNaOutlet.service.MemberService;

@Controller
public class ShowController {
	@Autowired
	MemberService memberService;

	// 메인화면
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws Exception {

		memberService.selectAll(model);
		return "main";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) throws Exception {

		memberService.selectAll(model);
		return "search";
	}

	// 템플릿
	@RequestMapping(value = "/template/navigation", method = RequestMethod.GET)
	public String nav() throws Exception {
		return "template/navigation";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {

		return "join";
	}

	// 로그인화면
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login/login";
	}

	// 회원관리
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public String memberList(Model model) throws Exception {

		memberService.selectAll(model);
		return "member/memberView";
	}

	// 회원개인신상조회
	@RequestMapping(value = "/member/{mnum}", method = RequestMethod.GET)
	public String memberOne(@PathVariable("mnum") int mnum, Model model) throws Exception {
		memberService.selectOne(model, mnum);

		return "member/memberOne";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST) // 회원 가입 부분
	public String joinSuccess(@ModelAttribute MemberVo bean) throws Exception {
		memberService.memberAdd(bean);
		return "member/success";
	}

	// 김성식 파일업로드 page view
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadView(HttpServletRequest req) throws Exception {
		return "hotdeal/upload";
	}

	// 파일 업로드 Insert 부분
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@ModelAttribute HotDealVo bean, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) throws Exception {
		String result = memberService.hotDealAdd(bean, file, req); // reulst 리턴값에 따라 success.jsp 또는 failed.jsp 페이지 보여줌
		return "hotdeal/" + result;
	}

	// 업로드한 파일 보여주는 view
	@RequestMapping(value = "/hotdeal", method = RequestMethod.GET)
	public String hotDeal(Model model) throws Exception {
		memberService.hotDealAll(model);
		return "hotdeal/hotdeal";
	}

	// 클립 메인
	@RequestMapping(value = "/clip/", method = RequestMethod.GET)
	public String clipList(Model model) throws Exception {
		memberService.clipList(model);
		return "clip/main";
	}

	@RequestMapping(value = "/dpg/view", method = RequestMethod.GET)
	public String dpgtestView(Model model) throws Exception {
		memberService.dpgMain(model);
		return "dpg/dpgviewtest";
	}

	@RequestMapping(value = "/dpg/test", method = RequestMethod.GET)
	public String dpgUpView() throws Exception {
		return "dpg/dpgtest";
	}

	@RequestMapping(value = "/dpg/test", method = RequestMethod.POST)
	public String dpgUp(@ModelAttribute DpgVo bean, @RequestParam("file") MultipartFile file, HttpServletRequest req)
			throws Exception {
		memberService.dpgAdd(bean, file, req);
		return "dpg/success";
	}

	// 게시판 메인
	@RequestMapping(value = "/dpg", method = RequestMethod.GET)
	public String dpg(Model model) throws Exception {
		memberService.dpgMain(model);
		return "dpg/dpgMain";
	}

	// 게시판(임시로 정해둠)
	@RequestMapping(value = "/dpg/detail", method = RequestMethod.GET)
	public String dpgDetail(Model model) throws Exception {

		memberService.selectAll(model);
		return "dpg/detail";
	}

	// 게시판(임시로 정해둠)
	@RequestMapping(value = "/dpg/selectOne", method = RequestMethod.GET)
	public String dpgSelectOne(Model model) throws Exception {

		memberService.selectAll(model);
		return "dpg/selectOne";
	}

	// 임시 확인 디테일
	@RequestMapping(value = "/con-detail", method = RequestMethod.GET)
	public String conDetail(Model model) throws Exception {

		memberService.selectAll(model);
		return "con-detail";
	}

}