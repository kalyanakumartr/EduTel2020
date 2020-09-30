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
<!--begin::Page Custom Styles(used by this page)-->
<script>
	var root = "${pageContext.request.contextPath}";
</script>
<link href="${pageContext.request.contextPath}/assets/plugins/custom/uppy/uppy.bundle.css" rel="stylesheet" type="text/css" />
<!--end::Page Custom Styles-->
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
</head>
<!--end::Head-->
<!--begin::Body-->
<body id="kt_body">
	<!--begin::Main-->
	<!--begin::Header Mobile-->
	<!--end::Header Mobile-->
	<div class="d-flex flex-column flex-root">
		<div class="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
			<!--begin::Content-->
			<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
				<!--begin::Entry-->
				<div class="d-flex flex-column-fluid">
					<!--begin::Container-->
					<div class="container">
						<div class="card card-custom gutter-b">
							<div class="card-body">
								<ul class="nav nav-tabs nav-tabs-line">
									<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#kt_tab_pane_1">Maths</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#kt_tab_pane_2">Physics</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#kt_tab_pane_3">Chemistry</a></li>
									<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> Biology </a>
										<div class="dropdown-menu">
											<a class="dropdown-item" data-toggle="tab" href="#kt_tab_pane_4">Botony</a> <a class="dropdown-item" data-toggle="tab" href="#kt_tab_pane_5">Zoology</a>
										</div></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#kt_tab_pane_6">Computer</a></li>
								</ul>
								<div class="tab-content mt-5" id="myTabContent">
									<div class="tab-pane fade show active" id="kt_tab_pane_1" role="tabpanel" aria-labelledby="kt_tab_pane_2">
										<c:forEach items="${videoList}" var="video">
											<c:if test="${video.subject=='Maths'}">
												<ul class="navi navi-hover">
													<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span class="navi-icon"><i
																class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
													</a></li>
													<li class="navi navi-separator my-3"></li>
												</ul>
											</c:if>
										</c:forEach>
									</div>
									<div class="tab-pane fade" id="kt_tab_pane_2" role="tabpanel" aria-labelledby="kt_tab_pane_2">
										<c:forEach items="${videoList}" var="video">
											<c:if test="${video.subject=='Physics'}">
												<ul class="navi navi-hover">
													<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span class="navi-icon"><i
																class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
													</a></li>
													<li class="navi navi-separator my-3"></li>
												</ul>
											</c:if>
										</c:forEach>
									</div>
									<div class="tab-pane fade" id="kt_tab_pane_3" role="tabpanel" aria-labelledby="kt_tab_pane_3">
										<c:forEach items="${videoList}" var="video">
											<c:if test="${video.subject=='Chemistry'}">
												<ul class="navi navi-hover">
													<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span class="navi-icon"><i
																class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
													</a></li>
													<li class="navi navi-separator my-3"></li>
												</ul>
											</c:if>
										</c:forEach>
									</div>
									<div class="tab-pane fade" id="kt_tab_pane_4" role="tabpanel" aria-labelledby="kt_tab_pane_4">
										<c:forEach items="${videoList}" var="video">
											<c:if test="${video.subject=='Botony'}">
												<ul class="navi navi-hover">
													<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span class="navi-icon"><i
																class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
													</a></li>
													<li class="navi navi-separator my-3"></li>
												</ul>
											</c:if>
										</c:forEach>
									</div>
									<div class="tab-pane fade" id="kt_tab_pane_5" role="tabpanel" aria-labelledby="kt_tab_pane_5">
										<c:forEach items="${videoList}" var="video">
											<c:if test="${video.subject=='Zoology'}">
												<ul class="navi navi-hover">
													<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span class="navi-icon"><i
																class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
													</a></li>
													<li class="navi navi-separator my-3"></li>
												</ul>
											</c:if>
										</c:forEach>
									</div>
									<div class="tab-pane fade" id="kt_tab_pane_6" role="tabpanel" aria-labelledby="kt_tab_pane_6">
										<c:forEach items="${videoList}" var="video">
											<c:if test="${video.subject=='ComputerScience'}">
												<ul class="navi navi-hover">
													<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span class="navi-icon"><i
																class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
													</a></li>
													<li class="navi navi-separator my-3"></li>
												</ul>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="videoModelId" tabindex="-1" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="videoTitleId"></h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<i aria-hidden="true" class="ki ki-close"></i>
								</button>
							</div>
							<div class="modal-body" id="videoBodyId">
								<p>Your Tutor Video Displayed Over Here...</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--end::Wrapper-->
		</div>
	</div>
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

		function openVideo(videoId, attachmentId, videoName, videoSubject) {
			// AJAX request
			$.ajax({
				url : root + '/viewVideo/' + videoId + "/" + attachmentId,
				type : 'post',
				headers : {
					"Authorization" : 'Bearer ' + access_token
				},
				contentType : "application/json",
				cache : false,
				timeout : 600000,
				success : function(response) {
					// Add response in Modal body
					$('.modal-body').html(response);

					// Display Modal
					$('#videoTitleId').text(videoSubject + ' : ' + videoName);
					$('#videoModelId').modal({
						show : true
					});
				}
			});
		}
	</script>
	<script src="${pageContext.request.contextPath}/assets/js/temp_token.js"></script>
	<script src="${pageContext.request.contextPath}/assets/plugins/global/plugins.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/plugins/custom/prismjs/prismjs.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>
</body>
<!--end::Body-->
</html>