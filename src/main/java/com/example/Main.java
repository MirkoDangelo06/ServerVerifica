package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
    
      System.out.println("Server avviato");
        ServerSocket ser1 = new ServerSocket(3000);// apre una porta 
       
        do{
            Random random = new Random();
            int numero = random.nextInt(100);
            Socket socket = ser1.accept(); // aspetta la connessione , restituisce una socket ;
            System.out.println("un client si è collegato");

            MioThread t  = new MioThread(socket,numero);
            t.start();
       
        }while(true);


    }
}