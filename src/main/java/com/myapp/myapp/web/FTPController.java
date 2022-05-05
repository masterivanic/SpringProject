package com.myapp.myapp.web;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;

public class FTPController {

    private FTPClient client;
    private FTPClientConfig conf;
    private int reply;
    private String serverAddress;
    private String image;

    public FTPController() { }

    public void initialise() {
        client = new FTPClient();
        conf = new FTPClientConfig();
        conf.setDefaultDateFormatStr("MM-dd-yyyy");
        reply = 0;
        image = "im.jpeg";
    }

    public void FtpConnection() throws Exception {
        initialise();
        try {
            serverAddress = "10.1.1.223";
            try{
                client.connect(serverAddress);
            } catch (ConnectException e){
                throw new Exception("Server is not available now, try later");
            }
            
            System.out.println("connected to : " + serverAddress);

            reply = client.getReplyCode();
            System.out.println("status code: " + reply);
            client.login("dgls", "");
            client.enterLocalPassiveMode();

            sendFileThroughFTP(client, image);

            FTPFile[] listFiles = client.listFiles("/Tambia");
            System.out.println(listFiles.length);
            printFilesDetails(listFiles);

            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                System.out.println("ftp refused connection");
                throw new Exception("ftp refused connection");
                // System.exit(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printFilesDetails(FTPFile[] files) {
        for (FTPFile file : files) {
            String details = file.getName();
            if (file.isDirectory()) {
                details = "[" + details + "]";
            } else {
                System.out.println("empty directory");
            }

            details += "\t\t" + file.getSize() + "KB";
            System.out.println("*********************************file in directory***********************");
            System.out.println(details);
        }
    }

    public void showServerReply(FTPClient ftp) {
        String[] replies = ftp.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String reply : replies) {
                System.out.println("Server reply: " + reply);
            }
        }
    }

    public void listDirectories() {
        initialise();
        try {
            serverAddress = "10.1.1.223";
            client.connect(serverAddress);
            boolean login = client.login("dgls", "");
            client.enterLocalPassiveMode();
            System.out.println(client.getReplyCode());
            if (client.getReplyCode() == 230) {
                if (login) {
                    System.out.print("inside login");
                    FTPFile[] directories = client.listDirectories();
                    System.out.print("directory length " + directories.length);

                    for (FTPFile di : directories) {
                        String value = di.getName();
                        if (value.isEmpty()) {
                            System.out.println("empty folder");
                        }
                        System.out.println(di.getName());
                    }
                } else {
                    System.out.println("Credentials incorrect");
                }
            } else {
                System.out.println("ftp refused connection, error code == " + client.getReplyCode());
            }

            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFileThroughFTP(FTPClient client, String filename) throws IOException {
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        client.setSoTimeout(1000000);
        client.changeWorkingDirectory("/Tambia");
        client.makeDirectory("/Tambia");
        // client.setAutodetectUTF8(true);

        FileInputStream fs = new FileInputStream(filename);
        boolean store = client.storeFile(filename, fs);
        
        if (store) {
            System.out.println("Send successfully, and operation completed...");
        } else {
            System.out.println("error occur");
        }
        client.completePendingCommand();
    }

    public void deleteFileFromFTP() {

    }

    public void createDirectoryFromFTP() {

    }
}
