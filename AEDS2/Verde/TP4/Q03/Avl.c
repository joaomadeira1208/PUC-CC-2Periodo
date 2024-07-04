#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <sys/time.h>


#define MAX 250
#define MAX_TAM 5

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

typedef struct No
{
    Jogador jogador;
    struct No *esq;
    struct No *dir;
    int nivel;
} No;

typedef struct
{
    No *raiz;
} Avl;

No *startNo(Jogador jogador)
{
    No *no = (No*)malloc(sizeof(No));
    no->jogador = jogador;
    no->dir = NULL;
    no->esq = NULL;
    no->nivel = 1;
    return no;
}

int getNivel(No *i) {
    if(i == NULL) {
        return 0;
    }
    else {
        return i->nivel;
    }
}

void setNivel(No *no) {
    int noEsq = getNivel(no->esq);
    int noDir = getNivel(no->dir);

    if(noEsq > noDir) {
        no->nivel = 1 + noEsq;
    }
    else {
        no->nivel = 1 + noDir;
    }
}

Avl *startAvl()
{
    Avl *avl = (Avl*)malloc(sizeof(Avl));
    avl->raiz = NULL;
    return avl;
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

No *rotacionarDir(No *i) {
    No *noEsq = i->esq;
    No *noEsqDir = noEsq->dir;
    
    noEsq->dir = i;
    i->esq = noEsqDir;
    setNivel(i);
    setNivel(noEsq);

    return noEsq;
}

No *rotacionarEsq(No *i) {
    No *noDir = i->dir;
    No *noDirEsq = noDir->esq;

    noDir->esq = i;
    i->dir = noDirEsq;
    setNivel(i);
    setNivel(noDir);

    return noDir;
}

No *rotacionarDirEsq(No *i) {
    i->dir = rotacionarDir(i->dir);
    return rotacionarEsq(i);
}

No *rotacionarEsqDir(No *i) {
    i->esq = rotacionarEsq(i->esq);
    return rotacionarDir(i);
}

No *balancear(No *i) {
    if(i != NULL) {
        int fator = getNivel(i->dir) - getNivel(i->esq);

        if(fator == 1 || fator == 0 || fator == -1) {
            setNivel(i);
        }
        else if(fator == 2) {
            int fatorFilhoDir = getNivel(i->dir->dir) - getNivel(i->dir->esq);
            if(fatorFilhoDir == -1) {
                i = rotacionarDirEsq(i);
            }
            else {
                i = rotacionarEsq(i);
            }
        }
        else if(fator == -2) {
            int fatorFilhoEsq = getNivel(i->esq->dir) - getNivel(i->esq->esq);

            if(fatorFilhoEsq == 1) {
                i = rotacionarEsqDir(i);
            }
            else {
                i = rotacionarDir(i);
            }
        }
        else {
            fprintf(stderr, "Fator de balanceamento invalido");
            exit(1);
        }

        return i;
    }
}

No *inserirRec(Jogador jogador, No *i) {
    if(i == NULL) {
        i = startNo(jogador);
    }
    else if(strcmp(jogador.nome,i->jogador.nome) < 0) {
        i->esq = inserirRec(jogador, i->esq);
    }
    else if(strcmp(jogador.nome, i->jogador.nome) > 0) {
        i->dir = inserirRec(jogador, i->dir);
    }
    else {
        fprintf(stderr, "Jogador repetido");
        exit(1);
    }

    return balancear(i);
}

void inserir(Avl *arvore, Jogador jogador) {
    arvore->raiz = inserirRec(jogador, arvore->raiz);
}


bool pesquisarRec(No *i, char *nome) {
    bool resp;
    if(i == NULL) {
        resp = false;
    }
    else {
        comparacoes++;
        if(strcmp(nome, i->jogador.nome) == 0) {
            resp = true;
        }
        else {
            comparacoes++;
            if(strcmp(nome, i->jogador.nome) < 0) {
                printf("esq ");
                resp = pesquisarRec(i->esq, nome);
            }
        
            else {
                printf("dir ");
                resp = pesquisarRec(i->dir, nome);
            }
        }
    }

    return resp;
}

bool pesquisar(Avl *arvore, char* nome) {
    if(arvore->raiz != NULL) {
        printf("raiz ");
    }
    return pesquisarRec(arvore->raiz, nome);
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
    struct timeval startTime, endTime;
    Jogador *jogadores = NULL;
    Avl *arvore = startAvl();
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
            inserir(arvore, jogador);
        }
    }

    char *entrada2 = (char *)malloc(250 * sizeof(char));
    gettimeofday(&startTime, NULL);
    while (strcmp(entrada2, "FIM") != 0)
    {
        scanf(" %[^\n]", entrada2);
        if (strcmp(entrada2, "FIM") != 0)
        {
            printf("%s ", entrada2);
            bool resp = pesquisar(arvore, entrada2);
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
    FILE *logFile = fopen("800854_avl.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "800854\t%d\t%lf", comparacoes, totalTime);
        fclose(logFile);
    }
    

    free(entrada);
    free(entrada2);
    free(jogadores);
    return 0;
}
