package com.example.Network2Server;

import java.sql.*;
class Database{
    private Connection connection;
    String JdbcURL = "jdbc:mysql://localhost:3306/login";
    String Username = "root";
    String Password = "";
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rst = null;

    Database(){

    }

    public boolean checkCredentials(String username, String password){

        try {
            String myQuery = "select username,password from users where username="+username+" and password="+password;
            con = DriverManager.getConnection(JdbcURL, Username, Password);
            pstmt = con.prepareStatement(myQuery);
            rst = pstmt.executeQuery();
            String userName;
            String passWord;
            boolean isExist = false;
            while(rst.next()) {
                isExist = true;
                userName = rst.getString(1);
                passWord = rst.getString(2);
            }
            if(!isExist) {
                return false;
            }
            else {
            return true;
            }
        } catch(Exception exec) {
            exec.printStackTrace();
            return false;
        }
    }

}
