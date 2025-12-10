package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {

    @RequestMapping(value = {"/", "/dashboard", "/projects", "/projects/**", "/tasks", "/tasks/**"})
    public String forward() {
        return "forward:/index.html";
    }
}