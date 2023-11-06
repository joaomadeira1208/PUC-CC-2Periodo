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
        Lista lista = new Lista();

        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                lista.inserirFinal(jogadorId);
            }
        } while (!equalStrings(entrada, "FIM"));

        long tempoInicio = System.currentTimeMillis();
        lista.quickSort(lista.primeiro.prox, lista.ultimo);
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicio;
        lista.mostrar();
        lista.registroDeLog("800854", tempoTotal);
    }

}

class Celula {
    Jogador jogador;
    Celula prox;
    Celula ant;

    public Celula() {
        this(null);
    }

    public Celula(Jogador jogador) {
        this.jogador = jogador;
        this.prox = this.ant = null;
    }

}

class Lista {
    Celula primeiro, ultimo;
    static int comparacoes = 0;
    static int movimentacoes = 0;
    public Lista() {
        this.primeiro = new Celula();
        this.ultimo = this.primeiro;
    }

    public void inserirInicio(Jogador jogador) {
        Celula tmp = new Celula(jogador);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo) {
            ultimo = tmp;
        }
        else {
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    public void inserirFinal(Jogador jogador) {
        ultimo.prox = new Celula(jogador);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public Jogador removerInicio() throws Exception{
        if(primeiro == ultimo) {
            throw new Exception("Erro: Lista Vazia");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador resp = primeiro.jogador;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        return resp;
    }

    public Jogador removerFinal() throws Exception {
        if(primeiro == ultimo) {
            throw new Exception("Erro: lista vazia");
        }

        Jogador resp = ultimo.jogador;
        ultimo = ultimo.ant;
        ultimo.prox.ant = ultimo.prox = null;
        return resp;
    }

    public void inserir(Jogador jogador, int pos) throws Exception{
        int n = tamanho();
        if(pos < 0 || pos > n) {
            throw new Exception("Erro: Posição invalida");
        }
        else if(pos == 0) {
            inserirInicio(jogador);
        }
        else if(pos == n) {
            inserirFinal(jogador);
        }
        else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(jogador);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    public Jogador remover(int pos) throws Exception{
        int n = tamanho();
        Jogador resp;
        if(ultimo == primeiro) {
            throw new Exception("Erro: Lista vazia");
        }
        else if(pos < 0 || pos > n - 1) {
            throw new Exception("Erro: posicao invalida");
        }
        else if(pos == 0) {
            resp = removerInicio();
        }
        else if(pos == n - 1) {
            resp = removerFinal();
        }
        else {
            Celula i = primeiro.prox;
            for(int j = 0; j < pos; j++, i = i.prox);
            resp = i.jogador;
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            i.prox = i.ant = null;
            i = null;
        }
        return resp;
    }

    public Jogador encontrarPivo(Celula esq, Celula dir) {
        Celula i, j;
        for(i = esq, j = dir; i != j && i.prox != j; i = i.prox, j = j.ant);
        Jogador resp = i.jogador;
        return resp;
    }

    public void quickSort(Celula esq, Celula dir) {
        Celula i = esq, j = dir;
        Jogador pivo = encontrarPivo(esq, dir);

        while(i != j.prox && j.prox != i.ant ) {
            while(true) {
                comparacoes += 2;
                int compEstado = i.jogador.getEstadoNasc().compareTo(pivo.getEstadoNasc());
                int compNome = i.jogador.getNome().compareTo(pivo.getNome());
                if(compEstado < 0) {
                    i = i.prox;
                    comparacoes--;
                }
                else if(compEstado == 0 && compNome < 0) {
                    i = i.prox;
                } 
                else {
                    break;
                }
            }

            while(true) {
                comparacoes += 2;
                int compEstado = j.jogador.getEstadoNasc().compareTo(pivo.getEstadoNasc());
                int compNome = j.jogador.getNome().compareTo(pivo.getNome());
                if(compEstado > 0) {
                    comparacoes--;
                    j = j.ant;
                }
                else if(compEstado == 0 && compNome > 0) {
                    j = j.ant;
                } 
                else {
                    break;
                }
            }

            if(i != j.prox) {
                movimentacoes += 2;
                Jogador tmp = i.jogador;
                i.jogador = j.jogador;
                j.jogador = tmp;
                i = i.prox;
                j = j.ant;
            }
        }

        if(esq != j.prox && esq != j) quickSort(esq, j);
        if(i != dir.prox && i != dir) quickSort(i, dir);
    }

    public void mostrar() {
        Celula i;
        for(i = primeiro.prox; i != null; i = i.prox) {
            MyIO.println("[" + i.jogador.getId() + " ## " + i.jogador.getNome() + " ## " + i.jogador.getAltura() 
            + " ## " + i.jogador.getPeso() + " ## " + i.jogador.getAnoNasc() + " ## " + i.jogador.getUniversidade() 
            + " ## " + i.jogador.getCidadeNasc() + " ## " + i.jogador.getEstadoNasc() + "]");
        }
    }

    // Método para criar o arquivo de registro de log.
    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_quicksort2_java.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int tamanho() {
        int tamanho = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox, tamanho++);
        return tamanho;
    }
}
