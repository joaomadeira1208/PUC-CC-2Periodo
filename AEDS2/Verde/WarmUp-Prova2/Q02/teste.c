#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

int main() {
    char* str = (char*) malloc(250 * sizeof(char));
    char* stp = (char*) malloc(250 * sizeof(char));
    while(scanf(" %[^\n]", str) != EOF) {
        scanf(" %[^\n]", stp);
        printf("%s\n", str);
    }
}