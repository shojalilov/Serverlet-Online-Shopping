package shop.nick.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.nick.demo.entity.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer id;

    private User client;

    private TemplateDto template;

    private LocalDateTime date;
}
