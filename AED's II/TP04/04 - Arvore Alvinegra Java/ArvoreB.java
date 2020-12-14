public class ArvoreB{

    private No raiz;
    private int n;
    private int cmp;   
 
    // ------------------------------------- Construtores
 
    public ArvoreB(){
 
       raiz = null;
       n = 0;
       cmp = 0;
       mov = 0;
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
       
       if(raiz == null){
          raiz = new No(e, false);
       } else if(raiz.esq == null && raiz.dir == null){
 
          raiz = new No(e, true);
 
          if(raiz.elemento.getNome().compareTo(e.getNome()) >  0){
             raiz.esq = new No(e, true);
          } else{
             raiz.dir = new No(e, true);
          }
 
       } else if(raiz.esq == null){
 
          if(raiz.elemento.getNome().compareTo(e.getNome()) > 0){
             raiz.esq = new No(e);
          } else if (raiz.dir.elemento.getNome().compareTo(e.getNome()) > 0){
 
             raiz.esq = new No(raiz.elemento);
             raiz.elemento = e;
 
          } else {
 
             raiz.esq = new No(raiz.elemento);
             raiz.elemento = raiz.dir.elemento;
             raiz.dir.elemento = e;
 
          }
 
          raiz.esq.cor = raiz.dir.cor = false;
 
       } else if(raiz.dir == null){
 
          if(raiz.elemento.getNome().compareTo(e.getNome()) < 0){
             raiz.dir = new No(e);
          } else if(raiz.esq.elemento.getNome().compareTo(e.getNome()) < 0){
             
             raiz.dir = new No(raiz.elemento);
             raiz.elemento = e;
 
          } else {
 
             raiz.dir = new No(raiz.elemento);
             raiz.elemento = raiz.esq.elemento;
             raiz.esq.elemento = e;
 
          }
 
          raiz.esq.cor = raiz.dir.cor = false;
 
       } else {
          inserir(e, null, null, null, raiz);
       }
 
       raiz.cor = false;
    }
 
 
    /**
     * Insere um elemento na arvore.
     * @param e - elemento a inserir.
     * @param bisa - no avo
     * @param avo - no avo
     * @param pai - no pai
     * @param i - no 
     */
    private void inserir(Personagem e, No bisa, No avo, No pai, No i){
 
       if(i == null) { 
 
          if(e.getNome().compareTo(pai.elemento.getNome()) < 0){
             i = pai.esq = new No(e, true);
          } else {
             i = pai.dir = new No(e, true);
          }
 
          if(pai.cor == true){
             balancear(bisa, avo, pai, i);
          }
 
       } else {
 
          if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
 
             i.cor = true;
             i.esq.cor = i.dir.cor = false;
 
             if(i == raiz){
                i.cor = false;
             } else if(pai.cor == true){
                balancear(bisa, avo, pai, i);
             }
 
          }
 
          if(e.getNome().compareTo(i.elemento.getNome()) < 0){
             inserir(e, avo, pai, i, i.esq);
          } else if(e.getNome().compareTo(i.elemento.getNome()) > 0){
             inserir(e, avo, pai, i, i.dir);
          } else {
             MyIO.println("Erro inserir!");
          }
       }      
    }   
 
    // ------------------------------------- Balancear/Rotacionar
 
    /**
     * Balanceamento de nos.
     * @param bisa - no bisavo
     * @param avo -  no avo
     * @param pai - no pai
     * @param i - no
     */
    private void balancear(No bisa, No avo, No pai, No i){
 
       if(pai.cor == true){
          
          if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){
 
             if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0){
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
 
          } else {
 
             if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
 
          }
 
          if(bisa == null){
             raiz = avo;
          } else {
             if(avo.elemento.getNome().compareTo(bisa.elemento.getNome()) < 0){
                bisa.esq = avo;
             } else {
                bisa.dir = avo;
             }
          }
 
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
       }
    }
 
    /**
     * Ritacao simpres direita
     * @param i - no a rotacionar
     * @return referencia no rotacionado
     */
    private No rotacaoDir(No i){
 
       No noEsq = i.esq;
       No noEsqDir = noEsq.dir;
 
       noEsq.dir = i;
       i.esq = noEsqDir;
 
       return noEsq;
    }
 
    /**
     * Rotacao simples esquerda
     * @param i - no a rotacionar
     * @return referencia no rotacionado
     */
    private No rotacaoEsq(No i){
 
       No noDir = i.dir;
       No noDirEsq = noDir.esq;
 
       noDir.esq = i;
       i.dir = noDirEsq;
 
       return noDir;     
    }
 
    /**
     * Rotacao dupla direita-esquerda
     * @param i - no a rotacionar
     * @return referencia no rotacionado
     */
    private No rotacaoDirEsq(No i){
       i.dir = rotacaoDir(i.dir);
       return rotacaoEsq(i);
    }
 
    /**
     * Rotacao dupla esquerda-direita
     * @param i - no a rotacionar 
     * @return referencia no rotacionado
     */
    private No rotacaoEsqDir(No i){
       i.esq = rotacaoEsq(i.esq);
       return rotacaoDir(i);
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
 
    /**
     * Mostra recursivamente os itens da arvore.
     * @param i - raiz da arvore
     */
    private void mostrarCentral(No i){
 
       if(i != null){
          mostrarCentral(i.esq);
          i.elemento.imprimir();
          mostrarCentral(i.dir);
       }
    }   
}