package com.example.demo.DAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.TrafficVO;

@Repository
public class TrafficDAO {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public int cnt(int maxNum) {
		return session.selectOne("traffic.cnt", maxNum);
	}
	
	public int max() {
		return session.selectOne("traffic.max");
	}
	
	public List<TrafficVO> rank() {
		return session.selectList("traffic.rank");		
	}
	
	public List<TrafficVO> time() {
		return session.selectList("traffic.time");
	}
	
	public List<TrafficVO> search(Map<String, String> content) {
		return session.selectList("traffic.search", content);
	}
}
