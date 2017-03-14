package com.vm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.entity.Car;
import com.entity.Standard;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.ResultMapUtil;
import com.vm.service.CarBiz;
import com.vm.service.StandardBiz;

@Controller
@RequestMapping("car.do")
public class CarController {
	private Logger log = Logger.getLogger(CarController.class);

    @Autowired
    private CarBiz carBiz;
    @Autowired
    private StandardBiz standardBiz;

    @RequiresPermissions("car:query")
    @RequestMapping("car.view/{pageNum}")
    public String CarView(Car car,Model m,@PathVariable("pageNum") int pageNum,
            @RequestParam(required=false,defaultValue="3") Integer pageSize) {
    	
    	PageHelper.startPage(pageNum, pageSize);
    	List<Car> list =  carBiz.find(car);
    	PageInfo<Car> page = new PageInfo<Car>(list);
        m.addAttribute("carList", list);
        m.addAttribute("page", page);
        return "/vm/car/car";
    }

    @RequiresPermissions("car:add")
    @RequestMapping("car_add.view")
    public String carAddView(Model m) {
    	m.addAttribute("standardList", standardBiz.find(new Standard()));
        return "/vm/car/car_add";
    }

    @RequiresPermissions("car:update")
    @RequestMapping("car_update.view")
    public String carUpdateView(Long id,Model m) {
    	m.addAttribute("standardList", standardBiz.find(new Standard()));
    	m.addAttribute("car",carBiz.findById(id));
        return "/vm/car/car_update";
    }

    @RequiresPermissions("car:add")
    @RequestMapping("add")
    public String add(Car car) {
        carBiz.add(car);
        return "redirect:/car.do/car.view";
    }

    @RequiresPermissions("car:update")
    @RequestMapping("update")
    public String update(Car car) {
        carBiz.update(car);
        return "redirect:/car.do/car.view";
    }

    @RequiresPermissions("car:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
        carBiz.delete(id);
        return "redirect:/car.do/car.view";
    }
    
    @RequiresPermissions("car:delete")
    @RequestMapping("test")
    @ResponseBody
    public Map<String,Object> test(@RequestBody String json) {
        log.info(json.toString());
        return ResultMapUtil.getSuccessMap("123");
    }
    
    @SuppressWarnings("resource")
	@RequiresPermissions("car:import")
    @RequestMapping("import")
    @ResponseBody
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
				String carId = row.getCell(0).getStringCellValue();
				if(StringUtils.isEmpty(carId)) continue;
				Boolean isSale = row.getCell(1).getBooleanCellValue();
				Integer high = (int) row.getCell(2).getNumericCellValue();
				Integer length = (int) row.getCell(3).getNumericCellValue();
				Integer wide = (int) row.getCell(4).getNumericCellValue();
				Integer weight = (int) row.getCell(5).getNumericCellValue();
				
				Car car = new Car(carId,isSale,high,length,wide,weight);
				carBiz.add(car);
			}
		} catch (IOException e) {
			log.error("上传文件解析失败",e);
			return ResultMapUtil.getFailMap();
		}
    	return ResultMapUtil.getSuccessMap();
    }

}
