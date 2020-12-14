public class Matriz{

    // ------------------------------------- Atributos
    
    private Celula inicio;
    private int linhas;
    private int colunas;
    
    // ------------------------------------- Construtores
 
    /**
     * Construtor Padrao.
     */
    public Matriz(){
       this(3,3);
    }
 
    /**
     * Construtor alternativo.
     * @param linhas - inteiro 
     * @param colunas - inteiro
     */
    public Matriz(int linhas, int colunas){
 
       this.linhas = linhas;
       this.colunas = colunas;
 
       inicio = gerarLinha();
 
       Celula ref = inicio;
 
       for(int i = 0 ; i < (linhas - 1) ; i++, ref = ref.inf){
 
          Celula l = gerarLinha();
          conectarLinhas(ref, l);
       }
    }
 
    /**
     * Gera uma linha da matriz.
     * @return referencia para primeira celula
     */
    private Celula gerarLinha(){
 
       Celula inicio = new Celula();
       Celula ref = inicio;
 
       for (int i = 0 ; i < (colunas - 1) ; i++){
 
          ref.dir = new Celula();
          ref.dir.esq = ref;
          ref =  ref.dir;
       }
 
       ref = null;
 
       return inicio;
    }
 
    /**
     * Conecta duas linhas da matriz.
     * @param superior - referencia para linha superior
     * @param inferior - referencia para linha inferior
     */
    private void conectarLinhas(Celula superior, Celula inferior){
 
       for(Celula i = superior, j = inferior ; i != null ; i = i.dir, j = j.dir){
 
          i.inf = j;
          j.sup = i;
       }
    }
 
    // ------------------------------------- getters
  
    public Celula getInicio(){ return inicio; }
    public int getLinhas(){ return linhas; }
    public int getColunas(){ return colunas; }
 
    // ------------------------------------- leitura dos dados
    
    /**
     * Preenche a matriz.
     * @param a - array de string com os elementos da matriz.
     */
    public void preencher(String [] a){
       
       int c = 0;
 
       for(Celula i = inicio ; i != null ; i = i.inf){
 
          for(Celula j = i ; j != null ; j = j.dir){
             
             j.elemento = Integer.parseInt(a[c]);
             c++;
          }
       }
 
    }
    
    // ------------------------------------- Mostrar
 
    /**
     * Mostra a matriz.
     */    
    public void mostrar(){
       MyIO.print(toString());
    }
 
    @Override
    public String toString(){
 
       final String LINE_SEPARATOR = System.getProperty("line.separator");
       String str = "";
 
       for(Celula i = inicio ; i != null ; i = i.inf){
 
          for(Celula j = i ; j != null ; j = j.dir){
             str += j.elemento + " ";
          }
          str += LINE_SEPARATOR;   
       }
 
       return str;
    }
 
    // ------------------------------------- diagonal principal
    
    /**
     * Mostra a diagonal principal de uma matriz.
     */
    public void diagP(){
 
       String str = "";
 
       if(isQuadrada()){
 
          int a = 0;
          int b = 0;
 
          for(Celula i = inicio ; i != null ; i = i.inf, a++){
 
             for(Celula j = i ; j != null ; j = j.dir, b++){            
                if(a == b){
                   str += j.elemento + " ";
                }
             }
 
             b = 0;
          }
 
       }
 
       MyIO.println(str);
    }
 
 
    // ------------------------------------- diagonal secundaria
 
    /**
     * Mostra a diagonal secundaria de uma matriz.
     */
     public void diagS(){
 
       String str = "";
 
       if(isQuadrada()){
 
          int a = 0;
          int b = 0;
 
          for(Celula i = inicio ; i != null ; i = i.inf, a++){
 
             for(Celula j = i ; j != null ; j = j.dir, b++){            
                if((a + b) == (linhas - 1)){
                   str += j.elemento + " ";
                }
             }
 
             b = 0;
          }
       }
 
       MyIO.println(str);
     }
 
    // ------------------------------------- isQuadrada
    
    /**
     * Verifica se a matriz e quadrada.
     * @return verdadeiro se a matriz for quadrada.
     */
    public boolean isQuadrada(){
       return (linhas == colunas);
    }
 
    // ------------------------------------- soma
 
    /**
     * Soma duas matrizes.
     * @param m - matriz a somar
     * @return matriz soma
     */
    public Matriz soma(Matriz m){
 
       Matriz r = null;
 
       int ln = m.getLinhas();
       int cl = m.getColunas();
 
       if(linhas == ln && colunas == cl){
 
          r = new Matriz(ln, cl);
 
          Celula a;
          Celula b = m.getInicio();
          Celula c = r.getInicio();
 
          Celula d;
          Celula e;
          Celula f;
 
          for(a = inicio ; a != null ; a = a.inf, b = b.inf, c = c.inf){
 
             for(d = a, e = b, f = c ; d != null ; d = d.dir, e = e.dir, f = f.dir){
 
                f.elemento = d.elemento + e.elemento;
             }
          }
       }
 
       return r;
    }
 
    // ------------------------------------- multiplicacao
 
    /**
     * Multiplica duas matrizes.
     * @param m - matriz a multiplicar
     * @return matriz produto
     */
    public Matriz multiplica(Matriz m){
 
       int elemento;
       Matriz r = new Matriz(linhas, m.getColunas());
 
       Celula pM1Linha;
       Celula pM1Coluna;
 
       Celula pM2Linha;
       Celula pM2Coluna;
 
       Celula pMRespL = r.getInicio();
       Celula pMRespC = r.getInicio();
 
       for (pM1Linha = this.inicio ; 
            pM1Linha != null ; 
            pM1Linha = pM1Linha.inf, pMRespL = pMRespL.inf){ 
 
          for (pM2Coluna = m.getInicio(), pMRespC = pMRespL ; 
               pM2Coluna != null ; 
               pM2Coluna = pM2Coluna.dir, pMRespC = pMRespC.dir){ 
            
            elemento = produtoLC(pM1Linha, pM2Coluna);
            pMRespC.elemento = elemento;
            
          }
       }
 
       return r;
    }
 
    private int produtoLC(Celula linha, Celula coluna){
 
       int e = 0;
 
       for(Celula i = linha, j = coluna ; i != null ; i = i.dir, j = j.inf){
          
          e += i.elemento * j.elemento;
       }
 
       return e;
    }  
}