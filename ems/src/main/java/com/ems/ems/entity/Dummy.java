package com.ems.ems.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;


@JacksonXmlRootElement
@Data
public class Dummy {
	
	@JacksonXmlAnyAttribute
	private Map<String,String> attributes = new HashMap<>();
	
	@JacksonXmlAnyElement
	List<Object> elements;
	

}
