package web.lab;

import web.lab.model.Area;
import web.lab.model.DefaultArea;
import web.lab.model.HistoryRecord;
import web.lab.model.Point;
import web.lab.validation.Limiter;
import web.lab.validation.LimiterImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Point> points;
        try {
            points = buildPoints(request);
        } catch (IllegalArgumentException e) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        Area area = new DefaultArea();
        updateHistory(request, points, area);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<html lang=\"ru\">");

        writer.println("<head>");
        writer.println("<link rel=\"shortcut icon\" href=\"img/favicon.ico\">");
        writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">");
        writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/result.css\">");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<div id=\"header\">");
        writer.println("<span id=\"author-name\" class=\"header-left-element header-content\">Неманков Илья Олегович</span>");
        writer.println("<span id=\"author-group\" class=\"header-center-element header-content\">P3211</span>");
        writer.println("<span id=\"lab-variant\" class=\"header-rigth-element header-content\">Вариант 666666</span>");
        writer.println("</div>");

        writer.println("<div id=\"result-container\" class=\"horisontal-centering-container\">");
        writer.println("<h1>Результаты последнего запроса</h1>");
        writer.println("<a href=\"/\" class=\"return-link\">Вернуться на главную</a>");
        writer.println("<div id=\"result-table\" class=\"table\">");
        for (Point point : points) {
            writer.println("<div class=\"table-tr result-table-tr\">");
            writer.println("<div class=\"table-td\">X: </div>");
            writer.println("<div class=\"table-td result-value result-x\">");
            writer.println(point.getX());
            writer.println("</div>");

            writer.println("<div class=\"table-td\">Y: </div>");
            writer.println("<div class=\"table-td result-value result-y\">");
            writer.println(point.getY());
            writer.println("</div>");

            writer.println("<div class=\"table-td\">R: </div>");
            writer.println("<div class=\"table-td result-value result-r\">");
            writer.println(point.getR());
            writer.println("</div>");

            writer.println("<div class=\"table-td\">Попадание: </div>");
            writer.println("<div class=\"table-td result-value result-hit\">");
            writer.println(area.hit(point) ? "Да" : "Нет");
            writer.println("</div>");
            writer.println("</div>");
        }
        writer.println("</div>");
        writer.println("</div>");

        writer.println("</body>");
        writer.println("</html>");
    }

    private List<Point> buildPoints(HttpServletRequest request) {
        List<Point> points = new ArrayList<Point>();
        String[] arrayY = request.getParameterValues("Y");
        if (request.getParameter("X").length() > 10 || request.getParameter("R").length() > 10) {
            throw new IllegalArgumentException();
        }
        double x, r;
        try {
            x = Double.parseDouble(request.getParameter("X"));
            r = Double.parseDouble(request.getParameter("R"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        boolean isForm;
        try {
            if (request.getParameter("isForm") != null || Boolean.parseBoolean(request.getParameter("isForm")) == true) {
                isForm = true;
            } else {
                isForm = false;
            }
        } catch (NumberFormatException e) {
            isForm = false;
        }
        Limiter limiter = new LimiterImpl();
        for (int i = arrayY.length - 1; i >= 0; i--) {
            try {
                if (arrayY[i].length() > 10) {
                    continue;
                }
                Point point = new Point(x, Double.parseDouble(arrayY[i]), r);
                if (!isForm || limiter.isInLimits(point)) {
                    points.add(point);
                }
            } catch (NumberFormatException e) {

            }
        }
        return points;
    }

    private void updateHistory(HttpServletRequest request, List<Point> points, Area area) {
        List<HistoryRecord> records = new ArrayList<HistoryRecord>();
        for (Point point : points) {
            records.add(new HistoryRecord(point, area.hit(point)));
        }
        List<HistoryRecord> history = (List<HistoryRecord>)request.getSession().getAttribute("history");
        if (history == null) {
            history = Collections.synchronizedList(new ArrayList<HistoryRecord>());
            request.getSession().setAttribute("history", history);
        }
        Collections.reverse(records);
        history.addAll(records);
    }

}
