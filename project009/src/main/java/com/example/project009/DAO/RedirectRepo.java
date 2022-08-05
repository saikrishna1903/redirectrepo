package com.example.project009.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project009.daoCLass.RedirectRedirection;

public interface RedirectRepo extends JpaRepository<RedirectRedirection,Integer>{

	@Query("SELECT i.TargetUrl from RedirectRedirection i where i.id=1")
	List<String> findTargetURLS();
	
	
	@Query("SELECT i.TargetUrl from RedirectRedirection i")
	List<String> findAllTargetURLS();
	
	@Query("SELECT i.TargetUrl from RedirectRedirection i where i.SourceUrl= :SourceUrl ")
	List<String> findAllTargetURLS(@Param("SourceUrl") String SourceUrl);
	
	
}
