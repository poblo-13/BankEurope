package banco.modelo.cuentas;

public class CuentaAhorros extends CuentaBancaria {

    private final double tasaInteres = 0.03; 

    public CuentaAhorros(double saldoInicial, String numeroCuenta) {
        super(saldoInicial, numeroCuenta);
    }

    @Override
    public double calcularInteres() {
        return saldo * tasaInteres;
    }
}
