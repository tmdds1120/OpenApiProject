package domain.servlet;

import domain.dao.HistoryDAO;
import domain.dao.WifiDAO;
import domain.vo.NearVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/near-wifi.do")
public class NearFindServlet extends HttpServlet {

    private final WifiDAO wifiDAO;
    private final HistoryDAO historyDAO;

    public NearFindServlet() {
        this.wifiDAO = new WifiDAO();
        this.historyDAO = new HistoryDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Double xCoordinate = Double.valueOf(req.getParameter("x-coordinate"));
        Double yCoordinate = Double.valueOf(req.getParameter("y-coordinate"));


        List<NearVO> nearWifiList = null;

        try {
            nearWifiList = wifiDAO.NearFindInfo(xCoordinate, yCoordinate);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            historyDAO.saveHistory(yCoordinate, xCoordinate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("nearWifiList", nearWifiList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, res);
    }
}
