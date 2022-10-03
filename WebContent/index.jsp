<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
var userAgent=navigator.userAgent.toLowerCase();
function deviceCheck() {
    // 디바이스 종류 설정
    var pcv = "win16|win32|win64|mac|macintel";
    // 접속한 디바이스 환경
    if ( navigator.platform ) {
        if ( pcv.indexOf(navigator.platform.toLowerCase()) < 0 ) {
            console.log('MOBILE');
            location="mobile/"
        } else {
        	var browser;
        	if(userAgent.indexOf('edge')>-1){
        		browser='익스플로러 엣지';
        	} else if (userAgent.indexOf('whale')>-1){
        		browser='네이버 웨일'
        	} else if (userAgent.indexOf('chrome')>-1){
        		browser='크롬';
        		
        	} else if (userAgent.indexOf('firefox')>-1){
        		browser='파이어폭스';
        	} else {
        		browser='익스플로러';
        	}
        	console.log('browser'+browser)
        	location="web/"
        }
    }
}
window.onload=function(){
	deviceCheck();
}

</script>
</head>
<body>

</body>
</html>