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

// Função para encontrar o jogador com o maior ID entre o array de jogadores fornecido.
Jogador getMax(Jogador *jogadores, int numJogadores, int *comparacoes) {
    Jogador maior = jogadores[0];

    for (int i = 1; i < numJogadores; i++) {
        (*comparacoes)++;
        if(maior.id < jogadores[i].id){
            maior = jogadores[i];
        }
    }
    return maior;
}

// Função para ordenar os Jogadores com base no dígito representado por 'exp', utilizando o método de contagem.
void radcountingSort(Jogador *jogadores, int numJogadores, int exp, int *comparacoes, int *movimentacoes) {
    int count[10] = {0};  
    Jogador output[numJogadores];

    
    for(int i = 0; i < numJogadores; i++) {
        count[(jogadores[i].id/exp) % 10]++;
        (*comparacoes)++;
    }

    
    for(int i = 1; i < 10; i++) {
        count[i] += count[i-1];
        (*comparacoes)++;
    }

    
    for(int i = numJogadores - 1; i >= 0; i--) {
        (*comparacoes)++;
        (*movimentacoes)++;
        output[count[(jogadores[i].id/exp) % 10] - 1] = jogadores[i];
        count[(jogadores[i].id/exp) % 10]--;
    }

    
    for(int i = 0; i < numJogadores; i++) {
        (*movimentacoes)++;
        jogadores[i] = output[i];
    }
}
// Função para ordenar o vetor de Jogadores a partir de seus respectivos IDs, utilizando o método de ordenação RadixSort.
void radixsort(Jogador *jogadores, int numJogadores, int *comparacoes, int *movimentacoes) {
    Jogador max = getMax(jogadores, numJogadores, comparacoes);
    for(int exp = 1; max.id/exp > 0; exp *= 10) {
        radcountingSort(jogadores, numJogadores, exp, comparacoes, movimentacoes);
    }
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
    gettimeofday(&startTime, NULL);
    radixsort(vetorJogadores, contadorVetor, &comparacoes, &movimentacoes);
    gettimeofday(&endTime, NULL);
    for(int i = 0; i < contadorVetor; i++) {
        imprimir(vetorJogadores, i);
    }


    double totalTime = (endTime.tv_sec - startTime.tv_sec) + ((endTime.tv_usec - startTime.tv_usec) / 1000000.0);

    FILE *logFile = fopen("800854_radixsort.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "800854\t%d\t%d\t%lf", comparacoes, movimentacoes, totalTime);
        fclose(logFile);
    }

    free(entrada);
    free(jogadores);
    free(vetorJogadores);
    return 0;
}

