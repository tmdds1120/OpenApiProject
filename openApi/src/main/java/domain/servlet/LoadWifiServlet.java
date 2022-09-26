package domain.servlet;

import domain.dao.WifiDAO;
import domain.dto.SetDTO;
import domain.util.OpenApiJson;
import domain.util.TotalCnt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/wifi-insert.do")
public class LoadWifiServlet extends HttpServlet {
    private final OpenApiJson openApiJson;
    private final WifiDAO wifiDAO;

    public LoadWifiServlet() {
        openApiJson = new OpenApiJson();
        wifiDAO = new WifiDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TotalCnt totalCnt = new TotalCnt();

        try {
            wifiDAO.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int num = totalCnt.getCnt();
        int start = 0, end = 999;

        for (int i = 0; i < num; i++) {
            SetDTO setDTO = openApiJson.getWifiJson(start, end);

            try {
                wifiDAO.insertWifi(setDTO.getJsonDtos());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            start += 500;
            end += 500;
        }

        req.setAttribute("totalCount", openApiJson.getWifiJson(0, 1).getTotalCount());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/wifi_load.jsp");
        requestDispatcher.forward(req, res);
    }
}
