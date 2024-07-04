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
        Alvinegra arvoreAlvinegra = new Alvinegra();

        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                arvoreAlvinegra.inserir(jogadorId);
            }
        } while (!equalStrings(entrada, "FIM"));

        String entrada2;

        long tempoInicio = System.currentTimeMillis();
        do {
            entrada2 = br.readLine();
            if(!entrada2.equals("FIM")) {
                System.out.print(entrada2 + " ");
                boolean resp = arvoreAlvinegra.pesquisar(entrada2);
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
        arvoreAlvinegra.registroDeLog("800854", tempoTotal);
    }

}

class NoAN {
    boolean cor;
    NoAN esq, dir;
    Jogador jogador;

    public NoAN() {
        this(null);
    }

    public NoAN(Jogador jogador) {
        this(jogador, false, null, null);
    }

    public NoAN(Jogador jogador, boolean cor) {
        this(jogador, cor, null, null);
    }

    public NoAN(Jogador jogador, boolean cor, NoAN esq, NoAN dir) {
        this.jogador = jogador;
        this.cor = cor;
        this.esq = esq;
        this.dir = dir;
    }
}

class Alvinegra {
    NoAN raiz;
    int comparacoes = 0;

    public Alvinegra() {
        raiz = null;
    }

    public void inserir(Jogador jogador) throws Exception {
        if(raiz == null) {
            raiz = new NoAN(jogador);
        }
        else if(raiz.esq == null && raiz.dir == null) {
            if(jogador.getNome().compareTo(raiz.jogador.getNome()) < 0) {
                raiz.esq = new NoAN(jogador);
            }
            else {
                raiz.dir = new NoAN(jogador);
            }
        }
        else if(raiz.esq == null) {
            if(jogador.getNome().compareTo(raiz.jogador.getNome()) < 0) {
                raiz.esq = new NoAN(jogador);
            }
            else if(jogador.getNome().compareTo(raiz.dir.jogador.getNome()) < 0) {
                raiz.esq = new NoAN(raiz.jogador);
                raiz.jogador = jogador;
            }
            else {
                raiz.esq = new NoAN(raiz.jogador);
                raiz.jogador = raiz.dir.jogador;
                raiz.dir.jogador = jogador;
            }

            raiz.esq.cor = raiz.dir.cor = false;
        }
        else if(raiz.dir == null) {
            if(jogador.getNome().compareTo(raiz.jogador.getNome()) > 0) {
                raiz.dir = new NoAN(jogador);
            }
            else if(jogador.getNome().compareTo(raiz.esq.jogador.getNome()) > 0) {
                raiz.dir = new NoAN(raiz.jogador);
                raiz.jogador = jogador;
            }
            else {
                raiz.dir = new NoAN(raiz.jogador);
                raiz.jogador = raiz.esq.jogador;
                raiz.esq.jogador = jogador;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        }
        else {
            inserir(jogador, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    public void inserir(Jogador jogador, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception{
        if(i == null) {
            if(jogador.getNome().compareTo(pai.jogador.getNome()) < 0) {
                i = pai.esq = new NoAN(jogador, true);
            }
            else {
                i = pai.dir = new NoAN(jogador, true);
            }

            if(pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        }
        else {
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz) {
                    i.cor = false;
                }
                else if(pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }

            if(jogador.getNome().compareTo(i.jogador.getNome()) < 0) {
                inserir(jogador, avo, pai, i, i.esq);
            }
            else if(jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
                inserir(jogador, avo, pai, i, i.dir);
            }
            else {
                throw new Exception("Erro");
            }
        }
    }
    

    public void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if(pai.cor == true) {
            if(pai.jogador.getNome().compareTo(avo.jogador.getNome()) > 0) {
                if(i.jogador.getNome().compareTo(pai.jogador.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                }
                else {
                    avo = rotacaoDirEsq(avo);
                }
            }
            else {
                if(i.jogador.getNome().compareTo(pai.jogador.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                }
                else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if(bisavo == null) {
                raiz = avo;
            }
            else if(avo.jogador.getNome().compareTo(bisavo.jogador.getNome()) < 0) {
                bisavo.esq = avo;
            }
            else {
                bisavo.dir = avo;
            }

            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    public NoAN rotacaoDir(NoAN i) {
        NoAN noEsq = i.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = i;
        i.esq = noEsqDir;

        return noEsq;
    }

    public NoAN rotacaoEsq(NoAN i) {
        NoAN noDir = i.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = i;
        i.dir = noDirEsq;

        return noDir;
    }

    public NoAN rotacaoDirEsq(NoAN i) {
        i.dir = rotacaoDir(i.dir);
        return rotacaoEsq(i);
    }

    public NoAN rotacaoEsqDir(NoAN i) {
        i.esq = rotacaoEsq(i.esq);
        return rotacaoDir(i);
    }

    public boolean pesquisar(String nome) {
        if(raiz != null) {
            System.out.print("raiz ");
        }
        return pesquisar(nome, raiz);
    }

    public boolean pesquisar(String nome, NoAN i) {
        boolean resp;
        if(i == null) {
            resp = false;
        }
        else {
            comparacoes++;
            if(nome.equals(i.jogador.getNome())) {
                resp = true;
            }
            else {
                comparacoes++;
                if(nome.compareTo(i.jogador.getNome()) < 0) {
                    System.out.print("esq ");
                    resp = pesquisar(nome, i.esq);
                }
                else {
                    System.out.print("dir ");
                    resp = pesquisar(nome, i.dir);
                }
            }
        }
        return resp;
    }


    // Método para criar o arquivo de registro de log.
    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_alvinegra.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




