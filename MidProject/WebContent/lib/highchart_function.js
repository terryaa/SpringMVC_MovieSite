var chart = Highcharts.chart('chartcontainer', {
	
	title: {
        text: '내 일간 사용 금액'
    },	

    subtitle: {
        text: 'Day'
    },
    xAxis: {
        categories: soldDate   
    },
    series: [{
        type: 'column',
        colorByPoint: true,
        data: soldAvg,
        showInLegend: false
    }]
});
//시작값은 일간 차트로 시작하므로 이것부터 실행하고 본다.
function chartFunction(){
	$('#chartType').val('day');
	$.ajax( {
		url: "./userInfo/chartType/day" ,			
		type: 'GET',				//타입
			success:function(data){
			  var soldDate = new Array();
			  var soldAvg = new Array();
			  for(var i=0; i<=data.length-1; i++ ){
				  soldDate[i] = data[i].sold_date;
				  soldAvg[i] = data[i].average;
			  }
			  chart.update({
				  	title: {
				        text: '내 일간 사용 금액'
				    },	
			        chart: {
			            inverted: false,
			            polar: false
			        },
			        subtitle: {
			            text: 'Day'
			        },
			        xAxis: {
			            categories: soldDate
			        },

			        series: [{
			        	data: soldAvg
			        }]
			  });
			}
	});

}
	$('#inverted').click(function(){
		$('#chartType').val('month');
		$.ajax( {
			url: "./userInfo/chartType/month" ,			
			type: 'GET',				//타입
				success:function(data){
				    var soldDate = new Array();
				    var soldAvg = new Array();
					for(var i=0; i<=data.length-1; i++ ){
				  	  soldDate[i] = data[i].sold_date;
					  soldAvg[i] = data[i].average;
					}
				  	chart.update({
				  		title: {
				  	        text: '내 월간 사용 금액'
				  	    },	
				        chart: {
				            inverted: true,
				            polar: false
				        },
				        subtitle: {
				            text: 'Month'
				        },
				        xAxis: {
				            categories: soldDate
				        },
				        series: [{
				        	data: soldAvg
				        }]
				        
				    });
				}
		});
	});
	