"use strict";
// Class definition
var KTDatatableVideo = function() {
    // Private functions

    // basic videoFn
    var videoFn = function() {

        var videoDataTable = $('#kt_datatable').KTDatatable({
            // datasource definition
            data: {
                type: 'remote',
                source: {
                    read: {
                        url : root + '/searchVideo',
                        headers: { "Authorization": 'Bearer ' + access_token },
                        map: function(raw) {
                            // sample data mapping
                            var dataSet = raw;
                            if (typeof raw.data !== 'undefined') {
                                dataSet = raw.data;
                            }
                            return dataSet;
                        },
                    },
                },
                pageSize: 10,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true,
            },

            // layout definition
            layout: {
                scroll: false,
                footer: false,
            },

            // column sorting
            sortable: true,

            pagination: true,

            search: {
                input: $('#kt_datatable_search_query'),
                key: 'generalSearch'
            },
            columnDefs: [
            	{ className: "expand-100-percent", "targets": [ 7 ] }
            ],

            // columns definition
            columns: [ {
                field: 'displayName',
                title: 'Display Name',
                autoHide: false,
                width: 175,
                template: function(row)
                {
                	return '<div><i class="fa fa-envelope-open-text" data-toggle="tooltip"  data-placement="bottom" title="'+ row.description +'"></i>&nbsp;&nbsp;&nbsp;' + row.displayName + '</div>';
                }
            }, {
                field: 'subject',
                title: 'Subject',
                width: 125,
                autoHide: false,
            }, {
                field: 'tutorName',
                title: 'Tutor Name',
                width: 125,
                autoHide: false,
            }, {
                field: 'byUser.modifiedUserName',
                title: 'Modified User',
                width: 125,
                autoHide: false,
            }, {
                field: 'modifiedDateByTimeZone',
                title: 'Modified Date',
                width: 125,
                autoHide: false,
            }, {
                field: 'View',
                title: '',
                width: 30,
                template: function(row)
                {
                	return "<a href=\"javascript:openVideo('" + row.videoId + "','0','" + row.displayName + "','" + row.subject + "')\"><i class='fas fa-video text-danger'></i></a>";
                }
            }, {
                field: 'status',
                title: 'Status',
                autoHide: false,
                width: 60,
                
                // callback function support for column rendering
                template: function(row) {
                    var status = {
                        true: {
                            'title': 'Online',
                            'state': 'success'
                        },
                        false: {
                            'title': 'Offline',
                            'state': 'danger'
                        }
                    };
                    return '\<a href="javascript:blockVideo(\''+ row.videoId +'\',\''+ row.displayName +'\',\'' + row.status + '\')" title="Block video"><span class="label label-' + status[row.status].state + ' label-dot mr-2"></span><span class="font-weight-bold text-' + status[row.status].state + '">' +
                        status[row.status].title + '</span></a>';
                }
           
            }, {
                field: 'Actions',
                title: 'Actions',
                sortable: false,
                overflow: 'visible',
                autoHide: false,
                template: function(row) {
                    return '\
                        <a href="javascript:updateVideo(\''+ row.videoId +'\')" class="btn btn-sm btn-clean btn-icon mr-2" title="Edit details">\
                            <span class="svg-icon svg-icon-md">\
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
                                        <rect x="0" y="0" width="24" height="24"/>\
                                        <path d="M8,17.9148182 L8,5.96685884 C8,5.56391781 8.16211443,5.17792052 8.44982609,4.89581508 L10.965708,2.42895648 C11.5426798,1.86322723 12.4640974,1.85620921 13.0496196,2.41308426 L15.5337377,4.77566479 C15.8314604,5.0588212 16,5.45170806 16,5.86258077 L16,17.9148182 C16,18.7432453 15.3284271,19.4148182 14.5,19.4148182 L9.5,19.4148182 C8.67157288,19.4148182 8,18.7432453 8,17.9148182 Z" fill="#000000" fill-rule="nonzero"\ transform="translate(12.000000, 10.707409) rotate(-135.000000) translate(-12.000000, -10.707409) "/>\
                                        <rect fill="#000000" opacity="0.3" x="5" y="20" width="15" height="2" rx="1"/>\
                                    </g>\
                                </svg>\
                            </span>\
                        </a>\
                        <a href="javascript:deleteVideo(\''+ row.videoId +'\',\''+ row.displayName +'\')" class="btn btn-sm btn-clean btn-icon" title="Delete">\
                            <span class="svg-icon svg-icon-md">\
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
                                        <rect x="0" y="0" width="24" height="24"/>\
                                        <path d="M6,8 L6,20.5 C6,21.3284271 6.67157288,22 7.5,22 L16.5,22 C17.3284271,22 18,21.3284271 18,20.5 L18,8 L6,8 Z" fill="#000000" fill-rule="nonzero"/>\
                                        <path d="M14,4.5 L14,4 C14,3.44771525 13.5522847,3 13,3 L11,3 C10.4477153,3 10,3.44771525 10,4 L10,4.5 L5.5,4.5 C5.22385763,4.5 5,4.72385763 5,5 L5,5.5 C5,5.77614237 5.22385763,6 5.5,6 L18.5,6 C18.7761424,6 19,5.77614237 19,5.5 L19,5 C19,4.72385763 18.7761424,4.5 18.5,4.5 L14,4.5 Z" fill="#000000" opacity="0.3"/>\
                                    </g>\
                                </svg>\
                            </span>\
                        </a>\
                    ';
                },
            }, {
                field: 'attachmentList',
                title: '&nbsp;',
                overflow: 'visible',
                autoHide: true,
                template: function(row) {
                	var videoList = "<div class='card card-custom card-stretch'><div class='card-body pt-2'>"  ;
                	for(var i = 0 ; i < row.attachmentList.length; i++)
                	{
                		// videoList += "<div class='row' ><p class='bg-gray-100
						// text-dark-50 py-2 px-4'><i class='fas fa-video
						// text-primary mr-5'></i>&nbsp;&nbsp;"+
						// row.attachmentList[i].uploadFileName +"&nbsp;<a
						// href='javascript:void' class='align-items-right'><p
						// class='bg-gray-100 text-dark-50 py-2 px-4'><i
						// class='fas fa-times-circle text-danger
						// mr-5'></p></i></a></div>";
                		var sizeLabel = " bytes";
        				var filesize = row.attachmentList[i].uploadFileSize;
        				if (filesize > 1024){
        					filesize = filesize / 1024;
        					sizeLabel = " kb";

        					if(filesize > 1024){
        						filesize = filesize / 1024;
        						sizeLabel = " MB";
        					}
        				}
        				if(i % 3 == 0)
        					videoList += "<div class='row'>"	;
                		
        				videoList += 
                		"<div class='col-md-4'><div class='d-flex flex-wrap align-items-center mb-10'style='width: max-content'>" + 
                		"<div class='symbol symbol-60 symbol-2by3 flex-shrink-0'>" +
                		"<div class='symbol-label'><i class='fas fa-video text-primary icon-2x'></i></div></div>" + 
                		"<div class='d-flex flex-column ml-4 flex-grow-1 mr-2'>" + 
                		"<a href=\"javascript:openVideo(\'" + row.videoId + "\',\'" + row.attachmentList[i].autoId + "\',\'" + row.displayName + "\',\'" + row.subject + "\')\" class='text-dark-75 font-weight-bold text-hover-primary font-size-lg mb-1'>" + row.attachmentList[i].uploadFileName + "</a>" + 
                		"<span class='text-muted font-weight-bold'>Size: "+ Math.round(filesize, 2) + sizeLabel + "</span></div>" + 
                		"<span class='mt-lg-0 mb-lg-0 my-2 font-weight-bold py-4'><i class='fas fa-times-circle text-danger mr-5 align-items-right'></i></span>" + 
                		"</div></div>";
        				
        				if(i % 3 == 2)
        					videoList += "</div>"	

                	}
                	
                	if(row.attachmentList.length == 1)
    					videoList += "<div class='col-md-4' style='z-index:-100'>&nbsp;</div><div class='col-md-4' style='z-index:-100'>&nbsp;</div></div>";
    						
                	if(row.attachmentList.length == 2)
    					videoList += "<div class='col-md-4' style='z-index:-100'>&nbsp;</div></div>";
    				
                	
                    return videoList + "</div></div>" ;
                }
           
            }, {
            	field: 'videoId',
                visible: false
            }],

        });

		$('#kt_datatable_search_subject').on('change', function() {
            videoDataTable.search($(this).val().toLowerCase(), 'subject');
        });

        $('#kt_datatable_search_tutor').on('change', function() {
            videoDataTable.search($(this).val().toLowerCase(), 'tutorName');
        });

        $('#kt_datatable_search_status, #kt_datatable_search_type').selectpicker();
        
        
        $(function () {
    	  $('[data-toggle="tooltip"]').tooltip();
    	});
        
    	return videoDataTable;
    };

    return {
        // public functions
        init: function() {
            return videoFn();
        },
    };
}();
var videoDataTable ;
jQuery(document).ready(function() {
	videoDataTable = KTDatatableVideo.init();
	
});
function endUserVideo()
{
	 // AJAX request
	$.ajax({
		url: root + '/endUserVideo',
		type: 'post',
		headers : {
			"Authorization" : 'Bearer ' + access_token
		},
		contentType : "application/json",
		cache : false,
		timeout : 600000,
		success: function(response){ 
			// Add response in Div body
			$('#viewVideoDivId').html(response);
		}
	});
}


function openVideo(videoId, attachmentId , videoName, videoSubject)
{
	 // AJAX request
	$.ajax({
		url: root + '/viewVideo/' + videoId +"/" + attachmentId,
		type: 'post',
		headers : {
			"Authorization" : 'Bearer ' + access_token
		},
		contentType : "application/json",
		cache : false,
		timeout : 600000,
		success: function(response){ 
			// Add response in Modal body
			$('.modal-body').html(response);
			
			// Display Modal
			$('#videoTitleId').text(videoSubject + ' : ' + videoName);
			$('#videoModelId').modal({show:true});
		}
	});
}


function blockVideo(videoId, displayName, status)
{
	Swal.fire({
        title: "Are you sure?",
        text: "Do you want to make '" + displayName + (status=='false' ? "' Video Online ?" : "' Video Offline ?") ,
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, " + (status=='false' ? "Online" : "Offline") +" it!"
    }).then(function(result) {
        if (result.value) {
        	var video = {}
        	video["videoId"] = videoId;
        	
        	var data = {};
        	data["video"] = video;
        	
        	$.ajax({
				type : "POST",
				url : root + "/blockVideo",
				headers : {
					"Authorization" : 'Bearer ' + access_token
				},
				contentType : "application/json",
				cache : false,
				timeout : 600000,
				data : JSON.stringify(data),
				success : function(response) {
					Swal.fire(
			            	(status=='false' ? "Online !" : "Offline !"),
			                "Your video '" + displayName + "' has been set to " + (status=='false' ? " Online." : " Offline.") ,
			                "success"
			            );
					videoDataTable.reload();
				},
				error : function(response) {
					Swal.fire(
			            	"<i class='fa fa-times-circle text-danger mr-5 icon-2x'></i>Error!",
			            	response.responseJSON.messageCode,
			                "failure"
			            );
				}
			});
        }
    });
}
function updateVideo(videoId)
{
	var video = {}
	video["videoId"] = videoId;
	
	var data = {};
	data["video"] = video;
	
	$.ajax({
		type : "POST",
		url : root + "/preUpdateVideo",
		headers : {
			"Authorization" : 'Bearer ' + access_token
		},
		contentType : "application/json",
		cache : false,
		timeout : 600000,
		data : JSON.stringify(data),
		success : function(response) {
			// window.location.href = response.redirect;
		}
	});
}
function deleteVideo(videoId, videoName)
{
	Swal.fire({
        title: "Are you sure to delete '"+ videoName +"'?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it!"
    }).then(function(result) {
    	
    	if (result.value) {
        	var video = {}
        	video["videoId"] = videoId;
        	
        	var data = {};
        	data["video"] = video;
        	
        	$.ajax({
        		type : "POST",
				url : root + "/deleteVideo",
				headers : {
					"Authorization" : 'Bearer ' + access_token
				},
				contentType : "application/json",
				cache : false,
				timeout : 600000,
				data : JSON.stringify(data),
				success : function(response) {
					Swal.fire(
			                "Deleted!",
			                "Your video '"+ videoName +"' has been deleted.",
			                "success"
			            );
					videoDataTable.reload();
				},
				error : function(response) {
					
					Swal.fire(
			            	"<i class='fa fa-times-circle text-danger mr-5 icon-2x'></i>Error!",
			            	response.responseJSON.messageCode,
			                "failure"
			            )
				}
			});
        }
    });
}
