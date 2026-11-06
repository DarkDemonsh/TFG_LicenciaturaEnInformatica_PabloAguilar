package prototipo.Servicio;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import prototipo.Entidad.Ingreso;
import prototipo.Entidad.Gasto;

public class Proyector_Financiero {

    private final Obtener_Listas gestor;

    public Proyector_Financiero() {
        this(new Obtener_Listas());
    }

    Proyector_Financiero(Obtener_Listas gestorDatos) {
        if (gestorDatos == null) {
            throw new IllegalArgumentException();
        }
        this.gestor = gestorDatos;
    }

    public Map<YearMonth, Double> obtenerIngresosMensuales() {

        Map<YearMonth, Double> resultado = new TreeMap<>();

        List<Ingreso> ingresos = gestor.obtenerIngresos();

        for (Ingreso ingreso : ingresos) {

            if (ingreso.getFecha() == null) {
                continue;
            }

            YearMonth mes = YearMonth.from(
                    ingreso.getFecha().toLocalDate()
            );

            resultado.put(
                    mes,
                    resultado.getOrDefault(mes, 0.0)
                    + ingreso.getMonto()
            );
        }

        return resultado;
    }

    public Map<YearMonth, Double> obtenerGastosMensuales() {

        Map<YearMonth, Double> resultado = new TreeMap<>();

        List<Gasto> gastos = gestor.obtenerGastos();

        for (Gasto gasto : gastos) {

            if (gasto.getFecha() == null) {
                continue;
            }

            String categoria = gasto.getCategoria() == null
                    ? ""
                    : gasto.getCategoria().trim();

            if (categoria.equalsIgnoreCase("Retiro de Efectivo")) {
                continue;
            }

            YearMonth mes = YearMonth.from(
                    gasto.getFecha().toLocalDate()
            );

            resultado.put(
                    mes,
                    resultado.getOrDefault(mes, 0.0)
                    + gasto.getMonto()
            );
        }

        return resultado;
    }

    public double calcularMediaMovil(List<Double> valores, int cantidadPeriodos) {

        if (valores == null || valores.isEmpty()) {
            throw new IllegalArgumentException(
                    "No existen datos para realizar la proyección."
            );
        }

        if (cantidadPeriodos <= 0
                || cantidadPeriodos > valores.size()) {

            throw new IllegalArgumentException(
                    "La cantidad de períodos no es válida."
            );
        }

        double suma = 0;

        int inicio = valores.size() - cantidadPeriodos;

        for (int i = inicio; i < valores.size(); i++) {

            double valor = valores.get(i);

            if (!Double.isFinite(valor) || valor < 0) {
                throw new IllegalArgumentException(
                        "Los valores históricos deben ser válidos."
                );
            }

            suma += valor;
        }

        return suma / cantidadPeriodos;
    }

    public double proyectarIngresos(int cantidadPeriodos) {

        Map<YearMonth, Double> datos
                = obtenerIngresosMensuales();

        List<Double> valores
                = new ArrayList<>(datos.values());

        return calcularMediaMovil(
                valores,
                cantidadPeriodos
        );
    }

    public double proyectarGastos(int cantidadPeriodos) {

        Map<YearMonth, Double> datos
                = obtenerGastosMensuales();

        List<Double> valores
                = new ArrayList<>(datos.values());

        return calcularMediaMovil(
                valores,
                cantidadPeriodos
        );
    }

    public double proyectarBalance(int cantidadPeriodos) {

        double ingresos = proyectarIngresos(
                cantidadPeriodos
        );

        double gastos = proyectarGastos(
                cantidadPeriodos
        );

        return ingresos - gastos;
    }

    public double proyectarAhorroAcumulado(int cantidadPeriodos, int mesesFuturos) {

        if (mesesFuturos < 0) {
            throw new IllegalArgumentException(
                    "La cantidad de meses no puede ser negativa."
            );
        }

        double balance = proyectarBalance(
                cantidadPeriodos
        );

        return balance * mesesFuturos;
    }

}
