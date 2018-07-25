<%@include file='owner_header.jsp' %>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
<form name=save action='owner_bus_trip_info_update.do' method='post' enctype='multipart/form-data' >
<logic:notEmpty name='list0'>
<logic:iterate id="list" name="list0"><div class='form-group'>
<label>Id</label>
<input type='text' value='<bean:write name="list" property="id"/>' name='id' req='text' minm='1' maxm='10' class='form-control'/>
</div>
<div class='form-group'>
<label>Source</label>
<input type='text' value='<bean:write name="list" property="bus_start"/>' name='bus_start' req='novalue' minm='3' maxm='100' class='form-control'/>
</div>
<div class='form-group'>
<label>Destination</label>
<input type='text' value='<bean:write name="list" property="bus_stop"/>' name='bus_stop' req='novalue' minm='3' maxm='100' class='form-control'/>
</div>
<div class='form-group'>
<label>Trip Time</label>
<input type='text' value='<bean:write name="list" property="trip_time"/>' name='trip_time' req='text' minm='1' maxm='10' class='form-control'/>
</div>

<input type='hidden' name='eby' value="id=<bean:write name='list' property='id'/>"/>
<input type='hidden' name='gby' value="BusTripInfo:id=<bean:write name='list' property='id'/>"/>
<input type='hidden' name='page' value='owner_bus_trip_info_update'/>
</logic:iterate>
</logic:notEmpty>
<input type='reset' class='btn' value='Clear'/><input type='submit' class='btn' value='Update'/>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>