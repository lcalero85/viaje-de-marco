import java.util.Random;

public class ViajeDeMarco {
    static Random rand = new Random();

    // Variables globales
    static double distanciaEntreMarcoYMadre = 350.0; // Distancia inicial en Km
    static double velocidadMadre; // Velocidad diaria de la madre
    static double velocidadMarco; // Velocidad diaria de Marco
    static boolean marcoEncuentraMadre = false;

    public static void main(String[] args) {
        int dia = 1;
        double totalDistanciaMadre;
        double totalDistanciaMarco;

        System.out.println("DIARIO DEL VIAJE DE MARCO\n=========================");
        
        while (!marcoEncuentraMadre) {
            System.out.println("DIA " + dia);

            // Actualización de Marco
            double horasMarco = 8 + rand.nextDouble() * 2; // Entre 8 y 10 horas
            velocidadMarco = generarVelocidadMarco();
            totalDistanciaMarco = horasMarco * velocidadMarco;
            totalDistanciaMarco = ajustarPorClimaYMono(totalDistanciaMarco);

            System.out.printf("Avance %.2f horas a %.2f Km/h recorriendo %.2f Km\n",
                    horasMarco, velocidadMarco, totalDistanciaMarco);

            // Actualización de la Madre
            double horasMadre = 6 + rand.nextDouble() * 3; // Entre 6 y 9 horas
            velocidadMadre = generarVelocidadMadre();
            totalDistanciaMadre = horasMadre * velocidadMadre;
            totalDistanciaMadre = ajustarPorClimaMadre(totalDistanciaMadre);

            System.out.printf("Mama pudo avanzar %.2f horas a %.2f Km/h recorriendo %.2f Km\n",
                    horasMadre, velocidadMadre, totalDistanciaMadre);

            // Actualización de la distancia entre Marco y su madre
            distanciaEntreMarcoYMadre -= (totalDistanciaMarco + totalDistanciaMadre);
            System.out.printf("Al final del día %d la distancia entre Marco y su Madre es de %.2f Km\n",
                    dia, distanciaEntreMarcoYMadre);

            // Verificación de encuentro especial cuando distancia < 50 km
            if (distanciaEntreMarcoYMadre < 50 && rand.nextDouble() < 0.5) {
                System.out.println("A Marco le dicen que han visto a su mamá, y rompe a correr!!!");
                totalDistanciaMarco += 25; // Corre 25 Km adicionales
            }

            // Condición de encuentro
            if (distanciaEntreMarcoYMadre <= 0) {
                System.out.println("************************************************************");
                System.out.println("Al final del día " + dia + " Marco encuentra a su madre!!!");
                marcoEncuentraMadre = true;
            }
            dia++;
            System.out.println("----------------------------------------------------------");
        }
    }

    // Genera velocidad para Marco (10 a 15 km/h)
    private static double generarVelocidadMarco() {
        return 10 + rand.nextDouble() * 5;
    }

    // Genera velocidad para la Madre (6 a 9 km/h)
    private static double generarVelocidadMadre() {
        return 6 + rand.nextDouble() * 3;
    }

    // Ajuste por clima y comportamiento de Amedio para Marco
    private static double ajustarPorClimaYMono(double distancia) {
        double probabilidadClima = rand.nextDouble();
        double probabilidadMono = rand.nextDouble();

        if (probabilidadClima < 0.1) {
            System.out.println("Ha llovido muchísimo!");
            distancia *= 0.25;
        } else if (probabilidadClima < 0.4) {
            System.out.println("Ha llovido un poco");
            distancia *= 0.75;
        } else {
            System.out.println("Ha hecho un buen día");
        }

        if (probabilidadMono < 0.15) {
            System.out.println("Amedio se ha escapado!");
            distancia -= (2 * generarVelocidadMarco());
        } else if (probabilidadMono < 0.4) {
            System.out.println("Amedio se ha cansado y Marco tuvo que cargarlo!");
            distancia *= 0.9;
        }

        return distancia > 0 ? distancia : 0;
    }

    // Ajuste por clima para la Madre
    private static double ajustarPorClimaMadre(double distancia) {
        double probabilidadClima = rand.nextDouble();

        if (probabilidadClima < 0.1) {
            System.out.println("A mamá le ha llovido muchísimo");
            distancia *= 0.5;
        } else if (probabilidadClima < 0.4) {
            System.out.println("A mamá le ha llovido un poco");
            distancia *= 0.75;
        } else {
            System.out.println("A mamá le ha hecho un buen día");
        }
        return distancia > 0 ? distancia : 0;
    }
}
