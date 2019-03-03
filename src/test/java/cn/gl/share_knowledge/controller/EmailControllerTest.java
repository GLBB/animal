package cn.gl.share_knowledge.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailControllerTest {

    @Test
    public void test1(){
        EmailController emailController = new EmailController();
        emailController.registerEmail("unbeatableglbb@outlook.com");
    }

}