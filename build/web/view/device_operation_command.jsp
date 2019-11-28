<%-- 
    Document   : command
    Created on : Dec 31, 2018, 4:02:40 PM
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
    jQuery(function () {

        $("#device_type").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getDeviceType"
                },
                action2: function() { return  $("#manufacturer").val();}
            }
        });
        $("#manufacturer").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getManufacturer"
                }
            }
        });
        $("#device_name").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getDeviceName"
                },
                 action2: function() { return  $("#manufacturer").val();},
                action3: function() { return  $("#device_type").val();}
            }
        });
        $("#device_no").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getDeviceNo"
                },
                action2: function() { return  $("#device_name").val();},
                action3: function() { return  $("#manufacturer").val();},
                action4: function() { return  $("#device_type").val();}
            }
        });
        $("#operation_name").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getOperationName"
                }
            }
        });
        $("#command_type").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getCommandType"
                }
            }
        });
        $("#searchManufacturerName").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getManufacturerName"
                }
            }
        });
        $("#searchDeviceType").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getSearchDeviceType"
                }
            }
        });
        $("#searchDeviceName").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getSearchDeviceName"
                }
            }
        });

        $("#searchCommandName").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getSearchCommandName"
                }
            }
        });
        
         $("#searchOperationName").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getSearchOperationName"
                }
            }
        });
        
        
          
           $("#command").autocomplete("DeviceOperationCommandCont.do", {
            extraParams: {
                action1: function () {
                    return "getCommand"
                }
            }
        });


//        $('#btnDialog').click(function ()
//{
//    $(this).speedoPopup(
//    {
//        width:550,
//        height:265,
//        useFrame: TRUE,
//        href: '#divContentToPopup'
//    });
//});





    });

    function setDefaultColor(noOfRowsTraversed, noOfColumns) {
        for (var i = 0; i < noOfRowsTraversed; i++) {
            for (var j = 1; j <= noOfColumns; j++) {
                document.getElementById("t1c" + (i * noOfColumns + j)).bgColor = "";     // set the default color.
            }
        }
    }
    function makeEditable(id) {

        document.getElementById("device_name").disabled = false;
        document.getElementById("device_no").disabled = false;

        document.getElementById("manufacturer").disabled = false;
        document.getElementById("device_type").disabled = false;

        document.getElementById("command_format").disabled = false;
        document.getElementById("command_format1").disabled = false;
        document.getElementById("command").disabled = false;
     
   

       
        document.getElementById("operation_name").disabled = false;
       
       
        document.getElementById("remark").disabled = false;

        document.getElementById("save").disabled = false;
//        document.getElementById("revise").disabled =false;
        document.getElementById("cancel").disabled = false;
        document.getElementById("save_As").disabled = false;
        //document.getElementById("save").disabled = true;
        if (id === 'new') {
            //    document.getElementById("created_date").disabled = true;
            // document.getElementById("active").value ='';
            document.getElementById("message").innerHTML = "";      // Remove message
            document.getElementById("manufacturer").focus();
            $("#message").html("");

            //document.getElementById("revise").disabled = true;
            document.getElementById("cancel").disabled = true;
            document.getElementById("save_As").disabled = true;
            document.getElementById("save").disabled = false;

            setDefaultColor(document.getElementById("noOfRowsTraversed").value, 3);
            document.getElementById("manufacturer").focus();

        }
        if (id === 'edit') {

            document.getElementById("save_As").disabled = false;
            document.getElementById("cancel").disabled = false;
        }
    }
    function setStatus(id) {
        debugger;
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
    
     
                         function generateReport(id){
                               var searchOperationName=document.getElementById("searchOperationName").value;
                               var searchCommandName=document.getElementById("searchCommandName").value;
                               var searchDeviceType=document.getElementById("searchDeviceType").value;
                               var searchDeviceName=document.getElementById("searchDeviceName").value;
                              
                             
                             var queryString = "task=generateMapReport&device_command_id="+id;
                                 var url = "DeviceOperationCommandCont.do?" + queryString+ "&searchOperationName="+searchOperationName+ "&searchCommandName="+searchCommandName+ "&searchDeviceType="+searchDeviceType+ "&searchDeviceName="+searchDeviceName;
                              popupwin = openPopUp(url, "Show Image", 600, 900);
                         }
                        
    
    
    
    
    function verify() {
        var result;
        if (document.getElementById("clickedButton").value == 'Save' || document.getElementById("clickedButton").value == 'Save AS New' || document.getElementById("clickedButton").value == 'Revise' || document.getElementById("clickedButton").value == 'Delete') {
            var division_name_m = document.getElementById("division_name_m").value;
            var a = document.getElementById("active").value;
            //    alert(a);
            if (myLeftTrim(division_name_m).length == 0) {

                // document.getElementById("message").innerHTML = "<td colspan='5' bgcolor='coral'><b>Organisation Type Name is required...</b></td>";
                $("#message").html("<td colspan='5' bgcolor='coral'><b>Ward No is required...</b></td>");
                document.getElementById("division_name_m").focus();
                return false; // code to stop from submitting the form2.
            }

            if (document.getElementById("active").value == 'Revised' || document.getElementById("active").value == 'Cancelled')
            {
                $("#message").html("<td colspan='5' bgcolor='coral'><b>You can not perform any operation on revised and cancelled record...</b></td>");
                // document.getElementById("source_wattage"+i).focus();
                return false; // code to stop from submitting the form2.
            }
            if (result == false)    // if result has value false do nothing, so result will remain contain value false.
            {

            } else {
                result = true;
            }

            if (document.getElementById("clickedButton").value == 'Save AS New') {
                result = confirm("Are you sure you want to save it as New record?")
                return result;
            }
        } else
            result = confirm("Are you sure you want to cancel this record?")
        return result;
    }
    function fillColumns(id) {
        debugger;
        var noOfRowsTraversed = document.getElementById("noOfRowsTraversed").value;
        var noOfColumns = 17;
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

        document.getElementById("device_command_id").value = document.getElementById(t1id + (lowerLimit + 0)).innerHTML;
        document.getElementById("manufacturer").value = document.getElementById(t1id + (lowerLimit + 2)).innerHTML;
        document.getElementById("device_type").value = document.getElementById(t1id + (lowerLimit + 3)).innerHTML;
        document.getElementById("device_name").value = document.getElementById(t1id + (lowerLimit + 4)).innerHTML;
        document.getElementById("device_no").value = document.getElementById(t1id + (lowerLimit + 5)).innerHTML;
        document.getElementById("operation_name").value = document.getElementById(t1id + (lowerLimit + 6)).innerHTML;
        document.getElementById("command").value = document.getElementById(t1id + (lowerLimit + 7)).innerHTML;
        document.getElementById("command_type").value = document.getElementById(t1id + (lowerLimit + 8)).innerHTML;
    
        var format = document.getElementById(t1id + (lowerLimit + 9)).innerHTML;
        if (format === "hex") {
            document.getElementById("command_format1").checked = true;
            document.getElementById("command_format").checked = false;
        } else if (format === "string") {
            document.getElementById("command_format").checked = true;
            document.getElementById("command_format1").checked = false;
        }
        document.getElementById("remark").value = document.getElementById(t1id + (lowerLimit + 16)).innerHTML;

        //       var b=  document.getElementById(t1id +(lowerLimit+8)).innerHTML;
        // alert(b);
        for (var i = 0; i < noOfColumns; i++) {
            document.getElementById(t1id + (lowerLimit + i)).bgColor = "#d0dafd";        // set the background color of clicked row to yellow.
        }
        //   makeEditable('');

        document.getElementById("edit").disabled = false;
        if (!document.getElementById("save").disabled)   // if save button is already enabled, then make edit, and cancel button enabled too.
        {
            document.getElementById("save_As").disabled = true;
            document.getElementById("cancel").disabled = false;
        }
        //  document.getElementById("message").innerHTML = "";      // Remove message
        $("#message").html("");
    }
    function myLeftTrim(str) {
        var beginIndex = 0;
        for (var i = 0; i < str.length; i++) {
            if (str.charAt(i) === ' ')
                beginIndex++;
            else
                break;
        }
        return str.substring(beginIndex, str.length);
    }
    var popupwin = null;
              function displayMapList() {
                               var searchOperationName=document.getElementById("searchOperationName").value;
                               var searchCommandName=document.getElementById("searchCommandName").value;
                               var searchDeviceType=document.getElementById("searchDeviceType").value;
                               var searchDeviceName=document.getElementById("searchDeviceName").value;
                               
        
        
        
                               var queryString = "task=generatePDF";
                               var url = "DeviceOperationCommandCont.do?" + queryString+ "&searchOperationName="+searchOperationName+ "&searchCommandName="+searchCommandName+ "&searchDeviceType="+searchDeviceType+ "&searchDeviceName="+searchDeviceName;
                               popupwin = openPopUp(url, "Mounting Type Map Details", 500, 1000);

    }

    function openPopUp(url, window_name, popup_height, popup_width) {
        var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
        var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
        var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=yes, scrollbars=yes, location=0, menubar=no, status=no, dependent=yes";

        return window.open(url, window_name, window_features);
    }

    function pack(bytes) {
        var str = "";
// You could make it faster by reading bytes.length once.
        for (var i = 0; i < bytes.length; i += 2) {
// If you're using signed bytes, you probably need to mask here.
            var char = bytes[i] << 8;
// (undefined | 0) === 0 so you can save a test here by doing
//     var char = (bytes[i] << 8) | (bytes[i + 1] & 0xff);
            if (bytes[i + 1])
                char |= bytes[i + 1];
// Instead of using string += you could push char onto an array
// and take advantage of the fact that String.fromCharCode can
// take any number of arguments to do
//     String.fromCharCode.apply(null, chars);
            str += String.fromCharCode(char);
        }
        return str;
    }

    function selectionPopup(url,selection_no,command_id) {
        debugger;
        var popup_height = 580;
        var popup_width = 900;
        var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
        var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
        url = url + "?selection_no="+selection_no+"&command_id="+command_id;
        var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=no, scrollbars=yes, status=no, dialog=yes, dependent=yes";
        popupWindow = window.open(url, 'Selection Window', window_features);
    }
    
    function inputPopup(url,input_no,command_id) {
        debugger;
        var popup_height = 580;
        var popup_width = 900;
        var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
        var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
        url = url + "?input_no="+input_no+"&command_id="+command_id;
        var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=no, scrollbars=yes, status=no, dialog=yes, dependent=yes";
        popupWindow = window.open(url, 'Selection Window', window_features);
    }

</script>


    <body>
<%@include file="/layout/menu1.jsp" %>
<div class="container" style="margin-top: 5%">
    <table class="table table-hover"  style="background-color:#d0dafd ">


                <tr>
                    <td align="center" class="header_table" width="100%">Device Operation Command</td>

                </tr>
            </table>
        </div> <div class="container">
                                    <form name="form0" method="POST" action="DeviceOperationCommandCont.do">
                                        <table class="table table-hover" border="1" style="border-color:#d0dafd">
                                            <tr>
                                                <td>Operation Name</td><td><input class="form-control" type="text" id="searchOperationName" name="searchOperationName" value="${searchOperationName}" size="20" ></td>
                                                <td>Device Type</td><td><input class="form-control" type="text" id="searchDeviceType" name="searchDeviceType" value="${searchDeviceType}" size="20" ></td>
                                                <td>Model Name</td><td><input class="form-control" type="text" id="searchDeviceName" name="searchDeviceName" value="${searchDeviceName}" size="20" ></td>
                                                <td>Command Name</td><td><input class="form-control" type="text" id="searchCommandName" name="searchCommandName" value="${searchCommandName}" size="20" ></td>
                                                <td><input class="btn btn-primary" type="submit" name="task" id="searchIn" value="Search"></td>
                                                <td><input class="btn btn-success" type="submit" name="task" id="showAllRecords" value="Show All Records"></td>
                                                <td><input class="btn btn-danger" type="pdf_button" id="viewPdf" name="task" value="PDF" onclick="displayMapList()" style="width:50px;text-align:center"></td>
                                          </tr>
                </table>
            </form></div>
                                <form name="form1" method="POST" action="DeviceOperationCommandCont.do">
                  <div class="container">
                <table class="table table-hover" border="1" style="border-color:#d0dafd">
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Manufacturer</th>
                                                <th>Device Type</th>
                                                <th>Model Name</th>
                                                <th>Model No.</th>
                                                <th>Operation Name</th>
                                                <th>Command</th>
                                                <th>Remark</th>
                                                <th>Report</th>
                                              
                                            </tr>
                                            <!---below is the code to show all values on jsp page fetched from trafficTypeList of TrafficController     --->
                                            <c:forEach var="divisionTypeBean" items="${requestScope['divisionTypeList']}"  varStatus="loopCounter">
                                                <tr  class="${loopCounter.index % 2 == 0 ? 'even': 'odd'}" >
                                                    <%--  <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">
                                                          <input type="hidden" id="status_type_id${loopCounter.count}" value="${statusTypeBean.status_type_id}">${lowerLimit - noOfRowsTraversed + loopCounter.count}
                                                          <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                                      </td> --%>
                                                    <td id="t1c${IDGenerator.uniqueID}" style="display:none" onclick="fillColumns(id)">${divisionTypeBean.device_command_id}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.manufacturer}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.device_type}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.device_name}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.device_no}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.operation_name}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.command}</td>
                                                    <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" >${divisionTypeBean.remark}</td>
                                                    <td <input class="button" type="submit" id="${divisionTypeBean.device_command_id}" name="report"  value="Generate Report" size="100" onclick="generateReport(id)" >Generate Report</td>
                                                   

                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td align='center' colspan="16">
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
                                            <input  type="hidden" id="searchCityType" name="searchCityType" value="${searchCityType}" >
                                            <input  type="hidden" id="searchDivisionType" name="searchDivisionType" value="${searchDivisionType}" >
                               </table></DIV>
        </form>

        <div class="container">
                                    <form name="form2" method="POST" action="DeviceOperationCommandCont.do" onsubmit="return verify()">
                                        <table class="table table-hover" border="1" style="border-color:#d0dafd">
                                            <tr id="message">
                                                <c:if test="${not empty message}">
                                                    <td colspan="2" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Manufacturer </th>
                                                <td><input  class="form-control" type="text" id="manufacturer" name="manufacturer" value="${manufacturer}" size="40" disabled></td>
                                                <!--<button type="button" class="btn btn-primary small" data-toggle="modal" data-target="#myModal">
                                                    Open modal
                                                  </button></td>-->
                                            </tr>

                                            <tr >
                                                <th class="heading1">Device Type</th>
                                                <td> 
                                                 
                                                    <input  class="form-control" type="text" id="device_type" name="device_type" value="${device_type}" size="40" disabled>
                                                </td>
                                            </tr>
                                            
                                            
                                            <tr>
                                                <th class="heading1">Model Name </th>
                                                <td><input  class="form-control" type="text" id="device_name" name="device_name" value="${deviceName}" size="40" disabled></td>
                                                   <input  class="form-control" type="hidden" id="device_id" name="device_id" value="" >
                                            </tr>
                                            <tr>
                                                <th class="heading1">Model no.  </th>
                                                <td><input  class="form-control" type="text" id="device_no" name="device_no" value="${device_no}" size="40" disabled></td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Operation Name</th>
                                                <td><input  class="form-control" type="text" id="operation_name" name="operation_name" value="" size="40" disabled></td>
                                                   <input  class="form-control" type="hidden" id="operation_id" name="operation_id" value="" >
                                            </tr>
                                            <tr>
                                                <th class="heading1">Command Format</th>
                                                <td>
                                                    <input  class="form-control" type="radio" id="command_format1" name="command_format" value="hex" size="40" disabled> Hexadecimal
                                                    <input  class="form-control" type="radio" id="command_format" name="command_format" value="String" size="40" disabled> String
                                                </td>
                                            </tr>
                                            <tr>
                                                <th class="heading1">Command</th>
                                                <td><input  class="form-control" type="text" id="command" name="command" value="" size="80" disabled></td>
                                                   <input  class="form-control" type="hidden" id="command_id" name="command_id" value="" >
                                            </tr>

<!--                                            <tr>
                                                <th class="heading1">Command Type</th>
                                                <td><input class="input" type="text" id="command_type" name="command_type" value="" size="40" disabled></td>
                                            </tr>-->

                                            
                                            
                                            
                                           
                                            
                                            
                                            <tr>
                                                <th class="heading1">Remark</th>
                                                   <input  class="form-control" type="hidden" id="device_command_id" name="device_command_id" value="" >
                                                <td><input  class="form-control" type="text" id="remark" name="remark" value="" size="40" ></td>
                                            </tr>


                                            <tr>
                                                <td align='center' colspan="2">
                                                    <input class="btn btn-primary" type="button" name="edit" id="edit" value="Edit" onclick="makeEditable(id)" disabled>
                                                    <%--       <input class="button" type="submit" name="task" id="revise" value="Revise" onclick="setStatus(id)" disabled>  --%>
                                                    <input class="btn btn-success" type="submit" name="task" id="save" value="Save" onclick="setStatus(id)" >
                                                    <input class="btn btn-danger"  type="submit" name="task" id="save_As" value="Save AS New" onclick="setStatus(id)" disabled>
                                                    <input class="btn btn-warning" type="reset" name="new" id="new" value="New" onclick="makeEditable(id)">
                                                    <input class="btn btn-info" type="submit" name="task" id="cancel" value="Cancel" onclick="setStatus(id)" disabled>

                                                </td>
                                            </tr>

                                            <%-- These hidden fields "lowerLimit", "noOfRowsTraversed", and "clickedButton" belong to form2 of table2. --%>
                                            <input type="hidden" name="active" id="active" value="">
                                            <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                            <input type="hidden" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                            <input type="hidden" id="clickedButton" value="">
                                            <input type="hidden"  name="searchCityType" value="${searchCityType}" >
                                            <input type="hidden"  name="searchDivisionType" value="${searchDivisionType}" >
                              </table>
            </form>
        </div>

        <%@include file="/layout/footer.jsp" %>


    </body>
</html>
