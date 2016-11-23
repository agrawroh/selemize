package com.agrawroh.selemize.printer;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default Printer
 * 
 * @author agraw_ds7m
 * @version v1.0
 */
public class DefaultPrinter implements IPrinter {

    /************************************ Logger Instance ***********************************/
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultPrinter.class);

    /**
     * Pretty Print File Contents
     * 
     * @param fileContents
     */
    @Override
    public void prettyPrint(List<List<?>> fileContents, CharSequence seperator) {
        /* Print File Content */
        for (final List<?> rowContent : fileContents) {
            /* Access Row Data */
            StringBuilder rowOutput = new StringBuilder();
            for (final Object columnContent : rowContent) {
                /* Access Column Data */
                rowOutput.append(getData(columnContent)).append(
                        seperator.toString());
            }

            /* Print Row Data */
            LOGGER.info(rowOutput.toString());
        }
    }

    //TODO : Move to separate class
    private String getData(Object columnContent) {
        if (columnContent instanceof Cell) {
            /* Append Column Data To Output */
            return getCellData((Cell) columnContent);
        } else if (columnContent instanceof String) {
            /* Append Column Data To Output */
            return (String) columnContent;
        }

        return new String();
    }

    /**
     * Get Cell Data
     * 
     * @param cell
     * @return cellData
     */
    @SuppressWarnings("deprecation")
    private String getCellData(Cell cell) {
        /* Return Cell Type */
        CellType cellType = cell.getCellTypeEnum();

        /* Get Data */
        switch (cellType) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());

            case STRING:
                return cell.getStringCellValue();

            case BLANK:
                return new String();

            default:
                return new String();
        }
    }

}
