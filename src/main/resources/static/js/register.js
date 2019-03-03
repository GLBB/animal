
function verificationCode() {
    emailElem = document.getElementById("email");
    errorElm = document.getElementById("emailError");
    console.log(errorElm)
    email = emailElem.value;
    reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/;
    if (email === "") {
        errorElm.textContent = "邮箱不能为空";
        errorElm.style.display = "block";
    } else if (!reg.test(email)) {
        errorElm.textContent = "邮箱格式错误";
        errorElm.style.display = "block";
    } else{
        // 通过 ajax 请求发送 email 信息


    }

}

/**
 * 把邮箱号码发送到服务端
 * @param email
 */
function sendEmail(email) {
    var xmlhttp;
    if (window.XMLHttpRequest)
    {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp=new XMLHttpRequest();
    }
    else
    {
        // IE6, IE5 浏览器执行代码
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        // 服务端收到邮箱号
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            return true;
        } else {
            return false;
        }
    }
    xmlhttp.open("GET","/try/ajax/ajax_info.txt",true);
    xmlhttp.send();
}