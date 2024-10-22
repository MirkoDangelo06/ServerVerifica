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
    private String continuo;
    
    int numeroTentativi =0; 

    public MioThread(Socket socket, int numeroCasuale) {
            this.socket = socket;
            this.numeroCasuale = numeroCasuale;
    }

    @Override
    public void run() {

        try {
            String nuemroDaIndovinare;
            String sceltaInput;
            String scelta ="";
            int numeroTentativi =0; 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
           //do{
            do {   
               
             
                        
                System.out.println(numeroCasuale);
               // prendo una stringa in imput, e poi cambio e lo rendo intero
                numeroDaIndovinareString = in.readLine();
               
                if(numeroDaIndovinareString.equals("")){
                    out.writeBytes("!!!" );
                   
                }else{
                    numeroDaIndovinareInt = Integer.parseInt(numeroDaIndovinareString);
                }
                
                if( numeroDaIndovinareInt< 0 || numeroDaIndovinareInt >100 ){
                    out.writeBytes("!!!" );
                    System.out.println("!!!");
                }
                System.out.println(numeroDaIndovinareInt);



                //controlli 
                if(numeroDaIndovinareInt > numeroCasuale){
                   
                    out.writeBytes(">"+ "\n");
                }

                if( numeroDaIndovinareInt< numeroCasuale){
                    out.writeBytes("<" + "\n");
                }
                
                //indovinato, invio numero tentativi
                if( numeroDaIndovinareInt == numeroCasuale){
                    out.writeBytes("=" + "\n");
                    String numeroTentativiString = Integer.toString(numeroTentativi + 1);
                   out.writeBytes(numeroTentativiString );
                    break;
                }
                
                numeroTentativi ++ ; 
                System.out.println("numero tentativi :" + numeroTentativi);

            } while (true); 

           //}while(scelta.equals("y"));
                  
              socket.close();

            
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}