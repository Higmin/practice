package com.practice.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Sets;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 发送短信工具类
 *
 * 依赖：
 *        <!-- 腾讯云发短信-->
 *         <dependency>
 *             <groupId>com.tencentcloudapi</groupId>
 *             <artifactId>tencentcloud-sdk-java</artifactId>
 *             <version>3.1.715</version> 版本号自己确认
 *         </dependency>
 */
@Slf4j
@Component
public class TencentSmsUtil {

    /**
     * secret_id
     */
    private static final String SECRET_ID = "补充自己的 secret_id";
    /**
     * secret_key
     */
    private static final String SECRET_KEY = "补充自己的 secret_key";
    /**
     * appID
     */
    private static final String SDK_APP_ID_DEFAULT = "补充自己的 appId";
    /**
     * 模版ID
     */
    public static final String TEMPLATE_ID_DEFAULT = "补充自己的 模版ID";
    /**
     * REGION,可按官网提供地区填写，默认北京
     */
    private static final String REGION_DEFAULT = "ap-beijing";
    /**
     * 签名：短信信息中最开头的【】中的文字，例如：【TEST网】
     */
    private static final String SIGN_NAME_DEFAULT = "补充自己的 签名";
    /**
     * SDK默认用TC3-HMAC-SHA256进行签名,非必要请不要修改这个字段
     */
    private static final String SIGN_METHOD_DEFAULT = "HmacSHA256";
    /**
     * 腾讯云地址
     */
    private static final String END_POINT_DEFAULT = "sms.tencentcloudapi.com";

    private static volatile Credential credential;
    private static volatile HttpProfile httpProfile;
    private static volatile ClientProfile clientProfile;
    private static volatile SmsClient smsClient;

    private TencentSmsUtil() {
    }

    /**
     * 发送短信
     *
     * @param appid            申请腾讯云appid
     * @param countryCode      国家码, 举例：+86
     * @param phoneNumberSet   发送人
     * @param signName         签名
     * @param templateId       短信模版ID
     * @param templateParamSet 模版参数值
     * @return
     */
    public static SendSmsResponse sendSms(String appid, String countryCode, Set<String> phoneNumberSet, String signName, String templateId, Set<String> templateParamSet) {
        try {
            if (phoneNumberSet.size() > 200) {
                throw new RuntimeException("发送用户数超限，单次请求最多支持200个手机号且要求全为境内手机号或全为境外手机号。");
            }
            HashSet<String> sendNums = new HashSet<>();
            for (String phoneNumber : phoneNumberSet) {
                sendNums.add(countryCode + phoneNumber);
            }
            SendSmsRequest sendSmsRequest = new SendSmsRequest();
            sendSmsRequest.setSmsSdkAppId(appid);
            sendSmsRequest.setSignName(signName);
            sendSmsRequest.setTemplateId(templateId);
            sendSmsRequest.setTemplateParamSet(templateParamSet.toArray(new String[0]));
            sendSmsRequest.setPhoneNumberSet(sendNums.toArray(new String[0]));
            return TencentSmsUtil.getSmsClient().SendSms(sendSmsRequest);
        } catch (TencentCloudSDKException e) {
            log.error("send sms error", e);
        }
        return new SendSmsResponse();
    }

    /**
     * 发送短信
     *
     * @param countryCode      国家码, 举例：+86
     * @param phoneNumberSet   发送人
     * @param templateParamSet 模版参数值
     * @return
     */
    public static SendSmsResponse sendSms(String countryCode, Set<String> phoneNumberSet, String templateId, Set<String> templateParamSet) {
        return TencentSmsUtil.sendSms(SDK_APP_ID_DEFAULT, countryCode, phoneNumberSet, SIGN_NAME_DEFAULT, templateId, templateParamSet);
    }

    /**
     * 发送6位验证码短信
     *
     * @param countryCode 国家码, 举例：+86
     * @param phoneNumber 发送人
     * @return 6位验证码
     */
    public static String sendSms(String countryCode, String phoneNumber, String templateId) {
        String code = TencentSmsUtil.generate6Code();
        TencentSmsUtil.sendSms(countryCode, Sets.newHashSet(phoneNumber), templateId, Sets.newHashSet(code));
        return code;
    }

    /**
     * 随机生成6位随机验证码
     *
     * @return 6位验证码
     */
    public static String generate6Code() {
        return Integer.toString(100000 + new Random().nextInt(900000));
    }

    /**
     * 随机生成4位随机验证码
     *
     * @return 6位验证码
     */
    public static String generate4Code() {
        return Integer.toString(1000 + new Random().nextInt(9000));
    }

    private static Credential getCredential() {
        if (credential == null) {
            synchronized (TencentSmsUtil.class) {
                if (credential == null) {
                    credential = new Credential(SECRET_ID, SECRET_KEY);
                }
            }
        }
        return credential;
    }

    private static HttpProfile getHttpProfile() {
        if (httpProfile == null) {
            synchronized (TencentSmsUtil.class) {
                if (httpProfile == null) {
                    httpProfile = new HttpProfile();
                    httpProfile.setReqMethod(RequestMethod.POST.name());
                    httpProfile.setConnTimeout(60);
                    httpProfile.setEndpoint(END_POINT_DEFAULT);
                }
            }
        }
        return httpProfile;
    }

    private static ClientProfile getClientProfile() {
        if (clientProfile == null) {
            synchronized (TencentSmsUtil.class) {
                if (clientProfile == null) {
                    clientProfile = new ClientProfile();
                    /* SDK默认用TC3-HMAC-SHA256进行签名
                     * 非必要请不要修改这个字段 */
                    clientProfile.setSignMethod(SIGN_METHOD_DEFAULT);
                    clientProfile.setHttpProfile(TencentSmsUtil.getHttpProfile());
                }
            }
        }
        return clientProfile;
    }

    private static SmsClient getSmsClient() {
        if (smsClient == null) {
            synchronized (TencentSmsUtil.class) {
                if (smsClient == null) {
                    smsClient = new SmsClient(TencentSmsUtil.getCredential(), REGION_DEFAULT, TencentSmsUtil.getClientProfile());
                }
            }
        }
        return smsClient;
    }


    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(TencentSmsUtil.sendSms("+86", "12345567899", TEMPLATE_ID_DEFAULT)));
    }

}
