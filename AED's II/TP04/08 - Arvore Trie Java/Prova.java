class Trie{

   private No raiz;

   public void inserir (String s){
      inserir(s, raiz);
   }

   private void inserir(String s, No i, int indice){

      if (i.prox[pos] == null){

         i.prox[pos] = new No(s.charAt(indice));

         if(indice == s.length() - 1){

            i.prox[pos].isFolha = true;

         } else {

            inserir(s, i.prox[pos], indice + 1);

         }

      } else if(i.prox[pos].isFolha == false && indice < s.length() - 1){
         
         inserir(s, i.prox[pos], indice + 1);

      } else {
         MyIO.println("Erro!");
      }
   }
}


class No{

   //public int elemento;
   
   public No esq;
   public No dir;

   public NoBinaria raiz;
   
   public No(int e) {

      elemento = e;
      esq = null;
      dir = null;
      raiz = null;
   }
}

class NoBinaria{

   public int elemento;
   public NoBinaria esq;
   public NoBinaria dir;

   public NoBinaria(int e){

      elemento = e;
      esq = null;
      dir =  null;
   }
}
 
