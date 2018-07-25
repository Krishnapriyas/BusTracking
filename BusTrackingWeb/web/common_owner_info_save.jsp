<%@page import="java.util.Random"%>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
<form name=save action='common_owner_info_save.do' method='post' enctype='multipart/form-data'>

    <input placeholder="Name" type='text' name='owner_name' req='text' minm='3' maxm='50' />
    <textarea placeholder="Address" name='owner_address' req='novalue' minm='3' maxm='100'></textarea>

    <input placeholder="Email" type='text' name='owner_email' req='email' minm='3' maxm='50'/>

    <input placeholder="Phone" type='text' name='owner_phone' req='number' minm='10' maxm='10'  />
    <input placeholder="Username" type='text' name='username' req='novalue' minm='3' maxm='10'/>
    <input placeholder="Password" type='password' name='password' req='novalue' minm='5' maxm='10'/>
    <input type='hidden' name='page' value='index'/>
    <input type='hidden' name='random' value='<%=new Random().nextInt()%>'/>
    <input type='submit' class='btn' value='CREATE ACCOUNT'/>
    
</form>

<script src='js/validation2.2.js'></script>
