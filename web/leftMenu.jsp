<%-- 
    Document   : leftMenu
    Created on : 09-04-2014, 02:47:51 PM
    Author     : patricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="left-panel">

    <!-- User info -->
    <div class="login-info">
        <span> <!-- User image size is adjusted inside CSS, it should stay as it --> 

            <a href="javascript:void(0);" id="show-shortcut">
                <img src="img/avatars/sunny.png" alt="me" class="online" /> 
                <span>
                    <c:out value="${usernameX}"/>                    
                </span>
                <i class="fa fa-angle-down"></i>
            </a> 

        </span>
    </div>
    <!-- end user info -->

    <!-- NAVIGATION : This navigation is also responsive

    To make this navigation dynamic please make sure to link the node
    (the reference to the nav > ul) after page load. Or the navigation
    will not initialize.
    -->
    <nav>
        <!-- NOTE: Notice the gaps after each icon usage <i></i>..
        Please note that these links work a bit different than
        traditional hre="" links. See documentation for details.
        -->

        <ul>
            <li>
                <a href="index.html" title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">Dashboard (*)</span></a>
            </li>

            <li>
                <a href="inbox.html"><i class="fa fa-lg fa-fw fa-inbox"></i> <span class="menu-item-parent">Inbox (*)</span><span class="badge pull-right inbox-badge">14</span></a>
            </li>

            <li>
                <a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">Estadísticas (*)</span></a>
                <ul>
                    <li><a href="flot.html">Compras / Ventas</a></li>
                    <li><a href="morris.html">Ingresos / Egresos</a></li>
                    <li><a href="inline-charts.html">Inline Charts</a></li>
                </ul>
            </li>

            <li>
                <a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">Indicador Económico</span></a>
                <ul>
                    <li <c:if test="${indGrafActive != null}">class="active"</c:if>><a href="IndicadorEconomicoGetServlet">Gráficos</a></li>
                    <li <c:if test="${indicadorDiarioActive != null}">class="active"</c:if>><a href="IndicadorDiarioMainServlet">Mantenedor Diario</a></li>
                    <li <c:if test="${indicadorSemanalActive != null}">class="active"</c:if>><a href="IndicadorSemanalMainServlet">Mantenedor Semanal</a></li>
                    <li <c:if test="${indicadorMensualActive != null}">class="active"</c:if>><><a href="IndicadorMensualMainServlet">Mantenedor Mensual</a></li>
                    </ul>                
                </li>

                <li <c:if test="${userActive != null}">class="active"</c:if>>
                    <a href="UserMainServlet"><i class="fa fa-lg fa-fw fa-user"></i> <span class="menu-item-parent">Usuarios</span></a>
                </li>

                <li <c:if test="${ciudadActive != null}">class="active"</c:if>>
                    <a href="CiudadMainServlet"><i class="fa fa-lg fa-fw fa-map-marker"></i> <span class="menu-item-parent">Ciudades</span></a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-lg fa-fw fa-hdd"></i> <span class="menu-item-parent">ERP</span></a>
                    <ul>                        
                        <li><a href="#"><i class="fa fa-group"></i> <span class="menu-item-parent">Recursos Humanos</span></a>
                            <ul>
                                <li><a href="PersonalMainServlet">Personal</a></li>
                                <li><a href="#">Asistencia</a></li>
                                <li><a href="#">Remuneraciones</a></li>
                            </ul>  
                        </li>
                        <li><a href="#"><i class="fa fa-list-ol"></i> <span class="menu-item-parent">Inventario</span></a>  
                            <ul>
                                <li><a href="#">Asignaciones</a></li>
                                <li><a href="#">Bodegas</a></li>
                                <li><a href="#">Productos Bodega</a></li>
                                <li><a href="#">Ordenes Bodega</a>
                                    <ul>                            
                                        <li><a href="#">Ingresos</a></li>                            
                                        <li><a href="#">Egresos</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-lg fa-fw fa-shopping-cart"></i> <span class="menu-item-parent">Tienda</span></a>
                            <ul>
                                <li><a href="#">Asignaciones</a></li>
                                <li><a href="#">Secciones</a></li>
                                <li><a href="#">Productos en sección</a></li>
                                <li><a href="#">Ordenes de sección</a>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-lg fa-fw fa-shopping-cart"></i> <span class="menu-item-parent">Tienda Online</span></a>
                            <ul>
                                <li><a href="#">Asignaciones</a></li>
                                <li><a href="#">Secciones</a></li>
                                <li><a href="#">Productos en sección</a></li>
                                <li><a href="#">Ordenes de sección</a>
                            </ul>
                        </li>

                        <li>
                            <a href="#"><i class="fa fa-lg fa-fw fa-gears"></i> <span class="menu-item-parent">Producción</span></a>
                            <ul>
                                <li><a href="#">Asignaciones</a></li>
                                <li><a href="#">Proceso General</a></li>
                                <li><a href="#">Plantas</a></li>
                                <li><a href="#">Procesos en Plantas</a></li>
                                <li><a href="#">Productos en planta</a></li>
                                <li><a href="#">Ordenes de producción</a>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-lg fa-fw fa-group"></i> <span class="menu-item-parent">CRM</span></a>
                    <ul>
                        <li <c:if test="${clienteActive != null}">class="active"</c:if>><a href="ClienteMainServlet">Clientes</a></li>                                   
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-lg fa-fw fa-group"></i> <span class="menu-item-parent">SRM</span></a>
                    <ul>
                        <li <c:if test="${proveedorActive != null}">class="active"</c:if>><a href="ProveedorMainServlet">Proveedores</a></li>                                   
                </ul>
            </li>

            <li><a href="ParameterGetServlet"><i class="fa fa-lg fa-fw fa-gear"></i> <span class="menu-item-parent">Configuración (*)</span></a></li>
        </ul>
    </nav>
    <span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>

</aside>
