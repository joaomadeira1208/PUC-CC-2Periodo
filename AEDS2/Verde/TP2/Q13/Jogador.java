import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    static int comparacoes = 0;
    static int movimentacoes = 0;

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

    // Método para imprimir os dados do jogador
    public static void imprimir(ArrayList<Jogador> listaJogadores) {
        for(int i = 0; i < listaJogadores.size(); i++) {
            MyIO.println("[" + listaJogadores.get(i).getId() + " ## " + listaJogadores.get(i).getNome() + " ## " + listaJogadores.get(i).getAltura() 
            + " ## " + listaJogadores.get(i).getPeso() + " ## " + listaJogadores.get(i).getAnoNasc() + " ## " + listaJogadores.get(i).getUniversidade() 
            + " ## " + listaJogadores.get(i).getCidadeNasc() + " ## " + listaJogadores.get(i).getEstadoNasc() + "]");
        }
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
    // Método para ordenar os Jogadores de uma lista, a partir de seus nomes.
    public static void ordenacaoNome(ArrayList<Jogador> listaJogadores) {
        quicksortNomes(listaJogadores, 0, listaJogadores.size() - 1);
    }
    // Método recursivo de quicksort para ordenar os jogadores a partir dos nomes.
    public static void quicksortNomes(ArrayList<Jogador> listaJogadores, int esq, int dir) {
        int i = esq, j = dir;
        Jogador pivo = listaJogadores.get((dir+esq)/2);
        while(i <= j) {
            while(listaJogadores.get(i).getNome().compareTo(pivo.getNome()) < 0) i++;
            while(listaJogadores.get(j).getNome().compareTo(pivo.getNome()) > 0) j--;
            if(i <= j) {
                swap(listaJogadores, i, j);
                i++;
                j--;
            }
        }
        if(esq < j) quicksortNomes(listaJogadores, esq, j);
        if(i < dir) quicksortNomes(listaJogadores, i, dir);
    }

    // Método que ordena a lista de jogadores por universidade usando o algoritmo Merge Sort.
    public static void mergeSort(ArrayList <Jogador> listaJogadores, int esq, int dir) {
        if(esq < dir) {
            int meio = (esq + dir)/2;
            mergeSort(listaJogadores, esq, meio);
            mergeSort(listaJogadores, meio + 1, dir);
            intercalar(listaJogadores, esq, meio, dir);
        }
    }

    // Método combina duas sublistas adjacentes de listaJogadores que foram previamente ordenadas.
    public static void intercalar(ArrayList<Jogador> listaJogadores, int esq, int meio, int dir) {
        int n1, n2, i, j, k;
        Jogador sentinela = new Jogador();
        sentinela.setUniversidade("~");

        n1 = meio - esq + 1;
        n2 = dir - meio;
        Jogador[] a1 = new Jogador[n1 +1];
        Jogador[] a2 = new Jogador[n2 + 1];

        for(i = 0; i < n1; i++) {
            movimentacoes++;
            a1[i] = listaJogadores.get(esq + i);
        }

        for(j = 0; j < n2; j++) {
            movimentacoes++;
            a2[j] = listaJogadores.get(meio + j + 1);
        }

        a1[i] = a2[j] = sentinela;
        movimentacoes += 2;

        for(i = j = 0, k = esq; k <= dir; k++) {
            listaJogadores.set(k, ((a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) <= 0) ? a1[i++] : a2[j++]));
            comparacoes++;
            movimentacoes++;    
        }
    }
    
    // Método Swap que troca a posição de dois jogadores na lista.
    public static void swap(ArrayList<Jogador> listaJogadores, int index1, int index2) {
        Jogador temp = listaJogadores.get(index1);
        listaJogadores.set(index1, listaJogadores.get(index2));
        listaJogadores.set(index2, temp);
    }

    // Método para criar o arquivo de registro de log.
    public static void registroDeLog(String matricula, long tempoExecucao) {
        try (FileWriter fw = new FileWriter(matricula + "_mergesort.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
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
    
    // Main
    public static void main(String[] args) {
        Map<Integer, Jogador> jogadores = new HashMap<>();
        ArrayList<Jogador> listaJogadores = new ArrayList<>();
        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);
        
        String entrada;
        do{
            entrada = MyIO.readLine();
            if(!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                listaJogadores.add(jogadorId);
            }
        }while(!equalStrings(entrada, "FIM"));

        ordenacaoNome(listaJogadores);
        long tempoInicio = System.currentTimeMillis();
        mergeSort(listaJogadores, 0, listaJogadores.size() - 1);
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicio;
        registroDeLog("800854", tempoTotal);
        imprimir(listaJogadores);

    }

}
