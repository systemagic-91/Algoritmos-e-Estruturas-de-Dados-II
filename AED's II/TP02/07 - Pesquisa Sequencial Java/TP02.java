/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP02 - Questao 07 - Pesquisa Sequencial em Java
 *  
 * @author - Rayane Paiva Reginaldo
 * @version - 0.1
 */


import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter; 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.NegativeArraySizeException;
import java.lang.ArrayIndexOutOfBoundsException;

public class TP02{

   public static boolean isFim(String s){
      return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   } 

   public static void main(String [] args){

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

         String[] comandos = new String[1000];
         int nComandos = 0;


         do {

            String s = MyIO.readLine();
            isoBytes = s.getBytes("ISO-8859-1");

            comandos[nComandos] = new String (isoBytes, "UTF-8");

         } while (isFim(comandos[nComandos++]) == false);

         nComandos--;


         File file = new File ("matricula_sequencial.txt");

         if(!file.exists()){
            file.createNewFile();
         }

         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);      

         for (int i = 0; i < nComandos ; i++){

            long inicio = System.nanoTime();
            boolean b = l.pesquisaSequencial(comandos[i]);
            long fim = System.nanoTime();

            if (b == true){
               System.out.println("SIM");
            } else {
               System.out.println("NAO");
            }

            String log = ("635383" +  
                  String.format("\t%06f", ((fim - inicio) / 1000000.1)) + "s\t" + 
                  l.getComparacoes() + "\n");

            bw.write(log);
         }

         bw.close();
   
      } catch (UnsupportedEncodingException e0) {
         MyIO.println(e0.toString());
      } catch (IOException e1) {
         MyIO.println(e1.toString());
      } finally{     
      }
   }
}