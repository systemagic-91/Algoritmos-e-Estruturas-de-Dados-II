# Radix Sort Código

``` c

void radixSort();
void radix(int e);
int getMaiorAltura();

/**
 * Ordenacao por Radixsort.
 */
void radixSort(){

   int maior = getMaiorAltura();

   for(int e = 1 ; (maior / e) > 0 ; e *= 10){
      radix(e);
   }
}

/**
 * Ordenacao por radix.
 * @param e - inteiro
 */
void radix(int e){

   int i;
   int cont[10];
   Personagem saida[n];

   for(int i = 0 ; i < 10 ; i++){
      cont[i] = 0;
   }
   
   for(i = 0 ; i < n ; i++){
      cont[(lista[i].altura / e) % 10]++;
   }

   for(i = 1 ; i < 10 ; i++){
      cont[i] += cont[i - 1]; 
   }

   for(i = (n - 1) ; i >= 0 ; i--){
      saida[cont[(lista[i].altura / e) % 10] - 1] = lista[i];
      cont[(lista[i].altura / e) % 10]--;
   }

   for(i = 0 ; i < n ; i++){
      lista[i] = saida[i];
   }
}

/**
 * Retorna maior altura da lista de personagens
 * @return inteiro.
 */
int getMaiorAltura(){

   int maior = lista[0].altura;

   for(int i = 0 ; i < n ; i++){
      if(lista[i].altura > maior){
         maior = lista[i].altura;
      }
   }

   return maior;
}

```

## Complexidade

_O algoritmo de ordenação Radix Sort é O(n * k) onde n é o número de elementos e k é o tamanho médio das chaves._