package com.exercise.demo.entity;

import java.util.Date;

public class TAdapterInfo {
    private Integer id;

    private String adapterNumber;

    private String adapterType;

    private Date createTime;

    private String adapterIp;

    private String adapterStatus;

    private Integer floorNumber;

    private Integer roomNumber;

    private String adapterSite;

    private String adapterDirection;

    private String ftpName;

    private String adapterAdapterRelation;

    private String adapterVideoRelation;

    private Date updateTime;

    private String isAlarm;

    private Date alarmTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdapterNumber() {
        return adapterNumber;
    }

    public void setAdapterNumber(String adapterNumber) {
        this.adapterNumber = adapterNumber == null ? null : adapterNumber.trim();
    }

    public String getAdapterType() {
        return adapterType;
    }

    public void setAdapterType(String adapterType) {
        this.adapterType = adapterType == null ? null : adapterType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAdapterIp() {
        return adapterIp;
    }

    public void setAdapterIp(String adapterIp) {
        this.adapterIp = adapterIp == null ? null : adapterIp.trim();
    }

    public String getAdapterStatus() {
        return adapterStatus;
    }

    public void setAdapterStatus(String adapterStatus) {
        this.adapterStatus = adapterStatus == null ? null : adapterStatus.trim();
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAdapterSite() {
        return adapterSite;
    }

    public void setAdapterSite(String adapterSite) {
        this.adapterSite = adapterSite == null ? null : adapterSite.trim();
    }

    public String getAdapterDirection() {
        return adapterDirection;
    }

    public void setAdapterDirection(String adapterDirection) {
        this.adapterDirection = adapterDirection == null ? null : adapterDirection.trim();
    }

    public String getFtpName() {
        return ftpName;
    }

    public void setFtpName(String ftpName) {
        this.ftpName = ftpName == null ? null : ftpName.trim();
    }

    public String getAdapterAdapterRelation() {
        return adapterAdapterRelation;
    }

    public void setAdapterAdapterRelation(String adapterAdapterRelation) {
        this.adapterAdapterRelation = adapterAdapterRelation == null ? null : adapterAdapterRelation.trim();
    }

    public String getAdapterVideoRelation() {
        return adapterVideoRelation;
    }

    public void setAdapterVideoRelation(String adapterVideoRelation) {
        this.adapterVideoRelation = adapterVideoRelation == null ? null : adapterVideoRelation.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(String isAlarm) {
        this.isAlarm = isAlarm == null ? null : isAlarm.trim();
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adapterNumber=").append(adapterNumber);
        sb.append(", adapterType=").append(adapterType);
        sb.append(", createTime=").append(createTime);
        sb.append(", adapterIp=").append(adapterIp);
        sb.append(", adapterStatus=").append(adapterStatus);
        sb.append(", floorNumber=").append(floorNumber);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", adapterSite=").append(adapterSite);
        sb.append(", adapterDirection=").append(adapterDirection);
        sb.append(", ftpName=").append(ftpName);
        sb.append(", adapterAdapterRelation=").append(adapterAdapterRelation);
        sb.append(", adapterVideoRelation=").append(adapterVideoRelation);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isAlarm=").append(isAlarm);
        sb.append(", alarmTime=").append(alarmTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TAdapterInfo other = (TAdapterInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdapterNumber() == null ? other.getAdapterNumber() == null : this.getAdapterNumber().equals(other.getAdapterNumber()))
            && (this.getAdapterType() == null ? other.getAdapterType() == null : this.getAdapterType().equals(other.getAdapterType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAdapterIp() == null ? other.getAdapterIp() == null : this.getAdapterIp().equals(other.getAdapterIp()))
            && (this.getAdapterStatus() == null ? other.getAdapterStatus() == null : this.getAdapterStatus().equals(other.getAdapterStatus()))
            && (this.getFloorNumber() == null ? other.getFloorNumber() == null : this.getFloorNumber().equals(other.getFloorNumber()))
            && (this.getRoomNumber() == null ? other.getRoomNumber() == null : this.getRoomNumber().equals(other.getRoomNumber()))
            && (this.getAdapterSite() == null ? other.getAdapterSite() == null : this.getAdapterSite().equals(other.getAdapterSite()))
            && (this.getAdapterDirection() == null ? other.getAdapterDirection() == null : this.getAdapterDirection().equals(other.getAdapterDirection()))
            && (this.getFtpName() == null ? other.getFtpName() == null : this.getFtpName().equals(other.getFtpName()))
            && (this.getAdapterAdapterRelation() == null ? other.getAdapterAdapterRelation() == null : this.getAdapterAdapterRelation().equals(other.getAdapterAdapterRelation()))
            && (this.getAdapterVideoRelation() == null ? other.getAdapterVideoRelation() == null : this.getAdapterVideoRelation().equals(other.getAdapterVideoRelation()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsAlarm() == null ? other.getIsAlarm() == null : this.getIsAlarm().equals(other.getIsAlarm()))
            && (this.getAlarmTime() == null ? other.getAlarmTime() == null : this.getAlarmTime().equals(other.getAlarmTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdapterNumber() == null) ? 0 : getAdapterNumber().hashCode());
        result = prime * result + ((getAdapterType() == null) ? 0 : getAdapterType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAdapterIp() == null) ? 0 : getAdapterIp().hashCode());
        result = prime * result + ((getAdapterStatus() == null) ? 0 : getAdapterStatus().hashCode());
        result = prime * result + ((getFloorNumber() == null) ? 0 : getFloorNumber().hashCode());
        result = prime * result + ((getRoomNumber() == null) ? 0 : getRoomNumber().hashCode());
        result = prime * result + ((getAdapterSite() == null) ? 0 : getAdapterSite().hashCode());
        result = prime * result + ((getAdapterDirection() == null) ? 0 : getAdapterDirection().hashCode());
        result = prime * result + ((getFtpName() == null) ? 0 : getFtpName().hashCode());
        result = prime * result + ((getAdapterAdapterRelation() == null) ? 0 : getAdapterAdapterRelation().hashCode());
        result = prime * result + ((getAdapterVideoRelation() == null) ? 0 : getAdapterVideoRelation().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsAlarm() == null) ? 0 : getIsAlarm().hashCode());
        result = prime * result + ((getAlarmTime() == null) ? 0 : getAlarmTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}