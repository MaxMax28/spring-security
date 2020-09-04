package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    void deleteRole(long id);

    Role getRoleById(long id);

    List<Role> getAllRoles();
}
