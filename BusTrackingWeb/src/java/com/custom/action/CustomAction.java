package com.custom.action;

import com.st.bean.BusStopInfo;
import com.st.bean.BusTripInfo;
import com.st.bean.DriverInfo;
import com.st.bean.LoginInfo;
import com.st.bean.OwnerInfo;
import com.st.bean.StopsInfo;
import com.st.db.Manager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class CustomAction {

    public ActionForm execute(ActionForm form,
            HttpServletRequest request, String path) {
        Manager manager = new Manager();
        /* if (form instanceof LoginInfo) {
            LoginInfo bean = (LoginInfo) form;

            //==============================================================
                    String write = write(path + "IMAGES", bean.getFile().getFileName(), bean.getFile().getFileData(),false);
                    fileUpload(form,write,false);            
        bean.setFile_path(write);
                form = bean;
                //==============================================================

        }*/
        if (form instanceof OwnerInfo) {
            OwnerInfo ownerInfo = (OwnerInfo) form;
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUsername(ownerInfo.getUsername());
            loginInfo.setPassword(ownerInfo.getPassword());
            loginInfo.setStatus("pending");
            loginInfo.setType("owner");
            if (manager.save(loginInfo) > 0) {
                ownerInfo.setLogin_id(loginInfo.getId());
                form = ownerInfo;
            }
        } else if (form instanceof DriverInfo) {
            DriverInfo driverInfo = (DriverInfo) form;
            LoginInfo loginInfo = new LoginInfo();
            Random random = new Random();
            loginInfo.setUsername(driverInfo.getDriver_name().substring(0, 3) + random.nextInt(400));
            loginInfo.setPassword(String.valueOf(random.nextInt(8000)));
            loginInfo.setStatus("approved");
            loginInfo.setType("driver");
            if (manager.save(loginInfo) > 0) {
                driverInfo.setLogin_id(loginInfo.getId());
                form = driverInfo;
            }
        } else if (form instanceof BusTripInfo) {
            BusTripInfo busTripInfo = (BusTripInfo) form;
            int startStop = Integer.parseInt(busTripInfo.getBus_start());
            int endStop = Integer.parseInt(busTripInfo.getBus_stop());
            StopsInfo stopsInfo1 = (StopsInfo) manager.getBean(StopsInfo.class, "id", startStop);
            StopsInfo stopsInfo2 = (StopsInfo) manager.getBean(StopsInfo.class, "id", endStop);
            busTripInfo.setBus_start(stopsInfo1.getStop_name());
            busTripInfo.setBus_stop(stopsInfo2.getStop_name());
            manager.save(busTripInfo);
            
            BusStopInfo busStopInfo1 = new BusStopInfo();
            busStopInfo1.setStop_id(startStop);
            busStopInfo1.setStop_order(1);
            busStopInfo1.setTrip_id(busTripInfo.getId());
            busStopInfo1.setStop_time(busTripInfo.getTrip_time());
            manager.save(busStopInfo1);
            
            BusStopInfo busStopInfo2 = new BusStopInfo();
            busStopInfo2.setStop_id(endStop);
            busStopInfo2.setStop_order(busTripInfo.getStop_num());
            busStopInfo2.setStop_time(busTripInfo.getTrip_end_time());
            busStopInfo2.setTrip_id(busTripInfo.getId());
            form = busStopInfo2;

        }

        return form;

    }

    private String write(String path, String fileName, byte[] data, boolean changefilename) {
        try {
            if (fileName != null && !fileName.equals("")) {
                String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (changefilename) {
                    fileName = System.currentTimeMillis() + ext;
                }
                FileOutputStream fos = new FileOutputStream(file + File.separator + fileName);
                fos.write(data);
                fos.flush();
                fos.close();
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return fileName;
    }

    private ArrayList<String> fileUpload(ActionForm form, String path, boolean changefilename) throws IOException {
        Hashtable map = form.getMultipartRequestHandler().getFileElements();
        Enumeration enu = map.elements();
        ArrayList<String> listname = new ArrayList<>();
        while (enu.hasMoreElements()) {
            ArrayList<FormFile> list = (ArrayList<FormFile>) enu.nextElement();
            for (FormFile file : list) {
                listname.add(write(path, file.getFileName(), file.getFileData(), changefilename));
            }

        }
        return listname;
    }
}
