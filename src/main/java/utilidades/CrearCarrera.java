package utilidades;

import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;
import javax.swing.*;

public class CrearCarrera {

    public static void crearCarrera() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Solicitar los datos de la carrera
            String nombre = JOptionPane.showInputDialog("Ingrese nombre de carrera:");
            int tipo = Integer.parseInt(JOptionPane.showInputDialog("Digite el tipo de carrera:"));

            //Solicitar el ID de la facultad
            Long idfacultad = Long.valueOf(JOptionPane.showInputDialog("Digite el id de la facultad:"));

            // Buscar la facultad por ID
            Facultad facultad = em.find(Facultad.class, idfacultad);
            if (facultad == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna facultad con el ID especificado.");
                return; // Salir si no se encuentra la facultad
            }

            //Iniciar la transacción
            em.getTransaction().begin();

            // Crear una nueva carrera y asignar los valores
            Carrera ca = new Carrera();
            ca.setNombre(nombre);
            ca.setTipo(tipo);
            ca.setFacultad(facultad); //Asignar la facultad encontrada

            //Persistir la nueva carrera
            em.persist(ca);

            //Confirmar la transacción
            em.getTransaction().commit();

            //Mostrar el ID de la nueva carrera creada
            JOptionPane.showMessageDialog(null, "El nuevo código de carrera es: " + ca.getId());
        } catch (Exception e) {
            // En caso de error, hacer rollback de la transacción
            em.getTransaction().rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear la carrera.");
        } finally {
            // Cerrar el EntityManager
            em.close();
        }
    }
}

