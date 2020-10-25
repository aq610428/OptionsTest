package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/10/24
 * @name:ActiveBean
 */
public class ActiveBean implements Serializable {


    /**
     * miningChance : {"id":null,"memberId":null,"memberName":null,"miningType":0,"userableNum":0,"usedNum":0,"directCount":0,"createTime":null,"updateTime":null,"stringCreateTime":"","stringUpdateTime":null}
     * key : 100USDT挖矿
     */

    private MiningChanceBean miningChance;
    private String key;

    public MiningChanceBean getMiningChance() {
        return miningChance;
    }

    public void setMiningChance(MiningChanceBean miningChance) {
        this.miningChance = miningChance;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static class MiningChanceBean {
        /**
         * id : null
         * memberId : null
         * memberName : null
         * miningType : 0
         * userableNum : 0
         * usedNum : 0
         * directCount : 0
         * createTime : null
         * updateTime : null
         * stringCreateTime :
         * stringUpdateTime : null
         */

        private Object id;
        private Object memberId;
        private Object memberName;
        private int miningType;
        private int userableNum;
        private int usedNum;
        private int directCount;
        private Object createTime;
        private Object updateTime;
        private String stringCreateTime;
        private Object stringUpdateTime;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public Object getMemberName() {
            return memberName;
        }

        public void setMemberName(Object memberName) {
            this.memberName = memberName;
        }

        public int getMiningType() {
            return miningType;
        }

        public void setMiningType(int miningType) {
            this.miningType = miningType;
        }

        public int getUserableNum() {
            return userableNum;
        }

        public void setUserableNum(int userableNum) {
            this.userableNum = userableNum;
        }

        public int getUsedNum() {
            return usedNum;
        }

        public void setUsedNum(int usedNum) {
            this.usedNum = usedNum;
        }

        public int getDirectCount() {
            return directCount;
        }

        public void setDirectCount(int directCount) {
            this.directCount = directCount;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getStringCreateTime() {
            return stringCreateTime;
        }

        public void setStringCreateTime(String stringCreateTime) {
            this.stringCreateTime = stringCreateTime;
        }

        public Object getStringUpdateTime() {
            return stringUpdateTime;
        }

        public void setStringUpdateTime(Object stringUpdateTime) {
            this.stringUpdateTime = stringUpdateTime;
        }
    }
}
