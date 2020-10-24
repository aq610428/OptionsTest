package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/10/24
 * @name:ActiveBean
 */
public class ActiveBean implements Serializable {

    private MiningChance miningChance;
    private String key;

    public MiningChance getMiningChance() {
        return miningChance;
    }

    public void setMiningChance(MiningChance miningChance) {
        this.miningChance = miningChance;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static class MiningChance implements Serializable {
        private String userableNum; //可用次数
        private String usedNum;//已用次数
        private String directCoun;//直推激活人数



        public String getUserableNum() {
            return userableNum;
        }

        public void setUserableNum(String userableNum) {
            this.userableNum = userableNum;
        }

        public String getUsedNum() {
            return usedNum;
        }

        public void setUsedNum(String usedNum) {
            this.usedNum = usedNum;
        }

        public String getDirectCoun() {
            return directCoun;
        }

        public void setDirectCoun(String directCoun) {
            this.directCoun = directCoun;
        }



    }

}
