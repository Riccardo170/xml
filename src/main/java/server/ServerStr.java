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
    int numero = new java.util.Random().nextInt(100) + 1;

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
                outVersoClient.writeBytes("Indovina il numero" + '\n');
                numero1 = inDalClient.readLine();
                System.out.println("numero scelto : " + numero1 + " turno: " + turno);
                int Numero1 = Integer.parseInt(numero1);

                /**
                 * controllo tra il numero generato random e il numero inserito dall'utente
                 */

                if (numero == Numero1) {
                    turno++;
                    outVersoClient.writeBytes("Hai indovinato il numero in :" + turno + " turni" + '\n');

                } else if (numero > Numero1) {
                    outVersoClient.writeBytes("Numero troppo piccolo" + '\n');
                    turno++;

                } else {
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
