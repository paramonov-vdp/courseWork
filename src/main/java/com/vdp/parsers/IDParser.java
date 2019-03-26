package com.vdp.parsers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;


public class IDParser {

    TelefParser telefParser;
    private Map<String, String> stuffAndID;
    List<String> cafedras;

    public IDParser(TelefParser telefParser) {
        this.telefParser = telefParser;
        stuffAndID = new HashMap<>();
        this.cafedras = telefParser.getCafedras();
    }

    public void doParse() throws IOException {
        for (String cafedra : cafedras) {
            cafedra = "Кафедра" + cafedra;
            Connection.Response res2 = Jsoup.connect("http://telef.kpi.ua/cafedra_table.php")
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .data("cafedra_name", cafedra)
                    .method(Connection.Method.POST)
                    .execute();

            Document document = res2.parse();

            Elements table = document.select("table");
            String name;
            String link;
            for (int i = 0; i < table.select("td").size(); i += 4) {
                name = table.select("td").get(i).text();
                link = table.select("td").get(i).select("a").attr("href");
                stuffAndID.put(name, idParser(link));
            }


        }
    }

    public String idParser(String link){
        int start = link.indexOf('=');
        int end = link.indexOf('&');
        String pureID = link.substring(start+1, end);

        return pureID;
        }


    public Map<String, String> getStuffAndID() {
        return stuffAndID;
    }

    public void printStuffAndID() {
        for (Map.Entry<String, String> stringStringEntry : stuffAndID.entrySet()) {
            System.out.println(stringStringEntry.getKey()+ " id "+ stringStringEntry.getValue());
        }
        System.out.println("----------------");
        System.out.println(stuffAndID.size());



    }
}
