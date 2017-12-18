 /*自助下单功能逻辑开始*/
$(function(){

    //订单管理路由跳转结束
    $(".data1").cxCalendar({
        baseClass: 'cxcalendar_holyday'
    });
    $(".data2").cxCalendar({
        baseClass: 'cxcalendar_holyday'
    });
     //获取当前时间
    var myDate = new Date();
    var year=myDate.getFullYear();   //获取当前年
    var month=myDate.getMonth()+1;   //获取当前月
    var date=myDate.getDate();       //获取当前日
    var now_time=year+'.'+month+"."+date;

    //OEM流程展示
    $.getJSON({  
        type: "get",  
        url:"../../json/oem_flow.json",  
        async: false, 
        cache:false,
        dataType:"json", 
        success: function(status) {
            console.log(status); 
            if(status.data.state==200){
                /*加载预付款流程信息*/
                $.each(status.data.oem, function(idx,obj){
                    var oem;
                    oem='<div class="tow">'+
                        '<div class="round_red">'+
                            '<span>'+obj.num+'</span>'+
                        '</div>'+
                        '<span>'+obj.title+'</span>'+
                    '</div>';
                    $(".red_detail").append(oem);
                }); 
            }
        }  
    }); 
    
    renderOemTable();
    
    //查看详情
    $(".page_list_oem").on('click','.product_details_oem',function(){
        var id= $(this).attr("product_id");
        console.log(id);
        var id_data={
            "id":id
        };
        //加载弹出遮盖层
        $(".overlay2_tow").show();
        $(".oem_details").show();
        $(".cental_pro").on('click',function(){
            $(".oem_details").hide();
            $(".overlay2_tow").hide();
            window.location.reload();
        });
        //获取产品信息
        $.getJSON({
            url:"/product/detail?id="+id,
            cache:false,
            success:function(result,data){
                    //oem订单信息
                    //产品型号
                    var html_product;
                    html_product = '<div class="col-md-4 product_list" data-field="product_id" data-align="center">'+result.product_id+'</div>'+
                                    '<div class="col-md-4 product_list" data-field="product_id" data-align="center">'+result.product_name+'</div>'+
                                    '<div class="col-md-4 product_list" data-field="product_id" data-align="center">'+result.product_type+'</div>';
                    $(".list_product").append(html_product);

                    //产品pcb信息
                    var html_pcb;
                    html_pcb = '<div class="col-md-3 product_list" data-field="product_id" data-align="center">'+result.oem_pcb_cmstart+'*'+result.oem_pcb_cmstop+'</div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.oem_pcb_layer+'</div></div></div></div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.oem_pcb_thickness+'</div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.oem_pcb_spray+'</div>'+
                                '<div class="col-md-3 product_list" data-field="product_id" data-align="center">'+result.oem_pcb_solder+'</div>';
                    $(".list_pcb").append(html_pcb);

                    //下载按钮
                    var html_downlod;
                    html_downlod = '<a class="col-md-5 product_list" data-field="product_id" data-align="center" href="/product/down?path='+result.pcb_file+'" title="PCB下载" style="height: 30px;line-height: 30px;background: #03A9F4;border-radius: 10px;color: #fff;float: left;">PCB</a>'+
                                '<a class="col-md-5 product_list" data-field="product_id" data-align="center" title="坐标文件下载" style="height: 30px;line-height: 30px;background: #03A9F4;border-radius: 10px;color: #fff;float: right;">坐标</a>'+
                                '<a class="col-md-5 product_list" data-field="product_id" data-align="center" title="工艺文件下载" style="height: 30px;line-height: 30px;background: #03A9F4;border-radius: 10px;color: #fff;float: left;margin-top: 10px;">工艺</a>'+
                                '<a class="col-md-5 product_list" data-field="product_id" data-align="center" href="/product/down?path='+result.bom_shopfile+'" title="BOM文件下载" style="height: 30px;line-height: 30px;background: #03A9F4;border-radius: 10px;color: #fff;float: right;margin-top: 10px;">BOM</a>';   
                    $(".list_downlod").append(html_downlod);

                    //产品pcba清单
                    var html_pcba;
                    html_pcba = '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.pcba_process+'</div>'+
                                '<div class="col-md-1 product_list" data-field="product_id" data-align="center">'+result.pcba_smt_type+'</div></div></div></div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.pcba_smt_joints+'</div>'+
                                '<div class="col-md-1 product_list" data-field="product_id" data-align="center">'+result.pcba_dip_type+'</div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.pcba_dip_joints+'</div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.pcba_stencil+'</div>'+
                                '<div class="col-md-2 product_list" data-field="product_id" data-align="center">'+result.pcba_stencil_num+'</div>';
                    $(".list_pcba").append(html_pcba);

                    //测试组装
                    var html_text;

                    html_text = '<div class="col-md-4 product_list" data-field="product_id" data-align="center">'+result.oem_test_time+'</div>'+
                                '<div class="col-md-4 product_list" data-field="product_id" data-align="center">'+result.oem_prevent_cm2+'</div>'+
                                '<div class="col-md-4 product_list" id="product_smark" data-field="product_id" data-align="center" title="'+result.oem_remark+'">'+
                                    '<span class="remark" id="chuang_news">'+result.oem_remark+'</span>'+
                                '</div>';
                    $(".list_testing").append(html_text);
                    $(".remark").each(function(){
                        var maxwidth=2;
                        if($(this).text().length>maxwidth){
                            $(this).text($(this).text().substring(0,maxwidth));
                            $(this).html($(this).html()+'…');
                        }
                    });
            }
        });

        //获取bom表头以及bom信息
        $.getJSON({
            url:"../../json/product.json",
            cache:false,
            data:id_data,
            success:function(result,data){
                if(result.state==200){
                    //获取bom表头
                    //bom清单
                    $.each(result.bom_tital, function(idx,obj){
                        var bom_tital;
                        bom_tital ='<div class="col-md-1">'+obj.numbering+'</div>'+
                                    '<div class="col-md-2">'+obj.name+'</div>'+
                                    '<div class="col-md-1">'+obj.type+'</div>'+
                                    '<div class="col-md-1">'+obj.encapsulation+'</div>'+
                                    '<div class="col-md-2">'+obj.bit_number+'</div>'+
                                    '<div class="col-md-1">'+obj.accuracy+'</div>'+
                                    '<div class="col-md-2">'+obj.brands+'</div>'+
                                    '<div class="col-md-1">'+obj.quantity+'</div>'+
                                    '<div class="col-md-1">备注</div>';
                        $(".product_tr_oem").append(bom_tital);
                    });

                    //bom清单
                    $.each(result.bom, function(idx,obj){
                        var html_bom;
                        html_bom ='<div class="bom_list_oem">'+ 
                                '<span class="col-md-1">'+obj.number+'</span>'+
                                '<span class="col-md-2">'+obj.name+'</span>'+
                                '<span class="col-md-1">'+obj.model_number+'</span>'+
                                '<span class="col-md-1">'+obj.encapsulation+'</span>'+
                                '<span class="col-md-2">'+obj.accuracy+'</span>'+
                                '<span class="col-md-1">'+obj.brands+'</span>'+
                                '<span class="col-md-2">'+obj.bit_number+'</span>'+
                                '<span class="col-md-1">'+obj.quantity+'</span>'+
                                '<span class="col-md-1 remark" title="'+obj.remark+'">'+obj.remark+'</span>'+
                            '</div>';   
                        $(".product_bom").append(html_bom);
                        $(".accuracy").each(function(){
                            var maxwidth=2;
                            if($(this).text().length>maxwidth){
                                $(this).text($(this).text().substring(0,maxwidth));
                                $(this).html($(this).html()+'…');
                            }
                        });
                        $(".remark").each(function(){
                            var maxwidth=2;
                            if($(this).text().length>maxwidth){
                                $(this).text($(this).text().substring(0,maxwidth));
                                $(this).html($(this).html()+'…');
                            }
                        });
                    });
                }
            }
        });
    });
    
    
    $(".page_list_oem").on('click','.product_btn',function(){
    	 var id= $(this).attr("id"),
         product_stat = Number($(this).attr("product_stat"));
         if(product_stat == 0){
             var id_data={
                 "id":id
             };
             $(".bom_procurement").show();
             $(".bom_div").show();
             $(".cental_pro").on('click',function(){
                 $(".bom_div").hide();
                 $(".bom_procurement").hide();
                 window.location.reload();
             });

             //获取BOM标题
             $.getJSON({  
                 type: "get",  
                 url:"../../json/bom_tital.json",  
                 async: false, 
                 cache:false,
                 dataType:"json", 
                 success: function(status) {
                     console.log(status); 
                     if(status.state==200){
                         /*加载预付款流程信息*/
                         $.each(status.orderlist, function(idx,obj){
                             var bom_tital;
                             bom_tital='<div class="col-md-1 numbering">'+obj.numbering+'</div>'+
                                     '<div class="col-md-2 bom_name">'+obj.name+'</div>'+
                                     '<div class="col-md-1 type">'+obj.type+'</div>'+
                                     '<div class="col-md-1 encapsulation">'+obj.encapsulation+'</div>'+
                                     '<div class="col-md-2 bit_number">'+obj.bit_number+'</div>'+
                                     '<div class="col-md-1 accuracy">'+obj.accuracy+'</div>'+
                                     '<div class="col-md-1 brands">'+obj.brands+'</div>'+
                                     '<div class="col-md-1 quantity">'+obj.quantity+'</div>'+
                                     '<div class="col-md-1 remark">备注</div>'+
                                     '<div class="col-md-1">购买方</div>'
                             $(".product_tr_one").append(bom_tital);
                         }); 
                     }
                 }  
             });

              //获取BOM信息
             $.getJSON({  
                 type: "get",  
                 url:"../../json/bom_tital.json",  
                 async: false, 
                 cache:false,
                 dataType:"json", 
                 success: function(status) {
                     console.log(status); 
                     if(status.state==200){
                         /*加载预付款流程信息*/
                         $.each(status.oem_info, function(idx,obj){
                             var bom_des;
                             bom_des='<div class="product_tr_hree">'+
                                     '<div class="col-md-1 numbering">'+obj.numbering+'</div>'+
                                     '<div class="col-md-2 bom_name">'+obj.name+'</div>'+
                                     '<div class="col-md-1 type">'+obj.type+'</div>'+
                                     '<div class="col-md-1 encapsulation">'+obj.encapsulation+'</div>'+
                                     '<div class="col-md-2 bit_number">'+obj.bit_number+'</div>'+
                                     '<div class="col-md-1 accuracy">'+obj.accuracy+'</div>'+
                                     '<div class="col-md-1 brands">'+obj.brands+'</div>'+
                                     '<div class="col-md-1 quantity">'+obj.quantity+'</div>'+
                                     '<div class="col-md-1 remark">'+obj.remark+'</div>'+
                                     '<div class="col-md-1">'+
                                         '<select name="bom_purchaser" id="bom_purchaser">'+
                                             '<option value="创元采购">创元采购</option>'+
                                             '<option value="乙方提供">乙方提供</option>'+
                                         '</select>'+
                                         '<input name="bom_ids" value="'+obj.id+'" style="display:none">'+
                                     '</div>'+
                                 '</div>';
                             $(".product_tr_tow").append(bom_des);
                         }); 
                     }
                 }  
             });

             //提交bom采购方
             $(".bom_updata").on('click',function(){
                 //id数组
                 var id_arr = [];
                 var bom_ids = $("input[name='bom_ids']");
                 $.each(bom_ids,function(key,obj){
                     var bom_id = Number(obj.value);
                     id_arr.push(bom_id);
                 });

                 //购买方数组
                 var purchasers_arr = [];
                 var bom_purchasers = $("select[name='bom_purchaser']");
                 $.each(bom_purchasers,function(key,obj){
                     var purchaser = obj.value;
                     purchasers_arr.push(purchaser);
                 });

                 //合并数组
                 var purchasers_data = [];
                 var a = id_arr.length,
                     b = purchasers_arr.length;
                 if(a == b){
                     for(var i=0;i<a;i++){
                         var json = {
                             "id" : id_arr[i],
                             "purchasers" : purchasers_arr[i]
                         };
                         purchasers_data.push(json);    
                     }
                 }
                 
                 //bom购买数据交互
                 $.getJSON({
                     type: "get",  
                     url:"../../json/bom_tital.json",  
                     async: false, 
                     cache:false,
                     dataType:"json",
                     data:  purchasers_data,
                     success:function(result){
                         swal({ 
                           title: "提交成功！", 
                           timer: 2000, 
                           showConfirmButton: false 
                         },function(){
                             window.location.reload();
                         });
                        
                     }
                 });
             });
         }
    });
   
    
    //展示oem信息
    $.getJSON({
        url:"../../json/data.json1",
        cache:false,
        success:function(result,data){
            if(result.orderlist.state==200){
                $.each(result.orderlist.oem, function(idx,obj){
                var html;
                html = '<div class="product_all">'+
                        '<div class="col-md-1 product_list">'+'<input type="checkbox" name="sign" style="width: 15px;height: 15px;" value="'+obj.id+'" />'+'</div>'+
                        '<div class="col-md-2 product_list">'+obj.product_id+'</div>'+
                        '<div class="col-md-2 product_list">'+obj.product_time+'</div>'+
                        '<div class="col-md-2 product_list">'+obj.product_name+'</div>'+
                        '<div class="col-md-2 product_list">'+obj.product_number+'</div>'+
                        '<div class="col-md-1 product_list" name="sign" id="product_type" value="'+obj.product_type+'">'+obj.product_type+'</div>'+
                        '<div class="col-md-1 product_list">'+
                            '<div class="product_btn" product_stat="'+obj.product_stat+'" id="'+obj.id+'">选择</div>'+
                        '</div>'+
                        '<div class="col-md-1 product_details_oem" product_id="'+obj.id+'">查看详情</div>';
                    $(".page_list_oem").append(html);         
                 }); 
                var content=result.orderlist.content;       //总数
                var pagesize=14;                            //每页显示数据14条
                var pageTotal=Math.ceil(content/pagesize);  //分页数量
                var html;
                html='<ul class="pagination" id="page2"></ul>';
                $(".page-left").append(html);
                Page({
                    num:pageTotal,             //页码数
                    startnum:1,
                    pagesize:1,             //每页显示的数量
                    elem:$('#page2'),       //指定的元素
                    callback:function(n){   //回调函数 
                        console.log(n);     
                    }
                });

                //判断选择状态
                $(".product_btn").each(function(){
                    var product_stat = $(this).attr("product_stat");
                    if(product_stat == "1"){
                        $(this).attr({"disabled":"disabled"});
                        $(this).css("background","#bfbfbf");
                    }
                });

                 //选择bom采购方
                $(".product_btn").on('click',function(){
                    
                });

               

            }else{
                swal("暂无数据ff！");
            }
        },
        error:function(result,sweetalert){
           // swal("暂无数据3！");
        }
    });


    
    //查询时间段的信息
    $(".oem_search_btn").on('click',function(){
    	renderOemTable();
    });
   
    /*下单按钮以及图层业务逻辑展示*/
    $(".product_menu_oem_add").on('click',function(){
        $(".overlay_one").show();
        $(".add_products").show();
        $(".oem").show();
        $(".weld").hide();
        $(".cental_pro").on('click',function(){
            $(".overlay_one").hide();
            $(".add_products").hide();
             window.location.reload();;
        });
        $(".oem_product").on('click',function(){
           $(".oem_product").css("border-bottom","2px solid #fff");
            $(".weld_product").css("border-bottom","none");
            $(".oem").show();
            $(".weld").hide();
            $(".cental_pro").on('click',function(){
                $(".overlay_one").hide();
                $(".add_products").hide();
                  window.location.reload();
            });
        });
    });

    /*自助下单获取信息业务逻辑编写结束*/

    /*删除OEM单个产品开始*/
    $(".product_menu_oem_dele").on('click',function(){
        var productIds = '';
        var signs = $("input[name='sign']");
        $.each(signs,function(key,obj){
            if(obj.checked){
                var productId = Number(obj.value);
                productIds+=productId+",";
            }
        });
        if(productIds.length>0){
           swal({
            title: "您确定要删除选中的产品吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#7B69B3",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false
            },function(isConfirm){
                if(isConfirm){
                    $.getJSON({
                        url:"/product/del?ids="+productIds,
                        cache:false,
                        success:function(result){
                            swal("删除成功!");
                            window.location.reload();
                        }
                    });
                }
            })
        }else{
            swal("请选择删除的数据!");
        }
    });
     /*删除OEM单个产品结束*/


     //点击图片上传pcb文件
    $(".uplod_pcb").on('click',function(sweetalert){
        document.getElementById("oem_pcb_file").click();
    });

    //点击图片上传坐标文件
    $(".uplod_coordinate").on('click',function(sweetalert){
        document.getElementById("oem_coordinate_file").click();
    });

     //点击图片工艺文件
    $(".uplod_process").on('click',function(sweetalert){
        document.getElementById("oem_process_file").click();
    });

     //点击图片BOM文件
    $(".uplod_bom").on('click',function(sweetalert){
        document.getElementById("oem_bom_shopfile").click();
    });

    /*OEM产品信息填写业务逻辑编写*/
    $(".product_menu_oem_add").on('click',function(){
        $(".overlay_one").show();
        $(".product_oem_add").show();
        $(".cental_pro").on('click',function(){
            $(".overlay_one").hide();
            $(".product_oem_add").hide();
            window.location.reload();
        });
        $(".oem_tital").html("OEM产品信息填写");
        $(".add_oem_pro").on('click',function(sweetalert){
            swal({
                    title: "",
                    text: "您确定添加此产品到产品列表吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#7B69B3",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
            },function(isConfirm){
                if(isConfirm){
                	addOrUpdOem();
                }
            });
        });
    });
    /*OEM产品信息填写业务逻辑编写*/

    /*修改OEM产品信息开始*/
    $(".product_menu_oem_update").on('click',function(){
        var productIds = [];
        var signs = $("input[name='sign']");
        $.each(signs,function(key,obj){
            if(obj.checked){
                var productId = Number(obj.value);
                productIds.push(productId);
            }
        });
        if(productIds.length == 1){
            $.getJSON({
            url:"/product/detail?id="+productIds[0],
            cache:false,
            success:function(result){
                //修改oem订单
                $(".overlay_one").show();
                $(".product_oem_add").show();
                $(".cental_pro").on('click',function(){
                    $(".overlay_one").hide();
                    $(".product_oem_add").hide();
                     window.location.reload();
                });
                $(".oem_tital").html("OEM产品信息修改");
                //oem信息
                $("#oem_product_name").val(result.oem_product_name);
                $("#oem_product_type").val(result.oem_product_type);
                $("#oem_pcb_cmstart").val(result.oem_pcb_cmstart);
                $("#oem_pcb_cmstop").val(result.oem_pcb_cmstop);
                $("#oem_pcb_layer").val(result.oem_pcb_layer);
                $("#oem_pcb_thickness").val(result.oem_pcb_thickness);
                $("#oem_pcb_spray").val(result.oem_pcb_spray);
                $("#oem_pcb_solder").val(result.oem_pcb_solder);
                $("#oem_pcba_process").val(result.oem_pcba_process);
                $("#oem_pcba_smt_type").val(result.oem_pcba_smt_type);
                $("#oem_pcba_smt_joints").val(result.oem_pcba_smt_joints);
                $("#oem_pcba_dip_type").val(result.oem_pcba_dip_type);
                $("#oem_pcba_dip_joints").val(result.oem_pcba_dip_joints);
                $("#oem_pcba_stencil").val(result.oem_pcba_stencil);
                $("#oem_pcba_stencil_num").val(result.oem_pcba_stencil_num);
                $("#oem_test_time").val(result.oem_test_time);
                $("#oem_prevent_cm2").val(result.oem_prevent_cm2);
                $("#oem_remark").val(result.oem_remark);
            
                $(".add_oem_pro").on('click',function(sweetalert){
                    swal({
                            title: "",
                            text: "您确定添加此产品到产品列表吗？",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#7B69B3",
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            closeOnConfirm: false
                    },function(isConfirm){
                        if(isConfirm){
                        	addOrUpdOem(result.id);
                        }
                    });
                });      
            }
        });
    }else{
        swal("请选择一个产品进行修改!");
    } 
    });
    /*修改产品信息结束*/

    /*下单业务逻辑编写开始*/
    $(".product_menu_oem_order").on('click',function(sweetalert){
        var productIds = '';
        var signs = $("input[name='sign']");
        $.each(signs,function(key,obj){
            if(obj.checked){
                var product_Id = Number(obj.value);
                productIds+=product_Id+",";
            }
        });

        if(productIds.length>0){
            //加载弹出遮盖层
            swal.close();
            $(".overflow_three1").show();
            $(".shop_orders").show();
            $(".cental_pro").on('click',function(){
                 window.location.reload();
            });

            //展示产品信息列表开始
            $.getJSON({  
                    url:"/product/order?ids="+productIds,  
                    async: false, 
                    cache:false,
                    error: function(status) { 
                        console.log(status); 
                        if(status.data.state==0){
                            swal({
                                title: "暂务数据!",
                                type: "error",
                                timer: 5000,
                                showConfirmButton: false,
                                sleep : 20000
                            });  
                        }
                    },  
                    success: function(status) {
                       $(".user_ares").empty();
                           $.each(status.data, function(idx,obj){
                                var order_info_html;
                                order_info_html='<div class="order_info" style="margin-top: 15px;background: #fff;">'+
                                    ' <div class="pro_da" style="margin-left: 20px;margin-top:5px;">'+
                                        '<div class="whpro" style="height: 18px;margin-top:5px;"></div>'+
                                        '<span class="whpro_name" style="line-height: 25px;margin-top:5px;">产品：'+obj.product_name+'</span>'+
                                        '<input type="text" name="product_order_id"  value="'+obj.id+'" style="display:none;">'+
                                    '</div>'+
                                    '<div class="order_shop" style="height: 75px;"><!--weld bom信息TUDO-->'+
                                        ' <div class="depart_info">'+
                                            '<span class="col-md-2" style="margin: 0;padding: 0;line-height: 50px;">产品编号：'+obj.product_id+'</span>'+
                                            '<span class="col-md-2" style="margin: 0;padding: 0;line-height: 50px;">产品名称：'+obj.product_name+'</span>'+
                                            '<span class="col-md-2" style="margin: 0;padding: 0;line-height: 50px;">产品型号：'+obj.product_type+'</span>'+
                                            '<span class="col-md-2" style="margin: 0;padding: 0;line-height: 50px;">'+
                                                '<span  style="color: red;">*</span>生产总数：'+
                                                '<input type="text" class="production_num" name="production_num" style="width: 80px;height: 25px;">'+
                                            '</span>'+
                                            '<div class="col-md-4" style="margin: 0;padding: 0;">'+
                                                '<span class="col-md-2" style="color:red;line-height:20px;">备注：</span>'+
                                                '<textarea class="col-md-10 text text_info" name="text_info" id="text" value="" style="height: 65px;resize:none;margin: 0;padding: 0;overflow-y:visible " placeholder="如果此产品发送地址不同，填写详细的联系人、电话、公司地址、发货数量、发货方式，用分号隔开"></textarea>'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>';
                               $(".order_list").append(order_info_html);
                            }); 
                    }  
                }); 

            //展示产品信息列表结束

            //弹出快递
            $(".add_delivery").on('click',function(){
                $(".overlay_tow").show();
                $(".delivery").show();
                $(".insert_delivery").on('click',function(){
                    var delivery=$("#delivery").val();
                    $(".overlay_tow").hide();
                    $(".delivery").hide();
                     //清除数据
                    $(".express_y").empty();
                    $(".express_way").append('<span class="express_y">'+delivery+'</span>');
                });
            });

            $(".add_button").on('click',function(){
                $(".adress_information").toggle(10);
            });
            //弹出地址
            $(".add_adres").on('click',function(){
                $(".overlay_tow").show();
                $(".addres").show();
                //获取地址信息
                showAddr();
                 //添加地址到下单也页面中
               $(".insert_address").on('click',function(sweetalert){
                    //隐藏弹出层
                    $(".overlay_tow").hide();
                    $(".addres").hide();
                    //添加用户数据
                     var userId;
                    var delivery = $("input[name='delivery']");
                    $.each(delivery,function(key,obj){
                        if(obj.checked){
                            var user_Id = Number(obj.value);
                            userId=user_Id;
                        }
                    });
                   $.getJSON({  
                        type: "get",  
                        url:"/user/editAddr?id="+userId,  
                        async: false, 
                        cache:false,
                        success: function(status) {
                           console.log(status);
                           $(".user_ares").empty();
                            if(status.code==1){
                                $(".express_n").empty();
                                $(".express_p").empty();
                                $(".express_a").empty();
                                $(".express_s").find("p").empty();
                                $(".express_name").append('<span class="express_n">'+status.name+'</span>');
                                $(".express_phone").append('<span class="express_p">'+status.phone+'</span>');
                                $(".express_address").append('<span class="express_a">'+status.encapsulation+'</span>');
                                $(".express_s").find("p").append('<input type="text" id="express_s" value="'+status.id+'" style="display:none;">');
                            }
                        }  
                    }); 
                });
            });

            //添加新地址
           $(".appent_address").on('click',function(){
                var province = $("#province").val(),
                    city = $("#city").val(),
                    district =$("#district").val(),
                    name = $("#name").val(),
                    contact = $("#contact").val(),
                    text_detail  = $("#text_detail").val(),
                    address=province+" "+city+" "+district+" "+text_detail,
                    address1=province+" "+city+" "+district;

                    var data1 = {
    						"name" : name,
    						"phone" : contact,
    						"province" : province,
    						"city" : city,
    						"district" : district,
    						"encapsulation" : text_detail
    					}

                if(name==""){
                    swal("联系人不能为空!");
                    return false;
                }
                if(contact=="" || !(/^1[34578]\d{9}$/.test(contact))){
                    swal("请填写手机号且注意格式！");
                }    
                if(text_detail.length<=2){
                     swal("详细地址不能小于2字!");
                    return false;
                }
                var randId=Math.round(Math.random()*100000);
                var html;
                html = '<div class="delivery_all" style="overflow: hidden;">'+
                    '<div class="col-md-1 product_list" data-field="state" data-checkbox="true" style="margin: 0;padding: 0;">'+
                        '<input type="radio" name="delivery" id="'+randId+'" value="" />'+
                    '</div>' +
                    '<div class="col-md-2 product_list" data-field="product_id" data-align="center" style="margin: 0;padding: 0;">'+name+'</div>'+
                    '<div class="col-md-2 product_list" data-field="product_id" data-align="center" style="margin: 0;padding: 0;">'+contact+'</div>'+
                    '<div class="col-md-7 product_list" data-field="product_id" data-align="center" style="margin: 0;padding: 0;" style="overflow: hidden;">'+address+'</div>'+
                '</div>'
                $(".user_ares").append(html);

                //将信息添加到相应的页面中
                $.getJSON({  
                    type: "post",  
                    url:"/user/address",
		            contentType: 'application/json',
		          	data:JSON.stringify(data1),// 序列化表单值  
                    async: false, 
                    cache:false,
                    dataType:"json", 
                    success: function(status) {
                        if(status.code==1){
                        	$('#'+randId).val(status.data.id)
                            //$(".addres").hide();
                           // $(".overlay_tow").hide(); 
                        } 
                    }  
                }); 
            });

            //生成订单
            $(".sales_review").on('click',function(sweetalert){

                //需下单种类的数数组
                var product_order_ids = [];
                var productList=[];
                var order={};
                var product = $("input[name='product_order_id']");
                $.each(product,function(key,obj){
                    if(obj){
                        var id = Number(obj.value);
                        //product_order_ids.push(id);
                        var pro={};
                        pro["product_id"]=id;
                        pro["product_quantity"]= $(this).parent().parent().find('.production_num').val();
                        pro["remark"]= $(this).parent().parent().find('.text_info').val();
                        productList.push(pro)
                    }
                });
                order['productList']=productList;

                //拼成json数据 需下单种类的数数组
                var product_order_id = {
                    "product_order_ids" : product_order_ids
                };

                //产品下单发货地址
                var delivery_ids=$("#express_s").val();
                if(delivery_ids==""){
                    swal("请选择发货地址");
                    return false;
                }

                //拼成json数据 产品下单发货地址
                var delivery_id = {
                    "delivery_ids" : delivery_ids
                };

                //下单个产品生产总数
                var production_nums = [];
                var num = $("input[name='production_num']");
                 $.each(num,function(key,obj){
                    if(obj){
                        var production_num = Number(obj.value);
                        production_nums.push(production_num); 
                    }
                });

                //获取是否含税信息
                var order_tax = $("#order_tax").val();
                if(order_tax == "" || order_tax == "-1"){
                    swal("请选择是否含税");
                    return false;
                }
                order['tax']=order_tax;

                for(var i=0;i<production_nums.length;i++){
                    if(production_nums[i]=="" || !(/[0-9]/.test(production_nums[i]))){
                        swal("生产总数不能为零且必须是数字！");
                        return false;
                    }
                }
                //拼成json数据 下单个产品生产总数
                var production_num = {
                    "production_nums" : production_nums
                };

                //快递方式
                var  express_address = $(".express_y").text();
                if(express_address==""){
                    swal("快递不能为空");
                    return false;
                }
                
                order['delivery_id']=$("#express_s").val();
                order['express_type']= $(".express_y").text();

                //拼成json数据 快递方式
                var express_addres = {
                    "express_address" : express_address
                };

                //获取产品备注信息
                var text_infos = [];
                var express_info = $("textarea[name='text_info']");
                 $.each(express_info,function(key,obj){
                    if(obj){
                        var express_address_info = obj.value;
                        text_infos.push(express_address_info);
                    }
                });

                //拼成json数据 快获取产品备注信息
                var text_info = {
                    "text_infos" : text_infos
                };

                //销售审核中数据json
                var contact_data = {
                    "product_order_id" : product_order_id,
                    "delivery_id" : delivery_id,
                    "production_num" : production_num,
                    "express_addres" : express_addres,
                    "text_info" : text_info,
                    "order_tax" : order_tax
                };
                
                //销售审核接口
               $.ajax({  
                    type: "post",  
                    async: false, 
                    url:"/order/save",
		            contentType: 'application/json',
		          	data:JSON.stringify(order),// 序列化表单值  
                    cache:false,
                    dataType:"json", 
                    success: function(status) {
                        if(status.code==1){
                            $(".shop_orders").hide();
                            $(".overflow_three1").hide();
                             window.location.reload();

                        }else{
                            swal("c");
                        } 
                    }  
                }); 
            });   

         }else{
            swal("请选择产品!");
         }
    });
    /*下单业务逻辑编写结束*/
})
/*自助下单功能逻辑开始*/