package prototipo.Servicio;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import prototipo.Entidad.Ingreso;
import prototipo.Entidad.Gasto;

public class DatosSOM {

    private final Obtener_Listas gestor;

    private static final int CANTIDAD_CARACTERISTICAS = 5;

    public DatosSOM() {
        this(new Obtener_Listas());
    }

    DatosSOM(Obtener_Listas gestorDatos) {
        if (gestorDatos == null) {
            throw new IllegalArgumentException();
        }
        this.gestor = gestorDatos;
    }

    private static class ResumenMes {

        double ingresos;
        double gastos;
        double gastosEsenciales;
        double gastosDiscrecionales;

    }

    public Map<YearMonth, double[]> prepararDatos() {

        Map<YearMonth, ResumenMes> resumenes = new TreeMap<>();

        List<Ingreso> ingresos = gestor.obtenerIngresos();
        List<Gasto> gastos = gestor.obtenerGastos();
        
        for (Ingreso ingreso : ingresos) {

            if (ingreso.getFecha() == null) {
                continue;
            }

            YearMonth mes = YearMonth.from(
                    ingreso.getFecha().toLocalDate()
            );

            ResumenMes resumen = resumenes.computeIfAbsent(
                    mes, k -> new ResumenMes()
            );

            resumen.ingresos += ingreso.getMonto();
        }

        for (Gasto gasto : gastos) {

            if (gasto.getFecha() == null) {
                continue;
            }

            YearMonth mes = YearMonth.from(
                    gasto.getFecha().toLocalDate()
            );

            ResumenMes resumen = resumenes.computeIfAbsent(
                    mes, k -> new ResumenMes()
            );

            String categoria = gasto.getCategoria() == null
                    ? ""
                    : gasto.getCategoria().trim().toLowerCase();

            if (categoria.equals("retiro de efectivo")) {
                continue;
            }

            double monto = gasto.getMonto();

            resumen.gastos += monto;

            if (esencial(categoria)) {

                resumen.gastosEsenciales += monto;

            } else if (discrecional(categoria)) {

                resumen.gastosDiscrecionales += monto;
            }
        }

        Map<YearMonth, double[]> datos = new TreeMap<>();

        double gastoAnterior = -1;

        for (Map.Entry<YearMonth, ResumenMes> entrada
                : resumenes.entrySet()) {

            ResumenMes mes = entrada.getValue();

            double[] vector = new double[CANTIDAD_CARACTERISTICAS];

            double proporcionGastos = 0;

            if (mes.ingresos > 0) {
                proporcionGastos = mes.gastos / mes.ingresos;
            }

            vector[0] = normalizarProporcion(proporcionGastos, 2);

            if (mes.gastos > 0) {
                vector[1] = mes.gastosEsenciales / mes.gastos;
            }

            if (mes.gastos > 0) {
                vector[2] = mes.gastosDiscrecionales / mes.gastos;
            }

            double ahorro = 0;

            if (mes.ingresos > 0) {
                ahorro = (mes.ingresos - mes.gastos)
                        / mes.ingresos;
            }

            vector[3] = normalizarRango(ahorro, -1, 1);

            if (gastoAnterior >= 0) {

                double variacion = 0;

                if (gastoAnterior > 0) {
                    variacion = (mes.gastos - gastoAnterior)
                            / gastoAnterior;
                }

                vector[4] = normalizarRango(
                        variacion, -1, 1
                );

            } else {

                vector[4] = 0.5;
            }

            datos.put(entrada.getKey(), vector);

            gastoAnterior = mes.gastos;
        }

        return datos;
    }

    private boolean esencial(String categoria) {

        return categoria.equals("supermercado")
                || categoria.equals("salud")
                || categoria.equals("hogar")
                || categoria.equals("transporte")
                || categoria.equals("educacion")
                || categoria.equals("educación");
    }

    private boolean discrecional(String categoria) {

        return categoria.equals("entretenimiento")
                || categoria.equals("otros")
                || categoria.equals("indumentaria");
    }

    private double normalizarProporcion(double valor, double maximo) {

        double resultado = valor / maximo;

        return Math.max(0, Math.min(1, resultado));
    }

    private double normalizarRango(double valor, double minimo, double maximo) {

        double resultado = (valor - minimo)
                / (maximo - minimo);

        return Math.max(0, Math.min(1, resultado));
    }

    public double[][] obtenerMatrizEntrenamiento() {

        Map<YearMonth, double[]> datos = prepararDatos();

        double[][] matriz = new double[datos.size()][CANTIDAD_CARACTERISTICAS];

        int fila = 0;

        for (double[] vector : datos.values()) {

            matriz[fila] = vector.clone();
            fila++;
        }

        return matriz;
    }

    public List<YearMonth> obtenerPeriodos() {

        return new ArrayList<>(prepararDatos().keySet());
    }
}
