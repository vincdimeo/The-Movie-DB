package com.example.themoviedb.Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread implements Runnable {

    private final String SERVER_IP = "20.197.17.179";
    private final int SERVER_PORT = 8080;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String dataToSend, response = "";

    public ClientThread(String dataToSend) {
        this.dataToSend = dataToSend;
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            socket = new Socket(serverAddr, SERVER_PORT);

            sendDataToServer(dataToSend);

            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!Thread.currentThread().isInterrupted()) {
                String message = in.readLine();
                System.out.println("*** " + message);

                if (message.equals("OK")) {
                    response = "OK";
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }

    public void sendDataToServer(final String data) {
        new Thread(() -> {
            try {
                if (socket != null) {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}