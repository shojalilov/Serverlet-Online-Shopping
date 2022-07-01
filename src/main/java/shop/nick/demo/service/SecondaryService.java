package shop.nick.demo.service;

import org.springframework.stereotype.Service;
import shop.nick.demo.entity.User;
import shop.nick.demo.entity.enums.RoleName;

@Service
public class SecondaryService {

    public boolean orderAdmin(User user){
        return user.getRole().getRoleName().equals(RoleName.ADMIN);
    }
}
