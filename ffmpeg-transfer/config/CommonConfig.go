package config

var ServerPort = ":8003"
var FfmpegPath = "../lib/ffmpeg.exe"
var CmdTemplate = " -i {0} -vcodec libx264 -vprofile baseline -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -q 10  {1}"