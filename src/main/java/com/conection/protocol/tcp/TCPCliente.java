package com.conection.protocol.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPCliente {

    public TCPCliente() {
        exec();
    }

    private void exec() {

        String sentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        int port = 8080;
        String server = "localhost";

        try (Socket clientSocket = new Socket(server, port)){

            System.out.println("Conectado ao servidor " + server + ":" + port);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

            System.out.println("Digite uma mensagem a ser enviada:");
            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            System.out.println(inFromServer.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
