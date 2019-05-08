<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">

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
<link rel="stylesheet" type="text/css" href="css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="lib/gritter/css/jquery.gritter.css" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">
<script src="lib/chart-master/Chart.js"></script>

<!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>
	<section id="container">
		<!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="Toggle Navigation"></div>
			</div>
			<!--logo start-->
			<a href="index.html" class="logo"><b>DASH<span>IO</span></b></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  notification start -->
				<ul class="nav top-menu">
					<!-- settings start -->
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="index.html#"> <i
							class="fa fa-tasks"></i> <span class="badge bg-theme">4</span>
					</a>
						<ul class="dropdown-menu extended tasks-bar">
							<div class="notify-arrow notify-arrow-green"></div>
							<li>
								<p class="green">You have 4 pending tasks</p>
							</li>
							<li><a href="index.html#">
									<div class="task-info">
										<div class="desc">Dashio Admin Panel</div>
										<div class="percent">40%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 40%">
											<span class="sr-only">40% Complete (success)</span>
										</div>
									</div>
							</a></li>
							<li><a href="index.html#">
									<div class="task-info">
										<div class="desc">Database Update</div>
										<div class="percent">60%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100" style="width: 60%">
											<span class="sr-only">60% Complete (warning)</span>
										</div>
									</div>
							</a></li>
							<li><a href="index.html#">
									<div class="task-info">
										<div class="desc">Product Development</div>
										<div class="percent">80%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-info" role="progressbar"
											aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
											style="width: 80%">
											<span class="sr-only">80% Complete</span>
										</div>
									</div>
							</a></li>
							<li><a href="index.html#">
									<div class="task-info">
										<div class="desc">Payments Sent</div>
										<div class="percent">70%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="70" aria-valuemin="0"
											aria-valuemax="100" style="width: 70%">
											<span class="sr-only">70% Complete (Important)</span>
										</div>
									</div>
							</a></li>
							<li class="external"><a href="#">See All Tasks</a></li>
						</ul></li>
					<!-- settings end -->
					<!-- inbox dropdown start-->
					<li id="header_inbox_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<i class="fa fa-envelope-o"></i> <span class="badge bg-theme">5</span>
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-green"></div>
							<li>
								<p class="green">You have 5 new messages</p>
							</li>
							<li><a href="index.html#"> <span class="photo"><img
										alt="avatar" src="img/ui-zac.jpg"></span> <span class="subject">
										<span class="from">Zac Snider</span> <span class="time">Just
											now</span>
								</span> <span class="message"> Hi mate, how is everything? </span>
							</a></li>
							<li><a href="index.html#"> <span class="photo"><img
										alt="avatar" src="img/ui-divya.jpg"></span> <span
									class="subject"> <span class="from">Divya Manian</span>
										<span class="time">40 mins.</span>
								</span> <span class="message"> Hi, I need your help with this. </span>
							</a></li>
							<li><a href="index.html#"> <span class="photo"><img
										alt="avatar" src="img/ui-danro.jpg"></span> <span
									class="subject"> <span class="from">Dan Rogers</span> <span
										class="time">2 hrs.</span>
								</span> <span class="message"> Love your new Dashboard. </span>
							</a></li>
							<li><a href="index.html#"> <span class="photo"><img
										alt="avatar" src="img/ui-sherman.jpg"></span> <span
									class="subject"> <span class="from">Dj Sherman</span> <span
										class="time">4 hrs.</span>
								</span> <span class="message"> Please, answer asap. </span>
							</a></li>
							<li><a href="index.html#">See all messages</a></li>
						</ul></li>
					<!-- inbox dropdown end -->
					<!-- notification dropdown start-->
					<li id="header_notification_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<i class="fa fa-bell-o"></i> <span class="badge bg-warning">7</span>
					</a>
						<ul class="dropdown-menu extended notification">
							<div class="notify-arrow notify-arrow-yellow"></div>
							<li>
								<p class="yellow">You have 7 new notifications</p>
							</li>
							<li><a href="index.html#"> <span
									class="label label-danger"><i class="fa fa-bolt"></i></span>
									Server Overloaded. <span class="small italic">4 mins.</span>
							</a></li>
							<li><a href="index.html#"> <span
									class="label label-warning"><i class="fa fa-bell"></i></span>
									Memory #2 Not Responding. <span class="small italic">30
										mins.</span>
							</a></li>
							<li><a href="index.html#"> <span
									class="label label-danger"><i class="fa fa-bolt"></i></span>
									Disk Space Reached 85%. <span class="small italic">2
										hrs.</span>
							</a></li>
							<li><a href="index.html#"> <span
									class="label label-success"><i class="fa fa-plus"></i></span>
									New User Registered. <span class="small italic">3 hrs.</span>
							</a></li>
							<li><a href="index.html#">See all notifications</a></li>
						</ul></li>
					<!-- notification dropdown end -->
				</ul>
				<!--  notification end -->
			</div>
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="login.html">Logout</a></li>
				</ul>
			</div>
		</header>
		<!--header end-->
		<!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered"><a href="profile.html"><img src="img/ui-sam.jpg" class="img-circle" width="80"></a></p>
          <h5 class="centered">Sam Soffes</h5>
          <li class="mt">
            <a href="index.html">
              <i class="fa fa-dashboard"></i>
              <span>종합사이트 분석</span>
              </a>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-pencil"></i>
              <span>로그 정보</span>
              </a>
            <ul class="sub">
              <li><a href="search_log.html">검색 로그 정보</a></li>
              <li><a href="view_log.html">조회 로그 정보</a></li>
              <li><a href="whatch_log.html">시청 로그 정보</a></li>
              <li><a href="movie_sold_log.html">영화 판매 로그 정보</a></li>
              <li><a href="login_log.html">로그인 로그 정보</a></li>
            </ul>
          </li>
          <li class="sub-menu">
            <a href="admin_MemberInfo.html">
              <i class="fa fa-users"></i>
              <span>회원정보</span>
              </a>
            <!-- <ul class="sub">
              <li><a href="grids.html">Grids</a></li>
              <li><a href="calendar.html">Calendar</a></li>
              <li><a href="gallery.html">Gallery</a></li>
              <li><a href="todo_list.html">Todo List</a></li>
              <li><a href="dropzone.html">Dropzone File Upload</a></li>
              <li><a href="inline_editor.html">Inline Editor</a></li>
              <li><a href="file_upload.html">Multiple File Upload</a></li>
            </ul> -->
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-toggle-right"></i>
              <span>영화정보</span>
              </a>
            <ul class="sub">
              <li><a href="blank5.html">영화 관리 페이지</a></li>
              
            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-bar-chart-o"></i>
              <span>통계</span>
              </a>
            <ul class="sub">
              <li><a href="form_component.html">Form Components</a></li>
              <li><a href="advanced_form_components.html">Advanced Components</a></li>
              <li><a href="form_validation.html">Form Validation</a></li>
              <li><a href="contactform.html">Contact Form</a></li>
            </ul>
          </li>        
          <li class="sub-menu">
			 <a href="javascript:;">
              <i class="fa fa-money"></i>
              <span>수익률 관리</span>
              </a>
            <ul class="sub">
              <li><a href="tag1.html">참고 태그</a></li>
               <li><a href="movie_ma.html">영화 관리</a></li>
            </ul>
          </li>
          
          <li class="sub-menu">
             <a href="javascript:;">
              <i class=" fa fa-desktop"></i>
              <span>홈페이지 운영</span>
              </a>
            <ul class="sub">
              <li><a href="notice_ma.html">공지사항 관리</a></li>
              <li><a href="event_ma.html">이벤트 관리</a></li>
              <li ><a href="tag_ma.html">태그 관리</a></li>

            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class=" fa fa-paste"></i>
              <span>판권 분석</span>
              </a>
            <ul class="sub">
              <li><a href="blank.html">분석페이지</a></li>
              
            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-comments-o"></i>
              <span>민원 관리</span>
              </a>
            <ul class="sub">
              <li><a href="complaint.html">민원 등록</a></li>
            </ul>
          </li>
          <li>
            <a href="google_maps.html">
              <i class="fa fa-edit"></i>
              <span>매출 예측</span>
              </a>
          </li>
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
		<!--sidebar end-->
		<!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-9 main-chart">
						<!--CUSTOM CHART START -->
						<div class="border-head">
							<h3>관리자 대시보드</h3>
						</div>
						<div class="content-panel">
							<div class="panel-body text-center">
								<div id="chartContainer" style="height: 300px;"></div>
								<div class="btn-group">
									<button type="button" class="btn btn-theme dropdown-toggle"
										data-toggle="dropdown" id="periodbtn" style="float: left;">
										기간선택 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu" id="period">
										<li><a href="#" data-value="0">일별</a></li>
										<li><a href="#" data-value="1">월별</a></li>
										<li><a href="#" data-value="2">년도별</a></li>
									</ul>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-theme dropdown-toggle"
										data-toggle="dropdown" id="typebtn" style="float: left;">
										그래프선택<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu" id="type">
										<li><a href="#" data-value="0">바 그래프</a></li>
										<li><a href="#" data-value="1">도넛 그래프</a></li>
										<li><a href="#" data-value="2">라인 그래프</a></li>
									</ul>
								</div>

							</div>
						</div>
						<!--custom chart end-->
						<div class="row mt">
							<!-- SERVER STATUS PANELS -->
							<div class="col-md-4 col-sm-4 mb">
								<div class="grey-panel pn donut-chart">
									<div class="darkblue-panel pn">
										<div class="darkblue-header">
											<h5 class="iconheader">총 방문자수</h5>
										</div>
										<h1 class="mt">
											<i class="fa fa-user icon fa-3x" id="usericon"
												style="cursor: pointer;"></i>
										</h1>
										<p>+ ${loginlogcount_currentdate } 새로운 방문자</p>
										<footer>
											<div class="centered">
												<h5>
													<i class="fa fa-sign-in"></i> ${loginlogcount_all }
												</h5>
											</div>
										</footer>
									</div>
								</div>
								<!-- /grey-panel -->
							</div>
							<!-- /col-md-4-->
							<div class="col-md-4 col-sm-4 mb">
								<div class="grey-panel pn donut-chart">
									<div class="grey-header">
										<h5 class="iconheader">총 판매금액</h5>
									</div>
									<h1 class="mt">
										<i class="fa fa-money fa-3x icon" style="cursor: pointer;"
											id="moneyicon"></i>
									</h1>
									<p>+ ${moviesoldlogcount_currentdate } 수익</p>
									<footer>
										<div class="centered">
											<h5>
												<i class="fa fa-won"></i> ${moviesoldlogcount_all }
											</h5>
										</div>
									</footer>
								</div>
								<!--  /darkblue panel -->
							</div>
							<!-- /col-md-4 -->
							<div class="col-md-4 col-sm-4 mb">
								<!-- REVENUE PANEL -->
								<div class="green-panel pn">
									<div class="green-header">
										<h5 class="iconheader">총 조회수</h5>

									</div>
									<h1 class="mt">
										<i class="fa fa-th-list fa-3x icon" style="cursor: pointer;"
											id="viewicon"></i>
									</h1>
									<p class="mt">
										<b>+ ${viewlogcount_currentdate } 회</b>
									<footer>
										<div class="centered">
											<h5>
												<i class="fa fa-check"></i> ${viewlogcount_all }
											</h5>
										</div>
									</footer>
								</div>
							</div>
							<!-- /col-md-4 -->
						</div>
						<!-- /row -->
						<div class="row">
							<!-- WEATHER PANEL -->
							<div class="col-md-4 col-sm-4 mb">
								<div class="weather-3 pn centered">
									<i class="fa fa-play-circle icon" style="cursor: pointer;"
										id="watchicon"></i>
									<h1 class="iconheader">총 시청수</h1>
									<div class="info">
										<div class="row">
											<h3 class="centered">${watchlogcount_all }회</h3>
											<div class="col-sm-6 col-xs-6 pull-left"></div>
											<div class="col-sm-6 col-xs-6 pull-right">
												<p class="goright">
													<i class="fa fa-play"></i>+${ watchlogcount_currentdate}회
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- /col-md-4-->
							<!-- DIRECT MESSAGE PANEL -->
							<div class="col-md-4 col-sm-4 mb">
								<div class="content-panel pn">
									<div id="spotify">
										<div class="col-xs-4 col-xs-offset-8">
											<button class="btn btn-sm btn-clear-g icon" id="signupicon">Go
												Chart!</button>
										</div>
										<div class="sp-title">
											<h3 class="iconheader">총 가입수</h3>
										</div>
										<div class="play">
											<i class="fa fa-mail-forward"> +
												${joinlogcount_currentdate }명</i>
										</div>
									</div>
									<p class="followers">
										<i class="fa fa-user"></i> ${joinlogcount_all } 가입자수
									</p>
								</div>
								<!-- /Message Panel-->
							</div>
							<div class="col-md-4 col-sm-4 mb">
								<div class="weather-3 pn centered">
									<div class="gm_chart" style="width: 100%; height: 100%;"></div>
								</div>


							</div>
							<!-- /col-md-8  -->
						</div>

						<!-- /row -->

						<!-- /row -->
						<div class="content-panel">
							<div class="panel-body text-center">
								<table class="table table-hover">
									<div>
										<h4>
											<i class="fa fa-angle-right"></i> 판권 수익
										</h4>
										<button class="btn btn-sm btn-clear-g icon" id="profiticon">Go
											Chart!</button>
									</div>
									<thead>
										<tr>
											<th></th>
											<th>판권 구매</th>
											<th align="right">수익</th>
											<th align="right">수익률</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>이전달</td>
											<td>
												<table class="table table-hover">
													<tr>
														<td>판권 개수</td>
														<td>${purchase_pastmonth }개</td>
													</tr>
													<tr>
														<td>판권 구매액</td>
														<td>${copyrightcost_pastmonth}원</td>
												</table>
											</td>
											<td align="left">${investment_pastmonth }원</td>
											<td align="left">${networth_pastmonth }%</td>
										</tr>
										<tr>
											<td>이번달</td>
											<td>
												<table class="table table-hover">
													<tr>
														<td>판권 개수</td>
														<td>${purchase_thismonth }개</td>
													</tr>
													<tr>
														<td>판권 구매액</td>
														<td>${copyrightcost_thismonth}원</td>
												</table>
											</td>
											<td align="left">${investment_thismonth }원</td>
											<td align="left">${networth_thismonth }%</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /col-lg-9 END SECTION MIDDLE -->
					<!-- **********************************************************************************************************************************************************
              RIGHT SIDEBAR CONTENT
              *********************************************************************************************************************************************************** -->
					<div class="col-lg-3 ds">
						<!--COMPLETED ACTIONS DONUTS CHART-->
						<div class="donut-main">
							<h4>사이트 위험도</h4>
							<canvas id="riskPie" height="130" width="130"></canvas>
							<script>
								var redval = '${riskPieRed}';
								var whiteval = '${riskPieWhite}';
								var pieData = [ {
									value : parseInt(redval),
									color : "#f68275"
								}, {
									value : parseInt(whiteval),
									color : "#fdfdfd"
								} ];
								var myPie = new Chart(document.getElementById(
										"riskPie").getContext("2d"))
										.Pie(pieData);
							</script>
							<h4>${riskPieRed}%</h4>
						</div>
						<!--NEW EARNING STATS -->

						<!--new earning end-->
						<!-- RECENT ACTIVITIES SECTION -->
						
						<!-- USERS ONLINE SECTION -->

						<!-- CALENDAR-->
						<div id="calendar" class="mb">
							<div class="panel green-panel no-margin">
								<div class="panel-body">
									<div id="date-popover" class="popover top"
										style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
										<div class="arrow"></div>
										<h3 class="popover-title" style="disadding: none;"></h3>
										<div id="date-popover-content" class="popover-content"></div>
									</div>
									<div id="my-calendar"></div>
								</div>
							</div>
						</div>
						<!-- / calendar -->
					</div>
					<!-- /col-lg-3 -->
				</div>
				<!-- /row -->
			</section>
		</section>
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
				<a href="index.html#" class="go-top"> <i class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
		<!--footer end-->
	</section>
	<!-- js placed at the end of the document so the pages load faster -->
	<script src="lib/jquery/jquery.min.js"></script>

	<script src="lib/bootstrap/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="lib/jquery.dcjqaccordion.2.7.js"></script>
	<script src="lib/jquery.scrollTo.min.js"></script>
	<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="lib/jquery.sparkline.js"></script>
	<!--common script for all pages-->
	<script src="lib/common-scripts.js"></script>
	<script type="text/javascript" src="lib/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="lib/gritter-conf.js"></script>
	<!--script for this page-->
	<script src="lib/sparkline-chart.js"></script>
	<script src="lib/zabuto_calendar.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			
			
			
			
			
			
			
			
			
			
			var unique_id = $.gritter.add({
				// (string | mandatory) the heading of the notification
				title : '와플렉스 관리자페이지입니다.',
				// (string | mandatory) the text inside the notification
				text : '오늘은 어떤 관리를 하실 예정이십니까?',
				// (string | optional) the image to display on the left
				image : 'img/ui-sam.jpg',
				// (bool | optional) if you want it to fade out on its own or just sit there
				sticky : false,
				// (int | optional) the time you want it to be alive for before fading out
				time : 8000,
				// (string | optional) the class name you want to apply to that specific message
				class_name : 'my-sticky-class'
			});

			return false;
		});
	</script>
	<script type="application/javascript">
		
		
		
			
    $(document).ready(function() {
    	
    	

		var chartdata = [];
		
		$.getJSON('/MidProject/GM/getchart_data',function(data){
			console.log(data);
			for (key in data) {
				console.log(data[key]);
				var k = "";
				if(key == "age_10s"){
					k="10대";
				}else if(key == "age_20s"){
					k="20대";
				}else if(key == "age_30s"){
					k="30대";
				}else if(key == "age_40s"){
					k="40대";
				}else if(key == "age_50s"){
					k="50대";
				}else if(key == "age_60s"){
					k="60대";
				}else if(key == "age_70s"){
					k="70대";
				}else if(key == "age_80s"){
					k="80대";
				}else if(key == "age_90s"){
					k="90대";
				}
				if(data[key] != 0){
					chartdata.push([k,data[key]]);
				}
				
			}
			console.log(chartdata);

			$('.gm_chart').highcharts({
				chart:{
					type:'pie'
					//column,bar,line
				},
				title:{
					text:"연령대별 접속자"
				},
				tooltip: {
			        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			    },
			    plotOptions: {
			        pie: {
			            allowPointSelect: true,
			            cursor: 'pointer',
			            events: {
			                click: function (e) {
			                	console.log(e);
// 			                    		e.point.x
			                }
			            },
			            dataLabels: {
			                enabled: true,
			                format: '<b>{point.name}</b>: {point.options.y}',
			                style: {
			                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                }
			            }
			        }
			    },
				
				 series: [{
	                  name: 'per',
	                  colorByPoint: true,
	                  sliced: true,
                      selected: true,
	                  data: 
	                      chartdata
	              }]
			});
		});		
    	
    	
    	
      $("#date-popover").popover({
        html: true,
        trigger: "manual"
      });
      $("#date-popover").hide();
      $("#date-popover").click(function(e) {
        $(this).hide();
      });

      $("#my-calendar").zabuto_calendar({
        action: function() {
          return myDateFunction(this.id, false);
        },
        action_nav: function() {
          return myNavFunction(this.id);
        },
        ajax: {
          url: "show_data.php?action=1",
          modal: true
        },
        legend: [{
            type: "text",
            label: "Special event",
            badge: "00"
          },
          {
            type: "block",
            label: "Regular event",
          }
        ]
      });
    });

    function myNavFunction(id) {
      $("#date-popover").hide();
      var nav = $("#" + id).data("navigation");
      var to = $("#" + id).data("to");
      console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
  
		
	
	
	</script>
</body>
<!-- canvasJS 실행을 위한 부분 시작 -->
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="lib/canvasjs-conf2.js" type="text/javascript"></script>
<script>
	start();
</script>
<!-- canvasJS 실행을 위한 부분 끝 -->

<!-- <!-- Tocplus 15.1 채팅봇 설정 시작--> -->
<!-- <script type="text/javascript"> -->
// 	tocplusTop = 100;
// 	tocplusLeft = 1;
// 	tocplusMinimizedImage = 'http://kr07.tocplus007.com/img/minimized_ko.gif';
// 	tocplusHAlign = 'right';
// 	tocplusWidth = 180;
// 	tocplusHeight = 220;
// 	tocplusUserName = '${sessionScope.uname}';
// 	tocplusFrameColor = '#808080';
// 	tocplusFloatingWindow = true;
// 	var tocplusHost = (("https:" == document.location.protocol) ? "https://"
// 			: "http://");
// 	document
// 			.write(unescape("%"
// 					+ "3Cscript src='"
// 					+ tocplusHost
// 					+ "kr07.tocplus007.com/chatLoader.do?userId=terryaa' type='text/javascript'"
// 					+ "%" + "3E" + "%" + "3C/script" + "%" + "3E"));
<!-- </script> -->
<!-- End of Tocplus 채팅봇 설정 끝 -->
<script>
	$("select.country").change(function() {
		var selectedCountry = $(this).children("option:selected").val();
		alert("You have selected the country - " + selectedCountry);
	});

	$(function() {
		$("#btn-upload").on("click", function() {
			var form = new FormData(document.getElementById('uploadForm'));
			$.ajax({
				url : "/MidProject/upload",
				data : form,
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(response) {
					aleat(response + "success");
					console.log(response);
				},
				error : function(jqXHR) {
					alert(jqXHR.responseText + "faile");
				}
			});

		});
	});
</script>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<!-- <script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script> -->
<!-- <script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script> -->
</html>
