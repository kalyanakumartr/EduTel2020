<div id="kt_header" class="header header-fixed">
	<!--begin::Container-->
	<div class="container-fluid d-flex align-items-stretch justify-content-between">
		<div class="topbar">
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
					<h5 class="text-dark font-weight-bold my-1 mr-5">Tutor Videos View</h5>
					<!--end::Page Title-->
					<!--begin::Breadcrumb-->
					<ul class="breadcrumb breadcrumb-transparent breadcrumb-dot font-weight-bold p-0 my-2 font-size-sm">
						<li class="breadcrumb-item"><a href="" class="text-muted">Offline Videos</a></li>
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
								<h3 class="card-label">Zoom | Teams | Skype Offline Videos</h3>
							</div>
						</div>
						<!--begin::Form-->
						<div class="card-body">
							<div class="mb-7">
								<div class="row align-items-center">
									<div class="col-lg-9 col-xl-8">
										<div class="row align-items-center">
											<ul class="nav nav-tabs nav-tabs-line">
												<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#kt_tab_pane_1">Maths</a></li>
												<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#kt_tab_pane_2">Physics</a></li>
												<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#kt_tab_pane_3">Chemistry</a></li>
												<c:if test="${subjects!='C' || subjects=='A'}">
													<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
															Biology </a>
														<div class="dropdown-menu">
															<a class="dropdown-item" data-toggle="tab" href="#kt_tab_pane_4">Botony</a> <a class="dropdown-item" data-toggle="tab" href="#kt_tab_pane_5">Zoology</a>
														</div></li>
												</c:if>
												<c:if test="${subjects=='C' || subjects=='A'}">
													<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#kt_tab_pane_6">Computer</a></li>
												</c:if>
											</ul>
											<div class="tab-content mt-5" id="myTabContent">
												<div class="tab-pane fade show active" id="kt_tab_pane_1" role="tabpanel" aria-labelledby="kt_tab_pane_2">
													<c:forEach items="${videoList}" var="video">
														<c:if test="${video.subject=='Maths'}">
															<ul class="navi navi-hover">
																<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span
																		class="navi-icon"><i class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
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
																<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span
																		class="navi-icon"><i class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
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
																<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span
																		class="navi-icon"><i class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
																</a></li>
																<li class="navi navi-separator my-3"></li>
															</ul>
														</c:if>
													</c:forEach>
												</div>
												<c:if test="${subjects!='C' || subjects=='A'}">
													<div class="tab-pane fade" id="kt_tab_pane_4" role="tabpanel" aria-labelledby="kt_tab_pane_4">
														<c:forEach items="${videoList}" var="video">
															<c:if test="${video.subject=='Botony'}">
																<ul class="navi navi-hover">
																	<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span
																			class="navi-icon"><i class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
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
																	<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span
																			class="navi-icon"><i class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
																	</a></li>
																	<li class="navi navi-separator my-3"></li>
																</ul>
															</c:if>
														</c:forEach>
													</div>
												</c:if>
												<c:if test="${subjects=='C' || subjects=='A'}">
													<div class="tab-pane fade" id="kt_tab_pane_6" role="tabpanel" aria-labelledby="kt_tab_pane_6">
														<c:forEach items="${videoList}" var="video">
															<c:if test="${video.subject=='ComputerScience'}">
																<ul class="navi navi-hover">
																	<li class="navi-item"><a class="navi-link" href="javascript:openVideo('${video.videoId}','0','${video.displayName}','${video.subject}')"> <span
																			class="navi-icon"><i class="fas fa-video text-danger"></i></span> <span class="navi-text">${video.displayName}</span><span class="navi-label"></span>
																	</a></li>
																	<li class="navi navi-separator my-3"></li>
																</ul>
															</c:if>
														</c:forEach>
													</div>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--end::Card-->
				</div>
				<!--end::Row-->
			</div>
			<!--end::Container-->
		</div>
		<!--end::Entry-->
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