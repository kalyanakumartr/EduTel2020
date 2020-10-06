<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					This Panel which is used to upload EduTel Tutor Videos. <br />End user can view the videos online. Please upload only video files.
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
					<h5 class="text-dark font-weight-bold my-1 mr-5">Tutor Videos</h5>
					<!--end::Page Title-->
					<!--begin::Breadcrumb-->
					<ul class="breadcrumb breadcrumb-transparent breadcrumb-dot font-weight-bold p-0 my-2 font-size-sm">
						<li class="breadcrumb-item"><a href="" class="text-muted">Video Upload Wizard</a></li>
						<li class="breadcrumb-item"><a href="" class="text-muted">Add Videos</a></li>
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
								<h3 class="card-label">Zoom | Teams | Skype Upload</h3>
							</div>
						</div>
						<!--begin::Form-->
						<form:form method="POST" class="form fv-plugins-bootstrap fv-plugins-framework" enctype="multipart/form-data" id="mediaForm" name="mediaForm" modelAttribute="mediaForm"
							novalidate="novalidate">
							<div class="card-body">
								<div id="alertSuccess" class="alert alert-custom alert-success show" role="alert" style="display: none;">
									<div class="alert-icon">
										<i class="flaticon-warning"></i>
									</div>
									<div class="alert-text">Video Added successfully</div>
									<div class="alert-close">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true"><i class="ki ki-close"></i></span>
										</button>
									</div>
								</div>
								<div id="alertFailure" class="alert alert-custom alert-danger show" role="alert" style="display: none;">
									<div class="alert-icon">
										<i class="flaticon-warning"></i>
									</div>
									<div class="alert-text">Unable to submit your request.Please try again later.</div>
									<div class="alert-close">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true"><i class="ki ki-close"></i></span>
										</button>
									</div>
								</div>
								<div class="form-group row fv-plugins-icon-container">
									<label class="col-lg-3 col-form-label text-lg-right">Tutor Name:</label>
									<div class="col-lg-6">
										<form:select class="form-control" id="tutorName" path="video.tutorName">
											<option value="">Select Tutor Name...</option>
											<form:options items="${tutorList}" />
										</form:select>
										<span class="form-text text-muted">Please select tutor name</span>
									</div>
								</div>
								<div class="form-group row fv-plugins-icon-container">
									<label class="col-lg-3 col-form-label text-lg-right">Display Name:</label>
									<div class="col-lg-6">
										<form:input type="text" class="form-control" placeholder="Enter Display name" id="displayName" path="video.displayName" />
										<span class="form-text text-muted">Please enter video display name</span>
									</div>
								</div>
								<div class="form-group row fv-plugins-icon-container">
									<label class="col-lg-3 col-form-label text-lg-right">Subject:</label>
									<div class="col-lg-6">
										<form:select class="form-control" id="subject" path="video.subject">
											<option value="">Select Subject...</option>
											<option value="Maths">Maths</option>
											<option value="Physics">Physics</option>
											<option value="Chemistry">Chemistry</option>
											<option value="Botony">Botony</option>
											<option value="Zoology">Zoology</option>
											<option value="ComputerScience">Computer Science</option>
										</form:select>
										<span class="form-text text-muted">Please select video subject</span>
									</div>
								</div>
								<div class="form-group row fv-plugins-icon-container">
									<label class="col-lg-3 col-form-label text-lg-right">Description:</label>
									<div class="col-lg-6">
										<form:textarea class="form-control" id="description" path="video.description" rows="3" placeholder="Please enter description about the videos"></form:textarea>
										<span class="form-text text-muted">Provide information about the tutors videos, so that end user can explore it.</span>
									</div>
								</div>
								<div class="form-group row fv-plugins-icon-container">
									<label class="col-lg-3 col-form-label text-lg-right">Upload File:</label>
									<div class="col-lg-6">
										<div class="uppy" id="kt_uppy_5">
											<div class="uppy-wrapper"></div>
											<div class="uppy-list"></div>
											<div class="uppy-status"></div>
											<div class="uppy-informer uppy-informer-min"></div>
										</div>
										<span class="form-text text-muted">Max file size is 50MB and max number of files is 10.</span>
										<div id="file-select" class="fv-plugins-message-container" style="display: none;">
											<div class="fv-help-block">Please select a video file(s).</div>
										</div>
									</div>
								</div>
								<div class="card-footer">
									<div class="row">
										<div class="col-lg-3"></div>
										<div class="col-lg-6">
											<input id="uppyUploadDomainURL" type="hidden" value="${pageContext.request.contextPath}" />
											<form:hidden path="random" />
											<form:hidden path="video.videoId" id="videoId" />
											<button id="submitButton" name="submitButton" class="btn btn-primary">Submit</button>
											<button id="cancelBtn" class="btn btn-secondary">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						</form:form>
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
<!--end::Content-->
<!--begin::Page Scripts(used by this page)-->
<script src="${pageContext.request.contextPath}/assets/plugins/custom/uppy/uppy.bundle.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/pages/crud/file-upload/uppy.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/edutel/video-form-controls.js"></script>
<script>
	$("#cancelBtn").click(function(e) {
		e.preventDefault();
		window.location.href = "${pageContext.request.contextPath}/preSearchVideo/" + localStorage.getItem("tokenBinder");
	});
</script>
<!--end::Page Scripts-->
