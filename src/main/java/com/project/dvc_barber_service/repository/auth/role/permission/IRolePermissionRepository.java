package com.project.dvc_barber_service.repository.auth.role.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
}
