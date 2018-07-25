<%@include file='owner_header.jsp' %>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
    <form name=save action='owner_bus_stop_info_update.do' method='post' enctype='multipart/form-data' >
    <logic:notEmpty name='list0'>
        <logic:iterate id="list0" name="list0">

            <div class='form-group'>
                <label>Stop</label>
                <select name='stop_id' req='novalue' minm='1' maxm='10' class='form-control'>
                    <option value='<bean:write name="list0" property="stop_id"/>'>Select Stop</option>
                        <logic:iterate name="list1" id="list">
                            <option value="<bean:write name="list" property="id"/>"> <bean:write name="list" property="stop_name"/></option>
                        </logic:iterate>
                    
                </select>
            </div>

            <div class='form-group'>
                <label>Order</label>
                <input type='text'  value='<bean:write name="list0" property="stop_order"/>' name='stop_order' req='number' minm='1' maxm='10' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Stop time</label>
                <input type='text' value='<bean:write name="list0" property="stop_time"/>' name='stop_time' req='novalue' minm='1' maxm='10' class='form-control'/>
            </div>

            <input type='hidden' name='eby' value="id=<bean:write name='list0' property='id'/>"/>
            <input type='hidden' name='trip_id' value="id=<bean:write name='list0' property='trip_id'/>"/>
            <input type='hidden' name='gby' value="StopsInfo" />
            <input type='hidden' name='gby' value="BusStopInfo:trip_id=<bean:write name='list0' property='trip_id'/>"/>
            <input type='hidden' name='page' value='owner_bus_stop_info_update'/>
        </logic:iterate>
    </logic:notEmpty>
    <input type='reset' class='btn' value='Clear'/><input type='submit' class='btn' value='Update'/>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>