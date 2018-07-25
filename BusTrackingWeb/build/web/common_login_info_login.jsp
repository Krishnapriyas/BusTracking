<form name=save action='common_login_info_login.do' method=get >
    <input type='text' placeholder="Username"name='username' req='novalue' minm='3' maxm='30'  onfocus="this.value = '';" onblur="if (this.value == '') {
                                                    this.value = 'Enter your Username';
                                                }"/>
    <input type='password' placeholder="Password" name='password' req='password' minm='3' maxm='20' onfocus="this.value = '';" onblur="if (this.value == '') {
                                                    this.value = 'Enter your Password';
                                                }"/>
    <input type='submit' class='btn' value='SIGNIN'/>
</form>
<script src='js/validation2.2.js'></script>