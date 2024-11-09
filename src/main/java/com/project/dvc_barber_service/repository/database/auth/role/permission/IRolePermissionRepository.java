package com.project.dvc_barber_service.repository.database.auth.role.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
    List<RolePermissionEntity> findByRoleId(Long roleId);

    @Query("""
        SELECT rp
        FROM RolePermissionEntity rp
        INNER JOIN RoleEntity r ON rp.roleId = r.roleId
        INNER JOIN PermissionEntity p ON p.permissionId = rp.permissionId
        WHERE rp.roleId = :roleId
            AND p.permissionCode = :permissionCode
    """)
    Optional<RolePermissionEntity> findByRoleIdAndPermissionCode(Long roleId, String permissionCode);

    List<RolePermissionEntity> findAllByRoleCode(String roleCode);

    void deleteByRoleCodeAndPermissionCode(String roleCode, String permissionCode);
}
