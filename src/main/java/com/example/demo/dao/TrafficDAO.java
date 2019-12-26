package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.RankingVO;
import com.example.demo.vo.TimehVO;
import com.example.demo.vo.TrafficVO;

@Repository
public class TrafficDAO {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public List<TrafficVO> search(Map<String, String> content) {
		return session.selectList("search", content);
	}
	
	public List<RankingVO> rank() {
		return session.selectList("rank");		
	}
	
	public void rankUpdate() {
		session.update("updateRank");
	}
	
	public List<TimehVO> time() {
		return session.selectList("time");
	}
	
	public void timeReset() {
		session.update("resetTime");
	}
	
	public void timeUpdate() {
		session.update("updateTime");
	}
	
}
