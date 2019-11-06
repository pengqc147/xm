<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery-3.3.1.js"></script>
    <style>

    </style>

</head>
<body>
<div class="register_head_on">

</div>
<div class="register_head">
    <a href="index.html"><img src="img/logo.jpg" alt=""></a>
    <div class="register_head_right">
        <p class="register_head_right_p1">小 米 商 城</p>
        <p class="register_head_right_p2">让每个人都享受科技乐趣</p>
    </div>

</div>

<div class="register">
    <div class="register_boby">
        <div class="register_boby_min">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">手机验证码登录 </span>
                </div>
            </div>
         
            <form id="myForm" action="user" method="post">
            
            <!-- func区分的方法 -->
            <input name="fs" value="validate" type="hidden">
            
            <div class="register_boby_no2">
            	<span id="msg" style="color: red;font-size: 12px;margin-left: 20px;"></span>
                <input id="phone" name="phone" type="text" placeholder="手机号码">
                
                <input id="code" name="code"  type="password" placeholder="手机校验码" style="width: 200px; margin-left: 32px;float: left;">
                <!-- 新增加 -->
                <input id="zphone" type="button" value=" 获取手机验证码 " style="width: 138px;float: left;height: 53px;margin-left: 8px;"> 
                <div style="clear: both;">
                
                <div id="loginDiv" class="register_boby_no2_div">
                    <span id="sub">登录</span>
                </div>
            </div>
            </div>
            </form>
            
            <div class="register_boby_no3">
                <a href="javascript:void(0);" style="color: #ff6700">账号密码登录</a>
                <span class="register_boby_no3_span">
                    <a href="register.jsp">立即注册</a>
                    <span>|</span>
                    <a href="avascript:void(0);">忘记密码?</a>
                </span>

            </div>
            <div class="register_boby_no4">
                <img src="img/register02.jpg" alt="">
            </div>

        </div>
    </div>
</div>
<div class="register_foot">
    <img  src="img/register03.jpg" alt="">
</div>
</body>
<script type="text/javascript">
	//点击获取验证码的方法
	$("#zphone").click(function(){
		//首先拿到手机号码
		var phone=$("#phone").val();
		//非空校验
		if(phone==null || phone==""){
			$("#msg").text("手机号码不能为空").css("color","red");
			$("#phone").focus();
			return;
		}
		//正则校验
		if(!(/^1[3456789]\d{9}$/.test(phone))){
			$("#msg").text("手机号码格式错误").css("color","red");
			$("#phone").focus();
		}else{
			//已注册校验
			$.ajax({
				url: "user",
				type: "post",
				data: {"phone" :phone,"func": "checkPhoneRegist"},
				dataType: "json",
				success: function(isRegist){
					/* isRegist为true，读秒并发送验证码
					**isRegist为false，提示未注册，去注册
					*/
					if(isRegist){
						remainTime();//读秒
						createCode(phone);//生成验证码
					}else{
						$("#msg").html("<a href='register.jsp' style='color: red;'>请先注册账号</a>")
					}
				}
			})
		}
	})
	//生成验证码的方法
	function createCode(phone){
		$.ajax({
			url: "user",
			type: "post",
			data: {"phone": phone,"func": "createCode"},
			dataType: "json",
			success: function(isSuccess){
				if(isSuccess){
					$("#msg").text("验证码发送成功，请输入验证码").css("color","green");
				}else{
					$("#msg").text("验证码发送失败，请重新获取").css("color","red");
                }
			}
		})
	}
	//登录方法
	$("#loginDiv").click(function(){
		var phone = $("#phone").val();
		var code = $("#code").val();
		if(code==null || code==""){
			$("#msg").text("验证码不能为空");
			$("#code").focus();
		}else{
		$.ajax({
			url: "user",
			type: "post",
			data: {"phone": phone, "code": code, "func": "validateCode"},
			dataType: "json",
			success: function(isSuccess){
				if(isSuccess){
					//登录成功，跳转商城页面
					window.location = "index?func=findAllCate";
				}else{
					$("#msg").text("验证码输入错误").css("color","red");
					$("#code").focus();
				}
			}
		 })
	   }
	})

	//读秒的方法
	var iTime = 59;
	var Account;
	function remainTime(){
		document.getElementById('zphone').disabled = true;
		var iSecond,sSecond="",sTime="";
		if (iTime >= 0){
			iSecond = parseInt(iTime%60);
			iMinute = parseInt(iTime/60)
			if (iSecond >= 0){
				if(iMinute>0){
					sSecond = iMinute + "分" + iSecond + "秒";
				}else{
					sSecond = iSecond + "秒";
				}
			}
			sTime=sSecond;
			if(iTime==0){
				clearTimeout(Account);
				sTime='获取手机验证码';
				iTime = 59;
				document.getElementById('zphone').disabled = false;
			}else{
				Account = setTimeout("remainTime()",1000);
				iTime=iTime-1;
			}
		}else{
			sTime='没有倒计时';
		}
		document.getElementById('zphone').value = sTime;
	}
</script>
</html>




