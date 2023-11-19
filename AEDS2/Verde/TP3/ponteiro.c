#include <stdio.h>
#include <stdlib.h>

void ponteiro(int *a, int*b) {
    *a *= 2;
    *b *= 3;
}

int main() {

    int valor = 20;
    int a = valor;
    int *b = &valor;
    ponteiro(&a, b);

    printf("%d %d", a, *b);
    return 0;
}