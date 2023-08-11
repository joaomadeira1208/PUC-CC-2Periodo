import java.io.*;
public class ExemploRAF03Cabecote {
    public static void main(String[] args) throws Exception{
        RandomAccessFile raf = new RandomAccessFile("exemplo3.txt", "rw");
        raf.writeInt(5);
        raf.writeDouble(3.14);
        raf.writeChar('X');
        raf.writeBoolean(false);
        raf.writeBytes("Rocket");

        raf.seek(0);
        System.out.println(raf.readInt());
        raf.seek(4);
        System.out.println(raf.readDouble());
        raf.seek(12);
        System.out.println(raf.readChar());
        raf.seek(12);
        System.out.println('s');
        raf.seek(12);
        System.out.println(raf.readChar());

        raf.close();
    }
}
