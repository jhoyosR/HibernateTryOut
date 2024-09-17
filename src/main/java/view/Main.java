package view;

import utilidades.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int opcion;

        do {
            String menu = "---- Menú CRUD Carreras ----\n" +
                    "1. Crear Carrera\n" +
                    "2. Editar Carrera\n" +
                    "3. Eliminar Carrera\n" +
                    "4. Listar Carrera por ID\n" +
                    "5. Listar Carreras con ID mayor que...\n" +
                    "6. Listar Carreras existentes\n" +
                    "0. Salir\n" +
                    "Seleccione una opción:";
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break; // En caso de cerrar el JOptionPane, se termina el programa
            }

            opcion = Integer.parseInt(input);

            switch (opcion) {
                case 1:
                    CrearCarrera.crearCarrera();
                    break;
                case 2:
                    EditarCarrera.editarCarrera();
                    break;
                case 3:
                    EliminarCarrera.eliminarCarrera();
                    break;
                case 4:
                    ListarUnaCarrera.listarCarreraPorId();
                    break;
                case 5:
                    ListarMuchasCarreras.listarCarrerasMayorQue();
                    break;
                case 6:
                    ListarCarreras.listarCarreras();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 0);
    }
}

