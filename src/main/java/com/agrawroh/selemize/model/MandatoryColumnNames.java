package com.agrawroh.selemize.model;

/**
 * Mandatory Column Names
 * 
 * @author agraw_ds7m
 * @version 2016-11-20 v1.0
 */
public enum MandatoryColumnNames {
    SEL_COMMAND(0), SEL_VALUE(1), SEL_TARGET(2);

    /* Column Index */
    private int index;

    /* Private Constructor */
    private MandatoryColumnNames(int index) {
        /* Set Index */
        this.index = index;
    }

    /**
     * Get Index Location
     * 
     * @return index
     */
    public int getIndex() {
        /* Return Index */
        return index;
    }
}
