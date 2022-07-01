package shop.nick.demo.service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import shop.nick.demo.entity.Attachment;
import shop.nick.demo.entity.AttachmentContent;
import shop.nick.demo.repository.AttachmentContentRepository;
import shop.nick.demo.repository.AttachmentRepository;

import java.util.Iterator;
import java.util.UUID;

@Service
public class AttachmentService {
    private final
    AttachmentRepository attachmentRepository;
    private final
    AttachmentContentRepository attachmentContentRepository;


    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    public HttpEntity<?> getFile(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getFile"));
        AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename:\"" + attachment.getName() + "\"")
                .body(byAttachmentId.getContent());
    }

    public UUID uploadFile(MultipartHttpServletRequest request) {
        try {
            Iterator<String> fileNames = request.getFileNames();
            while (fileNames.hasNext()) {
                MultipartFile file = request.getFile(fileNames.next());
                assert file != null;
                Attachment attachment = new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize());
                Attachment savedAttachment = attachmentRepository.save(attachment);
                AttachmentContent attachmentContent = new AttachmentContent(savedAttachment, file.getBytes());
                attachmentContentRepository.save(attachmentContent);
                return savedAttachment.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getAttachmentContent(UUID id) {
        try {
            AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            return java.util.Base64.getEncoder().encodeToString(byAttachmentId.getContent());
        } catch (Exception e) {
            return "";
        }

    }



}
