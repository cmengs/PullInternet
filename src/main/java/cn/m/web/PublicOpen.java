package cn.m.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class PublicOpen {

	@RequestMapping("/index")
    public String index(){
    	return "index接口";
    }
	
	@RequestMapping("/error")
	public String eeror(){
		return "error";
	}
}
