<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Schedule |My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/schedule.css" >

<style>
 body { font-size: 62.5%; }
 label, input { display:block; }
 input.text { margin-bottom:12px; width:95%; padding: .4em; }
 fieldset { padding:0; border:0; margin-top:25px; }
 h1 { font-size: 1.2em; margin: .6em 0; }
 div#users-contain { width: 350px; margin: 20px 0; }
 div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
 div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
 .ui-dialog .ui-state-error { padding: .3em; }
 .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script>
  $(function() {
   var evt_title = $( "#evt_title" ),
   		evt_category = $( "#evt_category" ),
        evt_datetime_s = $( "#evt_datetime_s" ),
        evt_datetime_e = $( "#evt_datetime_e" ),
        evt_location = $( "#evt_location" ),
        evt_contents = $( "#evt_contents" ),
        allFields = $( [] ).add( evt_title ).add( evt_category ).add( evt_datetime_s ).add( evt_datetime_e ).add( evt_location ).add( evt_contents ),
        tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips.text( t ).addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkNull( o, n ) {
        if ( o.val().length == 0 ) {
          o.addClass( "ui-state-error" );
          updateTips( "Please, enter a " + n + "." );
          return false;
        } else {
          return true;c
        }
      }
 	
    $( "#dialog-message" ).dialog({
    	autoOpen: false,
        modal: true,
        buttons: {
          Ok: function() {
        	$( "#dialog-form" ).dialog( "close" );
            $( this ).dialog( "close" );
            location.reload();
          }
        }
      });
    
    $( "#dialog-form" ).dialog({
      autoOpen: false,
      show: {
          effect: "blind",
          duration: 300
        },
      height: 550,
      width: 400,
      modal: true,
      buttons: {
        "Create an event": function() {
          var bValid = true;
          allFields.removeClass( "ui-state-error" );
 			
          bValid = bValid && checkNull( evt_title, "title of event");
          bValid = bValid && checkNull( evt_datetime_s, "start date of event");
          bValid = bValid && checkNull( evt_datetime_e, "end date of event");
           
          if ( bValid ) {
            /* $( "#users tbody" ).append( "<tr>" +
              "<td>" + name.val() + "</td>" +
              "<td>" + email.val() + "</td>" +
              "<td>" + password.val() + "</td>" +
            "</tr>" ); */
            $.ajax({
    			url: "<c:out value='${context_name}'/>/user/schedule/scheduleAddProc.do",
    		  	type: "POST",
    		  	dataType: "jsonp",
    		  	jsonp: "jsoncallback",
    			data: $("form#evtFrm").serialize(),
    		  	success: function( response ) {
    		  		console.log(response.result_code);
    		  		if (response.result_code == "E40007") {
    		  			user_id.addClass( "ui-state-error" );
    			        updateTips( response.result_msg );
    		  		} else {
    		  			$( "#dialog-message" ).append("<p>" + 
	    		  						"<span class='ui-icon ui-icon-circle-check' style='float:left; margin:0 7px 50px 0;''></span>" +
	    		  						response.result_msg + 
	    		    		  		  	"</p>");
    		  			$( "#dialog-message" ).dialog( "open" );
    		  			//$( "#errorDiv" ).append("");
    		  		}
    		  	},
    			error: function( jqXHR, textStatus, errorThrown ) {
    				//$.mobile.hidePageLoadingMsg();
    				console.log('Status: ' + textStatus + "\nError: " + errorThrown);
    			}
    		});
          }
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        allFields.val( "" ).removeClass( "ui-state-error" );
      }
    });
    
    $( "#dialog-view" ).dialog({
        autoOpen: false,
        show: {
            effect: "blind",
            duration: 300
          },
        height: 550,
        width: 400,
        modal: true,
        buttons: {
          Close: function() {
            $( this ).dialog( "close" );
            document.getElementById("title").value = "";
	  		document.getElementById("datetime_s").value = "";
	  		document.getElementById("datetime_e").value = "";
	  		document.getElementById("location").value = "";
	  		document.getElementById("contents").value = "";
          }
        },
        close: function() {
        	document.getElementById("title").value = "";
	  		document.getElementById("datetime_s").value = "";
	  		document.getElementById("datetime_e").value = "";
	  		document.getElementById("location").value = "";
	  		document.getElementById("contents").value = "";
        }
      });
 
    $("#new").button({
	    icons: {
	        primary: "ui-icon-document"
	    }
	}).click(function() {
		$( "#dialog-form" ).dialog( "open" );
	});
  });
  
  function doEventView(evt_id) {
	  $.ajax({
			url: "<c:out value='${context_name}'/>/user/schedule/" + evt_id + ".do",
		  	type: "POST",
		  	dataType: "jsonp",
		  	jsonp: "jsoncallback",
			data: $("form#evtView").serialize(),
		  	success: function( response ) {
		  		$( "#dialog-view" ).dialog( "open" );
		  		document.getElementById("title").value = response.evt_title;
		  		document.getElementById("datetime_s").value = response.evt_datetime_s;
		  		document.getElementById("datetime_e").value = response.evt_datetime_e;
		  		document.getElementById("location").value = response.evt_location;
		  		document.getElementById("contents").value = response.evt_contents;
		  	},
			error: function( jqXHR, textStatus, errorThrown ) {
				console.log('Status: ' + textStatus + "\nError: " + errorThrown);
			}
		});
  }
 </script>
  
</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="3" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/my_schedule_on.png" alt="my_schedule" width="186" height="50" border="0"></li>
            <li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/recommended_off.png" alt="recommended_school"
            width="186" height="50" border="0"></a></li>
            <li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/consult_off.png" alt="consult" width="186" height="50" border="0"></a></li>
        </ul>
    
    </div>
    
	<div class="form_box">
    	<div class="schedule_cover">
        <section class="calendar">
        	<div style="height: 50px; float: right;">
        	<button id="new" style="width: 70px; height: 25px; font-size: 8px;">New</button>
        	</div>
            <div class="schedule_btn">
            <p><a href="<%=request.getContextPath() %>/user/schedule/scheduleMonthly.do?curYear=${current_year }&curMonth=${current_month - 1}"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_back.png" alt="back" width="22" height="25" border="0"></a></p>
            <p><a href="<%=request.getContextPath() %>/user/schedule/scheduleMonthly.do?curYear=${current_year }&curMonth=${current_month + 1}"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_next.png" alt="next" width="22" height="25" border="0"></a></p>
            <p class="month"><c:out value='${current_year}'/>년 <c:out value='${current_month + 1}'/>월</p>
            <p class="select_schedule"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_month_on.png" alt="month" width="50" height="21" border="0"></p>
            <p class="select_schedule"><a href="<%=request.getContextPath() %>/user/schedule/scheduleWeekly.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_week_off.png" alt="week" width="50" height="21" border="0"></a></p>
            <p class="select_schedule"><a href="<%=request.getContextPath() %>/user/schedule/scheduleDaily.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_day_off.png" alt="day" width="50" height="21" border="0"></a></p>
            </div>
            
            <div class="schedule_month">
            	<table>
                	<tr>
	                	<c:forEach var="week_header" items="${weekdays }">
	                		<th><c:out value='${week_header }'/></th>
	                	</c:forEach>
                    </tr>
                    <tr>
                    
             <c:forEach var="day" items="${monthly_days }" varStatus="status">
				<c:if test="${status.count % 7 == 0 }">
						<td>
							<c:out value='${day }'/>
							
								<c:forEach var="event" items="${event_map[day] }">
								<li><a href="javascript:doEventView('<c:out value="${event.evt_id }"/>')"><c:out value='${event.evt_title }'/></a></li>
								</c:forEach>						
							
						</td>
					</tr>
					<tr>
				</c:if> 
				<c:if test="${status.count % 7 != 0 }">
					<c:if test="${status.count % 7 == 1 }">
						<td class="sunday">
							<c:out value='${day }'/>
							
								<c:forEach var="event" items="${event_map[day] }">
								<li><a href="javascript:doEventView('<c:out value="${event.evt_id }"/>')"><c:out value='${event.evt_title }'/></a></li>
								</c:forEach>					
							
						</td>   
					</c:if>
					<c:if test="${status.count % 7 != 1 }">
						<td>
							<c:out value='${day }'/>
							
								<c:forEach var="event" items="${event_map[day] }">
								<li><a href="javascript:doEventView('<c:out value="${event.evt_id }"/>')"><c:out value='${event.evt_title }'/></a></li>
								</c:forEach>							
							
						</td>   
					</c:if>
				</c:if>	
				<c:if test="${status.last }">
					</tr>
				</c:if>
             </c:forEach>
                </table>
			</div>
		</section>
    	<div class="layout_fix02"></div>  
		</div>
	</div>
</section>

<div id="dialog-form" title="Create new event" style="display: none;">
  <p class="validateTips">All form fields are required.</p>
 
  <form id="evtFrm" method="post">
  <input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
  <input type="hidden" name="student_id" value="<c:out value='${userEntity.user_id }'/>" />
  <fieldset>
  	<label for="title">Title</label>
    <input type="text" name="evt_title" id="evt_title" class="text ui-widget-content ui-corner-all" />
    <label for="category">Category</label>
    <input type="text" name="evt_category" id="evt_category" value="" class="text ui-widget-content ui-corner-all" />
    <label for="sdate">State date</label>
    <input type="datetime-local" name="evt_datetime_s" id="evt_datetime_s" value="2014-07-20T16:00" step="1800" class="text ui-widget-content ui-corner-all" />
    <label for="sdate">End date</label>
    <input type="datetime-local" name="evt_datetime_e" id="evt_datetime_e" value="2014-07-20T18:00" step="1800" class="text ui-widget-content ui-corner-all" />
    <label for="location">Location</label>
    <input type="text" name="evt_location" id="evt_location" value="" class="text ui-widget-content ui-corner-all" />
    <label for="location">Contents</label>
    <textarea name="evt_contents" id="evt_contents" style="width: 360px; height: 100px;" class="text ui-widget-content ui-corner-all" ></textarea>
  </fieldset>
  </form>
</div>

<div id="dialog-view" title="View event">
  <form id="evtView" method="post">
  <fieldset>
  	<label for="title">Title</label>
    <input type="text" name="_title" id="title" readonly="readonly" class="text ui-widget-content ui-corner-all" />
    <label for="category">Category</label>
    <input type="text" name="category" id="category" value="" readonly="readonly" class="text ui-widget-content ui-corner-all" />
    <label for="sdate">State date</label>
    <input type="datetime-local" name="datetime_s" id="datetime_s" readonly="readonly" value="" step="1800" class="text ui-widget-content ui-corner-all" />
    <label for="sdate">End date</label>
    <input type="datetime-local" name="datetime_e" id="datetime_e" readonly="readonly" value="" step="1800" class="text ui-widget-content ui-corner-all" />
    <label for="location">Location</label>
    <input type="text" name="location" id="location" value="" readonly="readonly" class="text ui-widget-content ui-corner-all" />
    <label for="location">Contents</label>
    <textarea name="contents" id="contents" style="width: 360px; height: 100px;" class="text ui-widget-content ui-corner-all" readonly="readonly"></textarea>
  </fieldset>
  </form>
</div>

<div id="dialog-message" title="New event created">
</div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
