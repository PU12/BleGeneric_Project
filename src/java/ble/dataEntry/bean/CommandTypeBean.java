/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ble.dataEntry.bean;

/**
 *
 * @author Shobha
 */
public class CommandTypeBean {
    String type,remark;
    int model_type_id;

    public int getModel_type_id() {
        return model_type_id;
    }

    public void setModel_type_id(int model_type_id) {
        this.model_type_id = model_type_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

}
