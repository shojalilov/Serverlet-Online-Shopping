package shop.nick.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import shop.nick.demo.entity.Attachment;
import shop.nick.demo.entity.Template;
import shop.nick.demo.payload.CustomResponse;
import shop.nick.demo.payload.ResPageable;
import shop.nick.demo.payload.TemplateDto;
import shop.nick.demo.repository.AttachmentRepository;
import shop.nick.demo.repository.TemplateRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TemplateService {

    private final CustomResponseService responseService;
    private final AttachmentService attachmentService;

    private final TemplateRepository templateRepo;
    private final AttachmentRepository attachmentRepo;


    public TemplateService(CustomResponseService responseService, AttachmentService attachmentService, TemplateRepository templateRepo, AttachmentRepository attachmentRepo) {
        this.responseService = responseService;
        this.attachmentService = attachmentService;
        this.templateRepo = templateRepo;
        this.attachmentRepo = attachmentRepo;
    }

    public CustomResponse saveTemplate(MultipartHttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            if (!templateRepo.existsByNameEqualsIgnoreCase(name)) {
                UUID uuid = attachmentService.uploadFile(request);

                Template template = new Template();
                template.setName(name);
                template.setPrice(Double.parseDouble(request.getParameter("price")));
                template.setDescription(request.getParameter("description"));
                template.setAttachment(
                        Collections.singletonList(
                                attachmentRepo.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("get Attachment"))
                        )
                );

                templateRepo.save(template);

                return responseService.savedResponse(template);
            }
            return responseService.existsResponse();
        } catch (Exception e) {
            return responseService.tryErrorResponse();
        }
    }

    public CustomResponse addTemplateImage(MultipartHttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            UUID uuid = attachmentService.uploadFile(request);

            Template template = templateRepo.getOne(id);

            List<Attachment> attachmentList = template.getAttachment();
            attachmentList.add(attachmentRepo.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("get Attachment")));

            template.setAttachment(attachmentList);

            templateRepo.save(template);

            return responseService.savedResponse(template);
        } catch (Exception e) {
            return responseService.tryErrorResponse();
        }
    }


    public List<TemplateDto> getTemplates() {
        try {
            return templateRepo.findAll().stream().map(this::getTemplate).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public TemplateDto getTemplateOne(Integer id) {
        try {
            return getTemplate(templateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("get Template")));
        } catch (Exception e) {
            return null;
        }
    }

    public TemplateDto getTemplate(Template template) {
        try {
            return new TemplateDto(
                    template.getId(),
                    template.getName(),
                    template.getAttachment(),
                    template.getPrice(),
                    template.getDescription()
            );
        } catch (Exception e) {
            return null;
        }
    }
}
