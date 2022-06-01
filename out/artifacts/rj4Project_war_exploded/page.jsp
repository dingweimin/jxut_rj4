<%--
  Created by IntelliJ IDEA.
  User: weimin ding
  Date: 2022/5/31
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        //0首页 -1上一页 1下一页 2尾页  3go页码
        function $$(id){
            return document.getElementById(id);
        }
        function  formSbm(num){
            var pageNum = $$("pageNo").value;
        if(num==0){
            $$("pageNo").value=1;
        }else if(num==-1) {
            if (pageNum > 1) {
                $$("pageNo").value = parseInt(pageNum) - 1;
            }else {
                alert("已经是第一页了");
                return;
            }
        }else if(num==1) {
            if (pageNum < parseInt($$("totalPage").value)) {
                $$("pageNo").value = parseInt(pageNum) + 1;
            }else {
                alert("已经是最后一页了");
                return;
            }
        }else if (num == 2) {
            $$("pageNo").value = parseInt($$("totalPage").value);
        } else if (num == 3) {
            var toNum = $$("toNum").value;
            if(isNaN(toNum)){
                alert("请输入数字");
                return;
            }else{
                var totalPage =parseInt($$("totalPage").value) ;
                console.log(totalPage);
                if(toNum>totalPage) {
                    alert("超出总页数");
                    return;
                }else {
                    $$("pageNo").value = toNum;
                }
            }
        }
        $$("sFrom").submit();
        }
    </script>
</head>
<body>

</body>
</html>
