public class No{

    public int elemento;
    public No esq;
    public No dir;
 
    public No3D raiz3D;
 
    public No(int e){
       this(e, null, null, null);
    }
 
    public No(int e, No eq, No dr, No3D n3D){
 
       elemento = e;
       esq = eq;
       dir = dr;
       raiz3D = n3D;
    }
 }