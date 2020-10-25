package com.mastertheboss.camel;

import java.io.DataOutputStream;
import java.net.Socket;

public class JavaClient {

    public static void main(String argv[]) throws Exception {

        Socket clientSocket = new Socket("localhost", 4209);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        outToServer.writeBytes("Hello Camel\n");

        clientSocket.close();
    }
}