package com.vdp.parsers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.*;

/**
 * Created by VolodymyrP on 11/10/2016.
 */
public class FacultyAndCafedrasParser {

    private Map<String, List<String>> parsedCafedras;
    private Map<String, String > parsedfaculty;

    public FacultyAndCafedrasParser() {
        parsedCafedras = new HashMap<>();
        parsedfaculty = new HashMap<>();
    }

    public Map parseCafedra(String cafedra) {
        try {
            List<String> stuff = new ArrayList<>();
            Connection.Response res2 = Jsoup.connect("http://telef.kpi.ua/cafedra_table.php")
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .data("cafedra_name", "Кафедра інженерії поверхні ЗФ")
                    .method(Connection.Method.POST)
                    .execute();

            System.out.println(res2.body());
            Document document = res2.parse();

            Elements table = document.select("table");


            Iterator<Element> ite = table.select("td").iterator();

            for (int i = 0; i < document.select("td").size(); i++)

                stuff.add(ite.next().text());


            System.out.println(stuff);
            System.out.println(stuff.size());
            parsedCafedras.put(cafedra, stuff);

        }catch (Exception e){
            e.printStackTrace();
        }
         return parsedCafedras;
    }




    public void parsefaculty(String faculty) {

    }



    public void raitingSorting(List<String> prepods){
        Integer[] toSorted;

    }





    }

