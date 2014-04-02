<html>
<head>
<script type="text/javascript" src="jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="Drinks.js"></script>
<script type="text/javascript">
	function send(value) {
		jQuery
				.ajax({
					url : 'services/speed/'
							+ (value),
					success : function() {
						console.log('fatto');
					}
				});
	}
	
	function switchDirection(){
		jQuery
		.ajax({
			url : 'services/switch/',
			success : function() {
				console.log('fatto');
			}
		});
	}
	
	function onChange(value){
		console.log(value);
		jQuery('#label').html(value);
	}
</script>
</head>
<body>
	<knob id="k1" onchange="onChange(this.value)"></knob>
	<span id="label"></span>
	<button onclick="send(k1.value);">Change Pace</button>
	<button onclick="switchDirection();">Change Direction</button>
</body>
</html>
