package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(long id) {
        entityManager.createNativeQuery("delete from roles where id=:id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }
}
