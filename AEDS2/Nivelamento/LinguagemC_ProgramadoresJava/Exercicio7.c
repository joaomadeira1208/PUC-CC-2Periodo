// Definição da estrutura célula
struct Celula {
    int elemento;           // Atributo inteiro
    struct Celula *prox;    // Atributo apontador para outra célula
};

// Exemplo de uso:
int main() {
    struct Celula c1;  // Declaração de uma variável do tipo Celula chamada c1
    c1.elemento = 10;  // Atribui o valor 10 ao atributo elemento da célula c1

    struct Celula c2;  // Declaração de outra variável do tipo Celula chamada c2
    c2.elemento = 20;  // Atribui o valor 20 ao atributo elemento da célula c2

    c1.prox = &c2;     // Atribui o endereço da célula c2 ao atributo prox da célula c1

    // Agora c1.prox aponta para a célula c2

    return 0;
}
