package com.project.dvc_barber_service.repository.auth.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
    @Query("""
        SELECT p
        FROM PermissionEntity p
        INNER JOIN RolePermissionEntity rp ON p.permissionId = rp.permissionId
        INNER JOIN RoleEntity r ON r.roleId = rp.roleId
        WHERE r.roleId = :roleId
    """)
    List<PermissionEntity> findByRoleId(Long roleId);
}
