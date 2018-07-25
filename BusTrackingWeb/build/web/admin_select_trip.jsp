
<%@include file="admin_header.jsp" %>
<script>
    $(document).ready(function () {
        $('.link').click(function () {
            var bus = $('#tripId').attr('value');
            var link = $(this).attr('href');
            $(this).attr('href', link + bus)
        })
    })
</script>
<form name=save action='owner_bus_stop_info_save.do' method='post' enctype='multipart/form-data'>
    <div class="col-md-5 col-md-offset-4 bann-info wow fadeInRight animated" data-wow-delay=".5s">
        <div class='form-group'>
            <label>Trip</label>
            <select  name='trip_id' req='number' minm='1' maxm='10' class='form-control'>
                <option >Select Trip</option>
                <logic:notEmpty name="list0">
                    <logic:iterate name="list0" id="list">
                        <option id="tripId" value="<bean:write name="list" property="id"/>">From&nbsp;<bean:write name="list" property="bus_start"/>&nbsp;to&nbsp;<bean:write name="list" property="bus_stop"/></option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
        </div>
        <div class="sear">
            <button class="seabtn"><a style="color: white;text-decoration: none;display: inline-block;" class="link" href="join_action.do?page=admin_bus_stop_view&action=stops&trip_id=">Go</a>
            </button>
        </div>


    </div>
</form>
<script src='js/validation2.2.js'></script>
<%@include file="admin_footer.jsp" %>