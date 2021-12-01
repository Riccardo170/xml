package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr
{
    ServerSocket server = null;
    Socket client = null;
    String operazione = null;
    String numero1=null;
    String numero2=null;
    String stringaricevuta=null;
    String stringaModificata = null;
    int risultato=0;
    String Risultato=null;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;


public Socket attendi()
{
    try
    {
        System.out.println(" Server partito in esecuzione ...");
        server = new ServerSocket(6789);
        client = server.accept();
        server.close();
        inDalClient=new BufferedReader (new InputStreamReader(client.getInputStream()));
        outVersoClient= new DataOutputStream ( client.getOutputStream());
        outVersoClient.writeBytes("Connessione effettuata!"+'\n');
    }

    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Errore durante l'istanza del server!");
        System.exit(1);
    }
    return client;

}

public void comunica()
{
    try
    {
        for(;;){
             outVersoClient.writeBytes("Indovina il numero"+'\n');
             numero1 = inDalClient.readLine();
             System.out.println ("numero 1 : " +numero1);

            int Numero1=Integer.parseInt(numero1);
             
        }
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Errore durante la comunicazione col server!");
        System.exit(1);
    }
}

public static void main(String args[]){
    ServerStr servente = new ServerStr();
    servente.attendi();
    servente.comunica();
}
}

