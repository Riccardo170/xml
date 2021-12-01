package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String numero1 = null;
    String stringaricevuta = null;
    String stringaModificata = null;
    int risultato = 0;
    int turno = 0;
    String Risultato = null;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    int numero = new java.util.Random().nextInt(100) + 1;//creazione di un numero random

    public Socket attendi() {
        try {
            System.out.println(" Server partito in esecuzione ...");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
            outVersoClient.writeBytes("Connessione effettuata!" + '\n');

        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;

    }

    public void comunica() {

        try {

            for (;;) {
                // System.out.println(numero);
                outVersoClient.writeBytes("Indovina il numero" + '\n');//mando il messaggio al client
                numero1 = inDalClient.readLine();
                System.out.println("numero scelto : " + numero1 + " turno: " + turno);//stampa nel server il numero giocato dal client e il turno
                int Numero1 = Integer.parseInt(numero1);

                /**
                 * controllo tra il numero generato random e il numero inserito dall'utente
                 */

                if (numero == Numero1) {//se numero uguale
                    turno++;
                    outVersoClient.writeBytes("Hai indovinato il numero in :" + turno + " turni" + '\n');

                    outVersoClient.writeBytes("Vuoi continuare a giocare SI o NO?"+'\n');//chiedo al client se vuole giocare ancora
                    stringaricevuta=inDalClient.readLine();//leggo cosa mi risponde
                    if(stringaricevuta.equals("NO")){//se mi risponde nNO termina il gioco se SI ricomincia
                        outVersoClient.writeBytes("Fine!"+'\n');
                        client.close();
                        break;
                    }

                } else if (numero > Numero1) { //se numero minore
                    outVersoClient.writeBytes("Numero troppo piccolo" + '\n');
                    turno++;

                } else { //se numero maggiore
                    outVersoClient.writeBytes("Numero troppo grande" + '\n');
                    turno++;
                }
                

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }

    /**
     * main server
     */

    public static void main(String args[]) {
        ServerStr servente = new ServerStr();
        servente.attendi();
        servente.comunica();
    }
}
