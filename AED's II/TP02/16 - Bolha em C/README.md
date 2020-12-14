# Bubble Sort Código

```c

void swap(int menor, int j);
void bubbleSort();

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

// ---------------- Bubblesort

/**
 * Ordenacao por BubbleSort
 */
void bubbleSort(){

   for(int i = (n - 1) ; i > 0 ; i--){

      for(int j = 0 ; j < i ; j++){

         if(strcmp(lista[j].anoNascimento, lista[j + 1].anoNascimento) > 0){
            swap(j, (j+1));
         }
      }
   }
}
```
## Complexidade

- Melhor caso: `T(n) = n` 
- Pior caso: `T(n) = n^2`
- Caso médio: `T(n) = n^2` 

_O algoritmo de ordenação Bubble Sort é O(n^2)_