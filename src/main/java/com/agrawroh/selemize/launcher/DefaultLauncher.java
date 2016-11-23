package com.agrawroh.selemize.launcher;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.agrawroh.selemize.parser.IParser;
import com.agrawroh.selemize.parser.POIParser;
import com.agrawroh.selemize.printer.DefaultPrinter;
import com.agrawroh.selemize.printer.IPrinter;
import com.agrawroh.selemize.utilities.Constants;

/**
 * Launcher Class
 * 
 * @author agraw_ds7m
 * @version 2016-11-18 v1.0
 */
public class DefaultLauncher {

    /************************************ Logger Instance ***********************************/
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultLauncher.class);

    /********************************** Application Context *********************************/
    private static final String APP_CONTEXT_NAME = "selemize-config.xml";
    private static final ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext(
            APP_CONTEXT_NAME);

    /*********************************** Main Entry Point ***********************************/
    public static void main(String[] args) throws Exception {
        /* Execute Program */
        new DefaultLauncher().execute(args);
    }

    /**
     * Execute Program
     * 
     * @throws Exception
     */
    private void execute(String[] arguments) throws Exception {
        /* Get System Arguments */
        String filePath = System.getProperty("fileName");
        String fileName = System.getProperty("filePath");
        String sheetName = System.getProperty("sheetName");

        /* Check Arguments */
        if (arguments.length < 2
                && (StringUtils.isEmpty(filePath) || StringUtils
                        .isEmpty(fileName))) {
            /* Raise Exception */
            LOGGER.error("Missing Path OR FileName!");
            throw new IllegalArgumentException(
                    "Invalid Number Of Arguments Passed!");
        }

        /* Map Arguments */
        if (StringUtils.isEmpty(filePath)) {
            filePath = arguments[0];
            fileName = arguments[1];
            sheetName = 3 == arguments.length ? arguments[2] : null;
        }

        /* Detect & Parse File */
        IParser parser = (IParser) getAppContext().getBean(POIParser.class);
        List<List<?>> fileContents = parser
                .parse(fileName, filePath, sheetName);

        /* Print File Contents */
        IPrinter printer = (IPrinter) getAppContext().getBean(DefaultPrinter.class);
        printer.prettyPrint(fileContents, Constants.SYM_SEP);
    }

    /**
     * Fetch Application Context
     * 
     * @return the appContext
     */
    public static ApplicationContext getAppContext() {
        return APP_CONTEXT;
    }
}
