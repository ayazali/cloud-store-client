package edu.nu.cs.rest.services;

import edu.nu.cs.constants.Constants;
import edu.nu.cs.rest.beans.User;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ayaz Ali Qureshi on 12/8/2014.
 */
@Component
public class LoginUser {

    private String username;

    private String pass;

    private User loggedInUser;

    private static final String loginURL = "http://" + Constants.WEB_HOSTIP + "/secure/authenticate";

    /**
     * Login function will invoke RestTemplate with jackson help and call POST based web service on cloud-server
     *
     * @return Boolean for if login was success or not
     */
    public boolean login() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        User sendUser = new User();
        sendUser.setUserName(this.getUsername());
        sendUser.setPassword(this.getPass());

        User usr = restTemplate.postForObject(loginURL, sendUser, User.class);

        if (usr != null) {
            System.out.println("\n" + usr.getUserName() + "\n");
            this.setLoggedInUser(usr);
            Constants.LOGIN_USERNAME = usr.getUserName();
            return usr.getUserID() != null;
        } else {
            return false;
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
