#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <stdio.h>

// Função para verificar se string é um palindromo.
bool isPalindromo(char string[]) {
    bool resp = true;
    int tamanho = strlen(string); 
    for(int i = 0, j = tamanho - 1; i < tamanho/2; i++, j--) {
            if(string[i] != string[j]) {
                resp = false;
                i = tamanho; 
            }
    }
    return resp; 
}

// Main
int main() {
    char *string = (char*)malloc(250 * sizeof(char)); // Aloca memoria para entrada
    bool palindromo;
    while(strcmp(string, "FIM") != 0) {
        scanf(" %[^\n]", string);  
        palindromo = isPalindromo(string); 
        if(palindromo) {
            printf("SIM\n");
        }
        else if(strcmp(string, "FIM") != 0) {
            printf("NAO\n");
        }
    }
    return 0;
}