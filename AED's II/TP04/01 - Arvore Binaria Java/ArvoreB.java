public class ArvoreB{

    private No raiz;
    private int n;
    private int cmp;   
 
    // ------------------------------------- Construtores
 
    public ArvoreB(){
 
       raiz = null;
       n = 0;
       cmp = 0;
    }
 
    // ------------------------------------- getters
 
    public No getRaiz(){ return raiz; }
    public int getComparacoes(){ return cmp; }
    public int getTamanho(){ return n; }
 
    // ------------------------------------- Insercao
 
    /**
     * Insere personagem na Arvore
     * @param e - elemento do No
     */
    public void inserir(Personagem e){
       raiz = inserir(e, raiz);      
    }
 
    private No inserir(Personagem e, No i){
 
       if(i == null) {         
          i = new No(e); n++;         
       } else if(e.getNome().compareTo(i.elemento.getNome()) < 0) {
          i.esq = inserir(e, i.esq);
       } else if(e.getNome().compareTo(i.elemento.getNome()) > 0) {
          i.dir = inserir(e, i.dir);
       } else {
          MyIO.println("Erro!");
       }
 
       return i;
    }   
 
    // ------------------------------------- Pesquisa
 
    /**
     * Chamada recursiva da Pesquisa de elementos Arvore.
     * @param e - elemento a buscar
     * @return verdadeiro se o elemento existir
     */
    public boolean pesquisar(String e){
       MyIO.print("raiz ");
       return pesquisar(e, raiz);
    }
 
    /**
     * Pesquisa um elemento na arvore de dados.
     * @param e - elemento a buscar.
     * @param i - raiz da arvore
     * @return verdadeiro caso o elemento exista.
     */
    private boolean pesquisar(String e, No i){
 
       boolean resp = true;
 
       if(i == null){
          resp = false;
       } else if(e.compareTo(i.elemento.getNome()) == 0){
          cmp++;
          resp = true;
       } else if(e.compareTo(i.elemento.getNome()) < 0){
          cmp++;
          MyIO.print("esq ");
          resp = pesquisar(e, i.esq);
       } else {
          MyIO.print("dir ");
          resp = pesquisar(e, i.dir);
       }
 
       return resp;
    }
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostra os elementos da Arvore em ordem.
     */
    public void mostrarCentral(){
       mostrarCentral(raiz);
    }
 
    private void mostrarCentral(No i){
 
       if(i != null){
          mostrarCentral(i.esq);
          i.elemento.imprimir();
          mostrarCentral(i.dir);
       }
    }  
 }