package com.example.demo;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.RandomUidGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class CalCalendar {
    private static net.fortuna.ical4j.model.Calendar Cal = new net.fortuna.ical4j.model.Calendar();
    private static RandomUidGenerator randomUidGenerator = new RandomUidGenerator();
    private static VEvent event;
    private static java.util.Calendar calendar;
    private static java.util.Date date;
    public static net.fortuna.ical4j.model.Calendar getCalendar(HashMap<Integer, String> events, int month, int day) {

        try {
            calendar = java.util.Calendar.getInstance();
            Cal.getProperties().add(CalScale.GREGORIAN);
            Cal.getProperties().add(new ProdId("Kalendarz"));
            Cal.getProperties().add(Version.VERSION_2_0);

            date = new SimpleDateFormat("yyyyMM")
                    .parse("2019" + month);

            calendar.setTime(date);

            for (Integer i : events.keySet()) {
                calendar.set(java.util.Calendar.DAY_OF_MONTH, i+1);

                if (events.get(i) != "") {
                    event = new VEvent(new Date(calendar.getTime()), events.get(i));
                    event.getProperties().add(randomUidGenerator.generateUid());
                    Cal.getComponents().add(event);
                }

            }
            return Cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printCalendar(net.fortuna.ical4j.model.Calendar calendar) throws IOException {

        FileOutputStream fout = new FileOutputStream("Calendar.ics");
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(Cal, fout);
    }
}
