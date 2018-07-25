<%@page import="java.util.Random"%>
<%@include file="owner_header.jsp" %>

<div class="sear col-md-offset-8">
    <button type="submit" class="seabtn fa fa-list">&nbsp;&nbsp;<a style="text-decoration: none;color: white;" href="owner_bus_info_get.do?gby=BusInfo:owner_id=<%=loginId%>&page=owner_bus_info_view">All Bus</a></button>
</div>
<form name=save action='owner_bus_info_save.do' method='post'  enctype='multipart/form-data'>
   <div class="col-md-7 col-md-offset-3 bann-info wow fadeInRight animated" data-wow-delay=".5s">
        <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
        <div class="ban-top">
            <div class="bnr-left">
                <label class="inputLabel">Bus Name</label>
                <input class="city" type="text" name='bus_name' req='novalue' minm='1' maxm='100' onfocus="this.value = '';"/>
            </div>
            <div class="bnr-left">
                <label class="inputLabel">Bus Description</label>
                <input class="city" type="text" name='bus_description' req='novalue' minm='3' maxm='100' onfocus="this.value = '';" />
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="ban-bottom">
            <div class="bnr-right">
                <label class="inputLabel">Bus Number</label>
                <input name='bus_number' req='novalue' minm='1' maxm='100' type="text" onfocus="this.value = '';" />
            </div>
            
            <input type='hidden' name='page' value='owner_bus_info_save'/>
            <input type='hidden' name='random' value='<%=new Random().nextInt()%>'/>
            <input type='hidden' name='owner_id' value='<%=loginId%>'/>
            <div class="clearfix"></div>
            <!---start-date-piker---->
            <link rel="stylesheet" href="css/jquery-ui.css" />
            <script src="js/jquery-ui.js"></script>
        </div>
        <div class="sear">
            <button type="submit" class="seabtn">Add Bus</button>
        </div>


    </div>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>