public class No{

    public Personagem elemento;
    public No esq;
    public No dir;
 
    public No(Personagem e){
       this(e, null, null);
    }
 
    public No(Personagem e, No eq, No dr){
 
       elemento = e;
       esq = eq;
       dir = dr;
    }
 
}
 