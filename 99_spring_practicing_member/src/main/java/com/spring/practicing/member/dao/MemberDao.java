package com.spring.practicing.member.dao;

import java.util.List;

import com.spring.practicing.member.dto.MemberDto;

public interface MemberDao {
	
	public void insert(MemberDto memberDto) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto overlapped(String memberId) throws Exception;
	public List<MemberDto> selectAllMember() throws Exception;
	public MemberDto selectOneMember(String memberId) throws Exception;
	public void updateMember(MemberDto memberDto) throws Exception;
	public void deleteMember(String memberId) throws Exception;
}
