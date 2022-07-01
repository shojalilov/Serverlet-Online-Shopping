package shop.nick.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nick.demo.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameEqualsIgnoreCase(String username);
}
