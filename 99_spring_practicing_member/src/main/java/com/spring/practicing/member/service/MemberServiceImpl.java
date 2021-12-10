package com.spring.practicing.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.practicing.member.dao.MemberDao;
import com.spring.practicing.member.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		memberDto.setMemberPw(passwordEncoder.encode(memberDto.getMemberPw())); 
		memberDao.insert(memberDto);
	}
	
	@Override
	public MemberDto loginMember(MemberDto memberDto) throws Exception {
		
		MemberDto dbMemberDto = memberDao.login(memberDto);
		
		if (dbMemberDto != null) {
			if (passwordEncoder.matches(memberDto.getMemberPw(), dbMemberDto.getMemberPw())) { // passwordEncoder 검색할 것
				return memberDto;
			} 
		} 
		return null; // 아이디가 없으면 null값을 내보낸다
	}
	
	@Override
	public String overlapped(String memberId) throws Exception {
		String isOverLapped = "true";
		if (memberDao.overlapped(memberId) == null) {
			return "false";  // String 값을 내보냄
		}
		return isOverLapped;
	}
	
	@Override
	public List<MemberDto> showAllMember() throws Exception {
		return memberDao.selectAllMember();
	}
	

	
	@Override
	public MemberDto showOneMember(String memberId) throws Exception {
		return memberDao.selectOneMember(memberId);
	}

	@Override
	public boolean updateMember(MemberDto memberDto) throws Exception {
		
		MemberDto dbMemberDto = memberDao.login(memberDto);
		
		if (passwordEncoder.matches(memberDto.getMemberPw(), dbMemberDto.getMemberPw())) {
			memberDao.updateMember(memberDto);
			return true;
		}
		
		return false;
		
	}
	
	
	@Override
	public boolean deleteMember(MemberDto memberDto) throws Exception {
		
		MemberDto dbMemberDto = memberDao.login(memberDto);
		
		if (passwordEncoder.matches(memberDto.getMemberPw(), dbMemberDto.getMemberPw())) {
			memberDao.deleteMember(memberDto.getMemberId());
			return true; // dao와 service의 반환형은 같은 기능을 하더라도 다른 메서드이기 때문에 연관이 없다
		}
		
		return false;
		
	}


	
}
