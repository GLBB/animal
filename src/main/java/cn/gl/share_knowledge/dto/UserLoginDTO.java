package cn.gl.share_knowledge.dto;

import cn.gl.share_knowledge.bean.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLoginDTO {

    @NotEmpty(message = "邮箱不能为空")
    @NotNull(message = "邮箱不能为空")
    @Email(message = "不符合邮箱格式")
    private String email;

    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于 6 位")
    private String password;

    private String remenberPassword;

    public UserLoginDTO() {
    }

    public User convert(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemenberPassword() {
        return remenberPassword;
    }

    public void setRemenberPassword(String remenberPassword) {
        this.remenberPassword = remenberPassword;
    }
}
