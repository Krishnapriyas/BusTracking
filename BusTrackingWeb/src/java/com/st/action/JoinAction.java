package com.st.action;

import com.custom.action.CustomAction;
import com.st.db.Manager;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JoinAction extends org.apache.struts.action.Action {
    private static final String SUCCESS = "success";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /* forwarding */
 /* forwarding */
        String page = request.getParameter("page");
        if (page == null) {
            Object attribute = request.getAttribute("page");
            if (attribute == null) {
                throw new ForwardNotFound("page=null ;\n page parameter have a value");
            }
        }
        /* forwarding */
        String errorMsg = "Failed the given operation.";
        if (!mapping.getPath().contains("_")) {
            throw new MappingPathException("path!contains '_' ;\n path value must have underscore \n ex: data_save, student_save");
        }
        if (!mapping.getPath().endsWith("_save") && !mapping.getPath().endsWith("_update")
                && !mapping.getPath().endsWith("_delete") && !mapping.getPath().endsWith("_get") && !mapping.getPath().endsWith("_join")) {
            throw new MappingPathException("No operation Found \n path value must be end with _save or _update or _delete");
        }
        URL resource = getServlet().getServletContext().getResource("/WEB-INF/struts-config.xml");
        BeanLoader.readXML(resource);
        if (mapping.getPath().endsWith("join_save")) {
            Manager manager = new Manager();
            int save = manager.save(form);
            listValues(request);
            if (save > 0) {
                errorMsg = "Successfully inserted the data !";
            } else if (save == -1) {
                errorMsg = "The data already exists !";
            }
            new CustomAction().execute(form, request,(getServlet().getServletContext().getRealPath("") + File.separator));
            request.setAttribute("error", errorMsg);
        }
        if (mapping.getPath().endsWith("_update")) {
            Manager manager = new Manager();
            String[] keyValue = getKeyValue(request, "eby");
            List listbykeyValue = manager.listbykeyValue(form.getClass(), keyValue);
            for (Object bean : listbykeyValue) {
                if (manager.update(form, bean, "AND")) {
                    request.setAttribute("error", "Successfully updated the data !");
                }
            }
            listValues(request);
        }
        if (request.getParameterValues("dby") != null) {

            Manager manager = new Manager();
            String[] keyValue = getKeyValue(request, "dby");
            List listbykeyValue = manager.listbykeyValue(form.getClass(), keyValue);
            for (Object bean : listbykeyValue) {
                if (manager.delete(bean)) {
                    request.setAttribute("error", "Successfully deleted the data !");
                }
            }
            listValues(request);
        }
        listValues(request);

        return mapping.findForward(page);
    }

    private void listValues(HttpServletRequest request) {
        if (request.getParameterValues("gby") != null) {
            String[] values = request.getParameterValues("gby");
            try {
                XMLReader xmlReader = new XMLReader();
                URL resource = getServlet().getServletContext().getResource("/WEB-INF/struts-config.xml");
                if (resource != null) {
                    xmlReader.readXML(resource);
                    Manager manager = new Manager();
                    int i = 0;
                    for (String value : values) {//gby=LoginBean:id=1,name=harris
                        if (value.contains(":")) {
                            String old = value;
                            value = value.substring(0, value.indexOf(":"));
                            String qery = old.substring(old.indexOf(":") + 1);
                            Class<?> forName = Class.forName(xmlReader.getClassName(value));
                            String[] keyValue = getKeyValue(qery);
                            List list = manager.listbykeyValue(forName, keyValue);
                            request.setAttribute("list" + i, list);
                        } else {
                            System.out.println("class name :" + value + "  : " + xmlReader.getClassName(value));
                            Class<?> forName = Class.forName(xmlReader.getClassName(value));
                            List list = manager.list(forName);
                            request.setAttribute("list" + i, list);
                        }
                        i++;
                    }
                }

            } catch (MalformedURLException ex) {
            } catch (ClassNotFoundException ex) {

            }
        }
    }

    private String[] getKeyValue(HttpServletRequest request, String param) {
        String[] values = request.getParameterValues(param);
        StringBuilder sb = new StringBuilder();
        for (String parameterValue : values) {
            sb.append(parameterValue.replaceAll("=", ","));
        }

        return sb.toString().split(",");
    }

    private String[] getKeyValue(String param) {
        param = param.replaceAll("=", ",");
        return param.split(",");
    }

}
