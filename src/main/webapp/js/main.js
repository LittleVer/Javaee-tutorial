$(document).ready(function () {
	$('html').niceScroll({cursorcolor:"#ccc", horizrailenabled: false, enablekeyboard: false});
    dataTable = $('#dataTables-example').DataTable({
	  dom: '<"toolbar">',
      responsive: true,
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
      /*createdRow: function( row, data, dataIndex ) {
   	    if ( dataIndex % 2 == 0 ) {
   	      $(row).addClass( 'important' );
   	    }
   	  } */
    });
    
    //$('form.searchForm').appendTo($("div.toolbar"));
  });