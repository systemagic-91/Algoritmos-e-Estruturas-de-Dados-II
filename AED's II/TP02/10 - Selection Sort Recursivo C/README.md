# Selection Sort Recursivo Código

``` c
void swap(int menor, int j);
void selectionSortRecursivo();
void selectionSort(int menor, int j);
void selectionR(int i);

/**
 * Troca elementos da lista de posicao.
 * @param a - indice (inteiro)
 * @param b - indice (inteiro)
 */
void swap(int a, int b){

   Personagem tmp = lista[b];
   lista[b] = lista[a];
   lista[a] = tmp;
}


/**
 * Chamada da Recursividade.
 */
void selectionSortRecursivo(){
   selectionR(0);
}


/**
 * Ordenacao por Selecao Recursiva.
 */
void selectionR(int i){

   if(i > (n-1)){

      i = i;

   } else {
      selectionR(i + 1);
      selectionSort(i, i + 1);
   }
}


/**
 * Busca a posicao do menor elemento em um array.
 * @param menor - inteiro indice menor elemento
 * @param j - indice inteiro prox elemento
 */
void selectionSort(int menor, int j){

   int pMenor = menor;

   if (j > (n - 1)) {

      pMenor = pMenor;

   } else {
      
      if(strcmp(lista[j].nome, lista[pMenor].nome) < 0){
         swap(pMenor, j);
      }

      selectionSort(pMenor, (j + 1));
   }

}
```