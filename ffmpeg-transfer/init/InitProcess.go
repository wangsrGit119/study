package init

import (
	"log"
	"os"
	"os/exec"
	"strings"
)
//具体命令执行处  带有实时日志
func RunCommand(name string, arg ...string) (pid int)  {
	cmd := exec.Command(name, arg...)
	stdout, _ := cmd.StdoutPipe()
	stderr, _ := cmd.StderrPipe()
	cmd.Start()
	pid2Now := cmd.Process.Pid
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

// 获取项目路径
func GetCurrentPath() string {
	dir, err := os.Getwd()
	if err != nil {
		log.Fatal(err)
	}
	return strings.Replace(dir, "\\", "/", -1)
}

func StartNginx() (resPid int) {

	log.Println("nginx 开始启动")
	log.Println(GetCurrentPath()+"/lib/nginx/nginx.exe")
	runCommand := RunCommand(GetCurrentPath()+"/lib/nginx/nginx.exe", "-p", GetCurrentPath()+"/lib/nginx")
	return runCommand
}

func StopNginx()  {
	log.Println("nginx 开始关闭")
	RunCommand(GetCurrentPath()+"/lib/nginx/nginx.exe", "-s","quit","-p",GetCurrentPath()+"/lib/nginx")
}