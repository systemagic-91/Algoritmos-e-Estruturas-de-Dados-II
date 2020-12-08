/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP02 - Questao 01 - Classe em Java
 *  
 * @author - Rayane Paiva Reginaldo
 * @version - 0.1
 */

import java.io.UnsupportedEncodingException;

public class TP02{

   public static boolean isFim(String s){
      return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   } 

   public static void main(String [] args){

      Personagem p = new Personagem();

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
         p.ler(entrada[i]);
         p.imprimir();
      }
   }
}