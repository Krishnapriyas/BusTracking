<%@include file="admin_header.jsp" %>
<script src="https://www.w3schools.com/lib/w3.js"></script>
<script src="w3script.js"></script>
<logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
    <div class="col-md-9 col-md-offset-2 bann-info wow fadeInRight animated" data-wow-delay=".5s">

    <logic:notEmpty name="list0">
        <h3>Pending List</h3>
        <p>
            <!--SEARCH the data -->
            <input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search">
        </p>
        <table class='table' id="id01" border="1">
            <thead>
                <tr>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(1)')">Name</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(2)')">Address</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(3)')">Email</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(4)')">Phone</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(5)')">Options</th>
                </tr>
            </thead><tbody>

                <logic:iterate id="list" name="list0">
                    <tr class="item">
                        <td><bean:write name="list" property="owner_name"/></td>
                        <td><bean:write name="list" property="owner_address"/></td>
                        <td><bean:write name="list" property="owner_email"/></td>
                        <td><bean:write name="list" property="owner_phone"/></td>
                        <td><a href="approve_owner_action.do?action=approve&page=approve_owner_action&loginId=<bean:write name="list" property="login_id"/>&email=<bean:write name="list" property="owner_email"/>"> Approve </a>/
                            <a href="login_info_get.do?dby=id=<bean:write name='list' property='login_id'/>&page=approve_owner_action"> Delete </a>
                        </td>
                    </tr>

                </logic:iterate>
            </tbody>
        </table>
    </logic:notEmpty>
    <logic:notEmpty name="list1">
        <h3>Approved List</h3>
        <p>
            <!--SEARCH the data -->
            <input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search">
        </p>
        <table class='table' id="id01" border="1">
            <thead>
                <tr>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(1)')">Name</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(2)')">Address</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(3)')">Email</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(4)')">Phone</th>
                    <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(5)')">Options</th>
                </tr>
            </thead><tbody>

                <logic:iterate id="list" name="list1">
                    <tr class="item">
                        <td><bean:write name="list" property="owner_name"/></td>
                        <td><bean:write name="list" property="owner_address"/></td>
                        <td><bean:write name="list" property="owner_email"/></td>
                        <td><bean:write name="list" property="owner_phone"/></td>
                        <td>
                            <a href="login_info_get.do?dby=id=<bean:write name='list' property='login_id'/>&page=approve_owner_action"> Delete </a>
                        </td>
                    </tr>

                </logic:iterate>
            </tbody>
        </table>
    </logic:notEmpty>
</div>
<%@include file="common_footer.jsp" %>