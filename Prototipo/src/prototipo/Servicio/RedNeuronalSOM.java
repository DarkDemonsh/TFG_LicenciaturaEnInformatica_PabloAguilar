package prototipo.Servicio;

import java.util.Random;

public class RedNeuronalSOM {

    private int filas;
    private int columnas;
    private int dimensiones;

    private double[][][] pesos;

    private Random random;

    public RedNeuronalSOM(int filas, int columnas, int dimensiones) {

        if (filas <= 0 || columnas <= 0 || dimensiones <= 0) {
            throw new IllegalArgumentException(
                    "Las dimensiones del SOM deben ser positivas."
            );
        }

        this.filas = filas;
        this.columnas = columnas;
        this.dimensiones = dimensiones;

        this.pesos = new double[filas][columnas][dimensiones];

        this.random = new Random(42);

        inicializarPesos();
    }

    private void inicializarPesos() {

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                for (int k = 0; k < dimensiones; k++) {

                    pesos[i][j][k] = random.nextDouble();
                }
            }
        }
    }

    public void entrenar(double[][] datos, int epocas, double tasaAprendizaje, double radioInicial) {

        validarDatos(datos);

        if (epocas <= 0
                || tasaAprendizaje <= 0
                || tasaAprendizaje > 1
                || radioInicial <= 0) {

            throw new IllegalArgumentException(
                    "Parámetros de entrenamiento inválidos."
            );
        }

        for (int epoca = 0; epoca < epocas; epoca++) {

            double progreso = (double) epoca / epocas;

            double tasaActual
                    = tasaAprendizaje * (1.0 - progreso);

            double radioActual
                    = Math.max(0.5, radioInicial * (1.0 - progreso));

            for (double[] dato : datos) {

                int[] mejorNeurona = encontrarBMU(dato);

                actualizarPesos(
                        dato,
                        mejorNeurona[0],
                        mejorNeurona[1],
                        tasaActual,
                        radioActual
                );
            }
        }
    }

    private int[] encontrarBMU(double[] dato) {

        double menorDistancia = Double.MAX_VALUE;

        int mejorFila = 0;
        int mejorColumna = 0;

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                double distancia = distanciaEuclidiana(
                        dato,
                        pesos[i][j]
                );

                if (distancia < menorDistancia) {

                    menorDistancia = distancia;

                    mejorFila = i;
                    mejorColumna = j;
                }
            }
        }

        return new int[]{mejorFila, mejorColumna};
    }

    private double distanciaEuclidiana(double[] dato, double[] peso) {

        double suma = 0;

        for (int i = 0; i < dimensiones; i++) {

            double diferencia = dato[i] - peso[i];

            suma += diferencia * diferencia;
        }

        return Math.sqrt(suma);
    }

    private void actualizarPesos(double[] dato, int filaBMU, int columnaBMU, double tasa, double radio) {

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                double distanciaMapa
                        = distanciaMapa(
                                i,
                                j,
                                filaBMU,
                                columnaBMU
                        );

                if (distanciaMapa > radio) {
                    continue;
                }

                double vecindad = Math.exp(
                        -(distanciaMapa * distanciaMapa)
                        / (2 * radio * radio)
                );

                for (int k = 0; k < dimensiones; k++) {

                    pesos[i][j][k]
                            += tasa * vecindad
                            * (dato[k] - pesos[i][j][k]);
                }
            }
        }
    }

    private double distanciaMapa(int fila1, int columna1, int fila2, int columna2) {

        double df = fila1 - fila2;
        double dc = columna1 - columna2;

        return Math.sqrt(df * df + dc * dc);
    }

    public int[] clasificar(double[] dato) {

        validarDato(dato);

        return encontrarBMU(dato);
    }

    public double calcularErrorCuantizacion(double[][] datos) {

        validarDatos(datos);

        double error = 0;

        for (double[] dato : datos) {

            int[] bmu = encontrarBMU(dato);

            error += distanciaEuclidiana(
                    dato,
                    pesos[bmu[0]][bmu[1]]
            );
        }

        return error / datos.length;
    }

    private void validarDatos(double[][] datos) {

        if (datos == null || datos.length == 0) {

            throw new IllegalArgumentException(
                    "No hay datos para procesar."
            );
        }

        for (double[] dato : datos) {
            validarDato(dato);
        }
    }

    private void validarDato(double[] dato) {

        if (dato == null || dato.length != dimensiones) {

            throw new IllegalArgumentException(
                    "La cantidad de variables no coincide con el SOM."
            );
        }

        for (double valor : dato) {

            if (!Double.isFinite(valor)) {

                throw new IllegalArgumentException(
                        "Los datos deben ser valores numéricos finitos."
                );
            }
        }
    }

    public double[] obtenerPesos(int fila, int columna) {

        if (fila < 0 || fila >= filas
                || columna < 0 || columna >= columnas) {

            throw new IndexOutOfBoundsException(
                    "Neurona fuera de los límites del mapa."
            );
        }

        return pesos[fila][columna].clone();
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getDimensiones() {
        return dimensiones;
    }
}
