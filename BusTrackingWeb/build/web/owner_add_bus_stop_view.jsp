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
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(2)')">Description</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(3)')">Number</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(6)')">Options</th>
                </tr>
            </thead><tbody>

                <logic:iterate id="list" name="list0">
                    <tr class="item">
                        <td><bean:write name="list" property="bus_name"/></td>
                        <td><bean:write name="list" property="bus_description"/></td>
                        <td><bean:write name="list" property="bus_number"/></td>
                        <td><a href="owner_bus_stop_info_get.do?gby=StopsInfo&gby=BusTripInfo:bus_id=<bean:write name="list" property="id"/>&page=owner_bus_stop_info_save"> Add Stop/ </a>
                        <a href="owner_bus_trip_info_get.do?gby=BusTripInfo:bus_id=<bean:write name="list" property="id"/>&page=select_trip"> View Stop </a>
                        </td>
                    </tr>

                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
</div>
<%@include file="owner_footer.jsp" %>