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

    public static void main(String[] args) {
        Map<Integer, Jogador> jogadores = new HashMap<>();

        // Lê o arquivo CSV e cria instâncias de Jogador
        try (BufferedReader br = new BufferedReader(new FileReader("players.csv"))) {
            String linha;
            boolean primeiraLinha = true; // Flag para verificar se é a primeira linha
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false; // Ignora a primeira linha (cabeçalho)
                    continue;
                }
                String[] dados = linha.split(",");
                if (dados.length >= 8) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    int altura = Integer.parseInt(dados[2]);
                    int peso = Integer.parseInt(dados[3]);
                    String universidade = dados[4];
                    int anoNascimento = Integer.parseInt(dados[5]);
                    String cidadeNascimento = dados[6];
                    String estadoNascimento = dados[7];

                    Jogador jogador = new Jogador(id, nome, altura, peso, universidade,
                            anoNascimento, cidadeNascimento, estadoNascimento);

                    jogadores.put(id, jogador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leitura dos IDs e impressão dos jogadores
        Scanner scanner = new Scanner(System.in);
        String linha;

        while (true) {
            linha = scanner.nextLine();
            if (linha.equals("FIM")) {
                break;
            }

            int id = Integer.parseInt(linha);

            if (jogadores.containsKey(id)) {
                Jogador jogador = jogadores.get(id);
                jogador.imprimir();
            } else {
                System.out.println("Jogador com ID " + id + " não encontrado.");
            }
        }
    }

}
