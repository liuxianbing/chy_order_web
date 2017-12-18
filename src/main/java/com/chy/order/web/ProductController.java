package com.chy.order.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chy.order.model.Order;
import com.chy.order.model.Product;
import com.chy.order.model.RelOrderProduct;
import com.chy.order.util.DateUtil;
import com.chy.order.util.DateUtil.DATE_PATTERN;
import com.chy.order.util.InstanceUtil;
import com.chy.order.util.PinYinUtil;
import com.chy.order.vo.Result;
import com.chy.order.vo.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月11日 上午10:53:43 
* 类说明 
*/
@Api(value = "自助下单管理", description = "自助下单模块")
@Controller
@RequestMapping(value = "/product")
public class ProductController extends AbstractController{
	
	@ApiOperation(value = "下单列表")
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Result<List<Product>> queryList(@RequestBody Map<String,Object> params){
		return new Result<>(ResultCode.SUCCEED,productService.selectList(params));
	}

	@ApiOperation(value = "下单请求")
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Result<Long> save(@RequestBody Map<String,Object> params) throws Exception{
		Map<String,Object> mm=new HashMap<>();
		params.entrySet().stream().forEach(m->{
			if(m.getKey().startsWith("oem_")){
				mm.put(m.getKey().replaceFirst("oem_", ""), m.getValue());
			}else if(m.getKey().startsWith("weld_")){
				mm.put(m.getKey().replaceFirst("weld_", ""), m.getValue());
			}else{
				mm.put(m.getKey(), m.getValue());
			}
		});
		Product product=new Product();
		InstanceUtil.transMap2Bean(mm, product);
		Map<String,Object> map=new HashMap<>();
		map.put("uid", getCurrUser().getId());
		int productNum=productService.selectList(map).size()+1;
		String pid="CY"+PinYinUtil.getAlpha(getCurrUser().getProvince())+
				String.format("%04d", getCurrUser().getId())+String.format("%02d", productNum);  
		product.setProduct_id(pid);
		productService.insertOrUpdate(product);
		return new Result<>(ResultCode.SUCCEED,product.getId());
	}
	
	@RequestMapping(value = "/upload")
	 @ApiOperation(value = "上传文件",hidden=true)
	    public @ResponseBody Result<Long> uploadFile(@RequestParam("file") CommonsMultipartFile upload, HttpServletResponse response,
	        HttpServletRequest request, Model model) throws IOException {
		   String type=request.getParameter("type");
		   String fileType = upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf(".") + 1);
		    String realpath = request.getSession().getServletContext().getRealPath("/");
		    String tomcatPath=new File(realpath).getParentFile().getParentFile().getPath();
		    realpath=tomcatPath+File.separator+type;
		    checkDir(realpath);
		    String storeName = upload.getOriginalFilename().replace(fileType, "") + "_" + DateUtil.getDateTime(DATE_PATTERN.YYYYMMDDHHMMSS) + "." + fileType;
		    File destFile=new File(realpath+File.separator+storeName);
		    FileUtils.copyInputStreamToFile(upload.getInputStream(), destFile);
		    
		    Product product=new Product();
		    Long id=Long.parseLong(request.getParameter("id"));
		    product.setId(id);
		    if(type.equals("pcb_file")){
		    	product.setPcb_file(destFile.getPath());
		    }else if(type.equals("coordinate_file")){
		    	product.setCoordinate_file(destFile.getPath());
		    }else if(type.equals("process_file")){
		    	product.setProcess_file(destFile.getPath());
		    }else{
		    	product.setBom_shopfile(destFile.getPath());
		    }
		    productService.updateById(product);
		    return new Result<>(ResultCode.SUCCEED,id);
	}
	
	@RequestMapping(value = "del", method = RequestMethod.GET)
	 @ApiOperation(value = "删除")
	@ResponseBody
	public Map<String, String> delete(@RequestParam String ids) throws Exception{
		
		productService.delProduct(ids);
		//bomService.delByProductId(id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "order", method = RequestMethod.GET)
	 @ApiOperation(value = "订单页面")
	@ResponseBody
	public Result<List<Product>> toOrderPage(@RequestParam String ids) throws Exception{
		return new Result<List<Product>>(ResultCode.SUCCEED,productService.selectList(ids));
	}
	
	@ApiOperation(value = "保存订单请求")
	@RequestMapping(value = "order", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Result<Boolean> order(@RequestBody Map<String,Object> params) throws Exception{
		Order order=new Order();
		InstanceUtil.transMap2Bean(params, order);
		order.setUid(getCurrUser().getId());
		orderService.insert(order);
		List<Map<String,Object>> list=(List<Map<String, Object>>) params.get("productList");
		List<RelOrderProduct> plist=list.stream().map(e->{
			RelOrderProduct rop=new RelOrderProduct();
			InstanceUtil.transMap2Bean(params, rop);
			rop.setOrder_id(order.getId());
			return rop;
		}).collect(Collectors.toList());
		relOrderProductService.insertBatch(plist);
		return new Result<Boolean>(ResultCode.SUCCEED,true);
	}
	
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	 @ApiOperation(value = "查询明细")
	@ResponseBody
	public Map<String, Object> queryDetail(@RequestParam Long id) throws Exception{
		 Map<String, Object> maps=InstanceUtil.transBean2Map(productService.selectById(id));
		 Map<String, Object> res=new HashMap<>();
		 maps.entrySet().stream().forEach(e->{
			 res.put("oem_"+e.getKey(), e.getValue());
			 res.put("weld_"+e.getKey(), e.getValue());
			 res.put(e.getKey(), e.getValue());
		 });
		// res.put("bom", bomService.selectByProductId(id));
		return res;
	}
	
	@RequestMapping(value = { "/down" }, method = RequestMethod.GET)
	public ResponseEntity  export(@RequestParam String path) throws Exception {
	    File file = new File(path);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", file.getName());
	    return new ResponseEntity(FileUtils.readFileToByteArray(file), headers,
	    		HttpStatus.CREATED);
	}
}
