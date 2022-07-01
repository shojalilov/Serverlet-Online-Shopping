package shop.nick.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import shop.nick.demo.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachmentId(UUID attachment_id);

    boolean deleteByAttachment_Id(UUID uuid);
}
