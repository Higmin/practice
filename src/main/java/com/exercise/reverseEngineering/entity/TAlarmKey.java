package com.exercise.reverseEngineering.entity;

public class TAlarmKey {
    private Integer id;

    private String type;

    private String alarmKey;

    private String alarmValue;

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
        sb.append(", alarmKey=").append(alarmKey);
        sb.append(", alarmValue=").append(alarmValue);
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
        TAlarmKey other = (TAlarmKey) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAlarmKey() == null ? other.getAlarmKey() == null : this.getAlarmKey().equals(other.getAlarmKey()))
            && (this.getAlarmValue() == null ? other.getAlarmValue() == null : this.getAlarmValue().equals(other.getAlarmValue()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAlarmKey() == null) ? 0 : getAlarmKey().hashCode());
        result = prime * result + ((getAlarmValue() == null) ? 0 : getAlarmValue().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}