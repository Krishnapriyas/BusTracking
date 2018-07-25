<%@page import="java.util.Random"%>
<%@include file="owner_header.jsp" %>
<form name=save action='owner_bus_stop_info_save.do' method='post' enctype='multipart/form-data'>
    <div class="col-md-7 col-md-offset-3 bann-info wow fadeInRight animated" data-wow-delay=".5s">
        <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
            <div class='form-group'>
                <label>Stop</label>
                <select name='stop_id' req='number' minm='1' maxm='10' class='form-control'>
                    <option >Select Stop</option>
                <logic:notEmpty name="list0">
                    <logic:iterate name="list0" id="list">
                        <option value="<bean:write name='list' property='id'/>"> <bean:write name="list" property="stop_name"/></option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
        </div>
        <div class='form-group'>
            <label>Trip</label>
            <select name='trip_id' req='number' minm='1' maxm='10' class='form-control'>
                <option >Select Trip</option>
                <logic:notEmpty name="list1">
                    <logic:iterate name="list1" id="list">
                        <option value="<bean:write name='list' property='id'/>">From&nbsp;<bean:write name="list" property="bus_start"/>&nbsp;to&nbsp;<bean:write name="list" property="bus_stop"/></option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
        </div>
        <div class='form-group'>
            <label>Order</label>
            <input type='text' name='stop_order' req='number' minm='1' maxm='10' class='form-control'/>
        </div>
        <div class='form-group'>
            <label>Stop time</label>
            <input type='time' name='stop_time' req='novalue' minm='1' maxm='10' class='form-control'/>
        </div>
        <input type='hidden' name='random' value='<%=new Random().nextInt()%>'/>
        <input type='hidden' name='page' value='owner_add_bus_stop_view'/>
        <input type='hidden' name='gby' value='BusInfo:owner_id=<%=loginId%>'/>
        
        <div class="sear">
            <button type="submit" class="seabtn">Add Stop</button>
        </div>
    </div>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>