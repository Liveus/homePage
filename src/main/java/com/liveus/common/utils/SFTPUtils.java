package com.liveus.common.utils;

import com.jcraft.jsch.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/12/6 14:18
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class SFTPUtils {

    /**
     * 默认端口
     */

    private final static int DEFAULT_PORT = 22;
    @Value("${SFTP_IP}")
    private final static String HOST = "${SFTP_IP}";
    @Value("${SFTP_PORT}")
    private final static String PORT = "22";
    @Value("${SFTP_USERNAME}")
    private final static String USER_NAME = "root";
    @Value("${SFTP_PASSWORD}")
    private final static String PASSWORD = "shen0410";

    /**
     * 服务端保存的文件名
     */
    private String remote;

    /**
     * 服务端保存的路径
     */
    private String remotePath = "/home/python/ftpfile";

    /**
     * 本地文件
     */
    private File local;

    /**
     * 主机地址
     */
    private String host = HOST;

    /**
     * 端口
     */
    private int port = DEFAULT_PORT;

    /**
     * 登录名
     */
    private String userName = USER_NAME;

    /**
     * 登录密码
     */
    private String password = PASSWORD;

    private ChannelSftp sftp;

    public SFTPUtils(){
    }

//    public SFTPUtils(String host, int port, String userName, String password) {
//        this.init(host, port, userName, password);
//    }

    /**
    * @Desc:  初始化
    * @author: shenliqiang
    * @Time: 2019/12/6 14:44
    * @return:
    */

    private void init(String host, int port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    /**
    * @Desc:  连接sftp
    * @author: shenliqiang
    * @Time: 2019/12/6 14:44
    * @return:
    */

    @Test
    public void connect() throws JSchException, NoSuchFieldException, IllegalAccessException, SftpException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(userName, host, port);
        session.setPassword(password);
        Properties sessionConfig = new Properties();
        // StrictHostKeyChecking
        // "如果设为"yes"，ssh将不会自动把计算机的密匙加入"$HOME/.ssh/known_hosts"文件，
        // 且一旦计算机的密匙发生了变化，就拒绝连接。
        sessionConfig.setProperty("StrictHostKeyChecking", "no");
        session.setConfig(sessionConfig);
        session.connect(60 * 1000);
        Channel channel = session.openChannel("sftp");
        sftp = (ChannelSftp) channel;
        channel.connect(60 * 1000);
        Class cl = ChannelSftp.class;
        Field f =cl.getDeclaredField("server_version");
        f.setAccessible(true);
        f.set(sftp, 2);
        sftp.setFilenameEncoding("GBK");
    }

    /**
    * @Desc:  上传文件
    * @author: shenliqiang
    * @Time: 2019/12/6 14:43
    * @return:
    */

    public void uploadFile() throws Exception {
        FileInputStream inputStream = null;
        try {
            connect();
            if (isEmpty(remote)) {
                remote = local.getName();
            }
            if (!isEmpty(remotePath)) {
                sftp.cd(remotePath);
            }
            inputStream = new FileInputStream(local);
            sftp.put(inputStream, remote);
        } catch (Exception e) {
            throw e;
        } finally {
            sftp.disconnect();
            close(inputStream);
        }
    }

    public void uploadFile(InputStream inputStream) throws Exception {
        try {
            connect();
            if (isEmpty(remote)) {
                remote = local.getName();
            }
            if (!isEmpty(remotePath)) {
                createDir(remotePath);
            }

            sftp.put(inputStream, remote);
        } catch (Exception e) {
            throw e;
        } finally {
            sftp.disconnect();
            close(inputStream);
        }
    }

    @Async("taskExecutor")//异步
    public void uploadFile(String filename, String filePath, MultipartFile file) throws Exception {
        try {
            connect();
            if (!isEmpty( filePath )) {
                createDir( filePath );
            }
            sftp.put( file.getInputStream(), filename );
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            sftp.disconnect();
            close(file.getInputStream());
        }
    }
    /**
    * @Desc:  目录是否存在
    * @author: shenliqiang
    * @Time: 2019/12/6 14:41
    * @return:
    */

    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }
    /**
    * @Desc:  创建目录
    * @author: shenliqiang
    * @Time: 2019/12/6 14:42
    * @param createPath
    * @return:
    */

    public void createDir(String createPath) throws Exception {
        try {
            if (isDirExist(createPath)) {
                this.sftp.cd(createPath);
            } else {
                String pathArry[] = createPath.split("/");

                for (String path : pathArry) {
                    if (path.equals("")) {
                        continue;
                    }

                    if (isDirExist(path.toString())) {
                        sftp.cd(path.toString());
                    } else {
                        // 建立目录
                        sftp.mkdir(path.toString());
                        // 进入并设置为当前目录
                        sftp.cd(path.toString());
                    }
                }

            }
        } catch (SftpException e) {
            throw new Exception("创建路径错误：" + createPath);
        }
    }

    /**
    * @Desc:  下载
    * @author: shenliqiang
    * @Time: 2019/12/6 14:43
    * @return:
    */

    public void download() throws Exception {
        FileOutputStream output = null;
        try {
            this.connect();
            if (null != remotePath || !("".equals(remotePath))) {
                sftp.cd(remotePath);
            }
            output = new FileOutputStream(local);
            sftp.get(remote, output);
        } catch (Exception e) {
            throw e;
        } finally {
            sftp.disconnect();
            close(output);
        }
    }

    public void download(OutputStream outputStream) throws Exception {

        try {
            this.connect();
            if (null != remotePath || !("".equals(remotePath))) {
                sftp.cd(remotePath);
            }
            sftp.get(remote, outputStream);
        } catch (Exception e) {
            throw e;
        } finally {
            sftp.disconnect();
        }
    }

    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    public static void close(OutputStream... outputStreams) {
        for (OutputStream outputStream : outputStreams) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void close(InputStream... inputStreams) {
        for (InputStream inputStream : inputStreams) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
