import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class PesquisaBinaria {
    private ArrayList<Jogador> vetorJogadores = new ArrayList<>();
    private int comparacoes = 0;

    // Método para inserir um objeto Jogador no vetor.
    public void inserirVetor(Jogador jogador) {
        vetorJogadores.add(jogador);
    }

    // Método para ordenar o vetor de acordo com o nome dos Jogadores.
    public void ordernarVetor() {
        int n = vetorJogadores.size() - 1;
        for(int i = n; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                String nome1 = vetorJogadores.get(j).getNome();
                String nome2 = vetorJogadores.get(j+1).getNome();
                int comparacaoNomes = nome1.compareTo(nome2);
                if(comparacaoNomes > 0) {
                    // Swap
                    Jogador aux = vetorJogadores.get(j);
                    vetorJogadores.set(j, vetorJogadores.get(j+1));
                    vetorJogadores.set(j+1, aux);
                }
            }
        }
    }


    // Métodos Recursivos para realizar a Pesquisa Binaria no vetor.
    public boolean pesquisaBin(String nome) {
        return pesquisaBin(nome, 0, vetorJogadores.size() - 1);
    }

    public boolean pesquisaBin(String nome, int esq, int dir) {
        int meio = (esq + dir)/2;
        Jogador jogador = vetorJogadores.get(meio);
        comparacoes++;
        int comparacaoNomes = nome.compareTo(jogador.getNome());
        if(esq > dir) {
            return false;
        }
        else if(comparacaoNomes == 0) {
            return true;
        }
        else if(comparacaoNomes > 0) {
            return pesquisaBin(nome, meio + 1, dir);
        }
        else {
            return pesquisaBin(nome, esq, meio - 1);
        }
        
        
    }

    // Método para criar o arquivo de registro de log.
    public void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_binaria.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + tempoExecucao + "\t" + comparacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para comparar duas strings e verificar se são iguais.
    public static boolean equalStrings(String str_1, String str_2) {
        if(str_1.length() != str_2.length()) {
            return false;
        }

        for(int i = 0; i < str_1.length(); i++) {
            if(str_1.charAt(i) != str_2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    
    static class Jogador {
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

            
        
    
    }
    // Main
    public static void main(String[] args) {
        long tempoInicio = System.currentTimeMillis();
        PesquisaBinaria pesquisaBinaria = new PesquisaBinaria();
        Map<Integer, Jogador> jogadores = new HashMap<>();
        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);
        String entrada1, entrada2;
        do {
            entrada1 = MyIO.readLine();
            if(!equalStrings(entrada1, "FIM")) {
                int idInsercao = Integer.parseInt(entrada1);
                Jogador jogadorInsercao = jogadores.get(idInsercao);
                pesquisaBinaria.inserirVetor(jogadorInsercao);
            }
        }while(!equalStrings(entrada1, "FIM"));

        pesquisaBinaria.ordernarVetor();

         do {
            entrada2 = MyIO.readLine();
            if(!equalStrings(entrada2, "FIM")) {
                String nome = entrada2;
                boolean jogadorEncontrado = pesquisaBinaria.pesquisaBin(nome);
                if(jogadorEncontrado) {
                    MyIO.println("SIM");
                }
                else {
                    MyIO.println("NAO");
                }
                
            }
        }while(!equalStrings(entrada2, "FIM"));
        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicio;
        pesquisaBinaria.registroDeLog("800854", tempoExecucao);
        

    }
}