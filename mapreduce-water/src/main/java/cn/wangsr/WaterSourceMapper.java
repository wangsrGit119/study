package cn.wangsr;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * @author: wjl
 * @description:      String detectionName 作为key  对应观测站点  map<String,String>  作为value  对应关键指标
 *      MapWritable mapExcessiveWater;//超标水量
 *      MapWritable mapWaterTemperature;//水温
 *      MapWritable mapPh;//PH
 *      MapWritable mapOxygen;//溶解氧
 *      Text detectionTime;  //检测日期
 *      静态值后期改为枚举
 * @time: 2019/8/15 0015 14:30
 */
public class WaterSourceMapper extends Mapper<LongWritable, Text, Text, WaterSourceResultBean> {
    Text k = new Text();
    Long currentDetectionNum=1L;
    WaterSourceResultBean v=new WaterSourceResultBean();
    String mapExcessiveWater;
    String mapWaterTemperature;
    String mapPh;
    String mapOxygen;
    String detectionTime;



    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        JSONObject jsonObject=JSONObject.parseObject(line);


        String  deN=jsonObject.getString("detectionName")==null? String.valueOf("未知监测点"): jsonObject.getString("detectionName");
        k.set(new Text(deN)); //检测站点作为key

        mapExcessiveWater=jsonObject.getString("excessiveWater")==null? String.valueOf(0): jsonObject.getString("excessiveWater");


        mapWaterTemperature=jsonObject.getString("waterTemperature")==null? String.valueOf(0): jsonObject.getString("waterTemperature");


        mapPh=jsonObject.getString("ph")==null? String.valueOf(0): jsonObject.getString("ph");


        mapOxygen=jsonObject.getString("oxygen")==null? String.valueOf(0): jsonObject.getString("oxygen");


        detectionTime=jsonObject.getString("detectionTime");


              v.setCurrentDetectionNum(currentDetectionNum);
              v.setMapExcessiveWater(mapExcessiveWater);
              v.setMapWaterTemperature(mapWaterTemperature);
              v.setMapPh(mapPh);
              v.setMapOxygen(mapOxygen);
              v.setDetectionTime(detectionTime);
              context.write(k, v); //一行写入一次

    }
}
