/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP03 - Questao 01 - Lista Dupla Dinamica em Java
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

         numEntrada--;

         for(int i = 0 ; i < numEntrada ; i++){

            Personagem p = new Personagem();
            p.ler(entrada[i]);
            l.inserirFim(p);
         }

         File file = new File ("matricula_quicksort_listaDupla_java.txt");

         if(!file.exists()){
            file.createNewFile();
         }

         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);    

         long inicio = System.nanoTime();
         l.quickSort();
         l.desempateQuick();
         long fim = System.nanoTime();

         String log = ("635383" + "\tC(n): " +
               l.getComparacoes() + "\tM(n): " +
               l.getMovimentacoes() +
               String.format("\t%06f", ((fim - inicio) / 1000000.1)) + "s");

         bw.write(log);
         bw.close();

      } catch (UnsupportedEncodingException e) {
         MyIO.println(e.toString());
      } catch (IOException e) {
         MyIO.println(e.toString());
      } finally{
      }

      l.mostrar();
   }   
}