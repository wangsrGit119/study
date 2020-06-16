package executer

import (
	"github.com/satori/go.uuid"
	"log"
	"os"
	"os/exec"
	"strings"
)
// 获取项目路径
func GetCurrentPath() string {
	dir, err := os.Getwd()
	if err != nil {
		log.Fatal(err)
	}
	return strings.Replace(dir, "\\", "/", -1)
}
//rtsp转rtmp
func RtspToRtmp(rtsp string) (rtmpResult string, pid int)  {
	log.Println("项目路径  ：",GetCurrentPath())
	newV4, err := uuid.NewV4()
	if err != nil {
		log.Println("UUID生成失败")
	}
	//组合新的rtmp流
	rtmp := "rtmp://localhost:1935/live/"+newV4.String()
	pid = RunCommandWithLogFile(GetCurrentPath()+"/lib/ffmpeg", "-i", rtsp, "-vcodec", "copy", "-acodec", "copy", "-f", "flv", rtmp)
	return rtmp,pid
}

//rtsp转flv
func RtspToFlv(rtsp string) (rtmpResult string, pid int)  {
	log.Println("项目路径  ：",GetCurrentPath())
	newV4, err := uuid.NewV4()
	if err != nil {
		log.Println("UUID生成失败")
	}
	//组合新的flv流
	rtmp := "rtmp://localhost:1935/live/"+newV4.String()
	pid = RunCommandWithLogFile(GetCurrentPath()+"/lib/ffmpeg", "-i", rtsp, "-vcodec", "copy", "-acodec", "copy", "-f", "flv", rtmp)
	return rtmp,pid
}

//多路合流
func RtspsToOneRtmp(rtsps []string, filters string) (rtmpResult string, pid int)   {
	log.Println("项目路径  ：",GetCurrentPath())
	newV4, err := uuid.NewV4()
	if err != nil {
		log.Println("UUID生成失败")
	}
	//组合新的rtmp流
	rtmp := "rtmp://localhost:1935/live/"+newV4.String()
	pid = RunCommandWithLogFile(GetCurrentPath()+"/lib/ffmpeg",
		"-rtsp_transport", "tcp", "-i",
		rtsps[0],
		"-rtsp_transport", "tcp", "-i",
		rtsps[1],
		"-rtsp_transport", "tcp", "-i",
		rtsps[2],
		"-filter_complex",
		filters,
		//"-map", "[out]" ,"-f", "flv", "-r", "25","-b:v", "10000k","-s","1920*1080",
		"-map", "[out]" ,"-c:v", "libx264",
		rtmp)
	return rtmp,pid
}
//具体命令执行处  无日志输出
func RunCommandWithLogFile(name string, arg ...string) (pid int)   {
	cmd := exec.Command(name, arg...)
	err := cmd.Start()
	if err != nil {
		panic("异常")
	}
	pid2Now := cmd.Process.Pid
	log.Println("进程pid  ",pid2Now)
	return pid2Now
}

//具体命令执行处  带有命令行输出
func RunCommand(name string, arg ...string) (pid int)  {
	cmd := exec.Command(name, arg...)
	stdout, _ := cmd.StdoutPipe()
	stderr, _ := cmd.StderrPipe()
	cmd.Start()
	pid2Now := cmd.Process.Pid
	log.Println("进程pid  ",pid2Now)
	go func() {
		for {
			buf := make([]byte, 1024)
			n, err := stderr.Read(buf)

			if n > 0 {
				log.Printf("warining info  %s", string(buf[:n]))
			}

			if n == 0 {
				break
			}

			if err != nil {
				log.Printf("错误信息 输出 %v", err)
				return
			}
		}
	}()

	go func() {
		for {
			buf := make([]byte, 1024)
			n, err := stdout.Read(buf)

			if n == 0 {
				break
			}

			if n > 0 {
				log.Printf("info  %s", string(buf[:n]))

			}

			if n == 0 {
				break
			}

			if err != nil {
				log.Printf("错误信息 输出  %v", err)
				return
			}

		}
	}()

	return pid2Now
}

//杀进程
func TsKillProcess(processId string) (res string)  {
	cmd := exec.Command("tskill",processId)
	cmd.Start()
	log.Println("即将关闭的进程pid",processId)
	return "进程已结束"
}

