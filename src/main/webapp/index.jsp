<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="web.lab.model.HistoryRecord"%>
<%
    List<HistoryRecord> history = (List<HistoryRecord>) session.getAttribute("history");
%>
<!DOCTYPE html>
<html lang="ru">

<head>
	<meta charset="utf-8"/>
	<title>Web-программирование, лабораторная работа #2</title>
	<link rel="shortcut icon" href="img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/result.css">
</head>

<body>
    <div id="header">
        <span id="author-name" class="header-left-element header-content">Неманков Илья Олегович</span>
        <span id="author-group" class="header-center-element header-content">P3211</span>
        <span id="lab-variant" class="header-rigth-element header-content">Вариант 666666</span>
    </div>
	<div id="workspace-container">
		<div id="task-chart-container" class="workspace-item-container">
			<h1>Область</h1>
			<div class="horisontal-centering-container">
				<canvas id="task-chart"></canvas>
				<span id="warning-container-chart" class="warning-container"></span>
			</div>
		</div>
		<div id="computation-form-container" class="workspace-item-container">
			<h1>Параметры</h1>
			<form id="computation-form" method="post" action="" onsubmit="return validate(this);">
				<div class="parameter-form-container">
					<div class="parameter-container">
						<label for="X-param" class="parameter-label">X:</label>
						<input id="X-param" type="text" name="X" placeholder="(-3 ... 3)" maxlength="10">
						<span id="warning-container-X" class="warning-container"></span>
					</div>
					<div class="parameter-container">
						<label class="parameter-label">Y:</label>
						
						<div id="parameter-container-Y">
							<label for="Y-param--4" class="checkbox-button-label">-4</label>
							<input id="Y-param--4" type="checkbox" name="Y" value="-4"/>

							<label for="Y-param--3" class="checkbox-button-label">-3</label>
							<input id="Y-param--3" type="checkbox" name="Y" value="-3"/>

							<label for="Y-param--2" class="checkbox-button-label">-2</label>
							<input id="Y-param--2" type="checkbox" name="Y" value="-2"/>

							<label for="Y-param--1" class="checkbox-button-label">-1</label>
							<input id="Y-param--1" type="checkbox" name="Y" value="-1"/>

							<label for="Y-param-0" class="checkbox-button-label">0</label>
							<input id="Y-param-0" type="checkbox" name="Y" value="0"/>
							
							<label for="Y-param-1" class="checkbox-button-label">1</label>
							<input id="Y-param-1" type="checkbox" name="Y" value="1"/>
							
							<label for="Y-param-2" class="checkbox-button-label">2</label>
							<input id="Y-param-2" type="checkbox" name="Y" value="2"/>
							
							<label for="Y-param-3" class="checkbox-button-label">3</label>
							<input id="Y-param-3" type="checkbox" name="Y" value="3"/>
							
							<label for="Y-param-4" class="checkbox-button-label">4</label>
							<input id="Y-param-4" type="checkbox" name="Y" value="4"/>
						</div>
						
						<span id="warning-container-Y" class="warning-container"></span>
						
					<div class="parameter-container">
						<label for="R-param" class="parameter-label">R:</label>
						<input id="R-param" type="text" name="R" placeholder="(2 ... 5)" maxlength="10">
						<span id="warning-container-R" class="warning-container"></span>
					</div>
					<input type="hidden" name="isForm" value="true">
					</div>
					<div class="horisontal-centering-container button-container">
						<button id="computation-form-button" class="submit-button" type="submit">Отправить</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="result-container" class="horisontal-centering-container">
		<h1>Результаты</h1>
		    <%
		        if (history != null) {
		            out.println("<div id=\"result-table\" class=\"table\">");
                    for(int i = history.size() - 1; i >= 0; i--) {
                        out.println("<div class=\"table-tr\">");
                        out.println("<div class=\"table-td\">X: </div>");
                        out.println("<div class=\"table-td result-value result-x\">");
                        out.println(history.get(i).getPoint().getX());
                        out.println("</div>");

                        out.println("<div class=\"table-td\">Y: </div>");
                        out.println("<div class=\"table-td result-value result-y\">");
                        out.println(history.get(i).getPoint().getY());
                        out.println("</div>");

                        out.println("<div class=\"table-td\">R: </div>");
                        out.println("<div class=\"table-td result-value result-r\">");
                        out.println(history.get(i).getR());
                        out.println("</div>");

                        out.println("<div class=\"table-td\">Попадание: </div>");
                        out.println("<div class=\"table-td result-value result-hit\">");
                        out.println(history.get(i).getHit() ? "Да" : "Нет");
                        out.println("</div>");
                        out.println("</div>");
                    }
                    out.println("</div>");
		        }
		    %>
	</div>

	<script src="js/jquery.min.js"></script>
    <script src="js/utils.js"></script>
	<script src="js/validation.js"></script>
	<script src="js/create-chart.js"></script>
	<script src="js/click-chart.js"></script>
</body>

</html>