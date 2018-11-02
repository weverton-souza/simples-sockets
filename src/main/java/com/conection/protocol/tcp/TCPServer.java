package com.conection.protocol.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {



    public TCPServer() {
        exec();
    }

    private void exec() {

        System.out.println("Servidor iniciado. Aguardando conexão.");
        try{
            Socket connectionSocket = new ServerSocket(8080)
                                                    .accept();

            System.out.println("Conexão estabelecida com sucesso!");
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader( connectionSocket.getInputStream() )
            );

            DataOutputStream outToClient = new DataOutputStream( connectionSocket.getOutputStream() );

            outToClient.writeBytes(
                    "Recebido do servidor: " + inFromClient.readLine().toUpperCase() + '\n'
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
