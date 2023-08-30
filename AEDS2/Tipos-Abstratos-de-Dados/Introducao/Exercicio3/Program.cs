using System;
using System.Collections;

class Fila {
    Stack p1, p2;

    public Fila() {
        p1 = new Stack();
        p2 = new Stack();
    }

    public void Inserir(int elemento) {
        p1.Push(elemento);
    }

    public int Remover() {
        while(p1.Count > 1) {
            p2.Push(p1.Pop());
        }
        int elementoRemovido = (int) p1.Pop();
        while(p2.Count > 0) {
            p1.Push(p2.Pop());
        }
        return elementoRemovido;

    }

    public void Mostrar() {
        object[] array = p1.ToArray();
        for(int i = p1.Count - 1; i >= 0; i--) {
            Console.WriteLine(array[i]);
        }
    }
}

class Exercicio3 {
    public static void Main(string[] args) {
        Fila fila = new Fila();

        for(int i = 0; i < 10; i++) {
            fila.Inserir(i);
        }

        fila.Mostrar();

        Console.WriteLine("Elemento removido: " + fila.Remover());
        Console.WriteLine("Elemento adicionado: 50");
        fila.Inserir(50);
        fila.Mostrar();
    }
}