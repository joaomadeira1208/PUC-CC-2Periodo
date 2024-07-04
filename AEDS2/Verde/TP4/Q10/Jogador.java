import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Construtor padrão da classe Jogador
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

    // Construtor com parametros para cada atributo.
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

    // Método para obter o ID do jogador
    public int getId() {
        return id;
    }

    // Método para definir o ID do jogador
    public void setId(int id) {
        this.id = id;
    }

    // Método para obter o nome do jogador
    public String getNome() {
        return nome;
    }

    // Método para definir o nome do jogador
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para obter a altura do jogador
    public int getAltura() {
        return altura;
    }

    // Método para definir a altura do jogador
    public void setAltura(int altura) {
        this.altura = altura;
    }

    // Método para obter o peso do jogador
    public int getPeso() {
        return peso;
    }

    // Método para definir o peso do jogador
    public void setPeso(int peso) {
        this.peso = peso;
    }

    // Método para obter a universidade do jogador
    public String getUniversidade() {
        return universidade;
    }

    // Método para definir a universidade do jogador
    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    // Método para obter o ano de nascimento do jogador
    public int getAnoNasc() {
        return anoNascimento;
    }

    // Método para definir o ano de nascimento do jogador
    public void setAnoNasc(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    // Método para obter a cidade de nascimento do jogador
    public String getCidadeNasc() {
        return cidadeNascimento;
    }

    // Método para definir a cidade de nascimento do jogador
    public void setCidadeNasc(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    // Método para obter o estado de nascimento do jogador
    public String getEstadoNasc() {
        return estadoNascimento;
    }

    // Método para definir o estado de nascimento do jogador
    public void setEstadoNasc(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // Método para criar uma cópia (clone) do objeto Jogador
    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

    // Método para ler dados de um arquivo CSV e preencher um mapa de jogadores
    public void ler(String nomeArquivo, Map<Integer, Jogador> map) {
        try (FileReader fileReader = new FileReader(nomeArquivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            boolean cabecalho = true;

            while ((linha = bufferedReader.readLine()) != null) {
                if (cabecalho) {
                    cabecalho = false;
                    continue;
                }

                String[] parts = new String[8];
                int contador = 0;
                int inicioCampo = 0;

                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) == ',') {
                        if (i == inicioCampo) {
                            parts[contador] = "nao informado";
                        } else {
                            parts[contador] = linha.substring(inicioCampo, i).trim();
                        }
                        contador++;
                        inicioCampo = i + 1;
                    }
                }

                if (linha.charAt(linha.length() - 1) == ',') {
                    parts[contador] = "nao informado";
                } else {
                    parts[contador] = linha.substring(inicioCampo).trim();
                }

                if (contador != 7) {
                    continue;
                }

                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                int altura = Integer.parseInt(parts[2]);
                int peso = Integer.parseInt(parts[3]);
                String universidade = parts[4];
                int anoNascimento = Integer.parseInt(parts[5]);
                String cidadeNascimento = parts[6];
                String estadoNascimento = parts[7];

                Jogador jogador = new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento,
                        estadoNascimento);
                map.put(id, jogador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para comparar duas strings e verificar se são iguais.
    public static boolean equalStrings(String str_1, String str_2) {
        if (str_1.length() != str_2.length()) {
            return false;
        }

        for (int i = 0; i < str_1.length(); i++) {
            if (str_1.charAt(i) != str_2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    

    // Main
    public static void main(String[] args) throws Exception {
        Map<Integer, Jogador> jogadores = new HashMap<>();
        Arvore arvore = new Arvore();
        int[] chaves = {7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14};
        for(int i = 0; i < chaves.length; i++) {
            arvore.inserir(chaves[i]);
        }

        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                arvore.inserir(jogadorId);
            }
        } while (!equalStrings(entrada, "FIM"));

        String entrada2;

        long tempoInicio = System.currentTimeMillis();
        do {
            entrada2 = br.readLine();
            if(!entrada2.equals("FIM")) {
                System.out.print(entrada2 + " ");
                boolean resp = arvore.pesquisar(entrada2);
                if(resp) {
                    System.out.println("SIM");
                }
                else {
                    System.out.println("NAO");
                }
            }
        }while(!entrada2.equals("FIM"));
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicio;
    }

}
class Arvore {
    No raiz;
    int contadorComparacoes = 0;

    public Arvore() {
        this.raiz = null;
    }

    public Arvore(No raizInicial) {
        this.raiz = raizInicial;
    }

    public void inserir(int x) throws Exception {
        raiz = inserirNo(x, raiz);
    }

    private No inserirNo(int x, No noAtual) throws Exception {
        if (noAtual == null) {
            noAtual = new No(x);
        } else if (x < noAtual.elemento) {
            noAtual.esquerda = inserirNo(x, noAtual.esquerda);
        } else if (x > noAtual.elemento) {
            noAtual.direita = inserirNo(x, noAtual.direita);
        } else {
            throw new Exception("Erro");
        }
        return noAtual;
    }

    public void inserir(Jogador jogador) throws Exception {
        inserirJogador(jogador, raiz);
    }

    private void inserirJogador(Jogador jogador, No no) throws Exception {
        if (no == null) {
            throw new Exception("Erro");
        } else if (jogador.getAltura() % 15 < no.elemento) {
            inserirJogador(jogador, no.esquerda);
        } else if (jogador.getAltura() % 15 > no.elemento) {
            inserirJogador(jogador, no.direita);
        } else {
            no.secundario = inserirNome(jogador.getNome(), no.secundario);
        }
    }

    private NoAux inserirNome(String nome, NoAux noAux) throws Exception {
        if (noAux == null) {
            noAux = new NoAux(nome);
        } else if (nome.compareTo(noAux.nome) < 0) {
            noAux.esquerda = inserirNome(nome, noAux.esquerda);
        } else if (nome.compareTo(noAux.nome) > 0) {
            noAux.direita = inserirNome(nome, noAux.direita);
        } else {
            throw new Exception("Erro");
        }
        return noAux;
    }

    public boolean pesquisar(String nome) {
        return pesquisarNome(raiz, nome, "raiz");
    }

    private boolean pesquisarNome(No no, String nome, String direcao) {
        boolean resultado = false;
        System.out.print(direcao + " ");
        if (no != null) {
            resultado = ((pesquisarNoAux(no.secundario, nome, "exception")) ||
                         pesquisarNome(no.esquerda, nome, "esq")) ||
                         (pesquisarNome(no.direita, nome, "dir"));
        }
        return resultado;
    }

    private boolean pesquisarNoAux(NoAux noAux, String nome, String direcao) {
        boolean resultado = false;
        if (!direcao.equals("exception")) {
            System.out.print(direcao + " ");
        }
        if (noAux != null) {
            contadorComparacoes++;
            resultado = ((nome.equals(noAux.nome)) ||
                         pesquisarNoAux(noAux.esquerda, nome, "ESQ")) ||
                         (pesquisarNoAux(noAux.direita, nome, "DIR"));
        }
        return resultado;
    }
}

class NoAux {
    NoAux esquerda, direita;
    String nome;

    public NoAux(String nome) {
        this(nome, null, null);
    }

    public NoAux(String nome, NoAux esquerda, NoAux direita) {
        this.nome = nome;
        this.esquerda = esquerda;
        this.direita = direita;
    }
}

class No {
    No esquerda, direita;
    NoAux secundario;
    int elemento;

    public No(int elemento) {
        this(elemento, null, null, null);
    }

    public No(int elemento, No esquerda, No direita, NoAux secundario) {
        this.elemento = elemento;
        this.esquerda = esquerda;
        this.direita = direita;
        this.secundario = secundario;
    }
}



