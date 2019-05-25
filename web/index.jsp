<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 2019-05-17
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
  private static String checkString(String string){
    if (string == null){return null;}
    if (string.isEmpty()){return null;}
    else {return string;}
  }
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Поиск подстроки</title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <!--Подключаемый файл хранит таблицу стилей CSS, MIME-тип данных для внешнего документа, путь к файлу-->
  <script type="text/javascript" src="scripts/script.js"></script>>
</head>
<body>
<div class="main">
  <h2>Поиск места вхождения второй строки в первую</h2>
  <form action="index.jsp" method="get"><!--адрес программы, обрабатывающей данные; метод HTTP-->
    <%
      String first, second = null;
      boolean firstError, secondError;

      String firstString = request.getParameter("first");
      String secondString = request.getParameter("second");

      first = checkString(firstString);
      second = checkString(secondString);


      firstError = firstString != null && !firstString.isEmpty();
      secondError = secondString != null && !secondString.isEmpty();
    %>
    <p>Первая строка:</p>
    <label>
      <input id="first" type="text" name="first" value="<%=firstString == null ? "": firstString%>" autocomplete="off"
             title="Введите первую строку" placeholder="Введите первую строку">
    </label>
    <%
      if (secondError){
    %>
    <p class="error">Введите первое слово</p>
    <script type="text/javascript">selectFirstInput()</script>
    <%
      }
    %>
    <p>Вторая строка:</p>
    <label>
      <input id="second" type="text" name="second" value="<%=secondString == null ? "": secondString%>"
             autocomplete="off" title="Ввеедите вторую строку" placeholder="Введите вторую строку">
    </label>
    <%
      if (firstError){
    %>
    <p class="error">Введите второе слово</p>
    <script type="text/javascript">selectSecondInput()</script>
    <%
      }
      if (first != null && second != null){
        model.Task task = new model.Task(first, second);
//        session.setAttribute("task", task);
        request.getSession().setAttribute("task", task);
        response.sendRedirect("html/result.html");
        %>
        <%
      }
    %>
    <input class="button" type="submit" value="Поиск"><!--кнопка формата "отправить"-->
  </form>
</div>
</body>
</html>
