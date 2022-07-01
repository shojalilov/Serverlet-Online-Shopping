package shop.nick.demo.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPageable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalElements;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer number;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object object;
}
