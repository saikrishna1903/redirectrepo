package com.example.project009.daoCLass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
public @Data class RedirectRedirection {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String SourceUrl;
	private String TargetUrl;
}