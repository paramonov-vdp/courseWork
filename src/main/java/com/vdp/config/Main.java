package com.vdp.config;

import com.vdp.parsers.FacultyAndCafedrasParser;
import com.vdp.parsers.IDParser;
import com.vdp.parsers.TelefParser;
import com.vdp.parsers.XLParser;

import java.io.IOException;

/**
 * Created by VolodymyrP on 11/10/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        TelefParser telefParser = new TelefParser("http://telef.kpi.ua/cafedra.php");
        telefParser.doParse();

        FacultyAndCafedrasParser facultyAndCafedrasParser = new FacultyAndCafedrasParser();
        facultyAndCafedrasParser.parseCafedra(telefParser.getCafedras().get(0));

        IDParser idParser = new IDParser(telefParser);

        idParser.doParse();
        idParser.printStuffAndID();

        XLParser xlParser = new XLParser();
        xlParser.setIDMap(idParser.getStuffAndID());
        xlParser.doParse();

        xlParser.idMatch();
        xlParser.printWrongIDs();

    }
}
