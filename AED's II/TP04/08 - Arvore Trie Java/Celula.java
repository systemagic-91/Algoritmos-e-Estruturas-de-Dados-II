public class Celula{

    public String elemento;
    public Celula prox;
 
    public Celula(){
       this("");
    }
 
    public Celula (String e){
 
       this.elemento = e;
       this.prox = null;
    }
}