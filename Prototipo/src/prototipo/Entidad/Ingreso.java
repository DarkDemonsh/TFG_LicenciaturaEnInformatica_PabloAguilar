package prototipo.Entidad;

import java.sql.Date;

public class Ingreso {

    int ID;
    double monto;
    Date fecha;
    String tipo;
    String Descripcion;
    String Periocidad;

    public Ingreso() {
    }

    public Ingreso(int ID, double monto, Date fecha, String tipo, String Descripcion, String Periocidad) {
        this.ID = ID;
        this.monto = monto;
        this.fecha = fecha;
        this.tipo = tipo;
        this.Descripcion = Descripcion;
        this.Periocidad = Periocidad;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPeriocidad() {
        return Periocidad;
    }

    public void setPeriocidad(String Periocidad) {
        this.Periocidad = Periocidad;
    }

}
