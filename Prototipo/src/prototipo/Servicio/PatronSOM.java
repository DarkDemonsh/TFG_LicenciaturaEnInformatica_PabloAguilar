package prototipo.Servicio;

public class PatronSOM {

    private final RedNeuronalSOM som;

    public PatronSOM(RedNeuronalSOM som) {
        if (som == null) {
            throw new IllegalArgumentException(
                    "La red neuronal no puede ser nula."
            );
        }

        this.som = som;
    }

    public String interpretar(double[] datos) {

        validarDatos(datos);

        double gastosSobreIngresos = datos[0];
        double gastosEsenciales = datos[1];
        double gastosDiscrecionales = datos[2];
        double ahorro = datos[3];
        double variacion = datos[4];

        if (ahorro < 0.5) {

            return "Desequilibrio financiero";
        }

        if (gastosSobreIngresos >= 0.4) {

            return "Consumo elevado";
        }

        if (gastosEsenciales >= 0.65) {

            return "Predominio de gastos esenciales";
        }

        if (gastosDiscrecionales >= 0.40) {

            return "Consumo discrecional elevado";
        }

        if (ahorro >= 0.70) {

            return "Buena capacidad de ahorro";
        }

        if (variacion >= 0.75) {

            return "Incremento importante del consumo";
        }

        return "Consumo equilibrado";
    }

    public String interpretarConSOM(double[] datos) {

        validarDatos(datos);

        int[] posicion = som.clasificar(datos);

        return interpretar(datos);
    }

    public String obtenerUbicacionNeurona(double[] datos) {

        validarDatos(datos);

        int[] posicion = som.clasificar(datos);

        return "Fila: " + posicion[0]
                + " | Columna: " + posicion[1];
    }

    public String interpretarNeurona(int fila, int columna) {

        double[] pesos = som.obtenerPesos(fila, columna);

        return interpretar(pesos);
    }

    private void validarDatos(double[] datos) {

        if (datos == null || datos.length != 5) {

            throw new IllegalArgumentException(
                    "Se necesitan exactamente 5 características."
            );
        }

        for (double valor : datos) {

            if (!Double.isFinite(valor)) {

                throw new IllegalArgumentException(
                        "Los datos contienen valores inválidos."
                );
            }

            if (valor < 0 || valor > 1) {

                throw new IllegalArgumentException(
                        "Los datos deben estar normalizados entre 0 y 1."
                );
            }
        }
    }
}
