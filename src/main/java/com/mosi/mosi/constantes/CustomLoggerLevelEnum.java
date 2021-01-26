package com.mosi.mosi.constantes;

import org.apache.logging.log4j.Level;

public enum CustomLoggerLevelEnum {
    /**
     * This exception will be logged to HTML file and can save the stack trace
     */
    EXCEPTION("EXCEP"),
    /**
     * This exception will log failed transactions to MongoDB
     */
    PUBLIC_SERVICE_FAILED_TRANSACTION("PFAIL");

    private String name;

    CustomLoggerLevelEnum(String name){
        this.setName(name);
    }

    public Level LEVEL(){
        return Level.forName(this.getName(), 450);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
