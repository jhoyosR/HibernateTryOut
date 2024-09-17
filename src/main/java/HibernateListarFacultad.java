import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import java.util.List;

public class HibernateListarFacultad {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Facultad> facultades = em.createQuery("select c from Facultad c", Facultad.class).getResultList();
        facultades.forEach(System.out::println);
    }
}