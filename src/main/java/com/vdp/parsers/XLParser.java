package com.vdp.parsers;

import jdk.nashorn.internal.runtime.ECMAErrors;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XLParser {
    private String file = "C:/Users/VolodymyrP/Desktop/newDiplom/nauk.xls";
    private Map<String, String> IDMap = new HashMap();
    private Map<String, String> parsedFromXl;
    private Map<String, String> wrongId;


    public XLParser() {
        parsedFromXl = new HashMap<>();
        wrongId = new HashMap<>();
    }

    public void doParse() {
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream(this.file);
            wb = new HSSFWorkbook(in);

            Sheet sheet = wb.getSheetAt(0);

            Iterator<Row> it = sheet.iterator();
            it.next();
            it.next();
            while (it.hasNext()) {

                Row row = it.next();
                Iterator<Cell> cells = row.iterator();
                String name = "";
                String id = "";
                cells.next();
                while (cells.hasNext()) {

                    Cell cell = cells.next();

                    name = cell.getStringCellValue();
                    cells.next();
                    cells.next();
                    cells.next();
                    cells.next();
                    cells.next();

                    Cell idCell = cells.next();
                    id = idCell.getStringCellValue();

                    parsedFromXl.put(name, id);
                    String correctID = IDMap.get(name);
                    if (correctID == "" || correctID == null) {
                        break;
                    }
                    idCell.setCellValue(correctID);
                    break;
                }
                in.close();
                FileOutputStream output_file = new FileOutputStream(file);
                wb.write(output_file);
                output_file.close();

            }

        } catch (Exception e) {

        }
    }


    public void setIDMap(Map IDMap) {
        this.IDMap = IDMap;
    }

    public void idMatch() {
        for (String s : parsedFromXl.keySet()) {
            if (!parsedFromXl.get(s).equals(IDMap.get(s))) {
                wrongId.put(s, parsedFromXl.get(s));
            }

        }
    }

    public void printWrongIDs() {
        /*for (Map.Entry<String, String> stringStringEntry : wrongId.entrySet()) {
            System.out.println("mudak is: " + stringStringEntry.getKey() + " id " + stringStringEntry.getValue());
        }*/

    }
}
