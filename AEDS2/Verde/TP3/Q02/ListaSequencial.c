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

// Função para imprimir as informações de um jogador no formato especificado.
void imprimir(Jogador* jogadores, int i) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogadores[i].id, jogadores[i].nome, 
    jogadores[i].altura, jogadores[i].peso, jogadores[i].anoNascimento, jogadores[i].universidade, jogadores[i].cidadeNascimento, jogadores[i].estadoNascimento);
}

// Main
int main() {
    Jogador* jogadores = NULL;
    Jogador* vetorJogadores = NULL;
    char* entrada = (char*)malloc(250 * sizeof(char));
    int contadorVetor = 0;
    int numJogadores = 0;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while(strcmp(entrada, "FIM") != 0) {
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0) {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserirVetor(jogador, &vetorJogadores, &contadorVetor);
        }
    }

    free(entrada);
    free(jogadores);
    free(vetorJogadores);
    return 0;
}

