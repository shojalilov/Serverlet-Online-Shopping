package shop.nick.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.nick.demo.entity.Attachment;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    private Integer id;

    private String name;

    private List<Attachment> attachments;
    private UUID attachmentId;
    private double price;
    private String description;

    public TemplateDto(String name, UUID attachmentId, double price, String description) {
        this.name = name;
        this.attachmentId = attachmentId;
        this.price = price;
        this.description = description;
    }

    public TemplateDto(Integer id, String name, List<Attachment> attachments, double price, String description) {
        this.id = id;
        this.name = name;
        this.attachments = attachments;
        this.price = price;
        this.description = description;
    }
}
