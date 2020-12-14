# Pesquisa Sequencial Código

``` java
/**
 * Pesquisa sequencialmente um elemento na Lista
 * @param chave - chave de pesquisa
 * @return verdadeiro se o elemento existir
 */
public boolean pesquisaSequencial(String chave){    

    boolean resposta = false;

    for (int i = 0 ; i < n ; i++){
        
        if (lista[i].getNome().compareTo(chave) == 0){
            resposta = true;
            i = n;
        }
    }

    return resposta;
}
```

## Complexidade

- Melhor caso: `T(n) = 1` 
- Pior caso: `T(n) = n`
- Caso médio: `T(n) = (n+1)/2`

_O algoritmo de pesquisa sequencial é O(n)_