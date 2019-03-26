package com.vdp.container;

import java.util.List;
import java.util.Objects;

/**
 * Created by VolodymyrP on 11/15/2016.
 */
public class ResultContainer {
    private List<Results> resultsList;


    public List<Results> getResultsList() {
        return resultsList;
    }


    public void setResultsList(List<Results> resultsList) {
        this.resultsList = resultsList;
    }


    public void add(Results results){
        resultsList.add(results);
    }
}
