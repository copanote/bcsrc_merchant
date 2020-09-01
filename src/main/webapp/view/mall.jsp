<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

               $("#srcPayment").bind("click", function() {

                       var _width = "1000";

                       var _height = "1000";

                       var _left = Math.ceil((window.screen.width - _width)/2);

                       var _top = Math.ceil((window.screen.height - _height)/2);

                       window.open("", "dpaForm", "width=" + _width + ",height=" + _height + ",left=" + _left + ",top=" + _top);

 

                       var form = $("#dpaForm");

                       form.attr("action", "PayAuth.jsp");

                       form.attr("method", "post");

                       form.attr("target", "dpaForm");

                       form.submit();

               })

        });

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>가맹점 테스트 페이지</title>

</head>

<body>

<form id="dpaForm" name="dpaForm" method="post">

        <table>

               <tbody>

                       <tr>

                              <td colspan="3">호출 PG사 고유 IDD</td>

                              <td><input type="text" id="srciDpaId" name="srcDpaId" value="040c3f37-5118-4f86-834c-0b397a6480bb" size="40px"></td>

                       </tr>

                       <tr>

                              <td rowspan="7">구매상품 </td>

                              <td colspan="2">구매 상품명</td>

                              <td><input type="text" id="productName" name="productName" value="Sulwasoo x 300ml" size="40px"></td>

                       </tr>

                       <tr>

                              <td rowspan="2">거래금액</td>

                              <td>거래금액</td>

                              <td><input type="text" id="transactionAmount" name="transactionAmount" value="120000" size="40px"></td>

                       </tr>

                       <tr>

                              <td>거래 통화 단위</td>

                              <td><input type="text" id="transactionCurrencyCode" name="transactionCurrencyCode" value="KRW" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">거래유형</td>

                              <td><input type="text" id="transactionType" name="transactionType" value="PURCHASE" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">판매자 국가 코드</td>

                              <td><input type="text" id="merchantCountryCode" name="merchantCountryCode" value="KR" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">판매자 주문 번호</td>

                              <td><input type="text" id="merchantOrderId" name="merchantOrderId" value="113-9545184-2920349" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">쇼핑몰국가코드</td>

                              <td><input type="text" id="dpaLocale" name="dpaLocale" value="ko_KR" size="40px"></td>

                       </tr>

                       <tr>

                              <td rowspan="9">구매자 </td>

                              <td rowspan="2">구매자 식별 정보</td>

                              <td>식별값 타입</td>

                              <td><input type="text" id="identityType" name="identityType" value="ID" size="40px"></td>

                       </tr>

                       <tr>

                              <td>식별 데이터</td>

                              <td><input type="text" id="identityValue" name="identityValue" value="testsrcid" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 E-Mail 주소</td>

                              <td><input type="text" id="emailAddress" name="emailAddress" value=testsrcid@naver.com size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 휴대전화번호</td>

                              <td><input type="text" id="mobileNumber" name="mobileNumber" value="01022389528" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 지역 코드</td>

                              <td><input type="text" id="countryCode" name="countryCode" value="VN" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 언어 코드</td>

                              <td><input type="text" id="languageCode" name="languageCode" value="vi" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 이름</td>

                              <td><input type="text" id="firstName" name="firstName" value="ziwag" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 성</td>

                              <td><input type="text" id="lastName" name="lastName" value="dsjing" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="2">고객 전체 성명</td>

                              <td><input type="text" id="fullName" name="fullName" value="ziwag dsjing" size="40px"></td>

                       </tr>

                       <tr>

                              <td colspan="3">paymentBrandName</td>

                              <td><input type="text" id="paymentBrandName" name="paymentBrandName" value="sacombank" size="50px"></td>

                       </tr>

                       <tr>

                              <td colspan="3">returnUrl</td>

                              <td><input type="text" id="returnUrl" name="returnUrl" value="http://localhost:7070/view/AuthResult.jsp" size="50px"></td>

                       </tr>
                       
                       

                       <tr>

                              <td colspan="4" align="center"><button type="button" id="srcPayment">BCSRC 결제</button></td>

                       </tr>

               </tbody>

        </table>

        <input type="hidden" name="json" id="json"/>

</form>

</body>

</html>