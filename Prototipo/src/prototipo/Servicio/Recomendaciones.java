package prototipo.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import prototipo.Entidad.Gasto;

public class Recomendaciones {

    private final Obtener_Listas gestor;

    public Recomendaciones() {
        this(new Obtener_Listas());
    }

    Recomendaciones(Obtener_Listas gestorDatos) {
        if (gestorDatos == null) {
            throw new IllegalArgumentException();
        }
        this.gestor = gestorDatos;
    }

    public List<String> generarRecomendaciones(String perfil, double ingresos, double gastos, Map<String, Double> gastosPorCategoria) {

        List<String> recomendaciones = new ArrayList<>();

        if (perfil == null || gastosPorCategoria == null) {
            throw new IllegalArgumentException(
                    "Los datos recibidos no pueden ser nulos."
            );
        }

        double balance = ingresos - gastos;

        if (balance < 0) {

            recomendaciones.add(
                    "Tus gastos superan tus ingresos. "
                    + "Revisá tus gastos variables y compromisos "
                    + "antes de asumir nuevos gastos."
            );

        } else if (balance > 0) {

            recomendaciones.add(
                    "Tus ingresos superan tus gastos. "
                    + "Podés destinar parte del excedente "
                    + "a un objetivo de ahorro."
            );
        }

        switch (perfil) {

            case "Consumo elevado":

                recomendaciones.add(
                        "Tu nivel de consumo es elevado "
                        + "en relación con tus ingresos. "
                        + "Establecé límites mensuales por categoría."
                );

                break;

            case "Predominio de gastos esenciales":

                recomendaciones.add(
                        "La mayor parte de tus gastos corresponde "
                        + "a necesidades básicas. Revisá alternativas "
                        + "que permitan reducir costos sin afectar "
                        + "tu calidad de vida."
                );

                break;

            case "Consumo discrecional elevado":

                recomendaciones.add(
                        "Tus gastos discrecionales representan "
                        + "una proporción importante de tu consumo. "
                        + "Podés establecer un presupuesto específico "
                        + "para entretenimiento y compras personales."
                );

                break;

            case "Buena capacidad de ahorro":

                recomendaciones.add(
                        "Tu comportamiento financiero muestra "
                        + "una buena capacidad de ahorro. "
                        + "Considerá establecer objetivos financieros."
                );

                break;

            case "Desequilibrio financiero":

                recomendaciones.add(
                        "Necesitás revisar la relación entre "
                        + "tus ingresos y gastos. Priorizá los gastos "
                        + "necesarios y evitá nuevos compromisos "
                        + "que no puedas afrontar."
                );

                break;

            case "Incremento importante del consumo":

                recomendaciones.add(
                        "Tus gastos aumentaron considerablemente "
                        + "respecto al período anterior. "
                        + "Identificá qué categorías provocaron "
                        + "ese incremento."
                );

                break;

            default:

                recomendaciones.add(
                        "Mantené un seguimiento periódico "
                        + "de tus ingresos y gastos para detectar "
                        + "posibles cambios en tus hábitos."
                );
        }

        analizarCategorias(
                gastosPorCategoria,
                recomendaciones
        );

        return recomendaciones;
    }

    private void analizarCategorias(Map<String, Double> categorias, List<String> recomendaciones) {

        double mayorGasto = 0;
        String categoriaMayor = "";

        for (Map.Entry<String, Double> entrada
                : categorias.entrySet()) {

            String categoria = entrada.getKey();
            double monto = entrada.getValue();

            if (monto > mayorGasto) {

                mayorGasto = monto;
                categoriaMayor = categoria;
            }
        }

        if (!categoriaMayor.isEmpty()) {

            recomendaciones.add(
                    "Tu categoría con mayor gasto es "
                    + categoriaMayor
                    + ". Revisá sus movimientos para identificar "
                    + "posibles oportunidades de ahorro."
            );
        }

        for (Map.Entry<String, Double> entrada
                : categorias.entrySet()) {

            String categoria = entrada.getKey().toLowerCase();
            double monto = entrada.getValue();

            if (categoria.equals("entretenimiento")
                    && monto > 0) {

                recomendaciones.add(
                        "Revisá tus gastos de entretenimiento. "
                        + "Podés establecer un límite mensual "
                        + "y buscar alternativas más económicas."
                );
            }

            if (categoria.equals("indumentaria")
                    && monto > 0) {

                recomendaciones.add(
                        "Podés planificar tus compras de indumentaria "
                        + "y establecer un presupuesto mensual."
                );
            }
        }
    }

    public double calcularAhorroPotencial(double gastoCategoria, double porcentajeReduccion) {

        if (gastoCategoria < 0) {
            throw new IllegalArgumentException(
                    "El gasto no puede ser negativo."
            );
        }

        if (porcentajeReduccion < 0
                || porcentajeReduccion > 100) {

            throw new IllegalArgumentException(
                    "El porcentaje debe estar entre 0 y 100."
            );
        }

        return gastoCategoria * porcentajeReduccion / 100.0;
    }

    public Map<String, Double> obtenerGastosPorCategoria() {

        List<Gasto> gastos = gestor.obtenerGastos();

        Map<String, Double> resultado
                = new java.util.HashMap<>();

        for (Gasto gasto : gastos) {

            String categoria = gasto.getCategoria();

            if (categoria == null) {
                categoria = "Sin categoría";
            }

            resultado.put(
                    categoria,
                    resultado.getOrDefault(categoria, 0.0)
                    + gasto.getMonto()
            );
        }

        return resultado;
    }
}
