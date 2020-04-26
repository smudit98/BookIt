/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mudit
 */
public class Conn {
    public static void main(String args[]){
                        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/db11g",
                "myuser",
                "password"
            );
            System.out.println("successful");
            

        }
        catch(Exception e) {
            System.out.println("did not connect");
        }
    }
}
