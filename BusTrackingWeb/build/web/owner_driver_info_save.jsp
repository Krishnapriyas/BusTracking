<%@page import="java.util.Random"%>
<%@include file="owner_header.jsp" %>
   <div class="col-md-7 col-md-offset-3 bann-info wow fadeInRight animated" data-wow-delay=".5s">
       <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
        <form name=save action='owner_driver_info_save.do' method='post' enctype='multipart/form-data'>
            <div class='form-group'>
                <label>Name</label>
                <input type='text' name='driver_name' req='text' minm='3' maxm='100' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Phone</label>
                <input type='text' name='driver_phone' req='number' minm='10' maxm='10' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Email</label>
                <input type='text' name='driver_email' req='email' minm='3' maxm='100' class='form-control'/>
            </div>
            <div class='form-group'>
                <label>Address</label>
                <textarea name='driver_address' req='text' minm='3' maxm='100' class='form-control'></textarea>
            </div>

            <input type='hidden' name='bus_id' value="<%=request.getParameter("busId")%>" class='form-control'/>

        <input type='hidden' name='page' value='owner_driver_info_save'/>
        <input type='hidden' name='random' value='<%=new Random().nextInt()%>'/>
        <div class="sear">
            <button type="submit" class="seabtn">Add Driver</button>
        </div>
    </form>
</div>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>