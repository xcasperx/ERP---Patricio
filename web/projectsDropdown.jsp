<%-- 
    Document   : mainMenu
    Created on : 16-ene-2014, 16:49:05
    Author     : patricio alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="project-context">

    <span class="label">Actualizaciones:</span>
    <span id="project-selector" class="popover-trigger-element dropdown-toggle" data-toggle="dropdown">Actualizaciones Recientes <i class="fa fa-angle-down"></i></span>

    <!-- Suggestion: populate this list with fetch and push technique -->
    <ul class="dropdown-menu">
        <li>
            <a href="javascript:void(0);">Módulo C3 Sistema de administración de e-merchant Online</a>
        </li>
        <li>
            <a href="javascript:void(0);">Módulo D2 - Sistema de Inventario auto proyectado</a>
        </li>
        <li>
            <a href="javascript:void(0);">Módulo R1- para Planificación de Procesos Productivos </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="javascript:void(0);"><i class="fa fa-power-off"></i> Limpiar</a>
        </li>
    </ul>
    <!-- end dropdown-menu-->

</div>