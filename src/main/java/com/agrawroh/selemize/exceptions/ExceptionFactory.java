package com.agrawroh.selemize.exceptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.agrawroh.selemize.model.ExceptionType;
import com.agrawroh.selemize.utilities.Constants;

/**
 * Exception Factory
 * 
 * @author agraw_ds7m
 * @version 2016-11-20 v1.0
 */
public class ExceptionFactory {

    /************************************ Logger Instance ***********************************/
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ExceptionFactory.class);

    /* Exception Table */
    private Map<String, String> exceptionTable = new HashMap<>();

    @PostConstruct
    public void loadExceptionCodes() throws Exception {
        String data = null;

        /* Iterate File, Load Exception Data */
        try (BufferedReader b = new BufferedReader(new InputStreamReader(this
                .getClass().getResourceAsStream(Constants.ERR_CODES)))) {
            /* Read File Contents */
            while (null != (data = b.readLine())) {
                String[] dataPair = data.split(Constants.SYM_SEP);

                /* Check Data Length */
                if (2 == dataPair.length) {
                    /* Put Exception */
                    exceptionTable.put(dataPair[0], dataPair[1]);
                }
            }
        } catch (Exception ex) {
            /* Catch, Log & Throw */
            LOGGER.error("Unable To Load Exception Table.", ex);
            throw ex;
        }

        /* Print Exception Table Info */
        LOGGER.info("Exception Table Loaded With Size: "
                + exceptionTable.size());
    }

    /**
     * Create Exception Message
     * 
     * @param exceptionType
     * @param exceptionCode
     * @param exceptionParams
     */
    public Throwable create(ExceptionType exceptionType, String exceptionCode,
            Throwable exception, String... exceptionParams) {
        /* Create Exception Message */
        String errorString = exceptionTable.get(exceptionCode);

        /* Handle Exception Unavailability */
        if (StringUtils.isEmpty(exceptionCode)) {
            return new Throwable(Constants.ERR_UNKN, exception);
        }

        /* Create Message */
        int i = 0;
        while (errorString.contains(Constants.ERR_CNS)) {
            errorString = errorString.replaceFirst(Constants.ERR_CNS,
                    exceptionParams[i++]);
        }

        /* Return Exception */
        return new Throwable(errorString, exception);
    }
}
