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
    public void inserir(int e){
       raiz = inserir(e, raiz);      
    }
 
    private No inserir(int e, No i){
 
       if(i == null) {         
          i = new No(e);          
       } else if(e < i.elemento) {
          i.esq = inserir(e, i.esq);
       } else if(e > i.elemento) {
          i.dir = inserir(e, i.dir);
       } else {
          MyIO.println("Erro!");
       }
 
       return i;
    }   
 
    // ------------------------------------- Insercao 3D
 
    /**
     * Insere personagem na Arvore
     * @param e - elemento do No
     */
    public void inserir3D(Personagem e){
       inserir3D(e, (e.getAltura() % 15), raiz);      
    }
 
    /**
     * Busca o No em que o personagem sera inserido.
     */
    private void inserir3D(Personagem e, int mod, No i){
 
       if(i == null) {
         MyIO.println("Erro!");
       } else if(mod < i.elemento) {
          inserir3D(e, mod, i.esq);
       } else if(mod > i.elemento) {
          inserir3D(e, mod, i.dir);
       } else {
          i.raiz3D = inserir3D(e, i.raiz3D);
       }      
    }   
 
    /**
     * Insere o Personagem.
     */
    private No3D inserir3D(Personagem e, No3D i){
 
       if(i == null) {         
          i = new No3D(e); n++;
       } else if(e.getNome().compareTo(i.elemento.getNome()) < 0) {
          i.esq = inserir3D(e, i.esq);
       } else if(e.getNome().compareTo(i.elemento.getNome()) > 0) {
          i.dir = inserir3D(e, i.dir);
       } else {
          MyIO.println("Erro!");
       }
 
       return i;
    }
 
    // ------------------------------------- Pesquisa
 
    /**
     * Pesquisa se um elemento exite na Arvore.
     * @param e - elemento a buscar
     * @return verdadeiro se o elemento existir
     */
    public boolean pesquisar(String e){
       MyIO.print("raiz ");
       return pesquisar(e, raiz);
    }
 
    private boolean pesquisar(String e, No i){
 
       boolean resp = false;
 
       if(i != null && resp == false){      
  
          resp = pesquisar3D(e, i.raiz3D);
 
          if (resp == false){
             MyIO.print("esq ");
             resp = pesquisar(e, i.esq);         
          }
 
          if(resp == false){
             MyIO.print("dir ");
             resp = pesquisar(e, i.dir);
          }
       }
 
       return resp;
    }
 
    private boolean pesquisar3D(String e, No3D i){
 
       boolean resp = false;
 
       if(i != null){
 
          if(e.compareTo(i.elemento.getNome()) == 0){
             cmp++;
             resp = true;
          } 
          
          if(resp == false){            
             MyIO.print("ESQ ");
             resp = pesquisar3D(e, i.esq);
          } 
          
          if (resp == false){
             MyIO.print("DIR ");
             resp = pesquisar3D(e, i.dir);
          }
 
       }
 
       return resp;
    }
 
 
    // ------------------------------------- Mostrar
 
    /**
     * Mostra os elementos da Arvore em ordem.
     */
    public void mostrarCentral(){
       MyIO.print("[ ");
       mostrarCentral(raiz);
       MyIO.println("]");
    }
 
    private void mostrarCentral(No i){
 
       if(i != null){
          mostrarCentral(i.esq);
          MyIO.print(i.elemento + " ");
          mostrarCentral(i.dir);
       }
    }
 
    // ------------------------------------- Mostrar 3D
 
    public void mostrar3DCentral(){
       mostrar3DCentral(raiz);
    }
 
    private void mostrar3DCentral(No i){
       
       if(i != null){
          mostrar3DCentral(i.esq);
          mostrarCentral3D(i.raiz3D);
          mostrar3DCentral(i.dir);
       }
    }
 
    private void mostrarCentral3D(No3D i){
       
       if(i != null){
          mostrarCentral3D(i.esq);
          i.elemento.imprimir();
          mostrarCentral3D(i.dir);
       }
    }    
}