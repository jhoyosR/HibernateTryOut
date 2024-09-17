package utilidades;

import entidades.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilidades.JpaUtil;
import javax.swing.*;

public class ListarUnaCarrera {

    public static void listarCarreraPorId() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese un código de carrera:"));
            Query consulta = em.createQuery("select c from Carrera c where c.id = ?1", Carrera.class);
            consulta.setParameter(1, id);

            Carrera ca = (Carrera) consulta.getSingleResult();
            JOptionPane.showMessageDialog(null, ca);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar la carrera.");
        } finally {
            em.close();
        }
    }
}

