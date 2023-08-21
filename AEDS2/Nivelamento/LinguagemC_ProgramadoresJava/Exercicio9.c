#include <stdio.h>

int main() {
    int n;

    printf("Quantidade de números a serem inseridos: ");
    scanf("%d", &n);

    // Abrir o arquivo para escrita
    FILE *arquivo = fopen("numeros.txt", "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para escrita.\n");
        return 1;
    }

    // Ler e escrever os números no arquivo
    for (int i = 0; i < n; i++) {
        int numero;
        printf("Digite o %dº número: ", i + 1);
        scanf("%d", &numero);
        fprintf(arquivo, "%d\n", numero);
    }

    fclose(arquivo); // Fechar o arquivo

    // Abrir o arquivo para leitura
    arquivo = fopen("numeros.txt", "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return 1;
    }

    // Ler e mostrar os números do arquivo na tela
    int numeroLido;
    printf("\nNúmeros lidos do arquivo:\n");
    while (fscanf(arquivo, "%d", &numeroLido) != EOF) {
        printf("%d ", numeroLido);
    }
    printf("\n");

    fclose(arquivo); // Fechar o arquivo de leitura

    return 0;
}
