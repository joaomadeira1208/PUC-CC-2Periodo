#include <stdlib.h>
#include <stdio.h>

int main(void) {
    FILE *arquivo;
    arquivo = fopen("arquivoC.txt", "w");
    int numeroValores;
    scanf("%d", &numeroValores);
    
    // Loop para ler os valores do usuário e imprimir no arquivo.
    for(int i = 0; i < numeroValores; i++) {
        double real; 
        scanf("%lf", &real);
        fwrite(&real, sizeof(double), 1, arquivo); // Escreve 1 valor real no arquivo
    }
    long posicaoAtual = pl(arquivo); // Obtém a posição atual no arquivo
    fclose(arquivo);

    arquivo = fopen("arquivoC.txt", "r");
    
    // Loop para ler e imprimir os valores armazenados no arquivo em ordem inversa
    for(long i = posicaoAtual - sizeof(double); i >= 0; i-=sizeof(double)) {
        fseek(arquivo, i, SEEK_SET); // Move o ponteiro do arquivo para a posição 'i'
        double real;
        fread(&real, sizeof(double), 1, arquivo); // Le 1 valor real do arquivo
        printf("%g\n", real); 

    }
    fclose(arquivo);
    return 0;
}