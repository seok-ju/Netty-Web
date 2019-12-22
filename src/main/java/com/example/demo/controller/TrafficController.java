package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.TrafficDAO;
import com.example.demo.service.RedisService;
import com.example.demo.vo.TrafficVO;

@Controller
public class TrafficController {
	
	@Autowired
	private TrafficDAO traffic;
	@Autowired
	private RedisService redis;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String enter() {
		return "main";
	}

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public @ResponseBody Object content(String startDay, String endDay, String startTime, String endTime) {
		
		String start = startDay + " " + startTime;
		String end = endDay + " " + endTime + ":59";
		
		Map<String, String> content = new HashMap<String, String>();
		content.put("start", start);
		content.put("end", end);
		
		return traffic.search(content);
	}

	@RequestMapping(value = "/graph", method = RequestMethod.GET)
	public @ResponseBody Object graph() {

		return redis.getCount();
	}
	
	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public @ResponseBody int session(HttpSession session) {
		if(session.getAttribute("maxNum") == null) return 0;
		int maxNum = (int) session.getAttribute("maxNum");
		
		return maxNum;
	}
	
	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public @ResponseBody Object rank() {
		
		return traffic.rank();
	}
	
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public @ResponseBody Object time() {
		
		int[] timeCnt = {0, 0, 0, 0, 0, 0, 0, 0};
		
		List<TrafficVO> time = traffic.time();
		
		for(int i=0; i<time.size(); i++) {
			switch(time.get(i).getNum()) {
			case 0:case 1:case 2:
				timeCnt[0] += time.get(i).getContent();
				break;
			case 3:case 4:case 5:
				timeCnt[1] += time.get(i).getContent();
				break;
			case 6:case 7:case 8:
				timeCnt[2] += time.get(i).getContent();
				break;
			case 9:case 10:case 11:
				timeCnt[3] += time.get(i).getContent();
				break;
			case 12:case 13:case 14:
				timeCnt[4] += time.get(i).getContent();
				break;
			case 15:case 16:case 17:
				timeCnt[5] += time.get(i).getContent();
				break;
			case 18:case 19:case 20:
				timeCnt[6] += time.get(i).getContent();
				break;
			case 21:case 22:case 23:
				timeCnt[7] += time.get(i).getContent();
				break;
			}
		}
		return timeCnt;
	}
}
