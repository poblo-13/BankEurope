package banco.app;

import banco.modelo.clientes.Cliente;
import banco.modelo.cuentas.CuentaBancaria;
import banco.modelo.cuentas.CuentaCorriente;
import banco.modelo.cuentas.CuentaAhorros;
import banco.modelo.cuentas.CuentaDigital;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Cliente cliente1 = null;
        CuentaBancaria cuentaCliente = null;

        boolean otraOperacion = true;
        int opcionMenu;

        do {
            while (true) {
                System.out.println("=== Bienvenido a Bank Europe ===\n");
                System.out.println("1. Registrar Cliente.");
                System.out.println("2. Datos Cliente.");
                System.out.println("3. Depositar.");
                System.out.println("4. Girar.");
                System.out.println("5. Consultar Saldo.");
                System.out.println("6. Salir.\n");
                System.out.print("Seleccione una opción: ");

                try {
                    opcionMenu = teclado.nextInt();
                    break; 
                } catch (InputMismatchException e) {
                    System.out.println("\nError: Ingrese una opción numérica válida (1-6).\n");
                    teclado.nextLine(); 
                }
            }

            teclado.nextLine();

            switch (opcionMenu) {
                case 1: 
                    System.out.println("\n--- Registro de Cliente ---");

                    String nuevoRut;
                    while (true) {
                        System.out.print("RUT (con puntos y guion, ej. 12.345.678-9): ");
                        nuevoRut = teclado.nextLine();
                        String rutRegex = "^(\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk])$";
                        if (nuevoRut.matches(rutRegex)) {
                            break;
                        } else {
                            System.out.println("Formato de RUT inválido. Use el formato X.XXX.XXX-X o XX.XXX.XXX-X.");
                        }
                    }

                    System.out.print("Nombre: ");
                    String nuevoNombre = teclado.nextLine();

                    System.out.print("Apellido Paterno: ");
                    String nuevoPaterno = teclado.nextLine();

                    System.out.print("Apellido Materno: ");
                    String nuevoMaterno = teclado.nextLine();

                    System.out.print("Domicilio: ");
                    String nuevoDomicilio = teclado.nextLine();

                    System.out.print("Comuna: ");
                    String nuevaComuna = teclado.nextLine();

                    int nuevoTelefono;
                    while (true) {
                        System.out.print("Teléfono: ");
                        try {
                            nuevoTelefono = teclado.nextInt();
                            teclado.nextLine(); 
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Número de teléfono inválido. Intente nuevamente.");
                            teclado.nextLine();
                        }
                    }

                    int tipoCuenta;
                    while (true) {
                        System.out.println("\nSeleccione el tipo de cuenta que desea:");
                        System.out.println("1. Cuenta Corriente.");
                        System.out.println("2. Cuenta de Ahorro.");
                        System.out.println("3. Cuenta Digital.\n");
                        System.out.print("Seleccione una opción: ");

                        try {
                            tipoCuenta = teclado.nextInt();
                            teclado.nextLine();
                            if (tipoCuenta >= 1 && tipoCuenta <= 3) {
                                break;
                            } else {
                                System.out.println("Opción de tipo de cuenta inválida. Por favor, elija 1, 2 o 3.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingrese una opción numérica válida.");
                            teclado.nextLine();
                        }
                    }

                    String numeroCuenta;
                    while (true) {
                        System.out.print("Número de cuenta (9 dígitos): ");
                        numeroCuenta = teclado.nextLine();
                        if (numeroCuenta.matches("\\d{9}")) { 
                            break;
                        } else {
                            System.out.println("Error: el número de cuenta debe tener exactamente 9 dígitos numéricos.");
                        }
                    }

                    double saldoInicial = 0.0;

                    switch (tipoCuenta) {
                        case 1:
                            cuentaCliente = new CuentaCorriente(saldoInicial, numeroCuenta);
                            System.out.println("Cuenta Corriente creada.");
                            break;
                        case 2:
                            cuentaCliente = new CuentaAhorros(saldoInicial, numeroCuenta);
                            System.out.println("Cuenta de Ahorro creada.");
                            break;
                        case 3:
                            cuentaCliente = new CuentaDigital(saldoInicial, numeroCuenta);
                            System.out.println("Cuenta Digital creada.");
                            break;
                        default:
                            System.out.println("Opción de cuenta inválida. No se pudo crear la cuenta.");
                            cuentaCliente = null; 
                            break;
                    }

                    if (cuentaCliente != null) {
                        cliente1 = new Cliente(nuevoRut, nuevoNombre, nuevoPaterno, nuevoMaterno,
                                nuevoDomicilio, nuevaComuna, nuevoTelefono, cuentaCliente);
                        System.out.println("\nCliente registrado con éxito.");
                    }
                    break;

                case 2: 
                    System.out.println("\n--- Datos del Cliente ---");
                    if (cliente1 != null) {
                        cliente1.mostrarInformacionCliente();
                        if (cuentaCliente != null) {
                            cuentaCliente.infoCuentaCliente();
                        }
                    } else {
                        System.out.println("Aún no hay ningún cliente registrado. Por favor, registre uno primero (Opción 1).");
                    }
                    break;

                case 3: 
                    System.out.println("\n--- Depositar Dinero ---");
                    if (cuentaCliente != null) {
                        System.out.print("Ingrese monto a depositar: $");
                        try {
                            double montoDeposito = teclado.nextDouble();
                            cuentaCliente.depositar(montoDeposito);
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Monto inválido. Ingrese un valor numérico.");
                        }
                    } else {
                        System.out.println("No hay cuenta registrada para depositar. Por favor, registre un cliente con una cuenta (Opción 1).");
                    }
                    teclado.nextLine(); 
                    break;

                case 4:
                    System.out.println("\n--- Girar Dinero ---");
                    if (cuentaCliente != null) {
                        System.out.print("Ingrese monto a girar: $");
                        try {
                            double montoGiro = teclado.nextDouble();
                            cuentaCliente.girar(montoGiro); 
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Monto inválido. Ingrese un valor numérico.");
                        }
                    } else {
                        System.out.println("No hay cuenta registrada para girar. Por favor, registre un cliente con una cuenta (Opción 1).");
                    }
                    teclado.nextLine(); 
                    break;

                case 5:
                    System.out.println("\n--- Consultar Saldo ---");
                    if (cuentaCliente != null) {
                        cuentaCliente.aplicarInteresGanado(); 
                        System.out.println("Saldo actual de la cuenta " + cuentaCliente.getNumeroCuenta() + ": $" + String.format("%.2f", cuentaCliente.getSaldo()));
                    } else {
                        System.out.println("No hay cuenta registrada para consultar. Por favor, registre un cliente con una cuenta (Opción 1).");
                    }
                    break;

                case 6: 
                    System.out.println("\nGracias por preferir Bank Europe. ¡Hasta pronto!");
                    otraOperacion = false; 
                    break;

                default: 
                    System.out.println("\nOpción inválida. Por favor, ingrese un número entre 1 y 6.\n");
                    break;
            }

            if (opcionMenu != 6) {
                int continuar; 
                do {
                    System.out.println("\n¿Desea realizar otra operación?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    System.out.print("Seleccione: ");
                    try {
                        continuar = teclado.nextInt();
                        if (continuar == 1) {
                            otraOperacion = true;
                        } else if (continuar == 2) {
                            System.out.println("\nGracias por preferir Bank Europe. ¡Hasta pronto!");
                            otraOperacion = false;
                        } else {
                            System.out.println("Opción inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                            continuar = 0; 
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese una opción numérica válida (1 o 2).");
                        teclado.nextLine(); 
                        continuar = 0; 
                    }
                } while (continuar != 1 && continuar != 2);
                teclado.nextLine(); 
            }

        } while (otraOperacion); 

        teclado.close(); 
    }
}