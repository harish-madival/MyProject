package com.hm.food.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FoodValidator {

	public static boolean isNotBlankNotNull(String key) {
		return StringUtils.isNotBlank(key);
		
	}
}
