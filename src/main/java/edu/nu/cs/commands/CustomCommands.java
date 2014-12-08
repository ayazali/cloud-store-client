package edu.nu.cs.commands;

import edu.nu.cs.constants.Constants;
import edu.nu.cs.utils.FileChangeListener;
import edu.nu.cs.vfs.GenericDestinationHandler;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

@Component
public class CustomCommands implements CommandMarker {

    private boolean simpleCommandExecuted = false;

    @CliAvailabilityIndicator({"run"})
    public boolean isRunAvailable() {
        //always available
        return true;
    }

    @CliCommand(value = "run", help = "Print a simple hello world message")
    public String simple() {

        try {
            FileSystemManager fsManager = VFS.getManager();
            FileObject cwd = fsManager.resolveFile(Constants.BASE_DIRECTORY);
            FileObject src = fsManager.resolveFile(cwd, Constants.SOURCE_DIRECTORY);
            GenericDestinationHandler.scheme = Constants.SCHEME_SFTP;

            FileChangeListener fileChangeListener = new FileChangeListener();
            DefaultFileMonitor fm = new DefaultFileMonitor(fileChangeListener);
            fm.setRecursive(true);
            fm.addFile(src);
            fm.start();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }

        return "Running.....";
    }

}
