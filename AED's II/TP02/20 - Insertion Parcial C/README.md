# Insertion Sort PARCIAL

``` c
/**
 * Ordenação por Inserção 
 */
void insertionSortParcial(){

   for (int i = 1 ; i < n ; i++) {

      Personagem tmp = lista[i];
      int j;

      if(i > k){
         j = k;
      } else {
         j = i - 1;
      }

      while ((j >= 0) && strcmp(lista[j].anoNascimento, tmp.anoNascimento) > 0){

         lista[j + 1] = lista[j];
         j--;
      }

      lista[j + 1] = tmp;
   }   
} 

```