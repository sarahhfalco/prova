import java.util.function.Function;

public class Main {

    // Metodo di Piyavskii per trovare il minimo globale
    public static double piyavskiiMinimoGlobale(Function<Double, Double> f, double a, double b, double epsilon, double L) {
        double x1 = a;
        double x2 = b;

        while (Math.abs(x2 - x1) > epsilon) {
            double x3 = (x1 + x2) / 2;
            double f1 = f.apply(x1);
            double f2 = f.apply(x2);
            double f3 = f.apply(x3);

            if (f3 <= f1 && f3 <= f2) {
                x2 = x3;
            } else if (f1 <= f2) {
                x2 = x3;
                x1 = x1 - (f2 - f3) / L;
            } else {
                x1 = x3;
                x2 = x2 - (f1 - f3) / L;
            }
        }

        return (x1 + x2) / 2;
    }


    public static void main(String[] args) {
        // Esempio di utilizzo
        Function<Double, Double> quadraticFunction = x -> x *x - 4 * x + 4;
        double a = 0;
        double b = 5;
        double epsilon = 1e-8;
        double lipschitzConstant = 23; // Imposta il valore della costante di Lipschitz

        double minimoGlobale = piyavskiiMinimoGlobale(quadraticFunction, a, b, epsilon, lipschitzConstant);
        System.out.println("Il minimo globale della funzione è in x = " + minimoGlobale);
    }
}
