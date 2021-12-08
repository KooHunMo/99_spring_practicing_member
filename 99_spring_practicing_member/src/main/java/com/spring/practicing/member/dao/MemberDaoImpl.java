package com.spring.practicing.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.practicing.member.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(MemberDto memberDto) throws Exception {
		sqlSession.insert("practicingMember.joinMember", memberDto);
	}

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		
		return sqlSession.selectOne("practicingMember.loginMember", memberDto);
	}

	@Override
	public MemberDto overlapped(String memberId) throws Exception {
		
		return sqlSession.selectOne("practicingMember.duplicatedMemberCheck", memberId);
	}

	@Override
	public List<MemberDto> selectAllMember() throws Exception {
		
		return sqlSession.selectList("practicingMember.showAllMember");
	}

	@Override
	public MemberDto selectOneMember(String memberId) throws Exception {
		
		return sqlSession.selectOne("practicingMember.showOneMember", memberId);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		sqlSession.update("practicingMember.updateMember", 	memberDto);
	}

	@Override
	public void deleteMember(String memberId) throws Exception {
		sqlSession.delete("practicingMember.deleteMember", 	memberId);
		
	}

}
