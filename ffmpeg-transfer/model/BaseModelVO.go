package model

type  RequestVOForOne struct {
	Rtsp string  `form:"rtsp" json:"rtsp" xml:"rtsp"  binding:"required"`
	Date string `form:"date" json:"date" xml:"date"  binding:"required"`
}

type  RequestVOForMany struct {
	Rtsps []string  `form:"rtsps" json:"rtsps" xml:"rtsps"  binding:"required"`
	Filters string `form:"filters" json:"filters" xml:"filters"  binding:"required"`
	Date string `form:"date" json:"date" xml:"date"  binding:"required"`
}