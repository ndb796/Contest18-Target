<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/dark-hive/jquery-ui.css" id="theme"> -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/fileupload2/css/demo.css"> --%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<style>
.ui-widget {
    font-size: 0.95em;
}
</style>

<link rel="stylesheet" href="http://blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/fileupload2/css/jquery.fileupload.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/fileupload2/css/jquery.fileupload-ui.css">

</head>
<script>
$(function() {
	$("#dialog-form").dialog({
		autoOpen: false,
		show:	{effect: "blind", duration: 500},
		height: 400,
		width: 700,
		modal: true,
		buttons: {
			Close: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			
		}
	});
});
function doUpload() {
	$("#dialog-form").dialog("open");
}
</script>

<div id="dialog-form" title="Image upload">
	<form id="fileupload" action="/My School/controller/upload2.do" method="POST" enctype="multipart/form-data">
	    <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
	    <div class="fileupload-buttonbar">
	        <div class="fileupload-buttons">
	            <!-- The fileinput-button span is used to style the file input field as button -->
	            <span class="fileinput-button">
	                <span>Add files...</span>
	                <input type="file" name="files[]" multiple>
	            </span>
	            <button type="submit" class="start">Start upload</button>
	            <button type="reset" class="cancel">Cancel upload</button>
	            <button type="button" class="delete">Delete</button>
	            <input type="checkbox" class="toggle">
	        </div>
	        <!-- The global file processing state -->
	        <br/>
	        <div style="border: 1px #cccccc solid ; overflow-y: scroll; height: 200px;">
	        	<span class="fileupload-process"></span>
		    	<!-- The global progress state -->
		        <div class="fileupload-progress fade" style="display:none">
		            <!-- The global progress bar -->
		            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
		            <!-- The extended global progress state -->
		            <div class="progress-extended">&nbsp;</div>
		        </div>
		        
		    	<table role="presentation"><tbody class="files"></tbody></table>
		    </div>
		    
	    </div>
	</form>
</div>

   		<div class="slide_area">
			<h2>Images</h2>
			<p class="pr_view">
				<c:if test="${studentEntity.filename == '' }">
  					<img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="140" height="100">
  				</c:if>
  				<c:if test="${studentEntity.filename != '' }">
					<img src="<%=request.getContextPath() %>/images/upload/school/<c:out value='${userEntity.user_id}'/>/<c:out value='${schoolEntity.logoname }'/>" alt="" width="140" height="100">
				</c:if>
			</p>
	      	<p><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="2" height="125"></p>
	  		<p class="slide_btn">
	  			<a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/icon_back.png" alt="" width="12" height="17"></a>
	  		</p>
	    	<ul>
	    		<li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="140" height="100"></a></li>
	      		<li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="140" height="100"></a></li>
	        	<li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="140" height="100"></a></li>
	        	<li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="140" height="100"></a></li>
	    	</ul>
  			<p class="slide_btn">
  				<a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/icon_next.png" alt="" width="11" height="17"></a>
  			</p>
	  	</div>
	  	<c:if test="${mode != 'view'}" >	
	  	<div style="text-align: right; padding-right: 50px;">
			<a href="javascript:doUpload();"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/btn_upload.png" alt="upload" width="206" height="35"></a>
		</div>
		<br/>
		</c:if>

<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress"></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled>Start</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel">Cancel</button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
            </p>
            {% if (file.error) { %}
                <div><span class="error">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            <button class="delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>Delete</button>
            <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<!-- blueimp Gallery script -->
<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-ui.js"></script>
<!-- The File Upload jQuery UI plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-jquery-ui.js"></script>
<!-- The main application script -->
<script src="<%=request.getContextPath() %>/fileupload2/js/main.js"></script>
