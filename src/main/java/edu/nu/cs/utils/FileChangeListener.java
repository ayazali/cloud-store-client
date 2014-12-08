package edu.nu.cs.utils;

import edu.nu.cs.constants.Constants;
import edu.nu.cs.vfs.GenericDestinationHandler;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.SynchronizedFileObject;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * @author Ayaz Ali Qureshi
 * @version 1.0
 * @see org.apache.commons.vfs2.FileListener
 * <p/>
 * Implementation of FileListener, this class is basically responsible for copying and deleting files once a change is detected in the
 * corresponding filesystem
 */
public class FileChangeListener implements FileListener {

    private FileSystemManager fsManager;
    private SynchronizedFileObject cwd;
    private SynchronizedFileObject dest;
    private SynchronizedFileObject src;
    private GenericDestinationHandler destHand;

    /**
     * Initialize all variables
     *
     * @throws org.apache.commons.vfs2.FileSystemException
     */
    public FileChangeListener() throws FileSystemException, UnsupportedEncodingException, URISyntaxException {
        this.fsManager = VFS.getManager();
        this.cwd = new SynchronizedFileObject(fsManager.resolveFile(Constants.BASE_DIRECTORY));

        this.destHand = new GenericDestinationHandler();
        /* Fetch destination object from generic handler cause it will contain specific implementations */
        this.dest = new SynchronizedFileObject(destHand.getDestinationObject(fsManager, null));
        this.src = new SynchronizedFileObject(fsManager.resolveFile(cwd, Constants.SOURCE_DIRECTORY));

    }

    /**
     * Invoke copy operation from SRC ==> DEST
     *
     * @param event
     * @throws Exception
     */
    @Override
    public void fileCreated(FileChangeEvent event) throws Exception {

        String fileName = UtilityClass.getRelPathToFile(event.getFile().getName().getFriendlyURI());
        System.out.println("file created : " + UtilityClass.getRelPathToFile(event.getFile().getName().getFriendlyURI()));
        dest.copyFrom(src, Selectors.SELECT_ALL);

    }

    /**
     * Delete file if-exists SRC ==> DEST
     *
     * @param event
     * @throws Exception
     */
    @Override
    public void fileDeleted(FileChangeEvent event) throws Exception {
        System.out.println("file deleted : "
                + event.getFile().getName());

        String fileName = UtilityClass.getRelPathToFile(event.getFile().getName().getFriendlyURI());
        //SynchronizedFileObject newDest = new SynchronizedFileObject(fsManager.resolveFile(cwd, Constants.DESTINATION_DIRECTORY + fileName));
        SynchronizedFileObject newDest = new SynchronizedFileObject(destHand.getDestinationObject(fsManager, fileName));

        if (newDest.exists()) {
            newDest.delete(Selectors.SELECT_SELF_AND_CHILDREN);
        }

    }

    /**
     * Invoke copy operation from SRC ==> DEST
     *
     * @param event
     * @throws Exception
     */
    @Override
    public void fileChanged(FileChangeEvent event) throws Exception {
        System.out.println(String.format(
                "File [%s] changed event from [%s]", event
                        .getFile().getName(), this));

        System.out.println("file created : " + UtilityClass.getRelPathToFile(event.getFile().getName().getFriendlyURI()));
        dest.copyFrom(src, Selectors.SELECT_ALL);
    }
}