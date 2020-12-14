public class ReHash{

    private Personagem hash [];
    private int tamanho;
 
    private int cmp;
    private int mov;
 
    // ------------------------------------- Construtores
    
    public ReHash(){
       this(25);
    }
 
    public ReHash(int tam){
 
       tamanho = tam;
 
       hash = new Personagem[tamanho];
 
       cmp = 0;
       mov = 0;
    }
 
    // ------------------------------------- getters
 
    public int getTamanho(){ return tamanho; }
    public int getComparacoes(){ return cmp; }
    public int getMovimentacoes(){ return mov; }
 
    // ------------------------------------- Insercao
  
    /**
     * Funcao de transformacao.
     * @param e - elemento
     */
    private int hash(int e){
       return e % tamanho;
    }
   
    /**
     * Funcao de transformacao rehash.
     * @param e - elemento
     */
    private int reHash(int e){
       return ++e % tamanho;
    }
 
    /**
     * Insere personagem na Tabela
     * @param e - elemento do No
     */
    public void inserir(Personagem e){
 
       int i = hash(e.getAltura());
       
       if(hash[i] == null){
          hash[i] = e;
       } else {
 
          i = reHash(e.getAltura());
          
          if (hash[i] == null){            
             hash[i] = e;
          } 
       }
    }
 
 
    /**
     * Pesquisa elemento na tabela.
     * @param e - elemento
     * @return verdadeiro se o elemento existir
     */
    public boolean pesquisar(String e){
 
       boolean resp = false;
       int end = tamanho;
       int i = 0;
       
       while(resp == false && i < end){
 
          cmp++;
 
          if(hash[i] != null && hash[i].getNome().compareTo(e) == 0){
             resp = true;
          }
 
          i++;
       }
 
       return resp;
    }
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostra elementos da tabela.
     */
    public void mostrar(){
 
       for(int i = 0 ; i < tamanho ; i++){
 
          if(hash[i] != null){
             hash[i].imprimir();
          } else {
             MyIO.println("null");
          }
       }
 
    }
}