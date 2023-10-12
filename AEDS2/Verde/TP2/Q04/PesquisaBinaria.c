#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX 250

// Struct Jogador
typedef struct {
    int id;
    char nome[MAX];
    int altura;
    int peso;
    char universidade[MAX];
    int anoNascimento;
    char cidadeNascimento[MAX];
    char estadoNascimento[MAX];

}Jogador;

/* 
    Função para ler jogadores de um arquivo CSV e preencher uma lista de jogadores.
    O arquivo deve ter um cabeçalho e os campos separados por vírgulas.
*/
void ler(const char *nomeArquivo, Jogador** pJogadores, int* pNumJogadores) {
    int tamanhoAtual = 10;
    *pJogadores = (Jogador*) malloc(tamanhoAtual * sizeof(Jogador));
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL) {
        perror("Erro ao abrir o arquivo");
        return;
    }

    char linha[1000];
    int cabecalho = 1;

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        if (cabecalho) {
            cabecalho = 0;
            continue;
        }

        int inicioCampo = 0;
        int contador = 0;
        int k = 0;
        char* parts[8];
        

        for(int i = 0; i < strlen(linha); i++) {
            if(linha[i] == ',') {
                if(i == inicioCampo) {
                    parts[contador] = "nao informado";
                }
                else {
                    char aux[i - inicioCampo];
                    for(int j = inicioCampo; j < i; j++) {
                        aux[k] = linha[j];
                        k++;
                    }
                    aux[k] = '\0';
                    parts[contador] = strdup(aux);

                }
                k = 0;
                contador++;
                inicioCampo = i + 1;
                
            }
        }
        
        // Tratar o que sobra após a última vírgula como um campo separado
        if (inicioCampo < strlen(linha) - 1) {
            char aux[1000];
            int k = 0;
            for (int j = inicioCampo; j < strlen(linha); j++) {
                aux[k] = linha[j];
                k++;
            }
            aux[k] = '\0';
            if (k > 0 && aux[k - 1] == '\n') {
                aux[k - 1] = '\0';
            }
            parts[contador] = strdup(aux);
            contador++;
        }
        else {
            parts[contador] = "nao informado";
            contador++;
        }
        
        Jogador jogador;
        jogador.id = atoi(parts[0]);
        strcpy(jogador.nome, parts[1]);
        jogador.altura = atoi(parts[2]);
        jogador.peso = atoi(parts[3]);
        strcpy(jogador.universidade, parts[4]);
        jogador.anoNascimento = atoi(parts[5]);
        strcpy(jogador.cidadeNascimento, parts[6]);
        strcpy(jogador.estadoNascimento, parts[7]);

        if (*pNumJogadores == tamanhoAtual) {
        tamanhoAtual *= 2;
        *pJogadores = (Jogador*) realloc(*pJogadores, tamanhoAtual * sizeof(Jogador));
        }
        (*pJogadores)[*pNumJogadores] = jogador;
        (*pNumJogadores)++;

        

        
    }


    fclose(arquivo);
    
    
}

// Função para inserir um objeto Jogador em um vetor Jogador*.
void inserirVetor(Jogador jogador, Jogador** jogadores, int *contadorVetor) {
    if(*jogadores == NULL) {
        *jogadores = (Jogador*) malloc(sizeof(Jogador));
    }
    else {
        *jogadores = (Jogador*) realloc(*jogadores, (*contadorVetor + 1) * sizeof(Jogador));
    }

    (*jogadores)[*contadorVetor] = jogador;
    (*contadorVetor)++;
}

// Função para ordenar um vetor de jogadores.
void ordenarVetor(Jogador* jogadores, int numJogadores) {
    int n = numJogadores - 1;
    for(int i = n; i > 0; i--) {
        for(int j = 0; j < i; j++) {
            char* nome1 = jogadores[j].nome;
            char* nome2 = jogadores[j+1].nome;
            int comparacaoNomes = strcmp(nome1, nome2);
            if(comparacaoNomes > 0) {
                // Swap
                Jogador aux = jogadores[j];
                jogadores[j] = jogadores[j+1];
                jogadores[j+1] = aux;
            }
        }
    }
}

// Função recursiva para realizar a pesquisa binária no vetor de jogadores.
bool pesquisaBinaria(char nome[], int esq, int dir, Jogador* jogadores, int* comparacoes) {
    (*comparacoes)++;
    if(esq <= dir) {
        int meio = (esq + dir)/2;
        Jogador jogador = jogadores[meio];
        int comparacoesNome = strcmp(nome, jogador.nome);
        if(comparacoesNome == 0) {
            return true;
        }
        else if(comparacoesNome > 0) {
            return pesquisaBinaria(nome, meio + 1, dir, jogadores, comparacoes);
        }
        else {
            return pesquisaBinaria(nome, esq, meio - 1, jogadores, comparacoes);
        }
    }
    return false;
}

// Função para imprimir as informações de um jogador no formato especificado.
void imprimir(Jogador* jogadores, int i) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogadores[i].id, jogadores[i].nome, 
    jogadores[i].altura, jogadores[i].peso, jogadores[i].anoNascimento, jogadores[i].universidade, jogadores[i].cidadeNascimento, jogadores[i].estadoNascimento);
}

// Main
int main() {
    Jogador* jogadores = NULL;
    Jogador* vetorJogadores = NULL;
    char* entrada1 = (char*)malloc(250 * sizeof(char));
    char* entrada2 = (char*)malloc(250*sizeof(char));
    int contadorVetor = 0;
    int numJogadores = 0;
    int comparacoes = 0;
    clock_t startTime, endTime;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while(strcmp(entrada1, "FIM") != 0) {
        scanf(" %[^\n]", entrada1);
        if(strcmp(entrada1, "FIM") != 0) {
            int num = atoi(entrada1);
            Jogador jogador = jogadores[num];
            inserirVetor(jogador, &vetorJogadores, &contadorVetor);
        }
    }
    ordenarVetor(vetorJogadores, contadorVetor);

    startTime = clock();
    while(strcmp(entrada2, "FIM") != 0) {
        scanf(" %[^\n]", entrada2);
        if(strcmp(entrada2, "FIM") != 0) {
            bool jogadorEncontrado = pesquisaBinaria(entrada2, 0, contadorVetor - 1, vetorJogadores, &comparacoes);
            if(jogadorEncontrado) {
                printf("SIM\n");
            }
            else {
                printf("NAO\n");
            }
        }
    }

    endTime = clock();

    double totalTime = (double)(endTime - startTime);

    FILE *logFile = fopen("800854_binaria.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "800854\t%lf\t%d", totalTime, comparacoes);
        fclose(logFile);
    }

    free(entrada1);
    free(entrada2);
    free(jogadores);
    free(vetorJogadores);
    return 0;
}

