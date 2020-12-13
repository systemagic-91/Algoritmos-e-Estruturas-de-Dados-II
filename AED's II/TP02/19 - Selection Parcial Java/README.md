# Selection Sort PARCIAL Código

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

// ---------------------------------- Selection Sort PARCIAL

/**
 * Ordenacao por Selecao Parcial
 */
public void selectionSortParcial(){

    for (int i = 0 ; i < k ; i++){

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