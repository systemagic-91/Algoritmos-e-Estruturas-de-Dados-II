/**
  * Pontificia Universidade Catolica de Minas Gerais
  * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
  *
  * TP02 - Questao 07 - Quicksort em Lista dupla C
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

typedef struct st_Celula Celula;

Celula* newCelula(Personagem e);
void start();
void statVazio();

void inserirInicio();
Personagem removerInicio();
void inserirFim(char* s);
Personagem removerFim();
void inserir();
Personagem remover();

void mostrar();
bool isVazia();
void liberar();

void swap(Celula* menor, Celula* j);
void quickS();
void quickSort(Celula* esq, Celula* dir, int i, int j);
void quickSortNome(Celula* esq, Celula* dir, int i, int j);
void desempateQuick();

char* substring(char* s, int b, int e);

// ------------------------------------------------------------- Estruturas

struct st_Personagem{

   char nome [100];
   int altura;
   double peso;
   char corCabelo [50];
   char corPele [50];
   char corOlhos [50];
   char anoNascimento [10];
   char genero [20];
   char homeworld [50];

};

struct st_Celula{
   
   Personagem e;
   Celula* prox;
   Celula* ante;
};

int n = 0;
int cmp = 0;
int mov = 0;

Personagem p;
Personagem pVazio;

Celula* primeiro;
Celula* ultimo;
Celula* pri_Celula;

void start(){
   
   pri_Celula = newCelula(pVazio);

   primeiro = pri_Celula;
   ultimo = primeiro; 
}

void startVazio(){   

   char nulo[5] = "none";
   nulo[4] = '\0';
   
   strcpy(pVazio.nome, nulo);
   pVazio.altura = 0;
   pVazio.peso = 0;
   strcpy(pVazio.corCabelo, nulo);
   strcpy(pVazio.corPele, nulo);
   strcpy(pVazio.corOlhos, nulo);
   strcpy(pVazio.anoNascimento, nulo);
   strcpy(pVazio.homeworld, nulo);
}


// ------------------------------------------------------------- Main

int main( int argc, char** argv){

   startVazio();
   start();

   //printf("%s\n", primeiro->e.nome);

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
   FILE *arq = fopen("matricula_quicksort_listaDupla_C.txt", "w");

   t1 = clock();
   quickS();
   desempateQuick();
   t2 = clock();

   tempo = (((float)t2 - (float)t1) / 100000.0F);

   snprintf(log, 100, "635383\t %6fms\t Comp: %d\t Mov: %d\n", tempo, cmp, mov);
   fputs(log, arq);

   fclose(arq);

   mostrar();
   liberar();

   return 0;
}

bool isFim (char* s){    
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

// ------------------------------------------------------------- Metodos Lista de Personagem

// ------------------------------------ Insercao/Remocao

/**
 * Cria uma nova celula com um personagem.
 * @param elm - personagem
 */
Celula* newCelula(Personagem elm){

   Celula* nova = (Celula*)malloc(sizeof(Celula));
   nova->e = elm;
   nova->prox = NULL;

   return nova;
}

/**
 * Insere Elemento no inicio da Lista.
 * @param s - elemento a inserir  
 */
void inserirInicio(char* s){

   ler(s);

   Celula* tmp = newCelula(p);
   tmp->ante = primeiro;
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   
   if(primeiro == ultimo){
      ultimo = tmp;
   } else{
      tmp->prox->ante = tmp;
   }

   tmp = NULL;
   free(tmp);

   n++;
 }


/**
 * Remove Elemento do inicio da Lista
 * @return item removido
 */
Personagem removerInicio(){

   if(isVazia() == true){
      exit(1);
   }

   Personagem removido = primeiro->prox->e;
   
   Celula* tmp = primeiro;
   primeiro = primeiro->prox;
   primeiro->ante = NULL;
   tmp = tmp->prox = NULL;
   primeiro->e = pVazio;   
   free(tmp);

   n--;

   return removido;
}


/**
 * Insere Elemento no fim da Lista.
 * @param s - elemento a inserir.
 */
void inserirFim(char* s){

   ler(s);
   
   ultimo->prox = newCelula(p);
   ultimo->prox->ante = ultimo;
   ultimo = ultimo->prox;

   n++;
}


/**
 * Remove elemento do fim da Lista.
 * @return item removido
 */
Personagem removerFim(){

   if(isVazia() == true){
      exit(1);
   }

   Personagem removido = ultimo->e;

   ultimo = ultimo->ante;
   ultimo->prox->ante = NULL;
   free(ultimo->prox);
   ultimo->prox = NULL;

   n--;

   return removido;
}


/**
 * Insere elemento em uma posicao especifica da Lista
 * @param s - elemento a inserir
 * @param pos - posicao a inserir
 */
void inserir(char* s, int pos){   

   int tam = n;

   if(0 > pos || pos > tam){
      exit(1);
   } else if(pos == 0){
      inserirInicio(s);
   } else if(pos == tam){
      inserirFim(s);
   } else{

      ler(s);

      Celula* i = primeiro;

      for(int j = 0 ; j < pos ; j++, i = i->prox);

      Celula* tmp = newCelula(p);
      tmp->ante = i;
      tmp->prox = i->prox;
      tmp->ante->prox = tmp->prox->ante = tmp;
      tmp = i = NULL;

      free(tmp);
      free(i);

      n++;
   }  
}


/**
 * Remove elemento em uma posicao especifica da Lista
 * @param pos - posicao a inserir
 * @return item removido
 */
Personagem remover(int pos){

   int tam = n;
   Personagem removido;

   if(primeiro == ultimo){
      exit(1);
   } else if(0 > pos || pos >= tam){
      exit(1);
   } else if(pos == 0){
      removerInicio();
   } else if(pos == tam - 1){
      removerFim();
   } else{
     
      Celula* i = primeiro->prox;
      for(int j = 0 ; j < pos ; j++, i = i->prox);      

      i->ante->prox = i->prox;
      i->prox->ante = i->ante;
      Personagem removido = i->e;
      i->prox = i->ante = NULL;
      i = NULL;

      free(i);

      n--;
   } 

   return removido;
}

// ------------------------------------ mostrar

/**
 * Mostra elementos da Lista.
 */
void mostrar(){

   Celula* i = primeiro->prox;

   for (int a = 0 ; i != NULL ; i = i->prox, a++){
      //printf("[%d] ", a);
      p = i->e;
      imprimir();
   }
   
}

/**
 * Verifica se a Lista esta vazia.
 * @return true se estiver vazia.
 */
bool isVazia(){
   return primeiro == ultimo;
}

/**
 * Libera memoria alocada.
 * Liberar sempre no final.
 */
void liberar(){

   if(isVazia() == true){
      exit(1);
   }

   Celula* tmp;

   while(primeiro != ultimo){

      tmp = primeiro;
      primeiro = primeiro->prox;
      primeiro->ante = NULL;
      tmp->prox = NULL;
      free(tmp);
   }

}

// ------------------------------------ Ordenacao

/**
 * Troca elementos da lista de posicao.
 * @param a - indice (inteiro)
 * @param b - indice (inteiro)
 */
void swap(Celula* a, Celula* b){

   Personagem tmp = a->e;
   a->e = b->e;
   b->e = tmp;
}

// ---------------- Quicksort

/**
 * Chamada do Quicksort.
 */
void quickS(){

   cmp = 0;
   mov = 0;
   quickSort(primeiro->prox, ultimo, 0, (n - 1));
}


/**
 * Ordenacao por Quicksort. (Chave de Ordenacao Cor do Cabelo).
 * @param esq - inicio vetor
 * @param dir - final  vetor
 */

void quickSort(Celula* esq, Celula* dir, int ini, int fim){

   Celula* i = esq;
   Celula* j = dir;

   int a = ini;
   int b = fim;

   Celula* pivo = esq;

   while(a <= b){

      while(strcmp(i->e.corCabelo, pivo->e.corCabelo) < 0){

         cmp++;
         i = i->prox;
         a++;
      }

      while(strcmp(j->e.corCabelo, pivo->e.corCabelo) > 0){

         cmp++;
         j = j->ante;
         b--;
      }

      if(a <= b){

         mov += 3;
         swap(i, j);

         i = i->prox;
         j = j->ante;
         a++;
         b--;
      }
   }

   if(ini < b){
      quickSort(esq, j, ini, b);
   }

   if(a < fim){
      quickSort(i, dir, a, fim);
   }
}


/**
 * Ordenacao por Quicksort. (Chave de Ordenacao Nome).
 * @param esq - inicio vetor
 * @param dir - final  vetor
 */

void quickSortNome(Celula* esq, Celula* dir, int ini, int fim){

   Celula* i = esq;
   Celula* j = dir;

   int a = ini;
   int b = fim;

   Celula* pivo = esq;

   while(a <= b){

      while(strcmp(i->e.nome, pivo->e.nome) < 0){

         cmp++;
         i = i->prox;
         a++;
      }

      while(strcmp(j->e.nome, pivo->e.nome) > 0){

         cmp++;
         j = j->ante;
         b--;
      }

      if(a <= b){

         mov += 3;
         swap(i, j);

         i = i->prox;
         j = j->ante;
         a++;
         b--;
      }
   }

   if(ini < b){
      quickSortNome(esq, j, ini, b);
   }

   if(a < fim){
      quickSortNome(i, dir, a, fim);
   }

}

/**
 * Desempate do quickSort corCabelo.
 */

void desempateQuick(){

      Celula* p = primeiro->prox;

      if(n >= 2){

         Celula* ref_ini = p->prox; 
         Celula* ref_ter = p->prox;
         Celula* i = p->prox;
         Celula* j = p->prox;

         int ini = 0;
         int ter = 0;
         int a = 0; // i
         int b = 0; // j

         while(i != NULL){

            if(strcmp(i->e.corCabelo, i->ante->e.corCabelo) == 0){

               b = a-1;
               ini = ter = a;

               j = i;
               ref_ini = ref_ter = i->ante;
               
               ter++;

               cmp++;

               while((j != NULL) && (strcmp(j->e.corCabelo, j->ante->e.corCabelo) == 0)){

                  ter++;
                  b++; 

                  ref_ter = j;
                  i = j;
                  j = j->prox;

                  cmp++;
               }

               ter--;
 
               /**
                * intervalos: 
                *
                * [01 - 08]
                * [09 - 19]
                * [21 - 22]
                * [23 - 39]
                */

               quickSortNome(ref_ini, ref_ter, ini, ter);               

               a = b;
            }

            a++;
            i = i->prox;
         }        
      }
}


// ------------------------------------ 

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

