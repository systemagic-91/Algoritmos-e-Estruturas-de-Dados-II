# Heap Sort Código

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


// ---------------------------------- Heap Sort 

/**
 * Ordenacao por Heapsort.
 */
public void heapSort(){

    for (int tam = 2 ; tam <= n ; tam++){
        constroi(tam);
    }

    int tam = n;

    while(tam > 1){
        swap(1, tam--);
        reconstroi(tam);
    }
}

/**
 * Construcao do Heap, coloca a Lista em formato de 
 * heap invertido de acordo com a altura dos personagens.
 * @param tam - tamanho do heap    
 */
public void constroi(int tam){

    for (int i = tam ; i > 1 && lista[i].getAltura() > lista[i/2].getAltura() ; i /= 2){
        swap(i, (i/2));
    }
}

/**
 * Reconstrucao do heap, ordenacao propriamente dita.
 * @param tam - tamanho do heap
 */
public void reconstroi(int tam){

    int i = 1;
    int filho;

    while (i <= (tam/2)){

        filho = getMaiorFilho(i, tam);

        if(lista[i].getAltura() < lista[filho].getAltura()){

            swap(i, filho);
            i = filho;

        } else {
            i = tam;
        }
    }
}

/**
 * Retorna posicao do maior filho de um No.
 * @param i - indice do No
 * @param tam - tamanho do heap
 * @return indice do maior filho
 */
private int getMaiorFilho(int i, int tam){

    int maior = 0;

    if((i * 2) == tam || lista[i * 2].getAltura() > lista[(i * 2) + 1].getAltura()){
        maior = i * 2;
    } else {
        maior = (i * 2) + 1;
    }

    return maior;
}
```

## Complexidade

- Melhor caso: `T(n) = n * lg(n)` 
- Pior caso: `T(n) = n * lg(n)`
- Caso médio: `T(n) = n * lg(n)` 

_O algoritmo de ordenação Heap Sort é O(n * lg(n))_