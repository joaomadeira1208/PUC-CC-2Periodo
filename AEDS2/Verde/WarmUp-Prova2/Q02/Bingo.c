#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


char **split(char* str, const char* delimitador) {
    int contador = 0;
    char* tmp = strdup(str);
    char* token = strtok(tmp, delimitador);
    while(token != NULL) {
        contador++;
        token = strtok(NULL, delimitador);
    }
    free(tmp);

    char** splits = (char**)malloc((contador + 1) * sizeof(char*));
    tmp = strdup(str);
    contador = 0;
    token = strtok(tmp, delimitador);

    while(token != NULL) {
        splits[contador] = strdup(token);
        contador++;
        token = strtok(NULL, delimitador);
    }
    splits[contador] = NULL;
    free(tmp);

    return splits;
}

int main() {
    char *str = (char*) malloc(250 * sizeof(char));
    char *entrada = (char*) malloc(250 * sizeof(char));

    while(scanf(" %[^\n]", str) != EOF) {
    char** partes = split(str, " ");
    int N = atoi(partes[0]);
    int K = atoi(partes[1]);
    int maiorNatural = atoi(partes[2]);
    int *cartela;
    int **cartelas = (int**) malloc(N * sizeof(cartela));
    for(int i = 0; i < N; i++) {
        scanf(" %[^\n]", entrada);
        partes = split(entrada, " ");
        cartela = (int*)malloc(K * sizeof(int));
        for(int j = 0; j < K; j++) {
            cartela[j] = atoi(partes[j]);
        }
        cartelas[i] = cartela;
        for(int j = 0;partes[j] != NULL; j++) {
            free(partes[j]);
        }
        free(partes);
    }

    scanf(" %[^\n]", entrada);
    partes = split(entrada, " ");
    int cartelaSorteada[maiorNatural];
    for(int i = 0; i < maiorNatural; i++) {
        cartelaSorteada[i] = atoi(partes[i]);
    }

    
    int contador = 0;
    int tempo = 0;
    int tempoVencedor = maiorNatural + 1;
    int vencedor = maiorNatural + 1;
    bool numeroEncontrado;
    for(int i = 0; i < N; i++) {
        for(int k = 0; k < maiorNatural && contador != K; k++) {
            numeroEncontrado = false;
            for(int j = 0; j < K && !numeroEncontrado && contador != K; j++) {
                if(cartelas[i][j] == cartelaSorteada[k]) {
                    contador++;
                    numeroEncontrado = true;
                }
            }

            tempo++;
        }
        
        if(tempo < tempoVencedor){
            tempoVencedor = tempo;
            vencedor = i+1;
        }
            
        
        contador = tempo = 0;
    }

    printf("%d\n", vencedor);
    }
    return 0;


}