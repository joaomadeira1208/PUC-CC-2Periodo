#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <sys/time.h>

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

typedef struct CelulaDupla
{
    Jogador jogador;
    struct CelulaDupla *prox;
    struct CelulaDupla *ant;
} CelulaDupla;

typedef struct
{
    CelulaDupla *primeiro;
    CelulaDupla *ultimo;
} Lista;

CelulaDupla *startCelula(Jogador jogador)
{
    CelulaDupla *celula = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    celula->jogador = jogador;
    celula->prox = NULL;
    celula->ant = NULL;
    return celula;
}

Lista *startLista()
{
    Jogador jogador;
    jogador.id = -1;
    Lista *lista = (Lista*)malloc(sizeof(Lista));
    lista->primeiro = startCelula(jogador);
    lista->ultimo = lista->primeiro;
    return lista;
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


void inserirInicio(Lista *lista, Jogador jogador) {
    CelulaDupla *tmp = startCelula(jogador);
    tmp->ant = lista->primeiro;
    tmp->prox = lista->primeiro->prox;
    lista->primeiro->prox = tmp;
    if(lista->primeiro == lista->ultimo) {
        lista->ultimo = tmp;
    }
    else {
        tmp->prox->ant = tmp;
    }
    tmp = NULL;
}

void inserirFinal(Lista *lista, Jogador jogador) {
    lista->ultimo->prox = startCelula(jogador);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
}

int tamanho(Lista *lista) {
    int tamanho = 0;
    for(CelulaDupla *i = lista->primeiro; i != lista->ultimo;i = i->prox, tamanho++);
    return tamanho;
}

void inserir(Lista *lista, Jogador jogador, int pos){
    int n = tamanho(lista);
    if(pos < 0 || pos > n) {
        fprintf(stderr, "Erro ao inserir na posição desejada.");
        exit(1);
    }
    else if(pos == 0) {
        inserirInicio(lista, jogador);
    }
    else if(pos == n) {
        inserirFinal(lista, jogador);
    }
    else {
        CelulaDupla *i = lista->primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);
        CelulaDupla *tmp = startCelula(jogador);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp;
        tmp->prox->ant = tmp;
        tmp = i = NULL;
    }

}

Jogador removerInicio(Lista *lista) {
    if(lista->primeiro == lista->ultimo) {
        fprintf(stderr, "Erro: Lista Vazia (removerInicio)");
        exit(1);
    }
    CelulaDupla *tmp = lista->primeiro;
    lista->primeiro = lista->primeiro->prox;
    Jogador jogadorRemovido = lista->primeiro->jogador;
    tmp->prox = lista->primeiro->ant = NULL;
    tmp = NULL;
    return jogadorRemovido;
}

Jogador removerFinal(Lista *lista) {
    if(lista->primeiro == lista->ultimo) {
        fprintf(stderr, "Erro: Lista vazia (removerFinal)");
        exit(1);
    }
    Jogador jogadorRemovido = lista->ultimo->jogador;
    lista->ultimo = lista->ultimo->ant;
    lista->ultimo->prox = lista->ultimo->prox->ant = NULL;
    return jogadorRemovido;
}

Jogador remover(Lista *lista, Jogador jogador, int pos) {
    int n = tamanho(lista);
    Jogador resp;
    if(lista->ultimo == lista->primeiro) {
        fprintf(stderr, "Erro: lista vazia(remover)");
        exit(1);
    }
    else if(pos < 0 || pos >= n) {
        fprintf(stderr, "Erro para remover da posicao desejada");
        exit(1);
    }
    else if(pos == 0) {
        resp = removerInicio(lista);
    }
    else if(pos == n - 1) {
        resp = removerFinal(lista);
    }
    else {
        CelulaDupla *i = lista->primeiro->prox;
        for(int j = 0; j < pos; j++, i = i->prox);
        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        resp = i->jogador;
        i->prox = i->ant = NULL;
        i = NULL;
    }
    return resp;
}

void mostrar(Lista *lista) {
    for(CelulaDupla *i = lista->primeiro->prox; i != NULL; i = i->prox) {
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", i->jogador.id, i->jogador.nome, i->jogador.altura, i->jogador.peso, i->jogador.anoNascimento, 
        i->jogador.universidade, i->jogador.cidadeNascimento, i->jogador.estadoNascimento);
    }
}

CelulaDupla *encontrarPivo(Lista *lista, CelulaDupla *esq, CelulaDupla *dir) {
    CelulaDupla *ptrEsq = esq, *ptrDir = dir;
    while(ptrEsq != ptrDir && ptrEsq->prox != ptrDir) {
        ptrEsq = ptrEsq->prox;
        ptrDir = ptrDir->ant;
    }
    CelulaDupla *pivo = ptrEsq;

    return pivo;
}

void quicksort(Lista*lista, CelulaDupla *esq, CelulaDupla *dir, int *comparacoes, int *movimentacoes) {
    CelulaDupla *i = esq, *j = dir;
    CelulaDupla *Celulapivo = encontrarPivo(lista, i, j);
    Jogador pivo = Celulapivo->jogador;
    while(i != j->prox && j->prox != i->ant) {
        while(((*comparacoes)++, strcmp(i->jogador.estadoNascimento, pivo.estadoNascimento) < 0) ||
        ((*comparacoes)++, strcmp(i->jogador.estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(i->jogador.nome, pivo.nome) < 0)) {
            i = i->prox;
        }
        while(((*comparacoes)++, strcmp(j->jogador.estadoNascimento, pivo.estadoNascimento) > 0) ||
        ((*comparacoes)++, strcmp(j->jogador.estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(j->jogador.nome, pivo.nome) > 0)) {
            j = j->ant;
        }
        if(i != j->prox) {
            (*movimentacoes) += 2;
            Jogador tmp = i->jogador;
            i->jogador = j->jogador;
            j->jogador = tmp;
            i = i->prox;
            j = j->ant;
        }
    }
    if(esq != j->prox && esq != j) {
        quicksort(lista, esq, j, comparacoes, movimentacoes);
    }
    if(i != dir->prox && i != dir) {
        quicksort(lista, i, dir, comparacoes, movimentacoes);
    }
    
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

// Main
int main()
{
    Jogador *jogadores = NULL;
    Lista *lista = startLista();
    char *entrada = (char *)malloc(250 * sizeof(char));
    int n = 0;
    int numJogadores = 0;
    int contadorRemocoes = 0;
    int comparacoes = 0;
    int movimentacoes = 0;
    struct timeval startTime, endTime;
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while (strcmp(entrada, "FIM") != 0)
    {
        scanf(" %[^\n]", entrada);
        if (strcmp(entrada, "FIM") != 0)
        {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserirFinal(lista, jogador);
        }
    }
    gettimeofday(&startTime, NULL);
    quicksort(lista, lista->primeiro->prox, lista->ultimo, &comparacoes, &movimentacoes);
    gettimeofday(&endTime, NULL);
    mostrar(lista);

    double totalTime = (endTime.tv_sec - startTime.tv_sec) + ((endTime.tv_usec - startTime.tv_usec) / 1000000.0);
    FILE *logFile = fopen("800854_quicksort2.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "800854\t%d\t%d\t%lf", comparacoes, movimentacoes, totalTime);
        fclose(logFile);
    }

    free(entrada);
    free(jogadores);
    return 0;
}


