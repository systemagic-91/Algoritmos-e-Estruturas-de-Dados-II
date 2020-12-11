/**
  * Pontificia Universidade Catolica de Minas Gerais
  * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
  *
  * TP02 - Questao 20 - Insertion Sort Parcial em C
  *  
  * @author - Rayane Paiva Reginaldo
  * @version - 0.1
  */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

#define bool    short
#define true    1
#define false   0
#define equals(a, b) (((strcmp(a, b) == 0 ) ? true : false))
#define NUMENTRADA 1000
#define TAMLINHA   1000


bool isFim(char* s);

typedef struct st_Personagem Personagem;

void ler(char* s);
void imprimir();
void lerNome(char* s);
void lerAltura(char* s);
void lerPeso(char* s);
void lerCorCabelo(char* s);
void lerCorPele(char* s);
void lerCorOlhos(char* s);
void lerAnoNascimento(char* s);
void lerGenero(char* s);
void lerHomeworld(char* s);
long int indexOf(char *padrao, char *entrada);
long int meuIndexOf(int i, char* s);

typedef struct st_Lista Lista;

void inserirInicio();
Personagem removerInicio();
void inserirFim(char* s);
Personagem removerFim();
void inserir();
Personagem remover();
void mostrar();

bool pesquisaBinaria(char* s);

void swap(int menor, int j);
void selectionSortRecursivo();
void selectionSort(int menor, int j);
void selectionR(int i);

void shellSort();
void insercaoPorCor(int cor, int h);
void desempateShell();

void quickS();
void quickSort(int i, int j);
void quickSortNome(int esq, int dir);
void desempateQuick();

void bubbleSort();

void radixSort();
void radix(int e);
int getMaiorAltura();

void insertionSortParcial();
void mostrarParcial(int k);

void heapSortParcial();
void constroi(int tam);
void reconstroi(int tam);
int getMenorFilho(int i, int tam);
void moverPreHeap();
void moverPosHeap();
void mostrarParcialH(int k);
void ordena();
void desempateHeap();

void tarefa(char* s);
char* substring(char* s, int b, int e);
int length();

// ------------------------------------------------------------- Estruturas

typedef struct st_Personagem{

   char nome [100];
   int altura;
   double peso;
   char corCabelo [50];
   char corPele [50];
   char corOlhos [50];
   char anoNascimento [10];
   char genero [20];
   char homeworld [50];

} Personagem;

Personagem p;
Personagem lista[1000];
int n = 0;
int comparacoes;
int movimentacoes;
int k = 10;

// ------------------------------------------------------------- Main

int main( int argc, char** argv){

   char entrada[NUMENTRADA][TAMLINHA];
   int numEntrada = 0;

   do{

      scanf("%s", entrada[numEntrada]);     

   }while(isFim(entrada[numEntrada++]) == false);

   numEntrada--;

   for(int i = 0 ; i < numEntrada ; i++){
      inserirFim(entrada[i]);
   }

   char log[100];
   clock_t t1, t2;
   float tempo;
   FILE *arq = fopen("matricula_heapsort_parcial.txt", "w");

   t1 = clock();
   heapSortParcial();   
//   desempateHeap();
   t2 = clock();

   tempo = (((float)t2 - (float)t1) / 100000.0F);

   snprintf(log, 100, "635383\t %6fms\t Comp: %d\t Mov: %d\n", tempo, comparacoes, movimentacoes);
   fputs(log, arq);

   fclose(arq);

   mostrarParcial(k);

   return 0;
}

bool isFim (char* s){    
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

// ------------------------------------------------------------- Metodos Lista de Personagem

// ------------------------------------ Insercao/Remocao

/**
 * Insere Elemento no inicio da Lista.
 * @param s - elemento a inserir  
 */
void inserirInicio(char* s){

   ler(s);

   for(int i = n ; i > 0  ; i --){

      lista[i] = lista[i-1];
   }

   strcpy(lista[0].nome, p.nome);   
   lista[0].altura = p.altura;   
   lista[0].peso = p.peso;   
   strcpy(lista[0].corCabelo, p.corCabelo);   
   strcpy(lista[0].corPele, p.corPele);   
   strcpy(lista[0].corOlhos, p.corOlhos);   
   strcpy(lista[0].anoNascimento, p.anoNascimento);   
   strcpy(lista[0].genero, p.genero);   
   strcpy(lista[0].homeworld, p.homeworld);

   n++;
 }


/**
 * Remove Elemento do inicio da Lista
 * @return item removido
 */
Personagem removerInicio(){

   Personagem removido = lista[0];

   for (int i = 0 ; i < n ; i++){
      lista[i] = lista[i+1];
   }

   n--;

   return removido;
}


/**
 * Insere Elemento no fim da Lista.
 * @param s - elemento a inserir.
 */
void inserirFim(char* s){

   ler(s);
   
   strcpy(lista[n].nome, p.nome);   
   lista[n].altura = p.altura;   
   lista[n].peso = p.peso;   
   strcpy(lista[n].corCabelo, p.corCabelo);   
   strcpy(lista[n].corPele, p.corPele);   
   strcpy(lista[n].corOlhos, p.corOlhos);   
   strcpy(lista[n].anoNascimento, p.anoNascimento);   
   strcpy(lista[n].genero, p.genero);   
   strcpy(lista[n].homeworld, p.homeworld);

   n++;
}


/**
 * Remove elemento do fim da Lista.
 * @return item removido
 */
Personagem removerFim(){

   Personagem removido = lista[n-1];
   n--;

   return removido;
}


/**
 * Insere elemento em uma posicao especifica da Lista
 * @param s - elemento a inserir
 * @param i - posicao a inserir
 */
void inserir(char* s, int i){

   ler(s);

   for(int a = n ; a > i ; a--){
      lista[a] = lista[a-1];
   }

   strcpy(lista[i].nome, p.nome);   
   lista[i].altura = p.altura;   
   lista[i].peso = p.peso;   
   strcpy(lista[i].corCabelo, p.corCabelo);   
   strcpy(lista[i].corPele, p.corPele);   
   strcpy(lista[i].corOlhos, p.corOlhos);   
   strcpy(lista[i].anoNascimento, p.anoNascimento);   
   strcpy(lista[i].genero, p.genero);   
   strcpy(lista[i].homeworld, p.homeworld);

   n++;
}


/**
 * Remove elemento em uma posicao especifica da Lista
 * @param i - posicao a inserir
 * @return item removido
 */
Personagem remover(int i){

   Personagem removido = lista[i];
   n--;

   for (int a = i ; a < n ; a++){
      lista[a] = lista[a+1];
   }

   return removido;
}

// ------------------------------------ mostrar

/**
 * Mostra elementos da Lista.
 */
void mostrar(){

   for (int i = 0 ; i < n ; i++){

//      printf("[%d] ", i);
      printf(" ## %s", lista[i].nome);
      printf(" ## %d", lista[i].altura);
      printf(" ## %g", lista[i].peso);
      printf(" ## %s", lista[i].corCabelo);
      printf(" ## %s", lista[i].corPele);
      printf(" ## %s", lista[i].corOlhos);
      printf(" ## %s", lista[i].anoNascimento);
      printf(" ## %s", lista[i].genero);
      printf(" ## %s", lista[i].homeworld);
      printf(" ## \n");
   }
}

/**
 * Mostra elementos da Lista.
 */
void mostrarParcial(int k){

   for (int i = 0 ; i < k ; i++){

//      printf("[%d] ", i);
      printf(" ## %s", lista[i].nome);
      printf(" ## %d", lista[i].altura);
      printf(" ## %g", lista[i].peso);
      printf(" ## %s", lista[i].corCabelo);
      printf(" ## %s", lista[i].corPele);
      printf(" ## %s", lista[i].corOlhos);
      printf(" ## %s", lista[i].anoNascimento);
      printf(" ## %s", lista[i].genero);
      printf(" ## %s", lista[i].homeworld);
      printf(" ##\n");
   }
}

// ------------------------------------ Pesquisa

/**
 * Pesquisa elemento na Lista.
 * @param s - elemento a pesquisar
 * @return verdadeiro se o elemento estiver na Lista
 */
bool pesquisaBinaria(char* s){
   
   comparacoes = 0;

   int esq;
   int dir;
   int meio;
   bool resp = false;

   esq = 0;
   dir = (n-1);   

   while(esq <= dir){      
     
      meio = (esq + dir) / 2;

      comparacoes++;
      
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

// ------------------------------------ Ordenacao

/**
 * Troca elementos da lista de posicao.
 * @param a - indice (inteiro)
 * @param b - indice (inteiro)
 */
void swap(int a, int b){

   Personagem tmp = lista[b];
   lista[b] = lista[a];
   lista[a] = tmp;
}

// ---------------- Selection Sort

/**
 * Chamada da Recursividade.
 */
void selectionSortRecursivo(){

   comparacoes = 0;
   movimentacoes = 0;
   selectionR(0);
}


/**
 * Ordenacao por Selecao Recursiva.
 */
void selectionR(int i){

   if(i > (n-1)){

      i = i;

   } else {

      selectionR(i + 1);
      selectionSort(i, i + 1);
   }
}


/**
 * Busca a posicao do menor elemento em um array.
 * @param menor - inteiro indice menor elemento
 * @param j - indice inteiro prox elemento
 */
void selectionSort(int menor, int j){

   int pMenor = menor;

   if (j > (n - 1)) {

      pMenor = pMenor;

   } else {

      comparacoes++;
      if(strcmp(lista[j].nome, lista[pMenor].nome) < 0){

         movimentacoes += 3;
         swap(pMenor, j);
      }

      selectionSort(pMenor, (j + 1));
   }

}

// ---------------- Shellsort

/**
 * Ordenacao por ShellSort.
 */
void shellSort(){

   movimentacoes = 0;
   comparacoes = 0;

   int h = 1;

   do{
      
      h = (h * 3) + 1;

   }while(h < n);

   do{

      h /= 3;

      for(int cor = 0 ; cor < n ; cor++){
         insercaoPorCor(cor, h);
      }

   }while(h != 1);
}

/**
 * Insertion Sort por Cor.
 * @param cor - numero da cor.
 * @param h - passo h
 */ 
void insercaoPorCor(int cor, int h){

   for( int i = (h + cor) ; i <  n ; i += h){

      Personagem tmp = lista[i];
      int j = i - h;

      comparacoes++;
      while((j >= 0) && (lista[j].peso > tmp.peso)){

         comparacoes++;
         movimentacoes++;

         lista[j + h] = lista [j];
         j -= h;
      }

      movimentacoes++;
      lista[j + h] = tmp;
   }
}

/**
 * desempate do shellsort (chave nome).
 */
void desempateshell(){

   int inicio = 0;
   int termino = 0;
   int i = 0;
   int j = 0;

   while(i < n){

      if(lista[i].peso == lista[i+1].peso){

         j = i;
         inicio = i;
         termino = inicio;
         
         while (lista[j].peso == lista[j+1].peso){
            termino++;
            j++;
         }

         quickSortNome(inicio, termino);
         i = j;
      }

      i++;      
   }
}

// ---------------- Quicksort

/**
 * Chamada do Quicksort.
 */
void quickS(){

   comparacoes = 0;
   movimentacoes = 0;
   quickSort(0, (n - 1));
}


/**
 * Ordenacao por Quicksort. (Chave de Ordenacao Cor do Cabelo).
 * @param esq - inicio vetor
 * @param dir - final  vetor
 */
void quickSort(int esq, int dir){

   int i = esq;
   int j = dir;

   Personagem pivo = lista[(dir+esq)/2];

   while(i <= j){

      while(strcmp(lista[i].corCabelo, pivo.corCabelo) < 0){

         comparacoes++;
         i++;
      }

      while(strcmp(lista[j].corCabelo, pivo.corCabelo) > 0){

         comparacoes++;
         j--;
      }

      if(i <= j){

         movimentacoes += 3;
         swap(i, j);
         i++;
         j--;
      }
   }

   if(esq < j){
      quickSort(esq, j);
   }

   if(i < dir){
      quickSort(i, dir);
   }
}


/**
 * Ordenacao por Quicksort. (Chave de Ordenacao Nome).
 * @param esq - inicio vetor
 * @param dir - final  vetor
 */
void quickSortNome(int esq, int dir){

   int i = esq;
   int j = dir;

   Personagem pivo = lista[(dir+esq)/2];

   while(i <= j){

      while(strcmp(lista[i].nome, pivo.nome) < 0){

         comparacoes++;
         i++;
      }

      while(strcmp(lista[j].nome, pivo.nome) > 0){

         comparacoes++;
         j--;
      }

      if(i <= j){

         movimentacoes += 3;
         swap(i, j);
         i++;
         j--;
      }
   }

   if(esq < j){
      quickSortNome(esq, j);
   }

   if(i < dir){
      quickSortNome(i, dir);
   }
}

/**
 * Desempate do quickSort corCabelo.
 */
void desempateQuick(){

   int inicio = 0;
   int termino = 0;
   int i = 0;
   int j = 0;

   while(i < n){

      if(strcmp(lista[i].corCabelo, lista[i+1].corCabelo) == 0){

         j = i;
         inicio = i;
         termino = inicio;
         
         while ((strcmp(lista[j].corCabelo, lista[j+1].corCabelo) == 0)){
            termino++;
            j++;
         }

         quickSortNome(inicio, termino);
         i = j;
      }

      i++;
   }
}

// ---------------- Bubblesort

/**
 * Ordenacao por BubbleSort
 */
void bubbleSort(){

   comparacoes = 0;
   movimentacoes = 0;

   for(int i = (n - 1) ; i > 0 ; i--){

      for(int j = 0 ; j < i ; j++){

         comparacoes++;

         if(strcmp(lista[j].anoNascimento, lista[j + 1].anoNascimento) > 0){

            movimentacoes += 3;

            swap(j, (j+1));
         }
      }
   }
}

// ---------------- Radixsort

/**
 * Ordenacao por Radixsort.
 */
void radixSort(){

   comparacoes = 0;
   movimentacoes = 0;

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

      movimentacoes++;

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

      comparacoes++;
      if(lista[i].altura > maior){
         maior = lista[i].altura;
      }
   }

   return maior;
}


// ---------------- Insertion Sort Parcial

void insertionSortParcial(){

   comparacoes = 0;
   movimentacoes = 0;

   for (int i = 1 ; i < n ; i++) {

      Personagem tmp = lista[i];
      int j;

      if(i > k){
         j = k;
      } else {
         j = i - 1;
      }

      while ((j >= 0) && strcmp(lista[j].anoNascimento, tmp.anoNascimento) > 0){

         lista[j + 1] = lista[j];
         j--;

         comparacoes++;
         movimentacoes++;
      }

      comparacoes++;
      movimentacoes++;

      lista[j + 1] = tmp;
   }   

} 

// ---------------- Heapsort Parcial

/**
 * Ordenacao Parcial por HeapSort.
 */
void heapSortParcial(){

   comparacoes = 0;
   movimentacoes = 0;

   moverPreHeap();

   for(int tam = 2 ; tam <= n ; tam++){
      constroi(tam);
   }

   int tam = k;

   while(tam > 1){
      swap(1, tam--);
      reconstroi(tam);
   }

   moverPosHeap();
//   ordena();
}


/**
 *
 */
void constroi(int tam){

   for(int i = tam ; i > 1 && lista[i].altura < lista[i/2].altura ; i /= 2){
      swap(i, (i/2));
   }
}

/**
 *
 */
void reconstroi(int tam){

   int i = 1;
   int filho;

   while(i <= (tam/2)){

      filho = getMenorFilho(i, tam);

   if(lista[i].altura > lista[filho].altura){
      
      swap(i, filho);
      i = filho;

   } else {
      i = tam;
   }
}
}

/**
*
*/
int getMenorFilho(int i, int tam){

int menor = 0;

if((i * 2) == tam || lista[i * 2].altura < lista[(i * 2) + 1].altura){
   menor = i * 2;
} else {
   menor = (i * 2) + 1;
}

return menor;
}

/**
*
*/
void moverPreHeap(){

for(int i = n ; i >= 1 ; i--){
      lista[i] = lista[i - 1];
   }
}

/**
 *
 */
void moverPosHeap(){

   for(int i = 0 ; i < n ; i++){
      lista[i] = lista[i + 1];
   }
}

void ordena(){

   int m = k - 1;
   Personagem listaAux [10];

   for(int i = 0 ; i < k ; i++, m--){
      listaAux[m] = lista[i];
   }

   for(int i = 0 ; i < k ; i++){
      lista[i] = listaAux[i];
   }
}

/**
 * Desempate do quickSort corCabelo.
 */
void desempateHeap(){

   int inicio = 0;
   int termino = 0;
   int i = 0;
   int j = 0;

   while(i < k){

      if(lista[i].altura == lista[i+1].altura){

         j = i;
         inicio = i;
         termino = inicio;
         
         while (lista[j].altura == lista[j+1].altura){
            termino++;
            j++;
         }

         quickSortNome(inicio, termino);
         i = j;
      }

      i++;
   }
}

// ------------------------------------ tarefa

/**
 * Realiza sequencia de comandos.
 * @param s - comandos
 */
void tarefa(char* s){
   
   int index;
   int comando;
   char* posicao;
   char* persona;
   
   Personagem a;

   if(s[0] == 'I' && s[1] == 'I'){
      comando = 1;
   } else if(s[0] == 'I' && s[1] == '*'){
      comando = 2;
   } else if(s[0] == 'I' && s[1] == 'F'){
      comando = 3;
   } else if(s[0] == 'R' && s[1] == 'I'){
      comando = 4;
   } else if(s[0] == 'R' && s[1] == '*'){
      comando = 5;
   } else if(s[0] == 'R' && s[1] == 'F'){
      comando = 6;
   }

   switch (comando) {

      case 1:

         persona = substring(s, 3, (strlen(s)));

         inserirInicio(persona);

         free(persona);

         break;

      case 2:

         persona = substring(s, 6, (strlen(s)));
         posicao = substring(s, 3, 5);
         index = atoi(posicao);

         inserir(persona, index);
         
         free(posicao);
         free(persona);

         break;

      case 3:
 
         persona = substring(s, 3, (strlen(s)));

         inserirFim(persona);

         free(persona);

         break;

      case 4:

         a = removerInicio();
         printf("(R) %s\n", a.nome);

         break;
        
      case 5:

         posicao = substring(s, 3, 5);
         index = atoi(posicao);

         a = remover(index);
         printf("(R) %s\n", a.nome);
        
         free(posicao);

         break;

      case 6:

         a = removerFim();
         printf("(R) %s\n", a.nome);
         break;

   }

}


/**
 * Funcao para retornar parte de uma string
 * @param s - string original
 * @param b - inicio corte
 * @param e - fim corte
 * @return parte de uma string
 */
char* substring(char* s, int b, int e){

   int i, j;
   char* sub;

   sub = (char*) malloc(sizeof(char) * (e-b+1));

   for(i = b, j = 0 ; i < e ; i++, j++){
      sub[j] = s[i];
   }

   sub[j] = '\0';

   return sub;
}

// ------------------------------------------------------------- Metodos Personagem

/**
 * Realiza leitura do arquivo e dos atributos do registro.
 * @param s - caminho do arquivo no diretorio 
 */
void ler (char* s){

   char *linha = NULL;
   FILE *file;
   size_t len = 0;

   file = fopen(s, "rb");

   getline(&linha, &len, file);

   while (!feof(file)){
      getline(&linha, &len, file);
   }

   fclose(file);   

   lerNome(linha);
   lerAltura(linha);
   lerPeso(linha);
   lerCorCabelo(linha);
   lerCorPele(linha);
   lerCorOlhos(linha);
   lerAnoNascimento(linha);
   lerGenero(linha);
   lerHomeworld(linha);
}


/**
 * Le o nome do personagem e atribui na struct.
 * @param s - sequencia de caracteres
 */
void lerNome(char* s){
   
   int i = 0;
   int inicio = indexOf ("name': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.nome[i++] = s[inicio++];
   }

   p.nome[i] = '\0';
}


/**
 * Le a altura do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerAltura(char* s){
   
   char num[10];
   int i = 0;
   int inicio = indexOf ("height': ", s);

   inicio = meuIndexOf(inicio, s);
 
   while (s[inicio] != 39){
      num[i++] = s[inicio++];
   }


   if(strcmp(num, "unknown") == 0){
      p.altura = 0;
   } else {
      p.altura = atoi(num);
   }
}


/**
 * Le o peso do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerPeso(char* s){
   
   char num[10];
   int i = 0;
   int inicio = indexOf ("mass': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      num[i++] = s[inicio++];
   }

   num[i] = '\0';

   if(strcmp(num, "unknown") == 0){
      p.peso = 0;
   } else if(strcmp(num, "1,358") == 0){
      p.peso = 1358;
   } else {
      p.peso = atof(num);
   }
}


/**
 * Le a cor do cabelo do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerCorCabelo(char* s){   
   
   int i = 0;
   int inicio = indexOf ("hair_color': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.corCabelo[i++] = s[inicio++];
   }

   p.corCabelo[i] = '\0';
}


/**
 * Le cor da pele do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerCorPele(char* s){   
   
   int i = 0;
   int inicio = indexOf ("skin_color': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.corPele[i++] = s[inicio++];
   }

   p.corPele[i] = '\0';
}


/**
 * Le cor dos olhos do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerCorOlhos(char* s){   
   
   int i = 0;
   int inicio = indexOf ("eye_color': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.corOlhos[i++] = s[inicio++];
   }

   p.corOlhos[i] = '\0';
}


/**
 * Le ano nascimento do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerAnoNascimento(char* s){   
   
   int i = 0;
   int inicio = indexOf ("birth_year': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.anoNascimento[i++] = s[inicio++];
   }

   p.anoNascimento[i] = '\0';
}


/**
 * Le genero do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerGenero(char* s){   
   
   int i = 0;
   int inicio = indexOf ("gender': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.genero[i++] = s[inicio++];
   }

   p.genero[i] = '\0';
}


/**
 * Le homeworld do personagem e atribui na struct.
 * @param s - sequencia de caracteres.
 */
void lerHomeworld(char* s){   
   
   int i = 0;
   int inicio = indexOf ("homeworld': ", s);

   inicio = meuIndexOf(inicio, s);

   while (s[inicio] != 39){
      p.homeworld[i++] = s[inicio++];
   }

   p.homeworld[i] = '\0';
}


/**
 * Imprime na saida padrao os dados da estrutura. 
 */
void imprimir(){

   printf(" ## %s", p.nome);
   printf(" ## %d", p.altura);
   printf(" ## %g", p.peso);
   printf(" ## %s", p.corCabelo);
   printf(" ## %s", p.corPele);
   printf(" ## %s", p.corOlhos);
   printf(" ## %s", p.anoNascimento);
   printf(" ## %s", p.genero);
   printf(" ## %s", p.homeworld);
   printf(" ## \n");
}


/**
 * Funcao para retornar indice de inicio de um padrao
 * de sequencia de caracteres.
 * @param padrao - padrao buscado
 * @param entrada - caracteres de entrada
 * @return ponteiro
 */
long int indexOf(char *padrao, char *entrada){
   char *pointer = strstr(entrada, padrao);
   return pointer - entrada;
}


/**
 * Funcao para retornar indice de inicio da sequencia 
 * de caracteres desejada.
 * @param i - inteiro
 * @param s - sequencia de caracteres
 * @return inteiro
 */
long int meuIndexOf(int i, char* s){
   
   long int soma = i;
   long int aspas = 0;

   while(aspas != 2){

      if (s[soma] == 39){
         aspas++;
      }

      soma++;
   }

   return soma;
}


/**
 * Retorna o tamanho da lista.
 * @return inteiro.
 */
int length(){
   return n;
}

