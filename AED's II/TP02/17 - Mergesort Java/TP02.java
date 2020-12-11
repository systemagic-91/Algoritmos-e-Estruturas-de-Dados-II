/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP02 - Questao 12 - MergeSort em Java
 *  
 * @author - Rayane Paiva Reginaldo
 * @version - 0.1
 */

import java.io.*;

import java.lang.NegativeArraySizeException;
import java.lang.ArrayIndexOutOfBoundsException;

public class TP02{

   public static boolean isFim(String s){
      return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   } 

   public static void main(String [] args){

      Lista l;

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
         l = new Lista(numEntrada);

         for(int i = 0 ; i < numEntrada ; i++){

            Personagem p = new Personagem();
            p.ler(entrada[i]);
            l.inserirFim(p);
         }

         File file = new File ("matricula_mergesort.txt");

         if(!file.exists()){
            file.createNewFile();
         }

         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);      


         long inicio = System.nanoTime();
         l.mergeSort();
         long fim = System.nanoTime();


         String log = ("635383" + "\tC(n): " +
               l.getComparacoes() + "\tM(n): " +
               l.getMovimentacoes() +
               String.format("\t%06f", ((fim - inicio) / 1000000.1)) + "s");


         bw.write(log);
         bw.close();

         l.mostrar();

      } catch (UnsupportedEncodingException e0) {
         MyIO.println(e0.toString());
      } catch (IOException e1) {
         MyIO.println(e1.toString());
      } finally{
      }

   }
}
