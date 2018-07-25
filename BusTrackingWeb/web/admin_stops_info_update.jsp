<%@include file='admin_header.jsp' %>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
<form name=save action='admin_stops_info_update.do' method='post' enctype='multipart/form-data' >
<logic:notEmpty name='list0'>
<logic:iterate id="list" name="list0"><div class='form-group'>
<label>Id</label>
<input type='text' value='<bean:write name="list" property="id"/>' name='id' req='text' minm='1' maxm='10' class='form-control'/>
</div>
<div class='form-group'>
<label>Stop name</label>
<input type='text' value='<bean:write name="list" property="stop_name"/>' name='stop_name' req='novalue' minm='2' maxm='100' class='form-control'/>
</div>

<input type='hidden' name='eby' value="id=<bean:write name='list' property='id'/>"/>
<input type='hidden' name='gby' value="StopsInfo:id=<bean:write name='list' property='id'/>"/>
<input type='hidden' name='page' value='admin_stops_info_update'/>
</logic:iterate>
</logic:notEmpty>
<input type='reset' class='btn' value='Clear'/><input type='submit' class='btn' value='Update'/>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="admin_footer.jsp" %>