<%-- 
    Document   : personaUpdate
    Created on : 27-04-2014, 11:31:01 AM
    Author     : patricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

        <title> Latte ERP | 
            <c:if test="${clienteActive != null}">Clientes</c:if>
            </title>
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
            <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.css">

            <!-- SmartAdmin RTL Support is under construction
            <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.css"> -->

            <!-- We recommend you use "your_style.css" to override SmartAdmin
                 specific styles this will also ensure you retrain your customization with each SmartAdmin update.
            <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

            <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
            <link rel="stylesheet" type="text/css" media="screen" href="css/demo.css">

            <!-- FAVICONS -->
            <link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
            <link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

            <!-- GOOGLE FONT -->
            <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
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
                        <button type="button" data-loading-text="<i class='fa fa-refresh fa-spin'></i> Loading..." class="btn btn-xs btn-default pull-right">
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
                        <c:if test="${clienteActive != null}">
                            <a href="ClienteMainServlet"><i class="text-primary fa fa-table"></i> DataTable Clientes</a>
                        </c:if>
                    </li>      
                    <li>
                        <i class="fa fa-edit"></i>
                        <c:if test="${clienteActive != null}">
                            Agregar Cliente
                        </c:if>
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
                                <i class="fa fa-user fa-fw"></i>                                 
                                <c:if test="${clienteActive != null}">
                                    Clientes
                                </c:if>
                            </span>
                        </h2>
                    </div>
                    <!-- INDICADORES ECONOMICOS HEADER -->
                    <c:import var="indicadoresHeader" url="/indicadoresHeader.jsp" />
                    <c:out value="${indicadoresHeader}" escapeXml="false" />
                </div>

                <!-- MENSAJES -->
                <c:import var="formMsg" url="/formMsg.jsp" />
                <c:out value="${formMsg}" escapeXml="false"/>

                <!-- widget grid -->
                <section id="widget-grid" class="">

                    <!-- START ROW -->
                    <div class="row">

                        <!-- NEW COL START -->
                        <article class="col-sm-12 col-md-12 col-lg-6">

                            <!-- Widget ID (each widget will need unique ID)-->
                            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-1" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-edit"></i> </span>
                                    <h2>Datos de Persona </h2>
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

                                        <form action="<c:if test="${clienteActive != null}">ClienteAddServlet</c:if>" 
                                              method="POST" name="form" id="form" class="smart-form" novalidate="novalidate">
                                            <c:if test="${clienteActive != null}"><input type="hidden" name="tipoPersona" value="1"></c:if>
                                                <fieldset>
                                                    <section>
                                                        <div class="inline-group">
                                                            <label class="radio">
                                                                <input type="radio" name="radio-inline" id="r1" value="1" checked="" onclick="changeDisplay();">
                                                                <i></i>Persona Natural</label>
                                                            <label class="radio">
                                                                <input type="radio" name="radio-inline" id="r2" value="2" onclick="changeDisplay();">
                                                                <i></i>Persona Jurídica</label>                                                        
                                                        </div>
                                                    </section>
                                                    <div class="row">                                                    
                                                        <section class="col col-6">
                                                            <label class="label">RUT</label>
                                                        <c:if test="${msgErrorRUT == null}">
                                                            <label class="input"> 
                                                            </c:if>
                                                            <c:if test="${msgErrorRUT != null}">
                                                                <label class="input state-error">
                                                                </c:if>
                                                                <i class="icon-prepend fa fa-user"></i>
                                                                <input type="text" maxlength="12" name="rut" placeholder="Ingrese RUT" value="<c:out value="${rut}"/>">
                                                                <b class="tooltip tooltip-top-right"><i class='text-yellowLight fa fa-warning'></i> Ingrese RUT</b>
                                                            </label>
                                                            <div class="note note-error">Este campo es requerido.</div>
                                                    </section>                                                  
                                                </div>                                             
                                                <div class="row">
                                                    <section class="col col-6">
                                                        <span class="label label-danger"><small class="slideInRight"><font color="white" size="1"><strong>&nbsp;&nbsp; Formato Rut:</strong> 12345678-9</font></small></span>
                                                    </section>
                                                </div>
                                            </fieldset>
                                            <fieldset>
                                                <div class="row">                                                    
                                                    <section class="col col-6">
                                                        <label class="label">Nombre o Razón social</label>
                                                        <c:if test="${msgErrorNombre == null}">
                                                            <label class="input"> 
                                                            </c:if>
                                                            <c:if test="${msgErrorNombre != null}">
                                                                <label class="input state-error">
                                                                </c:if>
                                                                <i class="icon-prepend fa fa-font"></i>
                                                                <input type="text" maxlength="100" name="nombre" placeholder="Ingrese nombre o razón social" value="<c:out value="${nombre}"/>">
                                                                <b class="tooltip tooltip-top-right"><i class='text-yellowLight fa fa-warning'></i> Ingrese nombre o razón social</b>
                                                            </label>
                                                            <div class="note note-error">Este campo es requerido.</div>
                                                    </section>
                                                    <div id="apellido">
                                                        <section class="col col-6">
                                                            <label class="label">Apellido</label>
                                                            <c:if test="${msgErrorApellido == null}">
                                                                <label class="input"> 
                                                                </c:if>
                                                                <c:if test="${msgErrorApellido != null}">
                                                                    <label class="input state-error">
                                                                    </c:if>
                                                                    <i class="icon-prepend fa fa-font"></i>
                                                                    <input type="text" maxlength="45" name="apellido" placeholder="Ingrese apeliido" value="<c:out value="${apellido}"/>">
                                                                    <b class="tooltip tooltip-top-right"><i class='text-yellowLight fa fa-warning'></i> Ingrese apellido</b>
                                                                </label>
                                                                <div class="note note-error">Este campo es requerido.</div>
                                                        </section>
                                                    </div>
                                                </div>
                                                <div class="row">                                                    
                                                    <section class="col col-sm-12">
                                                        <label class="label">Giro</label>
                                                        <c:if test="${msgErrorGiro == null}">
                                                            <label class="input"> 
                                                            </c:if>
                                                            <c:if test="${msgErrorGiro != null}">
                                                                <label class="input state-error">
                                                                </c:if>
                                                                <i class="icon-prepend fa fa-usd"></i>
                                                                <input type="text" maxlength="255" name="giro" placeholder="Ingrese giro o rubro" value="<c:out value="${giro}"/>">
                                                                <b class="tooltip tooltip-top-right"><i class='text-yellowLight fa fa-warning'></i> Ingrese giro o rubro</b>
                                                            </label>
                                                            <div class="note note-error">Este campo es requerido.</div>
                                                    </section>                                                  
                                                </div>
                                                <div id="fecNac">
                                                    <div class="row">                                                                                                             
                                                        <section class="col col-6">  
                                                            <div class="form-group">
                                                                <c:if test="${msgErrorFecNac == null}"><label class="input"></c:if>
                                                                    <c:if test="${msgErrorFecNac != null}"><label class="input state-error"></c:if>
                                                                            <label class="label">Fecha de Nacimiento</label>
                                                                            <div class="input-group">
                                                                                <input type="text" name="publicTime" class="form-control" data-mask="99/99/9999" data-mask-placeholder="-" value="<c:out value="${fecNac}"/>">
                                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                                        </div>
                                                                        <p class="note note-error">
                                                                            Este campo es requerido </br> Formato de Fecha **/**/**** 
                                                                        </p>
                                                                    </label>
                                                                </label>
                                                            </div>
                                                        </section>                                                   
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <section class="col col-6">
                                                        <label class="label">Dirección</label>
                                                        <c:if test="${msgErrorDireccion == null}">
                                                            <label class="input"> 
                                                            </c:if>
                                                            <c:if test="${msgErrorDireccion != null}">
                                                                <label class="input state-error">
                                                                </c:if>
                                                                <i class="icon-prepend fa fa-map-marker"></i>
                                                                <input type="text" maxlength="255" name="direccion" placeholder="Ingrese dirección" value="<c:out value="${direccion}"/>">
                                                                <b class="tooltip tooltip-top-right"><i class='text-yellowLight fa fa-warning'></i> Ingrese dirección</b>
                                                            </label>
                                                            <div class="note note-error">Este campo es requerido.</div>
                                                    </section>                                                  

                                                    <section class="col col-6">
                                                        <label class="label">Ciudad</label>
                                                        <label class="select">
                                                            <select name="idCiudad">                                                                
                                                                <option value="1" <c:if test="${idCiudad == 1}"> selected </c:if> >Valparaíso</option>
                                                                <option value="2" <c:if test="${idCiudad == 2}"> selected </c:if>>Viña Del Mar</option>                                                                                                                                
                                                                </select> <i></i> </label>
                                                        </section> 
                                                    </div>
                                                    <div class="row">
                                                        <section class="col col-6">                                                        
                                                            <label>Teléfono red fija</label>
                                                            <div class="input-group">
                                                                <input type="text" name="telFijo" class="form-control" data-mask=" (999) 9-999999" data-mask-placeholder="X" value="<c:out value="${telFijo}"/>">
                                                            <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                                                        </div>
                                                        <p class="note">
                                                            Este campo es requerido. </br>                                                            
                                                        </p>                                                        
                                                    </section>                                               
                                                    <section class="col col-6">                                                        
                                                        <label>Celular</label>
                                                        <div class="input-group">
                                                            <input type="text" name="telMovil" class="form-control" data-mask=" (999) 9999-9999" data-mask-placeholder="X" value="<c:out value="${telMovil}"/>">
                                                            <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                                                        </div>
                                                        <p class="note">
                                                            Este campo es opcional. </br>                                                            
                                                        </p>                                                        
                                                    </section>
                                                </div>
                                                <div class="row">
                                                    <section class="col col-6">
                                                        <label>Email</label>
                                                        <label class="input"> 
                                                            <i class="icon-prepend fa fa-envelope-o"></i>
                                                            <input type="email" name="email" placeholder="E-mail" value="<c:out value="${email}"/>">
                                                        </label>
                                                        <p class="note">
                                                            Este campo es opcional.                                                            
                                                        </p> 
                                                    </section>                                                   
                                                </div>                
                                            </fieldset>                                                                                                                                     

                                            <footer>
                                                <button class="btn btn-primary" type="submit">
                                                    <span><font size="1">AGREGAR</font></span>
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
                        <!-- END COL -->                       		

                    </div>
                    <!-- END ROW -->

                </section>
                <!-- end widget grid -->

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
        <script src="js/plugin/jquery-form/jquery-form.min.js"></script>

        <!-- disabledButton -->
        <script src="js/disabledButton.js"></script> 

        <script type="text/javascript">
                                                                    function changeDisplay() {
                                                                        r1 = document.getElementById("r1");
                                                                        r2 = document.getElementById("r2");
                                                                        apellido = document.getElementById("apellido");
                                                                        fecNac = document.getElementById("fecNac");

                                                                        if (r2.checked == false) {
                                                                            apellido.style.display = 'block';
                                                                            fecNac.style.display = 'block';
                                                                        } else {
                                                                            apellido.style.display = 'none';
                                                                            fecNac.style.display = 'none';
                                                                        }
                                                                    }
        </script>

        <script type="text/javascript">

            // DO NOT REMOVE : GLOBAL FUNCTIONS!

            $(document).ready(function() {

                pageSetUp();

                var $checkoutForm = $('#form').validate({
                    // Rules for form validation
                    rules: {
                        uf: {
                            required: true
                        },
                        dolar: {
                            required: true
                        },
                        euro: {
                            required: true
                        }
                    },
                    // Messages for form validation
                    messages: {
                        uf: {
                            required: 'Por favor ingrese el valor de la UF'
                        },
                        dolar: {
                            required: 'Por favor ingrese el valor del Dólar'
                        },
                        euro: {
                            required: 'Por favor ingrese el valor del Euro'
                        }
                    },
                    // Do not change code below
                    errorPlacement: function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                // Mensajes emergentes
            <c:if test="${msgOk != null}">
                $("#correcto").ready(function(e) {
                    $.smallBox({
                        title: "Correcto!",
                        content: "<i class='glyphicon glyphicon-floppy-disk'></i> <i> Registro guardado...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 5000
                    });
                    e.preventDefault();
                });
            </c:if>

            <c:if test="${msgList != null}">
                    $("#errores").ready(function(e) {
                        $.smallBox({
                            title: "Error!",
                            content: "<i class='glyphicon glyphicon-floppy-remove'></i> <i>No se pudo guardar el registro...</i>",
                            color: "#C46A69",
                            iconSmall: "fa fa-times fa-2x fadeInRight animated",
                            timeout: 5000
                        });
                        e.preventDefault();
                    });
            </c:if>


                    $('#state').editable({
                        source: ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                            "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                            "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
                            "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                            "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon",
                            "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
                            "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
                        ]
                    });

                    $('#state2').editable({
                        value: 'California',
                        typeahead: {
                            name: 'state',
                            local: ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
                                "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
                                "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio",
                                "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
                                "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
                                "Wisconsin", "Wyoming"
                            ]
                        }
                    });

                    $('#fruits').editable({
                        pk: 1,
                        limit: 3,
                        source: [{
                                value: 1,
                                text: 'banana'
                            }, {
                                value: 2,
                                text: 'peach'
                            }, {
                                value: 3,
                                text: 'apple'
                            }, {
                                value: 4,
                                text: 'watermelon'
                            }, {
                                value: 5,
                                text: 'orange'
                            }]
                    });

                    $('#tags').editable({
                        inputclass: 'input-large',
                        select2: {
                            tags: ['html', 'javascript', 'css', 'ajax'],
                            tokenSeparators: [",", " "]
                        }
                    });

                    var countries = [];
                    $.each({
                        "BD": "Bangladesh",
                        "BE": "Belgium",
                        "BF": "Burkina Faso",
                        "BG": "Bulgaria",
                        "BA": "Bosnia and Herzegovina",
                        "BB": "Barbados",
                        "WF": "Wallis and Futuna",
                        "BL": "Saint Bartelemey",
                        "BM": "Bermuda",
                        "BN": "Brunei Darussalam",
                        "BO": "Bolivia",
                        "BH": "Bahrain",
                        "BI": "Burundi",
                        "BJ": "Benin",
                        "BT": "Bhutan",
                        "JM": "Jamaica",
                        "BV": "Bouvet Island",
                        "BW": "Botswana",
                        "WS": "Samoa",
                        "BR": "Brazil",
                        "BS": "Bahamas",
                        "JE": "Jersey",
                        "BY": "Belarus",
                        "O1": "Other Country",
                        "LV": "Latvia",
                        "RW": "Rwanda",
                        "RS": "Serbia",
                        "TL": "Timor-Leste",
                        "RE": "Reunion",
                        "LU": "Luxembourg",
                        "TJ": "Tajikistan",
                        "RO": "Romania",
                        "PG": "Papua New Guinea",
                        "GW": "Guinea-Bissau",
                        "GU": "Guam",
                        "GT": "Guatemala",
                        "GS": "South Georgia and the South Sandwich Islands",
                        "GR": "Greece",
                        "GQ": "Equatorial Guinea",
                        "GP": "Guadeloupe",
                        "JP": "Japan",
                        "GY": "Guyana",
                        "GG": "Guernsey",
                        "GF": "French Guiana",
                        "GE": "Georgia",
                        "GD": "Grenada",
                        "GB": "United Kingdom",
                        "GA": "Gabon",
                        "SV": "El Salvador",
                        "GN": "Guinea",
                        "GM": "Gambia",
                        "GL": "Greenland",
                        "GI": "Gibraltar",
                        "GH": "Ghana",
                        "OM": "Oman",
                        "TN": "Tunisia",
                        "JO": "Jordan",
                        "HR": "Croatia",
                        "HT": "Haiti",
                        "HU": "Hungary",
                        "HK": "Hong Kong",
                        "HN": "Honduras",
                        "HM": "Heard Island and McDonald Islands",
                        "VE": "Venezuela",
                        "PR": "Puerto Rico",
                        "PS": "Palestinian Territory",
                        "PW": "Palau",
                        "PT": "Portugal",
                        "SJ": "Svalbard and Jan Mayen",
                        "PY": "Paraguay",
                        "IQ": "Iraq",
                        "PA": "Panama",
                        "PF": "French Polynesia",
                        "BZ": "Belize",
                        "PE": "Peru",
                        "PK": "Pakistan",
                        "PH": "Philippines",
                        "PN": "Pitcairn",
                        "TM": "Turkmenistan",
                        "PL": "Poland",
                        "PM": "Saint Pierre and Miquelon",
                        "ZM": "Zambia",
                        "EH": "Western Sahara",
                        "RU": "Russian Federation",
                        "EE": "Estonia",
                        "EG": "Egypt",
                        "TK": "Tokelau",
                        "ZA": "South Africa",
                        "EC": "Ecuador",
                        "IT": "Italy",
                        "VN": "Vietnam",
                        "SB": "Solomon Islands",
                        "EU": "Europe",
                        "ET": "Ethiopia",
                        "SO": "Somalia",
                        "ZW": "Zimbabwe",
                        "SA": "Saudi Arabia",
                        "ES": "Spain",
                        "ER": "Eritrea",
                        "ME": "Montenegro",
                        "MD": "Moldova, Republic of",
                        "MG": "Madagascar",
                        "MF": "Saint Martin",
                        "MA": "Morocco",
                        "MC": "Monaco",
                        "UZ": "Uzbekistan",
                        "MM": "Myanmar",
                        "ML": "Mali",
                        "MO": "Macao",
                        "MN": "Mongolia",
                        "MH": "Marshall Islands",
                        "MK": "Macedonia",
                        "MU": "Mauritius",
                        "MT": "Malta",
                        "MW": "Malawi",
                        "MV": "Maldives",
                        "MQ": "Martinique",
                        "MP": "Northern Mariana Islands",
                        "MS": "Montserrat",
                        "MR": "Mauritania",
                        "IM": "Isle of Man",
                        "UG": "Uganda",
                        "TZ": "Tanzania, United Republic of",
                        "MY": "Malaysia",
                        "MX": "Mexico",
                        "IL": "Israel",
                        "FR": "France",
                        "IO": "British Indian Ocean Territory",
                        "FX": "France, Metropolitan",
                        "SH": "Saint Helena",
                        "FI": "Finland",
                        "FJ": "Fiji",
                        "FK": "Falkland Islands (Malvinas)",
                        "FM": "Micronesia, Federated States of",
                        "FO": "Faroe Islands",
                        "NI": "Nicaragua",
                        "NL": "Netherlands",
                        "NO": "Norway",
                        "NA": "Namibia",
                        "VU": "Vanuatu",
                        "NC": "New Caledonia",
                        "NE": "Niger",
                        "NF": "Norfolk Island",
                        "NG": "Nigeria",
                        "NZ": "New Zealand",
                        "NP": "Nepal",
                        "NR": "Nauru",
                        "NU": "Niue",
                        "CK": "Cook Islands",
                        "CI": "Cote d'Ivoire",
                        "CH": "Switzerland",
                        "CO": "Colombia",
                        "CN": "China",
                        "CM": "Cameroon",
                        "CL": "Chile",
                        "CC": "Cocos (Keeling) Islands",
                        "CA": "Canada",
                        "CG": "Congo",
                        "CF": "Central African Republic",
                        "CD": "Congo, The Democratic Republic of the",
                        "CZ": "Czech Republic",
                        "CY": "Cyprus",
                        "CX": "Christmas Island",
                        "CR": "Costa Rica",
                        "CV": "Cape Verde",
                        "CU": "Cuba",
                        "SZ": "Swaziland",
                        "SY": "Syrian Arab Republic",
                        "KG": "Kyrgyzstan",
                        "KE": "Kenya",
                        "SR": "Suriname",
                        "KI": "Kiribati",
                        "KH": "Cambodia",
                        "KN": "Saint Kitts and Nevis",
                        "KM": "Comoros",
                        "ST": "Sao Tome and Principe",
                        "SK": "Slovakia",
                        "KR": "Korea, Republic of",
                        "SI": "Slovenia",
                        "KP": "Korea, Democratic People's Republic of",
                        "KW": "Kuwait",
                        "SN": "Senegal",
                        "SM": "San Marino",
                        "SL": "Sierra Leone",
                        "SC": "Seychelles",
                        "KZ": "Kazakhstan",
                        "KY": "Cayman Islands",
                        "SG": "Singapore",
                        "SE": "Sweden",
                        "SD": "Sudan",
                        "DO": "Dominican Republic",
                        "DM": "Dominica",
                        "DJ": "Djibouti",
                        "DK": "Denmark",
                        "VG": "Virgin Islands, British",
                        "DE": "Germany",
                        "YE": "Yemen",
                        "DZ": "Algeria",
                        "US": "United States",
                        "UY": "Uruguay",
                        "YT": "Mayotte",
                        "UM": "United States Minor Outlying Islands",
                        "LB": "Lebanon",
                        "LC": "Saint Lucia",
                        "LA": "Lao People's Democratic Republic",
                        "TV": "Tuvalu",
                        "TW": "Taiwan",
                        "TT": "Trinidad and Tobago",
                        "TR": "Turkey",
                        "LK": "Sri Lanka",
                        "LI": "Liechtenstein",
                        "A1": "Anonymous Proxy",
                        "TO": "Tonga",
                        "LT": "Lithuania",
                        "A2": "Satellite Provider",
                        "LR": "Liberia",
                        "LS": "Lesotho",
                        "TH": "Thailand",
                        "TF": "French Southern Territories",
                        "TG": "Togo",
                        "TD": "Chad",
                        "TC": "Turks and Caicos Islands",
                        "LY": "Libyan Arab Jamahiriya",
                        "VA": "Holy See (Vatican City State)",
                        "VC": "Saint Vincent and the Grenadines",
                        "AE": "United Arab Emirates",
                        "AD": "Andorra",
                        "AG": "Antigua and Barbuda",
                        "AF": "Afghanistan",
                        "AI": "Anguilla",
                        "VI": "Virgin Islands, U.S.",
                        "IS": "Iceland",
                        "IR": "Iran, Islamic Republic of",
                        "AM": "Armenia",
                        "AL": "Albania",
                        "AO": "Angola",
                        "AN": "Netherlands Antilles",
                        "AQ": "Antarctica",
                        "AP": "Asia/Pacific Region",
                        "AS": "American Samoa",
                        "AR": "Argentina",
                        "AU": "Australia",
                        "AT": "Austria",
                        "AW": "Aruba",
                        "IN": "India",
                        "AX": "Aland Islands",
                        "AZ": "Azerbaijan",
                        "IE": "Ireland",
                        "ID": "Indonesia",
                        "UA": "Ukraine",
                        "QA": "Qatar",
                        "MZ": "Mozambique"
                    }, function(k, v) {
                        countries.push({
                            id: k,
                            text: v
                        });
                    });

                    $('#country').editable({
                        source: countries,
                        select2: {
                            width: 200
                        }
                    });

                    // START AND FINISH DATE
                    $('#startdate').datepicker({
                        dateFormat: 'dd.mm.yy',
                        prevText: '<i class="fa fa-chevron-left"></i>',
                        nextText: '<i class="fa fa-chevron-right"></i>',
                        onSelect: function(selectedDate) {
                            $('#finishdate').datepicker('option', 'minDate', selectedDate);
                        }
                    });

                    $('#finishdate').datepicker({
                        dateFormat: 'dd.mm.yy',
                        prevText: '<i class="fa fa-chevron-left"></i>',
                        nextText: '<i class="fa fa-chevron-right"></i>',
                        onSelect: function(selectedDate) {
                            $('#startdate').datepicker('option', 'maxDate', selectedDate);
                        }
                    });

                });
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