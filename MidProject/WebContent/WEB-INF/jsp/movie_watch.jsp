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
									console.log("�Է¿Ϸ�");

								},
								error : function(e) {
									alert("�Է½���");
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
					style="width: 100; height: 100;">��ȭ ������ ��!!</div></td>
		</tr>
		<tr>
			<td>���� :</td>
			<td><input type="number" min="1" max="10" id="rating" value="1"></td>
		</tr>
		<tr>
			<td>��� :</td>
			<td><textarea rows="5" cols="70" id="text"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" id="submit"
				value="Ȯ��"></td>
		</tr>
	</table>

</body>

</html>