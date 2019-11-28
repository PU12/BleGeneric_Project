<%--
    Document   : manufacturer
    Created on : Jan 7, 2019, 12:52:04 PM
    Author     : Shobha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Command Page</title>
        <meta charset="utf-8">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="time/bootstrap-datetimepicker.min.css">
<script  type="text/javascript" src="time/moment.min.js"></script>
<script  type="text/javascript" src="time/bootstrap-datetimepicker.min.js"></script>
    </head>
    <script type="text/javascript" language="javascript">
  
           $(function () {
            $("#model_type").autocomplete({
                source: function (request, response) {
                    debugger
                    $.ajax({
                        url: "ModelCont.do",
                        type: "GET",
                        dataType: "json",
                        data: {action1: "getModelType"},
                        success: function (data) {

                            console.log(data);
                            response(data.list);
                        }, error: function (error) {
                            console.log(error.responseText);
                            response(error.responseText);
                        }
                    });
                },
                select: function (events, ui) {
                    console.log(ui);
                    $('#model_type').val(ui.item.label); // display the selected text
                    return false;
                }
            });
        });
      

   
          $(function () {
            $("#searchDeviceName").autocomplete({
                source: function (request, response) {
                    debugger
                    $.ajax({
                        url: "ModelCont.do",
                        type: "GET",
                        dataType: "json",
                        data: {action1: "getDeviceName"},
                        success: function (data) {

                            console.log(data);
                            response(data.list);
                        }, error: function (error) {
                            console.log(error.responseText);
                            response(error.responseText);
                        }
                    });
                },
                select: function (events, ui) {
                    console.log(ui);
                    $('#searchDeviceName').val(ui.item.label); // display the selected text
                    return false;
                }
            });
        });

    function setDefaultColor(noOfRowsTraversed, noOfColumns) {
        for(var i = 0; i < noOfRowsTraversed; i++) {
            for(var j = 1; j <= noOfColumns; j++) {
                document.getElementById("t1c" + (i * noOfColumns + j)).bgColor = "";     // set the default color.
            }
        }
    }
    function makeEditable(id) {

        document.getElementById("model_type").disabled = false;
        document.getElementById("device_name").disabled = false;
        document.getElementById("device_no").disabled = false;
        document.getElementById("model_id").disabled = false;
        document.getElementById("warranty_period").disabled = false;
        document.getElementById("device_address").disabled = false;
         document.getElementById("remark").disabled = false;

        document.getElementById("save").disabled = false;
//        document.getElementById("revise").disabled =false;
        document.getElementById("cancel").disabled =false;
        document.getElementById("save_As").disabled =false;
        //document.getElementById("save").disabled = true;
        if(id === 'new') {
        //    document.getElementById("created_date").disabled = true;
           // document.getElementById("active").value ='';

            document.getElementById("message").innerHTML = "";      // Remove message
            document.getElementById("model_type").focus();
            $("#message").html("");

            //document.getElementById("revise").disabled = true;
            document.getElementById("cancel").disabled = true;
            document.getElementById("save_As").disabled = true;
            document.getElementById("save").disabled = false;

            setDefaultColor(document.getElementById("noOfRowsTraversed").value, 3);


        }
        if(id === 'edit'){

            document.getElementById("save_As").disabled = false;
            document.getElementById("cancel").disabled = false;
        }
    }
    function setStatus(id) {
        if(id == 'save'){
            document.getElementById("clickedButton").value = "Save";
        }
        else if(id == 'save_As'){
            document.getElementById("clickedButton").value = "Save AS New";
        }
        else if(id == 'revise'){
            document.getElementById("clickedButton").value = "Revise";
        }
        else document.getElementById("clickedButton").value = "Delete";
    }
    function verify() {
        var result;
        if(document.getElementById("clickedButton").value == 'Save' || document.getElementById("clickedButton").value == 'Save AS New' || document.getElementById("clickedButton").value == 'Revise'||document.getElementById("clickedButton").value == 'Delete') {
            var division_name_m = document.getElementById("division_name_m").value;
            var a=document.getElementById("active").value;
            //    alert(a);
            if(myLeftTrim(division_name_m).length == 0) {

                // document.getElementById("message").innerHTML = "<td colspan='5' bgcolor='coral'><b>Organisation Type Name is required...</b></td>";
                $("#message").html("<td colspan='5' bgcolor='coral'><b>Ward No is required...</b></td>");
                document.getElementById("division_name_m").focus();
                return false; // code to stop from submitting the form2.
            }

            if(document.getElementById("active").value =='Revised' || document.getElementById("active").value =='Cancelled')
            {
                $("#message").html("<td colspan='5' bgcolor='coral'><b>You can not perform any operation on revised and cancelled record...</b></td>");
                // document.getElementById("source_wattage"+i).focus();
                return false; // code to stop from submitting the form2.
            }
            if(result == false)    // if result has value false do nothing, so result will remain contain value false.
            {

            }
            else{ result = true;
            }

            if(document.getElementById("clickedButton").value == 'Save AS New'){
                result = confirm("Are you sure you want to save it as New record?")
                return result;
            }
        } else result = confirm("Are you sure you want to cancel this record?")
        return result;
    }
    function fillColumns(id) {
        var noOfRowsTraversed = document.getElementById("noOfRowsTraversed").value;
        var noOfColumns =8;
        var columnId = id;                              <%-- holds the id of the column being clicked, excluding the prefix t1c e.g. t1c3 (column 3 of table 1). --%>
        columnId = columnId.substring(3, id.length);    <%-- for e.g. suppose id is t1c3 we want characters after t1c i.e beginIndex = 3. --%>
        var lowerLimit, higherLimit;

        for(var i = 0; i < noOfRowsTraversed; i++) {
            lowerLimit = i * noOfColumns + 1;       // e.g. 11 = (1 * 10 + 1)
            higherLimit = (i + 1) * noOfColumns;    // e.g. 20 = ((1 + 1) * 10)

            if((columnId >= lowerLimit) && (columnId <= higherLimit)) break;
        }

        setDefaultColor(noOfRowsTraversed, noOfColumns);        // set default color of rows (i.e. of multiple coloumns).
        var t1id = "t1c";       // particular column id of table 1 e.g. t1c3.

        document.getElementById("model_id").value= document.getElementById(t1id + (lowerLimit + 0)).innerHTML;
        document.getElementById("model_type").value = document.getElementById(t1id +(lowerLimit+2)).innerHTML;
        document.getElementById("device_name").value = document.getElementById(t1id +(lowerLimit+3)).innerHTML;
        document.getElementById("device_no").value = document.getElementById(t1id +(lowerLimit+4)).innerHTML;
        document.getElementById("warranty_period").value = document.getElementById(t1id +(lowerLimit+5)).innerHTML;
        document.getElementById("device_address").value = document.getElementById(t1id +(lowerLimit+6)).innerHTML;
        document.getElementById("remark").value = document.getElementById(t1id +(lowerLimit+7)).innerHTML;

        //       var b=  document.getElementById(t1id +(lowerLimit+8)).innerHTML;
        // alert(b);
        for(var i = 0; i < noOfColumns; i++) {
            document.getElementById(t1id + (lowerLimit + i)).bgColor = "#d0dafd";        // set the background color of clicked row to yellow.
        }
     //   makeEditable('');

        document.getElementById("edit").disabled = false;
        if(!document.getElementById("save").disabled)   // if save button is already enabled, then make edit, and cancel button enabled too.
        {
            document.getElementById("save_As").disabled = true;
            document.getElementById("cancel").disabled = false;
        }
        //  document.getElementById("message").innerHTML = "";      // Remove message
        $("#message").html("");
    }
    function myLeftTrim(str) {
        var beginIndex = 0;
        for(var i = 0; i < str.length; i++) {
            if(str.charAt(i) == ' ')
                beginIndex++;
            else break;
        }
        return str.substring(beginIndex, str.length);
    }
    var popupwin = null;
    function displayMapList(){
        var queryString = "task=generateMapReport" ;
        var url = "divisionCont?"+queryString;
        popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);

    }

    function openPopUp(url, window_name, popup_height, popup_width) {
        var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
        var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
        var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=yes, scrollbars=yes, location=0, menubar=no, status=no, dependent=yes";

        return window.open(url, window_name, window_features);
    }
    
    function goTo(type) {
        if(type === "model_type") {
            window.location.href = "ModelTypeCont.do";
        }
    }
    
    function goToDeviceType() {
        window.location.href="DeviceTypeCont.do";
    }

</script>


    <body>
<%@include file="/layout/menu1.jsp" %>
<div class="container" style="margin-top: 5%">
    <table class="table table-hover"  style="background-color:#d0dafd ">


                <tr>
                    <td align="center" class="header_table" width="100%">Model</td>

                </tr>
            </table>
        </div> <div class="container">
                                    <form name="form0" method="POST" action="ModelCont.do">
                                         <table class="table table-hover" border="1" style="border-color:#d0dafd">
                                            <tr>
                                                <td>Device Name<input class="form-control" type="text" id="searchDeviceName" name="searchDeviceName" value="${searchDeviceName}" size="20" ></td>
                                                <td><input class="btn btn-primary" type="submit" name="task" id="searchIn" value="Search"></td>
                                                <td><input class="btn btn-success" type="submit" name="task" id="showAllRecords" value="Show All Records"></td>
                                                <td><input class="btn btn-danger" type="button" name="task" id="showAllRecords" value="Next Page" onclick="goToDeviceType()"></td>
                                                <td><input type="button" class="btn btn-info" id="viewPdf" name="viewPdf" value="Pdf" onclick="displayMapList()"></td>
                                                  </tr>
                </table>
            </form></div>
                                <form name="form1" method="POST" action="ModelCont.do">
                                <div class="container">
                <table class="table table-hover" border="1" style="border-color:#d0dafd">
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Model Type</th>
                                                <th>Device Name</th>
                                                <th>Device No.</th>
                                                <th>Warranty Period</th>
                                                <th>Device Address</th>

                                                <th>Remark</th>
                                            </tr>
                                            <!---below is the code to show all values on jsp page fetched from trafficTypeList of TrafficController     --->
                                            <c:forEach var="divisionTypeBean" items="${requestScope['divisionTypeList']}"  varStatus="loopCounter">
                                                <tr  class="${loopCounter.index % 2 == 0 ? 'even': 'odd'}" >
                                                    <%--  <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">
                                                          <input type="hidden" id="status_type_id${loopCounter.count}" value="${statusTypeBean.status_type_id}">${lowerLimit - noOfRowsTraversed + loopCounter.count}
                                                          <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                                      </td> --%>
                                                    <td id="t1c${IDGenerator.uniqueID}" style="display:none" onclick="fillColumns(id)">${divisionTypeBean.model_id}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.model_type}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.device_name}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.device_no}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.warrenty_period}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.device_address}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.remark}</td>

                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td align='center' colspan="13">
                                                    <c:choose>
                                                        <c:when test="${showFirst eq 'false'}">
                                                            <input class="btn btn-primary" type='submit' name='buttonAction' value='First' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="btn btn-primary" type='submit' name='buttonAction' value='First'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${showPrevious == 'false'}">
                                                            <input class="btn btn-success" type='submit' name='buttonAction' value='Previous' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="btn btn-success" type='submit' name='buttonAction' value='Previous'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${showNext eq 'false'}">
                                                            <input class="btn btn-warning" type='submit' name='buttonAction' value='Next' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="btn btn-warning" type='submit' name='buttonAction' value='Next'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${showLast == 'false'}">
                                                            <input class="btn btn-info" type='submit' name='buttonAction' value='Last' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="btn btn-info" type='submit' name='buttonAction' value='Last'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <!--- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. -->
                                            <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                            <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
<!--                                            <input  type="hidden" id="searchCityType" name="searchCityType" value="${searchCityType}" >-->
                                            <input  type="hidden" id="Device_name" name="Device_name" value="${searchDeviceNAme}" >
                              </table></DIV>
        </form>

        <div class="container">
                                    <form name="form2" method="POST" action="ModelCont.do" onsubmit="return verify()">
                                         <table class="table table-hover" border="1" style="border-color:#d0dafd">
                                            <tr id="message">
                                                <c:if test="${not empty message}">
                                                    <td colspan="2" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Model Type</th>
                                                <td><input class="form-control" type="text" id="model_type" name="model_type" value="" size="40" disabled>
                                                <input  type="button" class="btn btn-primary"  name="create_new_model_type" value="Add" onclick="goTo('model_type')"></td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Device Name </th>

                                                <td><input class="form-control" type="text" id="device_name" name="device_name" value="" size="40" disabled>
                                                <input class="input" type="hidden" id="model_id" name="model_id" value="" ></td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Device no.</th>
                                                <td><input class="form-control" type="text" id="device_no" name="device_no" value="" size="40" disabled></td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Warranty Period</th>
                                                <td><input class="form-control" type="text" id="warranty_period" name="warranty_period" value="" size="40" disabled></td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Device Address</th>
                                                <td><input class="form-control" type="text" id="device_address" name="device_address" value="" size="40" disabled></td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Remark</th>
                                                <td><input class="form-control"  type="text" id="remark" name="remark" value="" size="40" disabled></td>
                                            </tr>

                                            <tr>
                                                <td align='center' colspan="2">
                                                    <input class="btn btn-primary" type="button" name="edit" id="edit" value="Edit" onclick="makeEditable(id)" disabled>
                                                    <%--       <input class="button" type="submit" name="task" id="revise" value="Revise" onclick="setStatus(id)" disabled>  --%>
                                                    <input class="btn btn-success" type="submit" name="task" id="save" value="Save" onclick="setStatus(id)" disabled>
                                                    <input class="btn btn-danger" type="submit" name="task" id="save_As" value="Save AS New" onclick="setStatus(id)" disabled>
                                                    <input class="btn btn-warning" type="reset" name="new" id="new" value="New" onclick="makeEditable(id)">
                                                    <input class="btn btn-info" type="submit" name="task" id="cancel" value="Cancel" onclick="setStatus(id)" disabled>

                                                </td>
                                            </tr>

                                            <%-- These hidden fields "lowerLimit", "noOfRowsTraversed", and "clickedButton" belong to form2 of table2. --%>
                                            <input type="hidden" name="active" id="active" value="">
                                            <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                            <input type="hidden" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                            <input type="hidden" id="clickedButton" value="">
<!--                                            <input type="hidden"  name="searchCityType" value="${searchCityType}" >-->
                                            <input type="hidden"  name="DeviceName" value="${searchDeviceName}" >
                              </table>
            </form>
        </div>

        <%@include file="/layout/footer.jsp" %>
    
    </body>
</html>
