/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP04 - Questao 02 - Arvore de Arvores
 *  
 * @author - Rayane Paiva Reginaldo
 * @version - 0.1
 */

import java.io.*;

// ------------------------------------------------------------------------------------ MAIN

public class TP04{

   public static boolean isFim(String s){
      return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');      
   }

   public static void main (String [] args){

      ArvoreB l = new ArvoreB();

      int [] ordem = {7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14};

      for(int i = 0 ; i < ordem.length ; i++){
         l.inserir(ordem[i]);
      }

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

      } catch (UnsupportedEncodingException e) {
         MyIO.println(e.toString());
      } finally{
      }

      for(int i = 0 ; i < numEntrada ; i++){

         Personagem p = new Personagem();
         p.ler(entrada[i]);
         l.inserir3D(p);
      }


      String [] pesquisa = new String[1000];
      int numPesquisa = 0;

      try {
      
         do {

            String s = MyIO.readLine();
            isoBytes = s.getBytes("ISO-8859-1");
            pesquisa[numPesquisa] = new String (isoBytes, "UTF-8");
     
         } while (isFim(pesquisa[numPesquisa++]) == false);

         numPesquisa--;


         File file = new File ("matricula_arvoreArvore.txt");

         if(!file.exists()){
            file.createNewFile();
         }

         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);    

         long inicio = System.nanoTime();
         for(int i = 0 ; i < numPesquisa ; i++){

            MyIO.print(pesquisa[i] + " ");

            if(l.pesquisar(pesquisa[i])){            
               MyIO.println("SIM");
            } else {
               MyIO.println("N" + (char)(195) + "O");
            }         
         }

         long fim = System.nanoTime();

         String log = ("635383" + "\tC(n): " +
               l.getComparacoes() + "\t" +                 
               String.format("\t%06f", ((fim - inicio) / 1000000.1)) + "s");

         bw.write(log);
         bw.close();

      } catch (IOException e) {
         MyIO.println(e.toString());
      } finally{
      }


   }   
}