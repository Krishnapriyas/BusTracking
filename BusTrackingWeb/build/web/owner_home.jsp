<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE HTML>
<%
    int loginId = (int) session.getAttribute("LOGINID");
%>
<html>
    <head>
        <title>Owner Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Green Wheels Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="applijewelleryion/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <link href='//fonts.googleapis.com/css?family=Open+Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
        <link href="css/font-awesome.css" rel="stylesheet">
        <!-- Custom Theme files -->
        <script src="js/jquery-1.12.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!--animate-->
        <link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
        <script src="js/wow.min.js"></script>
        <script>
            new WOW().init();
        </script>
        <!--//end-animate-->
    </head>
    <body>
        <!-- top-header -->
        <div class="top-header">
            <div class="container">
                <ul class="tp-hd-lft wow fadeInLeft animated" data-wow-delay=".5s">
                    <li class="hm"><a href="owner_home.jsp"><i class="fa fa-home"></i></a></li>
                </ul>
                <ul class="tp-hd-rgt wow fadeInRight animated" data-wow-delay=".5s"> 			
                    <li class="sig"><a href="logout_action.do" ><span><i class="fa fa-sign-out"></i></span>Logout</a></li> 
                </ul>
                <div class="clearfix"></div>
            </div>
        </div>
        <!--- /top-header ---->
        <!--- header ---->
        <div class="header">
            <div class="container">
                <div class="logo wow fadeInDown animated" data-wow-delay=".5s">
                    <a href="index.html">BUS <span>TRACKING</span></a>	
                </div>
                <div class="bus wow fadeInUp animated" data-wow-delay=".5s">
                    <a href="owner_bus_info_save.jsp" class="buses active">BUSES</a>
                    <a href="owner_bus_trip_info_get.do?gby=BusInfo:owner_id=<%=loginId%>&page=select_bus">TRIP</a>
                    <a href="owner_bus_info_get.do?gby=BusInfo:owner_id=<%=loginId%>&page=owner_add_bus_stop_view">STOPS</a>
                </div>
                <div class="lock fadeInDown animated" data-wow-delay=".5s"> 
                    <li><i class="fa fa-lock"></i></li>
                    <li><div class="securetxt">SAFE &amp; SECURE<br> ONLINE PAYMENTS</div></li>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!--- /header ---->
        <div class="banner">
            <div class="container">
                <h1 class="wow zoomIn animated animated" data-wow-delay=".5s" style="text-transform: uppercase;visibility: visible; animation-delay: 0.5s; animation-name: zoomIn;"> WELCOME <%=(String) session.getAttribute("OWNER_NAME")%></h1>
            </div>
        </div>
        <%@include file="owner_footer.jsp" %>