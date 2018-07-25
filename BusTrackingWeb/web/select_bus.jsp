<%@page import="java.util.Random"%>
<%@include file="owner_header.jsp" %>

<form name=save action='owner_bus_stop_info_save.do' method='post' enctype='multipart/form-data'>
    <div class="col-md-5 col-md-offset-4 bann-info wow fadeInRight animated" data-wow-delay=".5s">
        <div class='form-group'>
            <label>Bus</label>
            <select id="busId" name='bus_id' req='number' minm='1' maxm='10' class='form-control'>
                <option >select Bus</option>
                <logic:notEmpty name="list0">
                    <logic:iterate name="list0" id="list">
                        <option  value="<bean:write name="list" property="id"/>"><bean:write name="list" property="bus_name"/></option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
        </div>
        <div class="sear">
            <button class="seabtn"><a style="color: white;text-decoration: none;display: inline-block;" class="link" href="owner_bus_trip_info_get.do?gby=StopsInfo&page=owner_bus_trip_info_save&busId=">Add Trip</a></button>
            <button class="seabtn"><a style="color: white;text-decoration: none;display: inline-block;" class="link" href="owner_bus_trip_info_get.do?page=owner_bus_trip_info_view&gby=BusTripInfo:bus_id=">View Trip</a>
            </button>
        </div>


    </div>
</form>
<script>
    $(document).ready(function () {
        $('.link').click(function () {
            var bus = $('#busId').val();
            var link = $(this).attr('href');
            $(this).attr('href', link + bus)
        })
    })
</script>
<script src='js/validation2.2.js'></script>
<%@include file="owner_footer.jsp" %>