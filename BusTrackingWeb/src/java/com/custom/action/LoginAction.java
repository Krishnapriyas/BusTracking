package com.custom.action;

import com.st.bean.LoginInfo;
import com.st.bean.OwnerInfo;
import com.st.db.Manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends org.apache.struts.action.Action {

    private static String SUCCESS = "index";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Manager manager = new Manager();
        HttpSession session = request.getSession();
//=====================developer editing section==========================
//             LoginInfo lf = (LoginInfo)form;
//                   String[] key = {"username","password"};
//                 String[] value = {lf.getUserName,lf.getPassword()};
//                  lf = (LoginInfo)manager.getExists(lf.getClass(), key, value);
//            if(lf!=null && bean.getType().equals("A")){
//                session.setAttribute("LOGINBEAN", bean);
//                SUCCESS = "";
//            }
//=====================developer editing section==========================
        LoginInfo lf = (LoginInfo) form;
        String[] key = {"username", "password"};
        String[] value = {lf.getUsername(), lf.getPassword()};
        lf = (LoginInfo) manager.getExists(lf.getClass(), key, value);
        if (lf != null && lf.getType().equals("admin")) {
            session.setAttribute("LOGINBEAN", lf);
            SUCCESS = "admin_home";
        }
        if (lf != null && lf.getType().equals("owner")) {
            if (lf.getStatus().equals("approved")) {
                session.setAttribute("LOGINBEAN", lf);
                session.setAttribute("LOGINID", lf.getId());
                OwnerInfo ownerInfo = (OwnerInfo) manager.getBean(OwnerInfo.class, "login_id", lf.getId());
                session.setAttribute("OWNER_NAME", ownerInfo.getOwner_name());
                SUCCESS = "owner_home";
            }
        }
        return mapping.findForward(SUCCESS);
    }

}
