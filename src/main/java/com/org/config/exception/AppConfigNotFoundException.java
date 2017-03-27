package com.org.config.exception;

public class AppConfigNotFoundException extends RuntimeException {

    private String appcode;

    private String version;

    public AppConfigNotFoundException(String appcode, String version) {
       super("JSON Config not found for appcode : " + appcode + ", version : " + version);
       this.appcode = appcode;
       this.version = version;
    }

    public AppConfigNotFoundException(String appcode) {
        super("JSON Config not found for appcode : " + appcode);
        this.appcode = appcode;
    }
}
