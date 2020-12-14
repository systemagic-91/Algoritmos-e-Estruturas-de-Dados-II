public class No{

    public char elemento;   
    public No[] prox;
 
    public int tamanho = 255;
    public boolean isFolha;
 
    public No(){
       this(' ');
    }
 
    public No(char c){
 
       elemento = c;
       prox = new No[tamanho];
 
       for(int i = 0 ; i < tamanho ; i++){
          prox[i] = null;
       }
 
       isFolha = false;
    }
 
    public static int hash (char c){
       return (int)(c);
    }
} 