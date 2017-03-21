package com.vm.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.entity.Agent;
import com.entity.Car;
import com.entity.Order;
import com.entity.enumeration.OrderType;
import com.entity.enumeration.Status;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.ResultMapUtil;
import com.vm.service.AgentBiz;
import com.vm.service.CarBiz;
import com.vm.service.OrderBiz;

@Controller
@RequestMapping("order.do")
public class OrderController {
	private Logger log = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private CarBiz carBiz;
    @Autowired
    private AgentBiz agentBiz;
    
    
    @InitBinder    
    protected void initBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(OrderType.class, new PropertyEditorSupport(){
    		@Override  
		    public void setAsText(String text) throws IllegalArgumentException {  
    			if(!StringUtils.isEmpty(text))
    				this.setValue(OrderType.valueOf(text));  
		    }  
		    @Override  
		    public String getAsText() {  
		    	OrderType value = (OrderType)this.getValue();  
		        return value.name();  
		    }  
    	});
    }
    
    @RequiresPermissions("order:query")
    @RequestMapping("order.view")
    public String orderView(Model model) {
        return "forward:/order.do/order.view/0/10";
    }

    @RequiresPermissions("order:query")
    @RequestMapping("order.view/{pageNum}/{pageSize}")
    public String orderView(Order order,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<Order> list =  orderBiz.find(order);
    	PageInfo<Order> page = new PageInfo<Order>(list);
        m.addAttribute("orderList", list);
        m.addAttribute("page", page);
        return "/vm/order/order";
    }

    @RequestMapping("external/order_add.view")
    public String orderAddView(ModelMap m) {
    	String openid = (String) m.get("openid");
    	String orderType = (String) m.get("orderType");
    	if(StringUtils.isEmpty(openid)) {
    		openid = "test";
    		m.put("openid", openid);
    	}
    	if(StringUtils.isEmpty(orderType)) {
    		orderType = OrderType.DISTRIBUTION.name();
    		m.put("orderType", orderType);
    	}
    	Agent agent = agentBiz.findByOpenId(openid);
    	List<Car> carList = carBiz.findByLevel(agent.getLevel());
    	m.put("carList", carList);
        return "/vm/order/external_order_add";
    }

    @RequestMapping("external/add")
    public String add(Order order) {
    	order.setStatus(Status.START);
    	orderBiz.add(order);
    	return "/vm/order/external_order_add_success";
    }
    
    @RequiresPermissions("order:update")
    @RequestMapping("order_update.view")
    public String orderUpdateView(Long id,Model m) {
    	m.addAttribute("order",orderBiz.findById(id));
        return "/vm/order/order_update";
    }


    @RequiresPermissions("order:update")
    @RequestMapping("update")
    public String update(Order order) {
        orderBiz.update(order);
        return "redirect:/order.do/order.view";
    }

    @RequiresPermissions("order:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
        orderBiz.delete(id);
        return "redirect:/order.do/order.view";
    }
    
    
    @SuppressWarnings("resource")
	@RequiresPermissions("order:import")
    @RequestMapping("import")
    @ResponseBody
    @Transactional()
    public Map<String,Object> imp(@RequestParam("file") CommonsMultipartFile file){
    	Workbook wb = null;
    	try {
    		boolean isExcel2007 = file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$");  
    		if(isExcel2007) {
    			wb = new XSSFWorkbook(file.getInputStream());  
    		} else {
    			wb = new HSSFWorkbook(file.getInputStream());  
    		}
			Sheet sheet = wb.getSheetAt(0);   
			int rowNum = sheet.getLastRowNum();
			for(int i=1;i<=rowNum;i++) {
				Row row = sheet.getRow(i);
				String level = row.getCell(0).getStringCellValue();
				String orderName = row.getCell(1).getStringCellValue();
				if(StringUtils.isEmpty(orderName)) continue;
				String area = row.getCell(2).getStringCellValue();
				Order order = new Order();
				
				orderBiz.add(order);
			}
		} catch(NullPointerException e) {
			log.error("上传文件解析失败",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultMapUtil.getFailMap("字段不允许为空");
		} catch (IOException e) {
			log.error("上传文件解析失败",e);
			return ResultMapUtil.getFailMap();
		}
    	return ResultMapUtil.getSuccessMap();
    }
}
