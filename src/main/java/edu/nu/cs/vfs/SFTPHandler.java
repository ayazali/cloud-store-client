package edu.nu.cs.vfs;

import edu.nu.cs.constants.Constants;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * @author Ayaz Ali Qureshi
 *         Specific class for only handling connections with SFTP Handler
 */
public class SFTPHandler {

    /**
     * Return prepared SFTP url
     *
     * @param fileName
     * @return URL for resolving file
     * @throws FileSystemException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.net.URISyntaxException
     */
    public String getFTPURL(String fileName) throws FileSystemException, UnsupportedEncodingException, URISyntaxException {

        String url_s = Constants.SCHEME_SFTP + "://" + Constants.USERINFO_USERNAME + ":" + Constants.USERINFO_PASSWORD + "@" + Constants.REMOTE_HOSTIP + "/" + Constants.REMOTE_BASEPATH + Constants.DESTINATION_DIRECTORY;

        if (fileName != null)
            return url_s + "/" + fileName;
        else
            return url_s;
    }

    /**
     * Settings for filesystem
     *
     * @return FileSystem Options with settings
     * @throws FileSystemException
     */
    public FileSystemOptions getOptions() throws FileSystemException {
        FileSystemOptions opts = new FileSystemOptions();

        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);
        SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

        return opts;
    }

}
