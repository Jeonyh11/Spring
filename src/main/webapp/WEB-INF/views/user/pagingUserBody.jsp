<%@page import="org.apache.jasper.tagplugins.jstl.core.Choose"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			
			<c:forEach items="${userList }" var="user">
				<tr class ="user" data-userid="${user.userid }">
					<td>${user.userid }</td>
					<td>${user.usernm }</td>
					<td>${user.alias }</td>
<%-- 					<td>${user.getReg_dt_fmt() }</td> --%>
					<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd"/></td>
				</tr>
			</c:forEach>
		
