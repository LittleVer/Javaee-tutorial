$(document).ready(function () {
	$("[data-toggle='tooltip']").tooltip();
	$("[data-toggle='popover']").popover();
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
    	__init__ : false,			//延迟初始化标记
    	IMPORT_MODAL : 'import',
    	SINGLE_MODAL : 'single',
    	MULTI_MODAL : 'multi',
    	contextPath : $(':hidden[name=contextPath]').remove().val() + '/',
    	eventDom : null,
    	param : {
    		uploadUrl : null,
    		tempUrl : null,		//模板下载地址
    		type : null,		//上传文件类型   image word text 
    							//[{image:['all']},{word:['excel','word']}]
    		ids : null,			//文件主键  (修改时传入)
    		callback : null,	//回调fileInfoList数据
    		
    		//非前端传入
    		currentModal : null,	//上传模式	  import/single/multi
    		onlyImg : false		//仅图片可预览
    	},
    	acceptType : {
    		image:{
    			all:'',
    			jpg:'image/jpeg',
    			jpeg:'image/jpeg',
    			bmp:'image/bmp',
    			git:'image/gif',
    			png:'image/png'
    		},
    		word:{
    			all:'',
    			excel:'application/vnd.ms-excel	application/x-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
    			word:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document',
    			ppt:'application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation'
    		},
    		text:{
    			all:'',
    			txt:'text/plain',
    			word:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
    		}
    	},
    	fileDom : $('#file'),
		init : function() {
			var _this = this;
			this.__init__ = true;
			//初始化acceptType
			Object.getOwnPropertyNames(_this.acceptType).forEach(function(t) {
				var _all = '';
				Object.getOwnPropertyNames(_this.acceptType[t]).forEach(function(v) {
					if(v!='all') {
						_all += _this.acceptType[t][v] + ',';
					}
				});
				if(_all.length) {
					_this.acceptType[t]['all'] = _all.substring(0,_all.length-1);
				}
			});
			
			$('#uploadModal').on('hidden.bs.modal', function () {
		    	$('.modaldownBtn').addClass('hidden')
			});
			
			$('.attachmentArea').on('mouseover mouseout','div[id^=FILE]',function(){
				$(this).find('.fileDelete').toggleClass('hidden');
			});
			
			$('.attachmentArea').on('click','.fileDelete',function(){
				var id = $(this).parents('div[id^=FILE]').prop('id');
				swal({
				  title: "通知",
				  text: "是否要删除该附件？",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonText: "确认",
				  cancelButtonText: "取消",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				function(isConfirm){
				  if (isConfirm) {
					  _this.deleteAttachment(id.split('FILE_')[1]);
				  }
				});
			});
		    
			$('#uploadForm').submit(function(){
				if($('#file').val()=='') {
					swal('通知','请选择要上传的文件','info');
					return false;
				}
				if(_this.param.currentModal==_this.IMPORT_MODAL) {
					$('#uploadModal').modal('hide');
				}
				$.ajaxFileUpload({
					url : _this.contextPath + _this.param.uploadUrl,
					type : "POST",
					secureuri : false,
					fileElementId : "file",
					dataType : "json",
					success : function(data, status) {
						if (data && data.resultCode == "0") {
							_this.param.callback && _this.param.callback(data.data);
							if(_this.param.currentModal==_this.IMPORT_MODAL) {
								location.reload();
							} else if(_this.param.currentModal == this.MULTI_MODAL && this.uploadUrl.indexOf('multiple')!=-1) {
								data.data.forEach(function(v){
									if(_this.param.onlyImg) {
										_this.renderImgFile(v);
									} else {
										_this.renderFile(v);
									}
								});
							} else  {
								if(_this.param.currentModal == _this.SINGLE_MODAL) {
									$('.attachmentArea').empty();
									_this.deleteAttachment(_this.param.ids);
								}
								if(_this.param.onlyImg) {
									_this.renderImgFile(data.data);
								} else {
									_this.renderFile(data.data);
								}
							}
							swal({
								title:'通知',
								text:'上传成功',
								type:'success',
							}, function(){
								/*if(data.uploadType&&data.uploadType=="procurement"){
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
								}*/
							});
						} else {
							swal('通知','上传失败'+(data.resultMsg?':'+data.resultMsg:''),'error');
						}
					},
					error : function(data, status, e) {
						swal('通知','上传失败:服务器异常','error');
					}
				});
				return false;
			});
		},
		importModal : function(param) {
			param.currentModal = this.IMPORT_MODAL;
			this.initModalData(event.target,param);
		},
		singleModal : function(param){
			param.currentModal = this.SINGLE_MODAL;
			this.initModalData(event.target,param);
	    },
	    multiModal : function(param) {
	    	param.currentModal = this.MULTI_MODAL;
	    	this.initModalData(event.target,param);
	    },
	    initModalData : function(dom,param) {
	    	if(!this.__init__) this.init();
	    	var jdom = $(dom);
	    	if(!jdom.data('data')) {
	    		this.parseAcceptType(param);
	    		$.extend(this.param,param);
	    		jdom.data('data',this.param);
	    	}
	    	
	    	//调整UI
	    	if(param.currentModal==this.IMPORT_MODAL) {
	    		$('.modaldownBtn').removeClass('hidden').prop('href', this.contextPath + param.tempUrl);
	    		$('.attachmentArea').addClass('hidden');
	    	} else {
	    		$('.attachmentArea').removeClass('hidden');
	    		$('.modaldownBtn').addClass('hidden')
	    	}
	    	$('#uploadModal').modal('toggle');
	    	
	    	//调整上传控件资源
	    	if(this.param.type!=null) {
	    		this.fileDom.prop('accept',this.param.type);
	    	} else {
	    		this.fileDom.removeProp('accept');
	    	}
	    	if(this.param.currentModal == this.MULTI_MODAL && this.uploadUrl.indexOf('multiple')!=-1) {
	    		this.fileDom.prop('multiple','multiple');
	    	} else {
	    		this.fileDom.removeProp('multiple');
	    	}
	    	
	    	//重新加载资源
	    	if(this.eventDom!=dom) {
	    		this.eventDom = dom;
	    		this.reloadAttachment(param.ids);
	    	}
	    },
	    parseAcceptType: function(param) {
	    	var _this = this;
	    	var type = param.type;
	    	if(type==null) return;
	    	var acceptType = '';
	    	if(type.length==1 && type[0].image) {
	    		param.onlyImg = true;
	    	}
	    	for(var i in type) {
	    		var _type = type[i];
	    		var key = Object.getOwnPropertyNames(_type)[0];
	    		if(_this.acceptType[key]) {
	    			type[i][key].forEach(function(v){
	    				acceptType += _this.acceptType[key][v] + ',';
	    			});
	    		}
	    	}
	    	if(acceptType.length) acceptType = acceptType.substring(0,acceptType.length-1);
	    	param.type = acceptType;
	    },
	    reloadAttachment : function(ids) {
	    	var _this = this;
	    	if($.trim(ids)!='') {
	    		$.post(this.contextPath + '/upload.do/queryByIds',{ids:ids},function(json){
	    			if(json.resultCode=='0') {
	    				json.data.forEach(function(v){
	    					if(_this.param.onlyImg) {
	    						_this.renderImgFile(v);
	    					} else {
	    						_this.renderFile(v);
	    					}
	    				});
	    			} else {
	    				swal('通知','上传失败:服务器异常','error');
	    			}
	    		});
	    	}
	    },
	    deleteAttachment : function(id) {
	    	if($.trim(id)!='')
			$.post(this.contextPath + '/upload.do/delete',{id:id},function(json){
				if(json.resultCode=='0') {
					swal("通知", "文件已删除", "success");
					$('#FILE_'+id).remove();
				}
			});
	    },
	    renderImgFile : function(data) {
	    	this.renderInit();
	    	$('<div>')
	    	.prop('id',('FILE_'+data.id))
	    	.addClass('col-md-1')
	    		.append(this.closeSpan())
	    		.append(
	    			$('<img>')
	    			.prop({'src':data.filePath,'title':data.originalFilename})
	    			.addClass('img-thumbnail fileInfo'))
	    	.appendTo($('.attachmentArea .row'));
	    },
	    renderFile : function(data) {
	    	this.renderInit();
	    	$('<div>')
	    		.prop('id',('FILE_'+data.id))
	    		.addClass('col-md-1')
	    			.append(this.closeSpan())
	    			.append(
    				$('<a>')
				    	.prop({'href':data.filePath,'target':'_blank','title':data.originalFilename})
				    	.addClass('img-thumbnail fileInfo')
				    	.text(data.originalFilename))
			    .appendTo($('.attachmentArea .row'));
	    },
	    closeSpan : function() {
	    	return $('<i class="fa fa-lg fa-times fileDelete hidden">');
	    },
	    renderInit : function() {
	    	if(!$('.attachmentArea .row').length) {
	    		$('<div>').addClass('row').appendTo($('.attachmentArea'));
	    	}
	    }
    };
    
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