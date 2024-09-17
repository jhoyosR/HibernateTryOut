package utilidades;

import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;
import javax.swing.*;

public class EditarCarrera {

    public static void editarCarrera() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese un código de carrera a modificar:"));
            Carrera ca = em.find(Carrera.class, id);

            if (ca == null) {
                JOptionPane.showMessageDialog(null, "Carrera no encontrada.");
                return;
            }

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de carrera:", ca.getNombre());
            int tipo = Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo tipo de carrera:", ca.getTipo()));
            Long idfacultad = Long.valueOf(JOptionPane.showInputDialog("Digite el nuevo id de la facultad:"));

            // Buscar la facultad por ID
            Facultad facultad = em.find(Facultad.class, idfacultad);
            if (facultad == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna facultad con el ID especificado.");
                return; // Salir si no se encuentra la facultad
            }

            em.getTransaction().begin();
            ca.setNombre(nombre);
            ca.setTipo(tipo);
            ca.setFacultad(facultad);
            em.merge(ca);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Carrera actualizada: " + ca);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar la carrera.");
        } finally {
            em.close();
        }
    }
}

