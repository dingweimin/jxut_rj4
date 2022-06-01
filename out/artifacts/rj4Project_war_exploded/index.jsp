<%--
  Created by IntelliJ IDEA.
  User: weimin ding
  Date: 2022/5/24
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common.jsp"></jsp:include>
    <div class="right">
        <img class="wColck" src="${loginValue.pic}" alt=""/>
        <div class="wFont">
            <h2>${loginValue.userName}</h2>
            <p>欢迎来到19本软件四班课件管理系统!</p>
            <span id="hours"></span>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>
</body>
</html>
