window.onload = function() {
			
		var dps = [[]];
		var chart = new CanvasJS.Chart("chartContainer", {
			exportEnabled: true,
			animationEnabled: true,
			theme: "light2", // "light1", "dark1", "dark2"
			title: {
				text: "Memory"
			},
			axisX: {
				title: "chart updates every 1.5 secs",
			    interval: 30,
			    intervalType: "second"
			},
			axisY:{
				title: "load",
				includeZero: false
			}, 
			toolTip: {
				shared: true
			},
			legend: {
				cursor:"pointer",
				verticalAlign: "top",
				fontSize: 20,
				fontColor: "dimGrey",
				itemclick : toggleDataSeries
			},			
			data: [{
				type: "line",
				xValueType: "dateTime",
				yValueFormatString: "#,##0.0 u",
				xValueFormatString: "hh:mm:ss TT",
				showInLegend: true,
				name: "PM2.5",
				dataPoints: dataPoints[0]
			
				type: "line",
				yValueFormatString: "#,##0\"%\"",
				indexLabel: "{label} - {y}",
				dataPoints: dps[0]
			}]
		});
		 
		var yValue;
		var label;
		 
		<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
			<c:forEach items="${dataPoints}" var="dataPoint">
				yValue = parseFloat("${dataPoint.y}");
				xValue = parseInt("${dataPoint.x}");
				dataPoints["${loop.index}"].push({
					x : xValue,
					y : yValue
				});
			</c:forEach>
		</c:forEach>
		 
		chart.render();
		 
		}