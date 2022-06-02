package com.jxut.control; /**
 * @Author: WeiMin
 * @Date: 2022/5/25 9:28
 */

import com.jxut.dao.TypeDao;
import com.jxut.dao.UserDao;
import com.jxut.dao.impl.TypeDaoImpl;
import com.jxut.dao.impl.UserDaoImpl;
import com.jxut.model.PageUtil;
import com.jxut.model.Type;
import com.jxut.model.User;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name= "UserServlet" ,value="/UserServlet" )
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");//设置相应类型为html,编码为utf-8
        String method = request.getParameter("method");
        if("login".equals(method)){
            login(request,response);//登录
        }else if("logout".equals(method)){
            logout(request,response);//登出
        }else if ("getAll".equals(method)) {
            getAll(request,response);//用户管理/获取全部用户
        }else if("getUserById".equals(method)){
            getUserById(request,response);//查看用户信息
        }
        else if ("preUser".equals(method)) {
            preUser(request,response);//添加用户前的准备
        }else if ("addUser".equals(method)) {
            addUser(request,response);//添加用户
        }else if ("deleteUser".equals(method)) {
            deleteUser(request, response);//删除用户
        }else if ("updateUser".equals(method)) {
            updateUser(request, response);//修改用户信息
        }else if("userAddO".equals(method)){
            userAddO(request, response);//添加用户+头像上传
        }else if("title_download".equals(method)) {
            title_download(request, response);
        }

    }
    protected void  title_download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
        protected void  userAddO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   User user = new User();
        //创建磁盘对象 指向磁盘
       DiskFileItemFactory df = new DiskFileItemFactory();
       //创建文件上传对象
         ServletFileUpload sfu = new ServletFileUpload(df);
            //解析request
        try {
            List<FileItem> list = sfu.parseRequest(request);
            //遍历集合
            for (FileItem item : list) {
                //判断是否是普通表单项
                if (item.isFormField()) {
                    //获取表单项的名称
                    String name = item.getFieldName();
                    //获取表单项的值
                   if("userId".equals(name)) {
                          user.setUserName(item.getString("utf-8"));
                   }else if("userpassword".equals(name)) {
                       user.setPassword(item.getString("utf-8"));
                   }else if("userName".equals(name)) {
                       user.setRealName(item.getString("utf-8"));
                     }else if("userSex".equals(name)) {
                       user.setSex(item.getString("utf-8"));
                   }else if("date".equals(name)) {
                       user.setBirthday(Date.valueOf(item.getString("utf-8")));
                   }else if("userphone".equals(name)) {
                       user.setTel(item.getString("utf-8"));
                   }else if("userAddress".equals(name)) {
                       user.setAddress(item.getString("utf-8"));
                   }else if("userlei".equals(name)) {
                       user.setType(item.getString("utf-8"));
                   }
                }else {
                    //文件域，获取文件名
                    String fileName = item.getName();
                    System.out.println(fileName);
                    // 获取文件名后缀，以最后一个.为标识
                        String fileType = fileName.substring(fileName.lastIndexOf("."));
                    System.out.println(fileName.lastIndexOf("."));
                    //获取毫秒数 从1970年1月1日0时0分0秒开始到现在的毫秒数，以此分辨文件的唯一性
                    long time = System.currentTimeMillis();
                    //指定文件目录
                    String path = this.getServletContext().getRealPath("/upload/userHb/");
                    //如果文件夹不存在 创建文件夹
                    File file = new File(path);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    //创建复制后的文件保存
                    File newFile = new File(path, user.getUserName()+time + fileType);
                    try {
                        item.write(newFile);//写入文件
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    user.setPic("upload/userHb/" +user.getUserName()+ time + fileType);//夹件夹+用户名+时间+文件后缀
                }
            }
            UserDao userDao = new UserDaoImpl();
           boolean result = userDao.addUser(user);
           if (result == true) {
               response.sendRedirect("UserServlet?method=getAll");
           } else {
               request.setAttribute("msg", "添加失败");
               response.sendRedirect("UserServlet?method=preUser");
           }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
        protected void  getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserById(id);
        TypeDao typeDao = new TypeDaoImpl();
        List<Type> types = typeDao.getType();
        request.setAttribute("types",types);
        request.setAttribute("user",user);
        //转发到查看用户信息页面或者修改用户信息页面
        if("update".equals(request.getParameter("tiaozhuan"))){
            request.getRequestDispatcher("userUpdate.jsp").forward(request,response);
        }else if("view".equals(request.getParameter("tiaozhuan"))){
                request.getRequestDispatcher("userView.jsp").forward(request,response);
            }

    }
        protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDaoImpl();
        boolean flag = userDao.deleteUserByid(id);
        if(flag){
            response.sendRedirect("UserServlet?method=getAll");
        }else{
            request.setAttribute("msg","删除失败");
        }
    }
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id")) ;
        String realName = request.getParameter("realName");
        String  sex =request.getParameter("sex");
        String tel =request.getParameter("tel");
        Date birthday= Date.valueOf(request.getParameter("birthday"));
        String address = request.getParameter("userAddress");
        String type = request.getParameter("type");

        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setRealName(realName);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setTel(tel);
        user.setAddress(address);
        user.setType(type);
        user.setId(id);
       userDao.updateUser(user);
        response.sendRedirect("UserServlet?method=getAll");


    }
        protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username = request.getParameter("userId");
       String realname = request.getParameter("userName");
       String sex =request.getParameter("userSex");
       String password = request.getParameter("userpassword");
       Date birthday = Date.valueOf(request.getParameter("data"));
         String tel = request.getParameter("userphone");
            String address = request.getParameter("userAddress");
            String type = request.getParameter("userlei");
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setUserName(username);
        user.setRealName(realname);
        user.setSex(sex);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setTel(tel);
        user.setAddress(address);
        user.setType(type);
        boolean ru=    userDao.addUser(user);
        if (ru=true) {
            response.sendRedirect("UserServlet?method=getAll");
        }else {
            response.sendRedirect("UserServlet?method=preUser");
        }
       // response.sendRedirect("userList.jsp");
    }
    protected void preUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeDao typedao = new TypeDaoImpl();
        List<Type> types = typedao.getType();
        HttpSession session = request.getSession();
        session.setAttribute("typeList",types);
        response.sendRedirect("userAdd.jsp");
    }
        protected void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PageUtil page = new PageUtil();
            PrintWriter out = response.getWriter();
            Map map = new HashMap();
            String p = request.getParameter("pageNo");//getParamter('pageNo'),为空也可以，可以再做后续判断
            String realName = request.getParameter("realName");
            map.put("realName",realName);
            int currentPage = 1; //pageNo为空时，默认为1
            if(!StringUtils.isNullOrEmpty(p)) {  //如果page不为空，则赋值给currentPage
              currentPage = Integer.parseInt(p);
            }
               UserDao userDao = new UserDaoImpl();
            page.setPageSize(8);
            page.setTotalSize(userDao.getAllCount(map));
            page.setTotalPage();
            if (currentPage > page.getTotalPage()) {
                currentPage =1;//如果当前页大于总页数，则跳转到第一页，模糊查询时
            }
            page.setCurrentPage(currentPage);
            List<User> users = userDao.getAll(page,map);
            request.setAttribute("users",users);
            request.setAttribute("page",page);
            request.setAttribute("realName",realName);
            request.getRequestDispatcher("userList.jsp").forward(request, response);

    }
        protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
        protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao dao  = new UserDaoImpl();
        User user  = new User();
        user.setUserName(userName);
        user.setPassword(password);
        User u=dao.login(user);

        if( u!=null){
            HttpSession session = request.getSession();
            session.setAttribute("loginValue",u);
            response.sendRedirect("index.jsp");
            request.setAttribute("msg","登录成功");
        }else {
            response.sendRedirect("login.jsp");
            request.setAttribute("msg","登录失败，请检查用户名或密码");
        }

    }


    }
