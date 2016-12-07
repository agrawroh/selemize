package com.agrawroh.selemize.model;

/**
 * Selenium Step POJO
 * 
 * @author agraw_ds7m
 * @version 2016-12-02
 */
public class SeleniumStep {

    private String command;
    private String target;
    private String value;

    /* Constructor */
    public SeleniumStep(String command, String target, String value) {
        this.command = command;
        this.target = target;
        this.value = value;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command
     *            the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

}
