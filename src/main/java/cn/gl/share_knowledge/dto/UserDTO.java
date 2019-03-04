package cn.gl.share_knowledge.dto;

import cn.gl.share_knowledge.bean.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotNull(message = "邮箱不能为空")
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotNull(message = "验证码不能为空")
    @NotEmpty(message = "验证码不能为空")
    private String code;

    @NotNull(message = "昵称不能为空")
    @NotEmpty(message = "昵称不能为空")
    private String nickName;

    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度最短6位")
    private String password;

    public UserDTO() {
    }

    public UserDTO(String email, String code, String nickName, String password) {
        this.email = email;
        this.code = code;
        this.nickName = nickName;
        this.password = password;
    }

    public User converToUser(){
        User user = new User(nickName, email, password);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
