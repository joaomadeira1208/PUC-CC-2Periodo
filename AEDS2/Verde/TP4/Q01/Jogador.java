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
    Jogador jogador;

    public No(Jogador jogador) {
        this(jogador, null, null);
    }

    public No(Jogador jogador, No esq, No dir) {
        this.jogador = jogador;
        this.esq = esq;
        this.dir = dir;
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

    public void inserir(Jogador jogador) throws Exception{
        raiz = inserir(jogador, raiz);
    }

    public No inserir(Jogador jogador, No i) throws Exception{
        if(i == null) {
            i = new No(jogador);
        }
        else if(jogador.getNome().compareTo(i.jogador.getNome()) < 0) {
            i.esq = inserir(jogador, i.esq);
        }
        else if(jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
            i.dir = inserir(jogador, i.dir);
        }
        else {
            throw new Exception("Erro");
        }
        return i;
    }

    public boolean pesquisar(String chave) {
        if(raiz != null) {
            System.out.print("raiz ");
        }
        return pesquisar(chave, raiz);
    }

    public boolean pesquisar(String chave, No i) {
        boolean resp;
        if(i == null) {
            resp = false;
        }
        else {
            comparacoes++;
            if(chave.equals(i.jogador.getNome())) {
                resp = true;
            }
        
            else  {
                comparacoes++;
                if(chave.compareTo(i.jogador.getNome()) < 0) {
                    System.out.print("esq ");
                    resp = pesquisar(chave, i.esq);
                }
                else {
                    System.out.print("dir ");
                    resp = pesquisar(chave, i.dir);
                }
            }
        }
        return resp;
    }


    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_arvoreBinaria.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


