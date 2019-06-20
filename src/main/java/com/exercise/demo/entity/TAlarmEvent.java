package com.exercise.demo.entity;

import java.util.Date;

public class TAlarmEvent {
    private Integer id;

    private String type;

    private Date alarmTime;

    private String alarmContent;

    private String alarmKey;

    private String alarmValue;

    private Integer floorNumber;

    private Integer roomNumber;

    private String prisonSite;

    private String adapterNumber;

    private String adapterDirection;

    private String ftpName;

    private String cameraNumber;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent == null ? null : alarmContent.trim();
    }

    public String getAlarmKey() {
        return alarmKey;
    }

    public void setAlarmKey(String alarmKey) {
        this.alarmKey = alarmKey == null ? null : alarmKey.trim();
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue == null ? null : alarmValue.trim();
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

    public String getPrisonSite() {
        return prisonSite;
    }

    public void setPrisonSite(String prisonSite) {
        this.prisonSite = prisonSite == null ? null : prisonSite.trim();
    }

    public String getAdapterNumber() {
        return adapterNumber;
    }

    public void setAdapterNumber(String adapterNumber) {
        this.adapterNumber = adapterNumber == null ? null : adapterNumber.trim();
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

    public String getCameraNumber() {
        return cameraNumber;
    }

    public void setCameraNumber(String cameraNumber) {
        this.cameraNumber = cameraNumber == null ? null : cameraNumber.trim();
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
        sb.append(", type=").append(type);
        sb.append(", alarmTime=").append(alarmTime);
        sb.append(", alarmContent=").append(alarmContent);
        sb.append(", alarmKey=").append(alarmKey);
        sb.append(", alarmValue=").append(alarmValue);
        sb.append(", floorNumber=").append(floorNumber);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", prisonSite=").append(prisonSite);
        sb.append(", adapterNumber=").append(adapterNumber);
        sb.append(", adapterDirection=").append(adapterDirection);
        sb.append(", ftpName=").append(ftpName);
        sb.append(", cameraNumber=").append(cameraNumber);
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
        TAlarmEvent other = (TAlarmEvent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAlarmTime() == null ? other.getAlarmTime() == null : this.getAlarmTime().equals(other.getAlarmTime()))
            && (this.getAlarmContent() == null ? other.getAlarmContent() == null : this.getAlarmContent().equals(other.getAlarmContent()))
            && (this.getAlarmKey() == null ? other.getAlarmKey() == null : this.getAlarmKey().equals(other.getAlarmKey()))
            && (this.getAlarmValue() == null ? other.getAlarmValue() == null : this.getAlarmValue().equals(other.getAlarmValue()))
            && (this.getFloorNumber() == null ? other.getFloorNumber() == null : this.getFloorNumber().equals(other.getFloorNumber()))
            && (this.getRoomNumber() == null ? other.getRoomNumber() == null : this.getRoomNumber().equals(other.getRoomNumber()))
            && (this.getPrisonSite() == null ? other.getPrisonSite() == null : this.getPrisonSite().equals(other.getPrisonSite()))
            && (this.getAdapterNumber() == null ? other.getAdapterNumber() == null : this.getAdapterNumber().equals(other.getAdapterNumber()))
            && (this.getAdapterDirection() == null ? other.getAdapterDirection() == null : this.getAdapterDirection().equals(other.getAdapterDirection()))
            && (this.getFtpName() == null ? other.getFtpName() == null : this.getFtpName().equals(other.getFtpName()))
            && (this.getCameraNumber() == null ? other.getCameraNumber() == null : this.getCameraNumber().equals(other.getCameraNumber()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAlarmTime() == null) ? 0 : getAlarmTime().hashCode());
        result = prime * result + ((getAlarmContent() == null) ? 0 : getAlarmContent().hashCode());
        result = prime * result + ((getAlarmKey() == null) ? 0 : getAlarmKey().hashCode());
        result = prime * result + ((getAlarmValue() == null) ? 0 : getAlarmValue().hashCode());
        result = prime * result + ((getFloorNumber() == null) ? 0 : getFloorNumber().hashCode());
        result = prime * result + ((getRoomNumber() == null) ? 0 : getRoomNumber().hashCode());
        result = prime * result + ((getPrisonSite() == null) ? 0 : getPrisonSite().hashCode());
        result = prime * result + ((getAdapterNumber() == null) ? 0 : getAdapterNumber().hashCode());
        result = prime * result + ((getAdapterDirection() == null) ? 0 : getAdapterDirection().hashCode());
        result = prime * result + ((getFtpName() == null) ? 0 : getFtpName().hashCode());
        result = prime * result + ((getCameraNumber() == null) ? 0 : getCameraNumber().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}