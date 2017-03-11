package com.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

public class ResultMapUtil {
	public static Map<String,Object> getSuccessMap() {
		return getSuccessMap(null,null);
	}
	public static Map<String,Object> getSuccessMap(String msg) {
		return getSuccessMap(msg,null);
	}
	public static Map<String,Object> getSuccessMap(Object obj) {
		return getSuccessMap(null,obj);
	}
	public static Map<String,Object> getSuccessMap(String msg,Object obj) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("resultCode", 0);
		if(!StringUtils.isEmpty(msg))
			map.put("resultMsg", msg);
		if(obj!=null)
			map.put("data", obj);
		return map;
	}
	public static Map<String,Object> getFailMap() {
		return getFailMap(null);
	}
	public static Map<String,Object> getFailMap(String msg) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("resultCode", -1);
		if(!StringUtils.isEmpty(msg))
			map.put("resultMsg", msg);
		return map;
	}
}
