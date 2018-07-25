package com.st.webservlet;

import com.st.bean.BusInfo;
import com.st.bean.BusStopInfo;
import com.st.bean.BusTripInfo;
import com.st.bean.StopsInfo;
import com.st.db.Manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BusListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Manager manager = new Manager();
            JSONArray array = new JSONArray();
            StopsInfo stopsInfo1 = (StopsInfo) manager.getBean(StopsInfo.class, "stop_name", request.getParameter("source"));
            StopsInfo stopsInfo2 = (StopsInfo) manager.getBean(StopsInfo.class, "stop_name", request.getParameter("destination"));
            int start = stopsInfo1.getId();
            int stop = stopsInfo2.getId();
            List<BusStopInfo> list = (List<BusStopInfo>) manager.list(BusStopInfo.class, "stop_id", start);
            for (BusStopInfo stopInfo : list) {
                int tripId = stopInfo.getTrip_id();
                int stopOrder = stopInfo.getStop_order();
                String sql = "select stop_order, trip_id from bus_stop_info "
                        + "where trip_id = " + tripId + " and stop_id = " + stop + " and stop_order > " + stopOrder;
                ArrayList<HashMap> arrayList = (ArrayList<HashMap>) manager.join(sql);
                for (HashMap map : arrayList) {
                    List<BusTripInfo> list1 = (List<BusTripInfo>) manager.list(BusTripInfo.class, "id", map.get("trip_id"));
                    for(BusTripInfo busTripInfo : list1 ) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("source", busTripInfo.getBus_start());
                        jSONObject.put("destination", busTripInfo.getBus_stop());
                        List<BusInfo> list2 = (List<BusInfo>)manager.list(BusInfo.class, "id", busTripInfo.getBus_id());
                        for(BusInfo busInfo : list2){
                            jSONObject.put("busName", busInfo.getBus_name());
                            jSONObject.put("busId", busInfo.getId());
                            array.add(jSONObject);
                        }
                    }
                }
            }
            out.write(array.toJSONString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
