<%@include file='common_header.jsp' %>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
<form name=save action='common_owner_info_update.do' method='post' enctype='multipart/form-data' >
<logic:notEmpty name='list0'>
<logic:iterate id="list" name="list0"><div class='form-group'>
<label>Id</label>
<input type='text' value='<bean:write name="list" property="id"/>' name='id' req='text' minm='1' maxm='10' class='form-control'/>
</div>
<div class='form-group'>
<label>Owner Address</label>
<textarea name='owner_address' req='novalue' minm='50' maxm='100' class='form-control'>
<bean:write name="list" property="owner_address"/>'</textarea>
</div>
<div class='form-group'>
<label>Owner Email</label>
<input type='text' value='<bean:write name="list" property="owner_email"/>' name='owner_email' req='email' minm='3' maxm='50' class='form-control'/>
</div>
<div class='form-group'>
<label>Owner Name</label>
<input type='text' value='<bean:write name="list" property="owner_name"/>' name='owner_name' req='text' minm='3' maxm='50' class='form-control'/>
</div>
<div class='form-group'>
<label>Operator Phone</label>
<input type='text' value='<bean:write name="list" property="owner_phone"/>' name='owner_phone' req='number' minm='10' maxm='10' class='form-control'/>
</div>

<input type='hidden' name='eby' value="id=<bean:write name='list' property='id'/>"/>
<input type='hidden' name='gby' value="OwnerInfo:id=<bean:write name='list' property='id'/>"/>
<input type='hidden' name='page' value='common_owner_info_update'/>
</logic:iterate>
</logic:notEmpty>
<input type='reset' class='btn' value='Clear'/><input type='submit' class='btn' value='Update'/>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="common_footer.jsp" %>