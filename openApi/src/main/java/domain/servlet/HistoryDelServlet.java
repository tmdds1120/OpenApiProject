package domain.servlet;

import domain.dao.HistoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-history.do")
public class HistoryDelServlet extends HttpServlet {

    private final HistoryDAO historyDAO = new HistoryDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));

        try {
            historyDAO.deleteFindById(deleteId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
