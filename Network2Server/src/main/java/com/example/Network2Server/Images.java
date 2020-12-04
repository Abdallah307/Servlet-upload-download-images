package com.example.Network2Server;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "retrieveimages", value = "/retrieve")
public class Images extends HttpServlet {

    final String path = "/home/abdallah/IdeaProjects/Network2Server/src/main/webapp/images/";
    ArrayList<String> serverImages = new ArrayList<String>();

    public void init() {

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        serverImages.clear();
        Files.list(new File(path).toPath())
                .limit(10)
                .forEach(path -> {
                    String strokes[] = String.valueOf(path).split("/");
                    serverImages.add(strokes[strokes.length-1]);
                });
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(serverImages);





    }



    public void destroy() {
    }
}