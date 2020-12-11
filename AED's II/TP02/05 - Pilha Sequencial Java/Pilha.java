public class Pilha {

    // ----------------------------------------------------------------------- Atributos
 
    private Personagem pilha [];
    private int n;
 
    // ----------------------------------------------------------------------- Construtores
 
    /**
     * Construtor Padrao
     */
    public Pilha(){
       this(1000);
    }
 
    /**
     * Construtor Secundario, controi a Pilha de acordo com 
     * o tamanho passado por paramento.
     * @param i - inteiro maior que zero.
     */
    public Pilha(int i){
       
      try {
 
          n = 0;
          pilha = new Personagem [i];
 
      } catch (NegativeArraySizeException e1){
         MyIO.println("CONSTRUTOR ===> " + e1.toString());
      } finally {
      }    
    }
 
    // --------------------------------------------------------------- Clone
 
    /**
     * Retorna um clone do objeto que chama.
     * @return objeto do tipo Pilha.
     */   
    public Pilha myClone(){
 
       Pilha lst = new Pilha(this.pilha.length);
       
       lst.pilha = this.pilha;
 
       return lst;
    }
 
    // --------------------------------------------------------------- Insersao/Remocao
    
 
    /**
     * Insere um Persoangem na ultima posicao disponivel da pilha
     * @param e - Personagem    
     */
    public void inserirFim(Personagem e){
 
       try{
 
          pilha[n] = e;
          n++;
 
       } catch (ArrayIndexOutOfBoundsException e1){
          MyIO.println("INSERIR NO FIM ===> " + e1.toString());
       } finally {
       }
    }
 
    /**
     * Remove Personagem na ultima posicao preenchida da pilha.
     * @return Personagem removido
     */
    public Personagem removerFim(){
 
       Personagem removido = null;
 
       try{
 
          removido = pilha[n-1];
          n--;
 
       } catch (ArrayIndexOutOfBoundsException e1){
          MyIO.println("REMOVER DO FIM ===> " + e1.toString());
       } finally {         
       }
 
       return removido;
    }
 
 
    // --------------------------------------------------------------- Mostrar
    
    /**
     * Mostra os elementos da pilha em ordem.
     */
    public void mostrar(){
 
       for (int i = 0 ; i < n ; i++){
         MyIO.print("[" + i + "] ");
         pilha[i].imprimir();
       }
    }
 
    /**
     * Mostra os elementos da pilha em ordem inversa.
     */
    public void mostrarInverso(){
 
       for (int i = (n - 1) ; i >= 0 ; i--){
          pilha[i].imprimir();
       }
    }
 
    // --------------------------------------------------------------- 
 
   /**
     * Metodo para realizar insercoes/remocoes de acordo com
     * os comando da entrada padrao.
     * @param s - comando
     */
    public void tarefa(String s){
 
       MyIO.setCharset("UTF-8");
 
       String comando = s.substring(0,1);
       Personagem t;
       int p;
 
       switch (comando){
 
          case "I":
 
             t = new Personagem();
             t.ler(s.substring(2));
             inserirFim(t);
             break;
 
          case "R":
 
             t = removerFim();
             MyIO.println("(R) " + t.getNome());
             break;
       }
    }
 
 }
 