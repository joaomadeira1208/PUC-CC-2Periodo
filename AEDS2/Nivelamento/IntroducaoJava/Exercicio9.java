import mypackage.MyIO;

public class Exercicio9 {
    public static void main(String[] args) {
        int i = 0;
        float soma = 0;
        while(i < 5) {
            i++;
            soma += MyIO.readFloat("Digite a nota do aluno: ");
        }
        float media = soma / 5;
        MyIO.println("A media da turma = " + media);
    }    
}
