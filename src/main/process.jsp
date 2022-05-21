<%--
  Created by IntelliJ IDEA.
  User: dmytromykytiuk
  Date: 20.05.2022
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*,java.util.*"%>

<%
    String userid=request.getParameter("username");
    session.putValue("userid",userid);
    String password=request.getParameter("password");
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    Connection con ;
    try {
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rgrdb","root", "12345678");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    Statement st;
    try {
        st = con.createStatement();
    } catch (SQLException e) {
        out.println("2");
        throw new RuntimeException(e);
    }
    ResultSet rs= st.executeQuery("select * from clients where login='"+userid+"' and password='"+password+"'");
    ResultSet stat;
    try{
        rs.next();
        if(rs.getString("password").equals(password)&&rs.getString("login").equals(userid))
        {
            out.println("Ласкаво просимо "  + rs.getString("role"));
        }
        else{
            out.println("Invalid password or username.");
        }
    }
    catch (Exception e) {
        out.println("3");
        e.printStackTrace();
    }
%>