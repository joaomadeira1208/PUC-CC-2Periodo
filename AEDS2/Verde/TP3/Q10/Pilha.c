#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX 250
#define MAX_TAM 5

int delimitadorFila = 0;

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

typedef struct Celula
{
    Jogador jogador;
    struct Celula *prox;
} Celula;

typedef struct
{
    Celula *topo;
} Pilha;

// CONSTRUTOR CELULA
Celula *startCelula(Jogador jogador)
{
    Celula *celula = (Celula *)malloc(sizeof(Celula));
    celula->jogador = jogador;
    celula->prox = NULL;
    return celula;
}

// CONSTRUTOR PILHA
Pilha *startPilha()
{
    Jogador jogador;
    jogador.id = -1;
    Pilha *pilha = (Pilha *)malloc(sizeof(Pilha));
    pilha->topo = startCelula(jogador);
    return pilha;
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

// MÉTODO INSERIR
void inserir(Pilha *pilha, Jogador jogador) {
    Celula *tmp = startCelula(jogador);
    tmp->prox = pilha->topo;
    pilha->topo = tmp;
    tmp = NULL;
}

// MÉTODO REMOVER
Jogador remover(Pilha *pilha) {
    if(pilha->topo->jogador.id == -1) {
        fprintf(stderr, "Lista vazia");
        exit(1);
    }
    else {
        Celula *tmp = pilha->topo;
        Jogador resp = tmp->jogador;
        pilha->topo = pilha->topo->prox;
        tmp->prox = NULL;
        tmp = NULL;
        return resp;
    }
}

// MÉTODO INVERTER PILHA
Pilha *inverterPilha(Pilha *pilha) {
    Pilha *resp = startPilha();
    Celula *i;
    for(i = pilha->topo; i->prox != NULL; i = i->prox) {
        inserir(resp, i->jogador);
    }
    return resp;
}

// IMPRIMIR
void mostrar(Pilha *pilha) {
    Celula *i;
    int contador = 0;
    for(i = pilha->topo; i->prox != NULL; i = i->prox) {
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", contador, i->jogador.nome,
               i->jogador.altura, i->jogador.peso, i->jogador.anoNascimento, i->jogador.universidade, i->jogador.cidadeNascimento, i->jogador.estadoNascimento);
        
        contador++;
    }
}

// SPLIT
char **split(char *str, const char *delimitador)
{
    char **result;
    int count = 0;
    char *temp = strdup(str);
    char *token = strtok(temp, delimitador);

    while (token != NULL)
    {
        count++;
        token = strtok(NULL, delimitador);
    }
    free(temp);

    result = (char **)malloc((count + 1) * sizeof(char *));
    temp = strdup(str);
    token = strtok(temp, delimitador);
    count = 0;

    while (token != NULL)
    {
        result[count] = strdup(token);
        count++;
        token = strtok(NULL, delimitador);
    }
    result[count] = NULL;
    free(temp);

    return result;
}

// Main
int main()
{
    Jogador *jogadores = NULL;
    Pilha *pilha = startPilha();
    char *entrada = (char *)malloc(250 * sizeof(char));
    int n = 0;
    int numJogadores = 0;
    int contadorRemocoes = 0;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while (strcmp(entrada, "FIM") != 0)
    {
        scanf(" %[^\n]", entrada);
        if (strcmp(entrada, "FIM") != 0)
        {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserir(pilha, jogador);
        }
    }

    scanf(" %[^\n]", entrada);
    int quantidadeRegistros = atoi(entrada);
    for (int i = 0; i < quantidadeRegistros; i++)
    {
        scanf(" %[^\n]", entrada);
        char **splits = split(entrada, " ");
        if (strcmp(splits[0], "I") == 0)
        {
            int idJogador = atoi(splits[1]);
            Jogador jogador = jogadores[idJogador];
            inserir(pilha, jogador);
        }
        else
        {
            Jogador jogadorRemovido = remover(pilha);
            printf("(R) %s\n", jogadorRemovido.nome);
        }
    }
    pilha = inverterPilha(pilha);
    mostrar(pilha);

    free(entrada);
    free(jogadores);
    return 0;
}
