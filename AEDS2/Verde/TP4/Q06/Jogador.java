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
        ArvoreTrie arvore1 = new ArvoreTrie();
        ArvoreTrie arvore2 = new ArvoreTrie();
        ArvoreTrie arvoreResp = new ArvoreTrie();

        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                arvore1.inserir(jogadorId.getNome());
            }
        } while (!equalStrings(entrada, "FIM"));

        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                arvore2.inserir(jogadorId.getNome());
            }
        } while (!equalStrings(entrada, "FIM"));

        arvore1.merge(arvoreResp);
        arvore2.merge(arvoreResp);

        long tempoInicio = System.currentTimeMillis();
         
        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                System.out.print(entrada + " ");
                boolean resp = arvoreResp.pesquisar(entrada);
                if(resp == true) {
                    System.out.println("SIM");
                }
                else {
                    System.out.println("NAO");
                }
            }
        } while (!equalStrings(entrada, "FIM"));
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicio;
        arvoreResp.registroDeLog("800854", tempoTotal);
    }
        


}

class No {
    final int tamanho = 255;
    char elemento;
    No[] prox;
    boolean folha;


    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }
        folha = false;
    }

    
}

class ArvoreTrie {
    No raiz;
    int comparacoes = 0;

    public ArvoreTrie() {
        raiz = new No();
    }

    public void inserir(String s) throws Exception{
        inserir(s, raiz, 0);
    }

    public void inserir(String s, No no, int i) throws Exception{
        if(no.prox[s.charAt(i)] == null) {
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1) {
                no.prox[s.charAt(i)].folha = true;
            }
            else {
                inserir(s, no.prox[s.charAt(i)], i+1);
            } 
        }
        else if(no.prox[s.charAt(i)].folha == false && i < s.length() -1) {
            inserir(s, no.prox[s.charAt(i)], i+1);
        }
        else {
            throw new Exception("Erro");
        }
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
    boolean resp;
    comparacoes++; 
    if(no.prox[s.charAt(i)] == null) {
        resp = false;
    }
    else {
        comparacoes++; 
        if(i == s.length() -1) {
            resp = (no.prox[s.charAt(i)].folha == true);
        }
        else {
            resp = pesquisar(s, no.prox[s.charAt(i)], i+1);
        }
    }
    return resp;
}


    public void merge(ArvoreTrie C) throws Exception{
        merge(C, raiz, "");
    }

   

    private void merge(ArvoreTrie C, No no, String s) throws Exception {
        if(no.folha) {
            C.inserir(s);
        }
        
        for(int i = 0; i < no.prox.length; i++) {
            if(no.prox[i] != null) {
                char caractere = (char) i;
                merge(C, no.prox[i], s + caractere);
            }
        }
    }

    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_arvoreTrie.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


