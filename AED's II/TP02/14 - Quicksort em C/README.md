# Quick Sort Código

``` c

void swap(int menor, int j);
void quickS();
void quickSort(int i, int j);

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

// ---------------- Quicksort

/**
 * Chamada do Quicksort.
 */
void quickS(){
   quickSort(0, (n - 1));
}


/**
 * Ordenacao por Quicksort. (Chave de Ordenacao Cor do Cabelo).
 * @param esq - inicio vetor
 * @param dir - final  vetor
 */
void quickSort(int esq, int dir){

   int i = esq;
   int j = dir;

   Personagem pivo = lista[(dir+esq)/2];

   while(i <= j){

      while(strcmp(lista[i].corCabelo, pivo.corCabelo) < 0){
         
         i++;
      }

      while(strcmp(lista[j].corCabelo, pivo.corCabelo) > 0){
         
         j--;
      }

      if(i <= j){
         swap(i, j);
         i++;
         j--;
      }
   }

   if(esq < j){
      quickSort(esq, j);
   }

   if(i < dir){
      quickSort(i, dir);
   }
}
```
## Complexidade

- Melhor caso: `T(n) = n * lg(n)` 
- Pior caso: `T(n) = n^2`
- Caso médio: `T(n) = n * lg(n)` 

_O algoritmo de ordenação Quick Sort é O(n * lg(n))_