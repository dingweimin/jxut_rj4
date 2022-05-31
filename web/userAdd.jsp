<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: weimin ding
  Date: 2022/5/25
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common.jsp"></jsp:include>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
           <%-- <form action="UserServlet?method=addUser" enctype="multipart/form-data" method="post">>--%>
            <form action="UserServlet?method=userAddO" enctype="multipart/form-data" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="userId">用户账号：</label>
                    <input type="text" name="userId" id="userId"/>
                    <span>*请输入用户账号，且不能重复</span>
                </div>
                <div>
                    <label for="userName">真实姓名：</label>
                    <input type="text" name="userName" id="userName"/>
                    <span >*请输入用真实姓名</span>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="text" name="userpassword" id="userpassword"/>
                    <span>*密码长度必须大于6位小于20位</span>

                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="text" name="userRemi" id="userRemi"/>
                    <span>*请输入确认密码</span>
                </div>
                <div>
                    <label >用户性别：</label>

                    <select name="userSex">
                        <option value="1">男</option>
                        <option value="2" >女</option>
                    </select>
                    <span></span>
                </div>
                <div>
                    <label for="date">出生日期：</label>
                    <input type="date" name="date" id="date"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <c:forEach items="${sessionScope.typeList}" var="type">
                        <input type="radio" id="usertype" name="userlei" value="${type.id}"/>${type.name}
                    </c:forEach>
                </div>
                <div>
                    <label >头像：</label>
                        <input type="file" name="photo" />
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
<%--                    <input type="button" value="保存" onclick="history.back(-1)"/>--%>
                    <input type="submit" value="提交" />
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>