package cn.wangsr;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author: wjl
 * @description:
 * @time: 2019/8/15 0015 15:55
 */
public class WaterSourceResultBean implements Writable {


    private Long currentDetectionNum;//当前站点数量
    private String  mapExcessiveWater;//超标水量
    private String mapWaterTemperature;//水温
    private String mapPh;//PH
    private String mapOxygen;//溶解氧
    private String detectionTime;  //检测日期



    public WaterSourceResultBean() {
        super();
    }

    public WaterSourceResultBean(Long currentDetectionNum, String mapExcessiveWater, String mapWaterTemperature, String mapPh, String mapOxygen, String detectionTime) {
        super();
        this.currentDetectionNum = currentDetectionNum;
        this.mapExcessiveWater = mapExcessiveWater;
        this.mapWaterTemperature = mapWaterTemperature;
        this.mapPh = mapPh;
        this.mapOxygen = mapOxygen;
        this.detectionTime = detectionTime;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(currentDetectionNum);
        dataOutput.writeUTF(mapExcessiveWater);
        dataOutput.writeUTF(mapWaterTemperature);
        dataOutput.writeUTF(mapPh);
        dataOutput.writeUTF(mapOxygen);
        dataOutput.writeUTF(detectionTime);



    }

    public void readFields(DataInput dataInput) throws IOException {
        this.currentDetectionNum=dataInput.readLong();
        this.mapExcessiveWater=dataInput.readUTF();
        this.mapWaterTemperature=dataInput.readUTF();
        this.mapPh=dataInput.readUTF();
        this.mapOxygen=dataInput.readUTF();
        this.detectionTime=dataInput.readUTF();

    }

    public Long getCurrentDetectionNum() {
        return currentDetectionNum;
    }

    public void setCurrentDetectionNum(Long currentDetectionNum) {
        this.currentDetectionNum = currentDetectionNum;
    }

    public String getMapExcessiveWater() {
        return mapExcessiveWater;
    }

    public void setMapExcessiveWater(String mapExcessiveWater) {
        this.mapExcessiveWater = mapExcessiveWater;
    }

    public String getMapWaterTemperature() {
        return mapWaterTemperature;
    }

    public void setMapWaterTemperature(String mapWaterTemperature) {
        this.mapWaterTemperature = mapWaterTemperature;
    }

    public String getMapPh() {
        return mapPh;
    }

    public void setMapPh(String mapPh) {
        this.mapPh = mapPh;
    }

    public String getMapOxygen() {
        return mapOxygen;
    }

    public void setMapOxygen(String mapOxygen) {
        this.mapOxygen = mapOxygen;
    }

    public String getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(String detectionTime) {
        this.detectionTime = detectionTime;
    }

    @Override
    public String toString() {
        return "WaterSourceResultBean{" +
                "currentDetectionNum=" + currentDetectionNum +
                ", mapExcessiveWater='" + mapExcessiveWater + '\'' +
                ", mapWaterTemperature='" + mapWaterTemperature + '\'' +
                ", mapPh='" + mapPh + '\'' +
                ", mapOxygen='" + mapOxygen + '\'' +
                ", detectionTime='" + detectionTime + '\'' +
                '}';
    }
}
