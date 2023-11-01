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

void inserir(Jogador jogador, Jogador** listaSequencial, int *n, int posicao) {
    *listaSequencial = (Jogador*) realloc(*listaSequencial, (*n + 1) * sizeof(Jogador));
    if(posicao < *n) {
        for(int i = *n - 1; i > posicao - 1; i--) {
            (*listaSequencial)[i + 1] = (*listaSequencial)[i];
        }
        (*listaSequencial)[posicao] = jogador;
        (*n)++;
    }
}

void inserirInicio(Jogador jogador, Jogador** listaSequencial, int *n) {
    *listaSequencial = (Jogador*) realloc(*listaSequencial, (*n + 1) * sizeof(Jogador));
    for(int i = *n - 1; i >= 0; i--) {
        (*listaSequencial)[i + 1] = (*listaSequencial)[i]; 
    }
    (*listaSequencial)[0] = jogador;
    (*n)++;
}

// Função para inserir um objeto Jogador no final da listaSequencial.
void inserirFinal(Jogador jogador, Jogador** listaSequencial, int *n) {
    if(*listaSequencial == NULL) {
        *listaSequencial = (Jogador*) malloc(sizeof(Jogador));
    }
    else {
        *listaSequencial = (Jogador*) realloc(*listaSequencial, (*n + 1) * sizeof(Jogador));
    }

    (*listaSequencial)[*n] = jogador;
    (*n)++;
}

Jogador remover(Jogador** listaSequencial, int *n, int posicao) {
    Jogador resp = (*listaSequencial)[posicao];
    for(int i = posicao; i < *n - 1; i++) {
        (*listaSequencial)[i] = (*listaSequencial)[i+1];
    }
    *listaSequencial = (Jogador*) realloc(*listaSequencial, (*n - 1) * sizeof(Jogador));
    (*n)--;
    return resp;

}

Jogador removerInicio(Jogador** listaSequencial, int *n) {
    Jogador resp = (*listaSequencial)[0];
    for(int i = 0; i < *n - 1; i++) {
        (*listaSequencial)[i] = (*listaSequencial)[i+1];
    }
    *listaSequencial = (Jogador*) realloc(*listaSequencial, (*n - 1) * sizeof(Jogador));
    (*n)--;
    return resp;
    
}

Jogador removerFinal(Jogador** listaSequencial, int*n) {
    Jogador resp = (*listaSequencial)[(*n) - 1];
    *listaSequencial = (Jogador*) realloc(*listaSequencial, (*n - 1) * sizeof(Jogador));
    (*n)--;
    return resp;
}

char** split(char* str, const char* delimitador) {
    char** result;
    int count = 0;
    char* temp = strdup(str);  
    char* token = strtok(temp, delimitador);

    
    while (token != NULL) {
        count++;
        token = strtok(NULL, delimitador);
    }
    free(temp);

    
    result = (char**)malloc((count + 1) * sizeof(char*));
    temp = strdup(str);
    token = strtok(temp, delimitador);
    count = 0;

    
    while (token != NULL) {
        result[count] = strdup(token);
        count++;
        token = strtok(NULL, delimitador);
    }
    result[count] = NULL;  
    free(temp);

    return result;
}

// Função para imprimir as informações de um jogador no formato especificado.""
void imprimir(Jogador* jogadores, int i) {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", i, jogadores[i].nome, 
    jogadores[i].altura, jogadores[i].peso, jogadores[i].anoNascimento, jogadores[i].universidade, jogadores[i].cidadeNascimento, jogadores[i].estadoNascimento);
}

// Main
int main() {
    Jogador* jogadores = NULL;
    Jogador* listaSequencial = NULL;
    Jogador* jogadoresRemovidos = NULL;
    char* entrada = (char*)malloc(250 * sizeof(char));
    int n = 0;
    int numJogadores = 0;
    int contadorRemocoes = 0;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while(strcmp(entrada, "FIM") != 0) {
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0) {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserirFinal(jogador, &listaSequencial, &n);
        }
    }
    char* entrada2 = (char*)malloc(250 * sizeof(char));
    scanf(" %[^\n]", entrada2);
    int quantidadeRegistros = atoi(entrada2);
    for(int i = 0; i < quantidadeRegistros; i++) {
        scanf(" %[^\n]", entrada2);
        char** splits = split(entrada2, " ");
        if(strcmp(splits[0], "II") == 0) {
            int idJogador = atoi(splits[1]);
            Jogador jogador = jogadores[idJogador];
            inserirInicio(jogador, &listaSequencial, &n);
        }
        else if(strcmp(splits[0], "IF") == 0) {
            int idJogador = atoi(splits[1]);
            Jogador jogador = jogadores[idJogador];
            inserirFinal(jogador, &listaSequencial, &n);
        }
        else if(strcmp(splits[0], "I*") == 0) {
            int posicao = atoi(splits[1]);
            int idJogador = atoi(splits[2]);
            Jogador jogador = jogadores[idJogador];
            inserir(jogador, &listaSequencial, &n, posicao);
        }
        else if(strcmp(splits[0], "RI") == 0) {
            if(jogadoresRemovidos == NULL) {
                jogadoresRemovidos = (Jogador*)malloc(sizeof(Jogador));
            }
            else {
                jogadoresRemovidos = (Jogador*)realloc(jogadoresRemovidos, (contadorRemocoes + 1) * sizeof(Jogador));
            }
            jogadoresRemovidos[contadorRemocoes] = removerInicio(&listaSequencial, &n);
            contadorRemocoes++;
        }
        else if(strcmp(splits[0], "RF") == 0) {
            if(jogadoresRemovidos == NULL) {
                jogadoresRemovidos = (Jogador*)malloc(sizeof(Jogador));
            }
            else {
                jogadoresRemovidos = (Jogador*)realloc(jogadoresRemovidos, (contadorRemocoes + 1) * sizeof(Jogador));
            }
            jogadoresRemovidos[contadorRemocoes] = removerFinal(&listaSequencial, &n);
            contadorRemocoes++;
        }
        else {
            int posicao = atoi(splits[1]);
            if(jogadoresRemovidos == NULL) {
                jogadoresRemovidos = (Jogador*)malloc(sizeof(Jogador));
            }
            else {
                jogadoresRemovidos = (Jogador*)realloc(jogadoresRemovidos, (contadorRemocoes + 1) * sizeof(Jogador));
            }
            jogadoresRemovidos[contadorRemocoes] = remover(&listaSequencial, &n, posicao);
            contadorRemocoes++;
        }

    }

    for(int i = 0; i < contadorRemocoes; i++) {
        printf("(R) %s\n", jogadoresRemovidos[i].nome);
    }

    for(int i = 0; i < n; i++) {
        imprimir(listaSequencial, i);
    }

    free(entrada);
    free(jogadores);
    free(listaSequencial);
    return 0;
}

