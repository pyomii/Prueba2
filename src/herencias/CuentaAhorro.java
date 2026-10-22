package herencias;

import java.util.Calendar;

public class CuentaAhorro extends CuentaBancaria {
    private Calendar ultimaModif;

    public CuentaAhorro(int cuenta, String cliente, String moneda){
        super(cuenta, cliente, 0.0);
        this.tipo = TipoCuenta.AHORRO;
        this.ultimaModif=Calendar.getInstance();
    }

    public boolean status(){
        Calendar fechaActual=Calendar.getInstance();
        fechaActual.add(Calendar.MONTH, -6);
        return ultimaModif.after(fechaActual);
    }

    @Override
    public void depositar(double monto) {
        if (!status()) {
            throw new IllegalStateException("La cuenta esta inactiva...");
        }
        super.depositar(monto);
        ultimaModif = Calendar.getInstance();
    }

    @Override
    public boolean retirar(double monto) {
        if (!status()) {
            throw new IllegalStateException("La cuenta esta inactiva...");
        }
        boolean exito = super.retirar(monto);
        if (exito) {
            ultimaModif = Calendar.getInstance();
        }
        return exito;
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaAhorro");
    }

    @Override
    public String toString() {
        return super.toString() + " | Ultimo uso: " + (ultimaModif != null ? ultimaModif.getTime() : "Sin uso");
    }
}
