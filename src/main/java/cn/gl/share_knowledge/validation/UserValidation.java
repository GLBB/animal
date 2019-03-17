package cn.gl.share_knowledge.validation;

import cn.gl.share_knowledge.bean.User;

/***
 * 废弃
 */
@Deprecated
public class UserValidation {

    /**
     * 废弃
     * @param user
     * @return
     */
    private boolean loginUserValidate(User user){
        // 校验是否符合邮箱规范，密码字符串不为空，且长度不小于 6
        if (user.getEmail() == null || user.getPassword() == null) {

        }

        return false;
    }

}
