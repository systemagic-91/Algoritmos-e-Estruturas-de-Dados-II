# Shell Sort Código

``` c
void swap(int menor, int j);
void shellSort();
void insercaoPorCor(int cor, int h);

/**
 * Ordenacao por ShellSort.
 */
void shellSort(){

   int h = 1;

   do{
      
      h = (h * 3) + 1;

   }while(h < n);

   do{

      h /= 3;

      for(int cor = 0 ; cor < n ; cor++){
         insercaoPorCor(cor, h);
      }

   }while(h != 1);
}

/**
 * Insertion Sort por Cor.
 * @param cor - numero da cor.
 * @param h - passo h
 */ 
void insercaoPorCor(int cor, int h){

   for( int i = (h + cor) ; i <  n ; i += h){

      Personagem tmp = lista[i];
      int j = i - h;
   
      while((j >= 0) && (lista[j].peso > tmp.peso)){

         lista[j + h] = lista [j];
         j -= h;
      }

      lista[j + h] = tmp;
   }
}
```