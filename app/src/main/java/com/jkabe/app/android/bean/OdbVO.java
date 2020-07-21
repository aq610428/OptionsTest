package com.jkabe.app.android.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/17
 * @name:OdbVO
 */
public class OdbVO implements Serializable {
    private String load_calculation_value;
    private String coolant_temperature;
    private String engine_speed;
    private String obd_speed;
    private String ignition_dvance_angle;
    private String absolute_pressure_intake_manifold;
    private String control_module_voltage;
    private String intake_temperature;
    private String air_flow_rate;
    private String relative_position_throttle;
    private String longterm_fuel_correction;
    private String airfuel_ratio_coefficient;
    private String absolute_throttle_position;
    private String fuel_pressure;
    private String instantaneous_fuel_consumption;
    private String residual_oil;
    private String fuel_consumption;
    private String obdtime;
// 发动机净输出扭矩（作为发动机最大基准扭 矩的百分比），或发动机实际扭矩/指示扭矩（作为发动机最大基
// 准扭矩的百分比，例如依据喷射的燃料量计 算获得）
// 数据范围：-125~125%，0xff无效

    private String output_torque;
    // 摩擦扭矩（作为发动机最大基准扭矩的百分比）
    // 数据范围：-125~125%，0xff无效
    private String friction_torque;

    // SCR上游NOx传感器输出值
    // 数据范围：-200~3012.75ppm，0xff,0xff无效
    private String scr_up_nox_value;

    // SCR下游NOx传感器输出值
    // 数据范围：-200~3012.75ppm，0xff,0xff无效
    private String scr_down_nox_value;

    // 反应剂余量
    // 数据范围：0~100%，0xff无效
    private String reactant_margin;

    // SCR进口温度
    // 数据范围：-273~1734.96875 deg C，0xff,0xff无效
    private String scr_inlet_temperature;

    // SCR出口温度
    // 数据范围：-273~1734.96875 deg C，0xff,0xff无效
    private String scr_outlet_temperature;

    // DPF压差
    // 数据范围：0~6425.5kPa，0xff,0xff无效
    private String dpf_differential_pressure;

    public String getOutput_torque() {
        return output_torque;
    }

    public void setOutput_torque(String output_torque) {
        this.output_torque = output_torque;
    }

    public String getFriction_torque() {
        return friction_torque;
    }

    public void setFriction_torque(String friction_torque) {
        this.friction_torque = friction_torque;
    }

    public String getScr_up_nox_value() {
        return scr_up_nox_value;
    }

    public void setScr_up_nox_value(String scr_up_nox_value) {
        this.scr_up_nox_value = scr_up_nox_value;
    }

    public String getScr_down_nox_value() {
        return scr_down_nox_value;
    }

    public void setScr_down_nox_value(String scr_down_nox_value) {
        this.scr_down_nox_value = scr_down_nox_value;
    }

    public String getReactant_margin() {
        return reactant_margin;
    }

    public void setReactant_margin(String reactant_margin) {
        this.reactant_margin = reactant_margin;
    }

    public String getScr_inlet_temperature() {
        return scr_inlet_temperature;
    }

    public void setScr_inlet_temperature(String scr_inlet_temperature) {
        this.scr_inlet_temperature = scr_inlet_temperature;
    }

    public String getScr_outlet_temperature() {
        return scr_outlet_temperature;
    }

    public void setScr_outlet_temperature(String scr_outlet_temperature) {
        this.scr_outlet_temperature = scr_outlet_temperature;
    }

    public String getDpf_differential_pressure() {
        return dpf_differential_pressure;
    }

    public void setDpf_differential_pressure(String dpf_differential_pressure) {
        this.dpf_differential_pressure = dpf_differential_pressure;
    }

    public String getObdtime() {
        return obdtime;
    }

    public void setObdtime(String obdtime) {
        this.obdtime = obdtime;
    }

    public String getLoad_calculation_value() {
        return load_calculation_value;
    }

    public void setLoad_calculation_value(String load_calculation_value) {
        this.load_calculation_value = load_calculation_value;
    }

    public String getCoolant_temperature() {
        return coolant_temperature;
    }

    public void setCoolant_temperature(String coolant_temperature) {
        this.coolant_temperature = coolant_temperature;
    }

    public String getEngine_speed() {
        return engine_speed;
    }

    public void setEngine_speed(String engine_speed) {
        this.engine_speed = engine_speed;
    }

    public String getObd_speed() {
        return obd_speed;
    }

    public void setObd_speed(String obd_speed) {
        this.obd_speed = obd_speed;
    }

    public String getIgnition_dvance_angle() {
        return ignition_dvance_angle;
    }

    public void setIgnition_dvance_angle(String ignition_dvance_angle) {
        this.ignition_dvance_angle = ignition_dvance_angle;
    }

    public String getAbsolute_pressure_intake_manifold() {
        return absolute_pressure_intake_manifold;
    }

    public void setAbsolute_pressure_intake_manifold(String absolute_pressure_intake_manifold) {
        this.absolute_pressure_intake_manifold = absolute_pressure_intake_manifold;
    }

    public String getControl_module_voltage() {
        return control_module_voltage;
    }

    public void setControl_module_voltage(String control_module_voltage) {
        this.control_module_voltage = control_module_voltage;
    }

    public String getIntake_temperature() {
        return intake_temperature;
    }

    public void setIntake_temperature(String intake_temperature) {
        this.intake_temperature = intake_temperature;
    }

    public String getAir_flow_rate() {
        return air_flow_rate;
    }

    public void setAir_flow_rate(String air_flow_rate) {
        this.air_flow_rate = air_flow_rate;
    }

    public String getRelative_position_throttle() {
        return relative_position_throttle;
    }

    public void setRelative_position_throttle(String relative_position_throttle) {
        this.relative_position_throttle = relative_position_throttle;
    }

    public String getLongterm_fuel_correction() {
        return longterm_fuel_correction;
    }

    public void setLongterm_fuel_correction(String longterm_fuel_correction) {
        this.longterm_fuel_correction = longterm_fuel_correction;
    }

    public String getAirfuel_ratio_coefficient() {
        return airfuel_ratio_coefficient;
    }

    public void setAirfuel_ratio_coefficient(String airfuel_ratio_coefficient) {
        this.airfuel_ratio_coefficient = airfuel_ratio_coefficient;
    }

    public String getAbsolute_throttle_position() {
        return absolute_throttle_position;
    }

    public void setAbsolute_throttle_position(String absolute_throttle_position) {
        this.absolute_throttle_position = absolute_throttle_position;
    }

    public String getFuel_pressure() {
        return fuel_pressure;
    }

    public void setFuel_pressure(String fuel_pressure) {
        this.fuel_pressure = fuel_pressure;
    }

    public String getInstantaneous_fuel_consumption() {
        return instantaneous_fuel_consumption;
    }

    public void setInstantaneous_fuel_consumption(String instantaneous_fuel_consumption) {
        this.instantaneous_fuel_consumption = instantaneous_fuel_consumption;
    }

    public String getResidual_oil() {
        return residual_oil;
    }

    public void setResidual_oil(String residual_oil) {
        this.residual_oil = residual_oil;
    }

    public String getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(String fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }


}
