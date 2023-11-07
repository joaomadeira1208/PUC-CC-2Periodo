#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool isVazio(int array[]) {
    if(array[0] == -1) {
        return true;
    }
    return false;
}

void retirar(int array[], int array2[], int n) {
    for(int i = 0; i < n - 1; i++) {
        array[i] = array[i+1];
        array2[i] = array2[i+1];
    }
    array[n-1] = -1;
    array2[n-1] = -1;
}


void ordenacao(int array[], int array2[], int n) {
    for(int i = 0; i < n; i++) {
        for(int j = n - 1; j > 1; j--) {
            if(array2[j] >  array2[j-1]) {
                int tmp= array2[j];
                array2[j] = array2[j-1];
                array2[j-1] = tmp;

                tmp= array[j];
                array[j] = array[j-1];
                array[j-1] = tmp;
            }
        }
    }
} 

int main() {
    char* entrada = (char*) malloc(105*sizeof(char));
    int treinoDragoes[100];
    int multaDragoes[100];
    int n = 0;
    bool treinoFinalizado = false;
    int multaTotal = 0;
    int dragaoTreinando = 0;
    while(scanf(" %[^\n]", entrada) != EOF || !treinoFinalizado) {
        
        if (feof(stdin)) {

        }
        else {
            n++;
            char* temp = strdup(entrada);
            char* token = strtok(temp, " ");
            int contador = 0;
            int treinamento, multa;
            while(token != NULL) {
                if(contador == 0) {
                    treinamento = atoi(token);
                }
                else {
                    multa = atoi(token);
                }
                contador++;
                token = strtok(NULL, " ");
            }
            free(temp);

        
            treinoDragoes[n-1] = treinamento;
            multaDragoes[n-1] = multa;
        
        
        
        
            ordenacao(treinoDragoes, multaDragoes, n);
        }

        for(int i = 0; i < n; i++) {
            printf("%d %d\n", treinoDragoes[i], multaDragoes[i]);
        }

        if(treinoDragoes[0] == 0) {
            retirar(treinoDragoes, multaDragoes, n);
            n--;
        }
        

        treinoDragoes[0]--;

        if(n > 1) {
            for(int i = 1; i < n; i++) {
                multaTotal += multaDragoes[i];
            }
        }

        if(n == 0) {
            treinoFinalizado = true;
        }

        
    }
    printf("%d", multaTotal);

}