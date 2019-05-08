<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="e" items="${mlist }">
	<tr>
		<td>${e.member_number}</td>
		<td>${e.member_id }</td>
		<td>${e.member_name }</td>
	</tr>
</c:forEach>

<script>
	$(function() {
		//�� ���� ���� �̺�Ʈ�� �����ϰ� ó���ϰ� �� �� ���Ǵ� �Լ�
		$('#memberlist tbody tr').bind({
			click : function(e) {

				//var param = e.target.innerHTML;
				//var param = document.getElementById('memberlist');

				var tdArr = new Array();

				var tr = $(this);
				var td = tr.children();

				//console.log(param);
				//console.log(tr.text());

				// 							td.each(function(i){
				// 				                tdArr.push(td.eq(i).text());
				// 				            });

				// 				            console.log("�迭�� ��� �� : "+tdArr);

				var param = td.eq(0).text();
				var userid = td.eq(1).text();
				var name = td.eq(2).text();

				if (window.ActiveXObject) {
					xhr = new ActiveXObject("Microsoft.XMLHTTP");
				} else {
					xhr = new XMLHttpRequest();
				}
				// �����κ��� ����� ������ �� �����ϴ� �Լ� ����(�ݹ��Լ�)
				xhr.onreadystatechange = res;
				// �������� ȣ���ϱ� ���� �غ�
				// ���۹��, �ּ�, �񵿱��(true), �����(false)
				xhr.open("POST", "./admin_MemberDetail?number=" + param, true);
				// �������� ��û������
				xhr.send(null);
				// �츰 get������� ������� �߱� ������ ���ڴ� �ݵ�� null�̾�� �ϰ�, ���� post��� null�� �ƴ϶�, �������� �ϴ� �Ķ���͸� ���ڿ��� �־���Ѵ�.
				// xhr.send(s);
			},
			mouseenter : function() {
				$(this).addClass('cng')
			},
			mouseleave : function() {
				$(this).removeClass('cng')
			}
		});
	});

	function res() {
		if (xhr.readyState == 4) { //�����κ��� ������ ó���� �Ϸ�� ���
			if (xhr.status == 200) { //�������� ó���� ���
				let txt = document.getElementById("txt");
				txt.innerHTML = xhr.responseText;
				//alert(xhr.responseText);
			} else {
				alert("����" + xhr.status);
			}
		}
	}
	
	// ����¡ ó�� �˰���
	$(function() {
		var $setRows = $('#setRows');

		$setRows.submit(function(e) {
			e.preventDefault();
			var rowPerPage = $('[name="rowPerPage"]').val() * 1;// 1 ��  ���Ͽ� ���ڿ��� �������� ��ȯ

			//		      console.log(typeof rowPerPage);

			var zeroWarning = '�ǹٸ� ���� �Է��� �ּ���.'
			if (!rowPerPage) {
				alert(zeroWarning);
				return;
			}
			$('.pagination').remove();
			var $products = $('#memberlist');
			// 					<div class="container">
			$products.after('<div id="next"><ul class="pagination">');
			// 					<ul class="pagination">
			var $tr = $($products).find('tbody tr');
			var rowTotals = $tr.length;
			//  console.log(rowTotals);

			var pageTotal = Math.ceil(rowTotals / rowPerPage);
			var i = 0;

			$('<li></li>').appendTo('.pagination');

			for (; i < pageTotal; i++) {
				$('<a href="#"></a>').attr('rel', i).html(i + 1).appendTo(
						'.pagination li');
				// 						$('</li>').appendTo('.pagination');
			}

			$tr.addClass('off-screen').slice(0, rowPerPage).removeClass(
					'off-screen');

			var $pagingLink = $('.pagination a');
			$pagingLink.on('click', function(evt) {
				evt.preventDefault();
				var $this = $(this);
				if ($this.hasClass('active')) {
					return;
				}
				$pagingLink.removeClass('active');
				$this.addClass('active');

				// 0 => 0(0*4), 4(0*4+4)
				// 1 => 4(1*4), 8(1*4+4)
				// 2 => 8(2*4), 12(2*4+4)
				// ���� �� = ������ ��ȣ * �������� ���
				// �� �� = ���� �� + �������� ���

				var currPage = $this.attr('rel');
				var startItem = currPage * rowPerPage;
				var endItem = startItem + rowPerPage;

				$tr.css('opacity', '0.0').addClass('off-screen').slice(
						startItem, endItem).removeClass('off-screen').animate({
					opacity : 1
				}, 300);

			});

			$pagingLink.filter(':first').addClass('active');

		});

		$setRows.submit();
	});
</script>