#include <stdio.h>
#include <stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula *prox;
} Celula;

Celula *novaCelula(int elemento) {
    Celula *nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

int main() {
    Celula *tmp = novaCelula(3);

    // Imprime o valor do elemento da célula apontada por tmp
    printf("Valor do elemento: %d\n", tmp->elemento);

    // Libera a memória alocada para a célula
    free(tmp);

    return 0;
}
