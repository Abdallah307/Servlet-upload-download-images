package com.example.Network2Server;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "hi", value = "/download")
public class DownloadFileServlet extends HttpServlet {

    String path = "/home/abdallah/IdeaProjects/Network2Server/src/main/webapp/images/";
    ArrayList<String> images = new ArrayList<String>(Arrays.asList(
            "im.jpg",
            "im2.png",
            "im3.jpg"
    ));



    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path


        Files.list(new File(path).toPath())
                .limit(10)
                .forEach(path -> {
                    String strokes[] = String.valueOf(path).split("/");
                    System.out.println("path : " + strokes[strokes.length-1]);
                });
        final String authorization = request.getHeader("body");

        System.out.println("Casdasd : " + authorization);

        String filePath = path+authorization;

        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);



        // obtains ServletContext
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
