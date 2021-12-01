package it.galli;

import java.io.*;
import java.net.*;


public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    /**
      * metodo stabilire la connessione con il server 
      */
    public Socket connetti(){
        System.out.println(" CLIENT partito in esecuzione ...");
        try
        {
            tastiera= new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket (nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            stringRicevutaDalServer=inDalServer.readLine();
            System.out.println("server: "+stringRicevutaDalServer);
        }
        catch (UnknownHostException a){
            System.err.println("Host sconosciuto");
        }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);
            }
            return miosocket;
     }

      /**
      * metodo per comunicare con il server
      */

     public void comunica(){
         try{
             for(;;){
                stringRicevutaDalServer = inDalServer.readLine();
                System.out.println("server 1: " + stringRicevutaDalServer);
                stringaUtente = tastiera.readLine();
                outVersoServer.writeBytes(stringaUtente + '\n');

                stringRicevutaDalServer = inDalServer.readLine();
                System.out.println("server 1: " + stringRicevutaDalServer);
             
             }
         }
         catch (Exception e)
         {
             System.out.println(e.getMessage());
             System.out.println("Errore durante la comunicazione col server!");
             System.exit(1);
         }
     }



     /**
     * main client
     */

     public static void main (String args[]){
        ClientStr cliente = new ClientStr();
        cliente.connetti();
        cliente.comunica();
    }


    }

