package prototipo.Servicio;

public class Simulador {

    public static class ResultadoSimulacion {

        private double ingresos;
        private double gastosOriginales;
        private double gastosNuevos;
        private double balanceOriginal;
        private double balanceNuevo;
        private double ahorroGenerado;

        public ResultadoSimulacion(double ingresos, double gastosOriginales, double gastosNuevos) {

            this.ingresos = ingresos;
            this.gastosOriginales = gastosOriginales;
            this.gastosNuevos = gastosNuevos;

            this.balanceOriginal
                    = ingresos - gastosOriginales;

            this.balanceNuevo
                    = ingresos - gastosNuevos;

            this.ahorroGenerado
                    = gastosOriginales - gastosNuevos;
        }

        public double getIngresos() {
            return ingresos;
        }

        public double getGastosOriginales() {
            return gastosOriginales;
        }

        public double getGastosNuevos() {
            return gastosNuevos;
        }

        public double getBalanceOriginal() {
            return balanceOriginal;
        }

        public double getBalanceNuevo() {
            return balanceNuevo;
        }

        public double getAhorroGenerado() {
            return ahorroGenerado;
        }

        public String generarTexto() {

            StringBuilder resultado = new StringBuilder();

            resultado.append("RESULTADO DE LA SIMULACIÓN\n\n");

            resultado.append("Ingresos: $")
                    .append(String.format("%.2f", ingresos))
                    .append("\n");

            resultado.append("Gastos originales: $")
                    .append(String.format("%.2f", gastosOriginales))
                    .append("\n");

            resultado.append("Gastos nuevos: $")
                    .append(String.format("%.2f", gastosNuevos))
                    .append("\n");

            resultado.append("Balance original: $")
                    .append(String.format("%.2f", balanceOriginal))
                    .append("\n");

            resultado.append("Balance nuevo: $")
                    .append(String.format("%.2f", balanceNuevo))
                    .append("\n");

            resultado.append("Ahorro generado: $")
                    .append(String.format("%.2f", ahorroGenerado));

            return resultado.toString();
        }

        public double calcularAhorroAcumulado(double ahorroMensual, int cantidadMeses) {

            if (!Double.isFinite(ahorroMensual)
                    || ahorroMensual < 0
                    || cantidadMeses < 0) {

                throw new IllegalArgumentException(
                        "Los valores ingresados no son válidos."
                );
            }

            return ahorroMensual * cantidadMeses;
        }

        public int calcularMesesParaMeta(double meta, double ahorroMensual, double ahorroActual) {

            if (!Double.isFinite(meta)
                    || !Double.isFinite(ahorroMensual)
                    || !Double.isFinite(ahorroActual)
                    || meta < 0
                    || ahorroMensual <= 0
                    || ahorroActual < 0) {

                throw new IllegalArgumentException(
                        "Los valores de la meta no son válidos."
                );
            }

            if (ahorroActual >= meta) {
                return 0;
            }

            double faltante = meta - ahorroActual;

            return (int) Math.ceil(
                    faltante / ahorroMensual
            );
        }

        public double simularAumentoIngresos(double ingresos, double porcentajeAumento) {

            if (!Double.isFinite(ingresos)
                    || !Double.isFinite(porcentajeAumento)
                    || ingresos < 0
                    || porcentajeAumento < 0) {

                throw new IllegalArgumentException(
                        "Los valores ingresados no son válidos."
                );
            }

            return ingresos * (1 + porcentajeAumento / 100);
        }

    }

    public ResultadoSimulacion simularReduccionGastos(double ingresos, double gastos, double porcentaje) {

        validarImportes(ingresos, gastos);

        if (!Double.isNaN(porcentaje) || porcentaje < 0 || porcentaje > 100) {

            throw new IllegalArgumentException("La reducción debe estar entre 0 y 100.");

        }

        double nuevosGastos = gastos * (1 - porcentaje / 100.0);

        return new ResultadoSimulacion(ingresos, gastos, nuevosGastos);
    }

    private void validarImportes(double ingresos, double gastos) {

        if (!Double.isFinite(ingresos)
                || !Double.isFinite(gastos)
                || ingresos < 0
                || gastos < 0) {

            throw new IllegalArgumentException(
                    "Los importes deben ser valores válidos "
                    + "y no negativos."
            );
        }
    }

    public double simularNuevaCuota(double ingresos, double gastos, double nuevaCuota) {

        validarImportes(ingresos, gastos);

        if (!Double.isFinite(nuevaCuota)
                || nuevaCuota < 0) {

            throw new IllegalArgumentException(
                    "La cuota no puede ser negativa."
            );
        }

        return ingresos - gastos - nuevaCuota;
    }

}
