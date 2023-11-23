package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.MediaSize;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository("MemberRepositoryImpl")
public class MemberRepositoryImpl implements MemberRepository {
    SqlSession session;
    @Autowired
    public MemberRepositoryImpl(SqlSession session) {
        this.session = session;
    }
    final String NAMESPACE = "com.ssafy.enjoytrip.repository.MemberRepository.";
    @Override
    public MemberDto login(MemberDto memberDto) throws Exception {
        System.out.println("repo >>" + memberDto);
        return session.selectOne(NAMESPACE+"login", memberDto);
    }

    @Override
    public MemberDto userInfo(String userId) throws Exception {
        return session.selectOne(NAMESPACE+"userInfo", userId);
    }

    @Override
    public void saveRefreshToken(Map<String, String> map)  throws SQLException {
        session.update(NAMESPACE+"saveRefreshToken", map);
    }

    @Override
    public int idCheck(String userId) throws Exception {
        return session.selectOne(NAMESPACE+"idCheck", userId);
    }

    @Override
    public void signup(MemberDto memberDto) throws Exception {
        session.insert(NAMESPACE+"signup", memberDto);
    }

    @Override
    public void memberModify(MemberDto memberDto) throws Exception {
        session.update(NAMESPACE+"memberModify", memberDto);
    }

    @Override
    public void memberDelete(String userId) throws Exception {
        session.delete(NAMESPACE+"memberDelete", userId);
    }

	@Override
	public void deleteRefreshToken(Map<String, String> map) throws Exception {
		session.update(NAMESPACE + "deleteRefreshToken", map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return session.selectOne(NAMESPACE + "getRefreshToken", userid);
	}
}
