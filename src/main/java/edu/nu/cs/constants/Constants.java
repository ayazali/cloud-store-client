package edu.nu.cs.constants;

import edu.nu.cs.utils.PropertyUtil;

/**
 * Created by Hasnain on 11/29/2014.
 */
public class Constants {
    public static final String SOURCE_DIRECTORY = PropertyUtil.getProperty("source.directory");
    public static final String BASE_DIRECTORY = PropertyUtil.getProperty("base.directory");
    public static final String DESTINATION_DIRECTORY = PropertyUtil.getProperty("destination.directory");

    public static final String SCHEME_SFTP = "sftp";

    public static final String USERINFO_USERNAME = PropertyUtil.getProperty("userinfo.username");
    public static final String USERINFO_PASSWORD = PropertyUtil.getProperty("userinfo.password");

    public static final String REMOTE_HOSTIP = PropertyUtil.getProperty("remote.hostip");
    public static final String REMOTE_BASEPATH = PropertyUtil.getProperty("remote.basepath");
}
