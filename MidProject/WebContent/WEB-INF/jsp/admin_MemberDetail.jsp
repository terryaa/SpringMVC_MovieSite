<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:set var="resImg" value="/midproject/WebContent/img"/>
 --%> 
<!DOCTYPE html>
<form action="/admin_updateInfo" method="post" enctype="multipart/form-data"
	id="infoForm">
	<div style="margin-left: 20%;">
		<img src="img/${member.member_picture }">
	</div>
	<div>
		<ul>
			<li>��ȣ : ${member.member_number}</li>
			<li>���̵� : ${member.member_id }</li>
			<li>�̸� : ${member.member_name }</li>
			<li>��й�ȣ : ${member.member_password }</li>
			<li>���� : ${member.member_birth}</li>
			<li>ȸ�� ��� : ${member.member_grade }</li>
			<li>�� �����ݾ� : ${member.movie_sold_log[0].price.movie_real_price}</li>
			<li>�ֱ� �α��� : ${member.login_log[0].loginlog_logout }</li>
			<li>�� �湮Ƚ�� : ${member.login_log[0].loginlog_login }</li>
			<li>�� ��ûȽ�� : ${member.watch_log[0].watchlog_date }</li>
			<li>Ȱ��ȭ ���� : ${member.active_check_number }</li>
			
		</ul>
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
			data-target="#myModal" onclick="dataSetting()">����</button>
	</div>
	<input type="hidden" id="m_number" value="${member.member_number}">
	<input type="hidden" id="m_picture" value="img/${member.member_picture}">
	<input type="hidden" id="m_name" value="${member.member_name }">
	<input type="hidden" id="m_pass" value="${member.member_password }">
	<input type="hidden" id="m_birth" value="${member.member_birth }">
	<input type="hidden" id="m_grade" value="${member.member_grade }">
	<input type="hidden" id="m_active_check_number" value="${member.active_check_number }">
</form>
