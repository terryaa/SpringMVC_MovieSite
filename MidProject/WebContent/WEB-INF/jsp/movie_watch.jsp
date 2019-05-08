<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>movie_watch</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	$(document).ready(
			function() {
				var movie_number = ${movie_number};
				var rating = "";
				var text = "";
				console.log(movie_number);
				$('#submit').click(function() {
					
					
					
					rating = $("#rating").val();
					text = $("#text").val();
					console.log(rating);
					console.log(text);
							$.ajax({
								url : "/MidProject/GM/setM_data?movie_number="
										+ movie_number + "&rating=" + rating
										+ "&text=" + text,
								success : function(data) {
									console.log("입력완료");

								},
								error : function(e) {
									alert("입력실패");
								}

							});
						});
			});
</script>

</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center" height="300"><div
					style="width: 100; height: 100;">영화 나오는 곳!!</div></td>
		</tr>
		<tr>
			<td>별점 :</td>
			<td><input type="number" min="1" max="10" id="rating" value="1"></td>
		</tr>
		<tr>
			<td>댓글 :</td>
			<td><textarea rows="5" cols="70" id="text"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" id="submit"
				value="확인"></td>
		</tr>
	</table>

</body>

</html>