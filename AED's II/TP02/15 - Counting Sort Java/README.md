# Counting Sort Código

``` java
/**
 * Ordenacao por counting sort.
 */
public void countingSort(){

    int [] cont = new int[(int)(getMaiorPeso()) + 1];
    Personagem [] orde = new Personagem [n];

    for(int i = 0 ; i <  n ; i++){
        cont[(int)(lista[i].getPeso())]++;
    }

    for(int i = 1 ; i < cont.length ; i++){
        cont[i] += cont[i - 1];
    }

    for(int i = (n - 1) ; i >= 0 ; i--){

        orde[cont[(int)(lista[i].getPeso())] - 1] = lista[i];
        cont[(int)(lista[i].getPeso())]--;
    }

    for(int i = 0 ; i < orde.length ; i++){
        lista[i] = orde[i];
    }
}

/**
    * Busca o personagem com maior peso em uma 
    * lista de Personagens.
    * @return maior peso.
    */
private int getMaiorPeso(){

    int maior = (int)(lista[0].getPeso());

    for (int i = 1 ; i < n ; i++){

        if((int)(lista[i].getPeso()) > maior){
            maior = (int)(lista[i].getPeso());
        }
    }

    return maior;
}
```