package prototipo.Entidad;

import java.time.LocalDate;

public class Deuda {
    int ID;
    double monto_inicial;
    double couota;
    LocalDate vencimiento;

    public Deuda() {
    }

    public Deuda(int ID, double monto_inicial, double couota, LocalDate vencimiento) {
        this.ID = ID;
        this.monto_inicial = monto_inicial;
        this.couota = couota;
        this.vencimiento = vencimiento;
    } 
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(double monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public double getCouota() {
        return couota;
    }

    public void setCouota(double couota) {
        this.couota = couota;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }
    
}
