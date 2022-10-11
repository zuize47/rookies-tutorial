package com.nashtech.rookies.dto;

import org.springframework.beans.factory.annotation.Value;

public interface BookDetail {
	
	String getName();
	String getCode();
	
	@Value("#{target.author.name}")
	String getAuthorName();

}
