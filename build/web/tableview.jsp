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
        <script type="text/javascript" language="javascript">
            function fillColumns(id) {
                var noOfRowsTraversed = document.getElementById("noOfRowsTraversed").value;
                var noOfColumns = 4;
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

            h5{
                margin-bottom: 5%;
                color: #6c7ae0;


            }
            h1{

                color: #6c7ae0;
                font-style: oblique;


            }

            .table1{
                width:90%;
                margin-left: 5%;
            }
            .text-center1{

                margin-left: 34%;
                align-content: center;
                padding: 2%;

            }
            .p{
                margin-left: 110px;
            }
            <%-- nav bar--%>
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
            .center2 {
                margin-top: 4%;
                text-align: center;
                margin-bottom: 4%;
            }

        </style>

    </head>


    <body>
        <ul>
            <li><a class="active" href="TableViewCont">Home</a></li>
            <li><a href="generic.jsp">CREATE TABLE</a></li>
            <!--  <li><a href="#contact">EDIT TABLE</a></li>-->
            <li><a href="TableListCont">SHOW TABLE</a></li>
            <li style="float:right"><a class="active" href="login.jsp">LOGOUT</a></li>
        </ul>



        <div class="table1">
            <h1 class="center2">TABLE RECORDS</h1>
            <div class="wrap-table100">

                <div class="table100 ver1 m-b-110">
                    <form name="form" method="POST" action="TableViewCont">
                        <div class="table100-head">

                            <table>
                                <thead>
                                    <tr class="row100 head">
                                        <th style="display: none"> Table ID </th>
                                        <th class="cell100 column1 text-center">S.No.</th>
                                        <th class="cell100 column2  text-center">Table Name</th>
                                        <th class="cell100 column3 text-center">Table Column</th>
                                        <th class="cell100 column1 text-center">Sql Query</th>
                                        <th class="cell100 column2 text-center">peripheral_list</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>

                        <div class="table100-body js-pscroll">

                            <table>
                                <tbody>



                                    <c:forEach var="table_record" items="${requestScope['table_recordList']}" varStatus="loopCounter">
                                        <tr class="row100 body" onMouseOver=this.style.backgroundColor = '#E3ECF3' onmouseout=this.style.backgroundColor = 'white'>

<!--                                                                    <td  class="cell100 column1 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>-->
                                            <td class="cell100 column1 text-center" id="t1c${IDGenerator.uniqueID}" class="cell100 column1 text-center" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                            <td  class="cell100 column2 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${table_record.tablename}</td>
                                            <td  class="cell100 column3 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${table_record.column_list}</td>
                                            <td  class="cell100 column1 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${table_record.sql_query}</td>
                                            <td  class="cell100 column2 text-center" id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${table_record.peripheral_list}</td>
                                            <td id="t1c${IDGenerator.uniqueID}" style="display: none" onclick="fillColumns(id)">${table_record.table_record_id}</td>   
                                        </tr>


                                    </c:forEach>



                                    <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                                <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">

                                </tbody>         
                            </table>
                        </DIV><br>
                        <div><table style="margin-left: 10%"> <tr>

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
                    </form>
                </div>
                <table style="margin-left: 100px">
                    <tr><td><input type="image" class="text-center1" name="imgbtn" src="images/icons/images.png"onclick="window.location.href = 'generic.jsp';"></td>

                        <!--                                                      <td> <input type="image" class="text-center1" name="imgbtn" src="images/icons/show.png"onclick="window.location.href='ShowDataCont';"></td>-->
                        <td><input type="image" class="text-center1" name="imgbtn" src="images/icons/export-3.png"onclick="window.location.href = 'TableListCont';"></td></tr><tr><td><h5>Create Table</h5></td>
                        <td><h5>Show Table</h5></td></tr></table>
            </div>
        </div>



    </body>
</html>
