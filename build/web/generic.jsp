<%--
    Document   : success
    Created on : 23 Aug, 2019, 10:45:59 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>





<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>jQuery Add / Remove Table Rows Dynamically</title>
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <style type="text/css">
            form{
                margin: 20px 0;
            }
            form input, button{
                padding: 5px;
            }
            table{
                width: 1400px;
                margin-bottom: 20px;
                border-collapse: collapse;
                align-self: center;
            }
            table, th, td{
                border: 1px solid #cdcdcd;
            }
            table th, table td{
                padding: 10px;
                text-align: left;
            }
            .button {
                background-color: #6c7ae0;
                border: none;
                color: white;
                padding: 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 13px;
            }
            #example3 {
                margin-left: 10%;
                width: 1550px;
                position: absolute;
                margin-top: 10px;
                border-radius: 25px;
/*                border: 1px solid;*/
                padding: 10px;
/*                box-shadow: 5px 10px 18px #007bff;*/
            }
            h1{
                margin-bottom: 3%;
                color: #6c7ae0;
                font-style: oblique;

            }
            input[type=text], select {
                width: 100%;
                padding: 9px 15px;
                margin: 5px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
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
    width: 70%;
    margin-left: 15%;
}
.ms{
    background-color: yellow;
    align-items:baseline;
    width:90%;
    
    
} 

        </style>
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script type="text/javascript">

            var i = 0;
            
            $(document).ready(function () {
                

                $(".add-row").click(function () {
                                       
                    if(i === 0){
                    var markup = "<tr><td><input type='checkbox' name='record'></td>"

                            + "<td><input type='text' name='colname" + i + "' placeholder='Column name' value='id'></td>"
                            + "<td><input type='checkbox' name='PK" + i + "' checked></td>"
                            + "<td><input type='checkbox' name='status" + i + "' checked></td>"
                            + "<td><input type='checkbox' name='AI" + i + "' checked></td>"

                            + "<td><select name='ttype" + i + "'><option value=''>--Select--</option><option value='INT'>INT</option></select></td>"
                            + "<td><input type='text' name='size" + i + "'></td>"
                            + "<td><input type='text' name='defaultv" + i + "'></td>"
                            + "<td><select name='peripheraltype" + i + "'><option value=''>--Select--</option><option value='Image'>Image</option><option value='Images'>Images</option><option value='Audio'>Audio</option></select></td>"
                            + "</tr>";
                    $("table tbody").append(markup);                                       
                    i++;
                    
                    
                    var markup = "<tr><td><input type='checkbox' name='record'></td>"

                            + "<td><input type='text' name='colname" + i + "' placeholder='Column name'></td>"
                            + "<td><input type='checkbox' name='PK" + i + "'></td>"
                            + "<td><input type='checkbox' name='status" + i + "'></td>"
                            + "<td><input type='checkbox' name='AI" + i + "'></td>"

                            + "<td><select name='ttype" + i + "'><option value=''>--Select--</option><option value='VARCHAR'>VARCHAR</option></select></td>"
                            + "<td><input type='text' name='size" + i + "'></td>"
                            + "<td><input type='text' name='defaultv" + i + "'></td>"
                            + "<td><select name='peripheraltype" + i + "'><option value=''>--Select--</option><option value='Image'>Image</option><option value='Images'>Images</option><option value='Audio'>Audio</option></select></td>"
                            + "</tr>";
                    $("table tbody").append(markup);
                    
                    
                    i++;
                    
                }                                         
                    var markup = "<tr><td><input type='checkbox' name='record'></td>"

                            + "<td><input type='text' name='colname" + i + "' placeholder='Column name'></td>"
                            + "<td><input type='checkbox' name='PK" + i + "'></td>"
                            + "<td><input type='checkbox' name='status" + i + "'></td>"
                            + "<td><input type='checkbox' name='AI" + i + "'></td>"

                            + "<td><select name='ttype" + i + "'><option value=''>--Select--</option><option value='VARCHAR'>VARCHAR</option></select></td>"
                            + "<td><input type='text' name='size" + i + "'></td>"
                            + "<td><input type='text' name='defaultv" + i + "'></td>"
                            + "<td><select name='peripheraltype" + i + "'><option value=''>--Select--</option><option value='Image'>Image</option><option value='Images'>Images</option><option value='Audio'>Audio</option></select></td>"
                            + "</tr>";




                    $("table tbody").append(markup);
                    
                    
                    i++;

                });


                // Find and remove selected table rows
                $(".delete-row").click(function () {
                    $("table tbody").find('input[name="record"]').each(function () {
                        if ($(this).is(":checked")) {
                            $(this).parents("tr").remove();
                        }
                    });
                });
            });

            function inspectbox() {
                if (document.forms.addshow.morelocations.checked)
                    document.forms.addshow.Location1.style.visibility = 'visible';
                else
                    document.forms.addshow.Location1.style.visibility = 'hidden';
            }
            function count() {
                document.getElementById('colm').value = i;
                var table_name = document.getElementById("table_name").value;
                var result = true;
                if (table_name === null) {
                    result = false;
                    $("#message").html("<td colspan='5' bgcolor='coral'><b>Table Name is required...</b></td>");
                } else {
                    result = true;
                }
                return result;
            }

        </script>
       
    </head>
    <body>
          <ul>
  <li><a class="active" href="TableViewCont">Home</a></li>
  <li><a href="generic.jsp">CREATE TABLE</a></li>
<!--  <li><a href="#contact">EDIT TABLE</a></li>-->
  <li><a href="TableListCont">SHOW TABLE</a></li>
  <li style="float:right"><a class="active" href="login.jsp">LOGOUT</a></li>

</ul>
        <div id="example3"  align="center" >
            <h1 class="text-center">CREATE TABLE</h1>
            <form action="GenericCont" method="post" name='addshow' onsubmit="return count()">
                <table>
                    <thead>
                        <!--                    <tr id="message">
                                        <c:if test="${not empty message}">
                                            <td colspan="2" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                                        </c:if>
                                        </tr>-->
                        <tr>

                    <span>Table Name</span>
                    <span><input type="text" id="table_name"  name="tablename" placeholder="table name"></span>
                    <span><input  type="button" class="add-row button" name="task" value="Add Column"></span>
                    <span> <input type="button" class="delete-row button" value="Delete Column"></span>
                    <span><input  type="submit" value="Create Table" class="button" name="task"></span></tr>
                    <input type="hidden" name="colm" value="" id="colm">

                    <br> <br><tr>
                        <th>Check</th>

                        <th>column name</th>

                        <th>pk</th>
                        <th>notnull</th>
                        <th>AI</th>
                        <th>data type</th>
                        <th>size</th>
                        <th>default</th>
                        <th>Peripheral Type</th>
                    </tr>
                    </thead>
                    <tbody>
                        
<!--                                            <tr>
                                                <td><input type="checkbox" name="record"></td>
                                                <td><input type="text" name="colname" value="id"></td>
                        
                        
                                                <td><input type="checkbox" name="PK" checked></td>
                        
                                                <td><input type="checkbox" name="status" checked></td>
                                                <td><input type="checkbox" name="AI" checked></td>
                                            
                        
                                                <td>  <select name="ttype">
                                                        <option value="">--Select--</option>
                                                        <option value="int">INT</option>
                                                      
                                                    </select></td>
                                                <td><input type="text" name="size"></td>
                                                <td><input type="text" name="defaultv"></td>
                                                 <td><select name="peripheraltype"><option value=''>--Select--</option><option value='Image'>Image</option><option value='Images'>Images</option><option value='Vedio'>Vedio</option><option value='Audio'>Audio</option></select></td>
                                            </tr>-->
                     
                    </tbody>
                    
                </table>
                                          <center> <div class="ms"><p style="color: red">${msg}</p></div></center>
            </form>
        </div>
  
    </body>
</html>        