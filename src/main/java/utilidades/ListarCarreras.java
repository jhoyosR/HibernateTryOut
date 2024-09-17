package utilidades;

import entidades.Carrera;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.util.List;

public class ListarCarreras {

    public static void listarCarreras(){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            List<Carrera> carreras = em.createQuery("select c from Carrera c", Carrera.class).getResultList();

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
        JOptionPane.showMessageDialog(null, "Error al listar las carreras.");
        } finally {
        em.close();
        }
    }
}
