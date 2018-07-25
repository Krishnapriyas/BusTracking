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
                <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(1)')">Stop name</th>
                <th>Options</th>
            </tr>
        </thead>
        <tbody>
        <logic:iterate id="list" name="list0">
            <tr class="item">
                <td><bean:write name="list" property="stop_name"/></td>
            <td>
                <a href="admin_stops_info_get.do?dby=id=<bean:write name='list' property='id'/>&gby=StopsInfo&page=admin_stops_info_save"> Delete </a>
            </td>
            </tr>
        </logic:iterate>
        </tbody>
    </table>
</logic:notEmpty>
</div>

