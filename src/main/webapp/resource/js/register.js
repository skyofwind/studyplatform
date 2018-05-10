var userName = document.getElementById("userName");
var userPwd = document.getElementById("userPwd");
var confirmPwd = document.getElementById("confirmPwd");
var sname = document.getElementById("sname");
/*var email = document.getElementById("email");
var mobile = document.getElementById("mobile");*/
var school = document.getElementById("school");
var code = document.getElementById("code");
var agreement = document.getElementById("agreement");
var btn = document.getElementById("btn")
var verifyCode = document.getElementById("verifyCode");

/*----------------------------校验用户名----------------------------*/
function checkUserName (e) {
	var _e = window.event || e;
	var box = userName.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = userName.value;
	/*获取焦点*/
	if(_e){
		if(_e.type =="focus"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind default";
				span.innerHTML = "支持汉字、字母、数字、-、_的组合，4-20个字符";
				return false;
			}
		}
		if(_e.type == "blur"){
			if(v.length == 0){ 
				box.className = "box";
				remind.className = "remind hide";
				return false;
			}
		}
	}
	/*其他事件*/
	if(v.length == 0){
		box.className = "box error";
		remind.className = "remind error";
		span.innerHTML = "请输入用户名";
		return false;
	}else{
		if(regExpManger.userNameReg.test(v)){
			if(v.length>=4 && v.length<=20){
				box.className = "box right";
				remind.className = "remind hide";
				return true;
			}else{
				box.className = "box error";
				remind.className = "remind error";
				span.innerHTML = "长度4-20个字符";
				return false;
			}
		}else{
			box.className = "box error"; 
			remind.className = "remind error";
			span.innerHTML = "格式错误，仅支持汉字、字母、数字、-、_的组合，4-20个字符";
			return false;
		}
	}
}
userName.onfocus = checkUserName;
userName.onblur = checkUserName;
userName.onkeyup = checkUserName;

/*----------------------------校验密码----------------------------*/
function checkPwd (e) {
	var _e = window.event || e;
	var box = userPwd.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = userPwd.value;
	/*获取焦点*/
	if(_e){
		if(_e.type =="focus"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind default";
				span.innerHTML = "建议使用数字、字母和符号两种以上的组合，6-20个字符";
				return false;
			}
		}
		if(_e.type == "blur"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind hide";
				return false;
			}
		}
	}
	/*其他事件*/
	if(v.length == 0){
		box.className = "box error";
		remind.className = "remind error";
		span.innerHTML = "请输入密码";
		return false;
	}else{
		
		if(v.length>=6 && v.length<=20){
			box.className = "box right";
			var level = 0;
			if(regExpManger.regNum.test(v)){
				level++;
			}
			if(regExpManger.regWord.test(v)){
				level++;
			}
			if(regExpManger.regOther.test(v)){
				level++;
			}
			switch(level){
				case 1:
					remind.className = "remind ruo";
					span.innerHTML = "密码强度弱";
					break;
				case 2:
					remind.className = "remind zhong";
					span.innerHTML = "密码强度中";
					break;
				case 3:
					remind.className = "remind qiang";
					span.innerHTML = "密码强度强";
					break;
				default:
					remind.className = "remind ruo";
					span.innerHTML = "密码强度弱";
			}
			return true;
		}else{
			box.className = "box error";
			remind.className = "remind error";
			span.innerHTML = "密码长度不符合";
			return false;
		}
	}
}
userPwd.onfocus = checkPwd;
userPwd.onblur = checkPwd;
userPwd.onkeyup = checkPwd;

/*----------------------------校验确认密码----------------------------*/
function checkConfirmPwd (e) {
	var _e = window.event || e;
	var box = confirmPwd.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = confirmPwd.value;
	/*获取焦点*/
	if(_e){
		if(_e.type =="focus"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind default";
				span.innerHTML = "请确保密码一致";
				return false;
			}
		}
		if(_e.type == "blur"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind hide";
				return false;
			}
		}
	}
	/*其他事件*/
	if(v.length == 0){
		box.className = "box error";
		remind.className = "remind error";
		span.innerHTML = "请再次输入密码";
		return false;
	}else{
		if(v.length>=6 && v.length<=20){
			if(v == userPwd.value){
				box.className = "box right";
				remind.className = "remind hide";
				return true;
			}else{
				box.className = "box error";
				remind.className = "remind error";
				span.innerHTML = "您输入的密码有误";
				return false;
			}
		}else{
			box.className = "box error";
			remind.className = "remind error";
			span.innerHTML = "您输入的密码位数有误";
			return false;
		}
	}
}
confirmPwd.onfocus = checkConfirmPwd;
confirmPwd.onblur = checkConfirmPwd;
confirmPwd.onkeyup = checkConfirmPwd;

/*----------------------------校验邮箱----------------------------*/
function checkSname (e) {
	var _e = window.event || e;
	var box = sname.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = sname.value;
	/*获取焦点*/
    if(_e){
        if(_e.type =="focus"){
            if(v.length == 0){
                box.className = "box";
                remind.className = "remind default";
                span.innerHTML = "请输入你的姓名";
                return false;
            }
        }
        if(_e.type == "blur"){
            if(v.length == 0){
                box.className = "box";
                remind.className = "remind hide";
                return false;
            }
        }
    }
	/*其他事件*/
    if(v.length == 0){
        box.className = "box error";
        remind.className = "remind error";
        span.innerHTML = "请输入你的姓名";
        return false;
    }else{
        if(regExpManger.userNameReg.test(v)){
            if(v.length>=2 && v.length<=20){
                box.className = "box right";
                remind.className = "remind hide";
                return true;
            }else{
                box.className = "box error";
                remind.className = "remind error";
                span.innerHTML = "长度2-20个字符";
                return false;
            }
        }else{
            box.className = "box error";
            remind.className = "remind error";
            span.innerHTML = "格式错误，仅支持汉字、字母、数字、-、_的组合，2-20个字符";
            return false;
        }
    }
}
sname.onfocus = checkSname;
sname.onblur = checkSname;
sname.onkeyup = checkSname;

/*----------------------------校验邮箱----------------------------*/
/*function checkEmail (e) {
	var _e = window.event || e;
	var box = email.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = email.value;*/
	/*获取焦点*/
	/*if(_e){
		if(_e.type =="focus"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind default";
				span.innerHTML = "请输入常用邮箱";
				return false;
			}
		}
		if(_e.type == "blur"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind hide";
				return false;
			}
		}
	}*/
	/*其他事件*/
	/*if(v.length == 0){
		box.className = "box error";
		remind.className = "remind error";
		span.innerHTML = "请输入常用邮箱";
		return false;
	}else{
		if(regExpManger.emailReg.test(v)){
			if(v.length>=6 && v.length<=20){
				box.className = "box right";
				remind.className = "remind hide";
				return true;
			}else{
				box.className = "box error";
				remind.className = "remind error";
				span.innerHTML = "长度6-20个字符";
				return false;
			}
		}else{
			box.className = "box error";
			remind.className = "remind error";
			span.innerHTML = "您输入的邮箱有误";
			return false;
		}
	}
}
email.onfocus = checkEmail;
email.onblur = checkEmail;
email.onkeyup = checkEmail;*/

/*----------------------------校验手机号----------------------------*/
/*function checkMobile (e) {
	var _e = window.event || e;
	var box = mobile.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = mobile.value;*/
	/*获取焦点*/
	/*if(_e){
		if(_e.type =="focus"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind default";
				span.innerHTML = "请输入常用手机号";
				return false;
			}
		}
		if(_e.type == "blur"){
			if(v.length == 0){
				box.className = "box";
				remind.className = "remind hide";
				return false;
			}
		}
	}*/
	/*其他事件*/
	/*if(v.length == 0){
		box.className = "box error";
		remind.className = "remind error";
		span.innerHTML = "请输入常用手机号";
		return false;
	}else{
		if(regExpManger.mobilReg.test(v)){
			if(v.length==11){
				box.className = "box right";
				remind.className = "remind hide";
				return true;
			}else{
				box.className = "box error";
				remind.className = "remind error";
				span.innerHTML = "请检查输入的手机号";
				return false;
			}
		}else{
			box.className = "box error";
			remind.className = "remind error";
			span.innerHTML = "非正常手机号，请重新输入";
			return false;
		}
	}
}
mobile.onfocus = checkMobile;
mobile.onblur = checkMobile;
mobile.onkeyup = checkMobile;*/



/*----------------------------校验学校----------------------------*/
function checkSchool (e) {
	
        var _e = window.event || e;
	var box = school.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = school.value;
	/*获取焦点*/
    if(_e){
        if(_e.type =="focus"){
            if(v.length == 0){
                box.className = "box";
                remind.className = "remind default";
                span.innerHTML = "请输入你的学校";
                return false;
            }
        }
        if(_e.type == "blur"){
            if(v.length == 0){
                box.className = "box";
                remind.className = "remind hide";
                return false;
            }
        }
    }
	/*其他事件*/
    if(v.length == 0){
        box.className = "box error";
        remind.className = "remind error";
        span.innerHTML = "请输入你的学校";
        return false;
    }else{
        if(regExpManger.userNameReg.test(v)){
            if(v.length>=4 && v.length<=40){
                box.className = "box right";
                remind.className = "remind hide";
                return true;
            }else{
                box.className = "box error";
                remind.className = "remind error";
                span.innerHTML = "长度4-40个字符";
                return false;
            }
        }else{
            box.className = "box error";
            remind.className = "remind error";
            span.innerHTML = "格式错误，仅支持汉字、字母、数字、-、_的组合，4-40个字符";
            return false;
        }
    }
}

school.onfocus = checkSchool;
school.onblur = checkSchool;
school.onkeyup = checkSchool;



/*----------------------------校验验证码----------------------------*/
function checkCode (e) {
	var _e = window.event || e;
	var box = code.parentNode;
	var remind = box.nextElementSibling;
	var span = remind.lastElementChild;
	var v = code.value;
	/*获取焦点*/
	if(_e){
		if(_e.type =="focus"){
			if(v.length == 0){
				box.className = "box verify";
				remind.className = "remind default";
				span.innerHTML = "请正确输入验证码";
				return false;
			}
		}
		if(_e.type == "blur"){
			if(v.length == 0){
				box.className = "box verify";
				remind.className = "remind hide";
				return false;
			}
		}
	}
	/*其他事件*/
	if(v.length == 0){
		box.className = "box verify error";
		remind.className = "remind error";
		span.innerHTML = "请输入验证码";
		return false;
	}else{
		if(v.length == verifyCode.innerHTML.length){
			if(v == verifyCode.innerHTML){
				box.className = "box verify right";
				remind.className = "remind hide";
				return true;
			}
		}else{
			box.className = "box verify error";
			remind.className = "remind error";
			span.innerHTML = "您输入的验证码有误";
			return false;
		}
	}
}
code.onfocus = checkCode;
code.onblur = checkCode;
code.onkeyup = checkCode;

/*----------------------------点击注册按钮----------------------------*/
function checkSubmit(){
	var agreeBox = agreement.parentNode;
	var remind = agreeBox.nextElementSibling;
	var span = remind.lastElementChild;
	if(agreement.checked){
		agreeBox.className = "agreeBox";
		remind.className = "remind hide";
		return true;
		/*if(checkUserName()&&checkPwd()&&checkConfirmPwd()&&checkSname()&&checkCode()&&checkSchool()){
			location.href="http://localhost:8080/studyplatform/student/register.do";
		}*/
	}else{
		agreeBox.className = "agreeBox error";
		remind.className = "remind error";
		span.innerHTML = "请同意协议";
		return false;
	}
}
