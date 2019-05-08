<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

  
 <script>
 $(document).ready(function(){

	 
 

 $('#btn4').on('click', function(){
   
	 
	 //분석시 분석 결과에 출력하기 위한 FUNCTION
	 var form = {
			 analysistitle: $('#analysistitle').val(),
    		 analysisprice : $('#analysisprice').val(),
    		 analysistag : $('#analysistag').val()
     }
	 
	 console.log($('#analysistag').val());
	 var tags = $('#analysistag').val();
	 var txt1 = "분석 영화 제목 : "
	 var txt2 = "분석 영화 판권 금액 : "
	 var txt3 = "분석 영화 태그 정보 : "
     $.ajax({
         url: "/MidProject/rest/getdata?tags="+tags,
         contentType: "application/json; charset=UTF-8;",
         success: function(data){
        	 var p = [10000,5000,2500,1000];
        	 console.log(data.length-1);
        	 console.log(data);
        	 
//         	 $('#analysisresult').append(txt1);
// 			var res = $('#analysisresult');
        	var val_html = "";
        	var total_pay = 0;
        	
        	for(var j=0; j<4; j++){
     			for(var i=0; i<data.length; i++){
     				total_pay += data[i][j] ;
     			}
     		}
        	
        	console.log("total : ",total_pay);
        	val_html="<table class=\"price_table\"><tr class=\"b_tr\" style=\"margin-bottom: 10px;\"><td class=\"r_td\" style=\"margin-right: 10px;\"></td>";
        	console.log(data.length-1);
        	for(var i=0; i<data.length; i++){
        		val_html += "<td>"+(data.length-i)+"개 일치</td>";
 			}
        	val_html += "</tr>";
        	for(var j=0; j<4; j++){
        		val_html += "<tr><td class=\"r_td\" style=\"margin-right: 10px;\">"+p[j]+"</td>";
     			for(var i=0; i<data.length; i++){
     				val_html += "<td>"+ data[i][j] +"</td>";
     			}
     			val_html +="</tr>";
     		
     		}
        	val_html +="</table><br><p class=\"p_tot\">Total : "+total_pay+"</p>";
        	 $('.res_price').html(val_html);
        	 
//         	 $(".price_table .b_tr").css("padding","20px");
//         	 $(".price_table .r_td").css("margin-right","10px");
        	 
        	 $(".price_table tr").css("padding","10px");
        	 $(".price_table td").css("padding","10px");
        	 
        	 $(".price_table tr").css("border","1px solid black");
        	 $(".price_table td").css("border","1px solid black");
        	 
        	 $(".price_table td").css("font-size","20px");
        	 $(".p_tot").css("font-size","20px");
        	 
         },
         error: function(){
             alert("태그를 입력해 주세요");
         }
     });
 });
 });
 
   </script>



<body>
  <section id="container">
  
  
    <!-- **********************************************************************************************************************************************************
  
  
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <table>
    <tr>
    <td colspan="3"></td></tr>
    </table>
    <header class="header black-bg">
      <div class="sidebar-toggle-box" style="border-collapse: collapse; ">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="index.html" class="logo"><b>DASH<span>IO</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
          <!-- settings start -->
          <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
              <i class="fa fa-tasks"></i>
              <span class="badge bg-theme">4</span>
              </a>
            <ul class="dropdown-menu extended tasks-bar">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">You have 4 pending tasks</p>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Dashio Admin Panel</div>
                    <div class="percent">40%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                      <span class="sr-only">40% Complete (success)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Database Update</div>
                    <div class="percent">60%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                      <span class="sr-only">60% Complete (warning)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Product Development</div>
                    <div class="percent">80%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                      <span class="sr-only">80% Complete</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Payments Sent</div>
                    <div class="percent">70%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">
                      <span class="sr-only">70% Complete (Important)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li class="external">
                <a href="#">See All Tasks</a>
              </li>
            </ul>
          </li>
          <!-- settings end -->
          <!-- inbox dropdown start-->
          <li id="header_inbox_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
              <i class="fa fa-envelope-o"></i>
              <span class="badge bg-theme">5</span>
              </a>
            <ul class="dropdown-menu extended inbox">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">You have 5 new messages</p>
              </li>
              <li>
                <a href="index.html#">
                  <span class="photo"><img alt="avatar" src="img/ui-zac.jpg"></span>
                  <span class="subject">
                  <span class="from">Zac Snider</span>
                  <span class="time">Just now</span>
                  </span>
                  <span class="message">
                  Hi mate, how is everything?
                  </span>
                  </a>
              </li>
              <li>
                <a href="index.html#">
                  <span class="photo"><img alt="avatar" src="img/ui-divya.jpg"></span>
                  <span class="subject">
                  <span class="from">Divya Manian</span>
                  <span class="time">40 mins.</span>
                  </span>
                  <span class="message">
                  Hi, I need your help with this.
                  </span>
                  </a>
              </li>
              <li>
                <a href="index.html#">
                  <span class="photo"><img alt="avatar" src="img/ui-danro.jpg"></span>
                  <span class="subject">
                  <span class="from">Dan Rogers</span>
                  <span class="time">2 hrs.</span>
                  </span>
                  <span class="message">
                  Love your new Dashboard.
                  </span>
                  </a>
              </li>
              <li>
                <a href="index.html#">
                  <span class="photo"><img alt="avatar" src="img/ui-sherman.jpg"></span>
                  <span class="subject">
                  <span class="from">Dj Sherman</span>
                  <span class="time">4 hrs.</span>
                  </span>
                  <span class="message">
                  Please, answer asap.
                  </span>
                  </a>
              </li>
              <li>
                <a href="index.html#">See all messages</a>
              </li>
            </ul>
          </li>
          <!-- inbox dropdown end -->
          <!-- notification dropdown start-->
          <li id="header_notification_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
              <i class="fa fa-bell-o"></i>
              <span class="badge bg-warning">7</span>
              </a>
            <ul class="dropdown-menu extended notification">
              <div class="notify-arrow notify-arrow-yellow"></div>
              <li>
                <p class="yellow">You have 7 new notifications</p>
              </li>
              <li>
                <a href="index.html#">
                  <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                  Server Overloaded.
                  <span class="small italic">4 mins.</span>
                  </a>
              </li>
              <li>
                <a href="index.html#">
                  <span class="label label-warning"><i class="fa fa-bell"></i></span>
                  Memory #2 Not Responding.
                  <span class="small italic">30 mins.</span>
                  </a>
              </li>
              <li>
                <a href="index.html#">
                  <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                  Disk Space Reached 85%.
                  <span class="small italic">2 hrs.</span>
                  </a>
              </li>
              <li>
                <a href="index.html#">
                  <span class="label label-success"><i class="fa fa-plus"></i></span>
                  New User Registered.
                  <span class="small italic">3 hrs.</span>
                  </a>
              </li>
              <li>
                <a href="index.html#">See all notifications</a>
              </li>
            </ul>
          </li>
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
             <a class="active" href="javascript:;">
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
      <section class="wrapper site-min-height">
     
     
     
     
 
 
 
 
     
     <!-- 헤더같은 역할 -->
     	 <div id="edit" class="tab-pane">
                    <div class="row">
                      <div class="col-lg-8 col-lg-offset-2 detailed">
                        <h4 class="mb">영화 관리 페이지</h4>
     </div>
     </div>
     </div>
     
     
     
     
	<!-- 분석에 대한 div.  -->
	 <div id="edit" class="tab-pane">
                    <div class="row">
                      <div class="col-lg-8 col-lg-offset-2 detailed">
                        <h4 class="mb">분석 대상 정보</h4>
                        <form role="form" class="form-horizontal">
                          <div class="form-group">
                            <label class="col-lg-2 control-label" id="">분석 영화 제목</label>
                            <div class="col-lg-6">
                              <input type="text" placeholder=" " id="analysistitle" class="form-control">
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-lg-2 control-label" id="">분석 영화 판권 구매금액</label>
                            <div class="col-lg-6">
                              <input type="text" placeholder=" " id="analysisprice" class="form-control">
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-lg-2 control-label" id="">분석 영화 태그 정보</label>
                            <div class="col-lg-6">
                              <input type="text" placeholder=" " id="analysistag" class="form-control" value="C0000004,C0000048,10028667">
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-lg-2 control-label">분석 결과</label>
                            <div class="res_price" style="margin-left: 50px;">
<!--                               <textarea rows="10" cols="30" class="form-control" id="analysisresult" name="analysisresult"></textarea> -->
                            </div>
                          </div>
                        </form>
                      </div>
                     
                      <div class="col-lg-8 col-lg-offset-2 detailed mt">
                        <form role="form" class="form-horizontal">
                         
                          <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                       
                              <button class="btn btn-theme04" type="button" id="btn4">분석하기</button>
                            </div>
                          </div>
                        </form>
                      </div>
                      <!-- /col-lg-8 -->
                    </div>
                    <!-- /row -->
                  </div>
                  <!-- /tab-pane -->
                 
	
	
	<!-- 분석에 대한 div END.  -->
	
	
	
	
	<!-- 경계선 -->
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
    
    <!--footer end-->
  </section>



  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <!--script for this page-->
  <script src="lib/jquery-ui-1.9.2.custom.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap-fileupload/bootstrap-fileupload.js"></script>
  <script type="text/javascript" src="lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/moment.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
  <script src="lib/advanced-form-components.js"></script>



</body>

</html>
