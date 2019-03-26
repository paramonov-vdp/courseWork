package com.vdp.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VolodymyrP on 11/10/2016.
 */
public class TelefParser {
    private String site;
    private String File;
    List<String> cafedras;
    List<String> faculty;


    public TelefParser(String site) {
        this.site = site;
        cafedras = new ArrayList<>();
        faculty = new ArrayList<>();
        fillFaculty();

    }


    public void fillFaculty() {
        faculty.add("ІПСА");
        faculty.add("ІТС");
        faculty.add("ВПІ");
        faculty.add("ІЕЕ");
        faculty.add("ММІ");
        faculty.add("ФТІ");
        faculty.add("ЗФ");
        faculty.add("ІФФ");
        faculty.add("ІХФ");
        faculty.add("ПБФ");
        faculty.add("РТФ");
        faculty.add("ТЕФ");
        faculty.add("ФАКС");
        faculty.add("ФБМІ");
        faculty.add("ФБТ");
        faculty.add("ФЕА");
        faculty.add("ФЕЛ");
        faculty.add("ФЛ");
        faculty.add("ФММ");
        faculty.add("ФСП");
        faculty.add("ФПМ");
        faculty.add("ФМФ");
        faculty.add("ХТФ");
        faculty.add("ФІОТ");
    }

    public TelefParser() {
        fillFaculty();
    }

    public void doParse() {
        try {
            Document doc = Jsoup.connect(site).get();
            String caffedrasArray[] = doc.text().split("Кафедра");

            cafedras = new ArrayList(Arrays.asList(caffedrasArray));
            cafedras.remove(0);

            String toChange = cafedras.get(cafedras.size() - 1);

            if (toChange.contains("Copyright")) {

                String[] toSplit = toChange.split("Copyright");
                cafedras.add(toSplit[0]);

            }

            for (String s : cafedras) {
             s = "Кафедра " + s;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setSite(String site) {
        this.site = site;
    }

    public List<String> getCafedras() {
        return cafedras;
    }

    public List<String> getFaculty() {
        return faculty;
    }
}
