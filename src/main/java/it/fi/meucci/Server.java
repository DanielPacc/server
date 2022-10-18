package it.fi.meucci;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;


public class Server {
    ServerSocket server; 
    public Server(){
        try{
        server = new ServerSocket(6788);
        }
        catch(Exception e){}

    }
    //BufferedReader indalClient = new BufferedReader(new InputStreamReader(getInputStream()));

    ArrayList <DThread> listaT = new ArrayList();
    public void attendi() {
        try {
           
            for(;;) { 
                System.out.println("1, server in attesa");
                Socket socket = server.accept();
                System.out.println("2, server partito" + socket);
                DThread ST = new DThread(socket, this);
                
                listaT.add(ST);
                ST.start();
            }
            

        } catch (Exception e) {
        }
    }

    public void spegnimento() throws Exception{
        for (int i=0; i<listaT.size(); i++){
            listaT.get(i).client.close();
            listaT.get(i).outversoClient.close();
            listaT.get(i).indalClient.close();
        }
        server.close();
        System.exit(1);
    }
}
