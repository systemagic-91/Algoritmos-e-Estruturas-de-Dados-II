/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP04 - Questao 07 - Hash Indireta com Lista Simples em C
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

int fun_hash(int e);
void inserir(char* s);
bool pesquisar(char* s);

void mostrar();
void start(int tam);
void startVazio();

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

};

int tamanho;
int cmp;
int mov;

Personagem p;
Personagem pVazio;
Celula* hash;

void start(int tam){
 
   tamanho = tam;  
   hash = (Celula*)malloc(tamanho * sizeof(Celula));

   startVazio();
   
   cmp = 0;
   mov = 0;

   for (int i = 0 ; i < tamanho ; i++){

      hash[i].e = pVazio;
      hash[i].prox = NULL;
   }
}

void startVazio(){   

   char nulo[5] = "NULL";
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

   start(25);

   char entrada[NUMENTRADA][TAMLINHA];
   char pesquisa[NUMENTRADA][TAMLINHA];
   int numEntrada = 0;
   int numPesquisa = 0;

   do{

      scanf("%s", entrada[numEntrada]);     

   }while(isFim(entrada[numEntrada++]) == false);

   numEntrada--;

   for(int i = 0 ; i < numEntrada ; i++){
      inserir(entrada[i]);
  }


   do{

      fgets(pesquisa[numPesquisa], TAMLINHA, stdin);     
      pesquisa[numPesquisa][strcspn(pesquisa[numPesquisa], "\n")] = '\0';

   }while(isFim(pesquisa[numPesquisa++]) == false);

   numPesquisa--;

   char log[100];
   clock_t t1, t2;
   float tempo;
   FILE *arq = fopen("matricula_hashIndireta.txt", "w");

   t1 = clock();
   for(int i = 1 ; i < numPesquisa ; i++){

      printf("%s ", pesquisa[i]);

      if(pesquisar(pesquisa[i]) == true){
         printf("SIM\n");
      } else {
         printf("NÃO\n");
      }
   }
   t2 = clock();

   tempo = (((float)t2 - (float)t1) / 100000.0F);

   snprintf(log, 100, "635383\t %6fms\t Comp: %d\t Mov: %d\n", tempo, cmp, mov);
   fputs(log, arq);

   fclose(arq);

   return 0;
}

bool isFim (char* s){    
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

// ------------------------------------------------------------- Hash

// ------------------------------------ Metodos da Celula

/**
 * Cria uma nova celula com um personagem 
 * @param e - personagem
 * @return ponteiro para nova celula.
 */
Celula* newCelula(Personagem elm){

   Celula* nova = (Celula*)malloc(sizeof(Celula));
   nova->e = elm;
   nova->prox = NULL;

   return nova;
}

/**
 * Calcula a posicao de um personagem na tabela.
 * @param e - inteiro
 */
int fun_hash(int e){
   return e % tamanho;
}

/**
 * Insere personagem na tabela.
 * @param s - personagem
 */
void inserir(char* s){

   ler(s);

   int i = fun_hash(p.altura);

   
   if(strcmp(hash[i].e.nome, "NULL") == 0){
      
      hash[i].e = p;
      mov++;

   } else {
      
      Celula* tmp = &hash[i];      
         
      for( ; tmp->prox != NULL ; tmp = tmp->prox);
      tmp->prox = newCelula(p);
      tmp = NULL;
      mov++;
   }
}

// ------------------------------------ Pesquisa

/**
 * Pesquisa elemento na lista.
 * @param s - elemento
 * @return verdadeiro se o elemento existir
 */
bool pesquisar(char* s){

   bool resp = false;

   for(int i = 0 ; i < tamanho ; i++){

      Celula* tmp = &hash[i];

      for( ; tmp != NULL && i < tamanho ; tmp = tmp->prox){

         cmp++;
         if(strcmp(tmp->e.nome, s) == 0){

            resp = true;            
            i = tamanho;
         }
      }
   }

   return resp;
}


// ------------------------------------ mostrar

/**
 * Mostra a tabela.
 */
void mostrar(){

   for(int i = 0 ; i < tamanho ; i++){

      Celula* tmp = &hash[i];

      for ( ; tmp != NULL ; tmp = tmp->prox){
        
         if(strcmp(tmp->e.nome, "NULL") != 0){
            printf("%s - ", tmp->e.nome);         
         }
      }
   }
}


// ------------------------------------ 
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



