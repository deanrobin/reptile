package com.dean.reptile.util;

public class Config {
    private boolean jobSearchContinue = false;
    private Config() {};
    private static Config config = null;
    public static Config instance() {
        if (config == null) {
            synchronized (Config.class) {
                if (config == null) {
                    config = new Config();
                }
            }
        }
        return config;
    }

    public boolean isJobSearchContinue() {
        return jobSearchContinue;
    }

    public void setJobSearchContinue(boolean jobSearchContinue) {
        this.jobSearchContinue = jobSearchContinue;
    }
}
