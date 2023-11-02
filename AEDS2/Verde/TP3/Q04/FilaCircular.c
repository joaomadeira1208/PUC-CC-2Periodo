#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX 250
#define MAX_TAM 6

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

typedef struct
{
    Jogador filaCircular[MAX_TAM];
    int primeiro;
    int ultimo;
    int n;
} FilaCircular;

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

void start(FilaCircular *fila)
{
    fila->primeiro = fila->ultimo = fila->n = 0;
}

Jogador remover(FilaCircular *fila)
{
    if (fila->primeiro == fila->ultimo)
    {
        exit(1);
    }

    Jogador resp = fila->filaCircular[fila->primeiro];
    fila->primeiro = (fila->primeiro + 1) % MAX_TAM;
    fila->n--;
    return resp;
}

void inserir(FilaCircular *fila, Jogador jogador)
{
    if (((fila->ultimo + 1) % MAX_TAM) == fila->primeiro)
    {
        Jogador jogador = remover(fila);
    }
    fila->filaCircular[fila->ultimo] = jogador;
    fila->ultimo = (fila->ultimo + 1) % MAX_TAM;
    fila->n++;
}

float mediaAlturas(FilaCircular *fila)
{
    float soma = 0;
    int i = fila->primeiro;
    while (i != fila->ultimo)
    {
        soma += fila->filaCircular[i].altura;
        i = (i + 1) % MAX_TAM;
    }
    float media = soma / fila->n;
    printf("%.f\n", media);
    return media;
}

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

// Função para imprimir as informações de um jogador no formato especificado.""
void imprimir(FilaCircular *fila)
{
    int contador = 0;
    int i = fila->primeiro;
    while (i != fila->ultimo)
    {
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", contador, fila->filaCircular[i].nome,
               fila->filaCircular[i].altura, fila->filaCircular[i].peso, fila->filaCircular[i].anoNascimento, fila->filaCircular[i].universidade, fila->filaCircular[i].cidadeNascimento, fila->filaCircular[i].estadoNascimento);

        i = (i + 1) % MAX_TAM;
        contador++;
    }
}

// Main
int main()
{
    Jogador *jogadores = NULL;
    FilaCircular filaCircular;
    start(&filaCircular);
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
            inserir(&filaCircular, jogador);
            mediaAlturas(&filaCircular);
        }
    }
    char *entrada2 = (char *)malloc(250 * sizeof(char));
    scanf(" %[^\n]", entrada2);
    int quantidadeRegistros = atoi(entrada2);
    for (int i = 0; i < quantidadeRegistros; i++)
    {
        scanf(" %[^\n]", entrada2);
        char **splits = split(entrada2, " ");
        if (strcmp(splits[0], "I") == 0)
        {
            int idBusca = atoi(splits[1]);
            Jogador jogador = jogadores[idBusca];
            inserir(&filaCircular, jogador);
            mediaAlturas(&filaCircular);
        }
        else
        {
            Jogador jogadorRemovido = remover(&filaCircular);
            printf("(R) %s\n", jogadorRemovido.nome);
        }
    }

    imprimir(&filaCircular);

    free(entrada);
    free(jogadores);
    return 0;
}
