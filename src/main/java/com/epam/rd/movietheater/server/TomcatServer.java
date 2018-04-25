package com.epam.rd.movietheater.server;

import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TomcatServer {
    private static final int PORT = getPort();

    public void run() throws Exception {
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        String tempDir = createTempDir();
        tomcat.setBaseDir(tempDir);
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp("", ".");
        tomcat.start();
        createTempDirForFiles(tempDir);
        tomcat.getServer().await();
    }

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 8080;
    }

    // based on AbstractEmbeddedServletContainerFactory
    private static String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat", "-" + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            );
        }
    }
    private static void createTempDirForFiles(String workingDirectory) {
        try {
            String destination = workingDirectory + "/work/Tomcat/localhost/ROOT/tmp";
            Path path = Paths.get(destination);
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
