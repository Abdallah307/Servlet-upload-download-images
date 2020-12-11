package com.example.Network2Server;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/login")
public class Login extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/text");
        PrintWriter out = response.getWriter();
        final String authorization = request.getHeader("Authorization");
        if(authorizeLogin(authorization)){
            out.println("Authorized");
        }
        else{
            out.println("Not Authorized");
        }




    }

    public boolean authorizeLogin(String authorization) {
        Database database = new Database();
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {

            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);
            boolean authorized = database.checkCredentials(values[0].intern(),values[1].intern() );
            return authorized;
//            if(values[0].intern() == "Abdallah" && values[1].intern() == "12345"){
//                return true;
//            }
//            else return false;
        }
        return false;

    }

    public void destroy() {
    }
}