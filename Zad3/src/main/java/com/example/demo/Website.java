package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Website {
    private static HashMap<Integer, String> events;
    private URL site;
    private BufferedReader in;
    private int counter = 0;
    private int month, year;

    public Website(String url, int year, int month) {
        try {
            this.month = month;
            this.year = year;
            site = new URL(url + "/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month + "&lang=1");
            in = new BufferedReader(
                    new InputStreamReader(site.openStream(), "UTF-8"));
            events = new HashMap<>();
            readEvents();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readEvents() throws IOException {
        String inputLine, splitedTD[];
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("<table id='kalendarz' class='kalendarz'>")) {

                while (!inputLine.contains("</table>")) {
                    splitedTD = inputLine.split("</td>");
                    for (String s : splitedTD) {
                        if (s.contains("href=\"javascript:void();\"")) {
                            counter++;
                            if (s.contains("<p>")) {
                                events.put(counter, s.split("<p>")[1].split("</p>")[0]);
                            } else {
                                events.put(counter, "");
                            }
                        }
                    }
                    inputLine = in.readLine();
                }
            }
        }
    }

    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public HashMap<Integer, String> getEvents() {
        return events;
    }
}