public class Lista {

    // ----------------------------------------------------------------------- Atributos
 
    private Personagem lista [];
    private int n;
 
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
          lista = new Personagem [i + 1];
 
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
 
    // --------------------------------------------------------------- Pesquisa
 
    // ---------------------------------- Pesquisa Sequencial
 
    /**
     * Pesquisa sequencialmente um elemento na Lista
     * @param chave - chave de pesquisa
     * @return verdadeiro se o elemento existir
     */
    public boolean pesquisaSequencial(String chave){
 
       comparacoes = 0;
 
       boolean resposta = false;
 
       for (int i = 0 ; i < n ; i++){
 
          comparacoes++;
 
          if (lista[i].getNome().compareTo(chave) == 0){
             resposta = true;
             i = n;
          }
       }
 
       return resposta;
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
 
    // ---------------------------------- Selection Sort 
 
    /**
     * Ordenacao por Selecao
     */
    public void selectionSort(){
 
       comparacoes = 0;
       movimentacoes = 0;
 
       for (int i = 0 ; i < (n-1) ; i++){
 
          int menor = i;
 
          for (int j = (i+1) ; j < n ; j++){
 
             comparacoes++;
 
             if(lista[j].getNome().compareTo(lista[menor].getNome()) < 0){
                menor = j;
             }
          }
 
          movimentacoes += 3;
          swap(menor, i);
       }
 
    }
 
    // ---------------------------------- Insertion Sort 
 
    /**
     * Ordenacao por Insercao 
     */
    public void insertionSort(){
 
       comparacoes = 0;
       movimentacoes = 0;
 
       for (int i = 1 ; i < n ; i++) {
 
          Personagem tmp = lista[i];
          int j = i - 1;
 
          while ((j >= 0) && (lista[j].getAnoNascimento().compareTo(tmp.getAnoNascimento()) > 0)){
 
             lista[j+1] = lista[j];
             j--;
 
             comparacoes++;
             movimentacoes++;
          }
 
          comparacoes++;
          movimentacoes++;
 
          lista[j+1] = tmp;
       }
 
    }
 
    // ---------------------------------- Heap Sort 
 
    /**
     * Ordenacao por Heapsort.
     */
    public void heapSort(){
 
       comparacoes = 0;
       movimentacoes = 0;
 
       moverAntesHeap();
 
       for (int tam = 2 ; tam <= n ; tam++){
          constroi(tam);
       }
 
       int tam = n;
 
       while(tam > 1){
          swap(1, tam--);
          reconstroi(tam);
       }
 
       moverPosHeap();
    }
 
    /**
     * Construcao do Heap, coloca a Lista em formato de 
     * heap invertido de acordo com a altura dos personagens.
     * @param tam - tamanho do heap    
     */
    public void constroi(int tam){
 
       for (int i = tam ; i > 1 && lista[i].getAltura() > lista[i/2].getAltura() ; i /= 2){
 
          comparacoes++;
          movimentacoes += 3;
 
          swap(i, (i/2));
       }
    }
 
    /**
     * Reconstrucao do heap, ordenacao propriamente dita.
     * @param tam - tamanho do heap
     */
    public void reconstroi(int tam){
 
       int i = 1;
       int filho;
 
       while (i <= (tam/2)){
 
          comparacoes += 2;
          filho = getMaiorFilho(i, tam);
 
          if(lista[i].getAltura() < lista[filho].getAltura()){
 
             movimentacoes += 3;
 
             swap(i, filho);
             i = filho;
 
          } else {
 
             i = tam;
          }
       }
    }
 
    /**
     * Retorna posicao do maior filho de um No.
     * @param i - indice do No
     * @param tam - tamanho do heap
     * @return indice do maior filho
     */
    private int getMaiorFilho(int i, int tam){
 
       int maior = 0;
 
       if((i * 2) == tam || lista[i * 2].getAltura() > lista[(i * 2) + 1].getAltura()){
          maior = i * 2;
       } else {
          maior = (i * 2) + 1;
       }
 
       return maior;
    }
 
    /**
     * Move elementos da lista para q comecem 
     * na posicao 1.
     */
    private void moverAntesHeap(){
 
       for(int i = n ; i >= 1 ; i--){
          lista[i] = lista[i - 1];
       }
    }
 
    /**
     * Move elementos da lista para que comecem
     * na posicao 0.
     */
    private void moverPosHeap(){
 
       for(int i = 0 ; i < n ; i++){
          lista[i] = lista[i + 1];
       }
    }
 
    /**
     * Desempate do Heap Sort (chave nome).
     */
    public void desempateHeap(){
 
       int inicio = 0;
       int termino = 0;
       int i = 0;
       int j = 0;
 
       while(i < (n - 1)){
 
          if(lista[i].getAltura() == lista[i+1].getAltura()){
 
             j = i;
             inicio = i;
             termino = inicio;
 
             while(lista[j].getAltura() == lista[j+1].getAltura()){
                termino++;
                j++;
             }
 
             quickSortNome(inicio, termino);
             i = j;
          }
 
          i++;
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
 
    // ---------------------------------- CoutingSort 
 
    /**
     * Ordenacao por counting sort.
     */
    public void countingSort(){
 
       comparacoes = 0;
       movimentacoes = 0;
 
       int [] cont = new int[(int)(getMaiorPeso()) + 1];
       Personagem [] orde = new Personagem [n];
 
       for(int i = 0 ; i <  n ; i++){
          cont[(int)(lista[i].getPeso())]++;
       }
 
       for(int i = 1 ; i < cont.length ; i++){
          cont[i] += cont[i - 1];
       }
 
       for(int i = (n - 1) ; i >= 0 ; i--){
          
          movimentacoes++;
 
          orde[cont[(int)(lista[i].getPeso())] - 1] = lista[i];
          cont[(int)(lista[i].getPeso())]--;
       }
 
       for(int i = 0 ; i < orde.length ; i++ ){
          
          movimentacoes++;
 
          lista[i] = orde[i];
       }
    }
 
    /**
     * Busca o personagem com maior peso em uma 
     * lista de Personagens.
     * @return maior peso.
     */
    private int getMaiorPeso(){
 
       int maior = (int)(lista[0].getPeso());
 
       for (int i = 1 ; i < n ; i++){
 
          comparacoes++;
          if((int)(lista[i].getPeso()) > maior){
             maior = (int)(lista[i].getPeso());
          }
       }
 
       return maior;
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
 