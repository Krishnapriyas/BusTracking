package com.custom.action;

import com.st.db.Manager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JoinAction extends org.apache.struts.action.Action {

    private static String success = "";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Manager manager = new Manager();
        if ("bus".equalsIgnoreCase(request.getParameter("action"))) {
            List list = manager.join("select b.id, b.bus_description, b.bus_name, b.bus_number, "
                    + "b.owner_id,\n"
                    + "o.owner_name from bus_info b, owner_info o "
                    + "where b.owner_id = o.login_id");
            request.setAttribute("list0", list);
            success = "admin_bus_info_view";

        }
        if ("stops".equalsIgnoreCase(request.getParameter("action"))) {
            int tripId  = Integer.parseInt(request.getParameter("trip_id"));
            String page  = request.getParameter("page");
            List list = manager.join("select b.id, b.trip_id, s.stop_name, "
                    + "b.stop_id, b.stop_order, b.stop_time\n"
                    + "from bus_stop_info b, stops_info s \n"
                    + "where s.id = b.stop_id and b.trip_id = " + tripId);
            request.setAttribute("list0", list);
            success = page;

        }
        return mapping.findForward(success);
    }
}
