public class No{

    public Personagem elemento;
    public No esq;
    public No dir;
    public boolean cor;
 
    public No(Personagem e){
       this(e, null, null, false);
    }
 
    public No(Personagem e, boolean cor){
       this(e, null, null, cor);
    }
 
    public No(Personagem e, No eq, No dr, boolean c){
 
       elemento = e;
       esq = eq;
       dir = dr;
       cor = c;
    }
 
}
 