package com.smartshop.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smartshop.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
