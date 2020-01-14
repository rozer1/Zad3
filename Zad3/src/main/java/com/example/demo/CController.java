package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calendar")
public class CController {

    @GetMapping
    public boolean getCalendarForYearAndMonth(@RequestParam(name = "year") String year,
                                              @RequestParam(name = "month") String month){
        return true;
    }
}
