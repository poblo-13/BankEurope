package banco.modelo.cuentas;

public class CuentaDigital extends CuentaBancaria {

    private final double tasaInteres = 0.05;

    public CuentaDigital(double saldoInicial, String numeroCuenta) {
        super(saldoInicial, numeroCuenta);
    }

    @Override
    public double calcularInteres() {
        return saldo * tasaInteres;
    }
}
