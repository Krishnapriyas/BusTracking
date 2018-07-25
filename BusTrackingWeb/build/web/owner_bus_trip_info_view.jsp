<%@include file="owner_header.jsp" %>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
    <div class="col-md-9 col-md-offset-2 bann-info wow fadeInRight animated" data-wow-delay=".5s">

        <script src="https://www.w3schools.com/lib/w3.js"></script>
        <script src="w3script.js"></script>
    <logic:notEmpty name="list0"> 
        <p>
            <!--SEARCH the data -->
            <input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search">
        </p>
        <table class='table' id="id01" border="1">
            <thead>
                <tr>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(1)')">Source</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(2)')">Destination</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(3)')">Trip Time</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(4)')">Options</th>
                </tr>
            </thead><tbody>

                <logic:iterate id="list" name="list0">
                    <tr class="item">
                        <td><bean:write name="list" property="bus_start"/></td>
                        <td><bean:write name="list" property="bus_stop"/></td>
                        <td><bean:write name="list" property="trip_time"/></td>
                        <td><a href="owner_bus_trip_info_get.do?gby=BusTripInfo:id=<bean:write name='list' property='id'/>&page=owner_bus_trip_info_update"> Update </a>/
                            <a href="owner_bus_trip_info_get.do?dby=id=<bean:write name='list' property='id'/>&gby=BusTripInfo&page=owner_bus_trip_info_view"> Delete </a>
                        </td>
                    </tr>

                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
</div>
<%@include file="owner_footer.jsp" %>