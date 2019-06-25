package com.practice.reverseEngineering.entity;

import java.util.Date;

public class TVideoInfo {
    private Integer id;

    private String cameraNumber;

    private String videoName;

    private Date videoTime;

    private Integer floorNumber;

    private Integer roomNumber;

    private String videoSite;

    private String videoAdapterRelation;

    private String isAlarm;

    private Date alarmTime;

    private Date updateTime;

    private String remark;

    private String videoNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCameraNumber() {
        return cameraNumber;
    }

    public void setCameraNumber(String cameraNumber) {
        this.cameraNumber = cameraNumber == null ? null : cameraNumber.trim();
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
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

    public String getVideoSite() {
        return videoSite;
    }

    public void setVideoSite(String videoSite) {
        this.videoSite = videoSite == null ? null : videoSite.trim();
    }

    public String getVideoAdapterRelation() {
        return videoAdapterRelation;
    }

    public void setVideoAdapterRelation(String videoAdapterRelation) {
        this.videoAdapterRelation = videoAdapterRelation == null ? null : videoAdapterRelation.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getVideoNumber() {
        return videoNumber;
    }

    public void setVideoNumber(String videoNumber) {
        this.videoNumber = videoNumber == null ? null : videoNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cameraNumber=").append(cameraNumber);
        sb.append(", videoName=").append(videoName);
        sb.append(", videoTime=").append(videoTime);
        sb.append(", floorNumber=").append(floorNumber);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", videoSite=").append(videoSite);
        sb.append(", videoAdapterRelation=").append(videoAdapterRelation);
        sb.append(", isAlarm=").append(isAlarm);
        sb.append(", alarmTime=").append(alarmTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", videoNumber=").append(videoNumber);
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
        TVideoInfo other = (TVideoInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCameraNumber() == null ? other.getCameraNumber() == null : this.getCameraNumber().equals(other.getCameraNumber()))
            && (this.getVideoName() == null ? other.getVideoName() == null : this.getVideoName().equals(other.getVideoName()))
            && (this.getVideoTime() == null ? other.getVideoTime() == null : this.getVideoTime().equals(other.getVideoTime()))
            && (this.getFloorNumber() == null ? other.getFloorNumber() == null : this.getFloorNumber().equals(other.getFloorNumber()))
            && (this.getRoomNumber() == null ? other.getRoomNumber() == null : this.getRoomNumber().equals(other.getRoomNumber()))
            && (this.getVideoSite() == null ? other.getVideoSite() == null : this.getVideoSite().equals(other.getVideoSite()))
            && (this.getVideoAdapterRelation() == null ? other.getVideoAdapterRelation() == null : this.getVideoAdapterRelation().equals(other.getVideoAdapterRelation()))
            && (this.getIsAlarm() == null ? other.getIsAlarm() == null : this.getIsAlarm().equals(other.getIsAlarm()))
            && (this.getAlarmTime() == null ? other.getAlarmTime() == null : this.getAlarmTime().equals(other.getAlarmTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getVideoNumber() == null ? other.getVideoNumber() == null : this.getVideoNumber().equals(other.getVideoNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCameraNumber() == null) ? 0 : getCameraNumber().hashCode());
        result = prime * result + ((getVideoName() == null) ? 0 : getVideoName().hashCode());
        result = prime * result + ((getVideoTime() == null) ? 0 : getVideoTime().hashCode());
        result = prime * result + ((getFloorNumber() == null) ? 0 : getFloorNumber().hashCode());
        result = prime * result + ((getRoomNumber() == null) ? 0 : getRoomNumber().hashCode());
        result = prime * result + ((getVideoSite() == null) ? 0 : getVideoSite().hashCode());
        result = prime * result + ((getVideoAdapterRelation() == null) ? 0 : getVideoAdapterRelation().hashCode());
        result = prime * result + ((getIsAlarm() == null) ? 0 : getIsAlarm().hashCode());
        result = prime * result + ((getAlarmTime() == null) ? 0 : getAlarmTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getVideoNumber() == null) ? 0 : getVideoNumber().hashCode());
        return result;
    }
}