public class Lista {

    // ----------------------------------------------------------------------- Atributos
 
    private Personagem lista [];
    private int n;
 
    private final int k = 10;
 
    private int comparacoes;
    private int movimentacoes;   
 
    // ----------------------------------------------------------------------- Construtores
 
    /**
     * Construtor Padrao
     */
    public Lista(){
       this(1000);
    }
 
    /**
     * Construtor Secundario, controi a lista de acordo com 
     * o tamanho passado por paramento.
     * @param i - inteiro maior que zero.
     */
    public Lista(int i){
 
       try {
 
          n = 0;
          lista = new Personagem [i];
 
       } catch (NegativeArraySizeException e1){
          MyIO.println("CONSTRUTOR ===> " + e1.toString());
       } finally {
       }    
    }
      
    // --------------------------------------------------------------- Getters
 
    public int getComparacoes(){ return comparacoes; }
    public int getMovimentacoes(){ return movimentacoes; }
 
    // --------------------------------------------------------------- Clone
 
    /**
     * Retorna um clone do objeto que chama.
     * @return objeto do tipo Lista.
     */   
    public Lista myClone(){
 
       Lista lst = new Lista(this.lista.length);
 
       lst.lista = this.lista;
 
       return lst;
    }
 
    // --------------------------------------------------------------- Insersao/Remocao
 
    /**
     * Insere um Personagem  no inicio da lista.
     * @param e - Personagem
     */
    public void inserirInicio(Personagem e){
 
       try{
 
          if(n == 0 && lista.length > 0){
 
             lista[0] = e;
 
          } else {
 
             for (int i = n ; i > 0  ; i--){
 
                lista[i] = lista[i-1];
             }
 
          }
 
          lista[0] = e;
          n++;
 
       } catch (ArrayIndexOutOfBoundsException e1){
          MyIO.println("INSERIR NO INICIO ===> " + e1.toString());
       } finally {
       }    
 
    }
 
    /**
     * Remove um Personagem do inicio da lista.
     * @return Personagem removido.
     */
    public Personagem removerInicio(){
 
       Personagem removido = null;
 
       if(n < 1){
 
          MyIO.println("REMOVER INICIO ===> Lista Vazia!");
 
       } else {
 
          removido = lista[0];
 
          for (int i = 0 ; i < n ; i++){
             lista[i] = lista[i+1];
          }
 
          n--;
       }
 
       return removido;
    }
 
 
    /**
     * Insere um Persoangem na ultima posicao disponivel da lista
     * @param e - Personagem    
     */
    public void inserirFim(Personagem e){
 
       try{
 
          lista[n] = e;
          n++;
 
       } catch (ArrayIndexOutOfBoundsException e1){
          MyIO.println("INSERIR NO FIM ===> " + e1.toString());
       } finally {
       }
    }
 
    /**
     * Remove Personagem na ultima posicao preenchida da lista.
     * @return Personagem removido
     */
    public Personagem removerFim(){
 
       Personagem removido = null;
 
       try{
 
          removido = lista[n-1];
          n--;
 
       } catch (ArrayIndexOutOfBoundsException e1){
          MyIO.println("REMOVER DO FIM ===> " + e1.toString());
       } finally {         
       }
 
       return removido;
    }
 
 
    /**
     * Insere um Personagem de uma posicao valida da lista.
     * @param i - inteiro correspondente ao indice de insercao
     * @param e - Personagem a inserir
     */
    public void inserir(int i, Personagem e){
 
       try {
 
          for (int a = n ; a > i ; a--){
             lista[a] = lista[a-1];
          }
 
          lista[i] = e;
          n++;
 
       } catch (ArrayIndexOutOfBoundsException e1) {
          MyIO.println("INSERIR ===> " + e1.toString());    
       } finally {
       }
    }
 
    /**
     * Remove Personagem de uma posicao valida da lista.
     * @param i - inteiro correspondente ao indice do elemento a ser removido
     * @return Personagem removido
     */
    public Personagem remover(int i){
 
       Personagem removido = null;
 
       try{
 
          removido = lista[i];
          n--;
 
          for(int a = i ; a < n ; a++){
             lista[a] = lista[a+1];
          }
 
       } catch (ArrayIndexOutOfBoundsException e1){
          MyIO.println("REMOVER ===> " + e1.toString());
       } finally {         
       }
 
       return removido;
    }
 
    // --------------------------------------------------------------- Mostrar
 
    /**
     * Mostra os elementos da lista em ordem.
     */
    public void mostrar(){
 
       for (int i = 0 ; i < n ; i++){
          //MyIO.print("[" + i + "] ");
          lista[i].imprimir();
       }
    }
 
    /**
     * Mostra os elementos da lista em ordem inversa.
     */
    public void mostrarInverso(){
 
       for (int i = (n - 1) ; i >= 0 ; i--){
          lista[i].imprimir();
       }
    }
 
    /**
     * Mostra os elementos da lista em ordem.
     */
    public void mostrarParcial(){
 
       for (int i = 0 ; i < k ; i++){
          //MyIO.print("[" + i + "] ");
          lista[i].imprimir();
       }
    }

    // --------------------------------------------------------------- Ordenacao 
 
    /**
     * Troca elementos da lista de posicao.
     * @param i - inteiro (indice lista)
     * @param j - inteiro (indice lista)
     */
    private void swap(int i, int j){
 
       Personagem aux = lista[j];
       lista[j] = lista[i];
       lista[i] = aux;
    }

  
    // ---------------------------------- QuickSort PARCIAL
 
     public void quickSortParcial(){
         quickSortParcial(0, (n - 1), 10);
     }
 
    /**
     * Ordenacao PARCIAL por Quicksort (Chave de Ordenacao Cor do Cabelo).
     * @param esq - inicio vetor
     * @param dir - final  vetor
     */
    private void quickSortParcial(int esq, int dir, int k){
 
       int i = esq;
       int j = dir;
 
       Personagem pivo = lista[(dir+esq)/2];
 
       while(i <= j){
 
          while(lista[i].getCorCabelo().compareTo(pivo.getCorCabelo()) < 0){
 
             comparacoes++;
             i++;
          }
 
          while(lista[j].getCorCabelo().compareTo(pivo.getCorCabelo()) > 0){
 
             comparacoes++;
             j--;
          }
 
          if(i <= j){
 
             movimentacoes += 3;
             swap(i, j);
             i++;
             j--;
          }
       }
 
       if((j - esq) >= (k - 1)){
 
          if(esq < j){
             quickSortParcial(esq, j, k);            
          }        
          return;
       }
 
       if(esq < j){
          quickSortParcial(esq, j, k);
       }
 
       if(i < dir){
          quickSortParcial(i, dir, k);
       }
    }

     
    /**
     * Ordenacao por Quicksort. (Chave de Ordenacao Nome).
     * @param esq - inicio vetor
     * @param dir - final  vetor
     */
    private void quickSortNome(int esq, int dir){
 
      int i = esq;
      int j = dir;

      Personagem pivo = lista[(dir+esq)/2];

      while(i <= j){
         
         while(lista[i].getNome().compareTo(pivo.getNome()) < 0){

            comparacoes++;
            i++;
         }

         while(lista[j].getNome().compareTo(pivo.getNome()) > 0){

            comparacoes++;
            j--;
         }

         if(i <= j){

            movimentacoes += 3;
            swap(i, j);
            i++;
            j--;
         }
      }

      if(esq < j){
         quickSortNome(esq, j);
      }

      if(i < dir){
         quickSortNome(i, dir);
      }
   }


    /**
     * Desempate do Heap Sort (chave nome).
     */
    public void desempateQuick(){
 
       int inicio = 0;
       int termino = 0;
       int i = 0;
       int j = 0;
 
       int k = 10;
 
       while(i < k){
 
          comparacoes++;
          if(lista[i].getCorCabelo().compareTo(lista[i+1].getCorCabelo()) == 0){
 
             j = i;
             inicio = i;
             termino = inicio;
 
             comparacoes++;
             while(lista[j].getCorCabelo().compareTo(lista[j+1].getCorCabelo()) == 0){
                termino++;
                j++;
             }
 
             quickSortNome(inicio, termino);
             i = j;
          }
 
          i++;
       }
 
    }
 
    // --------------------------------------------------------------- Tarefa 
 
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
 