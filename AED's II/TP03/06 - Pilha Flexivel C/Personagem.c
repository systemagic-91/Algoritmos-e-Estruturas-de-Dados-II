/**
  * Pontificia Universidade Catolica de Minas Gerais
  * Ciencia da Computacao - Algoritmos e Estrutura de Dados II
  *
  * TP03 - Questao 06 - Pilha Flexivel em C
  *  
  * @author - Rayane Paiva Reginaldo
  * @version - 0.1
  */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
void empilhar(char* s);
Personagem desempilhar();
bool isVazia();
void mostrar();
void mostrarOrdem();
void mostrarR(Celula* i, int a);
void liberar();

void tarefa(char* s);
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

Celula* topo = NULL;
Personagem p;
int n = 0;

// -------------------------------------------------------------

int main( int argc, char** argv){

   char entrada[NUMENTRADA][TAMLINHA];
   int numEntrada = 0;

   do{

      scanf("%s", entrada[numEntrada]);     

   }while(isFim(entrada[numEntrada++]) == false);

   numEntrada--;

   for(int i = 0 ; i < numEntrada ; i++){
      empilhar(entrada[i]);
   }

   char comandos[NUMENTRADA][TAMLINHA];
   int j = 0;

   int nComandos;

   scanf("%d", &nComandos);

   while (nComandos >= 0){

      fgets(comandos[j], TAMLINHA, stdin);
      comandos[j][strcspn(comandos[j], "\n")] = '\0'; // elimina '\n'
      nComandos--;
      j++;
   }

   for (int i = 1 ; i < j ; i++){
      tarefa(comandos[i]);
   }

   mostrarOrdem();
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
 * Insere Elemento no inicio da Pilha.
 * @param s - elemento a inserir  
 */
void empilhar(char* s){

   ler(s);
   
   Celula* tmp = newCelula(p);
   tmp->prox = topo;
   topo = tmp;
   tmp = NULL;

   free(tmp);

   n++;
 }


/**
 * Remove Elemento do inicio da Pilha
 * @return item removido
 */
Personagem desempilhar(){

   if(isVazia() == true){
      exit(1);
   }

   Personagem removido = topo->e;

   Celula* tmp = topo;
   topo = topo->prox;
   tmp->prox = NULL;
   tmp = NULL;
   free(tmp);

   n--;

   return removido;
}

bool isVazia(){
   return topo == NULL;
}

// ------------------------------------ mostrar

/**
 * Mostra elementos da Pilha.
 */
void mostrar(){

   Celula* i;
   int c = 0;

   for(i = topo ; i != NULL; i = i->prox){
      p = i->e;
      printf("[%d] ", c++);
      imprimir();
   }
}

/**
 * Chamada da recursividade.
 */
void mostrarOrdem(){
   mostrarR(topo, (n-1));
}

/**
 * Mostra elementos da Pilha recursivamente.
 */
void mostrarR(Celula* i, int c){
   
   if(i != NULL){

      mostrarR(i->prox, (c-1));

      p = i->e;
      printf("[%d] ", c);
      imprimir();
   }
}

/**
 * Libera a Pilha da memoria.
 */
void liberar(){

   if(isVazia() == true){
      exit(1);
   }

   Celula* tmp;

   while(topo != NULL){
      tmp = topo;
      topo = topo->prox;
      tmp->prox = NULL;
      free(tmp);      
   }
 }

// ------------------------------------ mostrar

/**
 * Realiza sequencia de comandos.
 * @param s - comandos
 */
void tarefa(char* s){
   
   int comando;
   char* persona;   
   Personagem a;

   if(s[0] == 'I'){
      comando = 1;
   } else if(s[0] == 'R'){
      comando = 2;
   }

   switch (comando) {

      case 1:

         persona = substring(s, 2, (strlen(s)));
         empilhar(persona);
         free(persona);
         break;

      case 2:

         a = desempilhar();
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

