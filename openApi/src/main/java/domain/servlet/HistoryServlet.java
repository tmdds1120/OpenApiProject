package domain.servlet;

import domain.dao.HistoryDAO;
import domain.vo.HistoryVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/history-list.do")
public class HistoryServlet extends HttpServlet {

    private final HistoryDAO historyDAO = new HistoryDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<HistoryVO> historyList = null;

        try {
            historyList = historyDAO.getHistoryList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("historyList", historyList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/history.jsp");
        requestDispatcher.forward(req, res);
    }


}
