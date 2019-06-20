package com.exercise.reverseEngineering.entity;

public class TPrison {
    private Integer id;

    private Integer floorNumber;

    private Integer roomNumber;

    private String prisonSite;

    private String videoNumber;

    private String videoName;

    private String adapterNumber;

    private String isAlarm;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getVideoNumber() {
        return videoNumber;
    }

    public void setVideoNumber(String videoNumber) {
        this.videoNumber = videoNumber == null ? null : videoNumber.trim();
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getAdapterNumber() {
        return adapterNumber;
    }

    public void setAdapterNumber(String adapterNumber) {
        this.adapterNumber = adapterNumber == null ? null : adapterNumber.trim();
    }

    public String getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(String isAlarm) {
        this.isAlarm = isAlarm == null ? null : isAlarm.trim();
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
        sb.append(", floorNumber=").append(floorNumber);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", prisonSite=").append(prisonSite);
        sb.append(", videoNumber=").append(videoNumber);
        sb.append(", videoName=").append(videoName);
        sb.append(", adapterNumber=").append(adapterNumber);
        sb.append(", isAlarm=").append(isAlarm);
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
        TPrison other = (TPrison) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFloorNumber() == null ? other.getFloorNumber() == null : this.getFloorNumber().equals(other.getFloorNumber()))
            && (this.getRoomNumber() == null ? other.getRoomNumber() == null : this.getRoomNumber().equals(other.getRoomNumber()))
            && (this.getPrisonSite() == null ? other.getPrisonSite() == null : this.getPrisonSite().equals(other.getPrisonSite()))
            && (this.getVideoNumber() == null ? other.getVideoNumber() == null : this.getVideoNumber().equals(other.getVideoNumber()))
            && (this.getVideoName() == null ? other.getVideoName() == null : this.getVideoName().equals(other.getVideoName()))
            && (this.getAdapterNumber() == null ? other.getAdapterNumber() == null : this.getAdapterNumber().equals(other.getAdapterNumber()))
            && (this.getIsAlarm() == null ? other.getIsAlarm() == null : this.getIsAlarm().equals(other.getIsAlarm()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFloorNumber() == null) ? 0 : getFloorNumber().hashCode());
        result = prime * result + ((getRoomNumber() == null) ? 0 : getRoomNumber().hashCode());
        result = prime * result + ((getPrisonSite() == null) ? 0 : getPrisonSite().hashCode());
        result = prime * result + ((getVideoNumber() == null) ? 0 : getVideoNumber().hashCode());
        result = prime * result + ((getVideoName() == null) ? 0 : getVideoName().hashCode());
        result = prime * result + ((getAdapterNumber() == null) ? 0 : getAdapterNumber().hashCode());
        result = prime * result + ((getIsAlarm() == null) ? 0 : getIsAlarm().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}