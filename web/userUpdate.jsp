<%--
  Created by IntelliJ IDEA.
  User: weimin ding
  Date: 2022/6/2
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面 >> 用户修改页面</span>
    </div>
    <div class="providerAdd">
        <form action="UserServlet?method=updateUser&id=${user.id}" method="post">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div>
                <label for="userName">真实姓名：</label>
                <input type="text" name="realName" id="userName" value="${user.realName}" placeholder="${user.realName}"/>
                <span >*</span>
            </div>

            <div>
                <label >用户性别：</label>

                <select name="sex">
                    <option value="1">男</option>
                    <option value="2" selected>女</option>
                </select>
            </div>
            <div>
                <label for="data">出生日期：</label>
                <input type="date" name="birthday" id="data" value="${user.birthday}" placeholder="${user.birthday}"/>
                <span >*</span>
            </div>
            <div>
                <label for="userphone">用户电话：</label>
                <input type="text" value="${user.tel}" name="tel" id="userphone" placeholder="${user.tel}"/>
                <span >*</span>
            </div>
            <div>
                <label for="userAddress">用户地址：</label>
                <input type="text" name="userAddress" value="${user.address}" id="userAddress" placeholder="${user.address}"/>
            </div>
            <div>
                <label >用户类别：</label>
                <c:forEach items="${types}" var="type">
                    <input type="radio" id="usertype" name="type"  value="${type.id}"
                    <c:if test="${type.id == user.type}">
                        checked = "checked"</c:if>
                    />${type.name}
                </c:forEach>
            </div>
            <div class="providerAddBtn">
                <!--<a href="#">保存</a>-->
                <!--<a href="userList.html">返回</a>-->
                <input type="submit" value="保存" onclick="history.back(-1)"/>
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