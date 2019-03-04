package cn.gl.share_knowledge.util;

import java.util.Random;

public class VerifyCode {

    static Random random = new Random();

    /**
     * 返回四位数的验证码
     * @return
     */
    public static String registerCode(){
        int code = random.nextInt(9999);
        while (code < 1000) {
            code = random.nextInt(9999);
        }
        return code+"";
    }

}
