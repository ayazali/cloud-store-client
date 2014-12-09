package edu.nu.cs.vfs;

import edu.nu.cs.constants.Constants;
import edu.nu.cs.utils.FileChangeListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Main code for starting file syncing
 *
 * @author Ayaz Ali Qureshi
 */
public class FileSyncing {

    /**
     * Starting VFS file change thread to monitor file system on client
     */
    public void startSync() {
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
            e.printStackTrace(System.out);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            e.printStackTrace(System.out);
        } catch (FileSystemException e) {
            e.printStackTrace();
            e.printStackTrace(System.out);
        }
    }


}
