public class Exercicio2 {
    class Retangulo {
        private double altura;
        private double base;

        public Retangulo() {
            altura = 0;
            base = 0;
        }

        public Retangulo(double alt, double b) {
            altura = alt;
            base = b;
        }

        public double get_area() {
            return base * altura;
        }

        public double get_perimetro() {
            return (base * 2) + (altura * 2);
        }

        public double get_diagonal() {
            return Math.sqrt((base * base) + (altura * altura));
        }
    }

    public class Lixao {
        public void main(String[] args) {
            Retangulo r1 = new Retangulo(10, 20);
            Retangulo r2 = new Retangulo(20, 30);

            double areaR1 = r1.get_area();
            double areaR2 = r2.get_area();
            double perimetroR1 = r1.get_perimetro();
            double perimetroR2 = r2.get_perimetro();
            double diagonalR1 = r1.get_diagonal();
            double diagonalR2 = r2.get_diagonal();

            System.out.println("Área do retângulo r1: " + areaR1);
            System.out.println("Área do retângulo r2: " + areaR2);
            System.out.println("Perímetro do retângulo r1: " + perimetroR1);
            System.out.println("Perímetro do retângulo r2: " + perimetroR2);
            System.out.println("Diagonal do retângulo r1: " + diagonalR1);
            System.out.println("Diagonal do retângulo r2: " + diagonalR2);
        }
    }
}
