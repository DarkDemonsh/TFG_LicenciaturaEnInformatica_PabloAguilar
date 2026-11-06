package prototipo.Servicio;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Gestor_Perfiles {

    private final RedNeuronalSOM som;

    private final Map<String, PerfilFinanciero> perfiles;

    public Gestor_Perfiles(RedNeuronalSOM som) {

        if (som == null) {
            throw new IllegalArgumentException("La red neuronal no puede ser nula.");
        }

        this.som = som;
        this.perfiles = new LinkedHashMap<>();
    }

    public static class PerfilFinanciero {

        private String identificador;
        private String nombre;

        private int fila;
        private int columna;

        private List<YearMonth> periodos;

        private double[] caracteristicasPromedio;

        public PerfilFinanciero(
                String identificador,
                int fila,
                int columna) {

            this.identificador = identificador;
            this.fila = fila;
            this.columna = columna;

            this.periodos = new ArrayList<>();

            this.caracteristicasPromedio = new double[5];
        }

        public String getIdentificador() {
            return identificador;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

        public List<YearMonth> getPeriodos() {
            return new ArrayList<>(periodos);
        }

        public double[] getCaracteristicasPromedio() {
            return caracteristicasPromedio.clone();
        }

        private void agregarPeriodo(YearMonth periodo) {
            periodos.add(periodo);
        }

        private void establecerPromedios(double[] promedios) {
            caracteristicasPromedio = promedios.clone();
        }
    }

    public void generarPerfiles(Map<YearMonth, double[]> datos) {

        if (datos == null || datos.isEmpty()) {
            throw new IllegalArgumentException(
                    "No existen datos para generar perfiles."
            );
        }

        perfiles.clear();

        for (Map.Entry<YearMonth, double[]> entrada
                : datos.entrySet()) {

            YearMonth periodo = entrada.getKey();
            double[] vector = entrada.getValue();

            if (periodo == null || vector == null
                    || vector.length != som.getDimensiones()) {

                throw new IllegalArgumentException("Existe un período con datos inválidos.");
            }

            int[] posicion = som.clasificar(vector);

            String id = posicion[0] + "-" + posicion[1];

            PerfilFinanciero perfil = perfiles.computeIfAbsent(
                    id,
                    k -> new PerfilFinanciero(
                            id,
                            posicion[0],
                            posicion[1]
                    )
            );

            perfil.agregarPeriodo(periodo);
        }

        for (PerfilFinanciero perfil : perfiles.values()) {
            double[] suma = new double[som.getDimensiones()];
            int cantidad = 0;

            for (YearMonth periodo : perfil.getPeriodos()) {

                double[] vector = datos.get(periodo);

                if (vector == null) {
                    continue;
                }

                for (int i = 0; i < vector.length; i++) {
                    suma[i] += vector[i];
                }

                cantidad++;
            }

            if (cantidad > 0) {

                for (int i = 0; i < suma.length; i++) {
                    suma[i] /= cantidad;
                }

                perfil.establecerPromedios(suma);
                perfil.setNombre(interpretarPerfil(suma));
            }
        }
    }

    public String interpretarPerfil(double[] datos) {

        double gastosSobreIngresos = datos[0];
        double gastosEsenciales = datos[1];
        double gastosDiscrecionales = datos[2];
        double ahorro = datos[3];

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

        return "Consumo equilibrado";
    }

    public List<PerfilFinanciero> obtenerPerfiles() {

        return new ArrayList<>(perfiles.values());
    }

    public PerfilFinanciero buscarPerfil(String id) {

        return perfiles.get(id);
    }

    public void mostrarPerfiles() {

        System.out.println(
                "===== PERFILES FINANCIEROS ====="
        );

        for (PerfilFinanciero perfil : perfiles.values()) {

            System.out.println("--------------------------------");

            System.out.println(
                    "Identificador: "
                    + perfil.getIdentificador()
            );

            System.out.println(
                    "Nombre: " + perfil.getNombre()
            );

            System.out.println(
                    "Neurona: ("
                    + perfil.getFila()
                    + ", "
                    + perfil.getColumna()
                    + ")"
            );

            System.out.println(
                    "Períodos asociados: "
                    + perfil.getPeriodos()
            );
        }
    }

}
