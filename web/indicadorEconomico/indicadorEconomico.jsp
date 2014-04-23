<%-- 
    Document   : indicadorEconomico
    Created on : 21-04-2014, 07:24:35 PM
    Author     : patricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->       
        <title>Latte ERP | Usuarios </title>
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Use the correct meta names below for your web application
                 Ref: http://davidbcalhoun.com/2010/viewport-metatag 
                 
        <meta name="HandheldFriendly" content="True">
        <meta name="MobileOptimized" content="320">-->

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <!-- Basic Styles -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">

        <!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production_unminified.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.css">

        <!-- SmartAdmin RTL Support is under construction
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.css"> -->

        <!-- We recommend you use "your_style.css" to override SmartAdmin
             specific styles this will also ensure you retrain your customization with each SmartAdmin update.
        <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

        <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
        <!-- <link rel="stylesheet" type="text/css" media="screen" href="css/demo.css"> -->

        <!-- FAVICONS -->
        <link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
        <link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

        <!-- GOOGLE FONT -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

        <!-- export excel -->
        <script src="js/export-excel.js"></script>
    </head>
    <body class="">
        <!-- possible classes: minified, fixed-ribbon, fixed-header, fixed-width-->

        <!-- HEADER -->
        <header id="header">
            <div id="logo-group">

                <!-- PLACE YOUR LOGO HERE -->
                <c:import var="logo" url="/logo.jsp" />
                <c:out value="${logo}" escapeXml="false" />
                <!-- END LOGO PLACEHOLDER -->

                <!-- Note: The activity badge color changes when clicked and resets the number to 0
                Suggestion: You may want to set a flag when this happens to tick off all checked messages / notifications -->
                <span id="activity" class="activity-dropdown"> <i class="fa fa-user"></i> <b class="badge"> 21 </b> </span>

                <!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
                <div class="ajax-dropdown">

                    <!-- the ID links are fetched via AJAX to the ajax container "ajax-notifications" -->
                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                        <label class="btn btn-default">
                            <input type="radio" name="activity" id="ajax/notify/mail.html">
                            Msgs (14) </label>
                        <label class="btn btn-default">
                            <input type="radio" name="activity" id="ajax/notify/notifications.html">
                            notify (3) </label>
                        <label class="btn btn-default">
                            <input type="radio" name="activity" id="ajax/notify/tasks.html">
                            Tasks (4) </label>
                    </div>

                    <!-- notification content -->
                    <div class="ajax-notifications custom-scroll">

                        <div class="alert alert-transparent">
                            <h4>Click a button to show messages here</h4>
                            This blank page message helps protect your privacy, or you can show the first message here automatically.
                        </div>

                        <i class="fa fa-lock fa-4x fa-border"></i>

                    </div>
                    <!-- end notification content -->

                    <!-- footer: refresh area -->
                    <span> Last updated on: 12/12/2013 9:43AM
                        <button type="button" data-loading-text="<i class='fa fa-refresh fa-spin'></i> Cargando..." class="btn btn-xs btn-default pull-right">
                            <i class="fa fa-refresh"></i>
                        </button> </span>
                    <!-- end footer -->

                </div>
                <!-- END AJAX-DROPDOWN -->
            </div>

            <!-- projects dropdown -->
            <c:import var="projectsDropdown" url="/projectsDropdown.jsp" />
            <c:out value="${projectsDropdown}" escapeXml="false" />
            <!-- end projects dropdown -->

            <!-- pulled right: nav area -->
            <c:import var="pulledRight" url="/pulledRight.jsp" />
            <c:out value="${pulledRight}" escapeXml="false" />
            <!-- end pulled right: nav area -->

        </header>
        <!-- END HEADER -->

        <!-- Left panel : Navigation area -->
        <!-- Note: This width of the aside area can be adjusted through LESS variables -->
        <c:import var="leftMenu" url="/leftMenu.jsp" />
        <c:out value="${leftMenu}" escapeXml="false" />
        <!-- END NAVIGATION -->

        <!-- MAIN PANEL -->
        <div id="main" role="main">

            <!-- RIBBON -->
            <div id="ribbon">

                <span class="ribbon-button-alignment"> <span id="refresh" class="btn btn-ribbon" data-title="refresh" rel="tooltip" data-placement="bottom" data-original-title="<i class='text-yellowLight fa fa-warning'></i> Warning! Esto reiniciará la configuración de widgets." data-html="true"><i class="fa fa-refresh"></i></span> </span>

                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li>                        
                        <a href="IndicadorEconomicoGetServlet"><i class="text-primary fa fa-table"></i> Indicadores Económicos </a>
                    </li>                                 
                </ol>
                <!-- end breadcrumb -->

                <!-- You can also add more buttons to the
                ribbon for further usability

                Example below:

                <span class="ribbon-button-alignment pull-right">
                <span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
                <span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
                <span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
                </span> -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">

                <div class="row">
                    <!-- TITULO MANTENEDOR -->
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h2 class="page-title txt-color-blueDark">
                            <i class="fa fa-table fa-fw"></i> 
                            Mantenedor
                            <span>
                                <i class="fa fa-bar-chart-o fa-fw "></i> 
                                Indicadores Económicos
                            </span>
                        </h2>
                    </div>                    
                    <!-- INDICADORES ECONOMICOS HEADER -->
                    <c:import var="indicadoresHeader" url="/indicadoresHeader.jsp" />
                    <c:out value="${indicadoresHeader}" escapeXml="false" />
                </div>

                <!-- widget grid -->
                <section id="widget-grid" class="">                  

                    <div class="row">                        
                        <div class="col-sm-12">
                            <div class="well">
                                <h1>Indicador Económico<span class="semi-bold"> diario, semanal y mensual</span> <small></small></h1>
                                <p>Información estadística actualizada sobre el valor del Dolar, Euro,UF, UTM, IPC y otros indicadores.</p>                                
                                <p>&nbsp;</p>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-purple">UF Hoy: <span class="semi-bold">$<c:out value="${UFhoy}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-purple text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iedList" items="${iedList}" varStatus="status">
                                                <c:out value="${iedList.uf}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach>                                           
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-blueDark">UTM Actual: <span class="semi-bold">$<c:out value="${utmActual}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-blueDark text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iemList" items="${iemList}" varStatus="status">
                                                <c:out value="${iemList.utm}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-orangeDark">Euro Hoy <span class="semi-bold">$<c:out value="${euroHoy}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-orangeDark text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iedList" items="${iedList}" varStatus="status">
                                                <c:out value="${iedList.euro}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach> 
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-greenDark">Dolar Hoy: <span class="semi-bold">$<c:out value="${dolarHoy}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-greenDark text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iedList" items="${iedList}" varStatus="status">
                                                <c:out value="${iedList.dolar}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach> 
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-red">Bencina 93: <span class="semi-bold">$<c:out value="${b93Actual}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-red text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iesList" items="${iesList}" varStatus="status">
                                                <c:out value="${iesList.bencina93}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-blue">Bencina 95: <span class="semi-bold">$<c:out value="${b95Actual}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-blue text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iesList" items="${iesList}" varStatus="status">
                                                <c:out value="${iesList.bencina95}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-green">Bencina 97 <span class="semi-bold">$<c:out value="${b97Actual}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-green text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iesList" items="${iesList}" varStatus="status">
                                                <c:out value="${iesList.bencina97}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="well well-sm well-light">
                                        <h4 class="txt-color-yellow">Diesel <span class="semi-bold">$<c:out value="${dieselActual}"/></span> <a href="javascript:void(0);" class="pull-right txt-color-blueLight"><i class="fa fa-refresh"></i></a></h4>
                                        <br>
                                        <div class="sparkline txt-color-yellow text-center" 
                                             data-sparkline-type="bar" 
                                             data-sparkline-width="96%" 
                                             data-sparkline-barwidth="11" 
                                             data-sparkline-barspacing = "5" 
                                             data-sparkline-height="80px">
                                            <c:forEach var="iesList" items="${iesList}" varStatus="status">
                                                <c:out value="${iesList.diesel}"/>
                                                <c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div> 

                                <!-- UF -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

                                    <!-- Widget ID (each widget will need unique ID)-->
                                    <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
                                        <!-- widget options:
                                        usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
        
                                        data-widget-colorbutton="false"
                                        data-widget-editbutton="false"
                                        data-widget-togglebutton="false"
                                        data-widget-deletebutton="false"
                                        data-widget-fullscreenbutton="false"
                                        data-widget-custombutton="false"
                                        data-widget-collapsed="true"
                                        data-widget-sortable="false"
        
                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                            <h2>U.F. (Unidad de Fomento)</h2>

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <div id="ufGraph" class="chart"></div>

                                            </div>
                                            <!-- end widget content -->

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->

                                </article>
                                <!-- UF END -->

                                <!-- EURO VS DOLAR -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                    <!-- Widget ID (each widget will need unique ID)-->                                    
                                    <div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false">
                                        <!-- widget options:
                                        usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                                        data-widget-colorbutton="false"
                                        data-widget-editbutton="false"
                                        data-widget-togglebutton="false"
                                        data-widget-deletebutton="false"
                                        data-widget-fullscreenbutton="false"
                                        data-widget-custombutton="false"
                                        data-widget-collapsed="true"
                                        data-widget-sortable="false"

                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                            <h2>Euro vs Dolar</h2>

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <div id="year-graph" class="chart no-padding"></div>

                                            </div>
                                            <!-- end widget content -->

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->                                    
                                </article>  
                                <!-- EURO VS DOLAR END -->  

                                <!-- UTM -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

                                    <!-- Widget ID (each widget will need unique ID)-->
                                    <div class="jarviswidget" id="wid-id-2" data-widget-editbutton="false">
                                        <!-- widget options:
                                        usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
        
                                        data-widget-colorbutton="false"
                                        data-widget-editbutton="false"
                                        data-widget-togglebutton="false"
                                        data-widget-deletebutton="false"
                                        data-widget-fullscreenbutton="false"
                                        data-widget-custombutton="false"
                                        data-widget-collapsed="true"
                                        data-widget-sortable="false"
        
                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                            <h2>UTM (Unidad Tributaria Mensual)</h2>

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <div id="utmChart" class="chart"></div>

                                            </div>
                                            <!-- end widget content -->

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->

                                </article>
                                <!-- END UTM -->

                                <!-- IPC -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                    <!-- Widget ID (each widget will need unique ID)-->
                                    <div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
                                        <!-- widget options:
                                        usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
    
                                        data-widget-colorbutton="false"
                                        data-widget-editbutton="false"
                                        data-widget-togglebutton="false"
                                        data-widget-deletebutton="false"
                                        data-widget-fullscreenbutton="false"
                                        data-widget-custombutton="false"
                                        data-widget-collapsed="true"
                                        data-widget-sortable="false"
    
                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                            <h2>IPC (Índice del Precio al Consumidor)</h2>

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <div id="ipcGraph" class="chart no-padding"></div>

                                            </div>
                                            <!-- end widget content -->

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->
                                </article>

                                <!-- row -->
                                <div class="row">

                                    <!-- NEW WIDGET START -->
                                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                                        <!-- Widget ID (each widget will need unique ID)-->
                                        <div class="jarviswidget" id="wid-id-4" data-widget-editbutton="false">
                                            <!-- widget options:
                                            usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                                            data-widget-colorbutton="false"
                                            data-widget-editbutton="false"
                                            data-widget-togglebutton="false"
                                            data-widget-deletebutton="false"
                                            data-widget-fullscreenbutton="false"
                                            data-widget-custombutton="false"
                                            data-widget-collapsed="true"
                                            data-widget-sortable="false"

                                            -->
                                            <header>
                                                <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                                                <h2>Combustible</h2>

                                            </header>

                                            <!-- widget div-->
                                            <div>

                                                <!-- widget edit box -->
                                                <div class="jarviswidget-editbox">
                                                    <!-- This area used as dropdown edit box -->

                                                </div>
                                                <!-- end widget edit box -->

                                                <!-- widget content -->
                                                <div class="widget-body no-padding">

                                                    <div id="combustible" class="chart has-legend"></div>

                                                </div>
                                                <!-- end widget content -->

                                            </div>
                                            <!-- end widget div -->

                                        </div>
                                        <!-- end widget -->

                                    </article>
                                    <!-- WIDGET END -->

                                </div>

                            </div>
                        </div>	
                    </div>

                </section>    

                <!-- widget grid -->
                <section id="widget-grid" class="">                  

                    <div class="row">                        
                        <div class="col-sm-12">
                            <div class="well">
                                <h1>Ingresar Indicadores Económicos<span class="semi-bold"> diario, semanal y mensual</span> <small></small></h1>
                                <p>&nbsp;</p>
                                <!-- NUEVOS INDICADORES DIARIOS -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

                                    <!-- Widget ID (each widget will need unique ID)-->
                                    <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false">
                                        <!-- widget options:
                                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
                                                
                                                data-widget-colorbutton="false"	
                                                data-widget-editbutton="false"
                                                data-widget-togglebutton="false"
                                                data-widget-deletebutton="false"
                                                data-widget-fullscreenbutton="false"
                                                data-widget-custombutton="false"
                                                data-widget-collapsed="true" 
                                                data-widget-sortable="false"
                                                
                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-edit"></i> </span>
                                            <h2>Indicador Diario </h2>				

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <form id="indicadorDiarioForm" action="IndicadorEconomicoUpdateServlet" class="smart-form" novalidate="novalidate">

                                                    <fieldset>
                                                        <div class="row">
                                                            <section class="col col-6">
                                                                <label class="input"><i class="fa fa-usd"></i> UF
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="uf">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el valor UF de hoy</b>                                                                        
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                            <section class="col col-6">                                                                
                                                                <label class="input"><i class="fa fa-money"></i> Dolar
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="dolar">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el Dolar de hoy</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                            <section class="col col-6">
                                                                <label class="input"><i class="fa fa-eur"></i> Euro
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="euro">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el Euro de hoy</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </fieldset>                                                                                                      
                                                    <p>&nbsp;</p>
                                                    <footer>
                                                        <button type="submit" name="btnIEDiario" class="btn btn-primary">
                                                            Agregar
                                                        </button>
                                                    </footer>
                                                </form>

                                            </div>
                                            <!-- end widget content -->                                            

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->                                                               

                                </article>                                

                                <!-- NUEVOS INDICADORES SEMANALES -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

                                    <!-- Widget ID (each widget will need unique ID)-->
                                    <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-2" data-widget-editbutton="false" data-widget-custombutton="false">
                                        <!-- widget options:
                                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
                                                
                                                data-widget-colorbutton="false"	
                                                data-widget-editbutton="false"
                                                data-widget-togglebutton="false"
                                                data-widget-deletebutton="false"
                                                data-widget-fullscreenbutton="false"
                                                data-widget-custombutton="false"
                                                data-widget-collapsed="true" 
                                                data-widget-sortable="false"
                                                
                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-edit"></i> </span>
                                            <h2>Indicador Semanal </h2>				

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <form id="indicadorSemanalForm" action="IndicadorEconomicoUpdateServlet" class="smart-form" novalidate="novalidate">

                                                    <fieldset>
                                                        <div class="row">
                                                            <section class="col col-6">                                                                
                                                                <label class="input"><i class="glyphicon glyphicon-tint"></i> Bencina 93
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="bencina93">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el valor de la Bencina 93</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                            <section class="col col-6">
                                                                <label class="input"><i class="glyphicon glyphicon-tint"></i> Bencina 95
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="bencina95">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el valor de la Bencina 95</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                            <section class="col col-6">
                                                                <label class="input"><i class="glyphicon glyphicon-tint"></i> Bencina 97
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="bencina97">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el valor de la Bencina 97</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                            <section class="col col-6">
                                                                <label class="input"><i class="glyphicon glyphicon-tint"></i> Diesel
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="diesel">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el valor del Diesel</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                        </div>
                                                    </fieldset>                                                                                                      
                                                    <p>&nbsp;</p>
                                                    <footer>
                                                        <button type="submit" name="btnIESemanal" class="btn btn-primary">
                                                            Agregar
                                                        </button>
                                                    </footer>
                                                </form>

                                            </div>
                                            <!-- end widget content -->

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->                                                               

                                </article>                                

                                <!-- NUEVOS INDICADORES MENSUALES -->
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

                                    <!-- Widget ID (each widget will need unique ID)-->
                                    <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">
                                        <!-- widget options:
                                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
                                                
                                                data-widget-colorbutton="false"	
                                                data-widget-editbutton="false"
                                                data-widget-togglebutton="false"
                                                data-widget-deletebutton="false"
                                                data-widget-fullscreenbutton="false"
                                                data-widget-custombutton="false"
                                                data-widget-collapsed="true" 
                                                data-widget-sortable="false"
                                                
                                        -->
                                        <header>
                                            <span class="widget-icon"> <i class="fa fa-edit"></i> </span>
                                            <h2>Indicador Mensual </h2>				

                                        </header>

                                        <!-- widget div-->
                                        <div>

                                            <!-- widget edit box -->
                                            <div class="jarviswidget-editbox">
                                                <!-- This area used as dropdown edit box -->

                                            </div>
                                            <!-- end widget edit box -->

                                            <!-- widget content -->
                                            <div class="widget-body no-padding">

                                                <form id="indicadorMensualForm" name="IndicadorEconomicoUpdateServlet" class="smart-form" novalidate="novalidate">

                                                    <fieldset>
                                                        <div class="row">
                                                            <section class="col col-6">
                                                                <label class="input"><i class="fa fa-usd"></i> UTM
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                                                        <input class="form-control" type="text" name="utm">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el valor de la UTM</b>
                                                                        <span class="input-group-addon">.00</span>
                                                                    </div>
                                                                </label>
                                                            </section>
                                                            <section class="col col-6">
                                                                <label class="input"> IPC
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"></span>
                                                                        <input class="form-control" type="text" name="ipc">
                                                                        <b class="tooltip tooltip-top-right"><i class="fa fa-warning txt-color-yellowLight"></i> Ingrese el porcentaje del IPC</b>
                                                                        <span class="input-group-addon">%</span>
                                                                    </div>
                                                                </label> 
                                                            </section>                                                          
                                                        </div>
                                                    </fieldset>
                                                    <p>&nbsp;</p>
                                                    <footer>
                                                        <button type="submit" name="btnIEMensual" class="btn btn-primary">
                                                            Agregar
                                                        </button>
                                                    </footer>
                                                </form>

                                            </div>
                                            <!-- end widget content -->                                           

                                        </div>
                                        <!-- end widget div -->

                                    </div>
                                    <!-- end widget -->                                                               

                                </article>                                

                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>                                
                            </div>
                        </div>
                    </div>

            </div>
            <!-- END MAIN CONTENT -->

        </div>
        <!-- END MAIN PANEL -->

        <!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
        Note: These tiles are completely responsive,
        you can add as many as you like
        -->
        <c:import var="shortcut" url="/shortcut.jsp" />
        <c:out value="${shortcut}" escapeXml="false" />
        <!-- END SHORTCUT AREA -->

        <!--================================================== -->

        <!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
        <script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>

        <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script>
            if (!window.jQuery) {
                document.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');
            }
        </script>

        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <script>
            if (!window.jQuery.ui) {
                document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
            }
        </script>

        <!-- JS TOUCH : include this plugin for mobile drag / drop touch events
        <script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

        <!-- BOOTSTRAP JS -->
        <script src="js/bootstrap/bootstrap.min.js"></script>

        <!-- CUSTOM NOTIFICATION -->
        <script src="js/notification/SmartNotification.min.js"></script>

        <!-- JARVIS WIDGETS -->
        <script src="js/smartwidgets/jarvis.widget.min.js"></script>

        <!-- EASY PIE CHARTS -->
        <script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

        <!-- SPARKLINES -->
        <script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>

        <!-- JQUERY VALIDATE -->
        <script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>

        <!-- JQUERY MASKED INPUT -->
        <script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>

        <!-- JQUERY SELECT2 INPUT -->
        <script src="js/plugin/select2/select2.min.js"></script>

        <!-- JQUERY UI + Bootstrap Slider -->
        <script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

        <!-- browser msie issue fix -->
        <script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

        <!-- FastClick: For mobile devices -->
        <script src="js/plugin/fastclick/fastclick.js"></script>

        <!--[if IE 7]>

        <h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

        <![endif]-->   

        <!-- MAIN APP JS FILE -->
        <script src="js/app.js"></script>

        <!-- PAGE RELATED PLUGIN(S) -->

        <!-- Flot Chart Plugin: Flot Engine, Flot Resizer, Flot Tooltip -->
        <script src="js/plugin/flot/jquery.flot.cust.js"></script>
        <script src="js/plugin/flot/jquery.flot.resize.js"></script>
        <script src="js/plugin/flot/jquery.flot.fillbetween.min.js"></script>
        <script src="js/plugin/flot/jquery.flot.orderBar.js"></script>
        <script src="js/plugin/flot/jquery.flot.pie.js"></script>
        <script src="js/plugin/flot/jquery.flot.tooltip.js"></script>

        <!-- Morris Chart Dependencies -->
        <script src="js/plugin/morris/raphael.2.1.0.min.js"></script>
        <script src="js/plugin/morris/morris.min.js"></script>

        <script type="text/javascript">
            // PAGE RELATED SCRIPTS

            var $yellow = "#F2A500"; //color naranjo
            var $red = "#C20034"; //color rojo
            var $green = "#27C200"; //color verde
            var $blue = "#006DF2"; //color azul
            var $purple = "#6B00AE"; //color azul

            var $chrt_main = "#DBDB00"; //color amarillo
            var $chrt_border_color = "#efefef";
            var $chrt_grid_color = "#DDD";
            var $chrt_second = "#0076A9"; // color azul            
            var $chrt_third = "#FF9F01";
            var $chrt_fourth = "#7e9d3a"; //naranjo            
            var $chrt_fifth = "#BD362F"; //verde            
            var $chrt_mono = "#000"; // rojo oscuro

            $(document).ready(function() {

                // DO NOT REMOVE : GLOBAL FUNCTIONS!
                pageSetUp();

                var $indicadorDiarioForm = $("#indicadorDiarioForm").validate({
                    // Rules for form validation
                    rules: {
                        uf: {
                            required: true
                        },
                        dolar: {
                            required: true
                        },
                        euro: {
                            required: true,
                        }
                    },
                    // Messages for form validation
                    messages: {
                        uf: {
                            required: 'Por favor ingrese la UF del día'
                        },
                        dolar: {
                            required: 'Por favor ingrese el dólar del día'
                        },
                        euro: {
                            required: 'Por favor ingrese el euro del día'
                        }
                    },
                    // Do not change code below
                    errorPlacement: function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                var $indicadorSemanalForm = $("#indicadorSemanalForm").validate({
                    // Rules for form validation
                    rules: {
                        bencina93: {
                            required: true
                        },
                        bencina95: {
                            required: true
                        },
                        bencina97: {
                            required: true
                        },
                        diesel: {
                            required: true
                        }
                    },
                    // Messages for form validation
                    messages: {
                        bencina93: {
                            required: 'Por favor ingrese el valor de la Bencina 93'
                        },
                        bencina95: {
                            required: 'Por favor ingrese el valor de la Bencina 95'
                        },
                        bencina97: {
                            required: 'Por favor ingrese el valor de la Bencina 97'
                        },
                        diesel: {
                            required: 'Por favor ingrese el valor del Diesel'
                        }
                    },
                    // Do not change code below
                    errorPlacement: function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                var $indicadorMensualForm = $("#indicadorMensualForm").validate({
                    // Rules for form validation
                    rules: {
                        utm: {
                            required: true
                        },
                        ipc: {
                            required: true
                        }
                    },
                    // Messages for form validation
                    messages: {
                        utm: {
                            required: 'Por favor ingrese el valor de la UTM'
                        },
                        bencina95: {
                            ipc: 'Por favor ingrese el porcentaje del IPC'
                        }
                    },
                    // Do not change code below
                    errorPlacement: function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                if ($("#combustible").length) {

                    var bencina93 = [[1, 65], [3, 87], [4, 3, 34, 93], [5, 127], [6, 116], [7, 137], [8, 135], [9, 130], [10, 167], [11, 169], [12, 179], [13, 100], [14, 176], [15, 180], [16, 174], [17, 193], [18, 186], [19, 177], [20, 153], [21, 149], [22, 130], [23, 100], [24, 50]];
                    var bencina95 = [[1, 75], [3, 50], [4, 73], [5, 100], [6, 95], [7, 103], [8, 111], [9, 97], [10, 125], [11, 100], [12, 95], [13, 141], [14, 120], [15, 131], [16, 146], [17, 158], [18, 160], [19, 151], [20, 125], [21, 110], [22, 100], [23, 85], [24, 37]];
                    var bencina97 = [[1, 82], [3, 23], [4, 12], [5, 45], [6, 85], [7, 78], [8, 178], [9, 47], [10, 125], [11, 100], [12, 95], [13, 141], [14, 130], [15, 131], [16, 146], [17, 158], [18, 160], [19, 151], [20, 125], [21, 110], [22, 100], [23, 85], [24, 37]];
                    var diesel = [[1, 43], [3, 30], [4, 53], [5, 160], [6, 35], [7, 133], [8, 121], [9, 47], [10, 125], [11, 100], [12, 95], [13, 141], [14, 90], [15, 131], [16, 146], [17, 158], [18, 160], [19, 151], [20, 125], [21, 110], [22, 100], [23, 85], [24, 37]];
                    //console.log(pageviews)
                    var plot = $.plot($("#combustible"), [{
                            data: bencina93,
                            label: "Bencina 93"
                        }, {
                            data: bencina95,
                            label: "Bencina 95"
                        }, {
                            data: bencina97,
                            label: "Bencina 97"
                        }, {
                            data: diesel,
                            label: "Diesel"
                        }], {
                        series: {
                            lines: {
                                show: true,
                                lineWidth: 1,
                                fill: true,
                                fillColor: {
                                    colors: [{
                                            opacity: 0.1
                                        }, {
                                            opacity: 0.15
                                        }]
                                }
                            },
                            points: {
                                show: true
                            },
                            shadowSize: 0
                        },
                        xaxis: {
                            mode: "time",
                            tickLength: 10
                        },
                        yaxes: [{
                                min: 20,
                                tickLength: 5
                            }],
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: $chrt_border_color,
                            borderWidth: 0,
                            borderColor: $chrt_border_color,
                        },
                        tooltip: true,
                        tooltipOpts: {
                            content: "El precio de %s para el día <b>%x</b> fue de <font color='green'><b>$%y<b></font>",
                            dateFormat: "%y-%0m-%0d",
                            defaultTheme: false
                        },
                        colors: [$red, $blue, $green, $yellow],
                        xaxis : {
                            ticks: 15,
                            tickDecimals: 0
                        },
                        yaxis: {
                            ticks: 15,
                            tickDecimals: 0
                        },
                    });

                }

                if ($("#saleschart").length) {

                    var d = [[1196463600000, 0],
                        [1196550000000, 0],
                        [1196636400000, 0],
                        [1196722800000, 77],
                        [1196809200000, 3636],
                        [1196895600000, 3575],
                        [1196982000000, 2736],
                        [1197068400000, 1086],
                        [1197154800000, 676],
                        [1197241200000, 1205],
                        [1197327600000, 906],
                        [1197414000000, 710],
                        [1197500400000, 639],
                        [1197586800000, 540],
                        [1197673200000, 435],
                        [1197759600000, 301],
                        [1197846000000, 575],
                        [1197932400000, 481],
                        [1198018800000, 591],
                        [1198105200000, 608],
                        [1198191600000, 459],
                        [1198278000000, 234],
                        [1198364400000, 1352],
                        [1198450800000, 686],
                        [1198537200000, 279],
                        [1198623600000, 449],
                        [1198710000000, 468],
                        [1198796400000, 392], [1198882800000, 282], [1198969200000, 208], [1199055600000, 229], [1199142000000, 177], [1199228400000, 374], [1199314800000, 436], [1199401200000, 404], [1199487600000, 253], [1199574000000, 218], [1199660400000, 476], [1199746800000, 462], [1199833200000, 500], [1199919600000, 700], [1200006000000, 750], [1200092400000, 600], [1200178800000, 500], [1200265200000, 900], [1200351600000, 930], [1200438000000, 1200], [1200524400000, 980], [1200610800000, 950], [1200697200000, 900], [1200783600000, 1000], [1200870000000, 1050], [1200956400000, 1150], [1201042800000, 1100], [1201129200000, 1200], [1201215600000, 1300], [1201302000000, 1700], [1201388400000, 1450], [1201474800000, 1500], [1201561200000, 546], [1201647600000, 614], [1201734000000, 954], [1201820400000, 1700], [1201906800000, 1800], [1201993200000, 1900], [1202079600000, 2000], [1202166000000, 2100], [1202252400000, 2200], [1202338800000, 2300], [1202425200000, 2400], [1202511600000, 2550], [1202598000000, 2600], [1202684400000, 2500], [1202770800000, 2700], [1202857200000, 2750], [1202943600000, 2800], [1203030000000, 3245], [1203116400000, 3345], [1203202800000, 3000], [1203289200000, 3200], [1203375600000, 3300], [1203462000000, 3400], [1203548400000, 3600], [1203634800000, 3700], [1203721200000, 3800], [1203807600000, 4000], [1203894000000, 4500], ];

                    for (var i = 0; i < d.length; ++i)
                        d[i][0] += 60 * 60 * 1000;

                    function weekendAreas(axes) {
                        var markings = [];
                        var d = new Date(axes.xaxis.min);
                        // go to the first Saturday
                        d.setUTCDate(d.getUTCDate() - ((d.getUTCDay() + 1) % 7))
                        d.setUTCSeconds(0);
                        d.setUTCMinutes(0);
                        d.setUTCHours(0);
                        var i = d.getTime();
                        do {
                            // when we don't set yaxis, the rectangle automatically
                            // extends to infinity upwards and downwards
                            markings.push({
                                xaxis: {
                                    from: i,
                                    to: i + 2 * 24 * 60 * 60 * 1000
                                }
                            });
                            i += 7 * 24 * 60 * 60 * 1000;
                        } while (i < axes.xaxis.max);

                        return markings;
                    }

                    var options = {
                        xaxis: {
                            mode: "time",
                            tickLength: 5
                        },
                        series: {
                            lines: {
                                show: true,
                                lineWidth: 1,
                                fill: true,
                                fillColor: {
                                    colors: [{
                                            opacity: 0.1
                                        }, {
                                            opacity: 0.15
                                        }]
                                }
                            },
                            //points: { show: true },
                            shadowSize: 0
                        },
                        selection: {
                            mode: "x"
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: $chrt_border_color,
                            borderWidth: 0,
                            borderColor: $chrt_border_color,
                        },
                        tooltip: true,
                        tooltipOpts: {
                            content: "El valor de la UF para el <b>%x</b> fue <span>$%y</span>",
                            dateFormat: "%0d-%0m-%y",
                            defaultTheme: false
                        },
                        colors: [$chrt_second],
                    };

                    var plot = $.plot($("#saleschart"), [d], options);
                }

                if ($("#ufGraph").length) {
                    var d = [[1196463600000, 0],
                        [1196550000000, 0],
                        [1196636400000, 0],
                        [1196722800000, 77],
                        [1196809200000, 3636],
                        [1196895600000, 3575],
                        [1196982000000, 2736],
                        [1197068400000, 1086],
                        [1197154800000, 676],
                        [1197241200000, 1205],
                        [1197327600000, 906],
                        [1197414000000, 710],
                        [1197500400000, 639],
                        [1197586800000, 540],
                        [1197673200000, 435],
                        [1197759600000, 301],
                        [1197846000000, 575],
                        [1197932400000, 481],
                        [1198018800000, 591],
                        [1198105200000, 608],
                        [1198191600000, 459],
                        [1198278000000, 234],
                        [1198364400000, 1352],
                        [1198450800000, 686],
                        [1198537200000, 279],
                        [1198623600000, 449],
                        [1198710000000, 468],
                        [1198796400000, 392], [1198882800000, 282], [1198969200000, 208], [1199055600000, 229], [1199142000000, 177], [1199228400000, 374], [1199314800000, 436], [1199401200000, 404], [1199487600000, 253], [1199574000000, 218], [1199660400000, 476], [1199746800000, 462], [1199833200000, 500], [1199919600000, 700], [1200006000000, 750], [1200092400000, 600], [1200178800000, 500], [1200265200000, 900], [1200351600000, 930], [1200438000000, 1200], [1200524400000, 980], [1200610800000, 950], [1200697200000, 900], [1200783600000, 1000], [1200870000000, 1050], [1200956400000, 1150], [1201042800000, 1100], [1201129200000, 1200], [1201215600000, 1300], [1201302000000, 1700], [1201388400000, 1450], [1201474800000, 1500], [1201561200000, 546], [1201647600000, 614], [1201734000000, 954], [1201820400000, 1700], [1201906800000, 1800], [1201993200000, 1900], [1202079600000, 2000], [1202166000000, 2100], [1202252400000, 2200], [1202338800000, 2300], [1202425200000, 2400], [1202511600000, 2550], [1202598000000, 2600], [1202684400000, 2500], [1202770800000, 2700], [1202857200000, 2750], [1202943600000, 2800], [1203030000000, 3245], [1203116400000, 3345], [1203202800000, 3000], [1203289200000, 3200], [1203375600000, 3300], [1203462000000, 3400], [1203548400000, 3600], [1203634800000, 3700], [1203721200000, 3800], [1203807600000, 4000], [1203894000000, 4500], ];

                    for (var i = 0; i < d.length; ++i)
                        d[i][0] += 60 * 60 * 1000;

                    function weekendAreas(axes) {
                        var markings = [];
                        var d = new Date(axes.xaxis.min);
                        // go to the first Saturday
                        d.setUTCDate(d.getUTCDate() - ((d.getUTCDay() + 1) % 7))
                        d.setUTCSeconds(0);
                        d.setUTCMinutes(0);
                        d.setUTCHours(0);
                        var i = d.getTime();
                        do {
                            // when we don't set yaxis, the rectangle automatically
                            // extends to infinity upwards and downwards
                            markings.push({
                                xaxis: {
                                    from: i,
                                    to: i + 2 * 24 * 60 * 60 * 1000
                                }
                            });
                            i += 7 * 24 * 60 * 60 * 1000;
                        } while (i < axes.xaxis.max);

                        return markings;
                    }

                    var options = {
                        xaxis: {
                            mode: "time",
                            tickLength: 5
                        },
                        series: {
                            lines: {
                                show: true,
                                lineWidth: 1,
                                fill: true,
                                fillColor: {
                                    colors: [{
                                            opacity: 0.1
                                        }, {
                                            opacity: 0.15
                                        }]
                                }
                            },
                            //points: { show: true },
                            shadowSize: 0
                        },
                        selection: {
                            mode: "x"
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: $chrt_border_color,
                            borderWidth: 0,
                            borderColor: $chrt_border_color,
                        },
                        tooltip: true,
                        tooltipOpts: {
                            content: "El valor de la UF para el <b>%x</b> fue <span>$%y</span>",
                            dateFormat: "%0d-%0m-%y",
                            defaultTheme: false
                        },
                        colors: [$purple],
                    };

                    var plot = $.plot($("#ufGraph"), [d], options);
                }

                if ($("#utmChart").length) {
                    var d = [[1196463600000, 0],
                        [1196550000000, 0],
                        [1196636400000, 0],
                        [1196722800000, 77],
                        [1196809200000, 3636],
                        [1196895600000, 3575],
                        [1196982000000, 2736],
                        [1197068400000, 1086],
                        [1197154800000, 676],
                        [1197241200000, 1205],
                        [1197327600000, 906],
                        [1197414000000, 710],
                        [1197500400000, 639],
                        [1197586800000, 540],
                        [1197673200000, 435],
                        [1197759600000, 301],
                        [1197846000000, 575],
                        [1197932400000, 481],
                        [1198018800000, 591],
                        [1198105200000, 608],
                        [1198191600000, 459],
                        [1198278000000, 234],
                        [1198364400000, 1352],
                        [1198450800000, 686],
                        [1198537200000, 279],
                        [1198623600000, 449],
                        [1198710000000, 468],
                        [1198796400000, 392], [1198882800000, 282], [1198969200000, 208], [1199055600000, 229], [1199142000000, 177], [1199228400000, 374], [1199314800000, 436], [1199401200000, 404], [1199487600000, 253], [1199574000000, 218], [1199660400000, 476], [1199746800000, 462], [1199833200000, 500], [1199919600000, 700], [1200006000000, 750], [1200092400000, 600], [1200178800000, 500], [1200265200000, 900], [1200351600000, 930], [1200438000000, 1200], [1200524400000, 980], [1200610800000, 950], [1200697200000, 900], [1200783600000, 1000], [1200870000000, 1050], [1200956400000, 1150], [1201042800000, 1100], [1201129200000, 1200], [1201215600000, 1300], [1201302000000, 1700], [1201388400000, 1450], [1201474800000, 1500], [1201561200000, 546], [1201647600000, 614], [1201734000000, 954], [1201820400000, 1700], [1201906800000, 1800], [1201993200000, 1900], [1202079600000, 2000], [1202166000000, 2100], [1202252400000, 2200], [1202338800000, 2300], [1202425200000, 2400], [1202511600000, 2550], [1202598000000, 2600], [1202684400000, 2500], [1202770800000, 2700], [1202857200000, 2750], [1202943600000, 2800], [1203030000000, 3245], [1203116400000, 3345], [1203202800000, 3000], [1203289200000, 3200], [1203375600000, 3300], [1203462000000, 3400], [1203548400000, 3600], [1203634800000, 3700], [1203721200000, 3800], [1203807600000, 4000], [1203894000000, 4500], ];

                    for (var i = 0; i < d.length; ++i)
                        d[i][0] += 60 * 60 * 1000;

                    function weekendAreas(axes) {
                        var markings = [];
                        var d = new Date(axes.xaxis.min);
                        // go to the first Saturday
                        d.setUTCDate(d.getUTCDate() - ((d.getUTCDay() + 1) % 7))
                        d.setUTCSeconds(0);
                        d.setUTCMinutes(0);
                        d.setUTCHours(0);
                        var i = d.getTime();
                        do {
                            // when we don't set yaxis, the rectangle automatically
                            // extends to infinity upwards and downwards
                            markings.push({
                                xaxis: {
                                    from: i,
                                    to: i + 2 * 24 * 60 * 60 * 1000
                                }
                            });
                            i += 7 * 24 * 60 * 60 * 1000;
                        } while (i < axes.xaxis.max);

                        return markings;
                    }

                    var options = {
                        xaxis: {
                            mode: "time",
                            tickLength: 5
                        },
                        series: {
                            lines: {
                                show: true,
                                lineWidth: 1,
                                fill: true,
                                fillColor: {
                                    colors: [{
                                            opacity: 0.1
                                        }, {
                                            opacity: 0.15
                                        }]
                                }
                            },
                            //points: { show: true },
                            shadowSize: 0
                        },
                        selection: {
                            mode: "x"
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: $chrt_border_color,
                            borderWidth: 0,
                            borderColor: $chrt_border_color,
                        },
                        tooltip: true,
                        tooltipOpts: {
                            content: "El valor de la UF para el <b>%x</b> fue <span>$%y</span>",
                            dateFormat: "%0d-%0m-%y",
                            defaultTheme: false
                        },
                        colors: [$chrt_second],
                    };

                    var plot = $.plot($("#utmChart"), [d], options);
                }

                // donut
                if ($('#ipcGraph').length) {
                    Morris.Donut({
                        element: 'ipcGraph',
                        data: [{
                                value: 0.15,
                                label: 'Enero'
                            }, {
                                value: 0.23,
                                label: 'Febrero'
                            }, {
                                value: 0.23,
                                label: 'Marzo'
                            }, {
                                value: 0.12,
                                label: 'Abril'
                            }, {
                                value: 0.12,
                                label: 'Mayo'
                            }, {
                                value: 0.14,
                                label: 'Junio'
                            }, {
                                value: 0.5,
                                label: 'Julio'
                            }, {
                                value: 0.34,
                                label: 'Agosto'
                            }, {
                                value: 0.4,
                                label: 'Septiembre'
                            }, {
                                value: 0.3,
                                label: 'Octubre'
                            }, {
                                value: 0.2,
                                label: 'Noviembre'
                            }, {
                                value: 0.12,
                                label: 'Diciembre'
                            }],
                        formatter: function(x) {
                            return x + "%"
                        }
                    });
                }

                if ($('#year-graph').length) {
                    var day_data = [{
                            "period": "2012-10-01",
                            "EUR": 700,
                            "USD": 660
                        }, {
                            "period": "2012-09-30",
                            "EUR": 710,
                            "USD": 629
                        }, {
                            "period": "2012-09-29",
                            "EUR": 715,
                            "USD": 618
                        }, {
                            "period": "2012-09-20",
                            "EUR": 714,
                            "USD": 661
                        }, {
                            "period": "2012-09-19",
                            "EUR": 750,
                            "USD": 667
                        }, {
                            "period": "2012-09-18",
                            "EUR": 703,
                            "USD": 627
                        }, {
                            "period": "2012-09-17",
                            "EUR": 701,
                            "USD": 660
                        }, {
                            "period": "2012-09-16",
                            "EUR": 771,
                            "USD": 676
                        }, {
                            "period": "2012-09-15",
                            "EUR": 789,
                            "USD": 656
                        }, {
                            "period": "2012-09-10",
                            "EUR": 785,
                            "USD": 622
                        }];
                    Morris.Line({
                        element: 'year-graph',
                        data: day_data,
                        xkey: 'period',
                        ykeys: ['EUR', 'USD'],
                        labels: ['EUR', 'USD']
                    })
                }

            });

            /* end flot charts */

        </script>

        <!-- Your GOOGLE ANALYTICS CODE Below -->
        <script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
            _gaq.push(['_trackPageview']);

            (function() {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();

        </script>

    </body>

</html>