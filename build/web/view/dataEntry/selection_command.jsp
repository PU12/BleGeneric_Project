<%-- 
    Document   : selection_command
    Created on : 19 Sep, 2019, 4:17:59 PM
    Author     : apogee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


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
    <c:choose>        
        <c:when test="${fn:length(selectionListById) > 0}">            
            <body>
        </c:when>        
        <c:otherwise>
        <body onload="addRow('dataTable',${selection_no},'${command_name}')">
        </c:otherwise>
    </c:choose>

        <table class="table table-hover" border="1" style="border-color:#d0dafd">
           
            <tr>
                <td><%@include file="/layout/menu1.jsp" %> </td>
            </tr>
            <tr style="font-size:larger ;font-weight: 700;" align="center">
                <td>
                    "${command_name}" Selection Details
                </td>
            </tr>
            
            <tr id="message">
                <c:if test="${not empty message}">
                    <td colspan="8" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                </c:if>
            </tr>
            <tr>
                <td style="padding-top: 13px;">
                    <form name="form1" id ="form1" action="SelectionCont.do" method="post" >
                         <table class="table table-hover" border="1" style="border-color:#d0dafd">
                            <tbody>
                                <tr>
                                    <th>S.No.</th>
                                    <th>Command</th>
                                    <th>Parameter</th>                                    
                                    <th  colspan="2" style="min-width:200px;">Selection Value No</th>
                                    <th>Parameter Type</th>
                                    <th>Remark</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="list" items="${requestScope['selectionListById']}" varStatus="loopCounter">
                                    <tr>
                                        <td><input type="text" name="s_no${loopCounter.count}" id="s_no${loopCounter.count}" size="5" value="${loopCounter.count}" readonly>
                                        <input type="hidden" name="selection_id${loopCounter.count}" maxlength="8" size="5" id="selection_id${loopCounter.count}" value="${list.selection_id}">
                                        <input type="hidden" name="count" maxlength="8" size="5" id="count" value="${loopCounter.count}"></td>
                                        <td><input type="text" name="command_name${loopCounter.count}" maxlength="50" size="80" id="command_name${loopCounter.count}" value="${list.command_name}" readonly></td>
                                        <td><input type="text" name="parameter${loopCounter.count}" maxlength="8" size="15" id="parameter${loopCounter.count}" value="${list.parameter}" onkeyup="autocompleteMethod('parameter',${loopCounter.count}+1)"></td>
                                       
                                        <td><input type="text" name="selection_value_no${loopCounter.count}" maxlength="50" size="5" id="selection_value_no${loopCounter.count}" value="${list.selection_value_no}" onkeyup="check(value)"></td>                                         
                                        <td><a href="#" onclick="inputPopup('SelectionValueController','${list.parameter}',${list.selection_value_no},${list.selection_id});return false" id="input_button">View Selection Value</a></td>
                                     
                                        <td><input type="text" name="parameter_type${loopCounter.count}" maxlength="8" size="10" id="parameter_type${loopCounter.count}" value="${list.parameter_type}" onkeyup="autocompleteMethod('parameter_type',${loopCounter.count}+1)"></td>
                                       
                                        <td><input type="text" name="remark${loopCounter.count}" maxlength="8" size="20" id="remark{loopCounter.count}" value="${list.remark}" ></td>
<!--                                        <td><input type="button" class ="button" name="update" value="update" onclick="update(${list.selection_id},${list.selection_value_no})"></td>-->
                                        <td><input type="submit" class="button" name="task"  value="update" ></td>
<!--                                        <td><a href="SelectionCont.do?task=Save&selection_id="${list.selection_id} id="input_button1">Update Value</a></td>                                    -->
                                    </tr>
                                    
                                </c:forEach>
                       
                            <input  type="hidden" name="selection_no1" value="${selection_no}" >
                            <input  type="hidden" name="command_name" id="command_name" value="${command_name}" >
                            <input  type="hidden" name="command_id1" id="command_id" value="${command_id}" >
<!--                            <input  type="hidden" name="selection_update_value" id="selection_update_value" value="${selection_update_value}" >-->
                            
                            </tbody>
                        </table>
                            <div style="padding-top: 10px;" align="center">
                                <input class="btn btn-primary" type="submit" id="save" name="task" value="Save">
                            </div>
                    </form>
                </td>
            </tr>
        </table>
                            
        <script type="text/javascript" language="javascript">

       var selection_update_value;
            function autocompleteMethod(id, count) {
                debugger;
                if (id === "command_name") {
                    $("#command_name" + count).autocomplete("SelectionCont.do", {
                        extraParams: {
                            action1: function () {
                                return "getCommand";
                            }
                        }
                    });
                } else if (id === "parameter") {
                    $("#parameter" + count).autocomplete("SelectionCont.do", {
                        extraParams: {
                            action1: function () {
                                return "getParameter";
                            }
                        }
                    });

                } else if (id === "parameter_type") {
                    $("#parameter_type" + count).autocomplete("SelectionCont.do", {
                        extraParams: {
                            action1: function () {
                                return "getParameterType";
                            }
                        }
                    });
                }

            }
       
//            function update1(selection_id)
//            {
//                debugger;
//                var selection_value_no =selection_update_value;
//                var command_id = document.getElementById('command_id').value;
//                var queryString = "task=update&selection_id=" + selection_id + "&selection_value_no=" + selection_value_no+ "&command_id=" + command_id;
//                var url = "SelectionCont.do?" + queryString; 
//                alert(url);
//                $.ajax({url: url, 
//                    success: function(result){
//                        console.log()
//                    }
//                });
//                //window.open(url);
//            } 
            
            function check(selection_value_no){
                debugger;
               selection_update_value = selection_value_no;               
            }
                
          

            function addRow(tableID, selection_no, command) {
                debugger;


                $("#message").html("");
                var table = document.getElementById(tableID);

                for (var i = 1; i <= selection_no; i++) {
                    var row = table.insertRow(i);


                    var cell1 = row.insertCell(0);
                    var element1 = document.createElement("input");
                    element1.type = "text";
                    element1.id = "s_no" + i;
                    element1.name = "s_no" + i;
                    element1.value = i;
                    element1.size = 3;
                    cell1.appendChild(element1);

                    var cell2 = row.insertCell(1);
                    var element2 = document.createElement("input");
                    element2.type = "text";
                    element2.name = "command_name" + i;
                    element2.id = "command_name" + i;
                    element2.size = 100;
                    element2.maxLength = 2;
                    element2.value = command;
                    element2.setAttribute("onkeyup", 'autocompleteMethod("command_name",' + i + ')');
                    cell2.appendChild(element2);

                    var cell3 = row.insertCell(2);
                    var element2 = document.createElement("input");
                    element2.type = "text";
                    element2.name = "parameter" + i;
                    element2.id = "parameter" + i;
                    element2.size = 5;
                    element2.maxLength = 8;
                    element2.value = "";
                    element2.setAttribute("onkeyup", 'autocompleteMethod("parameter",' + i + ')');
                    cell3.appendChild(element2);

                    var cell4 = row.insertCell(3);
                    var element2 = document.createElement("input");
                    element2.type = "text";
                    element2.name = "selection_value_no" + i;
                    element2.id = "selection_value_no" + i;
                    element2.size = 5;
                    element2.maxLength = 50;
                    element2.value = "";
                    cell4.appendChild(element2);

                    var cell5 = row.insertCell(4);
                    var element2 = document.createElement("input");
                    element2.type = "text";
                    element2.name = "parameter_type" + i;
                    element2.id = "parameter_type" + i;
                    element2.size = 5;
                    element2.maxLength = 8;
                    element2.value = "";
                    element2.setAttribute("onkeyup", 'autocompleteMethod("parameter_type",' + i + ')');
                    cell5.appendChild(element2);

                    var cell6 = row.insertCell(5);
                    var element2 = document.createElement("input");
                    element2.type = "text";
                    element2.name = "remark" + i;
                    element2.id = "remark" + i;
                    element2.size = 5;
                    element2.maxLength = 8;
                    element2.value = "";
                    cell6.appendChild(element2);

                }


            }
   function inputPopup(url,parameter,selection_value_no,selection_id) {
        debugger;
        var popup_height = 580;
        var popup_width = 900;
        var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
        var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
        url = url + "?parameter="+parameter+"&selection_value_no="+selection_value_no+"&selection_id="+selection_id;
        //alert(url);
        var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=no, scrollbars=yes, status=no, dialog=yes, dependent=yes";
        popupWindow = window.open(url, 'Selection Window', window_features);
    }
            function deleteRow(tableID) {
                try {
                    // alert(tableID);
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    var delete_palan_row = 0;
                    for (var i = 0; i < rowCount; i++) {
                        var row = table.rows[i];
                        //  alert(row);
                        var chkbox = row.cells[0].childNodes[0];
                        //     alert(chkbox);
                        if (null != chkbox && true == chkbox.checked || k < rowCount) {
                            // alert(i);
                            var k = i;
                            for (k = i; k < rowCount; k++) {
                                table.deleteRow(i);
                                delete_palan_row++;
                                //  alert(delete_palan_row);
                                // --rowCount;
                                //setSequential(--rowCount, i);
                                // i--;
                                document.getElementById("no_of_plans").value = document.getElementById("no_of_plans").value - 1;
                            }
                        }
                    }
                    // alert("fdfdfd");
                    //document.getElementById("no_of_plans").value =(parseInt(rowCount)-parseInt(delete_palan_row));
                } catch (e) {
                    // alert(e);
                }
            }

            function verify() {
    //        var data = {
    //            command_name: document.getElementById(command_name),
    //            parameter:document.getElementById(parameter),
    //            parameter_value:document.getElementById(parameter_value),
    //            parametere_type:document.getElementById(parametere_type),
    //            remark:document.getElementById(remark)
    //        };
                debugger;
                var form = document.getElementById("form1");
                var dat = form.data;


            }






        </script>

    </body>
</html>