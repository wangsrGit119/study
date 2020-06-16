package main

import (
	. "ffmpeg-transfer/config"
	. "ffmpeg-transfer/init"
	. "ffmpeg-transfer/service"
	"github.com/gin-gonic/gin"
	"log"
	"os"
	"os/signal"
	//"strings"
)


func main() {

	gin.SetMode(gin.DebugMode)
	router := gin.Default()

	group1 := router.Group("/rtmp/init")
	{
		group1.POST("/",CreateTaskToRtmp)
		group1.DELETE("/:pid",TsKillHistory)

	}
	group2 := router.Group("/rtmp/merger")
	{
		group2.POST("/",ThreeRtspToRtmp)

	}
	group3 := router.Group("/flv/init")
	{
		group3.POST("/",ThreeRtspToRtmp)

	}
	go signalListen()
	router.Run(ServerPort)
}

//启动监听
func signalListen() {

	//合建chan
	c := make(chan os.Signal)
	//监听所有信号
	signal.Notify(c)
	//阻塞直到传入
	log.Println("项目启动")
	//启动nginx
	StartNginx()
	s := <-c
	log.Println("项目关闭", s)
	//关闭nginx
	StopNginx()
	os.Exit(-1)
}




