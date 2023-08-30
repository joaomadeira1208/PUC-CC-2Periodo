using System;
using System.Collections;

class Pilha {
    Queue f1, f2;

    public Pilha() {
        f1 = new Queue();
        f2 = new Queue();
    }

    public void Inserir(int elemento) {
        f1.Enqueue(elemento);
    }

    public int Remover() {
        while(f1.Count > 1) {
            f2.Enqueue(f1.Dequeue());
        }
        int elementoRemovido = (int) f1.Dequeue();
        while(f2.Count > 0) {
            f1.Enqueue(f2.Dequeue());
        }
        return elementoRemovido;
    }

    public void Mostrar() {
        object[] array = f1.ToArray();
        for(int i = f1.Count - 1; i >= 0; i--) {
            Console.WriteLine(array[i]);
        }
    }
}

class Exercicio4 {
    public static void Main(string[] args) {
        Pilha pilha = new Pilha();

        for(int i = 0; i < 10; i++) {
            pilha.Inserir(i);
        }

        pilha.Mostrar();

        Console.WriteLine("Elemento removido: " + pilha.Remover());
        Console.WriteLine("Elemento adicionado: 50");
        pilha.Inserir(50);
        pilha.Mostrar();
    }
}