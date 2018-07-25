/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custom.action;

import com.st.bean.LoginInfo;
import com.st.db.Manager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Soubhagya-
 */
public class ApproveOwnerAction extends org.apache.struts.action.Action {

    private static String success = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Manager manager = new Manager();
        if ("approve".equals(request.getParameter("action"))) {
            int loginId = Integer.parseInt(request.getParameter("loginId"));
            String email = request.getParameter("email");
            LoginInfo loginInfo = (LoginInfo) manager.getBean(LoginInfo.class, "id", loginId);
            loginInfo.setStatus("approved");
            manager.update(loginInfo);
        }
        List ownerPendingList = manager.join("select o.login_id, o.id, o.owner_address,"
                + " o.owner_email, o.owner_name, o.owner_phone\n"
                + "from owner_info o, login_info l "
                + "where o.login_id = l.id and l.status = 'pending'");
        List ownerApprovedList = manager.join("select o.login_id, o.id, o.owner_address,"
                + " o.owner_email, o.owner_name, o.owner_phone\n"
                + "from owner_info o, login_info l "
                + "where o.login_id = l.id and l.status = 'approved'");
        request.setAttribute("list0", ownerPendingList);
        request.setAttribute("list1", ownerApprovedList);
        success = "common_owner_info_view";
        return mapping.findForward(success);
    }

}

