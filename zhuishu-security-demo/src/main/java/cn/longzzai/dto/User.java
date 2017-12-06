package cn.longzzai.dto;

import cn.longzzai.validator.MyNotNull;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author longcho
 * 2017-10-15-10:18
 */
@Data
public class User {
    public interface UserSimpleView{}
    public interface UserDetailView extends UserSimpleView{}
    @JsonView(UserSimpleView.class)

    private String username;
    @JsonView(UserDetailView.class)
    private String password;

}
