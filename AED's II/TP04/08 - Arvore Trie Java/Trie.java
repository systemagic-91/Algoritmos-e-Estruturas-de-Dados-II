public class Trie{

    private No raiz;
 
    private Lista lista;
 
    private int cmp;
    private int mov;
 
    // ------------------------------------- Construtores
    
    public Trie(){
       
       raiz = new No();
       lista = new Lista();
 
       cmp = 0;
       mov = 0;
    }
 
 
    // ------------------------------------- getters
 
    public No getRaiz(){ return raiz; }
    public int getComparacoes(){ return cmp; }
    public int getMovimentacoes(){ return mov; }
 
    // ------------------------------------- Insercao
 
 
    /**
     * Funcao de transformacao
     * @param c - caractere
     * @return inteiro
     */
    private int hash(char c){
       return (int)(c);
    }
 
    /**
     * Insere Palavra caracter a caracter na arvore.
     * @param s - palavra
     */
    public void inserir (String s){
       inserir(s, raiz, 0);
    }
 
    /**
     * Insere os caracteres de uma palavra em nos da
     * arvore.
     * @param s - palavra
     * @param i - referencia
     * @param indice - inteiro
     */
    private void inserir(String s, No i, int indice){
 
       int pos = hash(s.charAt(indice));
 
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
 
    // ------------------------------------- Pesquisar
 
    /**
     * Pesquisa palavra na arvore
     * @param s - palavra
     * @return verdadeiro caso a palavra exista
     */
    public boolean pesquisar(String s){
       return pesquisar(s, raiz, 0);
    }
 
    /**
     * Pesquisa palavra na arvore
     * @param s - palavra
     * @param i - referencia
     * @param indice - inteiro
     * @return verdadeiro caso a palavra exita
     */
    private boolean pesquisar(String s, No i, int indice){
 
       boolean resp = false;
 
       int pos = hash(s.charAt(indice));
 
       if(i.prox[pos] == null){
          cmp++;
          resp = false;
       } else if (indice == s.length() - 1){
          cmp++;         
          resp = (i.prox[pos].isFolha == true) ? true : false;
       } else if (indice < s.length() - 1){         
          cmp++;
          resp = pesquisar(s, i.prox[pos], indice + 1);
       } else {
          MyIO.println("Erro");
       }
 
       return resp;
    }
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostras palavras na arvore trie.
     */
    public void mostrar(){
       mostrar("", raiz);
    }
 
    /**
     * Mostra palavras na arvore trie.
     * @param s - string
     * @param i - referencia
     */
    private void mostrar(String s, No i){
 
       if(i.isFolha == true){
          MyIO.println(s + i.elemento);
       } else {
 
          for (int a = 0 ; a < i.prox.length ; a++){
 
             if(i.prox[a] != null){               
                mostrar((s + i.elemento), i.prox[a]);
             }
          }
       }
    }
 
    // ------------------------------------- Mesclar
 
    /**
     * Mescla duas Arvores Trie
     * @param R1 - referencia
     * @param R2 - referencia
     */
    public void mesclar(No R1, No R2){
 
       toLista(R1);
       toLista(R2);
 
       Celula i = lista.getPrimeiro().prox;
 
       for( ; i != null ; i = i.prox)
          inserir(i.elemento.trim());      
    }
 
    /**
     * Insere palavras de uma arvore em uma lista
     * @param i - raiz
     */
    private void toLista(No i){
       toLista("", i);
    }
 
    /**
     * Insere palavras de uma arvore em uma lista
     * @param s - string
     * @param i - referencia
     */
    private void toLista(String s, No i){
 
       if(i.isFolha == true){
          lista.inserirFim(s + i.elemento);
       } else {
 
          for (int a = 0 ; a < i.prox.length ; a++){
 
             if(i.prox[a] != null){
                toLista((s + i.elemento), i.prox[a]);
             }
          }
       }
    } 
}