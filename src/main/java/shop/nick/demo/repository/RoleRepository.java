package shop.nick.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nick.demo.entity.Role;
import shop.nick.demo.entity.enums.RoleName;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
