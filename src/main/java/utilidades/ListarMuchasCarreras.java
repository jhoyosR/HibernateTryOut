package utilidades;

import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilidades.JpaUtil;
import javax.swing.*;
import java.util.List;

public class ListarMuchasCarreras {

    public static void listarCarrerasMayorQue() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese un código de carrera:"));
            Query consulta = em.createQuery("select c from Carrera c where c.id > ?1", Carrera.class);
            consulta.setParameter(1, id);

            List<Carrera> carreras = consulta.getResultList();

            // Construir el mensaje para mostrar en un JOptionPane
            StringBuilder mensaje = new StringBuilder("Listado de Carreras:\n");
            for (Carrera carrera : carreras) {
                mensaje.append("ID: ").append(carrera.getId())
                        .append(", Nombre: ").append(carrera.getNombre())
                        .append(", Tipo: ").append(carrera.getTipo())
                        .append(", Facultad: ").append(carrera.getFacultad().getNombre()) // Muestra el nombre de la facultad relacionada
                        .append("\n");
            }

            // Mostrar las carreras en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Listado de Carreras", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar carreras.");
        } finally {
            em.close();
        }
    }
}

