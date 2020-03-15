package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.service.FbService;



@Controller
@RequestMapping("/")
public class FacebookController {

	@Autowired
    private FbService facebookService;

    @GetMapping("/createFacebookAuthorization")
    public RedirectView createFacebookAuthorization(){
        return new RedirectView(facebookService.createFacebookAuthorizationURL());
    }
    
    @GetMapping("/facebook")
    public String createFacebookAccessToken(@RequestParam("code") String code){
        String accToken=facebookService.createFacebookAccessToken(code);
        return "details";
    }
    
    @RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
    
}

