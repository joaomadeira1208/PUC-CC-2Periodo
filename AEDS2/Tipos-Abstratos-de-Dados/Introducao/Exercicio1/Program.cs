using System;
using System.Collections;

class Fila {
    ArrayList arrayList;

    public Fila() {
        arrayList = new ArrayList();
    }

    public void Inserir(int elemento) {
        arrayList.Add(elemento);
    }

    public int Remover() {
        int elementoRemovido = (int) arrayList[0];
        arrayList.RemoveAt(0);
        return elementoRemovido;
    }

    public void Mostrar() {
        foreach(int i in arrayList) {
            Console.WriteLine(i);
        }
    }
}

class Exercicio1 {
    public static void Main(string[] args) {
        Fila fila = new Fila();
        for(int i = 0; i < 5; i++) {
            fila.Inserir(i);
        }
        fila.Mostrar();

        Console.WriteLine("Removendo: " + fila.Remover());
        fila.Inserir(50);
        Console.WriteLine("Adicionando: 50");
        fila.Mostrar();

    }
}