import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Personagem{

    private String dados;
 
    // ----------------------------------------------------------------------- Atributos
  
    private String nome;
    private int altura;
    private double peso;
    private String corCabelo;
    private String corPele;
    private String corOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;
 
    // ----------------------------------------------------------------------- Construtores
 
    /**
     * Construtor Padrao.
     */
    public Personagem (){
 
       nome = "";
       altura = 0;
       peso = 0;
       corCabelo = "";
       corPele = "";
       corOlhos = "";
       anoNascimento = "";
       genero = "";
       homeworld = "";
    }
 
    /**
     * Construtor secundario
     * @param nome - string
     * @param altura - inteiro
     * @param peso - double
     * @param corCabelo - string
     * @param corPele - string
     * @param corOlhos - string
     * @param anoNascimento - string
     * @param genero - string
     * @param homeworld - string
     */
    public Personagem (String nome, int altura, double peso, String corCabelo, 
                       String corPele, String corOlhos, String anoNascimento,
                       String genero, String homeworld){
 
       this.nome = nome;
       this.altura = altura;
       this.peso = peso;
       this.corCabelo = corCabelo;
       this.corPele = corPele;
       this.corOlhos = corOlhos;
       this.anoNascimento = anoNascimento;
       this.genero = genero;
       this.homeworld = homeworld;
    }
  
 
    // ----------------------------------------------------------------------- Getters
 
    public String getDados() { return dados; }
    public String getNome() { return nome; }
    public int getAltura() { return altura; }
    public double getPeso() { return peso; }
    public String getCorCabelo() { return corCabelo; }
    public String getCorPele() { return corPele; }
    public String getCorOlhos() { return corOlhos; }
    public String getAnoNascimento() { return anoNascimento; }
    public String getGenero() { return genero; }
    public String getHomeworld() { return homeworld; }
 
    // ----------------------------------------------------------------------- Setters
  
    public void setDados(String dados){
       this.dados = dados;
    }
 
    public void setNome(String nome){
       this.nome = nome;
    }
 
    public void setAltura(int altura){
       this.altura = altura;
    }
 
    public void setPeso(double peso){
       this.peso = peso;
    }
 
    public void setCorCabelo(String corCabelo){
       this.corCabelo = corCabelo;
    }
 
    public void setCorPele(String corPele){
       this.corPele = corPele;
    }
 
    public void setCorOlhos(String corOlhos){
       this.corOlhos = corOlhos;
    }
 
    public void setAnoNascimento(String anoNascimento){
       this.anoNascimento = anoNascimento;
    }
 
    public void setGenero(String genero){
       this.genero = genero;
    }
 
    public void setHomeworld(String homeworld){
       this.homeworld = homeworld;
    }
   
    // ----------------------------------------------------------------------- Clone
 
    /**
     * Retorna um clone 
     */
    public Personagem clone(Personagem p){
 
       Personagem outro = new Personagem();
 
       outro.setDados(p.getDados());
       outro.setNome(p.getNome());
       outro.setAltura(p.getAltura());
       outro.setPeso(p.getPeso());
       outro.setCorCabelo(p.getCorCabelo());
       outro.setCorPele(p.getCorPele());
       outro.setCorOlhos(p.getCorOlhos());
       outro.setAnoNascimento(p.getAnoNascimento());
       outro.setGenero(p.getGenero());
       outro.setHomeworld(p.getHomeworld());
 
       return outro;
    }
 
    // ----------------------------------------------------------------------- Imprimir
    
    /**
     * Imprime os atributos da classe.
     */
    public void imprimir(){
      
       MyIO.setCharset("UTF-8");       
 
       MyIO.print(" ## ");
       MyIO.print(getNome() + " ## ");
       MyIO.print(getAltura() + " ## ");
 
       if(getPeso() % 1 == 0) {      
          MyIO.print((int)(getPeso()) + " ## ");
       } else {
          MyIO.print((getPeso()) + " ## ");
       }
 
       MyIO.print(getCorCabelo() + " ## ");
       MyIO.print(getCorPele()+ " ## ");
       MyIO.print(getCorOlhos() + " ## ");
       MyIO.print(getAnoNascimento() + " ## ");
       MyIO.print(getGenero() + " ## ");
       MyIO.println(getHomeworld() + " ## ");
    }   
    
    // ----------------------------------------------------------------------- Ler
 
    /**
     * Realiza leitura dos dados de um arquivo.
     * @param s - string com endereco do diretorio do arquivo    
     */
    public void ler(String s) {
 
       String dados = "";
       String linha;
 
       try {
 
          FileReader f = new FileReader(s);
          BufferedReader b = new BufferedReader(f);
          
          while(b.ready()){
 
             linha = b.readLine();
             dados += linha;
          }
 
          this.dados = dados;
 
          b.close();
          f.close();
 
       } catch (IOException e) {
 
          MyIO.println(e.toString());
 
       } finally{
       }  
 
       limparAspasSimples();
       lerNome();
       lerAltura();
       lerPeso();
       lerCorCabelo();
       lerCorPele();
       lerCorOlhos();
       lerAnoNascimento();
       lerGenero();
       lerHomeworld();
    }
 
    // ----------------------------------------------------------------------- Leitura separada dos dados.
 
    /**
     * Remove aspas simples da string.
     */
    private void limparAspasSimples(){ 
       dados = dados.replaceAll("\\'", "");       
    }
    
 
    /**
     * Busca o atributo Nome.
     */
    private void lerNome(){
 
       String nome;
 
       int pInicio = dados.indexOf("name:");
       int pFinal = dados.indexOf(",", pInicio);
 
       nome = dados.substring(pInicio, pFinal);
       nome = nome.replaceAll("name:", "").trim();
 
       this.nome = nome;
    }
 
 
    /**
     * Busca o atributo altura.
     */
    private void lerAltura(){
 
       int altura;
       String alt;
 
       int pInicio = dados.indexOf("height:");
       int pFinal = dados.indexOf(",", pInicio);
 
       alt = dados.substring(pInicio, pFinal);
       alt = alt.replaceAll("height:", "").trim();
 
       if(alt.equals("unknown")){
          alt = "0";
       }
 
       try{
 
          this.altura = Integer.parseInt(alt);
 
       } catch (NumberFormatException e){
 
          MyIO.println("METODO LER ALTURA() ====> " + e.toString());
 
       } finally{
       }
    }
 
 
    /**
     * Busca o atributo peso.
     */
    private void lerPeso(){
 
       double peso;
       String ps;
 
       int pInicio = dados.indexOf("mass:");
       int pFinal = dados.indexOf(", hair_color:", pInicio);
 
       ps = dados.substring(pInicio, pFinal);
       ps = ps.replaceAll("mass:", "").trim();
       ps = ps.replaceAll("1,358", "1358");
 
       if(ps.equals("unknown")){
          ps = "0";
       }
 
       try{
 
          this.peso = Double.parseDouble(ps);
 
       } catch (NumberFormatException e){
 
          MyIO.println("METODO LER PESO() ====> " + e.toString());
 
       } finally{
       }
    }
 
 
    /**
     * Busca o atributo Cor do Cabelo.
     */
    private void lerCorCabelo(){
 
       String cor;
 
       int pInicio = dados.indexOf("hair_color:");
       int pFinal = dados.indexOf(", skin_color:", pInicio);
 
       cor = dados.substring(pInicio, pFinal);
       cor = cor.replaceAll("hair_color:", "").trim();
 
       this.corCabelo = cor;
    }
 
    /**
     * Busca o atributo Cor da Pele.
     */
    private void lerCorPele(){
 
       String cor;
 
       int pInicio = dados.indexOf("skin_color:");
       int pFinal = dados.indexOf(", eye_color:", pInicio);
 
       cor = dados.substring(pInicio, pFinal);
       cor = cor.replaceAll("skin_color:", "").trim();
 
       this.corPele = cor;
    }
 
    /**
     * Busca o atributo Cor dos Olhos.
     */
    private void lerCorOlhos(){
 
       String cor;
 
       int pInicio = dados.indexOf("eye_color:");
       int pFinal = dados.indexOf(", birth_year:", pInicio);
 
       cor = dados.substring(pInicio, pFinal);
       cor = cor.replaceAll("eye_color:", "").trim();
 
       this.corOlhos = cor;
    }
 
 
    /**
     * Busca o atributo Ano de Nascimento.
     */
    private void lerAnoNascimento(){
 
       String ano;
 
       int pInicio = dados.indexOf("birth_year:");
       int pFinal = dados.indexOf(",", pInicio);
 
       ano = dados.substring(pInicio, pFinal);
       ano = ano.replaceAll("birth_year:", "").trim();
 
       this.anoNascimento = ano;
    }
 
 
    /**
     * Busca o atributo Genero.
     */ 
    private void lerGenero(){
 
       String genero;
 
       int pInicio = dados.indexOf("gender:");
       int pFinal = dados.indexOf(",", pInicio);
 
       genero = dados.substring(pInicio, pFinal);
       genero = genero.replaceAll("gender:", "").trim();
 
       this.genero = genero;
    }
 
 
    /**
     * Busca o atributo Homeworld.
     */
    private void lerHomeworld(){
 
       String homeworld;
 
       int pInicio = dados.indexOf("homeworld:");
       int pFinal = dados.indexOf(",", pInicio);
 
       homeworld = dados.substring(pInicio, pFinal);
       homeworld = homeworld.replaceAll("homeworld:", "").trim();
 
       this.homeworld = homeworld;
    }
 
 }
 