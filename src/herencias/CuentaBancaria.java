package herencias;

import java.util.ArrayList;
import java.util.Calendar;

public class CuentaBancaria {
    //Atributos
    protected int numero;
    protected String cliente;
    protected double saldo;
    protected double tasa;
    protected Calendar apertura;
    
    protected String moneda;
    protected TipoCuenta tipo;
    protected String[] historial = new String[50];
    protected int cantiMovimientos = 0;

    public CuentaBancaria(int numero, String cliente, double tasa) {
        this.numero = numero;
        this.cliente = cliente;
        this.moneda = moneda;
        this.tasa = tasa;
        this.saldo = 0;
        this.apertura = Calendar.getInstance();
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public double getTasa() {
        return tasa;
    }

    public Calendar getApertura() {
        return apertura;
    }

    public String getMoneda() {
        return moneda;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public void depositar(double monto){
        try{
            if(monto<=0){
                System.out.println("Insuficiente saldo");
            }else{
                saldo+=monto;
                guardarHistorial("Cantidad depositada: $" +monto+ " saldo: " + saldo);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean retirar(double monto){
        if(monto>0 && saldo>=monto){
            saldo-=monto;
            guardarHistorial("Cantidad retirada: -$ " +monto);
            return true;
        }
        guardarHistorial("Intento de retiro fallido: " +monto);
        return false;
    }

    public String toString(){
        return String.format("Cuenta #%d | %s | $%.2f",numero,cliente,saldo);
    }
    
    private void guardarHistorial(String linea) {
        try {
            if (cantiMovimientos >= historial.length) {
                throw new IllegalStateException("El historial de movimientos se ha llenado");
            }
            historial[cantiMovimientos] = linea;
            cantiMovimientos++;
        } catch (IllegalStateException e) {
            System.out.println("Alerta: " + e.getMessage());
        }
    }
    
    public void imprimirHistorial(){
        System.out.println("=== HISTORIAL DE MOVIMIENTOS (" + numero + ") ===");
    }
    
    public void registrarIntereses(){
        if (tipo!=null) {
            double ganancia = saldo * tipo.tasa();
            saldo+=ganancia;
        }
    }
    public void quienSoy() {
        System.out.println("CuentaBancaria(clase base)");
    }
}
