<%-- 
    Document   : tableview
    Created on : 30 Aug, 2019, 12:01:20 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Table V04</title>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->


        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.js">

            function fillColumns(id) {
                var noOfRowsTraversed = document.getElementById("noOfRowsTraversed").value;
                var noOfColumns = ${Table_List_length};
                var columnId = id;
            <%-- holds the id of the column being clicked, excluding the prefix t1c e.g. t1c3 (column 3 of table 1). --%>
                columnId = columnId.substring(3, id.length);
            <%-- for e.g. suppose id is t1c3 we want characters after t1c i.e beginIndex = 3. --%>
                var lowerLimit, higherLimit;
                for (var i = 0; i < noOfRowsTraversed; i++) {
                    lowerLimit = i * noOfColumns + 1;       // e.g. 11 = (1 * 10 + 1)
                    higherLimit = (i + 1) * noOfColumns;    // e.g. 20 = ((1 + 1) * 10)
                    if ((columnId >= lowerLimit) && (columnId <= higherLimit))
                        break;
                }
                setDefaultColor(noOfRowsTraversed, noOfColumns);        // set default color of rows (i.e. of multiple coloumns).
                var t1id = "t1c";       // particular column id of table 1 e.g. t1c3.
                for (var i = 0; i < noOfColumns; i++) {
                    // set the background color of clicked/selected row to yellow.
                    document.getElementById(t1id + (lowerLimit + i)).bgColor = "lightgray";
                }
                // Now get clicked row data, and set these into the below edit table.
//                document.getElementById("camera_type_id").value = document.getElementById(t1id + (lowerLimit + 0)).innerHTML;
//                document.getElementById("camera_type").value = document.getElementById(t1id + (lowerLimit + 2)).innerHTML;
//                document.getElementById("remark").value = document.getElementById(t1id + (lowerLimit + 3)).innerHTML;
//                 Now enable/disable various buttons.
                document.getElementById("edit").disabled = false;
                if (!document.getElementById("save").disabled) {
                    // if save button is already enabled, then make edit, and delete button enabled too.
                    document.getElementById("delete").disabled = false;
                }
                document.getElementById("message").innerHTML = "";      // Remove message
            }

        </script>
        <style>
            .select-css {
                display: block;
                font-size: 16px;
                font-family: sans-serif;
                font-weight: 700;
                color: #444;
                line-height: 1.3;
                padding: .6em 1.4em .5em .8em;
                width: 1100px;

                box-sizing: border-box;

                border: 1px solid #aaa;
                box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
                border-radius: .5em;
                -moz-appearance: none;
                -webkit-appearance: none;
                appearance: none;
                background-color: #fff;
                background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
                    linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
                background-repeat: no-repeat, repeat;
                background-position: right .7em top 50%, 0 0;
                background-size: .65em auto, 100%;
            }
            .select-css::-ms-expand {
                display: none;
            }
            .select-css:hover {
                border-color: #888;
            }
            .select-css:focus {
                border-color: #aaa;
                box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
                box-shadow: 0 0 0 3px -moz-mac-focusring;
                color: #222; 
                outline: none;
            }
            .select-css option {
                font-weight:normal;
            }



            /*            body {
                            padding: 3rem;
                        }*/
            .button {
                display: inline-block;
                border-radius: 5px;
                background-color:#1E90FF;
                border:1;
                color: #FFFFFF;

                text-align: center;
                font-size: 18px;
                padding:8px;
                width: 180px;
                transition: all 0.5s;
                cursor: pointer;

            }

            .button  {
                cursor: pointer;
                display: inline-block;
                position: relative;
                transition: 0.5s;
            }

            .button :after {
                content: '\00bb';
                position: absolute;
                opacity: 0;
                top: 0;
                right: -20px;
                transition: 0.5s;
            }

            .button:hover{
                padding-right: 25px;
            }

            .button:hover:after {
                opacity: 1;
                right: 0;
            }

            h5{
                margin-bottom: 5%;
                color: #6c7ae0;


            }
            h1{
                margin-bottom: 12px;
                margin-top: 6px;
                color: #6c7ae0;
                font-style: oblique;
                align-content: center;

            }
            h2{
                margin-top: 14%;
                color: #6c7ae0;
                font-style: oblique;

            }

            .text-center1{

                margin-left: 44%;
                align-content: center;
                padding: 2%;

            }   
            #example3 {
                margin-left: 15%;
                width: 70%;
                position: absolute;
                margin-top: 15px;
                border-radius: 25px;
                /*                border: 1px solid;*/

                /*                box-shadow: 5px 10px 18px #007bff;*/
            }
            .center2 {

                margin-top: 5px;

            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #6c7ae0;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover:not(.active) {
                background-color: whitesmoke;
            }

            .active {
                background-color: #4CAF50;
            }    
            .table1{
                width:80%;
                margin-left: 9%;
            }

        </style>
        <script>
            $('#AddPoints').live('click', function (event) {
                $('#hideShowDiv').toggle('hide');
            });

            var popupwin = null;
            function displayImageList(id) {
                alert(id);

//          var image = document.getElementById('imageid'+id).value;

//        var image_id=document.getElementById('image_id'+id).value;
//         var task1 = document.getElementById('task1'+id).value;
//        var queryString = "task=View_Image&image_name="+image+"&general_image_details_id="+image_id+"&task1="+task1 ;
                var queryString = "task=View_Image";
                var url = "TableListCont?" + queryString;
                popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);

            }
            function displayAudioList(id) {
                alert(id);

//          var image = document.getElementById('imageid'+id).value;

//        var image_id=document.getElementById('image_id'+id).value;
//         var task1 = document.getElementById('task1'+id).value;
//        var queryString = "task=View_Image&image_name="+image+"&general_image_details_id="+image_id+"&task1="+task1 ;
                var queryString = "task=Liesten_Audio";
                var url = "TableListCont?" + queryString;
                popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);

            }
            function test1(id) {
                var table_name = document.getElementById('table_name').value;
                var lowerLimit = document.getElementById('lowerLimit').value;
                var noOfRowsTraversed = document.getElementById('noOfRowsTraversed').value;


                var queryString = "task=Liesten_Audio&audioid=" + id + "&table=" + table_name + "&lowerLimit=" + lowerLimit + "&noOfRowsTraversed=" + noOfRowsTraversed;

                var url = "TableListCont?" + queryString;

                popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);
            }

            function test(id) {
                var table_name = document.getElementById('table_name').value;
                var lowerLimit = document.getElementById('lowerLimit').value;
                var noOfRowsTraversed = document.getElementById('noOfRowsTraversed').value;
//       
                var queryString = "task=View_Image&imageid=" + id + "&table=" + table_name + "&lowerLimit=" + lowerLimit + "&noOfRowsTraversed=" + noOfRowsTraversed;

                var url = "TableListCont?" + queryString;
                //  var url = "TableListCont?task=View_Image";                     
                popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);
            }
            function pdfTest(id) {
                var table_name = document.getElementById('table_name').value;
                var lowerLimit = document.getElementById('lowerLimit').value;
                var noOfRowsTraversed = document.getElementById('noOfRowsTraversed').value;
//       
                var queryString = "task=generate_pdf&pdfid=" + id + "&table=" + table_name + "&lowerLimit=" + lowerLimit + "&noOfRowsTraversed=" + noOfRowsTraversed;

                var url = "TableListCont?" + queryString;
                alert(url);
                //  var url = "TableListCont?task=View_Image";                     
                popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);
            }
            function openMapForCord(id) {
                var table_name = document.getElementById('table_name').value;
                var lowerLimit = document.getElementById('lowerLimit').value;
                var noOfRowsTraversed = document.getElementById('noOfRowsTraversed').value;
                var url = "TableListCont?task=GetCordinates1&mapid=" + id + "&table=" + table_name + "&lowerLimit=" + lowerLimit + "&noOfRowsTraversed=" + noOfRowsTraversed;//"getCordinate";
                alert(url);
                popupwin = openPopUp(url, "", 600, 1000);

            }

            function openPopUp(url, window_name, popup_height, popup_width) {
                var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
                var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
                var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=yes, scrollbars=yes, location=0, menubar=no, status=no, dependent=yes";

                return window.open(url, window_name, window_features);
            }


        </script>

    </head>



    <body>
        <ul>
            <li><a class="active" href="TableViewCont">Home</a></li>
            <li><a href="generic.jsp">CREATE TABLE</a></li>
            <!--            <li><a href="#contact">EDIT TABLE</a></li>-->
            <li><a href="TableListCont">SHOW TABLE</a></li>
            <li style="float:right"><a class="active" href="login.jsp">LOGOUT</a></li>
        </ul>





        <div id="example3"  align="center" class="table1">
            <h1 class="text-center">SHOW TABLE</h1>
            <form action="TableListCont">

                <table align="center">
                    <tr>
                        <td>
                            <select class="select-css" name="Points" size="1" id="Points">
                                <c:forEach var="table_list" items="${requestScope['table_List']}" varStatus="loopCounter">
                                    <option id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" value="${table_list.tablename}">${table_list.tablename}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input type="submit" name="AddPoints" id="AddPoints" value="Show Table" class="button" ></td>
                    </tr>
                </table>
            </form>
        </div>





        <div class="table1">
            <div class="h2"><h2 class="text-center">${table_name}</h2></div>
            <div class="table100 ver1 m-b-110">
                <form  method ="post" action="TableListCont">
                    <div>
                        <table border="1">

                            <thead>
                                <tr class="row100 head">

                                    <th style="display: none"> Table ID </th>

                                    <c:forEach var="table_column_list" items="${requestScope['table_column_List']}" varStatus="loopCounter">

                                        <th class="cell100 column2   text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" value="">${table_column_list}</th>


                                    </c:forEach>
                                    <th class="cell100 column2   text-center" value="">Pdf</th>
                                      <th class="cell100 column2   text-center" value="">Map</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="table_values" items="${requestScope['table_values_List']}" varStatus="loopCounter">

                                    <tr class="row1001 body" onMouseOver=this.style.backgroundColor ='#E3ECF3' onmouseout=this.style.backgroundColor = 'white'>
                                        <td  class="cell100 column2 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>

                                        <c:forEach begin="0" end="${Table_List_length}" var="val">
<!--                                                <td  class="cell100 column2 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" value="">${table_values[val]}</td>-->

                                            <c:choose>


                                                <c:when test="${table_values[val] eq 'test'}"> 
                                                    <td><center><input class="btn btn-primary text-center" type='submit' name='task' value='View_Image' onclick="test(${table_values[0]})"></center></td>
                                                </c:when>

                                        <c:when test="${table_values[val] eq 'test1'}"> 
                                            <td><center><input class="btn btn-primary text-center" type='submit' name='task' value='Liesten_Audio' onclick="test1(${table_values[0]})"></center></td>


                                        </c:when>
                                        <c:when test="${table_values[val] eq table_values[0]}"> 
                                            <input class="btn btn-primary text-center" type='hidden' name='tableid' value='tableid'>

                                        </c:when>
                                        <c:otherwise>

                                            <td  class="cell100 column2 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" value="">${table_values[val]}</td>

                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>   
                                <td><center><input class="btn btn-primary text-center" type='submit' name='task'  value='generate_pdf' onclick="pdfTest(${table_values[0]})"></center></td>
                                <td><center><input class="btn btn-primary text-center" type='submit' name='task' value ='View_Map' onclick="openMapForCord(${table_values[0]})"></center></td>
                                </tr>
                            </c:forEach>

                            <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1.   <c:when test="${table_values[0] eq 'id'}"> 
                                           <td><center><input type='hidden'  name="" value="${table_values[val]}"></center></td>
                                       </c:when> --%>
                            <input type="hidden" id="lowerLimit" name="lowerLimit" value="${lowerLimit}">
                            <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">


                            </tbody>
                        </table>
                    </div>
                    <br>
                    <div><table style="margin-left:2%"> <tr>

                                <td class="cell100 column5">
                                    <c:choose>
                                        <c:when test="${showFirst eq 'false'}">
                                            <input class="btn btn-primary text-center  " type='submit' name='buttonAction' value='First' disabled>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='First'>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${showPrevious == 'false'}">
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='Previous'>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='Previous'>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${showNext eq 'false'}">
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='Next'>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='Next'>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${showLast == 'false'}">
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='Last'>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="btn btn-primary text-center" type='submit' name='buttonAction' value='Last'>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                            </tr></table>
                    </div>


                    <input type="hidden" id="table_name" name="table_name" value="${table_name}">
                </form>
            </div>

        </div>


    </body>
</html>