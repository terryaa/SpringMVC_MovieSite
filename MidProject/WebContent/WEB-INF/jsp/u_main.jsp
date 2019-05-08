<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<c:set value="${pageContext.request.contextPath}/resources/img" var="resimg"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<title>Dashio - Bootstrap Admin Template</title>

<!-- Favicons -->
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Bootstrap core CSS -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--external css-->
<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">

<!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>
<!-- js placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="lib/jquery/jquery.min.js"></script>
	<script src="lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="lib/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="lib/jquery.ui.touch-punch.min.js"></script>
	<script class="include" type="text/javascript"
		src="lib/jquery.dcjqaccordion.2.7.js"></script>
	<script src="lib/jquery.scrollTo.min.js"></script>
	<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
	<!--common script for all pages -->
	<script src="lib/common-scripts.js"></script>
	<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
	<!--script for this page-->
	<script>
		$(document).ready(function(){
// 			event();
			var uid = "${sessionScope.uid}";
			var title = "";
			var movie_number = "";
			
			$("#searchtarget").click(function(e) {
						
								if(${sessionScope.uid == null}){
									alert("로그인을 해주세요.");
								}else{
									console.log("${sessionScope.uid}");
									$("#myModal").css({
										"display" : "block"
									});
									console.log(e);
									title = $(e.target).attr("title");
									movie_number = $(e.target).attr("id");
									console.log(title);
									console.log(movie_number);
									$.ajax({
										url : "/MidProject/GM/getLink?title="+ title,
										success : function(data) {
										console.log(data);
										$('#poster').html(data.m_img);
										$('#title').text(data.m_Title);
										$('#eng_Title').text(data.m_EngTitle);
										if(data.m_price == 0){
											$('#price_area').text("가격 : 무료 ");
										}else{
											$('#price_area').text("가격 : "+data.m_price+" 원");
										}
										$('#step1').text(data.m_info_step1);
										$('#director').text(data.m_info_director);
										$('#actor').text(data.m_info_actor);
										if(data.event == true){
											$('#event').text("이벤트!!");
										}
										var tag_list_html = "";
										console.log(data.tag_list);
										if(data.tag_list.length != 0){
											for ( var i in data.tag_list) {
												if(i==5){
													tag_list_html += "<br>"
												}
												tag_list_html += "#"+ data.tag_list[i] +"		";
											}
// 											for(var k=0; k<10; i++){
// 												if(i=5){
// 													tag_list_html += "<br>"
// 												}
// 												tag_list_html += "#"+data.tag_list[k]+"		";
// 											}
										}
										
										$('#tag_list').html(tag_list_html);
										$('#filmrate').text(data.m_info_filmrate);
										$('#story_area').html(data.m_story);
										$('#user_rating').val(data.my_rating);
										// 						for(var i in data.comment_list){
										// 							console.log(data.comment_list[i].MEMBER_NAME);
										// 						}
										var comment_html = '';
										 
										for ( var i in data.comment_list) {
												
												comment_html += "<tr><td>";
												comment_html += data.comment_list[i].MEMBER_NAME;
												comment_html += "("+ data.comment_list[i].MEMBER_ID;
												comment_html += ")</td><td rowspan=\"2\" class=\"mid_line\" >";
												comment_html += data.comment_list[i].USEDWORDS_CONTENTS+ "</td></tr>";
												comment_html += "<tr ><td>"+ data.comment_list[i].USEDWORDS_TIMES+ "</td></tr>";
												comment_html += "<tr><td colspan=\"2\" class=\"line\"  style=\"border-bottom: 1px solid #f0f0f0;\" ></td> </tr>";
										}
										
										$('#comment').html(comment_html);

										},
										error : function(e) {
												alert(e);
										}
										});
								}
						

							});

			$(".close").click(function() {
				$("#myModal").css({
					"display" : "none"
				});
				$('#poster').html("");
				$('#title').text("");
				$('#eng_Title').text("");
				$('#event').text("");
				$('#step1').text("");
				$('#director').text("");
				$('#tag_list').text("");
				$('#actor').text("");
				$('#price_area').text("");
				$('#filmrate').text("");
				$('#story_area').html("");
				$('#user_rating').val("");
				$('#comment').html("");
			});
			
			$(".set_rank").click(function() {
				var rank = $("#rank_list").val();
				console.log(rank);
				if(rank == "non"){
					alert("랭크를 선택해 주세요.");
				}else{
					$.ajax({
						url : "/MidProject/GM/setRank?movie_number="+ movie_number+"&rank="+rank,
						success : function(data) {
							console.log(data);
							alert(data);
						},
						error : function(e) {
								alert(e);
						}
						});
				}
			});

			$("html").click(function(event) {
				if (event.target.id === "myModal") {
					$("#myModal").css({
						"display" : "none"
					});
					$('#poster').html("");
					$('#title').text("");
					$('#eng_Title').text("");
					$('#step1').text("");
					$('#price_area').text("");
					$('#tag_list').text("");
					$('#director').text("");
					$('#event').text("");
					$('#actor').text("");
					$('#filmrate').text("");
					$('#story_area').html("");
					$('#user_rating').val("");
					$('#comment').html("");
				}
			});

			$("#movie_watch").click(function() {
				console.log(movie_number);
				window.open(
				"/MidProject/movie_watch?movie_number="+movie_number,
				"영화시청",
				"width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
							});
			
			
			
			
			
			
			
			
// 			$(window).on('load',function(){
// 		        $('#eventModal').modal('show');
// 		    });//이벤트가 존재할 시 이벤트 사진을 띄워주는 모달이었으나 영화정보 띄워주는 모달과의 충돌로 삭제
			$('#btn-close-dialog').click(function() {
				$('#my-dialog,#dialog-background').hide();
		 	});
			$('#search').keypress(function(event) {//특정 키가 입력되었을때 발동하는 이벤트
				if(event.keyCode == 13){
					var selectsearch = $('#selectsearch').val();
					var text = $('#search').val();
					console.log(text);
					console.log(selectsearch);
					if(selectsearch != 0){
						search(selectsearch,text);
					}else if(selectsearch == 0){
						alert('카테고리를 선택해주세요');
					}
				}
			});
			
			function liketendencymovie(uid){
				console.log(uid);
				$.getJSON("/rating/user?uid="+uid,function(data){
					console.log(data);
//	 				$('#liketendencymovie').html
				});
			}
		function event() {
			$.ajax({
				url:"u_event",
				success: function(event) {
					console.log(event);
					if(event != null && event !=""){ 
						$('#modal-body').html('<li><img src="${resimg}/'+event+'"></li>');
					}else{
						$('#modal-body').html("특별한 이벤트가 없습니다.");
					}
				}
			});
		}	
		function search(selectsearch,text) {
			$.ajax({//검색을 ajax통신을 통해 비동기식으로 처리한다.
				url:"u_search?selectsearch="+selectsearch+"&search="+text,
				success: function(data) {
					console.log(data);
// 					var res = JSON.parse(data);
// 					console.log("res : ",res);
					var len = data.length;
					console.log(len);
					
					console.log(data[0]);
					$('#searchtarget').html(function() {
						var html="";
						var count = 0;
						
						if(len<6){
							html += "<div class=\"movies\"><table><tr><tr>";
				     			for(var i=0; i<len; i++){
				     				
				     				html += "<td style=\"width: 150;\"><table><tr align=\"center\">"
										 + "<td><div title=\""+data[count][1]+"\" id=\""+data[count][0]+"\">"
										 + "<img src=\""+data[count][2]+"\" title=\""+data[count][1]+"\" id=\""+data[count][0]+"\">"
										 + "</div></td></tr><tr align=\"center\">"
										 + "<td><div title=\""+data[count][1]+"\" id=\""+data[count][0]+"\">"+data[count][1]+"</div></td></tr></table><td>";
										 count++;
				     		}
				     			html += "</tr>";
						}else{
						html += "<div class=\"movies\"><table><tr>";
						for(var j=0; j<len/5; j++){
							html += "<tr>";
			     			for(var i=0; i<5; i++){
			     				if(count >= len){
			     					continue;
			     				}
			     				html += "<td style=\"width: 150;\"><table><tr align=\"center\">"
									 + "<td><div title=\""+data[count][1]+"\" id=\""+data[count][0]+"\">"
									 + "<img src=\""+data[count][2]+"\" title=\""+data[count][1]+"\" id=\""+data[count][0]+"\">"
									 + "</div></td></tr><tr align=\"center\">"
									 + "<td><div title=\""+data[count][1]+"\" id=\""+data[count][0]+"\">"+data[count][1]+"</div></td></tr></table><td>";
									 count++;
			     			}
			     			html += "</tr>";
			     		
			     		}
						}
						
						html += "</table></div>";
						
						$(document).ready(this);
						
						return html;
						});

				},
				error: function(error)
				   {
				     alert( "검색내용이 존재하지 않습니다."+error );
				   }
					});
				}
		});
 	</script>
<style>
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	position: fixed;
	width: auto;
	height: 100%;
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 100%; /* Could be more or less, depending on screen size */
	height: 100%;
	overflow: scroll;
}
/* The Close Button */
.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.info_spec {
	border-top: 1px solid #f0f0f0;
	border-bottom: 1px solid #f0f0f0;
}

.h_movie, .rating_area, #story_area, .info_spec, .comment_area {
	margin: 0 55px;
	margin-bottom: 30px;
}

html, body {
	overflow: auto;
}


#story_area, #comment .line {
	border-bottom: 1px solid #f0f0f0;
}
</style>
<body>

	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span>
			<div id="content">

				<div class="article" style="display: block;">
					<div class="mv_info">


						<!-- 영화정보(wide) -->



						<h3 class="h_movie">

							<p id="title"></p>

							<!-- N=a:ifo.title -->


							<strong id="eng_Title"> </strong>
						</h3>

						<table class="info_spec">
							<tr>
								<td colspan="3">
									<dl>
										<dt class="step1">
											<em>개요</em>
										</dt>
										<dd>
											<p id="step1"></p>
										</dd>
									</dl>
								</td>
								<td rowspan="4" align="right">
									<!-- 포스터 -->
									<div id="poster"></div> <!-- //포스터 -->
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<dl>
										<dt class="step2">
											<em>감독</em>
										</dt>
										<dd>
											<p id="director"></p>
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<dl>
										<dt class="step3">
											<em>출연</em>
										</dt>
										<dd>
											<p id="actor"></p>
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td>
									<dl>
										<dt class="step4">
											<em>등급</em>
										</dt>
										<dd>
											<p id="filmrate"></p>
										</dd>

									</dl>
								</td>
								<td colspan="2">

									<dl>
										<dt>
											<em>별점</em>
										</dt>
										<dd>
											<div class="rating_area">내 별점 : <input type="number" min="1" max="10" id="user_rating" readonly="readonly">
											</div>
										</dd>

									</dl>






								</td>
							</tr>
						</table>
						<div style="margin-left: 50px;" id="tag_list"></div>
						<div style="margin-left: 50px;">
						<table>
							<tr>
								<td><select id="rank_list" style="width: 100px; height: 30px">
									<option value="non">랭크 지정</option>
									<option id="rank_g" value="gold">금</option>
									<option id="rank_s" value="silver">은</option>
									<option id="rank_b" value="bronze">동</option>
								</select><input type="button" class="set_rank" value="추가" style="margin-left: 10px"></td>
								<td>
									<div id="event" style="margin-left: 30px;"></div><div id="price_area" style="margin-left: 30px;"></div>
								</td>
								<td><input type="button" id="movie_watch" value="영화 보기" style="width: 200px; height: 30px; margin-left: 50px;"></td>
							</tr>
						</table>
							
						</div>
						<div id="story_area"></div>
					</div>
					<div class="comment_area">
						<table id="comment">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>




	<section id="container">
		<!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<!--logo start-->
			<a href="./" class="logo"><b>Wa<span>flex</span></b></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
			</div>
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
				<li>
				<c:choose>
					<c:when test="${sessionScope.uid == null }">
					<!-- 세션에 데이터가 존재하는지 확인하여 회원가입 혹은 마이페이지로 분기시킨다. -->
						<a href="u_joinin" class="logout">회원가입</a>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${sessionScope.grade > 4 }"><!-- 유저의 등급에 따라 관리페이지 분기 -->
								<a href="index" class="logout">${sessionScope.uname}님 관리</a>
							</c:when>
							<c:otherwise><!-- 임시 링크. 추후 수정해야함. ks 190329 -->
								<a href="invoice" class="logout">${sessionScope.uname}님 유저</a>
							</c:otherwise>
						</c:choose>
					</c:otherwise>					
				</c:choose>
				</li>
				</ul>
				<ul class="nav pull-right top-menu">
					<li>
					<c:choose>
					<c:when test="${sessionScope.uid==null }">
					<!-- 로그인 시에 세션에 id와 이름을 저장해두므로 세션이 비어있다면 로그인버튼이 뜨고,
						세션에 값이 들어가있으면 로그아웃 버튼이 뜨게 만들어두었다. -->
						<a class="logout" href="u_login.html">LogIn</a>
					</c:when>
					<c:otherwise>
						<a class="logout" href="u_logout">LogOut</a>
					</c:otherwise>
				</c:choose>
					</li>
				</ul>
			</div>
			<div class="chat-room mt">
			<div class="chat-room-head" style="height: 30px">
			<select id="selectsearch" 
			style="width: 90px; height:30px; 
			background: #34495e;
			margin-top: 2px;">
             	<option value="0">---</option>
             	<option>장르</option>
             	<option>배우</option>
             	<option>제목</option>
             	<option>감독</option>
             </select>
             <input type="text" id="search" placeholder="Search" 
             style="background: #34495e; width: 250px; height:30px;
             padding:0 5px 0 35px; ">
              </div>
              </div>
		</header>
		<!--header end-->
		<!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height" style="background: black">
<!-- 				<H3> -->
<!-- 					<I CLASS="FA FA-ANGLE-RIGHT"></I>  -->
<!-- 				</H3> -->
				<br/><br/><br/><br/>
				<div id = "searchtarget">
				<div class="row mt" style="color:white; font-size: 25px;">
					<div class="col-lg-12">
						<p>인기영상 탑5</p>
						<div class="movies">
		<table>
			<tr>
				<c:forEach var="src_list" items="${list_popular}">
					<td style="width: 150;">
						<table>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">
								<img src="${src_list[2]}" title="${src_list[1]}" id="${src_list[0]}">
								</div></td>
							</tr>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">${src_list[1]}</div></td>
							</tr>
						</table>
					<td>
				</c:forEach>
			</tr>
		</table>
	</div>
						<br/>
					</div>
					<div class="col-lg-12">
						<p>상영중 탑5</p>
						<div class="movies">
		<table>
			<tr>
				<c:forEach var="src_list" items="${list_boxOffice}">
					<td style="width: 150;">
						<table>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">
								<img src="${src_list[2]}" title="${src_list[1]}" id="${src_list[0]}">
								</div></td>
							</tr>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">${src_list[1]}</div></td>
							</tr>
						</table>
					<td>
				</c:forEach>
			</tr>
		</table>
	</div>
						<br/>
					</div>
					<div class="col-lg-12">
						<p>최근조회</p>
						<div class="movies">
		<table>
			<tr>
				<c:forEach var="src_list" items="${list_lastviewed}">
					<td style="width: 150;">
						<table>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">
								<img src="${src_list[2]}" title="${src_list[1]}" id="${src_list[0]}">
								</div></td>
							</tr>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">${src_list[1]}</div></td>
							</tr>
						</table>
					<td>
				</c:forEach>
			</tr>
		</table>
	</div>
						<br/>
					</div>
					<div class="col-lg-12">
						<p>찜리스트</p>
						<div class="movies">
		<table>
			<tr>
				<c:forEach var="src_list" items="${list_saved}">
					<td style="width: 150;">
						<table>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">
								<img src="${src_list[2]}" title="${src_list[1]}" id="${src_list[0]}">
								</div></td>
							</tr>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">${src_list[1]}</div></td>
							</tr>
						</table>
					<td>
				</c:forEach>
			</tr>
		</table>
	</div>
						<br/>
					</div>
					<div class="col-lg-12">
						<p>유료추천</p>
						<div class="movies">
		<table>
			<tr>
				<c:forEach var="src_list" items="${list_pay}">
					<td style="width: 150;">
						<table>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">
								<img src="${src_list[2]}" title="${src_list[1]}" id="${src_list[0]}">
								</div></td>
							</tr>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">${src_list[1]}</div></td>
							</tr>
						</table>
					<td>
				</c:forEach>
			</tr>
		</table>
	</div>
						<br/>
					</div>
					<div class="col-lg-12">
						<p>무료추천</p>
						<div class="movies">
		<table>
			<tr>
				<c:forEach var="src_list" items="${list_free}">
					<td style="width: 150;">
						<table>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">
								<img src="${src_list[2]}" title="${src_list[1]}" id="${src_list[0]}">
								</div></td>
							</tr>
							<tr align="center">
								<td><div title="${src_list[1]}" id="${src_list[0]}">${src_list[1]}</div></td>
							</tr>
						</table>
					<td>
				</c:forEach>
			</tr>
		</table>
	</div>
<!-- 						<br/> -->
<!-- 					</div> -->
<!-- 					<div class="col-lg-12"> -->
<!-- 						<p>유저가 선호하는 영화와 비슷한 영화들</p> -->
<!-- 						<div id="likegenremovie">10개</div> -->
<!-- 						<br/> -->
<!-- 					</div> -->
<!-- 					<div class="col-lg-12"> -->
<!-- 						<p>비슷한 성향의 유저가 많이 본 영화들</p> -->
<!-- 						<div id="liketendencymovie">10개</div> -->
<!-- 						<br/> -->
<!-- 					</div> -->
<!-- 유저분석 알고리즘을 통해 분석된 결과에서 영화리스트를 받아와서 출력하는 장소... 알고리즘의 미구현으로 주석처리 -->
				</div>
				</div>
				<!-- Modal 이벤트 테이블과 연동하여 이벤트가 있으면 모달창에 띄워준다. 미완성 -->
				<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"
					tabindex="-1" id="eventModal" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">EVENT</h4>
							</div>
							<div class="modal-body" id="modal-body">
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default"
									type="button">Cancel</button>
							</div>
						</div>
					</div>
				</div>
				<!-- modal -->
			</section>
			<!-- /wrapper -->
		</section>
		<!-- /MAIN CONTENT -->
		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				<p>
					&copy; Copyrights <strong>Dashio</strong>. All Rights Reserved
				</p>
				<div class="credits">
					<!--
            You are NOT allowed to delete the credit link to TemplateMag with free version.
            You can delete the credit link only if you bought the pro version.
            Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/dashio-bootstrap-admin-template/
            Licensing information: https://templatemag.com/license/
          -->
					Created with Dashio template by <a href="https://templatemag.com/">TemplateMag</a>
				</div>
				<a href="blank.html#" class="go-top"> <i class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
		<!--footer end-->
	</section>
	
</body>

</html>