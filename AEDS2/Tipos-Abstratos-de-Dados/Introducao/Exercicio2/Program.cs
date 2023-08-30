using System;
using System.Collections;

class Pilha {
    ArrayList arrayList;

    public Pilha() {
        arrayList = new ArrayList();
    }

    public void Inserir(int elemento) {
        arrayList.Add(elemento);
    }

    public int Remover() {
        int elementoRemovido = (int)arrayList[arrayList.Count - 1];
        arrayList.RemoveAt(arrayList.Count - 1);
        return elementoRemovido;
    }

    public void Mostrar() {
        for(int i = arrayList.Count - 1; i >= 0; i--) {
            Console.WriteLine(arrayList[i]);
        }
    }
}

class Exercicio2 {
    public static void Main(string[] args) {
        Pilha pilha = new Pilha();
        for(int i = 0; i < 10; i++) {
            pilha.Inserir(i);
        }
        pilha.Mostrar();

        Console.WriteLine("Elemento removido: " + pilha.Remover());

        pilha.Inserir(10);
        pilha.Mostrar();
    }
}
