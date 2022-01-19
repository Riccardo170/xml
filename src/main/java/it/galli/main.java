package it.galli;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class main 
{
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException {
       File file = new File("src/main/java/it/galli/classe.xml");//si indica il percorso del file
         XmlMapper xmlMapper = new XmlMapper();
         root value = xmlMapper.readValue(file, root.class);//file per non scrivere tutti le linee di codice,root può variare in base al tag totale
         System.out.println("la classe " + value.getClasse() + " " + value.getSpecializzazione() +" si trova nell'aula " +
         value.getAula().getNome() + " ed è composta dai seguenti studenti:");//si fa la stampa
         
         for(int i=0;i<value.getStudenti().size();i++)
         {
         System.out.println("-" + value.getStudenti().get(i).getCognome());
         }

}
}