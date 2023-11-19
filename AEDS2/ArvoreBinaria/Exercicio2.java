import java.util.Random;

public class Exercicio2 {
    public static void main(String[] args) throws Exception{
        Arvore a = new Arvore();
        

        Random gerador = new Random();
        gerador.setSeed(0);

        for(int i = 1; i <= 10000; i++) {
            int valor;
            do {
                valor = Math.abs(gerador.nextInt());
            }while(a.pesquisar(valor) == true);

            a.inserir(valor);
            double log2 = (Math.log(i)) / (Math.log(2));
            System.out.println("Numeros de elementos = " + i + "  Logaritmo = " + log2 + "  Altura =" + a.getAltura());
            
        }

        
    }
}
