<%@ page import="java.util.List" %>
<%@ page import="com.zheng.pojo.Emp" %><%--
  Created by IntelliJ IDEA.
  User: 丑奴儿
  Date: 2022/10/3
  Time: 3:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--JSTL需要导入核心包  prefix是使用名，自定义--%>
<html>
  <head>
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.min.css"/>
    <script src="/js/jQuery.mini.js"></script>
    <style>
      body{
        padding: 30px;
      }
      *{
        text-align: center;
      }
    th{
      text-align: center;
    }
      td{
        width: 173px;
        padding: 0px;
        position: relative;
      }
      /*初始化将确定添加控件隐藏，因为没有执行添加操作，*/
      .confirm{
        display: none;
      }
      /*初始化将取消添加控件隐藏，因为没有执行添加操作，*/
      .quxiao{
        display: none;
      }
      #tishi{
        position: absolute;
        left: 60%;
      }
      #tish{
        position: absolute;
        left: 40%;
        margin-right: 20px;
      }
      input{
        border-style:none;
        width: 100%;
      }
      #ti{
        width: 300px;
        height: 40px;
        border-radius: 20px;
        position: absolute;
        left: 40%;
        top: 10px;
        color: #e1e6cc;
        line-height: 40px;
        font-size: 30px;
        background-color: #04152b;
      }

    </style>
    <script>
      //设置全局ajax处理逻辑
      $.ajaxSetup({
        //设置ajax请求结束后的执行动作
        complete: function (xhr) {
          setInterval(function () {//重定向延时1秒，方便提示框显示
            // 通过XMLHttpRequest取得响应头，sessionstatus
            if("REDIRECT" == xhr.getResponseHeader("REDIRECT")){ //若HEADER中含有REDIRECT说明后端想重定向，
              var win = window;
              while(win != win.top){
                win = win.top;
              }
              win.location.href = xhr.getResponseHeader("CONTENTPATH");//将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            }
          },1000)

        }
      });

      $(function () {
        // $(".confirm").hide()//初始化将确定添加控件隐藏，因为没有执行添加操作，
        // $(".quxiao").hide()//初始化将取消添加控件隐藏，因为没有执行添加操作，（这里css样式已经做了，仅作保留--郑）

        //添加控件  点击时将自己隐藏，并把确定、取消控件显示出来，因为自己已经触发添加事件，就不能再触发，必须等确定、取消控件执行后才恢复
        $(".add").click(function () {
            $(this).hide(200)
            $(".confirm").show(200)
            $(".quxiao").show(200)
          $("table").append(
                  "<tr id='newtr'><td>员工号由系统自动生成</td>" +
                  "<td><input class='newn' type='text' name='name'></td>" +
                  "<td><input class='new' type='number' name='salary'></td>" +
                  "<td><input class='new' type='number' name='age'></td></tr>"
          )
          //添加框的名字框控件失去焦点，就判断是否为空，空就创建提示，判断前先将之前的提示删除
        $(".newn").blur(function () {
          $("#tish").remove()
            if ($(this).val()==""){
              $(this).parent().append("<span id='tish'  style = 'color : #cd2121'}>不能为空</span>")
              $("#tish").fadeToggle(3000,function () {
                $(this).remove()
              })
            }
        })
          //数字添加框的薪水和年龄控件失去焦点，就判断是否小于1，小就创建提示，判断前先将之前的提示删除
          $(".new").blur(function () {
            $("#tishi").remove()
            if ($(this).val()<1){
              $(this).parent().append("<span id='tishi'  style = 'color : #cd2121'}>不能小于1</span>")
              $(this).val(0)//失去焦点默认给0
              $("#tishi").fadeToggle(3000,function () {
                  $(this).remove()
              })
            }
          })
        })

        //确定控件  按下后 向ajax提交数据进行添加，要显示添加控件，并隐藏自己和取消控件
        $(".confirm").click(function () {
          $("#tishi1").remove()
          var name =  $(".newn").val()
          var salary = $(".new:first").val()
          var age = $(".new:last").val()
          if (name==""||salary<1||age<1){//前端检查是否按规则填写，减轻服务器压力
            $("body").prepend("<p id='ti'}>检查是否填写正确</p>")
            setInterval(function () {
              $("#ti").fadeToggle(1000,function () {
                $(this).remove()
              })
            },2000)
          } else {
          $.get(

                  "http://localhost:8080/man/safe/update",
                          // "http://192.168.3.7:8080/man/safe/update",
                  {
                    "tab":"1-._`",
                    "name":name,
                    "salary":salary,
                    "age":age
                  },
                  function (res) {
                  if (res!=null){//有返回数据，表示添加失败//创建失败提示框
                    $("body").prepend("<p id='ti'}>"+res+"</p>")
                    setInterval(function () {
                      $("#ti").fadeToggle(1000,function () {
                        $(this).remove()
                      })
                    },3000)
                  }
                  //无返回数据，表示成功，后台会重定向此页面，达到刷新出新数据的效果，同时在重定向设置延时，为创建成功提示框增加显示时间
                    $("body").prepend("<p id='ti'}>"+res+"</p>")
                    setInterval(function () {
                      $("#ti").fadeToggle(1000,function () {
                        $(this).remove()
                      })
                    },3000)
                    $(".quxiao").hide(100)//创建成功需要把取消控件隐藏
                     }
          )}
        })

        //取消控件
        $(".quxiao").click(function () {
          $(this).hide(200)
          $(".confirm").hide(200)
          $(".add").show(600)
          $("#newtr").remove()
        })

        //修改控件
        $(".update").click(function (re) {
          $("#newtr").remove()//删除正在添加的控件，防止操作污染
          $(".add").hide()//修改中，禁止添加的所有操作，防止操作污染
          $(".quxiao").hide()
          $(".confirm").hide()
          $(".update").hide()
          //以上是在添加的过程中进行修改数据，删除和隐藏所有添加的数据和控件，防止操作污染
          let id =  $(this).parent().parent().find("td:eq(0)").html()
          let name =  $(this).parents("tr").find("td").eq(1).html()
          let salary=  $(this).parents("tr").find("td").eq(2).html()
          let age=  $(this).parents("tr").find("td").eq(3).html()
          //获取当前tr标签的所有数据，赋值给下面新创建的带有输入框的tr标签集，方便原地修改
          $(this).parents("tr").after("<tr id='newtr'><td>"+id+"</td>" +
                  "<td><input class='newn' type='text' name='name' value='"+name+"'></td>" +
                  "<td><input class='new t' type='number' name='salary' value='"+salary+"'></td>" +
                  "<td><input class='new' type='number' name='age' value='"+age+"'></td>" +
                  "<td><button style=\"float: right;margin-right: 12%\" type=\"button\" class=\"btn btn-success confirmupdate\"><span class=\"glyphicon glyphicon-ok\"></span>  确定修改</button></td>\n" +
                  "<td><button style=\"float: right;margin-right: 3%\" type=\"button\" class=\"btn btn-warning quxiaoupdate\"><span class=\"glyphicon glyphicon-remove\"></span>  取消修改</button></td></td></td>" +
                  "</tr>")

          var old =  $(this).parents("tr")
          $(old).hide()//同时将原来的tr标签集隐藏，达到原地修改的效果
          $(".quxiaoupdate").click(function () {//这是 “quxiaoupdate取消修改控件”，不要跟 “quxiao取消添加控件” 混淆
            $(old).show(300)//取消了修改操作，就要把上面隐藏的或者显示的控件归位
            $(".add").show(300)
            $(".update").show(300)
            $("#newtr").remove()
          })
          $(".confirmupdate").click(function () {//这是 “confirmupdate确定修改控件”，不要跟 “confirm确定添加控件” 混淆
            $(".quxiaoupdate").remove()//取消了修改操作，就要把上面隐藏的或者显示的控件归位
            name = $("#newtr").find("input").eq(0).val()
            salary = $("#newtr").find("input").eq(1).val()
            age = $("#newtr").find("input").eq(2).val()
            $.get(
                    "http://localhost:8080/man/safe/update"
            // "http://192.168.3.7:8080/man/safe/update"
                    ,
                    {
                      "tab":"2-.__`",
                      "id":id,
                      "name":name,
                      "salary":salary,
                      "age":age
                    },
                    function (res) {
                      $("body").prepend("<p id='ti'}>"+res+"</p>")//创建提示框
                      setInterval(function () {
                        $("#ti").fadeToggle(1000,function () {
                          $(this).remove()
                        })
                      },2000)
                    }
            )
          })
        })

        //删除控件
        $(".btn-danger").click(function () {
          var id = $(this).parents("tr").find(":first-child").html()//获取id，发送到后台进行删除操作
          if (confirm("是否要删除？"+id)){
            $.get(
                 "http://localhost:8080/man/safe/update",
                         // "http://192.168.3.7:8080/man/safe/update",
                    {
                      "tab":"3-.__。`",
                      "id":id,
                    },
                    function (res) {
                      $("body").prepend("<p id='ti'}>删除成功</p>")//创建删除成功提示框
                      setInterval(function () {
                        $("#ti").fadeToggle(1000,function () {
                          $(this).remove()
                        })
                      },2000)
                 }
            )
            $(this).parents("tr").remove()//删除后将当前的tr标签删除
          }
        })

        //退出登录控件
        $(".black").click(function () {
          $.ajax({
            // 'http://10.36.103.6:8080/'+url,
            url:"http://localhost:8080/man/login",
            // url:"http://192.168.3.7:8080/man/login",
            dataType:'json',
            data:{"tag":"0"},
            cache:false,
            ifModified :true ,
            success:function(response){
            },
            async:false
          })

        })
      })
    </script>
  </head>
  <body>
<%--  <% List<Emp> list = (List<Emp>) request.getAttribute("list"); %>--%>

  <table class="table table-bordered table-hover">
    <tr class="warning">
    <th>员工号</th>
    <th>名字</th>
    <th>薪水</th>
    <th>年龄</th>
    <th colspan="2" style="width: 20%" >其他操作</th>
    </tr>
   <%-- <% for (Emp e:list) {%>
    <tr>
      <td><%=e.getId()%></td>
      <td><%=e.getName()%></td>
      <td><%=e.getSalary()%></td>
      <td><%=e.getAge()%></td>
      <td><button type="button" class="btn btn-sm btn-warning update"><span class="glyphicon glyphicon-pencil"></span>  修改</button></td>
      <td><button type="button" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span>   删除</button></td>
    </tr>
    <% }%>--%>

    <%--一般来说EL和JSTL搭配使用--%>
    <%--EL表达式用${名字}==request.getAttribute("list");--%>
    <c:forEach var="e" items="${list}">

      <tr>
        <td>${e.id}</td>
        <td>${e.name}</td>
        <td>${e.salary}</td>
        <td>${e.age}</td>
        <td><button type="button" class="btn btn-sm btn-warning update"><span class="glyphicon glyphicon-pencil"></span>  修改</button></td>
        <td><button type="button" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span>   删除</button></td>
      </tr>
    </c:forEach>
  </table>
  <button type="button" style="float: left" class="btn btn-dark black">退出登录</button>
  <button style="float: right;margin-right: 12%" type="button" class="btn btn-success confirm"><span class="glyphicon glyphicon-ok"></span>  确定</button></td>
  <button style="float: right;margin-right: 3%" type="button" class="btn btn-warning quxiao"><span class="glyphicon glyphicon-remove"></span>  取消</button></td>
  <button style="float: right;margin-right: 3%" type="button" class="btn btn-info add"><span class="glyphicon glyphicon-plus"></span>  添加</button></td>
  </body>
</html>
