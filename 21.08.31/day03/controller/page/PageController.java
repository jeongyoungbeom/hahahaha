package com.koreait.day03.controller.page;

import com.koreait.day03.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages") // http://127.0.0.1:9090/pages
public class PageController {
    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path={""})
    public ModelAndView index(){
        return new ModelAndView("/pages/index")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    @RequestMapping(path={"/user"})
    public ModelAndView user(){
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user");
    }

    @RequestMapping(path = {"/category"})
    public ModelAndView category(){
        return new ModelAndView("/pages/category")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "category");
    }

    @RequestMapping(path = {"/item"})
    public ModelAndView item(){
        return new ModelAndView("/pages/item")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "item");
    }

    @RequestMapping(path = {"/ordergroup"})
    public ModelAndView ordergroup(){
        return new ModelAndView("/pages/ordergroup")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "ordergroup");
    }

    @RequestMapping(path = {"/adminuser"})
    public ModelAndView adminuser(){
        return new ModelAndView("/pages/adminuser")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "adminuser");
    }

    @RequestMapping(path = {"/partner"})
    public ModelAndView partner(){
        return new ModelAndView("/pages/partner")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "partner");
    }

}
