<%@include file="owner_header.jsp" %>
<script src="https://www.w3schools.com/lib/w3.js"></script>
<script src="w3script.js"></script>
<div class="col-md-9 col-md-offset-2 bann-info wow fadeInRight animated" data-wow-delay=".5s">
    <logic:notEmpty name="list0"> 
        <logic:notEmpty name="error"><bean:write name="error"/></logic:notEmpty>
            <p>
                <!--SEARCH the data -->
                <input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search">
            </p>
            <table class='table' id="id01" border="1">
                <thead>
                    <tr>
                        <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(1)')">Stop</th>
                        <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(2)')">Order</th>
                        <th onclick="w3.sortHTML('#id01', '.item', 'td:nth-child(3)')">Stop time</th>
                    </tr>
                </thead><tbody>
                <logic:iterate id="list" name="list0">
                    <tr class="item">
                        <td><bean:write name="list" property="stop_name"/></td>
                        <td><bean:write name="list" property="stop_order"/></td>
                        <td><bean:write name="list" property="stop_time"/></td>
                    </tr>

                </logic:iterate>
            </logic:notEmpty>
        </tbody>
    </table>
</div>
<%@include file="owner_footer.jsp" %>