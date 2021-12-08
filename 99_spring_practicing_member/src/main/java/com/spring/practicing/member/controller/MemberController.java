package com.spring.practicing.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.practicing.member.dao.MemberDao;
import com.spring.practicing.member.dto.MemberDto;
import com.spring.practicing.member.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		return "member/main";
	}

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "member/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ResponseEntity<Object> join(MemberDto memberDto, HttpServletRequest request) throws Exception {
		if(memberDto.getEmailstsYn() == null) memberDto.setEmailstsYn("n");
		else 								  memberDto.setEmailstsYn("y");
		
		if(memberDto.getSmsstsYn() == null)	  memberDto.setSmsstsYn("n");
		else								  memberDto.setSmsstsYn("y");
		
		memberService.joinMember(memberDto);
		
		String message  = "<script>";
			   message += "alert('회원가입이 완료되었습니다');";
			   message += "location.href='" + request.getContextPath()+ "/member.main'";
			   message += "</script>";
			   
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/overlapped.do", method=RequestMethod.POST) 
	public ResponseEntity<Object> overlapped(@RequestParam("id") String id) throws Exception {
		return new ResponseEntity<Object>(memberService.overlapped(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() throws Exception {
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(MemberDto memberDto, HttpServletRequest request) throws Exception {
		
		String message ="";
		if(memberService.loginMember(memberDto) != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", memberDto.getMemberId());
			message += "<script>";
			message += "alert('로그인이 완료 되었습니다');";
			message += "location.href='"+request.getContextPath() +"/member/main'";
			message += "<script/>";
		}else {
			message += "<script>";
			message += "alert('아이디와 비밀번호를 다시 확인해주세요');";
			message += "history.back(-1);";
			message += "</script>";
		}

		return message;
	}
	
	@RequestMapping(value="/memberList", method=RequestMethod.GET)
	public String memberList(Model model) throws Exception {
		model.addAttribute("memberList", memberService.showAllMember());
		return "member/memberList";
		
	}
	
	@RequestMapping(value="/memberList", method=RequestMethod.GET)
	public String mypage(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		model.addAttribute("memberDto", memberService.showOneMember((String)session.getAttribute("loginUser")));
		return "member/mypage";
	}
}
