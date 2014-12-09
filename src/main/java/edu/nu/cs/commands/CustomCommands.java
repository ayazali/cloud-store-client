package edu.nu.cs.commands;

import edu.nu.cs.rest.services.LoginUser;
import edu.nu.cs.vfs.FileSyncing;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class CustomCommands implements CommandMarker {

    private FileSyncing fileSyncing = new FileSyncing();

    @CliAvailabilityIndicator({"authenticate"})
    public boolean isLoginAvailable() {
        //always available
        return true;
    }

    @CliCommand(value = "authenticate", help = "Authenticate user command")
    public String authenticate(@CliOption(key = "username", mandatory = true, help = "User Name of Service") String username, @CliOption(key = "pass", mandatory = true, help = "Password for User") String pass) {

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(username);
        loginUser.setPass(pass);
        System.out.println("");

        boolean loginSuccess = loginUser.login();

        if (loginSuccess) {
            fileSyncing.startSync();
        }

        return loginSuccess ? "Login Successful" : "Login Failed";
    }

}
