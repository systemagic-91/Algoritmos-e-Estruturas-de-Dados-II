/**
 * Pontificia Universidade Catolica de Minas Gerais
 * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
 *
 * TP04 - Questao 03 - AVL em C
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

typedef struct st_No No;

No* newNo(Personagem e);
void setNivel(No* i);
int getNivel(No* i);
int max(int i, int j);

void start();
void statVazio();

void inserir(char* s);
No* inserirRec(Personagem e, No* i);
No* balancear(No* i);
No* rotacionarDir(No* i);
No* rotacionarEsq(No* i);

bool pesquisar(char* s);
bool pesquisarRec(char* s, No* i);

void mostrarCentral();
void mostrarCentralRec(No* i);

bool isVazia();
void liberar(No* i);

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

struct st_No{
   
   Personagem e;
   int nivel;
   No* esq;
   No* dir;
};

int n = 0;
int cmp = 0;
int mov = 0;

Personagem p;
Personagem pVazio;
No* raiz;

void start(){   
   raiz = NULL;
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
   FILE *arq = fopen("matricula_avl.txt", "w");

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

   //mostrarCentral();
   liberar(raiz);

   return 0;
}

bool isFim (char* s){    
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

// ------------------------------------------------------------- Metodos AVL de Personagem

// ------------------------------------ Metodos do NO

/**
 * Cria uma novo no com um personagem.
 * @param elm - personagem
 */
No* newNo(Personagem elm){

   No* nova = (No*)malloc(sizeof(No));
   nova->e = elm;
   nova->nivel = 1;
   nova->esq = NULL;
   nova->dir = NULL;

   return nova;
}

/**
 * Retorna o maior entre dois valores inteiros.
 * @param i - inteiro
 * @param j - inteiro
 * @return maior valor.
 */
int max(int i, int j){
   return (i > j) ? i : j;
}

/**
 * Calcula o nivel do no.
 * @param i - no há calcular nivel 
 */
void setNivel(No* i){
   i->nivel = 1 + max(getNivel(i->esq), getNivel(i->dir));   
}

/**
 * Retorna o nivel do no.
 * @param i - no
 * @return nivel
 */
int getNivel(No* i){
   return (i == NULL) ? 0 : i->nivel;
}


// ------------------------------------ Insercão


/**
 * Chamada recursiva prar inserir Elemento na Arvore
 * @param s - elemento a inserir  
 */
void inserir(char* s){
   ler(s);
   raiz = inserirRec(p, raiz);
}

/**
 * Insere elemento na arvore.
 * @param e - personagem
 * @param i - no raiz
 * @return endereco do novo no.
 */
No* inserirRec(Personagem e, No* i){

   if(i == NULL){
      i = newNo(e);
   } else if (strcmp(e.nome, i->e.nome) < 0){
      i->esq = inserirRec(e, i->esq);
   } else if (strcmp(e.nome, i->e.nome) > 0){
      i->dir = inserirRec(e, i->dir);
   } else {
      printf("ERRO - inserir!\n");
   }

   i = balancear(i);
   return i;
}

// ------------------------------------ balancear/rotacionar

/**
 * Verifica necessidade de balancear ou nao o No da
 * arvore.
 * @param i - no.
 * @return no balanceado.
 */
No* balancear(No* i){

   if(i != NULL){
       
      int fator = getNivel(i->dir) - getNivel(i->esq);

      if(abs(fator) <= 1){
         setNivel(i);
      } else if(fator == 2){
         
         int fatorFilhoDir = getNivel(i->dir->dir) - getNivel(i->dir->esq); 

         if(fatorFilhoDir == -1){
            i->dir = rotacionarDir(i->dir);
         }

         i = rotacionarEsq(i);

      } else if(fator == -2){

         int fatorFilhoEsq = getNivel(i->esq->dir) - getNivel(i->esq->esq); 

         if(fatorFilhoEsq == 1){
            i->esq = rotacionarEsq(i->esq);
         }

         i = rotacionarDir(i);

      } else {
         printf("ERRO - rotacionar - Fator: %d\n", fator);
      } 
   }

   return i;
}

/**
 * Rotaciona arvore para direita.
 * @param i - no
 * @return endereco do no rotacionado
 */
No* rotacionarDir(No* i){

   No* noEsq = i->esq;
   No* noEsqDir = noEsq->dir;

   noEsq->dir = i;
   i->esq = noEsqDir;

   setNivel(i);
   setNivel(noEsq);

   return noEsq;
}

/**
 * Rotaciona arvore para esquerda.
 * @param i - no
 * @return endereco do no rotacionado.
 */
No* rotacionarEsq(No* i){

   No* noDir = i->dir;
   No* noDirEsq = noDir->esq;

   noDir->esq = i;
   i->dir = noDirEsq;

   setNivel(i);
   setNivel(noDir);

   return noDir;
}

// ------------------------------------ Pesquisa

/**
 * Chamada recursiva para realizar uma pesquisa 
 * de elemento na arvore.
 * @param s - elemento a pesquisar
 * @return verdadeiro caso o elemento exista.
 */
bool pesquisar(char* s){
   printf("raiz ");
   return pesquisarRec(s, raiz);
}

/**
 * Realiza pesquisa de elemento na arvore.
 * @param s - elemento a pesquisar
 * @param i - raiz da arvore.
 * @return verdadeiro caso o elemento exista.
 */
bool pesquisarRec(char* s, No* i){

   bool resp = true;

   if(i == NULL){
      resp = false;
   } else if(strcmp(s, i->e.nome) == 0){
      cmp++;
      resp = true;
   } else if(strcmp(s, i->e.nome) < 0){
      cmp++;
      printf("esq ");
      resp = pesquisarRec(s, i->esq);
   } else {
      printf("dir ");
      resp = pesquisarRec(s, i->dir);
   }

   return resp;
}

// ------------------------------------ mostrar

/**
 * Chamada recursiva para mostrar elementos da Arvore.
 */
void mostrarCentral(){
   mostrarCentralRec(raiz);
}

/**
 * Mostra elementos da Arvore.
 */
void mostrarCentralRec(No* i){
   
   if(i != NULL){
      mostrarCentralRec(i->esq);
      p = i->e;
      imprimir();
      mostrarCentralRec(i->dir);
   }
}

/**
 * Verifica se a Arvore esta vazia.
 * @return true se estiver vazia.
 */
bool isVazia(){
   return raiz == NULL;
}

/**
 * Libera memoria alocada.
 */
void liberar(No* i){
   
   if(i != NULL){
      liberar(i->esq);
      liberar(i->dir);
      free(i);
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


/**
 * Retorna o tamanho da lista.
 * @return inteiro.
 */
int length(){
   return n;
}