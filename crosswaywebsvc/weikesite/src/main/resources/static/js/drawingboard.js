		var isEdit = true;
		function setIsEdit(is) {
			isEdit = is;
			if (isEdit) {
				document.getElementById("imgEdit").src = "/static/images/homework/pen_select.png";
				document.getElementById("imgNotEdit").src = "/static/images/homework/move.png";
			}
			else {
				document.getElementById("imgEdit").src = "/static/images/homework/pen.png";
				document.getElementById("imgNotEdit").src = "/static/images/homework/move_select.png";
			}
		}

		var scale = 1;
		function scaleCanvas(s) {
			scale *= s;
			var canvas = document.getElementById("mycanvas");
			var img = document.getElementById("tulip");
			var div = document.getElementById("divcanvas");
			canvas.style.MozTransform = 'scale(' + scale + ')';
			canvas.style.WebkitTransform = 'scale(' + scale + ')';
			canvas.style.OTransform = 'scale(' + scale + ')';
			canvas.style.marginTop = (scale - 1) * 0.5 * img.height + 'px';
			canvas.style.marginLeft = (scale - 1) * 0.5 * img.width + 'px';
			div.style.height = (img.height * scale) + 'px';
		}

		$(function () {
			var c = document.getElementById("mycanvas");
			var ctx = c.getContext("2d");
			ctx.lineCap = "round";　　//设置线条两端为圆弧
			ctx.lineJoin = "round";　　//设置线条转折为圆弧
			var img = document.getElementById("tulip");
			c.width = img.width;
			c.height = img.height;
			ctx.drawImage(img, 10, 10);
			$("#totulip").hide();
			$("#panrange").val(4);
			$("#panrangeval").val(4);
		});

		function changerange() {
			$("#panrangeval").val($("#panrange").val());
		};

		var maxX = -1;
		var maxY = -1;
		var minX = 99999;
		var minY = 99999;

		function checkData(event) {
			var x = event.pageX - $('canvas').offset().left;
			var y = event.pageY - $('canvas').offset().top;
			if (x > maxX) {
				maxX = x;
			} else if (x < minX) {
				minX = x;
			}
			if (y > maxY) {
				maxY = y;
			} else if (y < minY) {
				minY = y;
			}
		}

		$(function () {
			var obj = $('canvas');
			var temp_e;
			var temp_draw = false;
			var panval = $("#panrange").val();
			var x = 0;
			var y = 0;

			obj.on({
				mousedown: function (e) {
					temp_e = e;
					temp_draw = true;
					checkData(e);
				},
				touchstart: function (e) {
					temp_e = e.originalEvent.targetTouches[0];
					x = e.originalEvent.touches[0].pageX;
					y = e.originalEvent.touches[0].pageY;
					temp_draw = true;
					checkData(temp_e);
				},
				mousemove: function (e) {
					if (temp_draw) {
						obj.drawLine({
							strokeStyle: $("input[name='rcolor']:checked").val(),
							strokeWidth: $("#panrange").val(),
							x1: (temp_e.pageX - $('canvas').offset().left) / scale,
							y1: (temp_e.pageY - $('canvas').offset().top) / scale,
							x2: (e.pageX - $('canvas').offset().left) / scale,
							y2: (e.pageY - $('canvas').offset().top) / scale,
						});
					}
					temp_e = e;
					checkData(e);
				},
				touchmove: function (e) {
					if (!isEdit)
						return;

					e.preventDefault();
					//此处使用temp_e在iphone，ipad上Safari会造成new_e和temp_e坐标相同。
					//document.getElementById('inp').innerHTML = "<br>(" + e.originalEvent.touches[0].pageX + "," + event.touches[0].pageY + ")";
					//document.getElementById('inp').innerHTML += "<br>(" + x + "," + y + ")";
					var new_e = e.originalEvent.touches[0];
					if (temp_draw) {
						obj.drawLine({
							strokeStyle: $("input[name='rcolor']:checked").val(),
							strokeWidth: $("#panrange").val(),
							x1: (x - $('canvas').offset().left) / scale,
							y1: (y - $('canvas').offset().top) / scale,
							x2: (new_e.pageX - $('canvas').offset().left) / scale,
							y2: (new_e.pageY - $('canvas').offset().top) / scale,
						});
					}
					temp_e = new_e;
					x = e.originalEvent.touches[0].pageX;
					y = e.originalEvent.touches[0].pageY;
					checkData(temp_e);
				},
				mouseup: function (e) {
					temp_e = null;
					temp_draw = false;
					checkData(e);
				},
				touchend: function (e) {
					temp_e = null;
					temp_draw = false;
					checkData(e.originalEvent);
				},
				mouseout: function () {
					temp_e = null;
					temp_draw = false;
				}
			});

			$("#clearup").on("click", function () {

				maxX = -1;
				maxY = -1;
				minX = 99999;
				minY = 99999;
				obj.clearCanvas();

				var c = document.getElementById("mycanvas");
				var ctx = c.getContext("2d");
				var img = document.getElementById("tulip");
				c.width = img.width;
				c.height = img.height;
				ctx.drawImage(img, 10, 10);
				$("#totulip").hide();

			});

			$("#save").on("click", function () {
				var image2 = obj.getCanvasImage("png");
				$("#GradeIMGBase64").val(image2);
				document.getElementById("staffForm").submit();
			});
		});
		function check(){
			if($("#teachercomment").text()==null){
				alert("请输入批改内容");
				return false;
			}
		    var image2 = obj.getCanvasImage("png");
		    alert(image2);
		    return true;
		}