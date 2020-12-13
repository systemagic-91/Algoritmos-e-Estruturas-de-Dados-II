# Quick Sort PARCIAL

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


// ---------------------------------- QuickSort PARCIAL

    public void quickSortParcial(){
        quickSortParcial(0, (n - 1), 10);
    }

/**
 * Ordenacao PARCIAL por Quicksort (Chave de Ordenacao Cor do Cabelo).
 * @param esq - inicio vetor
 * @param dir - final  vetor
 */
private void quickSortParcial(int esq, int dir, int k){

    int i = esq;
    int j = dir;

    Personagem pivo = lista[(dir+esq)/2];

    while(i <= j){

        while(lista[i].getCorCabelo().compareTo(pivo.getCorCabelo()) < 0){
            i++;
        }

        while(lista[j].getCorCabelo().compareTo(pivo.getCorCabelo()) > 0){
            j--;
        }

        if(i <= j){
            swap(i, j);
            i++;
            j--;
        }
    }

    if((j - esq) >= (k - 1)){

        if(esq < j){
            quickSortParcial(esq, j, k);            
        }        
        return;
    }

    if(esq < j){
        quickSortParcial(esq, j, k);
    }

    if(i < dir){
        quickSortParcial(i, dir, k);
    }
}
```