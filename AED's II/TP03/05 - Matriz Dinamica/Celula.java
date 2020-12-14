public class Celula{

    public int elemento;
 
    public Celula dir;
    public Celula esq;
    public Celula sup;
    public Celula inf;
 
    public Celula(){
       this(0);
    }
 
    public Celula (int e){
 
       elemento = e;
       esq = null;
       dir = null;
       sup = null;
       inf = null;
    }
}