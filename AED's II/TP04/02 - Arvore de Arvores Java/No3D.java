public class No3D{

    public Personagem elemento;
    public No3D esq;
    public No3D dir;
 
    public No3D(Personagem e){
       this(e, null, null);
    }
 
    public No3D(Personagem e, No3D eq, No3D dr){
 
       elemento = e;
       esq = eq;
       dir = dr;
    }
 
}