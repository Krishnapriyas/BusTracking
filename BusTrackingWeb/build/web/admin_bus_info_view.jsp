<%@include file="admin_header.jsp" %>

<div class="col-md-9 col-md-offset-2 bann-info wow fadeInRight animated" data-wow-delay=".5s">
    <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>

        <table class='table'>
            <thead>
                <tr>
                    <th>Bus Name</th><th>Owner Name</th><th>Description</th><th>Number</th><th>Options</th>
                </tr>
            </thead><tbody>
            <logic:notEmpty name="list0"> 
                <logic:iterate id="list" name="list0">
                    <tr >
                        <td><bean:write name="list" property="bus_name"/></td>
                        <td><bean:write name="list" property="owner_name"/></td>
                        <td><bean:write name="list" property="bus_description"/></td>
                        <td><bean:write name="list" property="bus_number"/></td>
                        <td><a href="owner_bus_trip_info_get.do?gby=BusTripInfo:bus_id=<bean:write name="list" property="id"/>&page=admin_select_trip"> Stops </a>
                        </td>
                    </tr>
                
            </logic:iterate>
        </logic:notEmpty>
        </tbody>
    </table>
</div>

<%@include file="admin_footer.jsp" %>