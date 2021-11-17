package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String nomeRicevuto=null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    //Vector<String> nomeUtente = new Vector<String>();
    Contenitore c= new Contenitore();
    //nomeUtente.add(tastiera.readLine());
    String nome;

    public ServerThread(Socket socket, ServerSocket server,Contenitore c) {
        this.c = c;
        this.client = socket;
        this.server = server;
    }

    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        nome = inDalClient.readLine();
        if(nome.contains("A:")){
            c.aggiungi(nome, this);
            invia("scelta username completata");
        }
        for (;;) {
            stringaRicevuta = inDalClient.readLine();


            if (stringaRicevuta == null || stringaRicevuta.equals("FINE") || stringaRicevuta.equals("STOP")) {
                outVersoClient.writeBytes(stringaRicevuta + "(=>server in chiusura...)" + '\n');
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;
            }  else if (stringaRicevuta.startsWith("P:")) { //P:dest:messaggio
                int indexEndOfName = stringaRicevuta.indexOf(':', 3);
                String nomeDest = stringaRicevuta.substring(2, indexEndOfName);
                System.out.println("Destinatario " + nomeDest);
                // messaggio privato
                //System.out.println(stringaRicevuta.split(":", 3)[2]);
                c.messaggioPrivato(nome, nomeDest, " : "+ stringaRicevuta.split(":", 3)[2]);
            } else if  (stringaRicevuta.startsWith("@:")) {
                // messaggio pubblico
                c.messaggioPubblico(nome," : "+ stringaRicevuta);

            }
            
            
            /*else {
                if(stringaRicevuta.contains("A:")){
                nomeUtente.add(stringaRicevuta);
                }
                System.out.println("l'utente inserito è: "+ nomeUtente);
                outVersoClient.writeBytes("l'elenco dei client connessi è : "+nomeUtente);
                outVersoClient.writeBytes(stringaRicevuta + "(ricevuta e ritrasmessa)" + '\n');
                System.out.println("Echo sul server:" + stringaRicevuta);
            }*/
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura socket" + client);
        client.close();
        if (stringaRicevuta.equals("STOP")) {
            server.close();
        }
    }

    public void invia(String messaggio){
        try {
            outVersoClient.writeBytes(messaggio + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chiudi(){
        
    }

}