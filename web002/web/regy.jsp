<%--
  Created by IntelliJ IDEA.
  User: NFZ
  Date: 2021/6/17
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>
    <select name="" id="cityName">
        <%--<option value=""></option>--%>
    </select>
    <select name="" id="cn">

    </select>

    <select name="" id="cs">

    </select>




</head>
<body>




<script>
    $(function() {
        $.ajax({
            url:'/SanJiServlet',
            type:'get',
            success:function (res) {
                 console.log(res)
                // 遍历json，
                $.each(res.citylist,function (index, item) {
                     var cityName= item.p;
                  var cityNameDom=   $("<option value="+cityName+">"+ cityName +"</option>")
                    cityNameDom.appendTo("#cityName");
                    var citys= item.c;
                    $.each(citys,function (index, item) {
                        var  cn=   item.n;
                        var cnDom=$("<option value="+cn+">"+ cn +"</option>")
                        cnDom.appendTo("#cn");
                        
                        $.each(item.a,function (index,item) {
                            var  cs=   item.s;
                            var csDom=$("<option value="+cs+">"+ cs +"</option>")
                            csDom.appendTo("#cs");
                        })
                    });
                })

            },
            dataType:'json'
        });
    })
</script>




</body>
</html>
