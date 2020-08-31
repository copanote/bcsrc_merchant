<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<style type="text/css">

        body {

               white-space:pre;

        }

        table, td {

               border:1px solid;

        }

        table {

               margin:auto;

               width:85%;

               table-layout:fixed;

               word-break:break-all;

        }

</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>

</script>

<title>AuthResult</title>

</head>

<body>

<form>

        <table>

               <tbody>

                       <tr>

                              <td>AuthResult(Close Popup And Payment Checkout Process !!!)</td>

                       </tr>

                       <tr>

                              <td></td>

                       </tr>

                       <tr>

                              <td>resultCode : <%= request.getParameter("resultCode") %></td>

                       </tr>

                       <tr>

                              <td>resultMessage : <%= request.getParameter("resultMessage") %></td>

                       </tr>

                       <tr>

                              <td>srcCorrelationId : <%= request.getParameter("srcCorrelationId") %></td>

                       </tr>

               </tbody>

        </table>

</form>

</body>

</html>