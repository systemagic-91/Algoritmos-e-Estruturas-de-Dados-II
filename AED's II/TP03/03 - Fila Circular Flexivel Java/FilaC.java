public class FilaC{

    private Celula primeiro;
    private Celula ultimo;
    private int n;
 
    // ------------------------------------- Construtores
    
    public FilaC(){
       
       primeiro = new Celula();
       ultimo = primeiro;
       ultimo.prox = primeiro;
 
       n = 0;
    }
 
    // ------------------------------------- getters
 
    public int getTamanho(){ return n; }
 
    // ------------------------------------- Insercao/Remocao
 
    /**
     * Insere personagem no fim da fila.
     * @param e - elemento a inserir
     */
    public void inserirFila(Personagem e){
 
       if(n == 5){
          removerFila();
       }
 
       Celula tmp = new Celula(e);
       tmp.prox = ultimo.prox;
       ultimo.prox = tmp;
       ultimo = ultimo.prox;;
       tmp = null;
 
       n++;
       
       double media = calcMedia();
 
       MyIO.println((int)(Math.round(media)));
    }
 
    /**
     * Remove personagem do fim da fila.
     * @return personagem removido
     */
    public Personagem removerFila(){
 
       Personagem removido = null;
       
       if(primeiro == ultimo){
 
          MyIO.println("ERRO REMOVER: Fila vazia!");
 
       } else {
 
          removido = primeiro.prox.elemento;
 
          Celula tmp = primeiro;
          primeiro = primeiro.prox;
          ultimo.prox = primeiro;
          tmp = tmp.prox = null;         
 
          n--;
       }      
 
       return removido;
    }
 
    /**
     * Calucula a media dos itens na fila.    
     * @return inteiro.
     */
    private double calcMedia(){
 
       double media = 0;
       
       Celula a = primeiro.prox;
 
       for(int i = 0 ; i < n ; i++ , a = a.prox){         
          media += a.elemento.getAltura();
       }
 
       return (media/n);
    }
 
 
    // ------------------------------------- Mostrar
    
    public void mostrar(){
 
       Celula i;
       
       for(i = primeiro.prox ; i != primeiro ; i = i.prox){
          i.elemento.imprimir();
       }
   }
 
    // ------------------------------------- Tarefa
 
   /**
     * Metodo para realizar insercoes/remocoes de acordo com
     * comando na entrada padrao
     * @param s - comando
     */
    public void tarefa(String s){
 
       MyIO.setCharset("UTF-8");
 
       String comando = s.substring(0,1);
       Personagem t;      
 
       switch (comando){
 
          case "I":
 
             t = new Personagem();
             t.ler(s.substring(2));
             inserirFila(t);
             break;
 
          case "R":
 
             t = removerFila();
             MyIO.println("(R) " + t.getNome());
             break;
       }
    }
}