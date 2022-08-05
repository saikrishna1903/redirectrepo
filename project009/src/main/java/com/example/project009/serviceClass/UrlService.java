package com.example.project009.serviceClass;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.project009.DAO.RedirectRepo;
import com.example.project009.ExceptionClass.ServiceException;
import com.example.project009.daoCLass.RedirectRedirection;
@Service
public class UrlService {

	@Autowired
	RedirectRepo repo;

	public String AddUrlSer(RedirectRedirection url) {
		try {
			if(url.getSourceUrl().isEmpty() || url.getTargetUrl().isEmpty()) {
				throw new ServiceException("701","Please enter correct values!");
			}
			repo.save(url);
		}catch(IllegalArgumentException e) {
			throw new ServiceException("702","Given values are NULL, Please enter correct values");
		}catch(Exception e) {
			throw new ServiceException("703","Please enter correct values");

		}

		return "Saved";

	}
	public String AddListUrlSer(List<RedirectRedirection> url) {
		try {
			if(url.size()==0) {
				throw new ServiceException("711","Entered List is empty!");

			}else{

				try {

					for(int i=0;i<url.size();i++) {
						if(url.get(i).getSourceUrl().isEmpty() || url.get(i).getTargetUrl().isEmpty()) {
							throw new ServiceException("701","Please enter correct values!");
						}
					}
				}catch(Exception e) {
					throw new ServiceException("703","Please enter correct values");

				}
			}

			repo.saveAll(url);

		}catch(ServiceException e) {
			throw new ServiceException(e.getErrorCode(),e.getErrorMessage());

		}catch(Exception e) {
			throw new ServiceException("703",e.getMessage());

		}
		return "Saved";
	}

	public String alterUrlSer(RedirectRedirection url) {
		repo.save(url);
		return "Updated";
	}

	public String deleteUrlSer(int id){
		try {
			repo.deleteById(id);
			return "Deleted";
		}catch(Exception e) {
			throw new ServiceException("721","Entry with id is not available");

		}
	}

	public List<RedirectRedirection> getUrlSer() {
		try {
			if( repo.findAll().size()==0) {
				throw new ServiceException("722","List is empty, Please enter any values First");

			}
		}catch(ServiceException e) {
			throw new ServiceException(e.getErrorCode(),e.getErrorMessage());

		}catch(Exception e) {
			throw new ServiceException("722",e.getMessage());

		}
		return (List<RedirectRedirection>) repo.findAll();
	}

	public Object getTagetUrlSer() {
		return repo.findAllTargetURLS();	}

	public List<String> getTagetUrlStreamSer() {
		return 	repo.findAll().stream().map(i->i.getTargetUrl()).collect(Collectors.toList());

	}


	
	public List<String> RedirectUrlSer(RedirectRedirection sourceUrl) {
		try {
			if(repo.findAllTargetURLS(sourceUrl.getSourceUrl()).size()==0) {
				throw new ServiceException("722","List is empty, Please enter any values First");
			}else if(repo.findAllTargetURLS(sourceUrl.getSourceUrl()).size()==0) {
				return repo.findAllTargetURLS(sourceUrl.getSourceUrl());
			}else {
				return repo.findTargetURLS();
			}
		
	}catch(ServiceException e) {
		throw new ServiceException("722","List is empty, Please enter any values First");
	}catch(Exception e) {
		throw new ServiceException("724",e.getMessage());
	}

	}


}
