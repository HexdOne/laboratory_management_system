package com.spring.laboratory.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.LabManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.LabManagementMapper;
import com.spring.laboratory.Service.LabManagementService;
import com.spring.laboratory.Utils.ResultUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class LabManagementServiceImpl implements LabManagementService{
    @Autowired
    private LabManagementMapper labManagementMapper;

    private Logger logger = LogManager.getLogger(LabManagementServiceImpl.class);
    //查询列表
    @Override
    public Result<LabManagement> query(int pageNum) throws MyException {
        if (labManagementMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        logger.info(labManagementMapper.query());
        PageHelper.startPage(pageNum,15);
        List<LabManagement> list = labManagementMapper.query();
        logger.info(list);
        PageInfo<LabManagement> pageInfo=new PageInfo<LabManagement>(list);
        return ResultUtils.success(pageInfo);
    }
    //电脑数量
    @Override
    public Result<Map> querylist() throws MyException {
        return ResultUtils.success(labManagementMapper.querylist());
    }
    //查询bydoornumber
    @Override
    public Result<LabManagement> querybydoornumber(LabManagement labManagement) throws MyException {
        if (labManagementMapper.querybydoornumber(labManagement).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        return ResultUtils.success(labManagementMapper.querybydoornumber(labManagement));
    }
    //模糊查询
    @Override
    public Result<LabManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        if (labManagementMapper.queryfuzzy(doorNumber).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<LabManagement> list = labManagementMapper.queryfuzzy(doorNumber);
            PageInfo<LabManagement> pageInfo=new PageInfo<LabManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //增加一条数据
    @Override
    public Result insert(LabManagement lab) throws MyException {
        if (lab.getDoorNumber()==null||lab.getLabName()==null||lab.getLabType()==null||lab.getLabManager()==null||lab.getPhoneNumber()==null){
            throw new MyException(ErrorEnum.ERROR_400);
        }
        else if (!labManagementMapper.querybydoornumber(lab).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_401);
        }
        else {
            labManagementMapper.insert(lab);
            return ResultUtils.success();
        }
    }
    //通过门牌号修改数据
    @Override
    public Result update(LabManagement lab) throws MyException {
        if (lab.getDoorNumber()==null||lab.getLabName()==null||lab.getLabType()==null||lab.getLabManager()==null||lab.getPhoneNumber()==null){
            throw new MyException(ErrorEnum.ERROR_500);
        }
        else {
            labManagementMapper.update(lab);
            return ResultUtils.success();
        }
    }
    //删除一条数据
    @Override
    public Result deletebyuuid(String uuid) throws MyException {
        labManagementMapper.deletebyuuid(uuid);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        labManagementMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        labManagementMapper.deleteall();
        return ResultUtils.success();
    }
    //导出
    @Override
    public void getExcel(HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        HSSFWorkbook demoWorkBook = new HSSFWorkbook();
        HSSFSheet demoSheet = demoWorkBook.createSheet("The World's 500 Enterprises");
        HSSFCell cell1 = demoSheet.createRow(0).createCell(0);
        List<LabManagement> excleList = labManagementMapper.queryall();
        String fileName = "labManagement"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "门牌号", "实验室名称", "负责人","负责人电话", "实验室类型","所属部门","电脑台数","电脑可用台数","实验室介绍","软件配置","设备配置"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应 的列
        for (LabManagement labManagement : excleList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(labManagement.getDoorNumber());//门牌号
            row1.createCell(1).setCellValue(labManagement.getLabName());//实验室名称
            row1.createCell(2).setCellValue(labManagement.getLabManager());//负责人
            row1.createCell(3).setCellValue(labManagement.getPhoneNumber());//负责人电话
            row1.createCell(4).setCellValue(labManagement.getLabType());//实验室类型
            row1.createCell(5).setCellValue(labManagement.getDepartment());//所属部门
            row1.createCell(6).setCellValue(labManagement.getComputerNumber());//电脑台数
            row1.createCell(7).setCellValue(labManagement.getAvailableComputer());//电脑可用台数
            row1.createCell(8).setCellValue(labManagement.getLabIntroduction());//实验室介绍
            row1.createCell(9).setCellValue(labManagement.getSoftware());//软件配置
            row1.createCell(10).setCellValue(labManagement.getConfiguration());//设备配置
            rowNum++;
        }
        System.out.println("对位完成");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
