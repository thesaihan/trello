package com.thesaihan.trello.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  
	@Value("${mm.edu.ytu.erms.jwtSecret}")
	private String jwtSecretKey;
	
	@Value("${mm.edu.ytu.erms.jwtExpirationMs}")
	private int jwtExpirationMs;

}