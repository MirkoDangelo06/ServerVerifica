package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread{
    private Socket socket;
    private int numeroCasuale;
 
    private String numeroDaIndovinareString;
    private int numeroDaIndovinareInt;
    private String risposta;
    
    public MioThread(Socket socket, int numeroCasuale) {
            this.socket = socket;
            this.numeroCasuale = numeroCasuale;
    }

    @Override
    public void run() {

        try {
            String nuemroDaIndovinare;
            String sceltaInput;
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            do {             
                System.out.println(numeroCasuale);
               // prendo una stringa in imput, e poi cambio e lo rendo intero
                numeroDaIndovinareString = in.readLine();
                numeroDaIndovinareInt = Integer.parseInt(numeroDaIndovinareString);
                System.out.println(numeroDaIndovinareInt);

                if(numeroDaIndovinareInt > numeroCasuale){
                   
                    out.writeBytes(">"+ "\n");
                }
                if( numeroDaIndovinareInt< numeroCasuale){
                    out.writeBytes(risposta + "\n");
                }




            } while (true);









            


            //socket.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}