// Class definition
var KTFormControls = function() {
	// Private functions
	var _initVideoUpload = function() {
		FormValidation
				.formValidation(
						document.getElementById('mediaForm'),
						{
							fields : {
								"video.tutorName" : {
									validators : {
										notEmpty : {
											message : 'Tutor Name is required'
										}
									}
								},
								"video.displayName" : {
									validators : {
										notEmpty : {
											message : 'Display Name is required'
										}
									}
								},
								"video.subject" : {
									validators : {
										notEmpty : {
											message : 'Subject is required'
										}
									}
								},
								"video.description" : {
									validators : {
										notEmpty : {
											message : 'Please enter description about videos'
										},
										stringLength : {
											min : 50,
											max : 500,
											message : 'Please enter a description within text length range 50 and 500'
										}
									}
								},
							},

							plugins : { // Learn more:
								trigger: new FormValidation.plugins.Trigger(),
								// Validate fields when clicking the Submit
								// button
								submitButton: new FormValidation.plugins.SubmitButton(),
			            		// Submit the form when all fields are valid
			            		//defaultSubmit: new FormValidation.plugins.DefaultSubmit(),
								// Bootstrap Framework Integration
								bootstrap: new FormValidation.plugins.Bootstrap({
									eleInvalidClass: '',
									eleValidClass: '',
								})
							}
						})
						.on('core.form.valid', function() {
							
							if(Object.keys(selectedFileList).length === 0 && selectedFileList.constructor === Object)
							{
								$("#file-select").show();
								return false;
							}
							else
								$("#file-select").hide();
							
							var i = 0;
							var mediaFormData = {};
							var videoData = {};
							var uploadedFiles = [];

							$.each(selectedFileList, function(key, value) {
								uploadedFiles[i++] = value;
							});

							videoData["videoId"] = $("#videoId").val();
							videoData["tutorName"] = $("#tutorName").val();
							videoData["displayName"] = $("#displayName").val();
							videoData["subject"] = $("#subject").val();
							videoData["description"] = $("#description").val();
							mediaFormData["video"] = videoData;
							mediaFormData["uploadedFiles"] = uploadedFiles;
							mediaFormData["random"] = $("#random").val();

							var formData = new FormData();
							formData.append('mediaForm', JSON.stringify(mediaFormData));
							
							document.body.scrollTop = 0; // For Safari
							document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
							//alert(localStorage.getItem("accessToken"));
							  
							$.ajax({
								type : "POST",
								enctype : 'multipart/form-data',
								url : root + "/addVideo",
								processData : false, // prevent jQuery from
														// automatically
														// transforming the data
														// into a query string
								headers : {
									"Authorization" : 'Bearer ' + localStorage.getItem("accessToken")
								},
								contentType : false,
								cache : false,
								timeout : 600000,
								data : formData,
								success : function(response) {
									$("#alertSuccess").fadeIn(2000);
									$("#videoId").val("").trigger('change'); 
									$("#tutorName").val("");
									$("#displayName").val("");
									$("#subject").val("");
									$("#description").val("");
									KTUppy.init();
									setTimeout(() => {
										$("#alertSuccess").fadeOut(2000);
										
									}, 5000);
								},
								error : function(response) {
									$("#alertFailure").fadeIn(2000);
									$("#videoId").val("").trigger('change'); 
									KTUppy.init();
									setTimeout(() => {
										$("#alertFailure").fadeOut(2000);
										
									}, 10000);
								}
							});
						});
	}

	return {
		// public functions
		init : function() {
			_initVideoUpload();
		}
	};
}();

jQuery(document).ready(function() {
	KTFormControls.init();
});
