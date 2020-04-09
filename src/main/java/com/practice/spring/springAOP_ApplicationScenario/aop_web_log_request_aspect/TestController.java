package com.practice.spring.springAOP_ApplicationScenario.aop_web_log_request_aspect;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Higmin
 * @date 2020/4/9 14:19
 **/
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
	@PostMapping("/user/login")
	@WebLog(description = "请求用户登录接口")
	public User userLogin(@RequestBody User user){
		log.info("user login ...");
		return user;
	}
}

@Data
class User{
	private String username;
	private String password;
}
