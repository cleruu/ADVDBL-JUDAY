package com.example.juday;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
    String className = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://192.168.56.1/juday?useSSL=false&allowPublicKeyRetrieval=true";
    String un = "root";
    String password = "5wLCmVS9Q2Z4tvVp";

    public Connection conn() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url, un, password);

//            conn = DriverManager.getConnection(ConnURL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

}

