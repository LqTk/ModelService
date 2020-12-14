<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/12/3
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
  <head>
    <title>测试</title>
  </head>
  <body>
  <form id="registerAdmin" method="post">
    <table>
      <tr>
        <td>用户名</td>
        <td><input type="text" name="name"/></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><input type="text" name="password"/></td>
      </tr>
      <tr>
        <td>电话</td>
        <td><input type="text" name="phone"/></td>
      </tr>
      <tr>
        <td>详情</td>
        <td><input type="text" name="des"/></td>
      </tr>
    </table>
    <input id="register" type="button" onclick="registerd()" value="注册"/>
  </form>

  <form id="login">
    <table>
      <tr>
        <td>用户名</td>
        <td><input type="text" name="name"/></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><input type="text" name="password"/></td>
      </tr>
    </table>
    <input type="button" onclick="logind()" value="登录"/>
  </form>

  <form name="up" action="/SocialService/file/uploadImg" method="post" enctype="multipart/form-data">
    <input type="file" id="fileAvatar" name="img"/>
    <input type="submit" value="上传图片"/>
  </form>

  <script type="application/javascript">

    function uploadFile() {
      var files = $('#fileAvatar').prop('files');
      var data = new FormData();
      data.append('img',files[0]);

      $.ajax({
        url:"http://192.168.2.162:8080/SocialService/file/upload",
        type:'POST',
        data:data,
        cache:false,
        processData:false,
        contentType:false
      });
    }
    function logind() {
      $.ajax({
        url:"http://192.168.2.162:8080/SocialService/user/login",
        type:"POST",
        data:JSON.stringify($(document.getElementById('login')).serializeObject()),
        contentType:"application/json",  //缺失会出现URL编码，无法转成json对象
        success:function(result){
          var res = eval(result);
          if (res.status==0) {
            alert("登录成功")
          }else {
            alert(res.msg)
          }
        },
        fail:function () {
          alert("请求失败");
        }
      });
    }

    function registerd(){
      $.ajax({
        url:"http://192.168.2.162:8080/SocialService/user/register",
        type:"POST",
        data:JSON.stringify($(document.getElementById('registerAdmin')).serializeObject()),
        contentType:"application/json",  //缺失会出现URL编码，无法转成json对象
        success:function(result){
          var res = eval(result);
          if (res.status==0) {
            alert("注册成功")
          }else {
            alert(res.msg)
          }
        },
        fail:function () {
          alert("请求失败");
        }
      });
    }
    /**
     * 自动将form表单封装成json对象
     */
    $.fn.serializeObject = function() {
      var o = {};
      var a = this.serializeArray();
      $.each(a, function() {
        if (o[this.name]) {
          if (!o[this.name].push) {
            o[this.name] = [ o[this.name] ];
          }
          o[this.name].push(this.value || '');
        } else {
          o[this.name] = this.value || '';
        }
      });
      return o;
    };
  </script>
  </body>
</html>
