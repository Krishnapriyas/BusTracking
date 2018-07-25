<%@page import="java.util.Random"%>
<%@include file="owner_header.jsp" %>
<form name=save action='owner_bus_trip_info_save.do' method='post' enctype='multipart/form-data'>
    <div class="col-md-7 col-md-offset-3 bann-info wow fadeInRight animated" data-wow-delay=".5s">
        <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
        <input type="hidden" name="bus_id" value="<%=request.getParameter("busId")%>" />
        <div class="ban-top">

            <label>Source</label>
            <select name='bus_start' req='novalue' minm='1' maxm='10' class='form-control'>
                <option >Select Source</option>
                <logic:notEmpty name="list0">
                    <logic:iterate name="list0" id="list">
                        <option value="<bean:write name="list" property="id"/>"> <bean:write name="list" property="stop_name"/></option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
        </div>
        <div class="ban-top">

            <label>Destination</label>
            <select name='bus_stop' req='novalue' minm='1' maxm='10' class='form-control'>
                <option >Select Destination</option>
                <logic:notEmpty name="list0">
                    <logic:iterate name="list0" id="list">
                        <option value="<bean:write name='list' property='id'/>"> <bean:write name="list" property="stop_name"/></option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
        </div>
        <div class="ban-top">
            <label>No:of Stops</label>
            <input type='text' name='stop_num' req='number' minm='1' maxm='10' class='form-control'/>
        </div>
        <div class="ban-top">
            <label>Trip Start Time</label>
            <input type='time' name='trip_time' req='text' minm='1' maxm='10' class='form-control'/>
        </div>
        <div class="ban-top">
            <label>Trip End Time</label>
            <input type='time' name='trip_end_time' req='text' minm='1' maxm='10' class='form-control'/>
        </div>
        <input type='hidden' name='random' value='<%=new Random().nextInt()%>'/>
        <input type='hidden' name='page' value='select_bus'/>
        <input type='hidden' name='gby' value='BusInfo:owner_id=<%=loginId%>'/>
        <div class="sear">
            <button type="submit" class="seabtn">Add Trip</button>
        </div>
    </div>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>