package com.agrawroh.selemize.printer;

import java.util.List;

/**
 * Printer Interface
 * 
 * @author agraw_ds7m
 * @version 2016-11-18 v1.0
 */
public interface IPrinter {
    /**
     * Pretty Print Data
     * 
     * @param fileContents
     */
    void prettyPrint(List<List<?>> fileContents, CharSequence seperator);
}
