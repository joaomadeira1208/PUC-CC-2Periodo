import java.io.*;
public class ArquivoJava {

    // Main
    public static void main(String[] args) throws Exception{
        RandomAccessFile raf = new RandomAccessFile("arquivoJava.txt","rw");
        int numeroValores = MyIO.readInt();
        
        // Loop para ler valores do usuário e escrevêlos no arquivo
        for(int i = 0; i < numeroValores; i++) {
            double real = MyIO.readDouble();
            raf.writeDouble(real);
        }
        long posicaoAtual = raf.getFilePointer(); // Obtêm a posição atual do ponteiro no arquivo, após a escrita de todos os valores.
        raf.close();
        raf = new RandomAccessFile("arquivoJava.txt", "r");
        
        // Loop para ler e imprimir os valores armazenados no arquivo em ordem inversa
        for(long i = posicaoAtual - 8; i >= 0; i-=8) {
            // Posiciona o ponteiro de leitura no arquivo na posição obtida após a escrita.
            raf.seek(i);
            double real = raf.readDouble();
            
            // Verifica se o valor real possui decimais, caso não imprime sua forma inteira. Caso sim, imprime sua forma real.
            if(real == (int) real) {
                MyIO.println((int) real);
            }
            else {
                MyIO.println(real);
            } 
        }
        raf.close();
        

    }
}
