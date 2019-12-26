package com.practice.spring.exception;

import com.practice.spring.customizeValid.Mac;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Higmin
 * @date 2019/12/26 9:33
 **/
@RequestMapping(value = "/test")
public class TestController {

	@ApiOperation(value = "测试 - 故意造成一个空指针异常，并且不进行处理", notes = "测试 - 故意造成一个空指针异常，并且不进行处理", httpMethod = "GET")
	@ApiImplicitParam(name = "mac", value = "设备mac地址", paramType = "query", required = false, dataType = "String", defaultValue = "080027004C44")
	@RequestMapping(value = "/test_null", method = RequestMethod.GET)
	public Boolean test_null(String mac){
		mac = null; // 故意造成一个空指针异常，并且不进行处理
		return mac.equals(111);
	}

	@ApiOperation(value = "测试 - 故意造成一个mac地址检验异常，并且不进行处理", notes = "测试 - 故意造成一个mac地址检验异常，并且不进行处理", httpMethod = "GET")
	@ApiImplicitParam(name = "mac", value = "设备mac地址", paramType = "query", required = false, dataType = "String", defaultValue = "080027004C44")
	@RequestMapping(value = "/test_mac", method = RequestMethod.GET)
	public String test_mac(@Mac String mac){
		return mac;
	}

	@ApiOperation(value = "测试 - 故意造成一个异常(Exception)，并且不进行处理", notes = "测试 - 故意造成一个异常(Exception)，并且不进行处理", httpMethod = "GET")
	@ApiImplicitParam(name = "mac", value = "设备mac地址", paramType = "query", required = false, dataType = "String", defaultValue = "080027004C44")
	@RequestMapping(value = "/test_exception", method = RequestMethod.GET)
	public String test_exception(@Mac String mac){
		mac = "abc123";
		Integer.parseInt(mac); // 故意造成一个异常(Exception)，并且不进行处理
		return mac;
	}
}
