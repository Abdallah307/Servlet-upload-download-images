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

@WebServlet(name = "upload", value = "/upload")
public class UploadImage extends HttpServlet {



    public void init() {

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }



    public void destroy() {
    }
}