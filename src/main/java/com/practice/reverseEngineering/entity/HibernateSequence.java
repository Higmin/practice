package com.practice.reverseEngineering.entity;

public class HibernateSequence {
    private Long nextVal;

    public Long getNextVal() {
        return nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nextVal=").append(nextVal);
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
        HibernateSequence other = (HibernateSequence) that;
        return (this.getNextVal() == null ? other.getNextVal() == null : this.getNextVal().equals(other.getNextVal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNextVal() == null) ? 0 : getNextVal().hashCode());
        return result;
    }
}