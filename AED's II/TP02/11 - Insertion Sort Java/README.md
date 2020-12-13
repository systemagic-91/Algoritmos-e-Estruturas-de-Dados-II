# Insertion Sort Código

``` java
/**
 * Ordenacao por Insercao 
 */
public void insertionSort(){

    for (int i = 1 ; i < n ; i++) {

        Personagem tmp = lista[i];
        int j = i - 1;
        
        while ((j >= 0) && (lista[j].getAnoNascimento().compareTo(tmp.getAnoNascimento()) > 0)){

            lista[j+1] = lista[j];
            j--;
        }

        lista[j+1] = tmp;
    }

}
```