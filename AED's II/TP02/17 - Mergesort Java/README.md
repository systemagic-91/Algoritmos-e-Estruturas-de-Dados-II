# Merge Sort Código

``` java
/**
 * Chamada da recursividade.
 */
public void mergeSort(){
    mergeSort(0, (n - 1));
}

/**
 * Algoritmo de ordenacao mergeSort.
 * @param esq - inicio da lista
 * @param dir - fim da lista
 */
public void mergeSort(int esq, int dir){

    if(esq < dir){

        int meio = esq + (dir - esq) / 2;

        mergeSort(esq, meio);
        mergeSort((meio + 1), dir);
        merge(esq, meio, dir);
    }
}

/**
 * Algoritmo de ordenacao merge.
 * @param esq - inicio da lista
 * @param meio - meio da lista
 * @param dir - final da lista
 */
public void merge(int esq, int meio, int dir){
    
    int tam1 = meio - esq + 1;
    int tam2 = dir - meio;

    Personagem [] left = new Personagem[tam1];
    Personagem [] rigth = new Personagem[tam2];

    for(int i = 0 ; i < tam1 ; ++i){
        left[i] = lista[esq + i];
    }

    for (int i = 0 ; i < tam2 ; ++i){       
        rigth[i] = lista[meio + 1 + i];
    }
    
    int i = 0;
    int j = 0;
    int k = esq;

    while(i < tam1 && j < tam2){

        if(left[i].getHomeworld().compareTo(rigth[j].getHomeworld()) <= 0){
        
            lista[k] = left[i];
            i++;

        } else {

            lista[k] = rigth[j];
            j++;

        }

        k++;
    }

    while(i < tam1){

        lista[k] = left[i];
        i++;
        k++;      
    }

    while(j < tam2){

        lista[k] = rigth[j];
        j++;
        k++;      
    }      
}
```

## Complexidade

- Melhor caso: `T(n) = n * lg(n)` 
- Pior caso: `T(n) = n * lg(n)`
- Caso médio: `T(n) = n * lg(n)` 

_O algoritmo de ordenação Merge Sort é O(n * lg(n))_