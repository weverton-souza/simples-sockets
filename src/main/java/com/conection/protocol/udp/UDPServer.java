package com.conection.protocol.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public UDPServer() {
        exec();
    }

    private void exec() {

        int porta = 8080;
        int numConn = 1;

        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(porta);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            System.out.println("Esperando por datagrama UDP na porta " + porta);
            serverSocket.receive(receivePacket);
            System.out.print("Datagrama UDP [" + numConn + "] recebido: ");

            String sentence = new String(receivePacket.getData());
            sendData = sentence.toUpperCase().getBytes();

            System.out.println(sentence);

            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, receivePacket.getAddress(), receivePacket.getPort());

            System.out.print("Enviando resposta: " + sentence.toUpperCase());

            serverSocket.send(sendPacket);
            System.out.println("OK\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
