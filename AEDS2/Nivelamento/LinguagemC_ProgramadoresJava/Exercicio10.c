#include <stdio.h>

void imprimirReverso(int n) {
    if (n <= 0) {
        printf("Nenhum número para mostrar.\n");
        return;
    }

    int numero;
    printf("Digite o 1º número: ");
    scanf("%d", &numero);

    for (int i = 1; i < n; i++) {
        int temp;
        printf("Digite o %dº número: ", i + 1);
        scanf("%d", &temp);

        // Mover o cursor do arquivo para o início
        fseek(stdin, 0, SEEK_SET);

        // Imprimir o número anterior antes do atual
        printf("Número atual: %d\n", numero);
        numero = temp;
    }

    // Imprimir o último número
    printf("Número atual: %d\n", numero);
}

int main() {
    int n;
    printf("Quantidade de números a serem inseridos: ");
    scanf("%d", &n);

    imprimirReverso(n);

    return 0;
}
