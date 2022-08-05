package com.example.project009;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project009.ControllerClass.controller;
import com.example.project009.DAO.RedirectRepo;
import com.example.project009.daoCLass.RedirectRedirection;
import com.example.project009.serviceClass.UrlService;

@SpringBootTest
class Project009ApplicationTests {
	@Autowired
	controller c;

	@Autowired
	RedirectRepo repo;
	
	@Autowired
	UrlService service;
	
	@Test
	void contextLoads() {
	}
	@Test
	void saveUrl() {
		RedirectRedirection r=new RedirectRedirection(1,"google.com","youtubecom");
		c.addPUrl(r);
		assertNotNull(repo.findById(1));
		
	}
	
	@Test
	void saveListUrl() {
		List<RedirectRedirection> l=Stream.of(
		new RedirectRedirection(1,"google.com","youtubecom"),
		new RedirectRedirection(2,"yiutube.com","twitter"),
		new RedirectRedirection(3,"email.com","youtubecom")).collect(Collectors.toList());
		c.addListUrl(l);
		assertNotNull(repo.findAll().size());
		
	}
	
	@Test
	void getListUrl() {
		List<RedirectRedirection> l=Stream.of(
		new RedirectRedirection(1,"google.com","youtubecom"),
		new RedirectRedirection(2,"yiutube.com","twitter"),
		new RedirectRedirection(3,"email.com","youtubecom")).collect(Collectors.toList());
		c.addListUrl(l);
		assertNotNull(repo.findAll().size()==3);
		
	}
	
	
	@Test
	void updateUrl() {
		List<RedirectRedirection> l=Stream.of(
				new RedirectRedirection(1,"google.com","youtubecom"),
				new RedirectRedirection(2,"yiutube.com","twitter"),
				new RedirectRedirection(3,"email.com","youtubecom")).collect(Collectors.toList());
				c.addListUrl(l);
		RedirectRedirection r=repo.findAll().get(2);
		r.setSourceUrl("facebook.com");
		c.alterUrl(r);		
		RedirectRedirection r1=repo.findAll().get(2);
		String d=r1.getSourceUrl();
		assertEquals(d,"facebook.com");
	}
	
	@Test
	void deleteUrl() {
		List<RedirectRedirection> l=Stream.of(
				new RedirectRedirection(1,"google.com","youtubecom"),
				new RedirectRedirection(2,"yiutube.com","twitter"),
				new RedirectRedirection(3,"email.com","youtubecom")).collect(Collectors.toList());
				c.addListUrl(l);
		String f=service.deleteUrlSer(2);
		assertEquals(f,"Deleted");
		
	}
	
	
}
