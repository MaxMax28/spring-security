package web.repository;

import web.model.Role;

public interface RoleDao {

    Role getRoleById(long id);

    Role getRoleByName(String name);
}
