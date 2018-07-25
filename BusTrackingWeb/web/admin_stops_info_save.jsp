<%@page import="java.util.Random"%>
<%@include file="admin_header.jsp" %>
<div style="position: relative" class="col-md-7 col-md-offset-3 bann-info wow fadeInRight animated" data-wow-delay=".5s">
<form name=save action="admin_stops_info_save.do" method='post'>
        <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
            <div class='form-group'>
                <label>Stop name</label>
                <input type='text' name='stop_name' req='novalue' minm='2' maxm='100' class='form-control'/>
            </div>
            <input type='hidden' name='random' value='<%=new Random().nextInt()%>'/>
        <input type='hidden' name='page' value='admin_stops_info_save'/>
        <input type='hidden' name='gby' value='StopsInfo'/>
        <div class="sear">
            <button type="submit" style="margin-bottom: 30px" class="seabtn">Add Stop</button>
        </div>
    
</form>
<%@include file="admin_stops_info_view.jsp" %>
<script src='js/validation2.2.js'></script>
<%@include file="admin_footer.jsp" %>