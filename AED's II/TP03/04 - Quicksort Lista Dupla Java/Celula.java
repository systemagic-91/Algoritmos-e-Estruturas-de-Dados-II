public class Celula{

    public Personagem elemento;
    public Celula prox;
    public Celula ante;
 
    public Celula(){
       this(null);
    }
 
    public Celula (Personagem e){
 
       elemento = e;
       prox = null;
       ante = null;
    }
}