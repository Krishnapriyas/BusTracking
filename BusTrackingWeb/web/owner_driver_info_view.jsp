<%@include file="owner_header.jsp" %>
<div class="col-md-9 col-md-offset-2 bann-info wow fadeInRight animated" data-wow-delay=".5s">

    <script src="https://www.w3schools.com/lib/w3.js"></script>
    <script src="w3script.js"></script>

    <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
    <logic:notEmpty name="list0"> 
        <p>
            <!--SEARCH the data -->
            <input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search">
        </p>
        <table class='table' id="id01" border="1">
            <thead>
                <tr>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(1)')">Name</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(2)')">Phone</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(3)')">Email</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(4)')">Address</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(5)')">Options</th>
                </tr>
            </thead><tbody>
                <logic:iterate id="list" name="list0">
                    <tr class="item">
                        <td><bean:write name="list" property="driver_name"/></td>
                        <td><bean:write name="list" property="driver_phone"/></td>
                        <td><bean:write name="list" property="driver_email"/></td>
                        <td><bean:write name="list" property="driver_address"/></td>
                        <td><a href="owner_driver_info_get.do?gby=DriverInfo:id=<bean:write name='list' property='id'/>&page=owner_driver_info_update"> Update </a>
                            <a href="login_info_get.do?dby=id=<bean:write name='list' property='login_id'/>&gby=DriverInfo:bus_id=<bean:write name="list" property="bus_id"/>&page=owner_driver_info_view"> Delete </a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </logic:iterate>
    </logic:notEmpty>
</div>
    <%@include file="owner_footer.jsp" %>