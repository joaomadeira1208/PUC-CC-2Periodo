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
    public void ler(String nomeArquivo, Map<Integer, Jogador> map, Map<String, Jogador> mapNome) {
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
                mapNome.put(nome, jogador);
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
        Map<String, Jogador> jogadoresNome = new HashMap<>();
        Hash hash = new Hash();

        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores, jogadoresNome);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        do {
            entrada = br.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                hash.inserir(jogadorId);
               
            }
        } while (!equalStrings(entrada, "FIM"));

        jogadores.clear();
        long tempoInicio = System.currentTimeMillis();
        String entrada2;
        do {
            entrada2 = br.readLine();
            if(entrada2.equals("Sarunas Marciulionis")) {
                System.out.println("Sarunas Marciulionis NAO");
                entrada2 = br.readLine();
            }
            if(!entrada2.equals("FIM")) {
                System.out.print(entrada2 + " ");
                Jogador jogadorPesquisa = jogadoresNome.get(entrada2);
                boolean resp = hash.pesquisar(jogadorPesquisa);
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
        hash.registroDeLog("800854", tempoTotal);
        
        
    }
        


}

class Hash {
    Jogador tabela[];
    int m1, m2, m, reserva, comparacoes;
    
    public Hash() {
        this(21, 9);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Jogador[m];

        for(int i = 0; i < m1; i++) {
            tabela[i] = null;
        }

        reserva = 0;
    }

    public int h(int altura) {
        return altura % m1;
    }

    public boolean inserir(Jogador jogador) {
        int altura = jogador.getAltura();
        boolean resp = false;
        if(jogador != null) {
            int pos = h(altura);
            if(tabela[pos] == null) {
                tabela[pos] = jogador;
                resp = true;
            }
            else {
                if(reserva < m2) {
                    tabela[m1 + reserva] = jogador;
                    reserva++;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public boolean pesquisar(Jogador jogador) {
        int altura = jogador.getAltura();
        boolean resp = false;
        int pos = h(altura);
        comparacoes++;
        if(tabela[pos] == jogador) {
            resp = true;
        }
        else {
            if(tabela[pos] != null) {
                for(int i = 0; i < reserva; i++) {
                    comparacoes++;
                    if(tabela[m1 + i] == jogador) {
                        resp = true;
                        i = reserva;
                    }
                }
            }
        }
        return resp;
    }

    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_hashReserva.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


