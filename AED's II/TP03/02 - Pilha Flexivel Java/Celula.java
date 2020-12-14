public class Celula{

    public Personagem elemento;
    public Celula prox;
 
    public Celula(){
       this(null);
    }
 
    public Celula (Personagem e){
 
       this.elemento = e;
       this.prox = null;
    }
}
 