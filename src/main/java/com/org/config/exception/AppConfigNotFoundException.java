package com.org.config.exception;

public class AppConfigNotFoundException extends RuntimeException {

    private String appcode;

    private String version;

    //No config is located for a specific version of an app
    public AppConfigNotFoundException(String appcode, String version) {
       super("JSON Config not found for appcode : " + appcode + ", version : " + version);
       this.appcode = appcode;
       this.version = version;
    }

    //No config is located for an app
    public AppConfigNotFoundException(String appcode) {
        super("JSON Config not found for appcode : " + appcode);
        this.appcode = appcode;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
