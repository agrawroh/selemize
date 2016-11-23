package com.agrawroh.selemize.parser;

import java.io.File;
import java.util.List;

/**
 * Parser Interface
 * 
 * @author agraw_ds7m
 * @version 2016-11-18 v1.0
 */
public interface IParser {
    /**
     * Parse File
     * 
     * @param filePath
     * @param fileName
     * @param sheetName
     * @return isSuccess
     * @throws Exception
     */
    List<List<?>> parse(String filePath, String fileName, String sheetName)
            throws Exception;

    /**
     * Parse File
     * 
     * @param filePath
     * @param fileName
     * @param sheetName
     * @return isSuccess
     * @throws Exception
     */
    List<List<?>> parse(File completeFilePath, String sheetName)
            throws Exception;
}
