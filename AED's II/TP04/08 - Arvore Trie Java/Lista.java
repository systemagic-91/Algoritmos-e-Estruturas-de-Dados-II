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
    public Celula getPrimeiro(){ return primeiro; }
 
    // ------------------------------------- Insercao/Remocao
 
 
    /**
     * Insere personagem no fim da Lista.
     * @param e - elemento a inserir
     */
    public void inserirFim(String e){
 
       ultimo.prox = new Celula(e);
       ultimo = ultimo.prox;
 
       n++;
    }
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostra os elementos da lista.
     */
    public void mostrarLista(){
 
       Celula i = primeiro.prox;
       for(int a = 0 ; i != null ; i = i.prox, a++){
          MyIO.println("["+ a +"] - " + i.elemento);
       }
    }  
}