package it.fi.meucci;

import java.io.*;
import java.net.*;
import java.util.*;

import java.net.Socket;

public class DThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringamodificata = null;
    BufferedReader indalClient;
    DataOutputStream outversoClient;
    Server mio;

    public DThread(Socket s, Server e) {
        this.client = s;
        mio=e;
    }

    public void run() {
        try {
            indalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outversoClient = new DataOutputStream(client.getOutputStream());
            for(;;){
                stringaRicevuta = indalClient.readLine();
                if(stringaRicevuta==null||stringaRicevuta.equals("fine")){
                    outversoClient.writeBytes(stringaRicevuta+"server in chiusura"+'\n');
                    System.out.println("chiusura server: " + stringaRicevuta);
                
                    break;
                }
                else if(stringaRicevuta.equals("spegni")){
                    System.out.println("OK SPENGO TUTO");
                    mio.spegnimento();}
                else{
                    outversoClient.writeBytes(stringaRicevuta+" bravo"+'\n');
                    System.out.println("ricevuta " + stringaRicevuta);
                }
            }
            outversoClient.close();
            indalClient.close();
            client.close();


        } catch (Exception e) {
        }
        
    }

}
