package com.agrawroh.selemize.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.agrawroh.selemize.exceptions.ExceptionFactory;
import com.agrawroh.selemize.exceptions.ParserException;
import com.agrawroh.selemize.model.ExceptionType;
import com.agrawroh.selemize.utilities.Constants;

/**
 * POI Parser Class
 * 
 * @author agraw_ds7m
 * @version 2016-11-18 v1.0
 */
public class POIParser implements IParser {
    /************************************ Logger Instance ***********************************/
    private static final Logger LOGGER = LoggerFactory
            .getLogger(POIParser.class);

    /********************************* Dependency Injections ********************************/
    @Autowired
    private ExceptionFactory exceptionFactory;

    /**
     * File Parser
     */
    @Override
    public List<List<?>> parse(String filePath, String fileName,
            String sheetName) throws ParserException {
        /* Call File Parser */
        return parse(new File(filePath + Constants.PTH_SEP + fileName),
                sheetName);
    }

    /**
     * File Parser
     */
    @Override
    public List<List<?>> parse(File completeFilePath, String sheetName)
            throws ParserException {
        LOGGER.info("Start Parsing File: " + completeFilePath);

        /* Get File Extension */
        String fileExtension = completeFilePath.getName().substring(
                completeFilePath.getName().indexOf(Constants.SYM_DOT));

        /* Get & Return File Contents */
        return readExcel(completeFilePath, fileExtension, sheetName);
    }

    /**
     * Read Excel File
     * 
     * @param completeFilePath
     * @param fileExtension
     * @param sheetName
     * @throws ParserException
     */
    private List<List<?>> readExcel(File completeFilePath,
            String fileExtension, String sheetName) throws ParserException {
        /* Open Workbook */
        Workbook workbook = getWorkbook(completeFilePath, fileExtension);

        /* Check Whether Valid Workbook Instance */
        if (null == workbook) {
            /* Raise Exception */
            throw new IllegalArgumentException("Unsupported File!");
        }

        /* Open Sheet */
        Sheet sheet = workbook.getSheet(sheetName);

        /* Check Whether Valid Sheet Instance */
        if (null == sheet) {
            /* Throw Warning */
            LOGGER.warn("Sheet Does Not Exists. Reading First Available Sheet...");
            sheet = workbook.getSheetAt(0);
        }

        /* Check Whether Valid Sheet Instance */
        if (null == sheet) {
            /* Raise Exception */
            throw new IllegalArgumentException("No Sheets Are Present!");
        }

        /* Get Count */
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
        LOGGER.info("Sheet Opened With {} Rows.", rowCount);

        /* Read Data */
        return getData(sheet, rowCount);
    }

    private List<List<?>> getData(Sheet sheet, int rowCount) {
        List<List<?>> fileContents = new ArrayList<>();

        /* Get Rows */
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            /* Read Row */
            Row row = sheet.getRow(rowIndex);

            /* Get Cell Data */
            List<?> rowContent = getCellData(row);

            /* Check Whether Row Has Data */
            if (!rowContent.isEmpty()) {
                /* Append Data */
                fileContents.add(rowContent);
            }
        }

        /* Return File Contents */
        return fileContents;
    }

    /**
     * Get Cell Data
     * 
     * @param row
     * @return
     */
    private List<?> getCellData(Row row) {
        List<Cell> columnData = new ArrayList<>();

        /* Get Columns */
        for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
            /* Read Column Data */
            columnData.add(row.getCell(columnIndex));
        }

        /* Return Output */
        return columnData;
    }

    /**
     * Get Workbook
     * 
     * @param completeFilePath
     * @param fileExtension
     * @return workbook
     * @throws ParserException
     */
    private Workbook getWorkbook(File completeFilePath, String fileExtension)
            throws ParserException {
        /* Open & Read File */
        try (FileInputStream inputStream = new FileInputStream(completeFilePath)) {
            /* Get Type Based On File Extension */
            return fileExtension.equals(Constants.EXT_XLSX) ? (XSSFWorkbook) WorkbookFactory
                    .create(inputStream) : fileExtension
                    .equals(Constants.EXT_XLS) ? (HSSFWorkbook) WorkbookFactory
                    .create(inputStream) : null;
        } catch (Exception ex) {
            /* Catch, Log & Throw Exception */
            LOGGER.error("Error Occured While Parsing File!", ex);
            throw new ParserException(exceptionFactory.create(
                    ExceptionType.PARSER, "M001", ex,
                    completeFilePath.getAbsolutePath()));
        }
    }
}
