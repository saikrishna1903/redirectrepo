package com.example.project009.ControllerClass;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import com.example.project009.ExceptionClass.ControllerException;
import com.example.project009.ExceptionClass.ServiceException;
import com.example.project009.Util.JwtUtil;
import com.example.project009.daoCLass.AuthRequest;
import com.example.project009.daoCLass.RedirectRedirection;
import com.example.project009.serviceClass.UrlService;
@RestController
public class controller {
	@Autowired
	UrlService service;
	
	 @Autowired
	 private JwtUtil jwtUtil;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;

	
	Logger logger =  LoggerFactory.getLogger(controller.class);
	
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return jwtUtil.generateToken(authRequest.getUserName());
	    }
	 
	//To save a single object
	@PostMapping(value="/link")
	public ResponseEntity addPUrl(@RequestBody RedirectRedirection url) {
		try {
			service.AddUrlSer(url);
			logger.info("Data saved successfully");
			return new ResponseEntity<String>(service.AddUrlSer(url),HttpStatus.CREATED);
		}catch(ServiceException e) {
			logger.error("Exception occured while saving");
			ControllerException c=new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//To save a List of objects
	@PostMapping(value="/linkL")
	public ResponseEntity addListUrl(@RequestBody List<RedirectRedirection> url) throws ServiceException{
		try {
			service.AddListUrlSer(url);
			logger.info("Data saved successfully");
			return new ResponseEntity<String>(service.AddListUrlSer(url),HttpStatus.CREATED);

		}catch(ServiceException e) {
			logger.error("Exception occured while saving");
			ControllerException c=new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//To get Target Urls from sourceurls
	@GetMapping(value="/linka")
	public ResponseEntity RedirectedUrl(@RequestBody RedirectRedirection SourceUrl) {
		try {
			service.RedirectUrlSer(SourceUrl);
			logger.info("Retrived all TargetUrls Successfully");
			return new ResponseEntity<>(service.RedirectUrlSer(SourceUrl),HttpStatus.OK);

		}catch(ServiceException e) {
			logger.error("Exception occured while Retriving");
			ControllerException c=new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	//TO get all the Objects
	@GetMapping(value="/link")
	public ResponseEntity getUrl() {
		try {
			service.getUrlSer();
			logger.info("All the Values are Retrived");
			return new ResponseEntity<>(service.getUrlSer(),HttpStatus.OK);
		}catch(ServiceException e) {
			logger.error("Error occured while Retriving");
			ControllerException c=new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
		
	}

	//To get all the target Urls
	@GetMapping(value="/links")
	public ResponseEntity getTargetUrl() {

		//return new ResponseEntity<>(service.getTagetUrlSer(),HttpStatus.OK);
		return new ResponseEntity<>(service.getTagetUrlStreamSer(),HttpStatus.OK);

	}
	
	
	//TO update a single Object
	@PutMapping(value="/link")
	public ResponseEntity alterUrl(@RequestBody RedirectRedirection url) {
		return new ResponseEntity<String>(service.alterUrlSer(url),HttpStatus.OK);
	}
	
	
	//To delete a object based on ID
	@DeleteMapping(value="/link/{id}")
	public ResponseEntity deleteUrl(@PathVariable("id") int id) throws ServiceException{
		try {
			String g=service.deleteUrlSer(id);
			logger.info("Deleted link of Id: "+id);
			return new ResponseEntity<String>(g,HttpStatus.OK);
		}catch(ServiceException e) {
			logger.error("Exception occured while Deleting");
			ControllerException c=new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}

	}

}
