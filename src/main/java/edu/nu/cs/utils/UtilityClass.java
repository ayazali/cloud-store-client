package edu.nu.cs.utils;

import edu.nu.cs.constants.Constants;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Ayaz Ali Qureshi
 * @version 1.0
 *          <p/>
 *          This class will contain utility functions
 */
public class UtilityClass {    /**
     * Method will split file URL on source directory path
     *
     * @param fullPath
     * @return
     */
    public static String getRelPathToFile(String fullPath) {
        return fullPath.split(Constants.SOURCE_DIRECTORY)[1];
    }

    public static String getMD5(String input){
        String md5 = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5 = new BigInteger(1, md.digest(input.getBytes())).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }


}
