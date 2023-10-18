#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

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

// Função para obter o índice do maior filho de um nó no heap.
int getMaiorFilho(Jogador *array, int i, int tamHeap){
    int filho;
    if (2*i == tamHeap || array[2*i].altura > array[2*i+1].altura){
        filho = 2*i;
    }
    else if(array[2*i].altura == array[2*i + 1].altura) {
        if(strcmp(array[2*i].nome, array[2*i + 1].nome) > 0) {
            filho = 2*i;
        }
        else {
            filho = 2*i + 1;
        }
    } 
    else {
        filho = 2*i + 1;
    }
    return filho;
}

// Função para construir o heap a partir da altura dos jogadores, utilizando o nome como chave de desempate.
void construir(Jogador *array, int tamHeap){
    int i = tamHeap;
    while(i > 1) {
        if(array[i].altura > array[i/2].altura) {
            swap(array + i, array + i/2);
        }
        else if(array[i].altura == array[i/2].altura) {
            if(strcmp(array[i].nome, array[i/2].nome) > 0) {
                swap(array + i, array + i/2);
            }
            else {
                break;
            }
        }
        else {
            break;
        }
        i /= 2;

    }
}

// Função para reconstruir o heap após uma modificação.
void reconstruir(Jogador *array, int tamHeap){
    int i = 1;
    while(i <= (tamHeap/2)){
        int filho = getMaiorFilho(array, i, tamHeap);
        if(array[i].altura < array[filho].altura){
            swap(array + i, array + filho);
            i = filho;
        }
        else if(array[i].altura == array[filho].altura) {
            if(strcmp(array[i].nome, array[filho].nome) < 0) {
                swap(array + i, array + filho);
                i = filho;
            }
            else {
                i = tamHeap;
            }
        }
        else{
            i = tamHeap;
        }
    }
}

// Função para realizar o heapsort parcial dos jogadores com base em suas alturas.
Jogador* heapSortPartial(Jogador* jogadores, int numJogadores, int k) {
    Jogador *arrayTmp = (Jogador*) malloc((k + 1) *sizeof(Jogador));
    int size = 0;
    
    for(int i = 0; i < k; i++) {
        arrayTmp[i+1] = jogadores[i];
        size++;
    }


    for(int tamHeap = 2; tamHeap <= size; tamHeap++) {
        construir(arrayTmp, tamHeap);
    }

    

    for(int i = k; i < numJogadores; i++) {
        bool condicaoEmpate = (jogadores[i].altura == arrayTmp[1].altura) && (strcmp(jogadores[i].nome, arrayTmp[1].nome) < 0);
        if(jogadores[i].altura < arrayTmp[1].altura || condicaoEmpate) {
            arrayTmp[1] = jogadores[i];
            reconstruir(arrayTmp, k);
        }
    }

    int tamHeap = k;
    while(tamHeap > 1) {
        swap(arrayTmp + 1, arrayTmp + tamHeap--);
        reconstruir(arrayTmp, tamHeap);
    }

    

    Jogador *listaParcial = (Jogador*) malloc(k * sizeof(Jogador));
    for(int i = 0; i < k; i++) {
        listaParcial[i] = arrayTmp[i+1];
    }

    free(arrayTmp);

    return listaParcial;

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
    ler("/tmp/players.csv", &jogadores, &numJogadores);
    while(strcmp(entrada, "FIM") != 0) {
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0) {
            int num = atoi(entrada);
            Jogador jogador = jogadores[num];
            inserirVetor(jogador, &vetorJogadores, &contadorVetor);
        }
    }
  
    Jogador* listaParcial = heapSortPartial(vetorJogadores, contadorVetor, 10);

    for(int i = 0; i < 10; i++) {
        imprimir(listaParcial, i);
    }



    free(entrada);
    free(jogadores);
    free(vetorJogadores);
    return 0;
}

