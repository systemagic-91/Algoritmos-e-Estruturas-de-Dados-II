# Pesquisa Binária Código

``` c
/**
 * Pesquisa elemento na Lista.
 * @param s - elemento a pesquisar
 * @return verdadeiro se o elemento estiver na Lista
 */
bool pesquisaBinaria(char* s){

   int esq;
   int dir;
   int meio;
   bool resp = false;

   esq = 0;
   dir = (n-1);   

   while(esq <= dir){      
     
      meio = (esq + dir) / 2;
            
      if (strcmp(lista[meio].nome, s) == 0){
         resp = true;
         esq = n;
      } else if (strcmp(lista[meio].nome, s) < 0){
         esq = meio + 1;
      } else {
         dir = meio - 1;
      }
   }
   
   return resp;
}
```
## Complexidade

- Melhor caso: `T(n) = 1` 
- Pior caso: `T(n) = lg(n)`
- Caso médio: `T(n) = (lg(n)+1)/2`

_O algoritmo de pesquisa binária é O(lg(n))_