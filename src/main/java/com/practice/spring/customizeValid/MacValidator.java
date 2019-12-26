package com.practice.spring.customizeValid;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Higmin
 * @date 2019/12/25 15:38
 *
 * 自定义注解 校验类 => 检验 mac 地址（格式：080027004C44）
 **/
public class MacValidator implements ConstraintValidator<Mac, String> {

	@Override
	public boolean isValid(String mac, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(mac)) {
			return false;
		}
		return mac.matches("^(([0-9a-fA-F]{2}){6})$");
	}
}
