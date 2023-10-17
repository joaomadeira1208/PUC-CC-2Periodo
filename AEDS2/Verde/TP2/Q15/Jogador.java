import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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

    // Método para imprimir os dados do jogador
    public static void imprimir(ArrayList<Jogador> listaJogadores) {
        for (int i = 0; i < listaJogadores.size(); i++) {
            MyIO.println("[" + listaJogadores.get(i).getId() + " ## " + listaJogadores.get(i).getNome() + " ## "
                    + listaJogadores.get(i).getAltura()
                    + " ## " + listaJogadores.get(i).getPeso() + " ## " + listaJogadores.get(i).getAnoNasc() + " ## "
                    + listaJogadores.get(i).getUniversidade()
                    + " ## " + listaJogadores.get(i).getCidadeNasc() + " ## " + listaJogadores.get(i).getEstadoNasc()
                    + "]");
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

    // Método de ordenação por seleção que retorna os 'k' menores jogadores com base
    // no nome.
    public static ArrayList<Jogador> ordernacaoSelecao(ArrayList<Jogador> listaJogadores, int k) {
        int n = listaJogadores.size();
        ArrayList<Jogador> listaParcial = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            listaParcial.add(i, listaJogadores.get(i));
        }

        for (int i = k; i < n; i++) {
            Jogador maior = getMaior(listaParcial);
            String nome1 = maior.getNome();
            String nome2 = listaJogadores.get(i).getNome();
            if (nome1.compareTo(nome2) > 0) {
                listaParcial.remove(maior);
                listaParcial.add(listaJogadores.get(i));
            }
        }

        for (int i = 0; i < k - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < k; j++) {
                String nome1 = listaParcial.get(menor).nome;
                String nome2 = listaParcial.get(j).nome;
                if (nome1.compareTo(nome2) > 0) {
                    menor = j;
                }
            }
            swap(listaParcial, menor, i);
        }

        return listaParcial;
    }

    // Método para encontrar e retornar o maior Jogador de acordo com o nome.
    public static Jogador getMaior(ArrayList<Jogador> listaJogadores) {
        Jogador maior = listaJogadores.get(0);
        for (int i = 0; i < listaJogadores.size(); i++) {
            if (maior.getNome().compareTo(listaJogadores.get(i).getNome()) < 0) {
                maior = listaJogadores.get(i);
            }
        }
        return maior;
    }

    // Método Swap que troca a posição de dois jogadores na lista.
    public static void swap(ArrayList<Jogador> listaJogadores, int index1, int index2) {
        Jogador temp = listaJogadores.get(index1);
        listaJogadores.set(index1, listaJogadores.get(index2));
        listaJogadores.set(index2, temp);
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
    public static void main(String[] args) {
        Map<Integer, Jogador> jogadores = new HashMap<>();
        ArrayList<Jogador> listaJogadores = new ArrayList<>();
        Jogador jogador = new Jogador();
        jogador.ler("/tmp/players.csv", jogadores);

        String entrada;
        do {
            entrada = MyIO.readLine();
            if (!equalStrings(entrada, "FIM")) {
                int idBusca = Integer.parseInt(entrada);
                Jogador jogadorId = jogadores.get(idBusca);
                listaJogadores.add(jogadorId);
            }
        } while (!equalStrings(entrada, "FIM"));

        ArrayList<Jogador> listaParcial = ordernacaoSelecao(listaJogadores, 10);
        imprimir(listaParcial);

    }

}
