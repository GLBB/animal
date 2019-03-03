package cn.gl.share_knowledge.util;

import java.util.Random;

public class VerifyCode {

    static Random random = new Random();

    public static int registerCode(){
        int i = random.nextInt();
        return i;
    }

}
