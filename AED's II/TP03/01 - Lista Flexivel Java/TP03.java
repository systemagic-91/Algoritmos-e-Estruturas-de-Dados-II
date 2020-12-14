/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP03 - Questao 01 - Lista Dinamica em Java
 *  
 * @author - Rayane Paiva Reginaldo
 * @version - 0.1
 */

import java.io.*;

// ------------------------------------------------------------------------------------ MAIN

public class TP03{

   public static boolean isFim(String s){
      return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');      
   }

   public static void main (String [] args){

      Lista l = new Lista();

      String[] entrada = new String [1000];
      int numEntrada = 0;
      byte [] isoBytes;

      try {
      
         do {

            String s = MyIO.readLine();
            isoBytes = s.getBytes("ISO-8859-1");
            entrada[numEntrada] = new String (isoBytes, "UTF-8");
     
         } while (isFim(entrada[numEntrada++]) == false);

      } catch (UnsupportedEncodingException e) {
         MyIO.println(e.toString());
      } finally{
      }

      numEntrada--;

      for(int i = 0 ; i < numEntrada ; i++){

         Personagem p = new Personagem();
         p.ler(entrada[i]);
         l.inserirFim(p);
      }

      int nComandos;
      nComandos = Integer.parseInt(MyIO.readLine());

      String [] comandos = new String[nComandos];
      int j = 0;

      try {

         while (nComandos > 0){

            String s = MyIO.readLine();
            isoBytes = s.getBytes("ISO-8859-1");

            comandos[j] = new String (isoBytes, "UTF-8");
            nComandos--;
            j++;

         }

      } catch (UnsupportedEncodingException e) {
         MyIO.println(e.toString());
      } finally{
      }

      for (int i = 0; i < comandos.length ; i++){

         l.tarefa(comandos[i]);
      }
      
      l.mostrar();
   }   
}
