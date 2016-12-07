package com.agrawroh.selemize.data;

import java.util.ArrayList;
import java.util.List;

import com.agrawroh.selemize.model.SeleniumStep;

/**
 * Selenium Test Data
 * 
 * @author agraw_ds7m
 * @version 2016-12-02
 */
public class SeleniumTestData {

    /**
     * Gather Test Data
     * 
     * @param fileContents
     * @return
     */
    public List<SeleniumStep> getTestData(List<List<?>> fileContents) {
        List<SeleniumStep> seleniumSteps = new ArrayList<>();

        /* Iterate File Content List */
        for (List<?> subList : fileContents) {
            /* Add Step */
            seleniumSteps.add(new SeleniumStep((String) subList.get(0),
                    (String) subList.get(1), (String) subList.get(2)));
        }

        /* Return Steps */
        return seleniumSteps;
    }
}
