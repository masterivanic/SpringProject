package com.myapp.myapp.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/notAutorized")
    public String error(){
        return "notAuthorized";
    }
    
}