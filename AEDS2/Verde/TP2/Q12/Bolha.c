#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <sys/time.h>

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

// Função swap que troca a posição de dois jogadores no vetor.
void swap(Jogador *a, Jogador *b) {
    Jogador temp = *a;
    *a = *b;
    *b = temp;
}

// Função recursiva para realizar o quicksort em um array de Jogador com base em seu nome
void quicksortRec(Jogador* jogadores, int esq, int dir) {
    int i = esq, j = dir;
    Jogador pivo = jogadores[(dir + esq)/2];
    while(i <= j) {
        
        while((strcmp(jogadores[i].nome, pivo.nome) < 0) ) {
            i++;
        }
        
        while((strcmp(jogadores[j].nome, pivo.nome) > 0)  ) {
            j--;
        }
        if(i <= j) {
            swap(jogadores + i, jogadores + j);
            i++;
            j--;
        }
    }
    if(esq < j) quicksortRec(jogadores, esq, j);
    if(i < dir) quicksortRec(jogadores, i, dir);
}

// Função para realizar a ordenação Quicksort em um array de Jogador.
void quicksort(Jogador * jogadores, int numJogadores) {
    quicksortRec(jogadores, 0, numJogadores-1);
}

// Função para realizar o bubble sort a partir do ano de nascimento do Jogador.
void bolha(Jogador *jogadores, int numJogadores, int *comparacoes, int *movimentacoes) {
    int i, j, n = numJogadores;
    for(i = n - 1; i > 0; i--) {
        for(j = 0; j < i; j++) {
            (*comparacoes)++;
            if(jogadores[j].anoNascimento > jogadores[j + 1].anoNascimento) {
                (*movimentacoes) += 2;
                swap(&jogadores[j], &jogadores[j+1]);
            }
        }
    }
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
    int comparacoes = 0;
    int movimentacoes = 0;
    struct timeval startTime, endTime;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while(strcmp(entrada, "FIM") != 0) {
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0) {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserirVetor(jogador, &vetorJogadores, &contadorVetor);
        }
    }
    quicksort(vetorJogadores, contadorVetor);
    gettimeofday(&startTime, NULL);
    bolha(vetorJogadores, contadorVetor, &comparacoes, &movimentacoes);
    gettimeofday(&endTime, NULL);
    for(int i = 0; i < contadorVetor; i++) {
        imprimir(vetorJogadores, i);
    }


    double totalTime = (endTime.tv_sec - startTime.tv_sec) + ((endTime.tv_usec - startTime.tv_usec) / 1000000.0);

    FILE *logFile = fopen("800854_bolha.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "800854\t%d\t%d\t%lf", comparacoes, movimentacoes, totalTime);
        fclose(logFile);
    }

    free(entrada);
    free(jogadores);
    free(vetorJogadores);
    return 0;
}

