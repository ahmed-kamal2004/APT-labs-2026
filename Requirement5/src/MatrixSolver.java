import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.*;
import javax.servlet.http.*;
public class MatrixSolver  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String matrix = request.getParameter("matrix");
        String rows[] = matrix.split("\n");

        String isIdentity = request.getParameter("IsIdentity");
        String GetTranspose = request.getParameter("Transpose");

        StringBuilder output = new StringBuilder("<h1>Result</h1><h2>Original Matrix</h2>");
        String[][] elements = new String[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++) {
            List<String> stringElements = Arrays.stream(rows[i].split(" ")).toList();
            for (int j = 0; j < rows.length; j++) {

                elements[i][j] = stringElements.get(j);
                output.append(elements[i][j]);
                output.append(" ");
            }
            output.append("<br>");
        }
        if (Objects.equals(GetTranspose, "on")) {
            output.append("<br><h2>Transpose</h2>");
            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows.length; j++) {
                    output.append(elements[j][i]);
                    output.append(" ");
                }
                output.append("<br>");
            }

        }
        if (Objects.equals(isIdentity, "on")) {
            output.append("<br><h2>isIdentity</h2>");
            int counter = 0;
            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows.length; j++) {
                    if (i == j && elements[i][j].equals("1")) {
                        counter++;
                    }
                }
            }
            if (counter == rows.length) {
                output.append("<h5>true</h5>");
            } else {
                output.append("<h5>false</h5>");
            }
        }
        response.getWriter().println("<!doctype html><html> <body><center>" + output.toString() + "</center></body></html>");
    }
}
