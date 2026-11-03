package prototipo.Entidad;

import java.sql.Date;

public class Deuda {
    int ID;
    double monto_inicial;
    int couota;
    Date vencimiento;

    public Deuda() {
    }

    public Deuda(int ID, double monto_inicial, int couota, Date vencimiento) {
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

    public int getCouota() {
        return couota;
    }

    public void setCouota(int couota) {
        this.couota = couota;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    
}
