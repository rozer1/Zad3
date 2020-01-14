package com.example.demo;

import net.fortuna.ical4j.model.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/calendar")
public class CController {

    @GetMapping
    public boolean getCalendarForYearAndMonth(@RequestParam(name = "year") String year,
                                              @RequestParam(name = "month") String month){
        return true;
    }

    @GetMapping("/now")
    @ResponseBody
    public Boolean getCalendarNow() {
        Website website = new Website("http://www.weeia.p.lodz.pl", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR), java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));
        Calendar calendar = CalCalendar.getCalendar(website.getEvents(), website.getMonth(), website.getYear());
        try {
            CalCalendar.printCalendar(calendar);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
