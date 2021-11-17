
package server;

import java.util.HashMap;

public class Contenitore {
    HashMap<String,ServerThread> m = new HashMap<String, ServerThread>();

    public Contenitore(){

    }

    public void aggiungi(String nome, ServerThread server){
        m.put(nome,server);
        for(String i:m.keySet()){
            m.get(i).invia(lista());
        }
    }

    /**
     * 
     * @param utente mittente messaggio
     * @param messaggio
     */
    public void messaggioPubblico(String utente,String messaggio){
        for(String i:m.keySet()){
            if(i.equals(utente))
            continue;
            m.get(i).invia(utente + messaggio);
        }
    }

   
    /**
     * 
     * @param utente mittente
     * @param dest dest
     * @param messaggio
     */
    public void messaggioPrivato(String utente, String dest, String messaggio){
        for(String i:m.keySet()){
            if(i.equals("A:"+dest)) {
                m.get(i).invia("(P)" + utente + messaggio);
            }
        }
    }


    public String lista(){
    String lista = "lista:";
    for(String i:m.keySet()){
        lista+=i + ";";
    }
    return lista;
    }
}
