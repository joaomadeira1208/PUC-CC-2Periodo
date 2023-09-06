#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

bool isPalindromo(char string[], int index) {
    bool resp = true;
    if(index < strlen(string)) {
        if(string[index] != string[strlen(string) - 1 - index]) {
            resp = false;
        }
        else {
            index++;
            resp = isPalindromo(string, index);
        }
    }
    return resp;
}

int main() {
    char *string = (char*)malloc( 500 * sizeof(char));
    int index = 0;
    while(strcmp(string, "FIM") != 0) {
        scanf(" %[^\n]", string);
        if(isPalindromo(string, index)) {
            printf("SIM\n");
        }
        else if(strcmp(string, "FIM") != 0) {
            printf("NAO\n");
        }
    }
    return 0;
}