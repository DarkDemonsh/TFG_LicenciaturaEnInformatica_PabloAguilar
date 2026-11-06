package prototipo.Servicio;

public class ModeloSOM {

    private final RedNeuronalSOM som;

    public ModeloSOM(RedNeuronalSOM som) {

        if (som == null) {
            throw new IllegalArgumentException(
                    "La red neuronal no puede ser nula."
            );
        }

        this.som = som;
    }

    private void validarDatos(double[][] datos) {

        if (datos == null || datos.length == 0) {

            throw new IllegalArgumentException(
                    "No existen datos para evaluar."
            );
        }

        for (double[] vector : datos) {

            if (vector == null
                    || vector.length != som.getDimensiones()) {

                throw new IllegalArgumentException(
                        "Las dimensiones de los datos no coinciden "
                        + "con las de la red neuronal."
                );
            }

            for (double valor : vector) {

                if (!Double.isFinite(valor)) {

                    throw new IllegalArgumentException(
                            "Los datos contienen valores inválidos."
                    );
                }
            }
        }
    }

    public double calcularErrorCuantizacion(double[][] datos) {

        validarDatos(datos);

        return som.calcularErrorCuantizacion(datos);
    }

    private double calcularDistancia(double[] a, double[] b) {

        double suma = 0;

        for (int i = 0; i < a.length; i++) {

            double diferencia = a[i] - b[i];

            suma += diferencia * diferencia;
        }

        return Math.sqrt(suma);
    }

    private boolean sonVecinas(int[] a, int[] b) {

        int diferenciaFila = Math.abs(a[0] - b[0]);
        int diferenciaColumna = Math.abs(a[1] - b[1]);

        return diferenciaFila <= 1
                && diferenciaColumna <= 1
                && (diferenciaFila + diferenciaColumna > 0);
    }

    public double calcularErrorTopografico(double[][] datos) {

        validarDatos(datos);

        int errores = 0;

        for (double[] dato : datos) {

            int[] primera = null;
            int[] segunda = null;

            double menorDistancia = Double.MAX_VALUE;
            double segundaDistancia = Double.MAX_VALUE;

            for (int fila = 0;
                    fila < som.getFilas();
                    fila++) {

                for (int columna = 0;
                        columna < som.getColumnas();
                        columna++) {

                    double[] pesos = som.obtenerPesos(
                            fila, columna
                    );

                    double distancia
                            = calcularDistancia(dato, pesos);

                    if (distancia < menorDistancia) {

                        segundaDistancia = menorDistancia;
                        segunda = primera;

                        menorDistancia = distancia;
                        primera = new int[]{fila, columna};

                    } else if (distancia < segundaDistancia) {

                        segundaDistancia = distancia;
                        segunda = new int[]{fila, columna};
                    }
                }
            }

            if (segunda != null
                    && !sonVecinas(primera, segunda)) {

                errores++;
            }
        }

        return (double) errores / datos.length;
    }

    public void mostrarEvaluacion(double[][] datos) {

        double errorCuantizacion
                = calcularErrorCuantizacion(datos);

        double errorTopografico
                = calcularErrorTopografico(datos);

        System.out.println(
                "===== EVALUACIÓN DEL MODELO SOM ====="
        );

        System.out.println(
                "Error de cuantización: "
                + errorCuantizacion
        );

        System.out.println(
                "Error topográfico: "
                + errorTopografico
        );

        System.out.println(
                "Error topográfico porcentual: "
                + (errorTopografico * 100) + "%"
        );
    }

}
