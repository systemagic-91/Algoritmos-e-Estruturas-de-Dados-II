/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP03 - Questao 05 - Matriz Dinamica em Java
 *  
 * @author - Rayane Paiva Reginaldo
 * @version - 0.1
 */

import java.io.*;

public class TP03{

   public static boolean isFim(String s){
      return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');      
   }

   public static void main (String [] args){

      int casos = MyIO.readInt();
 
      String [] a;
      int lin, col; 
      String str = "";

      while(casos > 0){

         // --------------------------------- m1

         lin = MyIO.readInt();
         col = MyIO.readInt();

         Matriz m1 = new Matriz(lin, col);

         while(lin > 0){

            str += MyIO.readLine() + " ";

            lin--;
         }

         a = str.split(" ");
         m1.preencher(a);
         m1.diagP();
         m1.diagS();

         str = "";

         // --------------------------------- m2

         lin = MyIO.readInt();
         col = MyIO.readInt();

         Matriz m2 = new Matriz(lin, col);

         while(lin > 0){

            str += MyIO.readLine() + " ";

            lin--;
         }

         a = str.split(" ");
         m2.preencher(a);

         Matriz m3; 

         m3 = m1.soma(m2);
         m3.mostrar();
         m3 = m1.multiplica(m2);
         m3.mostrar();

         str = "";

         // --------------------------------- end

         casos--;
      }
  }   
}