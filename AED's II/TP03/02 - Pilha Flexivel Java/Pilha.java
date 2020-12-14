public class Pilha{

    private Celula topo;
    private int n;
 
    // ------------------------------------- Construtores
 
    public Pilha(){
 
       topo = null;
       n = 0;
    }
 
    // ------------------------------------- getters
 
    public int getTamanho(){ return n; }
 
    // ------------------------------------- Insercao/Remocao
 
    /**
     * Insere personagem no fim da Pilha.
     * @param e - elemento a inserir
     */
    public void inserirFim(Personagem e){
 
       Celula tmp = new Celula(e);
       tmp.prox = topo;
       topo = tmp;
       tmp = null;
 
       n++;
    }
 
    /**
     * Remove personagem do fim da Pilha.
     * @return personagem removido
     */
    public Personagem removerFim(){
 
       Personagem removido = null;
       
       if(topo == null){
           MyIO.println("ERRO REMOVER FIM - Lista vazia!");
       } else {
 
          removido = topo.elemento;
          Celula tmp = topo;
          topo = topo.prox;
          tmp.prox = null;
          tmp = null;
 
          n--;
       }
 
       return removido;
    }
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostra os elementos da lista.
     */
    public void mostrar(){
 
       Celula i = topo;
       for(int a = 0 ; i != null ; i = i.prox, a++){
          MyIO.print("["+ a +"] ");
          i.elemento.imprimir();
       }
    }
 
    /**
     * Mostrar recursivo.
     */
    public void mostrarR(){
       mostrarR(topo, (getTamanho() - 1));
    }
 
    /**
     * Mostra recursivamente os elementos da pilha na ordem 
     * em que foram inseridos na pilha.
     * @param i - referencia para o topo da pilha.
     * @param a - inteiro tamanho da pilha.
     */
    public void mostrarR(Celula i, int a){
 
       if(i != null){
          mostrarR(i.prox, (a - 1));
          MyIO.print("[" + a + "] ");
          i.elemento.imprimir();
       }
    }
 
    /**
     * Mostra os elementos da pilha na ordem em que foram
     * inseridos na pilha.
     */
    public void mostrarI(){
 
       int a = 0;
       Celula ultimo = topo;
       Celula penultimo = topo;
 
       while (ultimo.prox != null){
          ultimo = ultimo.prox;
       }
 
       while (ultimo != topo){
 
          while(penultimo.prox != ultimo){
             penultimo = penultimo.prox;
          }
 
          if(penultimo.prox == ultimo){
 
             MyIO.print("[" + a + "] ");
             ultimo.elemento.imprimir();
 
             ultimo = penultimo;
             penultimo = topo;
             a++;
          }
 
          if(ultimo == topo){
 
             MyIO.print("[" + a + "] ");
             ultimo.elemento.imprimir();
          }
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
       int p;
 
       switch (comando){
 
          case "I":
 
             t = new Personagem();
             t.ler(s.substring(2));
             inserirFim(t);
             break;
 
          case "R":
 
             t = removerFim();
             MyIO.println("(R) " + t.getNome());
             break;
       }
    } 
 }