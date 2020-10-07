<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
Template Name: Metronic - Bootstrap 4 HTML, React, Angular 9 & VueJS Admin Dashboard Theme
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: https://1.envato.market/EA4JP
Renew Support: https://1.envato.market/EA4JP
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<html lang="en">
<!--begin::Head-->
<head>
<base href="../../">
<meta charset="utf-8" />
<title>EduTel Videos Upload | EduTel Academy</title>
<meta name="description" content="Uppy file upload plugin" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="canonical" href="https://keenthemes.com/metronic" />
<!--begin::Fonts-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" />
<!--end::Fonts-->
<script>
	var root = "${pageContext.request.contextPath}";
</script>
<!--begin::Page Custom Styles(used by this page)-->
<!--begin::Global Theme Styles(used by all pages)-->
<link href="${pageContext.request.contextPath}/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/plugins/custom/prismjs/prismjs.bundle.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/style.bundle.css" rel="stylesheet" type="text/css" />
<!--end::Global Theme Styles-->
<!--begin::Layout Themes(used by all pages)-->
<link href="${pageContext.request.contextPath}/assets/css/themes/layout/header/base/light.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/themes/layout/header/menu/light.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/themes/layout/brand/dark.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/themes/layout/aside/dark.css" rel="stylesheet" type="text/css" />
<!--end::Layout Themes-->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/media/logos/favicon.ico" />
<style>
video::-internal-media-controls-download-button {
	display: none;
}

video::-webkit-media-controls-enclosure {
	overflow: hidden;
}

video::-webkit-media-controls-panel {
	width: calc(100% + 30px); /* Adjust as needed */
}

video {
  max-width: 100%;
  height: auto;
}
</style>
</head>
<body id="kt_body">
	<div class="d-flex flex-column-fluid">
		<c:set var="first" value="true" scope="page" />
		<video id="tutorVideoId" controls autoplay="autoplay" controlsList="nodownload" >
			<c:forEach items="${videoList}" var="video">
				<c:if test="${first}">
					<source class="active" src="${pageContext.request.contextPath}/streamVideo/${video.uploadFileFolderURL}/${video.uploadFileName}" type="video/mp4" class>
				</c:if>
				<c:if test="${!first}">
					<source src="${pageContext.request.contextPath}/streamVideo/${video.uploadFileFolderURL}/${video.uploadFileName}" type="video/mp4">
				</c:if>
				<c:set var="first" value="false" scope="page"/>
			</c:forEach>
		</video>
	</div>
	<!--begin::Global Config(global config for global JS scripts)-->
	<script>
		var KTAppSettings = {
			"breakpoints" : {
				"sm" : 576,
				"md" : 768,
				"lg" : 992,
				"xl" : 1200,
				"xxl" : 1400
			},
			"colors" : {
				"theme" : {
					"base" : {
						"white" : "#ffffff",
						"primary" : "#3699FF",
						"secondary" : "#E5EAEE",
						"success" : "#1BC5BD",
						"info" : "#8950FC",
						"warning" : "#FFA800",
						"danger" : "#F64E60",
						"light" : "#E4E6EF",
						"dark" : "#181C32"
					},
					"light" : {
						"white" : "#ffffff",
						"primary" : "#E1F0FF",
						"secondary" : "#EBEDF3",
						"success" : "#C9F7F5",
						"info" : "#EEE5FF",
						"warning" : "#FFF4DE",
						"danger" : "#FFE2E5",
						"light" : "#F3F6F9",
						"dark" : "#D6D6E0"
					},
					"inverse" : {
						"white" : "#ffffff",
						"primary" : "#ffffff",
						"secondary" : "#3F4254",
						"success" : "#ffffff",
						"info" : "#ffffff",
						"warning" : "#ffffff",
						"danger" : "#ffffff",
						"light" : "#464E5F",
						"dark" : "#ffffff"
					}
				},
				"gray" : {
					"gray-100" : "#F3F6F9",
					"gray-200" : "#EBEDF3",
					"gray-300" : "#E4E6EF",
					"gray-400" : "#D1D3E0",
					"gray-500" : "#B5B5C3",
					"gray-600" : "#7E8299",
					"gray-700" : "#5E6278",
					"gray-800" : "#3F4254",
					"gray-900" : "#181C32"
				}
			},
			"font-family" : "Poppins"
		};

		var myvid = document.getElementById('tutorVideoId');

		myvid.addEventListener('ended', function(e) {
		  // get the active source and the next video source.
		  // I set it so if there's no next, it loops to the first one
		  var activesource = document.querySelector("#tutorVideoId source.active");
		  var nextsource = document.querySelector("#tutorVideoId source.active + source") || document.querySelector("#tutorVideoId source:first-child");
		  
		  // deactivate current source, and activate next one
		  activesource.className = "";
		  nextsource.className = "active";
		  
		  // update the video source and play
		  myvid.src = nextsource.src;
		  myvid.play();
		});

	</script>
	<!--end::Global Config-->
	<!--begin::Global Theme Bundle(used by all pages)-->
	<script src="${pageContext.request.contextPath}/assets/plugins/global/plugins.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/plugins/custom/prismjs/prismjs.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>
	<script>
		$('#videoModelId').on("hide.bs.modal", function() {
			$('#videoBodyId').html("<p>Your Tutor Video Displayed Over Here...</p>");
		});
	</script>
	<!--end::Global Theme Bundle-->
</body>
<!--end::Body-->
</html>