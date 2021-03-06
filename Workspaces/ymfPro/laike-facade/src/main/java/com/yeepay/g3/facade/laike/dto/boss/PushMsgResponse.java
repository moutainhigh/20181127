package com.yeepay.g3.facade.laike.dto.boss;

import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.enumtype.MsgTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.PlatformEnum;
import com.yeepay.g3.facade.laike.enumtype.PushStatus;
import com.yeepay.g3.facade.laike.enumtype.RoleEnum;

import java.util.Date;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/11/9
 * @Time: 下午3:45
 */
public class PushMsgResponse extends BaseResponse {

    /**
     * 消息编号
     */
    private String messageNo;

    /**
     * 极光编号
     */
    private String jpushId;

    /**
     * 消息发布者
     */
    private String operator;

    /**
     * 消息类型
     */
    private MsgTypeEnum type;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 业务生效时间
     */
    private Date lifeStart;

    /**
     * 业务失效时间
     */
    private Date lifeEnd;

    /**
     * 网页/图片链接1
     */
    private String url1;

    /**
     * 网页/图片链接2
     */
    private String url2;

    /**
     * 接收者
     */
    private String phoneNumbers;

    /**
     * 用户类型
     */
    private RoleEnum role;

    /**
     * 应用平台
     */
    private String appVersionId;

    /**
     * 终端厂商
     */
    private String manufacturer;

    /**
     * 终端型号
     */
    private String model;

    /**
     * 推送状态
     */
    private PushStatus pushStatus;

    /**
     * 推送时间
     */
    private Date pushTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 应用平台
     */
    private PlatformEnum platform;

    public String getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public MsgTypeEnum getType() {
        return type;
    }

    public void setType(MsgTypeEnum type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLifeStart() {
        return lifeStart;
    }

    public void setLifeStart(Date lifeStart) {
        this.lifeStart = lifeStart;
    }

    public Date getLifeEnd() {
        return lifeEnd;
    }

    public void setLifeEnd(Date lifeEnd) {
        this.lifeEnd = lifeEnd;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        this.appVersionId = appVersionId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public PushStatus getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(PushStatus pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }
}
