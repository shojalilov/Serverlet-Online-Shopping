package shop.nick.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shop.nick.demo.entity.template.AbsEntityInt;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbsEntityInt {

    @ManyToOne(optional = false)
    private Template template;

    @ManyToOne(optional = false)
    private User user;

    private Timestamp date;
}
