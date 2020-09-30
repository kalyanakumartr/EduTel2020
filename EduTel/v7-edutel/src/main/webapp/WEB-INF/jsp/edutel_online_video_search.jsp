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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/media/logos/favicon.ico" />
<style>
.expand-100-percent {
	width: 100%
}
</style>
</head>
<!--end::Head-->
<!--begin::Body-->
<body id="kt_body" class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed aside-minimize-hoverable page-loading">
	<!--begin::Main-->
	<!--begin::Header Mobile-->
	<div id="kt_header_mobile" class="header-mobile align-items-center header-mobile-fixed">
		<!--begin::Logo-->
		<a href="index.html"> <img alt="Logo" src="${pageContext.request.contextPath}/assets/media/logos/logo-light.png" />
		</a>
		<!--end::Logo-->
		<!--begin::Toolbar-->
		<div class="d-flex align-items-center">
			<!--begin::Aside Mobile Toggle-->
			<button class="btn p-0 burger-icon burger-icon-left" id="kt_aside_mobile_toggle">
				<span></span>
			</button>
			<!--end::Aside Mobile Toggle-->
			<!--begin::Header Menu Mobile Toggle-->
			<button class="btn p-0 burger-icon ml-4" id="kt_header_mobile_toggle">
				<span></span>
			</button>
			<!--end::Header Menu Mobile Toggle-->
			<!--begin::Topbar Mobile Toggle-->
			<button class="btn btn-hover-text-primary p-0 ml-2" id="kt_header_mobile_topbar_toggle">
				<span class="svg-icon svg-icon-xl"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/General/User.svg--> <svg xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
							<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
								<polygon points="0 0 24 0 24 24 0 24" />
								<path d="M12,11 C9.790861,11 8,9.209139 8,7 C8,4.790861 9.790861,3 12,3 C14.209139,3 16,4.790861 16,7 C16,9.209139 14.209139,11 12,11 Z" fill="#000000" fill-rule="nonzero" opacity="0.3" />
								<path
							d="M3.00065168,20.1992055 C3.38825852,15.4265159 7.26191235,13 11.9833413,13 C16.7712164,13 20.7048837,15.2931929 20.9979143,20.2 C21.0095879,20.3954741 20.9979143,21 20.2466999,21 C16.541124,21 11.0347247,21 3.72750223,21 C3.47671215,21 2.97953825,20.45918 3.00065168,20.1992055 Z"
							fill="#000000" fill-rule="nonzero" />
							</g>
						</svg> <!--end::Svg Icon-->
				</span>
			</button>
			<!--end::Topbar Mobile Toggle-->
		</div>
		<!--end::Toolbar-->
	</div>
	<!--end::Header Mobile-->
	<div class="d-flex flex-column flex-root">
		<!--begin::Page-->
		<div class="d-flex flex-row flex-column-fluid page">
			<!--begin::Aside-->
			<div class="aside aside-left aside-fixed d-flex flex-column flex-row-auto" id="kt_aside">
				<!--begin::Brand-->
				<div class="brand flex-column-auto" id="kt_brand">
					<!--begin::Logo-->
					<a href="javascript:void" class="brand-logo"> <img alt="Logo" src="${pageContext.request.contextPath}/assets/media/logos/logo-light.png" />
					</a>
					<!--end::Logo-->
					<!--begin::Toggle-->
					<button class="brand-toggle btn btn-sm px-0" id="kt_aside_toggle">
						<span class="svg-icon svg-icon svg-icon-xl"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Navigation/Angle-double-left.svg--> <svg
								xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
									<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
										<polygon points="0 0 24 0 24 24 0 24" />
										<path
									d="M5.29288961,6.70710318 C4.90236532,6.31657888 4.90236532,5.68341391 5.29288961,5.29288961 C5.68341391,4.90236532 6.31657888,4.90236532 6.70710318,5.29288961 L12.7071032,11.2928896 C13.0856821,11.6714686 13.0989277,12.281055 12.7371505,12.675721 L7.23715054,18.675721 C6.86395813,19.08284 6.23139076,19.1103429 5.82427177,18.7371505 C5.41715278,18.3639581 5.38964985,17.7313908 5.76284226,17.3242718 L10.6158586,12.0300721 L5.29288961,6.70710318 Z"
									fill="#000000" fill-rule="nonzero" transform="translate(8.999997, 11.999999) scale(-1, 1) translate(-8.999997, -11.999999)" />
										<path
									d="M10.7071009,15.7071068 C10.3165766,16.0976311 9.68341162,16.0976311 9.29288733,15.7071068 C8.90236304,15.3165825 8.90236304,14.6834175 9.29288733,14.2928932 L15.2928873,8.29289322 C15.6714663,7.91431428 16.2810527,7.90106866 16.6757187,8.26284586 L22.6757187,13.7628459 C23.0828377,14.1360383 23.1103407,14.7686056 22.7371482,15.1757246 C22.3639558,15.5828436 21.7313885,15.6103465 21.3242695,15.2371541 L16.0300699,10.3841378 L10.7071009,15.7071068 Z"
									fill="#000000" fill-rule="nonzero" opacity="0.3" transform="translate(15.999997, 11.999999) scale(-1, 1) rotate(-270.000000) translate(-15.999997, -11.999999)" />
									</g>
								</svg> <!--end::Svg Icon-->
						</span>
					</button>
					<!--end::Toolbar-->
				</div>
				<!--end::Brand-->
				<!--begin::Aside Menu-->
				<!--begin::Aside Menu-->
				<div class="aside-menu-wrapper flex-column-fluid" id="kt_aside_menu_wrapper">
					<!--begin::Menu Container-->
					<div id="kt_aside_menu" class="aside-menu my-4" data-menu-vertical="1" data-menu-scroll="1" data-menu-dropdown-timeout="500">
						<!--begin::Menu Nav-->
						<ul class="menu-nav">
							<li class="menu-item" aria-haspopup="true"><a href="${pageContext.request.contextPath}/preAddVideo" class="menu-link"> <span class="svg-icon menu-icon"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Design/Layers.svg-->
										<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
								<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
									<polygon points="0 0 24 0 24 24 0 24" />
									<path
												d="M12.9336061,16.072447 L19.36,10.9564761 L19.5181585,10.8312381 C20.1676248,10.3169571 20.2772143,9.3735535 19.7629333,8.72408713 C19.6917232,8.63415859 19.6104327,8.55269514 19.5206557,8.48129411 L12.9336854,3.24257445 C12.3871201,2.80788259 11.6128799,2.80788259 11.0663146,3.24257445 L4.47482784,8.48488609 C3.82645598,9.00054628 3.71887192,9.94418071 4.23453211,10.5925526 C4.30500305,10.6811601 4.38527899,10.7615046 4.47382636,10.8320511 L4.63,10.9564761 L11.0659024,16.0730648 C11.6126744,16.5077525 12.3871218,16.5074963 12.9336061,16.072447 Z"
												fill="#000000" fill-rule="nonzero" />
									<path
												d="M11.0563554,18.6706981 L5.33593024,14.122919 C4.94553994,13.8125559 4.37746707,13.8774308 4.06710397,14.2678211 C4.06471678,14.2708238 4.06234874,14.2738418 4.06,14.2768747 L4.06,14.2768747 C3.75257288,14.6738539 3.82516916,15.244888 4.22214834,15.5523151 C4.22358765,15.5534297 4.2250303,15.55454 4.22647627,15.555646 L11.0872776,20.8031356 C11.6250734,21.2144692 12.371757,21.2145375 12.909628,20.8033023 L19.7677785,15.559828 C20.1693192,15.2528257 20.2459576,14.6784381 19.9389553,14.2768974 C19.9376429,14.2751809 19.9363245,14.2734691 19.935,14.2717619 L19.935,14.2717619 C19.6266937,13.8743807 19.0546209,13.8021712 18.6572397,14.1104775 C18.654352,14.112718 18.6514778,14.1149757 18.6486172,14.1172508 L12.9235044,18.6705218 C12.377022,19.1051477 11.6029199,19.1052208 11.0563554,18.6706981 Z"
												fill="#000000" opacity="0.3" />
								</g>
							</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Videos Upload Wizard</span>
							</a></li>
							<li class="menu-item" aria-haspopup="true"><a href="${pageContext.request.contextPath}/preSearchVideo" class="menu-link"> <span class="svg-icon menu-icon"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Design/Layers.svg-->
										<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
								<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
									<polygon points="0 0 24 0 24 24 0 24" />
									<path
												d="M12.9336061,16.072447 L19.36,10.9564761 L19.5181585,10.8312381 C20.1676248,10.3169571 20.2772143,9.3735535 19.7629333,8.72408713 C19.6917232,8.63415859 19.6104327,8.55269514 19.5206557,8.48129411 L12.9336854,3.24257445 C12.3871201,2.80788259 11.6128799,2.80788259 11.0663146,3.24257445 L4.47482784,8.48488609 C3.82645598,9.00054628 3.71887192,9.94418071 4.23453211,10.5925526 C4.30500305,10.6811601 4.38527899,10.7615046 4.47382636,10.8320511 L4.63,10.9564761 L11.0659024,16.0730648 C11.6126744,16.5077525 12.3871218,16.5074963 12.9336061,16.072447 Z"
												fill="#000000" fill-rule="nonzero" />
									<path
												d="M11.0563554,18.6706981 L5.33593024,14.122919 C4.94553994,13.8125559 4.37746707,13.8774308 4.06710397,14.2678211 C4.06471678,14.2708238 4.06234874,14.2738418 4.06,14.2768747 L4.06,14.2768747 C3.75257288,14.6738539 3.82516916,15.244888 4.22214834,15.5523151 C4.22358765,15.5534297 4.2250303,15.55454 4.22647627,15.555646 L11.0872776,20.8031356 C11.6250734,21.2144692 12.371757,21.2145375 12.909628,20.8033023 L19.7677785,15.559828 C20.1693192,15.2528257 20.2459576,14.6784381 19.9389553,14.2768974 C19.9376429,14.2751809 19.9363245,14.2734691 19.935,14.2717619 L19.935,14.2717619 C19.6266937,13.8743807 19.0546209,13.8021712 18.6572397,14.1104775 C18.654352,14.112718 18.6514778,14.1149757 18.6486172,14.1172508 L12.9235044,18.6705218 C12.377022,19.1051477 11.6029199,19.1052208 11.0563554,18.6706981 Z"
												fill="#000000" opacity="0.3" />
								</g>
							</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Videos Search</span>
							</a></li>
							<li class="menu-item" aria-haspopup="true"><a href="javascript:endUserVideo();" class="menu-link"> <span class="svg-icon menu-icon"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Design/Layers.svg-->
										<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
								<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
									<polygon points="0 0 24 0 24 24 0 24" />
									<path
												d="M12.9336061,16.072447 L19.36,10.9564761 L19.5181585,10.8312381 C20.1676248,10.3169571 20.2772143,9.3735535 19.7629333,8.72408713 C19.6917232,8.63415859 19.6104327,8.55269514 19.5206557,8.48129411 L12.9336854,3.24257445 C12.3871201,2.80788259 11.6128799,2.80788259 11.0663146,3.24257445 L4.47482784,8.48488609 C3.82645598,9.00054628 3.71887192,9.94418071 4.23453211,10.5925526 C4.30500305,10.6811601 4.38527899,10.7615046 4.47382636,10.8320511 L4.63,10.9564761 L11.0659024,16.0730648 C11.6126744,16.5077525 12.3871218,16.5074963 12.9336061,16.072447 Z"
												fill="#000000" fill-rule="nonzero" />
									<path
												d="M11.0563554,18.6706981 L5.33593024,14.122919 C4.94553994,13.8125559 4.37746707,13.8774308 4.06710397,14.2678211 C4.06471678,14.2708238 4.06234874,14.2738418 4.06,14.2768747 L4.06,14.2768747 C3.75257288,14.6738539 3.82516916,15.244888 4.22214834,15.5523151 C4.22358765,15.5534297 4.2250303,15.55454 4.22647627,15.555646 L11.0872776,20.8031356 C11.6250734,21.2144692 12.371757,21.2145375 12.909628,20.8033023 L19.7677785,15.559828 C20.1693192,15.2528257 20.2459576,14.6784381 19.9389553,14.2768974 C19.9376429,14.2751809 19.9363245,14.2734691 19.935,14.2717619 L19.935,14.2717619 C19.6266937,13.8743807 19.0546209,13.8021712 18.6572397,14.1104775 C18.654352,14.112718 18.6514778,14.1149757 18.6486172,14.1172508 L12.9235044,18.6705218 C12.377022,19.1051477 11.6029199,19.1052208 11.0563554,18.6706981 Z"
												fill="#000000" opacity="0.3" />
								</g>
							</svg> <!--end::Svg Icon-->
								</span> <span class="menu-text">Videos View</span>
							</a></li>
						</ul>
						<!--end::Menu Nav-->
					</div>
					<!--end::Menu Container-->
				</div>
				<!--end::Aside Menu-->
				<!--end::Menu Container-->
			</div>
			<!--end::Aside Menu-->
		</div>
		<!--end::Aside-->
		<!--begin::Wrapper-->
		<div class="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
			<!--begin::Header-->
			<div id="kt_header" class="header header-fixed">
				<!--begin::Container-->
				<div class="container-fluid d-flex align-items-stretch justify-content-between">
					<div class="topbar">
						<!--begin::Search-->
						<!--end::Search-->
						<!--begin::Notifications-->
						<!--end::Notifications-->
						<!--begin::Quick Actions-->
						<!--end::Quick Actions-->
						<!--begin::Cart-->
						<!--end::Cart-->
						<!--begin::Quick panel-->
						<!--end::Quick panel-->
						<!--begin::Chat-->
						<!--end::Chat-->
						<!--begin::Languages-->
						<!--end::Languages-->
						<!--begin::User-->
						<div class="topbar-item">
							<div class="alert-icon">
								<span class="svg-icon svg-icon-primary svg-icon-xl"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Tools/Compass.svg--> <svg
										xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
												<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
													<rect x="0" y="0" width="24" height="24" />
													<path
											d="M7.07744993,12.3040451 C7.72444571,13.0716094 8.54044565,13.6920474 9.46808594,14.1079953 L5,23 L4.5,18 L7.07744993,12.3040451 Z M14.5865511,14.2597864 C15.5319561,13.9019016 16.375416,13.3366121 17.0614026,12.6194459 L19.5,18 L19,23 L14.5865511,14.2597864 Z M12,3.55271368e-14 C12.8284271,3.53749572e-14 13.5,0.671572875 13.5,1.5 L13.5,4 L10.5,4 L10.5,1.5 C10.5,0.671572875 11.1715729,3.56793164e-14 12,3.55271368e-14 Z"
											fill="#000000" opacity="0.3" />
													<path
											d="M12,10 C13.1045695,10 14,9.1045695 14,8 C14,6.8954305 13.1045695,6 12,6 C10.8954305,6 10,6.8954305 10,8 C10,9.1045695 10.8954305,10 12,10 Z M12,13 C9.23857625,13 7,10.7614237 7,8 C7,5.23857625 9.23857625,3 12,3 C14.7614237,3 17,5.23857625 17,8 C17,10.7614237 14.7614237,13 12,13 Z"
											fill="#000000" fill-rule="nonzero" />
												</g>
											</svg> <!--end::Svg Icon-->
								</span>
							</div>
							<div class="alert-text">
								This Panel which is used to search EduTel Tutor Videos. <br />Administrator / Employee user can search the videos online to update or block or delete.
							</div>
						</div>
						<!--end::User-->
					</div>
					<!--end::Topbar-->
				</div>
				<!--end::Container-->
			</div>
			<!--end::Header-->
			<!--begin::Content-->
			<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
				<!--begin::Subheader-->
				<div class="subheader py-2 py-lg-6 subheader-solid" id="kt_subheader">
					<div class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
						<!--begin::Info-->
						<div class="d-flex align-items-center flex-wrap mr-1">
							<!--begin::Page Heading-->
							<div class="d-flex align-items-baseline flex-wrap mr-5">
								<!--begin::Page Title-->
								<h5 class="text-dark font-weight-bold my-1 mr-5">Tutor Videos Search</h5>
								<!--end::Page Title-->
								<!--begin::Breadcrumb-->
								<ul class="breadcrumb breadcrumb-transparent breadcrumb-dot font-weight-bold p-0 my-2 font-size-sm">
									<li class="breadcrumb-item"><a href="" class="text-muted">Video Search Wizard</a></li>
									<li class="breadcrumb-item"><a href="" class="text-muted">Search Videos</a></li>
								</ul>
								<!--end::Breadcrumb-->
							</div>
							<!--end::Page Heading-->
						</div>
						<!--end::Info-->
						<!--begin::Toolbar-->
						<!--end::Toolbar-->
					</div>
				</div>
				<!--end::Subheader-->
				<!--begin::Entry-->
				<div class="d-flex flex-column-fluid">
					<!--begin::Container-->
					<div class="container">
						<!--begin::Row-->
						<div class="row">
							<div class="col-lg-12">
								<!--begin::Card-->
								<div class="card card-custom card-stretch">
									<div class="card-header">
										<div class="card-title">
											<h3 class="card-label">Zoom | Teams | Skype Search</h3>
										</div>
										<div class="card-toolbar">
											<!--begin::Dropdown-->
											<!--end::Dropdown-->
											<!--begin::Button-->
											<a href="${pageContext.request.contextPath}/preAddVideo" class="btn btn-primary font-weight-bolder"> <span class="svg-icon svg-icon-md"> <!--begin::Svg Icon | path:assets/media/svg/icons/Design/Flatten.svg-->
													<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
													<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
														<rect x="0" y="0" width="24" height="24" />
														<circle fill="#000000" cx="9" cy="15" r="6" />
														<path
															d="M8.8012943,7.00241953 C9.83837775,5.20768121 11.7781543,4 14,4 C17.3137085,4 20,6.6862915 20,10 C20,12.2218457 18.7923188,14.1616223 16.9975805,15.1987057 C16.9991904,15.1326658 17,15.0664274 17,15 C17,10.581722 13.418278,7 9,7 C8.93357256,7 8.86733422,7.00080962 8.8012943,7.00241953 Z"
															fill="#000000" opacity="0.3" />
													</g>
												</svg> <!--end::Svg Icon-->
											</span>Upload New Videos
											</a>
											<!--end::Button-->
										</div>
									</div>
									<!--begin::Form-->
									<div class="card-body">
										<!--begin: Search Form-->
										<!--begin::Search Form-->
										<div class="mb-7">
											<div class="row align-items-center">
												<div class="col-lg-9 col-xl-8">
													<div class="row align-items-center">
														<div class="col-md-4 my-2 my-md-0">
															<div class="d-flex align-items-center">
																<label class="mr-3 mb-0 d-none d-md-block">Subject:</label> <select class="form-control" id="kt_datatable_search_subject">
																	<option value="">All Subjects</option>
																	<option value="Maths">Maths</option>
																	<option value="Physcis">Physics</option>
																	<option value="Chemistry">Chemistry</option>
																	<option value="Botony">Botony</option>
																	<option value="Zoology">Zoology</option>
																	<option value="ComputerScience">Computer Science</option>
																</select>
															</div>
														</div>
														<div class="col-md-4 my-2 my-md-0">
															<div class="d-flex align-items-center">
																<label class="mr-3 mb-0 d-none d-md-block">Tutor:</label> <select class="form-control" id="kt_datatable_search_tutor">
																	<option value="">All Tutors</option>
																	<c:forEach items="${tutorList}" var="tutorName">
																		<option value="<c:out value="${tutorName}"/>"><c:out value="${tutorName}" /></option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="col-md-4 my-2 my-md-0">
															<div class="input-icon">
																<input type="text" class="form-control" placeholder="Search..." id="kt_datatable_search_query" /> <span> <i class="flaticon2-search-1 text-muted"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!--end::Search Form-->
										<!--end: Search Form-->
										<!--begin: Datatable-->
										<div class="datatable datatable-bordered datatable-head-custom" id="kt_datatable">
											</divs>
											<!--end: Datatable-->
										</div>
										<!--end::Form-->
										
									</div>
									<!--end::Card-->
								</div>
								<!--end::Card-->
							</div>
							<!--end::Row-->
						</div>
						<!--end::Container-->
					</div>
					<!--end::Entry-->
				</div>
				<div id="viewVideoDivId">This is a View Mode</div>
				<!--end::Content-->
				<!--begin::Footer-->
				<div class="footer bg-white py-4 d-flex flex-lg-column" id="kt_footer">
					<!--begin::Container-->
					<div class="container-fluid d-flex flex-column flex-md-row align-items-center justify-content-between">
						<!--begin::Copyright-->
						<div class="text-dark order-2 order-md-1">
							<span class="text-muted font-weight-bold mr-2">2020&nbsp;©</span> <a href="javascript:void" target="_blank" class="text-dark-75 text-hover-primary">EduTel Academy powered by HatchBird.</a>
						</div>
						<!--end::Copyright-->
						<!--begin::Nav-->
						<div class="nav nav-dark">
							<a href="javascript:void" target="_blank" class="nav-link pl-0 pr-0">Contact Us</a>
						</div>
						<!--end::Nav-->
					</div>
					<!--end::Container-->
				</div>
				<!--end::Footer-->
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
							<!--div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							</div -->
						</div>
					</div>
				</div>
			</div>
			<!--end::Wrapper-->
		</div>
		<!--end::Page-->
		<!--end::Main-->
		<!-- begin::User Panel-->
		<!-- end::User Panel-->
		<!--begin::Quick Cart-->
		<!--end::Quick Cart-->
		<!--begin::Quick Panel-->
		<!--end::Quick Panel-->
		<!--begin::Chat Panel-->
		<!--end::Chat Panel-->
		<!--begin::Scrolltop-->
		<div id="kt_scrolltop" class="scrolltop">
			<span class="svg-icon"> <!--begin::Svg Icon | path:${pageContext.request.contextPath}/assets/media/svg/icons/Navigation/Up-2.svg--> <svg xmlns="http://www.w3.org/2000/svg"
					xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
					<g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
						<polygon points="0 0 24 0 24 24 0 24" />
						<rect fill="#000000" opacity="0.3" x="11" y="10" width="2" height="10" rx="1" />
						<path
						d="M6.70710678,12.7071068 C6.31658249,13.0976311 5.68341751,13.0976311 5.29289322,12.7071068 C4.90236893,12.3165825 4.90236893,11.6834175 5.29289322,11.2928932 L11.2928932,5.29289322 C11.6714722,4.91431428 12.2810586,4.90106866 12.6757246,5.26284586 L18.6757246,10.7628459 C19.0828436,11.1360383 19.1103465,11.7686056 18.7371541,12.1757246 C18.3639617,12.5828436 17.7313944,12.6103465 17.3242754,12.2371541 L12.0300757,7.38413782 L6.70710678,12.7071068 Z"
						fill="#000000" fill-rule="nonzero" />
					</g>
				</svg> <!--end::Svg Icon-->
			</span>
		</div>
		<!--end::Scrolltop-->
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
		</script>
		<!--end::Global Config-->
		<!--begin::Global Theme Bundle(used by all pages)-->
		<script src="${pageContext.request.contextPath}/assets/js/temp_token.js"></script>
		<script src="${pageContext.request.contextPath}/assets/plugins/global/plugins.bundle.js"></script>
		<script src="${pageContext.request.contextPath}/assets/plugins/custom/prismjs/prismjs.bundle.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>
		<!--end::Global Theme Bundle-->
		<!--begin::Page Scripts(used by this page)-->
		<script src="${pageContext.request.contextPath}/assets/js/pages/crud/ktdatatable/base/data-ajax-edutel-video.js"></script>
		<!--end::Page Scripts-->
</body>
<!--end::Body-->
</html>