<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
.card {
    margin-bottom: 5%;
    min-height: 200px;
}


.card-caption h4 {
    margin-bottom: 8px;
    font-style: bold;
}

.card button {
    margin-top: 16px;
}

.card-text {
    margin: 8px 0;
    color: #232323;
}

#map {
    display: flex;
    width: 100%;
    min-height: 300px;
    height: 100%;
}

#icon2{
    max-width : 100px;
    max-height : 100px;
}

#city{
    color : #efefef;
}

#temp{
    color : #efefef;
    margin-bottom : 24px;
}

</style>

<c:set var="imgUrl" value="${pageContext.request.contextPath}/resources/image"/>
<div id="demo" class="carousel slide" data-bs-ride="carousel">
	<!-- Indicators/dots -->
	
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
			class="active"></button>
		<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
		<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
	</div>

	<!-- The slideshow/carousel -->
	<div id="demo" class="carousel slide" data-bs-ride="carousel">
   <!-- Indicators/dots -->
   <div class="carousel-indicators">
      <button type="button" data-bs-target="#demo" data-bs-slide-to="0"
         class="active"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
   </div>

   <!-- The slideshow/carousel -->
   <div class="carousel-inner">
      <div class="carousel-item active">
         <img
            src="${pageContext.request.contextPath}/resources/image/back1.jpg"
            alt="Carousel" class="d-block" style="width: 100%">
         <div class="carousel-caption card-capiton">
            <h3>오프라인 매장 리스트</h3>
            <div class="card">
               <div class="row no-gutters">
                  <div class="col-8">
                     <div id="map"></div>
                  </div>
                  <div class="col-4">
                     <div class="card-body">
                        <h4 class="card-text">오프라인 매장</h4>
                        <p class="card-text">서울 금천구 가산디지털1로 151 2층 B강의실</p>
                        <button type="button" class="btn btn-dark" onclick="location.href='http://192.168.0.83/bteam2/';">오프라인 매장 리스트
                           확인</button>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="carousel-item">
         <img
            src="${pageContext.request.contextPath}/resources/image/back2.png"
            alt="Carousel" class="d-block" style="width: 100%">
         <div class="carousel-caption">
            <button type="button" class="btn btn-dark btn-lg">
               무진장 세일 <span style="color: grey;">종료까지</span> <span id="saletimer"
                  style="color: grey;"></span>
            </button>
         </div>
      </div>
      <div class="carousel-item">
         <img src="${pageContext.request.contextPath}/resources/image/clearsky_bg.jpg"
            alt="JSP" class="d-block" style="width: 100%" id="img2">
         <div class="carousel-caption weather1">
            <h3 id="city"></h3>
            <img src="" id="icon2">
            <p> </p>
            <h5 id="temp"><span id= "temp2"></span> °C</h5>
         </div>
      </div>
   </div>

	<!-- Left and right controls/icons -->
	<button class="carousel-control-prev" type="button"
		data-bs-target="#demo" data-bs-slide="prev">
		<span class="carousel-control-prev-icon"></span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#demo" data-bs-slide="next">
		<span class="carousel-control-next-icon"></span>
	</button>
</div>
</div>
<!-- 카카오 지도 API -->
<script 
   type="text/javascript" 
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=84fef84451b088661361e3778a690ba3&libraries=services">
</script>
<script>

   //카카오 지도 불러오기
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
   mapOption = {
      center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level : 5 // 지도의 확대 레벨
   };

   // 지도를 생성합니다    
   var map = new kakao.maps.Map(mapContainer, mapOption);

   // 주소-좌표 변환 객체를 생성합니다
   var geocoder = new kakao.maps.services.Geocoder();

   // 주소로 좌표를 검색합니다
   geocoder.addressSearch('서울 금천구 가산디지털1로 151', function(result, status) {
      // 정상적으로 검색이 완료됐으면 
      if (status === kakao.maps.services.Status.OK) {
         var coords = new kakao.maps.LatLng(result[0].y , result[0].x);

         // 결과값으로 받은 위치를 마커로 표시합니다
         var marker = new kakao.maps.Marker({
            map : map,
            position : coords
         });

         // 인포윈도우로 장소에 대한 설명을 표시합니다
         var infowindow = new kakao.maps.InfoWindow({
            content : '<div style="width:150px;text-align:center;color:#232323;padding:6px 0;">B-Sinsa</div>'
         });
         infowindow.open(map, marker);

         // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
         map.setCenter(coords);
      }
   });
   //-----카운트 다운 기능
   const countDownTimer = function(id, date) {
      var _vDate = new Date(date); // 전달 받은 일자
      var _second = 1000;
      var _minute = _second * 60;
      var _hour = _minute * 60;
      var _day = _hour * 24;
      var timer;
      
      function showRemaining() {
         
         var now = new Date();
         var distDt = _vDate - now;

         if (distDt < 0) {
            clearInterval(timer);
            document.getElementById(id).textContent = '해당 이벤트가 종료 되었습니다!';
            return;
         }

         var days = Math.floor(distDt / _day);
         var hours = Math.floor((distDt % _day) / _hour);
         var minutes = Math.floor((distDt % _hour) / _minute);
         var seconds = Math.floor((distDt % _minute) / _second);

         //document.getElementById(id).textContent = date.toLocaleString() + "까지 : ";
         document.getElementById(id).textContent = "D-" + days;
         document.getElementById(id).textContent += " " + hours + ":";
         document.getElementById(id).textContent += minutes + ":";
         document.getElementById(id).textContent += seconds;
      }

      timer = setInterval(showRemaining, 1000);

   }

   var dateObj = new Date();
   dateObj.setDate(dateObj.getDate() + 1);

   countDownTimer('saletimer', '/7/22/2022 10:00 PM');
   //날씨 API
   var apiURI = "https://api.openweathermap.org/data/2.5/weather?id=1835848&appid=f86baf9bddf3c35d74d50679068c6c86";
   $.ajax({
   url : apiURI,
   dataType : "json",
   type : "GET",
   async : "false",
   success : function(data) {
      console.log(data);
      console.log("현재온도 : " + (data.main.temp - 273.15));
      console.log("현재습도 : " + data.main.humidity);
      console.log("날씨 : " + data.weather[0].main);
      console.log("상세날씨설명 : " + data.weather[0].description);
      console.log("날씨 이미지 : " + data.weather[0].icon);
      console.log("바람   : " + data.wind.speed);
      console.log("나라   : " + data.sys.country);
      console.log("도시이름  : " + data.name);
      console.log("구름  : " + (data.clouds.all) + "%");
      
      $('#demo2').html(data.main.humidity);
      $('#demo3').html(data.weather[0].main);
      $('#demo4').html(data.weather[0].description);
      $('#demo5').html(data.weather[0].icon);
      $('#demo6').html(data.sys.country);
      let weather = data.weather[0].main;
      let temperature = Math.round((data.main.temp - 273.15));
      if (weather == 'Clear sky') {
           $('#city').html(data.name);
         $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/clearsky_bg.jpg');
         $('#icon2').attr('src','${pageContext.request.contextPath}/resources/image/clearsky_icon.png');
         $('#temp2').html(temperature);
         } else if(weather == 'Rain'){
            $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/rain.jpg');
            $('#he').html("오늘의 날씨는 "+data.weather[0].main+"현재습도 : "+data.main.humidity);
         } else if(weather == 'Mist'){
            $('#city').html(data.name);
            $('#city').css("color","#232323"); 
             $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/mist_bg.jpg');
             $('#icon2').attr('src','${pageContext.request.contextPath}/resources/image/mist_icon.png');
             $('#temp2').html(temperature);
             $('#temp').css("color","#232323"); 
         } else if(weather == 'Clouds'){
            $('#city').html(data.name);
             $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/cloud_bg.jpg');
             $('#icon2').attr('src','${pageContext.request.contextPath}/resources/image/cloud_icon.png');
             $('#temp2').html(temperature);
         }else if(weather == 'overcast clouds'){
            $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/overcast.jpg');
            $('#he').html("오늘의 날씨는 "+data.weather[0].main+"현재습도 : "+data.main.humidity);
         }else if(weather == 'shower rain'){
            $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/shower.jpg');
            $('#he').html("오늘의 날씨는 "+data.weather[0].main+"현재습도 : "+data.main.humidity);
         }else if(weather == 'few clouds'){
            $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/fewcloud.jpg');
            $('#he').html("오늘의 날씨는 "+data.weather[0].main+"현재습도 : "+data.main.humidity);
         }else if(weather == 'scattered clouds'){
            $('#img2').attr('src','${pageContext.request.contextPath}/resources/image/scattered.jpg');
            $('#he').html("오늘의 날씨는 "+data.weather[0].main+"현재습도 : "+data.main.humidity);
         }
   }
});
</script>