package shop.nick.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.nick.demo.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

    @Query(nativeQuery = true, value = "delete from attachment where id=:deleteId")
    void deleteAttachment(UUID deleteId);

}
