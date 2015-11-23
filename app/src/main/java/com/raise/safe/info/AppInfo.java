package com.raise.safe.info;

/**
 * Created by raise on 2015/11/23.
 */
public class AppInfo {

    public String versionCode;
    public String versionName;

    private static AppInfo m_instance = new AppInfo();

    private AppInfo() {
    }

    public static AppInfo getInstance() {
        return m_instance;
    }
}
