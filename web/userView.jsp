<%--
  Created by IntelliJ IDEA.
  User: weimin ding
  Date: 2022/5/27
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户账号：</strong><span>${user.userName}</span></p>
            <p><strong>真实姓名：</strong><span>${user.realName}</span></p>
            <p><strong>用户性别：</strong><span>${user.sex}</span></p>
            <p><strong>出生日期：</strong><span>${user.birthday}</span></p>
            <p><strong>用户电话：</strong><span>${user.tel}</span></p>
            <p><strong>用户地址：</strong><span>${user.address}</span></p>
            <p><strong>用户类别：</strong><span>${user.type}
            <c:choose>
                <c:when test="${user.type==1}">学员</c:when>
                <c:when test="${user.type==2}">教师</c:when>
                <c:otherwise>管理员</c:otherwise>
            </c:choose></span></p>

            <a href="UserServlet?method=getAll">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>