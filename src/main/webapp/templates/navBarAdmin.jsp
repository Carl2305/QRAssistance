<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<body class="nav-fixed bg-light">
    <!-- Top app bar navigation menu-->
    <nav class="top-app-bar navbar navbar-expand navbar-dark bg-dark">
        <div class="container-fluid px-4">
            <!-- Drawer toggle button-->
            <button class="btn btn-lg btn-icon order-1 order-lg-0 mdc-ripple-upgraded" id="drawerToggle" href="javascript:void(0);" style="--mdc-ripple-fg-size:28px; --mdc-ripple-fg-scale:2.78151; --mdc-ripple-fg-translate-start:12.2px, 13.8px; --mdc-ripple-fg-translate-end:10px, 10px;"><i class="material-icons">menu</i></button>
            <!-- Navbar brand-->
            <a class="navbar-brand me-auto" href="Home"><div class="text-uppercase font-monospace">QRAssistance</div></a>
            <!-- Navbar items-->
            <div class="d-flex align-items-center mx-3 me-lg-0">
                <!-- Navbar-->
                <ul class="navbar-nav d-none d-lg-flex">
                    <li class="nav-item"><a class="nav-link active" href="Home">Inicio</a></li>
                </ul>
                <!-- Navbar buttons-->
                <div class="d-flex">                    
                    <!-- User profile dropdown-->
                    <div class="dropdown">
                        <button class="btn btn-lg btn-icon dropdown-toggle mdc-ripple-upgraded" id="dropdownMenuProfile" type="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="material-icons">person</i></button>
                        <ul class="dropdown-menu dropdown-menu-end mt-3" aria-labelledby="dropdownMenuProfile">
                            <li>
                                <a class="dropdown-item mdc-ripple-upgraded" href="#">
                                    <i class="material-icons leading-icon">person</i>
                                    <div class="me-3">Mi Perfil</div>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item mdc-ripple-upgraded" href="Logout">
                                    <i class="material-icons leading-icon">logout</i>
                                    <div class="me-3">Salir</div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <!-- Layout wrapper-->
    <div id="layoutDrawer">
        <!-- Layout navigation-->
        <div id="layoutDrawer_nav">
            <!-- Drawer navigation-->
            <nav class="drawer accordion drawer-light bg-white" id="drawerAccordion">
                <div class="drawer-menu">
                    <div class="nav">
                        <!-- Drawer link (Overview)-->
                        <a class="nav-link active mdc-ripple-upgraded" href="Home">
                            <div class="nav-link-icon"><i class="material-icons">home</i></div>
                            Inicio
                        </a>
                        <!-- Drawer section heading (Interface)-->
                        <div class="drawer-menu-heading pt-2">Mantenimiento</div>
                        <!-- Drawer link (Empleados)-->
                        <a class="nav-link collapsed mdc-ripple-upgraded" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseDashboards" aria-expanded="false" aria-controls="collapseDashboards">
                            <div class="nav-link-icon"><i class="material-icons">group</i></div>
                            Empleados
                            <div class="drawer-collapse-arrow"><i class="material-icons">expand_more</i></div>
                        </a>
                        <!-- Nested drawer nav (Empleados)-->
                        <div class="collapse" id="collapseDashboards" aria-labelledby="headingOne" data-bs-parent="#drawerAccordion">
                            <nav class="drawer-menu-nested nav">
                                <a class="nav-link mdc-ripple-upgraded" href="EmployeeRegister">Registrar Empleados</a>
                                <a class="nav-link mdc-ripple-upgraded" href="EmployeeUpdate">Actualizar Empleados</a>
                                <a class="nav-link mdc-ripple-upgraded" href="EmployeeListAll">Listado de Empleados</a>
                            </nav>
                        </div>
                        <!-- Drawer link (Layouts)-->
                        <a class="nav-link collapsed mdc-ripple-upgraded" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="nav-link-icon"><i class="material-icons">done_all</i></div>
                            Asistencias
                            <div class="drawer-collapse-arrow"><i class="material-icons">expand_more</i></div>
                        </a>
                        <!-- Nested drawer nav (Layouts)-->
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#drawerAccordion">
                            <nav class="drawer-menu-nested nav">
                                <a class="nav-link mdc-ripple-upgraded" href="#">Modificar Asistencias</a>
                            </nav>
                        </div>
                        <!-- Drawer link (Pages)-->
                        <a class="nav-link collapsed mdc-ripple-upgraded" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                            <div class="nav-link-icon"><i class="material-icons">fact_check</i></div>
                            Licencias
                            <div class="drawer-collapse-arrow"><i class="material-icons">expand_more</i></div>
                        </a>
                        <!-- Nested drawer nav (Pages)-->
                        <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#drawerAccordion">
                            <nav class="drawer-menu-nested nav">
                                <a class="nav-link mdc-ripple-upgraded" href="#">Aceptación de Licencias</a>
                            </nav>
                        </div>
                        <!-- Divider-->
                        <div class="drawer-menu-divider mt-2 mb-2"></div>
                        <!-- Drawer section heading (Plugins)-->
                        <div class="drawer-menu-heading pt-1">Asistencia</div>
                        <!-- Drawer link (Charts)-->
                        <a class="nav-link mdc-ripple-upgraded" href="AssistanceRegister">
                            <div class="nav-link-icon"><i class="material-icons">how_to_reg</i></div>
                            Marcación Entrada
                        </a>
                        <!-- Drawer link (Code Blocks)-->
                        <a class="nav-link mdc-ripple-upgraded" href="AssistanceCheckout">
                            <div class="nav-link-icon"><i class="material-icons">exit_to_app</i></div>
                            Marcación Salida
                        </a>
                        <!-- Drawer link (Data Tables)-->
                        <a class="nav-link mdc-ripple-upgraded" href="AssistanceHistory">
                            <div class="nav-link-icon"><i class="material-icons">history_toggle_off</i></div>
                            Historial Asistencia
                        </a>
                        <!-- Divider-->
                        <div class="drawer-menu-divider mt-2 mb-2"></div>
                        <!-- Drawer section heading (Plugins)-->
                        <div class="drawer-menu-heading pt-1">Licencia</div>
                        <!-- Drawer link (Charts)-->
                        <a class="nav-link mdc-ripple-upgraded" href="#">
                            <div class="nav-link-icon"><i class="material-icons">sticky_note_2</i></div>
                            Registrar Licencia
                        </a>
                        <!-- Divider-->
                        <div class="drawer-menu-divider mt-2 mb-2"></div>
                        <!-- Drawer section heading (Plugins)-->
                        <div class="drawer-menu-heading pt-1">Reportes</div>
                        <!-- Drawer link (Charts)-->
                        <a class="nav-link mdc-ripple-upgraded" href="#">
                            <div class="nav-link-icon"><i class="material-icons">pending_actions</i></div>
                            Reporte Empleados Contratados
                        </a>
                        <a class="nav-link mdc-ripple-upgraded" href="TotalEmployee">
                            <div class="nav-link-icon"><i class="material-icons">pending_actions</i></div>
                            Reporte Asistencias de Empledao
                        </a>
                    </div>
                </div>
                <!-- Drawer footer        -->
                <div class="drawer-footer border-top">
                    <div class="d-flex align-items-center">
                        <i class="material-icons text-muted">account_circle</i>
                        <div class="ms-3">
                            <div class="caption">Usuario: <span><%= misession.getAttribute("Nombre") %></div>
                            <div class="small fw-500">Administrador</div>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Layout content-->
        <div id="layoutDrawer_content">
            <!-- Main page content-->
            <main>
                <div class="container-xl p-5">