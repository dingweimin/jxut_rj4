<%--
  Created by IntelliJ IDEA.
  User: weimin ding
  Date: 2022/5/25
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="page.jsp"></jsp:include>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面</span>

        </div>
        <form action="UserServlet?method=getAll" id="sFrom" method="post">
        <div class="search">
            <span>姓名：</span>
            <input type="text" name="realName"  value="${realName}" placeholder="请输入真实姓名"/>
            <input type="submit"  value="查询"/>
            <a href="UserServlet?method=preUser">添加用户</a>
        </div>
        <!--用户-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">用户账号</th>
                <th width="20%">真实姓名</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户类型</th>
                <th width="10%">头像</th>
                <th width="20%">操作</th>
            </tr>
    <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.userName}</td>
                <td>${user.realName}</td>
                <td><c:if test="${user.sex==1}" >
                    男
                </c:if>
                    <c:if test="${user.sex==2}" >
                        女
                    </c:if></td>
                <td>${user.birthday}</td>
                <td>${user.tel}</td>
                <td><c:choose>
                    <c:when test="${user.type==1}">学员</c:when>
                    <c:when test="${user.type==2}">教师</c:when>
                    <c:otherwise>管理员</c:otherwise>
                </c:choose></td>
                <td>
                    <img src="${user.pic}" width="20%" title="查看图像">
                    <a href="${user.pic}" target="_blank">下载图像</a>
                </td>
                <td>
                    <a href="UserServlet?method=getUserById&id=${user.id}&tiaozhuan=view"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="UserServlet?method=getUserById&id=${user.id}&tiaozhuan=update"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="UserServlet?method=deleteUser&id=${user.id} " onclick="return confirm('你确定要删除嘛？')" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>
    </c:forEach>
            <tr>
                <td colspan="8">
                    <table border="0" width="100%" align="center">
                        <tr>
                            <td>
                                <input type="hidden" name="pageNo" id="pageNo" value="${page.currentPage}" >
                                <input type="hidden" name="totalPage" id="totalPage" value="${page.totalPage}" >
                                <input type="button" value="首页" onclick="formSbm(0)" />
                                <input type="button" value="上一页" onclick="formSbm(-1)" />
                                <input type="button" value="下一页" onclick="formSbm(1)" />
                                <input type="button" value="尾页" onclick="formSbm(2)" />
                                总记录数:${page.totalSize }条
                                每页显示${page.pageSize }条
                                共${page.totalPage }页
                                当前第${page.currentPage }页
                                <input type="text" name="toNum" id="toNum"/>
                                <input type="button" value="GO" onclick="formSbm(3)"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        </form>
    </div>
</section>

<!--点击删除按钮后弹出的页面
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="UserServlet?method=deleteUser" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
-->
<footer class="footer">
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>
