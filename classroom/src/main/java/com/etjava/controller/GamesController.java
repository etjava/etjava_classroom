package com.etjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GamesController {

    @RequestMapping("/games")
    public ModelAndView games(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("games/game");
        return mav;
    }

    @RequestMapping("/games/pvz")
    public ModelAndView pvz(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pvz/index");
        return mav;
    }



}
