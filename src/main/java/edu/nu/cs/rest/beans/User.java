package edu.nu.cs.rest.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Service;

/**
 * Bean for User Object received via server auth
 *
 * @author Ayaz Ali Qureshi
 */
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    Long userID;
    String userName;
    String password;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
