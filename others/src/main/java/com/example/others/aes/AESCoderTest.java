package com.example.others.aes;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AESCoderTest {

    @Test
    public void test() throws Exception {
//        String inputStr = "你好你是傻乎乎";
//        System.out.println(inputStr.getBytes().length);
//        String key = AESCoder.initKey();
//        System.err.println("原文:\t" + inputStr);
//
//        System.err.println("密钥:\t" + key);
//
//        byte[] inputData = inputStr.getBytes();
//        inputData = AESCoder.encrypt(inputData, key);
//        System.out.println(inputData.length);
//
//        System.err.println("加密后:\t" + Base64.encodeBase64String(inputData));
//
//        byte[] outputData = AESCoder.decrypt(Base64.decodeBase64(Base64.encodeBase64String(inputData)), key);
//        String outputStr = new String(outputData);
//
//        System.err.println("解密后:\t" + outputStr);
//
//        assertEquals(inputStr, outputStr);
        String  key = "qfn6UWPTLnCwMLFHzyWbMA==";
        String value = "rwka/Vj5O6mLsCab";
        System.out.println(new String(AESCoder.decrypt(Base64.decodeBase64(value.getBytes()),key)));
    }
}
