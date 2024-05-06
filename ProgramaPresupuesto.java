import java.awt.*;
import java.util.Scanner;

import javax.swing.*;

class Actividad {
    String nombre;
    String area;
    double montoAsignado;
    double montoConsumido;
    String periodo;

    Actividad(String nombre, String area, double montoAsignado, String periodo) {
        this.nombre = nombre;
        this.area = area;
        this.montoAsignado = montoAsignado;
        this.montoConsumido = 0;
        this.periodo = periodo;
    }


    void registrarCosto(double costo) {
        montoConsumido += costo;
    }


    double porcentajeRestante() {
        return (1 - (montoConsumido / montoAsignado)) * 100;
    }


    void mostrarActividad() {
        System.out.printf("| %-15s | %-10s | %10.2f | %10.2f | %6.2f%% | %-12s |%n",
                nombre, area, montoAsignado, montoConsumido, porcentajeRestante(), periodo);
    }


    boolean generarAlerta() {
        return montoConsumido / montoAsignado >= 0.9;
    }
}

class Presupuesto {
    static final int MAX_ACTIVIDADES = 10;
    Actividad[] actividades;
    int numActividades;

    Presupuesto() {
        actividades = new Actividad[MAX_ACTIVIDADES];
        numActividades = 0;
    }


    void agregarActividad(Actividad actividad) {
        if (numActividades < MAX_ACTIVIDADES) {
            actividades[numActividades++] = actividad;
        } else {
            System.out.println("Número máximo de actividades alcanzado.");
        }
    }


    void registrarCosto(String nombreActividad, double costo) {
        for (int i = 0; i < numActividades; i++) {
            if (actividades[i].nombre.equals(nombreActividad)) {
                actividades[i].registrarCosto(costo);
                if (actividades[i].generarAlerta()) {
                    System.out.printf("¡Alerta! La actividad '%s' ha alcanzado o Superado el 90%% del presupuesto asignado.%n", nombreActividad);
                                        
                }
                return;
            }
        }
        System.out.println("Actividad no encontrada.");
    }


    void mostrarReporte() {
        System.out.printf("| %-15s | %-10s | %10s | %10s | %6s | %-12s |%n",
                "Nombre", "Área", "Asignado", "Consumido", "Restante", "Periodo");
        for (int i = 0; i < numActividades; i++) {
            actividades[i].mostrarActividad();
        }
    }
}

public class ProgramaPresupuesto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Presupuesto presupuesto = new Presupuesto();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Registrar nueva actividad");
            System.out.println("2. Registrar costo en una actividad");
            System.out.println("3. Mostrar reporte de actividades");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la actividad: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Área de la actividad: ");
                    String area = scanner.nextLine();
                    System.out.print("Monto asignado: ");
                    double montoAsignado = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Periodo (Primer/Segundo Semestre): ");
                    String periodo = scanner.nextLine();
                    Actividad nuevaActividad = new Actividad(nombre, area, montoAsignado, periodo);
                    presupuesto.agregarActividad(nuevaActividad);
                    break;

                case 2:
                    System.out.print("Nombre de la actividad a actualizar: ");
                    String nombreActividad = scanner.nextLine();
                    System.out.print("Monto consumido: ");
                    double costo = scanner.nextDouble();
                    presupuesto.registrarCosto(nombreActividad, costo);
                    break;

                case 3:
                    presupuesto.mostrarReporte();
                    break;

                case 4:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}
