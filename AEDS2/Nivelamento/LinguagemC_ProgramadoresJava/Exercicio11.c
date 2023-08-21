#include <stdio.h>

int main() {
    int n, numero, soma;

    printf("Digite a quantidade de números: ");
    scanf("%d", &n);

    printf("Digite os %d números:\n", n);

    soma = 0;

    for (int i = 1; i <= n; i++) {
        printf("Número %d: ", i);
        scanf("%d", &numero);

        if (i % 2 == 1) {
            soma += numero;
        } else {
            soma -= numero;
        }
    }

    printf("A soma das sequências é: %d\n", soma);

    return 0;
}