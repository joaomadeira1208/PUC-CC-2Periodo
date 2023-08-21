
public class Exercicio3 {
    class Ponto {
        private double x;
        private double y;
        private int id;
        private static int nextID = 0;
    
        public Ponto() {
            this(0, 0);
        }
    
        public Ponto(double x, double y) {
            this.x = x;
            this.y = y;
            this.id = nextID;
            nextID++;
        }
    
        public double getX() {
            return x;
        }
    
        public void setX(double x) {
            this.x = x;
        }
    
        public double getY() {
            return y;
        }
    
        public void setY(double y) {
            this.y = y;
        }
    
        public int getID() {
            return id;
        }
    
        public static int getNextID() {
            return nextID;
        }
    
        public double dist(Ponto other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    
        public double dist(double x, double y) {
            double dx = this.x - x;
            double dy = this.y - y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    
        public static double dist(double x1, double y1, double x2, double y2) {
            double dx = x1 - x2;
            double dy = y1 - y2;
            return Math.sqrt(dx * dx + dy * dy);
        }
    
        public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3) {
            double d1 = p1.dist(p2);
            double d2 = p1.dist(p3);
            double d3 = p2.dist(p3);
            return (d1 + d2 > d3) && (d1 + d3 > d2) && (d2 + d3 > d1);
        }
    
        public double getAreaRetangulo(Ponto other) {
            double base = Math.abs(this.x - other.x);
            double altura = Math.abs(this.y - other.y);
            return base * altura;
        }
    }
}
