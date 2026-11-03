package prototipo.Servicio;

import META_INF.Conector_JBDC;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import prototipo.Entidad.Deuda;
import prototipo.Entidad.Gasto;
import prototipo.Entidad.Ingreso;

public class Analisis_Gastos {
    
    Deuda deuda = new Deuda();
    Ingreso ingreso = new Ingreso();
    Gasto gasto = new Gasto();
    
    Connection conn = Conector_JBDC.conectar();
    
    private Obtener_Listas gestor = new Obtener_Listas();

    public double calcularTotalIngresos() {

        List<Ingreso> ingresos = gestor.obtenerIngresos();

        double total = 0;

        for (Ingreso i : ingresos) {
            total += i.getMonto();
        }

        return total;
    }
    public double calcularTotalGastos() {

        List<Gasto> gastos = gestor.obtenerGastos();

        double total = 0;

        for (Gasto g : gastos) {
            total += g.getMonto();
        }

        return total;
    }
    public double calcularBalance() {

        double ingresos = calcularTotalIngresos();
        double gastos = calcularTotalGastos();

        return ingresos - gastos;
    }
    public double calcularPorcentajeAhorro() {

        double ingresos = calcularTotalIngresos();
        double balance = calcularBalance();

        if (ingresos <= 0) {
            return 0;
        }

        return (balance / ingresos) * 100;
    }
    public double calcularGastoPromedio() {

        List<Gasto> gastos = gestor.obtenerGastos();

        if (gastos.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Gasto g : gastos) {
            total += g.getMonto();
        }

        return total / gastos.size();
    }
    public Map<String, Double> obtenerGastosPorCategoria() {

        List<Gasto> gastos = gestor.obtenerGastos();

        Map<String, Double> resultado = new HashMap<>();

        for (Gasto g : gastos) {

            String categoria = g.getCategoria();

            double monto = g.getMonto();

            resultado.put(
                categoria,
                resultado.getOrDefault(categoria, 0.0) + monto
            );
        }

        return resultado;
    }
    public Map<String, Double> obtenerGastosPorMedioPago() {

        List<Gasto> gastos = gestor.obtenerGastos();

        Map<String, Double> resultado = new HashMap<>();

        for (Gasto g : gastos) {

            String medio = g.getMedio_Pago();

            double monto = g.getMonto();

            resultado.put(
                medio,
                resultado.getOrDefault(medio, 0.0) + monto
            );
        }

        return resultado;
    }
    public Map<YearMonth, Double> obtenerGastosPorMes() {

        List<Gasto> gastos = gestor.obtenerGastos();

        Map<YearMonth, Double> resultado = new TreeMap<>();

        for (Gasto g : gastos) {

            if (g.getFecha() == null) {
                continue;
            }

            LocalDate fecha = g.getFecha().toLocalDate();

            YearMonth mes = YearMonth.from(fecha);

            resultado.put(
                mes,
                resultado.getOrDefault(mes, 0.0) + g.getMonto()
            );
        }

        return resultado;
    }
    public double calcularVariacionMensual() {

        Map<YearMonth, Double> gastos = obtenerGastosPorMes();

        if (gastos.size() < 2) {
            return 0;
        }

        Double[] valores = gastos.values().toArray(new Double[0]);

        double mesAnterior = valores[valores.length - 2];

        double mesActual = valores[valores.length - 1];

        if (mesAnterior == 0) {
            return 0;
        }

        return ((mesActual - mesAnterior) / mesAnterior) * 100;
    }
    
}
