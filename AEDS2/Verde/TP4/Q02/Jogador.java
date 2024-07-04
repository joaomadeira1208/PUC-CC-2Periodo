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
        arvore.registroDeLog("800854", tempoTotal);
    }

}

class No {
    No esq, dir;
    No2 outro;
    int elemento;

    public No(int x) {
        this(x, null, null, null);
    }

    public No(int x, No esq, No dir, No2 outro) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.outro = outro;
    }
}

class Arvore {
    No raiz;
    int comparacoes = 0;

    public Arvore() {
        this.raiz = null;
    }

    public Arvore(No i) {
        this.raiz = i;
    }

    public void inserir(int x) throws Exception{
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i) throws Exception{
        if(i == null) {
            i = new No(x);
        }
        else if(x < i.elemento) {
            i.esq = inserir(x, i.esq);
        }
        else if(x > i.elemento) {
            i.dir = inserir(x, i.dir);
        }
        else {
            throw new Exception("Erro");
        }
        return i;
    }

    public void inserir(Jogador jogador) throws Exception {
        inserir(jogador, raiz);
    }

    public void inserir(Jogador jogador, No i) throws Exception {
        if(i == null) {
            throw new Exception("Erro");
        }
        else if(jogador.getAltura() % 15 < i.elemento) {
            inserir(jogador, i.esq);
        }
        else if(jogador.getAltura() % 15 > i.elemento) {
            inserir(jogador, i.dir);
        }
        else {
            i.outro = inserir(jogador.getNome(), i.outro);
        }
    }

    public No2 inserir(String nome, No2 i) throws Exception {
        if(i == null) {
            i = new No2(nome);
        }
        else if(nome.compareTo(i.nome) < 0) {
            i.esq = inserir(nome, i.esq);
        }
        else if(nome.compareTo(i.nome) > 0) {
            i.dir = inserir(nome, i.dir);
        }
        else {
            throw new Exception("Erro");
        }
        return i;
    }

    public boolean pesquisar(String nome) {
        return pesquisar(raiz, nome, "raiz");
    }

    public boolean pesquisar(No i, String nome, String direcao) {
        boolean resp = false;
        System.out.print(direcao + " ");
        if(i != null) {
            resp = ((pesquisar(i.outro, nome, "exception")) || pesquisar(i.esq, nome, "esq")) || (pesquisar(i.dir, nome, "dir"));
        }
        return resp;
    }

    public boolean pesquisar(No2 i, String nome, String direcao) {
        boolean resp = false;
        if(direcao != "exception") {
            System.out.print(direcao + " ");
        }
        if(i != null) {
            comparacoes++;
            resp = ((nome.equals(i.nome)) || pesquisar(i.esq, nome, "ESQ")) || (pesquisar(i.dir, nome, "DIR"));
        }
        return resp;
    }

    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_arvoreArvore.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class No2 {
    No2 esq, dir;
    String nome;

    public No2(String nome) {
        this(nome, null, null);
    }

    public No2(String nome, No2 esq, No2 dir) {
        this.nome = nome;
        this.esq = esq;
        this.dir = dir;
    }
}


