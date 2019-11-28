
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>

<html>
    <head>
        <title>Menu</title>
        <!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>-->




        <script type="text/javascript">
            $(function () {
                if ($.browser.msie && $.browser.version.substr(0, 1) < 7)
                {
                    $('li').has('ul').mouseover(function () {
                        $(this).children('ul').show();
                    }).mouseout(function () {
                        $(this).children('ul').hide();
                    })
                }
            });

            $(document).ready(function () {
                var mouseX;
                var mouseY;
                $(document).mousemove(function (e) {
                    mouseX = e.pageX;
                    mouseY = e.pageY;
                });

                $("#close_div").click(function () {
                    //this.title = "fffff</div>";
                    $("#popup").hide();
                    $('#ride_no_from').val("");
                    $('#ride_no_to').val("");

                });

                $("#logout").click(function () {
                    $('#popup').css({
                        'position': 'absolute',
                        'left': mouseX,
                        'top': mouseY
                    });
                    $("#make_work_order_btn").val("quickWorkOrder");
                    $("#popup").show();

                });

            });

        </script>

        <!--        <link type="text/css" href="style/menu.css" rel="stylesheet"/>-->

        <style>

            .navbar-inverse a{
                /*                        background-color: #3292a8;*/
                color: white;
            }
        </style>

    </head>
    <body>

    <nav class="navbar navbar-expand-sm navbar-expand" style="background-color: #99C1E8;font-size: 20px;color: #000;font-weight: bold;padding:10px"  >
        <div class="container-fluid">

            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">


                    <li class="nav-item dropdown"  style="color: #FFF">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" style="color: #FFF">
                            DEVICE
                        </a>
                        <div class="dropdown-menu">

                            <a class="dropdown-item" href="ManufacturerCont.do">Manufacturer</a>
                            <a class="dropdown-item" href="DeviceTypeCont.do">Device Type</a>
                            <a class="dropdown-item" href="ModelTypeCont.do">Model Type</a>
                            <a class="dropdown-item" href="ModelCont.do">Model</a>
                            <a class="dropdown-item" href="ServiceCont.do">Services</a>
                            <a class="dropdown-item" href="CharachtristicsCont.do">Characteristic</a>
                            <a class="dropdown-item" href="DeviceCont.do">Device</a>
                            <a class="dropdown-item" href="DeviceRegistrationCont.do">Device Registration</a>
                            <a class="dropdown-item" href="DeviceMapCont.do">Device Map</a>
                        </div></li>
                    <li>
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" style="color: #FFF">
                            MODULE
                        </a>
                        <div class="dropdown-menu">

                            <a class="dropdown-item" href="CommandTypeCont.do">Command Type</a>
                            <a class="dropdown-item" href="CommandCont.do">Command</a>
                            <a class="dropdown-item" href="OperationNameCont.do">Operation Name</a>
                            <a class="dropdown-item" href="ruleCont.do">Rules</a>
                            <a class="dropdown-item" href="SelectionCont.do">Selection</a>
                            <a class="dropdown-item" href="InputCont.do">Input</a>
                            <a class="dropdown-item" href="ParameterCont.do">Parameter</a>
                            <a class="dropdown-item" href="DeviceOperationCommandCont.do">Operation Command Map</a>
                        </div>
                    </li>
                    <li>
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" style="color: #FFF">
                            BLE
                        </a>
                        <div class="dropdown-menu">

                            <a class="dropdown-item" href="BleOperationNameCont.do">BLE Operation Name</a>
                            <a class="dropdown-item" href="ServiceCont.do">Services</a>
                            <a class="dropdown-item" href="CharachtristicsCont.do">Characteristics</a>
                            <a class="dropdown-item" href="DeviceOprtnChartstcMapCont.do">Device Character Operation map</a>
                        </div>
                    </li>



                    <!--                    <li class="nav-item"><a href="LoginCont.do?task=logout" class="nav-link" style="color: #FFF">Logout</a></li>-->


                </ul>

            </div>

        </div>
    </nav>



</body>
</html>