import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import mypackage.MyIO;

public class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        this.id = 0;
        this.nome = "";
        this.altura = 0;
        this.peso = 0;
        this.universidade = "";
        this.anoNascimento = 0;
        this.cidadeNascimento = "";
        this.estadoNascimento = "";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNasc() {
        return anoNascimento;
    }

    public void setAnoNasc(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNasc() {
        return cidadeNascimento;
    }

    public void setCidadeNasc(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNasc() {
        return estadoNascimento;
    }

    public void setEstadoNasc(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

    public void imprimir() {
        MyIO.print("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + universidade + " ## "
                + anoNascimento + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
    }

    public void ler(String nomeArquivo, Map<Integer, Jogador> map) {
        try(FileReader fileReader = new FileReader(nomeArquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String linha = bufferedReader.readLine();
                boolean cabecalho = true;

                while(linha != null) {
                    if(cabecalho) {
                        cabecalho = false;
                        continue;
                    }

                    String[] parts = linha.split(",");

                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].isEmpty() && i < 7) {
                            parts[i] = "MISSING"; // Or set it to some default value
                        }
                    }
                    int id = Integer.parseInt(parts[0].trim());
                    String nome = parts[1].trim();
                    int altura = Integer.parseInt(parts[2].trim());
                    int peso = Integer.parseInt(parts[3].trim());
                    String universidade = parts[4].trim();
                    int anoNascimento = Integer.parseInt(parts[5].trim());
                    String cidadeNascimento = parts[6].trim();
                    String estadoNascimento = parts[7].trim();

                    Jogador jogador = new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
                    map.put(id, jogador);
                    

                }
            }catch(IOException e) {
                e.printStackTrace();
            }
    }
    public static void main(String[] args) {
        Map<Integer, Jogador> jogadores = new HashMap<>();
        Jogador jogador = new Jogador();
        jogador.ler("players.csv", jogadores);
        Jogador foda = jogadores.get(2);
        foda.imprimir();

    }

    
    }

   


