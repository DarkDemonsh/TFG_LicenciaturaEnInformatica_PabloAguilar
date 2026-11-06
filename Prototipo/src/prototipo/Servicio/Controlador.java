package prototipo.Servicio;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Controlador {

    private Obtener_Listas gestorDatos;
    private Analisis_Gastos analisisGastos;
    private DatosSOM preparadorDatos;
    private RedNeuronalSOM redSOM;
    private Gestor_Perfiles gestorPerfiles;
    private Recomendaciones generadorRecomendaciones;
    private Simulador simulador;
    private Proyector_Financiero proyector;
    private Validar_Datos validador;

    private boolean modeloEntrenado = false;

    public Controlador() {

        gestorDatos = new Obtener_Listas();

        analisisGastos = new Analisis_Gastos();

        preparadorDatos = new DatosSOM(gestorDatos);

        redSOM = new RedNeuronalSOM(3, 3, 5);

        gestorPerfiles = new Gestor_Perfiles(redSOM);

        generadorRecomendaciones = new Recomendaciones(gestorDatos);

        simulador = new Simulador();

        proyector = new Proyector_Financiero(gestorDatos);

        validador = new Validar_Datos();
    }

    public boolean entrenarModelo() {

        Map<YearMonth, double[]> datos
                = preparadorDatos.prepararDatos();

        if (datos == null || datos.size() < 6) {
            System.out.println(
                    "No hay suficientes datos históricos. "
                    + "Se necesitan al menos 6 meses registrados."
            );

            modeloEntrenado = false;
            return false;
        }

        double[][] matriz
                = preparadorDatos.obtenerMatrizEntrenamiento();

        if (matriz == null || matriz.length < 6) {
            modeloEntrenado = false;
            return false;
        }

        redSOM.entrenar(
                matriz,
                1000,
                0.5,
                1.5
        );

        gestorPerfiles.generarPerfiles(datos);

        modeloEntrenado = true;

        return true;
    }

    public String obtenerResumenFinanciero() {

        double ingresos
                = analisisGastos.calcularTotalIngresos();

        double gastos
                = analisisGastos.calcularTotalGastos();

        double balance
                = analisisGastos.calcularBalance();

        double ahorro
                = analisisGastos.calcularPorcentajeAhorro();

        StringBuilder resultado = new StringBuilder();

        resultado.append("RESUMEN FINANCIERO\n\n");

        resultado.append("Ingresos: $")
                .append(String.format("%.2f", ingresos))
                .append("\n");

        resultado.append("Gastos: $")
                .append(String.format("%.2f", gastos))
                .append("\n");

        resultado.append("Balance: $")
                .append(String.format("%.2f", balance))
                .append("\n");

        resultado.append("Porcentaje de ahorro: ")
                .append(String.format("%.2f", ahorro))
                .append("%\n");

        return resultado.toString();
    }

    public String obtenerPerfilesFinancieros() {

        if (!modeloEntrenado) {
            return "El modelo todavía no fue entrenado.";
        }

        StringBuilder resultado = new StringBuilder();

        resultado.append("PERFILES FINANCIEROS\n\n");

        for (Gestor_Perfiles.PerfilFinanciero perfil
                : gestorPerfiles.obtenerPerfiles()) {

            resultado.append("Perfil: ")
                    .append(perfil.getNombre())
                    .append("\n");

            resultado.append("Identificador: ")
                    .append(perfil.getIdentificador())
                    .append("\n");

            resultado.append("Neurona: ")
                    .append(perfil.getFila())
                    .append(", ")
                    .append(perfil.getColumna())
                    .append("\n");

            resultado.append("Meses asociados: ")
                    .append(perfil.getPeriodos())
                    .append("\n\n");
        }

        return resultado.toString();
    }

    public String analizarMes(YearMonth periodo) {

        if (periodo == null) {
            return "Debe especificar un período válido.";
        }

        Map<YearMonth, double[]> datos
                = preparadorDatos.prepararDatos();

        double[] caracteristicas = datos.get(periodo);

        if (caracteristicas == null) {
            return "No existen registros para el período seleccionado.";
        }

        if (!modeloEntrenado) {
            return "Primero debe entrenar el modelo financiero.";
        }

        int[] ubicacion = redSOM.clasificar(caracteristicas);

        String perfil = gestorPerfiles.interpretarPerfil(caracteristicas);

        StringBuilder resultado = new StringBuilder();

        resultado.append("ANÁLISIS FINANCIERO\n\n");

        resultado.append("Período: ")
                .append(periodo)
                .append("\n");

        resultado.append("Neurona asignada: ")
                .append(ubicacion[0])
                .append(", ")
                .append(ubicacion[1])
                .append("\n");

        resultado.append("Perfil detectado: ")
                .append(perfil)
                .append("\n");

        return resultado.toString();
    }

    public List<String> obtenerRecomendaciones(YearMonth periodo) {

        if (periodo == null) {
            return Collections.singletonList("Debe seleccionar un período válido.");
        }

        Map<YearMonth, double[]> datos
                = preparadorDatos.prepararDatos();

        double[] caracteristicas = datos.get(periodo);

        if (caracteristicas == null) {
            return Collections.singletonList("No hay datos para generar recomendaciones.");
        }

        String perfil = gestorPerfiles.interpretarPerfil(caracteristicas);

        double ingresos
                = analisisGastos.calcularTotalIngresos();

        double gastos
                = analisisGastos.calcularTotalGastos();

        Map<String, Double> categorias
                = analisisGastos.obtenerGastosPorCategoria();

        return generadorRecomendaciones.generarRecomendaciones(
                perfil,
                ingresos,
                gastos,
                categorias
        );
    }

    public String simularReduccionGastos(double ingresos, double gastos, double porcentaje) {

        try {

            Simulador.ResultadoSimulacion resultado = simulador.simularReduccionGastos(ingresos, gastos, porcentaje);

            return resultado.generarTexto();

        } catch (IllegalArgumentException e) {

            return "Error en la simulación: " + e.getMessage();
        }
    }

    public YearMonth obtenerUltimoPeriodo() {

        Map<YearMonth, double[]> datos
                = preparadorDatos.prepararDatos();

        if (datos == null || datos.isEmpty()) {
            return null;
        }

        return datos.keySet()
                .stream()
                .max(YearMonth::compareTo)
                .orElse(null);
    }

    public double proyectarIngresos(int meses) {

        return proyector.proyectarIngresos(meses);
    }

    public double proyectarGastos(int meses) {

        return proyector.proyectarGastos(meses);
    }

    public String evaluarModelo() {

        if (!modeloEntrenado) {
            return "El modelo todavía no fue entrenado.";
        }

        ModeloSOM validadorModelo = new ModeloSOM(redSOM);

        double[][] datos
                = preparadorDatos.obtenerMatrizEntrenamiento();

        double errorCuantizacion
                = validadorModelo.calcularErrorCuantizacion(datos);

        double errorTopografico
                = validadorModelo.calcularErrorTopografico(datos);

        StringBuilder resultado = new StringBuilder();

        resultado.append("EVALUACIÓN DEL MODELO SOM\n\n");

        resultado.append("Error de cuantización: ")
                .append(String.format("%.4f", errorCuantizacion))
                .append("\n");

        resultado.append("Error topográfico: ")
                .append(String.format("%.4f", errorTopografico))
                .append("\n");

        return resultado.toString();
    }

    public Validar_Datos obtenerValidador() {
        return validador;
    }

    public boolean estaModeloEntrenado() {
        return modeloEntrenado;
    }

    public double obtenerTotalIngresos() {
        return analisisGastos.calcularTotalIngresos();
    }

    public double obtenerTotalGastos() {
        return analisisGastos.calcularTotalGastos();
    }

}
