#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <sys/time.h>


#define MAX 250
#define MAX_TAM 5
#define TAM 25

int comparacoes = 0;

// Struct Jogador
typedef struct
{
    int id;
    char nome[MAX];
    int altura;
    int peso;
    char universidade[MAX];
    int anoNascimento;
    char cidadeNascimento[MAX];
    char estadoNascimento[MAX];

} Jogador;

typedef struct Celula {
    struct Celula *prox;
    Jogador jogador;
}Celula;

typedef struct Lista {
    Celula *primeiro;
    Celula *ultimo;
} Lista;

typedef struct Hash {
    int tamanho;
    Lista *tabela[TAM];

} Hash;

Celula *startCelula(Jogador jogador) {
    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->jogador = jogador;
    celula->prox = NULL;
    return celula;
}

Lista *startLista() {
    Jogador jogador;
    jogador.id = -1;
    Lista *lista = (Lista*)malloc(sizeof(Lista));
    lista->primeiro = startCelula(jogador);
    lista->ultimo = lista->primeiro;
    return lista;
}

Hash startHash() {
    Hash hash;
    hash.tamanho = TAM;
    for(int i = 0; i < hash.tamanho; i++) {
        hash.tabela[i] = startLista();
    }
    return hash;
}


/*
    Função para ler jogadores de um arquivo CSV e preencher uma lista de jogadores.
    O arquivo deve ter um cabeçalho e os campos separados por vírgulas.
*/
void ler(const char *nomeArquivo, Jogador **pJogadores, int *pNumJogadores)
{
    int tamanhoAtual = 10;
    *pJogadores = (Jogador *)malloc(tamanhoAtual * sizeof(Jogador));
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL)
    {
        perror("Erro ao abrir o arquivo");
        return;
    }

    char linha[1000];
    int cabecalho = 1;

    while (fgets(linha, sizeof(linha), arquivo) != NULL)
    {
        if (cabecalho)
        {
            cabecalho = 0;
            continue;
        }

        int inicioCampo = 0;
        int contador = 0;
        int k = 0;
        char *parts[8];

        for (int i = 0; i < strlen(linha); i++)
        {
            if (linha[i] == ',')
            {
                if (i == inicioCampo)
                {
                    parts[contador] = "nao informado";
                }
                else
                {
                    char aux[i - inicioCampo];
                    for (int j = inicioCampo; j < i; j++)
                    {
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
        if (inicioCampo < strlen(linha) - 1)
        {
            char aux[1000];
            int k = 0;
            for (int j = inicioCampo; j < strlen(linha); j++)
            {
                aux[k] = linha[j];
                k++;
            }
            aux[k] = '\0';
            if (k > 0 && aux[k - 1] == '\n')
            {
                aux[k - 1] = '\0';
            }
            parts[contador] = strdup(aux);
            contador++;
        }
        else
        {
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

        if (*pNumJogadores == tamanhoAtual)
        {
            tamanhoAtual *= 2;
            *pJogadores = (Jogador *)realloc(*pJogadores, tamanhoAtual * sizeof(Jogador));
        }
        (*pJogadores)[*pNumJogadores] = jogador;
        (*pNumJogadores)++;

    }

    fclose(arquivo);
}

bool pesquisarLista(Jogador jogador, Lista *lista) {
    bool resp = false;
    for(Celula* i = lista->primeiro->prox; i != NULL; i = i->prox) {
        comparacoes++;
        if(jogador.id == i->jogador.id) {
            resp = true;
            break;
        }
    }
    return resp;
}

void inserirLista(Jogador jogador, Lista *lista) {
    Celula *tmp = startCelula(jogador);
    lista->ultimo->prox = tmp;
    lista->ultimo = lista->ultimo->prox;
    tmp = NULL;
}


int h(Jogador jogador, Hash hash) {
    return (jogador.altura % hash.tamanho);
}

void inserir(Jogador jogador, Hash *hash) {
    int pos = h(jogador, *hash);
    inserirLista(jogador, hash->tabela[pos]);
}

bool pesquisar(Jogador jogador, Hash *hash) {
    int pos = h(jogador, *hash);
    bool resp = pesquisarLista(jogador, hash->tabela[pos]);
    return resp;
}



Jogador pesquisarJogadorNome(Jogador **jogadores, char *nome, int *numJogadores)
{
    for (int i = 0; i < *numJogadores; i++)
    {
        if (strcmp((*jogadores)[i].nome, nome) == 0)
        {
            return (*jogadores)[i];
        }
    }
    Jogador naoEncontrado;
    naoEncontrado.id = -1;
    return naoEncontrado;
}

Jogador encontrarNome(char *nome, Jogador **jogadores, int numJogadores) {
    for (int i = 0; i < numJogadores; i++) {
        if (strcmp((*jogadores)[i].nome, nome) == 0) {
            return (*jogadores)[i];
        }
    }

    Jogador naoEncontrado;
    naoEncontrado.id = -1;
    strcpy(naoEncontrado.nome, "Nao Encontrado");
    return naoEncontrado;
}

void limparFinal(char *string) {
    int length = strlen(string);
    if (length > 0 && string[length - 1] == '\r') {
        string[length - 1] = '\0';
    }
}



// Main
int main()
{
    struct timeval startTime, endTime;
    Jogador *jogadores = NULL;
    Hash hash = startHash();
    char *entrada = (char *)malloc(250 * sizeof(char));
    int n = 0;
    int numJogadores = 0;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while (strcmp(entrada, "FIM") != 0)
    {
        scanf(" %[^\n]", entrada);
        limparFinal(entrada);
        if (strcmp(entrada, "FIM") != 0)
        {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserir(jogador, &hash);
        }
    }

    char *entrada2 = (char *)malloc(250 * sizeof(char));
    gettimeofday(&startTime, NULL);
    while (strcmp(entrada2, "FIM") != 0)
    {
        scanf(" %[^\n]", entrada2);
        limparFinal(entrada2);
        if (strcmp(entrada2, "FIM") != 0)
        {
            printf("%s ", entrada2);
            Jogador jogadorNome = encontrarNome(entrada2, &jogadores, numJogadores);
            bool resp = pesquisar(jogadorNome, &hash);
            if(resp) {
                printf("SIM\n");
            }
            else {
                printf("NAO\n");
            }
        }
    }

    gettimeofday(&endTime, NULL);

    double totalTime = (endTime.tv_sec - startTime.tv_sec) + ((endTime.tv_usec - startTime.tv_usec) / 1000000.0);
    FILE *logFile = fopen("800854_hashIndireta.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "800854\t%d\t%lf", comparacoes, totalTime);
        fclose(logFile);
    }
    

    free(entrada);
    free(entrada2);
    free(jogadores);
    return 0;
}
