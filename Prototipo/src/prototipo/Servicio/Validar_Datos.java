package prototipo.Servicio;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Validar_Datos {

    public List<String> validarMonto(String texto, String nombreCampo) {

        List<String> errores = new ArrayList<>();

        if (texto == null || texto.trim().isEmpty()) {
            errores.add("El campo " + nombreCampo + " es obligatorio.");
            return errores;
        }

        try {
            BigDecimal monto = new BigDecimal(
                    texto.trim().replace(",", ".")
            );

            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                errores.add(
                        "El campo " + nombreCampo + " debe ser mayor que cero."
                );
            }

            if (monto.scale() > 2) {
                errores.add(
                        "El campo " + nombreCampo
                        + " no puede tener más de dos decimales."
                );
            }

        } catch (NumberFormatException e) {
            errores.add(
                    "El campo " + nombreCampo + " debe contener un número válido."
            );
        }

        return errores;
    }

    public List<String> validarIngreso(String monto, Date fecha, String tipo, String periodicidad) {

        List<String> errores = new ArrayList<>();

        errores.addAll(validarMonto(monto, "monto del ingreso"));

        if (fecha == null) {
            errores.add("Debe seleccionar una fecha para el ingreso.");
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            errores.add("Debe especificar el tipo de ingreso.");
        }

        if (periodicidad == null || periodicidad.trim().isEmpty()) {
            errores.add("Debe especificar la periodicidad del ingreso.");
        }

        return errores;
    }

    public List<String> validarGasto(String monto, Date fecha, String categoria, String medioPago) {

        List<String> errores = new ArrayList<>();

        errores.addAll(validarMonto(monto, "monto del gasto"));

        if (fecha == null) {
            errores.add("Debe seleccionar una fecha para el gasto.");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            errores.add("Debe seleccionar una categoría.");
        }

        if (medioPago == null || medioPago.trim().isEmpty()) {
            errores.add("Debe seleccionar un medio de pago.");
        }

        return errores;
    }

    public List<String> validarDeuda(String montoInicial, String cantidadCuotas, Date vencimiento) {

        List<String> errores = new ArrayList<>();

        errores.addAll(
                validarMonto(montoInicial, "monto inicial de la deuda")
        );

        if (cantidadCuotas == null
                || cantidadCuotas.trim().isEmpty()) {

            errores.add("Debe ingresar la cantidad de cuotas.");

        } else {

            try {
                int cuotas = Integer.parseInt(cantidadCuotas.trim());

                if (cuotas <= 0) {
                    errores.add(
                            "La cantidad de cuotas debe ser mayor que cero."
                    );
                }

            } catch (NumberFormatException e) {
                errores.add(
                        "La cantidad de cuotas debe ser un número entero."
                );
            }
        }

        if (vencimiento == null) {
            errores.add("Debe seleccionar una fecha de vencimiento.");
        }

        return errores;
    }

    public boolean esValido(List<String> errores) {
        return errores == null || errores.isEmpty();
    }

    public String obtenerMensajeErrores(List<String> errores) {

        if (esValido(errores)) {
            return "Los datos son válidos.";
        }

        StringBuilder mensaje = new StringBuilder();

        mensaje.append("Se encontraron los siguientes errores:\n\n");

        for (String error : errores) {
            mensaje.append("- ").append(error).append("\n");
        }

        return mensaje.toString();
    }

}
