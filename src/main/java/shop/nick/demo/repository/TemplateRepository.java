package shop.nick.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nick.demo.entity.Template;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Integer> {

    boolean existsByNameEqualsIgnoreCase(String name);
}
