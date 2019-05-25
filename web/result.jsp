<%@ page import="model.Task" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Результат поиска</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
<%
    model.Task task = (Task) session.getAttribute("task");
    if (task == null){
%>
<div class="main">
    <p class="error" style="font-size: 18px; text-align: center">
        Результат не может быть получен
    </p>
    <p style="font-size: 18px; text-align: center">
        Вы были перенаправлены
    </p>
    <form action="index.jsp" method="get">
        <input class="button" type="submit" value="Назад"/>
    </form>
</div>
<script type="text/javascript">
    window.setTimeout(function () {
        window.location.href = "index.jsp";
    }, 5000);
</script>
<%
    }else{
%>
<div class="main">
    <h2>Результат поиска</h2>
    <form action="../index.jsp" method="get">
        <%if(task.getResult() != null){%>
        <i>
            строка <%=task.getSecond()%> входит в строку <%=task.getFirst()%> с позиции  <%=task.getResult()%>
        </i>
        <%}else{%>
        <i>
            строка <%=task.getSecond()%>  не входит в строку <%=task.getFirst()%>
        </i>
        <%}%>
        <input class="button" type="submit" value="Назад"/>
    </form>
    <form action="../result.xml" method="get">
        <input class="button" type="submit" value="Скачать"/>
    </form>
</div>
<%
    }
%>
</body>
</html>