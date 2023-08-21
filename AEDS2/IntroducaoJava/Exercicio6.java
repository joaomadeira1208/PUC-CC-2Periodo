import mypackage.MyIO;

public class Exercicio6 {
    public static void main(String[] args) {
        double Salario_Bruto = MyIO.readDouble("Digite o seu salario bruto: ");
        double Prestacao = MyIO.readDouble("Digite o valor da prestacao: ");
    
        if(Salario_Bruto * 0.40 >= Prestacao) {
            MyIO.println("Emprestimo Aprovado!");
        }
        else {
            MyIO.println("Infelizmente, seu emprestimo foi negado.");
        }
    }
}
