# Selection Sort Código

``` java
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

    for (int i = 0 ; i < (n-1) ; i++){

        int menor = i;

        for (int j = (i+1) ; j < n ; j++){

            if(lista[j].getNome().compareTo(lista[menor].getNome()) < 0){
                menor = j;
            }
        }
        
        swap(menor, i);
    }

}
```

## Complexidade

- Melhor caso: `T(n) = n^2` 
- Pior caso: `T(n) = n^2`
- Caso médio: `T(n) = n^2` 

_O algoritmo de ordenação Selection Sort é O(n^2)_