function showAddr(){
	$(".user_ares").html('')
	 $.ajax({  
         type: "get",  
         url:"/user/info",
         contentType: 'application/json',
         async: false, 
         cache:false,
         error: function(status) { 
                 swal({
                     title: "暂务数据!",
                     type: "error",
                     timer: 5000,
                     showConfirmButton: false,
                     sleep : 20000
                 });  
         },  
         success: function(status) {
            console.log(status);
            $(".user_ares").empty();
             if(status.code==1){
                $.each(status.data.person_address, function(idx,obj){
                     var html;
                     html = '<div class="delivery_all">'+
                             '<div class="col-md-1 product_list" data-field="state" data-checkbox="true" style="margin: 0;padding: 0;">'+
                                 '<input type="radio" name="delivery" id="id" value="'+obj.id+'" />'+
                             '</div>' +
                             '<div class="col-md-2 product_list" data-field="product_id" data-align="center" style="margin: 0;padding: 0;">'+obj.name+'</div>'+
                             '<div class="col-md-2 product_list" data-field="product_id" data-align="center" style="margin: 0;padding: 0;">'+obj.phone+'</div>'+
                             '<div class="col-md-7 product_list" data-field="product_id" data-align="center" style="margin: 0;padding: 0;" style="overflow: hidden;">'+obj.encapsulation+'</div>'+
                         '</div>'
                     $(".user_ares").append(html);
                 }); 

             }
         }  
     }); 
}

function renderOemTable(){
	var data1 = $("#data1").val(),
    data2 = $("#data2").val();
	var data_time ={}
	if(data1!=null && data1.length>0){
		data_time = {
			    "data1" : data1,
			    "data2" : data2
			}
	}
	 $(".page_list_oem").html('');
	$.ajax({
        url:"/product/list",
        cache:false,
        type: "post",  
        contentType: 'application/json',
        dataType:"json", 
        data:JSON.stringify(data_time),
        success:function(e){
        	   if(e.data.length==0){
        		   swal("暂无数据4！");
        		   return;
        	   }
        	   $.each(e.data, function(idx,obj){
        		   if(idx<pagesize){
        			   drawTableOem(obj)
        		    }
                    }); 
        	   var content=e.data.length;       //总数
               var pageTotal=Math.ceil(content/pagesize);  //分页数量
               var html='<ul class="pagination" id="page2"></ul>';
               $(".page-left").append(html);
               Page({
                   num:pageTotal,             //页码数
                   startnum:1,
                   pagesize:pagesize,             //每页显示的数量
                   elem:$('#page2'),       //指定的元素
                   callback:function(n){   //回调函数 
                	   paginationOem(n,e.data);     
                   }
               });
               
               $(".product_btn").each(function(){
                   var product_stat = $(this).attr("product_stat");
                   if(product_stat == "1"){
                       $(this).attr({"disabled":"disabled"});
                       $(this).css("background","#bfbfbf");
                   }
               });
        },
        error:function(result,sweetalert){
            swal("暂无数据2！");
         }
	})
}

function drawTableOem(obj){
	 var html = '<div class="product_all">'+
     '<div class="col-md-1 product_list">'+'<input type="checkbox" name="sign" style="width: 15px;height: 15px;" value="'+obj.id+'" />'+'</div>'+
     '<div class="col-md-2 product_list">'+obj.product_id+'</div>'+
     '<div class="col-md-2 product_list">'+obj.product_time+'</div>'+
     '<div class="col-md-2 product_list">'+obj.product_name+'</div>'+
     '<div class="col-md-2 product_list">'+obj.product_type+'</div>'+
     '<div class="col-md-1 product_list" name="sign" id="product_type" value="'+obj.product_class_type+'">'+obj.product_class_type+'</div>'+
     '<div class="col-md-1 product_list">'+
         '<div class="product_btn" product_stat="'+obj.product_stat+'" id="'+obj.id+'">选择</div>'+
     '</div>'+
     '<div class="col-md-1 product_details_oem" product_id="'+obj.id+'">查看详情</div>';
 $(".page_list_oem").append(html);   
}

function paginationOem(num,list){
	 $(".page_list_oem").html('');
	 $.each(list, function(idx,obj){
		 if(idx>=((num-1)*pagesize) && idx<(num*pagesize)){
			   drawTableOem(obj)
		    }
          });
}

function addOrUpdOem(oriId){
	   var  oem_product_name = $("#oem_product_name").val(),
       oem_product_type = $("#oem_product_type").val(),
       oem_pcb_cmstart = $("#oem_pcb_cmstart").val(),
       oem_pcb_cmstop = $("#oem_pcb_cmstop").val(),
       oem_pcb_layer = $("#oem_pcb_layer").children('option:selected').val(),
       oem_pcb_thickness = $("#oem_pcb_thickness").children('option:selected').val(),
       oem_pcb_spray = $("#oem_pcb_spray").children('option:selected').val(),
       oem_pcb_solder = $("#oem_pcb_solder").children('option:selected').val(),
       oem_pcba_process = $("#oem_pcba_process").val(),
       oem_pcba_smt_type = $("#oem_pcba_smt_type").val(),
       oem_pcba_smt_joints = $("#oem_pcba_smt_joints").val(),
       oem_pcba_dip_type = $("#oem_pcba_dip_type").val(),
       oem_pcba_dip_joints = $("#oem_pcba_dip_joints").val(),
       oem_pcba_stencil = $("#oem_pcba_stencil").children('option:selected').val(),
       oem_pcba_stencil_num = $("#oem_pcba_stencil_num").val(),
       oem_test_time = $("#oem_test_time").val(),
       oem_prevent_cm2 = $("#oem_prevent_cm2").val(),
       oem_remark = $("#oem_remark").val(),
       product_class_type = $("#product_class_type").val();
	   
	   //判断OEM信息不能为空的选项信息
       if(oem_product_name=='' || oem_pcba_process=='' || oem_pcba_smt_type=='' || oem_pcba_dip_type==''){
           swal("信息不能为空!");
           return false;
       }
       //判断产品编号以及钢网编号
       if(oem_product_type=='' || (/[\u4e00-\u9fa5]/.test(oem_product_type))){
           swal("产品型号不能为空、汉字或全角符号！");
           return false;
       }
       if(oem_pcba_stencil_num!=''){
           if((/[\u4e00-\u9fa5]/.test(oem_pcba_stencil_num))){
               swal("钢网编号不能为汉字或全角符号！");
               return false;
           }
       }
       //oem产品尺寸判断
        if(!(/[0-9]/.test(oem_pcb_cmstart)) || !(/[0-9]/.test(oem_pcb_cmstop))){
           swal("PCB尺寸必须是数字!");
           return false;
       }
       //判断焊接点数、种类、组装时间
       if(!(/[0-9]/.test(oem_pcba_smt_joints)) || !(/[0-9]/.test(oem_pcba_dip_joints)) || !(/[0-9]/.test(oem_pcba_smt_type)) || !(/[0-9]/.test(oem_pcba_dip_type))){
            swal("SMT和DIP的种类、焊接点数必须是数字!");
           return false;
       }
        if(oem_test_time!=''){
           if(!(/[0-9]/.test(oem_test_time))){
               swal("测试组装时间必须是数字!");
               return false;
          }
       }
        if(oem_prevent_cm2!=''){
           if(!(/[0-9]/.test(oem_prevent_cm2))){
               swal("喷涂三防必须是数字!");
               return false;
           }
       }
           //数据库json格式
       var oem_data = {
           "oem_product_name" : oem_product_name,
           "oem_product_type" : oem_product_type,
           "oem_pcb_cmstart" : oem_pcb_cmstart,
           "oem_pcb_cmstop" : oem_pcb_cmstop,
           "oem_pcb_layer" : oem_pcb_layer,
           "oem_pcb_thickness" : oem_pcb_thickness,
           "oem_pcb_spray" : oem_pcb_spray,
           "oem_pcb_solder" : oem_pcb_solder,
           "oem_pcba_process" : oem_pcba_process,
           "oem_pcba_smt_type" : oem_pcba_smt_type,
           "oem_pcba_smt_joints" : oem_pcba_smt_joints,
           "oem_pcba_dip_type" : oem_pcba_dip_type,
           "oem_pcba_dip_joints" : oem_pcba_dip_joints,
           "oem_pcba_stencil" : oem_pcba_stencil,
           "oem_pcba_stencil_num" : oem_pcba_stencil_num,
           "oem_test_time" : oem_test_time,
           "oem_prevent_cm2" : oem_prevent_cm2,
           "oem_remark" : oem_remark,
          "product_class_type" : product_class_type,
           "now_time":now_time
       };
       if(oriId){
    	   oem_data['id']=oriId;
       }
       //数据库交互
       $.ajax({  
           type: "post",  
           contentType: 'application/json',
           data:JSON.stringify(oem_data),// 序列化表单值  
           url:"/product/save",  
           async: false, 
           cache:false,
           dataType:"json", 
           error: function(status) { 
               console.log(status); 
               if(status.code==0){
                   swal({
                       title: "添加失败!",
                   });  
               }
           },  
           success: function(status) {
               console.log(status); 
               if(status.code==1){
                   swal({
                       title: "添加成功",
                       text:"点击OK，进行下一步上传文件操作"
                   },function(){
                       $(".overlay_uplod").show();
                       $(".file_uplod").show();
                       var id = status.data;
                       console.log(id);
                       //上传pcb文件
                       $(".add_pcb").on('click',function(sweetalert){
                       	uploadFile(id,"oem_pcb_file","PCB",'pcb_file',"progress_pcb","time1","prog","rar,zip,tar,gzip,jar")
                       });

                       //上传BOM的excel文件
                       $(".add_bom").on('click',function(){
                       	uploadFile(id,"oem_bom_shopfile","BOM",'bom_shopfile',"progress_bom","time2","prog_bom","xlsx,xls")
                       });

                       //上传工艺文件
                       $(".add_process").on('click',function(){
                       	uploadFile(id,"oem_process_file","工艺",'coordinate_file',"progress_process","time3","prog_process","rar,zip,tar,gzip,jar")
                       });

                       //上传坐标文件
                       $(".add_coordinate").on('click',function(){
                       	uploadFile(id,"oem_coordinate_file","坐标",'process_file',"progress_coordinate","time4","prog_coordinate","rar,zip,tar,gzip,jar")
                       });

                       //点击完成按钮
                       $(".complete").on('click',function(){
                           window.location.reload();
                       });
                   });  
               }
           }  
       }); 
}

// 上传文件
function uploadFile(id,fileId,ftype,stype,processclass,time,prog,types){
	  var oem_pcb_file = $("#"+fileId).val();
      if(oem_pcb_file==''){
          swal(ftype+"文件不能为空!");
          return false;
      }else{
    	  var fileTypes = types.split(",");
         // var fileTypes = new Array("rar","zip","tar","gzip","jar");
			// //定义可支持的文件类型数组
          var oem_pcb="0";
          var newFileName = oem_pcb_file.split('.');
          newFileName = newFileName[newFileName.length-1];
          for(var i=0;i<fileTypes.length;i++){
              if(fileTypes[i] == newFileName){
              　									　oem_pcb = "1";
              }
          }
          if(oem_pcb == "0"){
              swal(ftype+"文件必须是"+types);
              return false;
          }
      }   
  	var fd = new FormData();
      fd.append("file", $("#"+fileId).get(0).files[0]);

      $.getJSON({  
          type:"post",
    	  data:fd,// 序列化表单值
          url:"/product/upload?id="+id+"&type="+stype,  
          async: false, 
          cache:false,
          processData: false,
          contentType: false, 
          success: function(status) { 
              console.log(status);
              if(status.code==1){
                  $("."+processclass).show();
                  var timer=5;
                  var countdown = setInterval(CountDown,1000);
                  function CountDown(){
                      $("."+time).html("正在上传，请稍等,倒计时"+timer+"s");
                      if(timer==0){
                          $("."+time).html("上传"+ftype+"文件成功").css({"font-size":"18px","color":"#00ff45"});
                          $("."+prog).hide();
                          clearInterval(countdown);
                      }
                      timer--;
                  }    
              }
              if(status.code!=1){
            	  $("."+processclass).show();
                  var timer=5;
                  var countdown = setInterval(CountDown,1000);
                  function CountDown(){
                      $(".time1").html("正在上传，请稍等,倒计时"+timer+"s");
                      if(timer==0){
                          swal("上传"+ftype+"文件失败，请重新上传");
                          $("."+processclass).hide();
                          clearInterval(countdown);
                      }
                      timer--;
                  }    
              }
          },
      });
}