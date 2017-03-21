$(document).ready(function () {
	$('html').niceScroll({cursorcolor:"#ccc", horizrailenabled: false, enablekeyboard: false});
	/* dataTable = $('#dataTables-example').DataTable({
	  //dom: '<"toolbar">',
      searching: false,
      ordering:  false,
      paging: false,
      responsive: true,
      info: false,
      language: {
    		"srocessing":   "处理中...",
    		"lengthMenu":   "显示 _MENU_ 项结果",
    		"zeroRecords":  "没有匹配结果",
    		"info":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    		"infoEmpty":    "显示第 0 至 0 项结果，共 0 项",
    		"infoFiltered": "(由 _MAX_ 项结果过滤)",
    		"infoPostFix":  "",
    		"search":       "搜索:",
    		"url":          "",
    		"emptyTable":     "表中数据为空",
    		"loadingRecords": "载入中...",
    		"infoThousands":  ",",
    		"paginate": {
    			"sFirst":    "首页",
    			"sPrevious": "上页",
    			"sNext":     "下页",
    			"sLast":     "末页"
    		},
    		"aria": {
    			"sSortAscending":  ": 以升序排列此列",
    			"sSortDescending": ": 以降序排列此列"
    		}
    	}
      createdRow: function( row, data, dataIndex ) {
   	    if ( dataIndex % 2 == 0 ) {
   	      $(row).addClass( 'important' );
   	    }
   	  } 
    });*/
    
    
    //上传控件
    uploader = {
		uploadUrl : null,
		init : function() {
			$('#uploadModal').on('hidden.bs.modal', function () {
		    	$('.modaldownBtn').addClass('hidden')
			});
		    
			$('#uploadForm').submit(function(){
				if($('#file').val()=='') {
					swal('通知','请选择要上传的文件','info');
					return false;
				}
				$('#uploadModal').modal('hide');
				$.ajaxFileUpload({
					url : uploadUrl,
					type : "POST",
					secureuri : false,
					fileElementId : "file",
					dataType : "json",
					success : function(data, status) {
						console.log("================");
						console.log(data);
						$('.uploadFiles').show();
						if (data && data.resultCode == "0") {
							swal({
								title:'通知',
								text:'上传成功',
								type:'success',
							}, function(){
								if(data.uploadType&&data.uploadType=="procurement"){
									var files="<a src='"+data.data.filePath+"'>"+data.data.originalFilename+"</a>" +
											"<span class='glyphicon glyphicon-remove removeFile'></span>"
									$('.uploadFiles').html(files);
									$('.procureFilePath').val(data.data.filePath);
									$('.procureFileName').val(data.data.originalFilename);
								   $('.removeFile').click(function(){
									   debugger;
									   $(this).prev()[0].remove();
								       $(this).remove();
								       $('.procureFilePath').val();
								       $('.procureFileName').val();
								    });
								}else{
									location.reload();
								}
							});
						} else {
							swal('通知','上传失败'+(data.resultMsg?':'+data.resultMsg:''),'error');
						}
					},
					error : function(data, status, e) {
						swal('通知','上传失败','error');
						swal('通知','上传失败:服务器异常','error');
					}
				});
				return false;
			});
		},
		uploadModal : function(url,tempUrl){
	    	uploadUrl = url;
	    	if(tempUrl!=null && tempUrl!='') {
	    		$('.modaldownBtn').removeClass('hidden').attr('href',tempUrl);
	    	}
	    	$('#uploadModal').modal('toggle');
	    }
    };
    uploader.init();
    
    $("#toggle-advanced-search").click(function(){
        $("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
        $("#div-advanced-search").slideToggle("fast");
        $('#searchBtns').fadeToggle("fast");
    });

    //分页事件
    $('.pageNum').click(function(){
    	var action = $('.searchForm').attr('action');
    	$('.searchForm').attr('action',action + $(this).data('value') + '/' + $('.pageSize').val());
    	search();
    });
    $('.pageSize').change(function(){
    	var action = $('.searchForm').attr('action');
    	$('.searchForm').attr('action',action + '0/' + this.value);
    	search();
    });
    $('.searchBtn').click(function(){
    	var action = $('.searchForm').attr('action');
    	$('.searchForm').attr('action',action + ($('.pageNum.disabled').data('value')?$('.pageNum.disabled').data('value'):0) + '/' + $('.pageSize').val());
    	search();
    });
    function search() {
    	$('.searchForm').submit();
    }
  });