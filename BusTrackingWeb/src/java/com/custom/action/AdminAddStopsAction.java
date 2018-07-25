package com.custom.action;

import com.st.bean.StopsInfo;
import com.st.db.Manager;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AdminAddStopsAction extends org.apache.struts.action.Action {

    private static String success = "";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try (PrintWriter out = response.getWriter()) {
            Manager manager = new Manager();
            JSONArray array = new JSONArray();
            String stop_name = request.getParameter("stop_name");
            System.out.println("Stop Name : " + stop_name);
            if (stop_name != null) {
                StopsInfo stopsInfo = new StopsInfo();
                stopsInfo.setStop_name(stop_name);
                manager.save(stopsInfo);
            }
            List<StopsInfo> list = (List<StopsInfo>) manager.list(StopsInfo.class);
            for (StopsInfo info : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("stop_name", info.getStop_name());
                array.add(jSONObject);
            }
            out.println(array.toJSONString());
            out.close();
            success = "admin_stops_info_save";
        }
        return mapping.findForward(success);
    }
}
