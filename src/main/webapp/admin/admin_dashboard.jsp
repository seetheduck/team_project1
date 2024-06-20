<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="userDto" class="pack.admin.UserDto"/>
<jsp:useBean id="adminMgr" class="pack.admin.AdminMgr"/>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="../js/graph.js"></script>


<main class="main-content">
    <h2>대시보드</h2>
    <div style="width: 50%; height: 300px;">
        <canvas id="myChart" style="width: 100%"></canvas>
    </div>
    
</main>