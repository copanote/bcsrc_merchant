<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%    

        String json = request.getParameter("json");

        String srcDpaId = request.getParameter("srcDpaId");

        String transactionAmount = request.getParameter("transactionAmount");

        String transactionCurrencyCode = request.getParameter("transactionCurrencyCode");

        String transactionType = request.getParameter("transactionType");

        String merchantCountryCode = request.getParameter("merchantCountryCode");

        String merchantOrderId = request.getParameter("merchantOrderId");

        String dpaLocale = request.getParameter("dpaLocale");

        String identityType = request.getParameter("identityType");

        String identityValue = request.getParameter("identityValue");

        String emailAddress = request.getParameter("emailAddress");

        String mobileNumber = request.getParameter("mobileNumber");

        String countryCode = request.getParameter("countryCode");

        String languageCode = request.getParameter("languageCode");

        String firstName = request.getParameter("firstName");

        String lastName = request.getParameter("lastName");

        String fullName = request.getParameter("fullName");

        String paymentBrandName = request.getParameter("paymentBrandName");

        String returnUrl = request.getParameter("returnUrl");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<style type="text/css">

        table {

               margin:auto;

               width:70%;

        }

        table, td {

               border:1px solid;

        }

</style>

<script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>

<script>

        $(document).ready(function() {

                       var obj = {

                                      "srciDpaId": $("#srciDpaId").val(),

                                      "dpaTransactionOptions": {

                                              "productName": $("#productName").val(),

                                              "transactionAmount": {

                                                     "transactionAmount": $("#transactionAmount").val(),

                                                     "transactionCurrencyCode": $("#transactionCurrencyCode").val()

                                                     },

                                              "transactionType": $("#transactionType").val(),

                                              "merchantCountryCode": $("#merchantCountryCode").val(),

                                              "merchantOrderId": $("#merchantOrderId").val(),

                                              "dpaLocale": $("#dpaLocale").val(),

                                              },

                                      "consumer": {

                                              "consumerIdentity": {

                                                     "identityType": $("#identityType").val(),

                                                     "identityValue": $("#identityValue").val()

                                                     },

                                              "emailAddress" : $("#emailAddress").val(),

                                              "mobileNumber" : $("#mobileNumber").val(),

                                              "countryCode" : $("#countryCode").val(),

                                              "languageCode" : $("#languageCode").val(),

                                              "firstName" : $("#firstName").val(),

                                              "lastName" : $("#lastName").val(),

                                              "fullName" : $("#fullName").val()

                                              },

                                      "paymentBrandName": $("#paymentBrandName").val(),

                                      "returnUrl": $("#returnUrl").val()

                       }

 

                       var json = JSON.stringify(obj);

                      

 

                       var form = $("#dpaForm");

                       //개발에서 테스트

                       form.attr("action", "https://isrnd.bccard.com:56443/src/payments/init/");

                       //로컬에서 테스트

                       //form.attr("action", "http://localhost:8080/src/payments/init");

                       form.attr("method", "post");

                       //form.find("#json").val(json);

                       console.log("json")

                     

                       form.submit();

        });

       

       

        function to_ajax() {}

       

       

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>BC 카드 페이지</title>

</head>

<body>

<form id="dpaForm" name="dpaForm" method="post"  enctype="application/json" >

        <input type="hidden" name="srcDpaId" id="srcDpaId" value="<%=srcDpaId%>"/>

        <input type="hidden" name="transactionAmount" id="transactionAmount" value="<%=transactionAmount%>"/>

        <input type="hidden" name="transactionCurrencyCode" id="transactionCurrencyCode" value="<%=transactionCurrencyCode%>"/>

        <input type="hidden" name="transactionType" id="transactionType" value="<%=transactionType%>"/>

        <input type="hidden" name="merchantCountryCode" id="merchantCountryCode" value="<%=merchantCountryCode%>"/>

        <input type="hidden" name="merchantOrderId" id="merchantOrderId" value="<%=merchantOrderId%>"/>

        <input type="hidden" name="dpaLocale" id="dpaLocale" value="<%=dpaLocale%>"/>

        <input type="hidden" name="identityType" id="identityType" value="<%=identityType%>"/>

        <input type="hidden" name="identityValue" id="identityValue" value="<%=identityValue%>"/>

        <input type="hidden" name="emailAddress" id="emailAddress" value="<%=emailAddress%>"/>

        <input type="hidden" name="mobileNumber" id="mobileNumber" value="<%=mobileNumber%>"/>

        <input type="hidden" name="countryCode" id="countryCode" value="<%=countryCode%>"/>

        <input type="hidden" name="languageCode" id="languageCode" value="<%=languageCode%>"/>

        <input type="hidden" name="firstName" id="firstName" value="<%=firstName%>"/>

        <input type="hidden" name="lastName" id="lastName" value="<%=lastName%>"/>

        <input type="hidden" name="fullName" id="fullName" value="<%=fullName%>"/>

        <input type="hidden" name="paymentBrandName" id="paymentBrandName" value="<%=paymentBrandName%>"/>

        <input type="hidden" name="returnUrl" id="returnUrl" value="<%=returnUrl%>"/>
        <input type="hidden" name="productName" id="productName" value="FIFA"/>

        <input type="hidden" name="json" id="json" value="<%=json%>"/>   

</form>

</body>

</html>