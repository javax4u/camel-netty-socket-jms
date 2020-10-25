package com.javax4u.camel.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaClient {

    public static void main(String argv[]) {

        Socket clientSocket;
        try {
            clientSocket = new Socket("localhost", 4209);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            outToServer.writeBytes("\n\n *** Hello Camel *** \n\n");

            clientSocket.close();
            System.out.println("Message succssfully sent to server");
        } catch (IOException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
