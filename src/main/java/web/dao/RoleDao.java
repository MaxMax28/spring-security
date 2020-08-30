package web.dao;

import web.model.Role;

public interface RoleDao {

    void addRole(Role role);

    void deleteRole(long id);

    Role getRoleById(long id);
}
