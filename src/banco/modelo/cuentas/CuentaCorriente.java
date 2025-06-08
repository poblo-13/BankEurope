package banco.modelo.cuentas;

public class CuentaCorriente extends CuentaBancaria {

    public CuentaCorriente(double saldoInicial, String numeroCuenta) {
        super(saldoInicial, numeroCuenta);
    }

    @Override
    public double calcularInteres() {
        return 0;
    }
}
