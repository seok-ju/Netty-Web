package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	StringRedisTemplate redis;
	
	public int getCount() {
		ValueOperations<String, String>vop = redis.opsForValue();
		
		long key = (System.currentTimeMillis() / 1000) % 10 - 1;
		if(key < 0) key += 10;
				
		if(vop.get(String.valueOf(key)) == null) return 0;
		int count = Integer.valueOf(vop.get(String.valueOf(key)));
		vop.set(String.valueOf(key), "0");
		
		return count;		
	}
}
