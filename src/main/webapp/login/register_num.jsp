<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userbean" class="pack.user.UserBean" />
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="userMgr" class="pack.user.UserMgr" />

<% 

int newNo = userMgr.CurrentMaxNo() + 1;
userbean.setNo(newNo);

userMgr.UserInsert(userbean);
%>
