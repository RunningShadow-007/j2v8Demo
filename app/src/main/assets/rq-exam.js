// var pre = "http://apponline.huiedu.com.cn/";//online
//var pre = "http://192.168.1.123:8870/xpt-app-backend/";//zhao
// var pre = "http://192.168.1.110:8090/xpt-app-backend/"//zhangxingman
// var pre = "http://ys.huiedu.com.cn:8098/" //yanshou
// var pre = "http://apponline.huiedu.com.cn/" //online
var pre = "http://192.168.1.144:8880/"
var pre_autologin = "http://192.168.1.145:9999/api/"
//var pre = "http://mobile.huiedu.com.cn/"
//var pre_autologin = "http://api.huiedu.com.cn/"


var ApiUrl = {
	/**登录系统**/
	WDK_LOGIN: pre_autologin + "api/tpb/applogin.do",
	/**获取考试列表**/
	WDK_EXAMLIST: pre + "web/testController/myExamList.do",
	/**考试回顾列表**/
	WDK_REVIEW_EXAMLIST: pre + "web/testController/myReviewExamList.do",
	/**考试试题回顾*/
	WDK_EXAM_REVIEW: pre + "web/testController/reviewExamPaper.do",
	/*获取考题内容*/
	WDK_SHOW_EXAM_PAPER: pre + "web/testController/showExamPaper.do",
	/*提交答案*/
	WDK_SAVE_EXAM_PAPER: pre + "web/testController/saveExamPaper.do",
	/**考试说明**/
	WDK_EXAM_INTRODUCTION: pre + "web/testController/showTestinstructionsBytestID.do",
	/*学习模块-课程详情*/
	WDK_STUDY_DETAIL: pre + "web/myCourseController/getMyStage.do",
	/**去学习首页列表**/
	WDK_TASKLIST: pre + "web/myCourseController/getMyTaskListV2.do",

};


function fillData(name,age){
    var details=name+age;
    printData(details);
}

function printData(details){
}




