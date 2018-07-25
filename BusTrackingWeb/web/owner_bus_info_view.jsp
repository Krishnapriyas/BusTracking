<%@include file="owner_header.jsp" %>
<div class="sear col-md-offset-9">
    <button type="submit" class="seabtn fa fa-plus">&nbsp;&nbsp;<a style="text-decoration: none;color: white;" href="owner_bus_info_save.jsp">Add Bus</a></button>
</div>

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
                        <td><a href="owner_bus_info_get.do?gby=BusInfo:id=<bean:write name='list' property='id'/>&page=owner_bus_info_update"> Update /</a>
                            <a href="owner_bus_info_get.do?dby=id=<bean:write name='list' property='id'/>&gby=BusInfo&page=owner_bus_info_view"> Delete /</a>
                            <a href="owner_driver_info_save.jsp?busId=<bean:write name="list" property="id"/>"> Add Driver /</a>
                            <a href="owner_driver_info_get.do?&gby=DriverInfo:bus_id=<bean:write name="list" property="id"/>&page=owner_driver_info_view"> View Driver </a>
                        </td>
                    </tr>

                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
</div>
<%@include file="owner_footer.jsp" %>