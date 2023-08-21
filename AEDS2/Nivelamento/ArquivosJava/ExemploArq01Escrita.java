import mypackage.Arq;

class ExemploArq01Escrita {
    public static void main (String[] args){
        Arq.openWrite("exemplo.txt");
        Arq.println(1);
        Arq.println(5.3);
        Arq.println('x');
        Arq.println(true);
        Arq.println("Algoritmos");
        Arq.close();
    }
}



