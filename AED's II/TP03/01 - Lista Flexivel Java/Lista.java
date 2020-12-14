public class Lista{

    private Celula primeiro;
    private Celula ultimo;
 
    private int n;
 
    // ------------------------------------- Construtores
 
    public Lista(){
 
       primeiro = new Celula();
       ultimo = primeiro;
       n = 0;
    }
 
    // ------------------------------------- getters
 
    public int getTamanho(){ return n; }
 
    // ------------------------------------- Insercao/Remocao
 
    /**
     * Insere personagem no inicio da Lista
     * @param e - elemento da celula
     */
    public void inserirInicio(Personagem e){
 
       Celula tmp = new Celula(e);
       tmp.prox = primeiro.prox;
       primeiro.prox = tmp;
 
       if(primeiro == ultimo){
          ultimo = tmp;
       }
 
       tmp = null;
       n++;
    }
 
    /**
     * Remove personagem do inicio da Lista.
     * @return personagem removido
     */
    public Personagem removerInicio(){
 
       Personagem removido = null;
 
       if(primeiro == ultimo){
          MyIO.println("ERRO REMOVER INICIO - Lista vazia!");
       } else {
 
          removido = primeiro.prox.elemento;
          Celula tmp = primeiro;
          primeiro = primeiro.prox;
          tmp = tmp.prox = null;
          primeiro.elemento = null;
          n--;
       }
 
       return removido;
    }
 
    /**
     * Insere personagem no fim da Lista.
     * @param e - elemento a inserir
     */
    public void inserirFim(Personagem e){
 
       ultimo.prox = new Celula(e);
       ultimo = ultimo.prox;
 
       n++;
    }
 
    /**
     * Remove personagem do fim da Lista.
     * @return personagem removido
     */
    public Personagem removerFim(){
 
       Personagem removido = null;
       
       if(primeiro == ultimo){
           MyIO.println("ERRO REMOVER FIM - Lista vazia!");
       } else {
 
          Celula i;
 
          for(i = primeiro; i.prox != ultimo ; i = i.prox);
          removido = ultimo.elemento;
          ultimo = i;
          i = ultimo.prox = null;
 
          n--;
       }
 
       return removido;
    }
 
    /**
     * Insere um elemento em uma posicao da Lista.
     * @param pos - posicao a inserir
     * @param e - elemento a inserir
     */
    public void inserir(int pos, Personagem e){
 
       int tam = getTamanho();
 
       if(0 > pos || pos > tam){
           MyIO.println("ERRO INSERIR - Posicao invalida!");
       } else if(pos == 0){
          inserirInicio(e);
       } else if (pos == tam){
          inserirFim(e);
       } else {
 
          int a;
          Celula i = primeiro;
 
          for(a = 0 ; a < pos ; a++, i = i.prox);
          Celula tmp = new Celula(e);
          tmp.prox = i.prox;
          i.prox = tmp;
          i = tmp = null;
          
          n++;
       }
    }
 
    public Personagem remover(int pos){
 
       int tam = getTamanho();
       Personagem removido = null;
 
       if(0 > pos || pos > tam){
            MyIO.println("ERRO REMOVER - Posicao invalida!");
       } else if (pos == 0){
          removido = removerInicio();
       } else if (pos == tam){
          removido = removerFim();
       } else {
 
          int a;
          Celula i = primeiro;
 
          for(a = 0 ; a < pos ; a++, i = i.prox);
          Celula tmp = i.prox;
          removido = tmp.elemento;
          i.prox = tmp.prox;
          tmp.prox = null;
          i = tmp = null;
          
          n--;
       }
 
       return removido;
    }
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostra os elementos da lista.
     */
    public void mostrar(){
 
       Celula i = primeiro.prox;
       for(int a = 0 ; i != null ; i = i.prox, a++){
          MyIO.print("["+ a +"] ");
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
 
       String comando = s.substring(0,2);
       Personagem t;
       int p;
 
       switch (comando){
 
          case "II":
 
             t = new Personagem();
             t.ler(s.substring(3));
             inserirInicio(t);
             break;
 
          case "I*":
 
             p = Integer.parseInt(s.substring(3,5));
             t = new Personagem();
             t.ler(s.substring(6));
             inserir(p, t);
             break;
 
          case "IF":
 
             t = new Personagem();
             t.ler(s.substring(3));
             inserirFim(t);
             break;
 
          case "RI":
 
             t = removerInicio();
             MyIO.println("(R) " + t.getNome());
             break;
 
          case "R*":
 
             p = Integer.parseInt(s.substring(3,5));
             t = remover(p);
             MyIO.println("(R) " + t.getNome());
             break;
 
          case "RF":
 
             t = removerFim();
             MyIO.println("(R) " + t.getNome());
             break;
       }
    }
 
 }
