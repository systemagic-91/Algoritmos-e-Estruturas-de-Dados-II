public class Hash{

    private Personagem hash [];
    private int index_reserva;
    private int tamanho;
    private int reserva;
 
    private int cmp;
    private int mov;
 
    // ------------------------------------- Construtores
    
    public Hash(){
       this(21, 9);
    }
 
    public Hash(int tam, int res){
 
       tamanho = tam;
       reserva = res;
       index_reserva = tam;
 
       hash = new Personagem[tamanho + reserva];
 
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
     * Insere personagem na Tabela
     * @param e - elemento do No
     */
    public void inserir(Personagem e){
 
       int i = hash(e.getAltura());
       
       if(hash[i] == null){
 
          hash[i] = e;
 
       } else if (index_reserva < hash.length){
          
          hash[index_reserva] = e;
          index_reserva++;
 
       }
    }
 
 
    /**
     * Pesquisa elemento na tabela.
     * @param e - elemento
     * @return verdadeiro se o elemento existir
     */
    public boolean pesquisar(String e){
 
       boolean resp = false;
       int end = (tamanho + reserva);
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
 
    public void mostrar(){
 
       MyIO.println("------------------------- TABELA\n");
       for(int i = 0 ; i < tamanho ; i++){
 
          if(hash[i] != null){
             hash[i].imprimir();
          } else {
             MyIO.println("Posicao [" + i + "] vazia");
          }
       }
 
       MyIO.println("\n------------------------- RESERVA\n");
 
       for(int i = tamanho ; i < hash.length ; i++){
 
          if(hash[i] != null){
             hash[i].imprimir();
          } else {
             MyIO.println("Posicao [" + i + "] vazia");
          }      
       }
    }
}