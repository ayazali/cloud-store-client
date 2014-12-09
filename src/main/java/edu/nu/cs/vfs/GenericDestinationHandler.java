package edu.nu.cs.vfs;

import edu.nu.cs.constants.Constants;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.impl.SynchronizedFileObject;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * @author Ayaz Ali Qureshi
 *         <p/>
 *         Generic Destination Handler, handle all schecme selection in this class
 */
public class GenericDestinationHandler {

    public static String scheme = null;

    /**
     * Prepare a FileObject based on desired scheme
     *
     * @param fsManager
     * @param fileName
     * @return
     * @throws FileSystemException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.net.URISyntaxException
     */
    public org.apache.commons.vfs2.FileObject getDestinationObject(FileSystemManager fsManager, String fileName) throws FileSystemException, UnsupportedEncodingException, URISyntaxException {

        SynchronizedFileObject cwd = new SynchronizedFileObject(fsManager.resolveFile(Constants.BASE_DIRECTORY));

        /* If scheme is SFTP */
        if (this.scheme.equals(Constants.SCHEME_SFTP)) {
            SFTPHandler sftpHandler = new SFTPHandler();
            return fsManager.resolveFile(sftpHandler.getFTPURL(fileName), sftpHandler.getOptions());
        } else {
            return fsManager.resolveFile(cwd, Constants.DESTINATION_DIRECTORY + "/" + Constants.LOGIN_USERNAME);
        }

    }


}
