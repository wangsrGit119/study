package service

import (
	. "ffmpeg-transfer/executer"
	. "ffmpeg-transfer/model"
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
)


//rtsp 转 rtmp
func CreateTaskToRtmp(c *gin.Context)  {
	var one RequestVOForOne
	err := c.ShouldBindJSON(&one)
	if err == nil {
		rtmpResult, pid := RtspToRtmp(one.Rtsp)
		c.JSON(http.StatusOK,gin.H{
			"message": "success",
			"code" : http.StatusOK,
			"rtmp" : rtmpResult,
			"pid" : pid,
		})
	}else {
		c.JSON(http.StatusOK,gin.H{
			"message": "参数绑定错误，请检查参数",
			"code" : http.StatusBadRequest,
		})
	}

}

//rtsp 转 Flv
func CreateTaskToFlv(c *gin.Context)  {
	var one RequestVOForOne
	err := c.ShouldBindJSON(&one)
	if err == nil {
		flvResult, pid := RtspToFlv(one.Rtsp)
		c.JSON(http.StatusOK,gin.H{
			"message": "success",
			"code" : http.StatusOK,
			"flv" : flvResult,
			"pid" : pid,
		})
	}else {
		c.JSON(http.StatusOK,gin.H{
			"message": "参数绑定错误，请检查参数",
			"code" : http.StatusBadRequest,
		})
	}

}

//合流
func ThreeRtspToRtmp(c *gin.Context)  {
	var two RequestVOForMany
	err := c.ShouldBindJSON(&two)
	if err == nil {
		if len(two.Rtsps) > 3{
			c.JSON(http.StatusOK,gin.H{
				"message": "流不能超过3个",
				"code" : http.StatusBadRequest,
			})
			return
		}
		rtmpResult, pid := RtspsToOneRtmp(two.Rtsps,two.Filters)
		c.JSON(http.StatusOK,gin.H{
			"message": "success",
			"code" : http.StatusOK,
			"rtmp" : rtmpResult,
			"pid" : pid,
		})
	}else {
		c.JSON(http.StatusOK,gin.H{
			"message": "参数绑定错误，请检查参数",
			"code" : http.StatusBadRequest,
		})
	}

}



//杀进程
func TsKillHistory(c *gin.Context)  {
	pathParam := c.Params
	TsKillProcess(pathParam.ByName("pid"))
	c.JSON(http.StatusOK,gin.H{
		"message": "该进程已被杀掉",
		"code" : http.StatusOK,
	})
}


func GetDetails(c *gin.Context)  {
	pathParam := c.Params
	fmt.Println("路径参数获取结果 {}", pathParam)
	c.String(http.StatusOK,"success")
}

