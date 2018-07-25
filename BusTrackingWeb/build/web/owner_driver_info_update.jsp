<%@include file='owner_header.jsp' %>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
    <form name=save action='owner_driver_info_update.do' method='post' enctype='multipart/form-data' >
    <logic:notEmpty name='list0'>
        <logic:iterate id="list" name="list0">
            <div class='form-group'>
                <label>Name</label>
                <input type='text' value='<bean:write name="list" property="driver_name"/>' name='driver_name' req='text' minm='3' maxm='100' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Phone</label>
                <input type='text' value='<bean:write name="list" property="driver_phone"/>' name='driver_phone' req='number' minm='10' maxm='10' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Email</label>
                <input type='text' value='<bean:write name="list" property="driver_email"/>' name='driver_email' req='email' minm='3' maxm='100' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Address</label>
                <textarea name='driver_address' req='text' minm='3' maxm='100' class='form-control'><bean:write name="list" property="driver_address"/></textarea>
            </div>

            <input type='hidden' name='eby' value="id=<bean:write name='list' property='id'/>"/>
            <input type='hidden' name='gby' value="DriverInfo:id=<bean:write name='list' property='id'/>"/>
            <input type='hidden' name='page' value='owner_driver_info_update'/>
        </logic:iterate>
    </logic:notEmpty>
    <input type='reset' class='btn' value='Clear'/><input type='submit' class='btn' value='Update'/>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>