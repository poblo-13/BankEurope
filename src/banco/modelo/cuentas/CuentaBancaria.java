package banco.modelo.cuentas;

public abstract class CuentaBancaria {
    protected double saldo;
    protected String numeroCuenta;

    public CuentaBancaria(double saldoInicial, String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede ser nulo o vacío.");
        }
        if (!numeroCuenta.matches("\\d{9}")) {
            throw new IllegalArgumentException("El número de cuenta debe tener 9 dígitos.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.saldo = saldoInicial;
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito de $" + String.format("%.2f", monto) + " realizado con éxito.");
        } else {
            System.out.println("Monto inválido para depositar. Debe ser un valor positivo.");
        }
    }

    public boolean girar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido para girar. Debe ser un valor positivo.");
            return false;
        }
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Giro de $" + String.format("%.2f", monto) + " realizado con éxito. Nuevo saldo: $" + String.format("%.2f", saldo));
            return true;
        } else {
            System.out.println("No se puede girar ese monto: saldo insuficiente.");
            return false;
        }
    }

    public abstract double calcularInteres();

    public void aplicarInteresGanado() {
        double interes = calcularInteres();
        if (interes > 0) {
            this.saldo += interes;
            System.out.println("Interés de $" + String.format("%.2f", interes) + " aplicado al saldo.");
        } else {
            System.out.println("No se generaron intereses positivos para aplicar en esta cuenta.");
        }
    }

    public void infoCuentaCliente() {
        System.out.println("Número de Cuenta: " + numeroCuenta);
        System.out.println("Saldo Disponible: $" + String.format("%.2f", saldo));
        System.out.println("Interés que esta cuenta puede generar: $" + String.format("%.2f", calcularInteres()));
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
               "numeroCuenta='" + numeroCuenta + '\'' +
               ", saldo=" + String.format("%.2f", saldo) +
               '}';
    }
}