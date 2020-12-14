public class Lista{

    private Celula primeiro;
    private Celula ultimo;
 
    private int n;
    private int mov;
    private int cmp;
 
    // ------------------------------------- Construtores
 
    public Lista(){
 
       primeiro = new Celula();
       ultimo = primeiro;
       n = 0;
 
       mov = 0;
       cmp = 0;
    }
 
    // ------------------------------------- getters
 
    public int getTamanho(){ return n; }
    public int getComparacoes(){ return cmp; }
    public int getMovimentacoes(){ return mov; }
 
    // ------------------------------------- Insercao/Remocao
 
    /**
     * Insere personagem no inicio da Lista
     * @param e - elemento da celula
     */
    public void inserirInicio(Personagem e){
 
       Celula tmp = new Celula(e);
       tmp.ante = primeiro;
       tmp.prox = primeiro.prox;
       primeiro.prox = tmp;
 
       if(primeiro == ultimo){
          ultimo = tmp;
       } else {
          tmp.prox.ante = tmp;
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
          primeiro.ante = null;
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
       ultimo.prox.ante = ultimo;
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
 
          removido = ultimo.elemento;
          ultimo = ultimo.ante;
          ultimo.prox.ante = null;
          ultimo.prox = null;
 
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
          tmp.ante = i;
          tmp.ante.prox = tmp;
          tmp.prox.ante = tmp;
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
          removido = i.elemento;
          i.ante.prox = i.prox;
          i.prox.ante = i.ante;         
          i.prox = i.ante = null;
          
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
          i.elemento.imprimir();
       }
    }
 
    // ------------------------------------- Ordenacao
 
    /**
     * Chamada do algoritmo de ordenacao quicksort
     */
    public void quickSort(){
 
       int i = 0;
       int j = (getTamanho() - 1);
 
       quickSort(primeiro.prox, ultimo, i, j);
    }
 
    /**
     * Algoritmo de ordenacao quicksort
     * @param esq - referencia para o inicio da lista.
     * @param dir - referencia para o final da lista.
     * @param ini - sentinela
     * @param fim - sentinela
     */
    public void quickSort(Celula esq, Celula dir, int ini, int fim){
 
       Celula i = esq;
       Celula j = dir;
 
       int a = ini;
       int b = fim;
 
       Celula pivo = esq;
 
       while (a <= b){ // j.prox != i
 
          while(i.elemento.getCorCabelo().compareTo(pivo.elemento.getCorCabelo()) < 0){
             i = i.prox;
             a++;
 
             cmp++;
          }
  
          while(j.elemento.getCorCabelo().compareTo(pivo.elemento.getCorCabelo()) > 0){
             j = j.ante;
             b--;
 
             cmp++;
          }
 
          if(a <= b){ // j.prox != i
 
             swap(i, j);
 
             i = i.prox;
             j = j.ante;
             a++;
             b--;
 
             mov += 3;
          }
      }
 
       if(ini < b){ // pivo.ant != i
          quickSort(esq, j, ini, (b));
       }
 
       if(a < fim){ // pivo.prox != j
          quickSort(i, dir, a, fim);
       }
    }
 
 
    /**
     * Troca elementos de celula.
     */
    public void swap(Celula i, Celula j){
 
       Personagem aux = i.elemento;
       i.elemento = j.elemento;
       j.elemento = aux;
    }
 
    /**
     * Desempate por nome
     */
    public void desempateQuick(){
 
       Celula p = primeiro.prox;
 
       if(n >= 2){
 
          Celula ref_ini = p.prox; 
          Celula ref_ter = p.prox;
          Celula i = p.prox;
          Celula j = p.prox;
 
          int ini = 0;
          int ter = 0;
          int a = 0; // i
          int b = 0; // j
 
          while(i != null){
 
             if(i.elemento.getCorCabelo().compareTo(i.ante.elemento.getCorCabelo()) == 0){
 
                b = a-1;
                ini = ter = a;
 
                j = i;
                ref_ini = ref_ter = i.ante;
                
                ter++;
 
                cmp++;
 
                while((j != null) && (j.elemento.getCorCabelo().compareTo(j.ante.elemento.getCorCabelo()) == 0)){
 
                   ter++;
                   b++; 
 
                   ref_ter = j;
                   i = j;
                   j = j.prox;
 
                   cmp++;
                }
 
                ter--;
  
                /**
                 * intervalos: 
                 *
                 * [01 - 08]
                 * [09 - 19]
                 * [21 - 22]
                 * [23 - 39]
                 */
 
                quickSortN(ref_ini, ref_ter, ini, ter);
 
                a = b;
             }
 
             a++;
             i = i.prox;
          }        
       }
    } 
 
    /**
     * Algoritmo de ordenacao quicksort (desempate por nome).
     * @param esq - referencia para o inicio da lista.
     * @param dir - referencia para o final da lista.
     * @param ini - sentinela
     * @param fim - sentinela
     */
    public void quickSortN(Celula esq, Celula dir, int ini, int fim){
 
       Celula i = esq;
       Celula j = dir;
 
       int a = ini;
       int b = fim;
 
       Celula pivo = esq;
 
       while (a <= b){
 
          while(i.elemento.getNome().compareTo(pivo.elemento.getNome()) < 0){            
             i = i.prox;
             a++;
 
             cmp++;
          }
  
          while(j.elemento.getNome().compareTo(pivo.elemento.getNome()) > 0){
             j = j.ante;
             b--;
 
             cmp++;
          }
 
          if(a <= b){
 
             swap(i, j);
 
             i = i.prox;
             j = j.ante;
             a++;
             b--;
 
             mov += 3;
          }
      }
 
       if(ini < b){
          quickSortN(esq, j, ini, b);
       }
 
       if(a < fim){
          quickSortN(i, dir, a, fim);
       }
    } 
}