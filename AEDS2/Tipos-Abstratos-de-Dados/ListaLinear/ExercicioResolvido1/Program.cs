using System;
using System.Collections;

class Lista {
    private int[] array;
    private int contador;

    public Lista() {
        array = new int[6];
        contador = 0;
    }

    public Lista(int tamanhoArray) {
        array = new int[tamanhoArray];
        contador = 0;
    }

    public void InserirInicio(int valor) {
        if(contador >= array.Length) {
            Console.WriteLine("Erro ao inserir");
            Environment.Exit(0);
        }

        for(int i = contador; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = valor;
        contador++;    
    }

    public void InserirFim(int valor) {
        if(contador >= array.Length) {
            Console.WriteLine("Erro ao inserir");
            Environment.Exit(0);
        }

        array[contador] = valor;
        contador++;
    }

    public void Inserir(int valor, int posicao) {
        if(contador >= array.Length) {
            Console.WriteLine("Erro ao inserir");
            Environment.Exit(0);
        }

        for(int i = contador; i > posicao; i--) {
            array[i] = array[i - 1];
        }

        array[posicao] = valor;
        contador++;
    }

    public int RemoverInicio() {
        if(contador ==  0) {
            Console.WriteLine("Erro ao remover");
            Environment.Exit(0);
        }
        
        int elementoRemovido = array[0];
        for(int i = 0; i < contador - 1 ; i++) {
            array[i] = array[i+1];
        }
        contador--;
        return elementoRemovido;

    }

    public int RemoverFim() {
        if(contador ==  0) {
            Console.WriteLine("Erro ao remover");
            Environment.Exit(0);
        }
        int elementoRemovido = array[contador - 1];
        contador--;
        return elementoRemovido;
    }

    public int Remover(int posicao) {
        if(contador ==  0) {
            Console.WriteLine("Erro ao remover");
            Environment.Exit(0);
        }

        int elementoRemovido = array[posicao];
        for(int i = posicao; i < contador - 1; i++) {
            array[i] = array[i + 1]; 
        }
        contador--;
        return elementoRemovido;
    }

    public void Mostrar() {
        
        for(int i = 0; i < contador; i++) {
            Console.Write(array[i] + " ");
        }
        Console.WriteLine("");
    }


}

class Exercicio {
    public static void Main(string[] args) {
        Console.WriteLine("==== LISTA LINEAR ====");
        Lista lista = new Lista(6);
        int x1, x2, x3;
        lista.InserirInicio(1);
        lista.InserirFim(7);
        lista.InserirFim(9);
        lista.InserirInicio(3);
        lista.Inserir(8, 3);
        lista.Inserir(4, 2);
        lista.Mostrar();
        x1 = lista.RemoverInicio();
        x2 = lista.RemoverFim();
        x3 = lista.Remover(2);
        Console.WriteLine("Numeros removidos do array: " + x1 + ", " + x2 + ", " + x3);
        lista.Mostrar();
    }
}