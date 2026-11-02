package prototipo.Entidad;

import java.sql.Date;

public class Gasto {
    int ID;
    double monto;
    Date fecha;
    String Categoria;
    String Medio_Pago;
    String Descripcion;

    public Gasto() {
    }

    public Gasto(int ID, double monto, Date fecha, String Categoria, String Medio_Pago, String Descripcion) {
        this.ID = ID;
        this.monto = monto;
        this.fecha = fecha;
        this.Categoria = Categoria;
        this.Medio_Pago = Medio_Pago;
        this.Descripcion = Descripcion;
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

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getMedio_Pago() {
        return Medio_Pago;
    }

    public void setMedio_Pago(String Medio_Pago) {
        this.Medio_Pago = Medio_Pago;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}
