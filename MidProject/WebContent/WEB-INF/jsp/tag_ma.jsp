 <%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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
  <link href="lib/advanced-datatable/css/demo_page.css" rel="stylesheet" />
  <link href="lib/advanced-datatable/css/demo_table.css" rel="stylesheet" />
  <link rel="stylesheet" href="lib/advanced-datatable/css/DT_bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-fileupload/bootstrap-fileupload.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-datepicker/css/datepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-daterangepicker/daterangepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-timepicker/compiled/timepicker.css" />
  
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <script src="lib/advanced-form-components.js"></script>
  <!--script for this page-->
  <script src="lib/jquery-ui-1.9.2.custom.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap-fileupload/bootstrap-fileupload.js"></script>
  <script type="text/javascript" src="lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/moment.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
 
  
  
  
<!--   <script type="text/javascript" language="javascript" src="lib/advanced-datatable/js/jquery.js"></script> -->
  <script type="text/javascript" language="javascript" src="lib/advanced-datatable/js/jquery.dataTables.js"></script>
  <script type="text/javascript" src="lib/advanced-datatable/js/DT_bootstrap.js"></script>
  
  <script>
    /* Formating function for row details */

		$(document).ready(function(){
			$('#btn_tag').click(function(){
			

            $.ajax({
            	url:"inserttag.do",
                        success: function(result){
                        	
                        },
                        error: function (e) {
                            console.log("ERROR : ", e);
                        }
                });
         
	      });
			
			

  		
  		var selected;	
		var tag_type;
		$('#tag_in_type li a').on('click',function(e){
			tag_type = $(this).data('value');
			$("#tag_type_btn").text($(this).text());
		});
			$('#tag_in_moviename_btn').click(function(){
			var tag_name = $('#tag_ex_name').val();
			$.ajax({
				url:"tag_chk?tag_name="+tag_name+"&tag_type="+tag_type,
				success:function(data){
				$("#tag_in_moviename_modal").modal();
				console.log(data);
				document.getElementById("tag_in_moviename_search").innerHTML = data;

			
			
			$("#tag_in_moviename_search").click(function(){
				selected = $(this).children("option:selected").val();
				console.log(selected);

});
			$('#insert_target_btn').click(function(){
				$("#tag_ex_name").val(selected);

					
			});
				

		  					}
		  				});

					 
				
			});
			
  
		});
		
//-----------------

    
    
 
  </script>
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
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
              <li><a href="tag_profit.html">참고 태그</a></li>
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
      <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> 홈페이지 운영 페이지</h3>
        


<div class="profit">
      
          <div class="col-lg-14">
            <div class="form-panel">

              	<h4>태그 관리</h4>
              	

              <div class="panel-heading">
                <ul class="nav nav-tabs nav-justified">
                  <li class="active">
                    <a data-toggle="tab" href="#high_profit">태그 삭제</a>
                  </li>
                  <li>
                    <a data-toggle="tab" href="#low_profit">자동 태그 업데이트</a>
                  </li>
                </ul>
              </div>

              <div class="panel-body">
                <div class="tab-content">
                  <div id="high_profit" class="tab-pane active">
					<!-- 					---------------------------------  -->

    <div class="col-md-12 mt">

              <h4>태그 관리</h4>
         


              <form action="tag_ex" method="get" class="form-horizontal style-form">

                
                 <div class="form-group">
                 
                	<label class="control-label col-md-3"></label>

              			<div class="btn-group" style="float: left;">
                			<button type="button" class="btn btn-theme dropdown-toggle" data-toggle="dropdown" id="tag_type_btn">
                  				종류 <span class="caret"></span>
                  			</button>
                				<ul class="dropdown-menu" role="menu" id="tag_in_type">
                 			 		<li><a data-value="0">영화제목</a></li>
                 				 	<li><a data-value="1">태그명</a></li>
                				</ul>
                				
                				
              			</div>
              			<div class="col-lg-3">
                    		<input type="text" placeholder="" id="tag_ex_name" class="form-control" name="tag_Table_Num">
                   				 <p class="help-block" >영화제목이나 태그를 입력해주세요</p>          
                		</div>
                

              			<input type="button" class="btn btn-success btn-md" id="tag_in_moviename_btn" value="조회">

              				<div class="modal fade" id="tag_in_moviename_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                			<div class="modal-dialog">
                  			<div class="modal-content">
                    		<div class="modal-header">
                      		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      		<h4 class="modal-title" id="myModalLabel">조회</h4>
                    		</div>
                   			<div class="modal-body">
                      			<select multiple class="form-control" multiple="multiple"  id="tag_in_moviename_search">
                				</select>
                				<p class="help-block" >----------</p>   
                    		</div>
                    		<div class="modal-footer">
                      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      		<button type="button" class="btn btn-primary" id="insert_target_btn" data-dismiss="modal">submit</button>
                    		</div>
                  			</div>
                			</div>
              				</div>

            	</div>
            	 
            
				  <input type="submit" class="btn btn-primary btn-lg" style="position: relative; left: 1380px;" value="삭제">
				  

                </form>


                </div>
              				

					

                  </div>

                  <div id="low_profit" class="tab-pane">
					


          <div class="col-md-12 mt">

 			<h4>자동태그 새로고침</h4>
         <button type="button" class="btn btn-success btn-lg" id="btn_tag" style="position: relative; left: 1300px;" >새로고침</button>
            </div>
          </div>

					
					
					
					


                </div>

          </div>
              	

                </div>
             


                </div>
                </div>

     
    

       
	

              	
              	
        

       
                </section>
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
        <a href="blank.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>
  <!-- js placed at the end of the document so the pages load faster -->
  


</html>
