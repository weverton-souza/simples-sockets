package com.conection.protocol.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public UDPClient() {
        exec();
    }

    private void exec() {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                System.in));

        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();


            String server = "localhost";
            int port = 8080;

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            System.out.println("Digite o texto a ser enviado: ");
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, InetAddress.getByName(server), port);

            System.out
                    .println("Enviando pacote UDP para " + server + ":" + port);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);

            clientSocket.receive(receivePacket);
            System.out.println("Pacote UDP recebido.");

            String modifiedSentence = new String(receivePacket.getData());

            System.out.println("Texto recebido do servidor: " + modifiedSentence);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

