package com.spring.laboratory.Service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.EquipmentManagementMapper;
import com.spring.laboratory.Service.EquipmentManagementService;
import com.spring.laboratory.Utils.ResultUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentManagementServiceImpl implements EquipmentManagementService{
    @Autowired
    private EquipmentManagementMapper equipmentManagementMapper;
    //查询列表
    @Override
    public Result<EquipmentManagement> query(int pageNum) throws MyException {
        if (equipmentManagementMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<EquipmentManagement> list = equipmentManagementMapper.query();
            PageInfo<EquipmentManagement> pageInfo=new PageInfo<EquipmentManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    @Override
    public List<EquipmentManagement> queryall(){
        return equipmentManagementMapper.queryall();
    }
    //查询一条数据
    @Override
    public Result<EquipmentManagement> querybydoornumber(String doorNumber) throws MyException {
        if (equipmentManagementMapper.querybydoornumber(doorNumber).isEmpty()) {
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            return ResultUtils.success(equipmentManagementMapper.querybydoornumber(doorNumber));
        }
    }
    //查询实验室门牌号
    @Override
    public Result<EquipmentManagement> querydoornumber() throws MyException {
        return ResultUtils.success(equipmentManagementMapper.querydoornumber());
    }
    //查询各品牌电脑数量
    public Result<Map> queryequipmentbrand(){
        return ResultUtils.success(equipmentManagementMapper.queryequipmentbrand());
    }
    //模糊查询
    @Override
    public Result<EquipmentManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        if (equipmentManagementMapper.queryfuzzy(doorNumber).isEmpty()) {
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<EquipmentManagement> list = equipmentManagementMapper.queryfuzzy(doorNumber);
            PageInfo<EquipmentManagement> pageInfo=new PageInfo<EquipmentManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //增加列表
    @Override
    public Result addEquipment(EquipmentManagement equipmentManagement) throws MyException {
        if (equipmentManagement.getEquipmentId()==null||equipmentManagement.getDoorNumber()==null||equipmentManagement.getEquipmentBrand()==null
                ||equipmentManagement.getEquipmentModel()==null||equipmentManagement.getEquipmentName()==null||equipmentManagement.getEquipmentStatus()==null
                ||equipmentManagement.getParameterInformation()==null||equipmentManagement.getPrice()==null||equipmentManagement.getSupplier()==null) {
            throw new MyException(ErrorEnum.ERROR_400);
        }
        else {
            equipmentManagementMapper.insertEquipment(equipmentManagement);
            return ResultUtils.success();
        }
    }
    //通过门牌号修改数据
    @Override
    public Result updatebydoornumber(EquipmentManagement equipmentManagement) throws MyException {
        if (equipmentManagement.getEquipmentId()==null||equipmentManagement.getDoorNumber()==null||equipmentManagement.getEquipmentBrand()==null
                ||equipmentManagement.getEquipmentModel()==null||equipmentManagement.getEquipmentName()==null||equipmentManagement.getEquipmentStatus()==null
                ||equipmentManagement.getParameterInformation()==null||equipmentManagement.getPrice()==null||equipmentManagement.getSupplier()==null){
            throw new MyException(ErrorEnum.ERROR_500);
        }
        else {
            equipmentManagementMapper.updatebydoornumber(equipmentManagement);
            return ResultUtils.success();
        }
    }
    //修改设备状态
    @Override
    public Result updatestatus(EquipmentManagement equipmentManagement) throws MyException {
        equipmentManagementMapper.updatestatus(equipmentManagement);
        return ResultUtils.success();
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        equipmentManagementMapper.deleteall();
        return ResultUtils.success();
    }
    //删除byequipmentid
    @Override
    public Result deletebyuuid(EquipmentManagement equipmentManagement) throws MyException {
        equipmentManagementMapper.deletebyuuid(equipmentManagement);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        equipmentManagementMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
    //数据导出
    @Override
    public void getExcel (HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        HSSFWorkbook demoWorkBook = new HSSFWorkbook();
        HSSFSheet demoSheet = demoWorkBook.createSheet("The World's 500 Enterprises");
        HSSFCell cell1 = demoSheet.createRow(0).createCell(0);
        List<EquipmentManagement> excleList = equipmentManagementMapper.queryall();
        String fileName = "equipmentManagement"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "设备编号", "设备名称", "设备品牌","设备型号", "参数信息","供应商","设备价格","所在实验室","设备状态"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应 的列
        for (EquipmentManagement equipmentManagement : excleList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(equipmentManagement.getEquipmentId());
            row1.createCell(1).setCellValue(equipmentManagement.getEquipmentName());
            row1.createCell(2).setCellValue(equipmentManagement.getEquipmentBrand());
            row1.createCell(3).setCellValue(equipmentManagement.getEquipmentModel());
            row1.createCell(4).setCellValue(equipmentManagement.getParameterInformation());
            row1.createCell(5).setCellValue(equipmentManagement.getSupplier());
            row1.createCell(6).setCellValue(equipmentManagement.getPrice());
            row1.createCell(7).setCellValue(equipmentManagement.getDoorNumber());
            row1.createCell(8).setCellValue(equipmentManagement.getEquipmentStatus());

            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public String batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<EquipmentManagement> userList = new ArrayList<>();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            throw new MyException("上传文件格式不正确");
        }
        System.out.println("第1个判断正确");
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        System.out.println("第2个判断正确");
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        System.out.println("第3个判断正确");
        Sheet sheet = wb.getSheetAt(0);
        if(sheet==null){
            notNull = true;
            System.out.println("表为空！");
            return "表为空！";
        }
        System.out.println("第4个判断正确");
        EquipmentManagement equipmentManagement;

        int cw = 0,cg = 0,gx = 0;
        for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            System.out.println("------------------------------------------------------------------");
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null){
                continue;
            }
            System.out.println("第5个判断正确");
//            sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
            equipmentManagement = new EquipmentManagement();
//            if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
//                throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
//            }
//            System.out.println("第6个判断正确");


            if(row.getCell(0)==null){
                System.out.println("导入失败(第"+(r+1)+"行,设备编号未填写)");
                cw++;
                continue;
            }
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String equipmentId = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
            if(equipmentId == null || equipmentId.isEmpty()){//判断是否为空
                System.out.println("导入失败(第"+(r+1)+"行,设备编号未填写)");
            }
            System.out.println("设备编号判断正确");


            if(row.getCell(1)==null){
                System.out.println("导入失败(第"+(r+1)+"行,设备名称未填写)");
                cw++;
                continue;
            }
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String equipmentName = row.getCell(1).getStringCellValue();
            if(equipmentName==null || equipmentName.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,设备名称未填写)");
            }
            System.out.println("设备名称判断正确");


            if(row.getCell(2)==null){
                System.out.println("导入失败(第"+(r+1)+"行,设备品牌未填写)");
                cw++;
                continue;
            }
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第三个单元格的值
            String equipmentBrand = row.getCell(2).getStringCellValue();
            if(equipmentBrand==null || equipmentBrand.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,设备品牌未填写)");
            }
            System.out.println("设备品牌判断正确");


            if(row.getCell(3)==null){
                System.out.println("导入失败(第"+(r+1)+"行,设备型号未填写)");
                cw++;
                continue;
            }
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第四个单元格的值
            String equipmentModel = row.getCell(3).getStringCellValue();
            if(equipmentModel==null || equipmentModel.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,设备型号未填写)");
            }
            System.out.println("设备型号判断正确");


            if(row.getCell(4)==null){
                System.out.println("导入失败(第"+(r+1)+"行,参数信息未填写)");
                cw++;
                continue;
            }
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第五个单元格的值
            String parameterInformation = row.getCell(4).getStringCellValue();
            if(parameterInformation==null || parameterInformation.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,参数信息未填写)");
            }
            System.out.println("参数信息判断正确");


            if(row.getCell(5)==null){
                System.out.println("导入失败(第"+(r+1)+"行,供应商未填写)");
                cw++;
                continue;
            }
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第六个单元格的值
            String supplier = row.getCell(5).getStringCellValue();
            if(supplier==null || supplier.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,供应商未填写)");
            }
            System.out.println("供应商判断正确");


            if(row.getCell(6)==null){
                System.out.println("导入失败(第"+(r+1)+"行,设备价格未填写)");
                cw++;
                continue;
            }
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第七个单元格的值
            String price = row.getCell(6).getStringCellValue();
            if(price==null || price.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,设备价格未填写)");
            }
            System.out.println("设备价格判断正确");


            if(row.getCell(7)==null){
                System.out.println("导入失败(第"+(r+1)+"行,所在实验室未填写)");
                cw++;
                continue;
            }
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第八个单元格的值
            String doorNumber = row.getCell(7).getStringCellValue();
            if(doorNumber==null || doorNumber.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,所在实验室未填写)");
            }
            System.out.println("所在实验室判断正确");


            if(row.getCell(8)==null){
                System.out.println("导入失败(第"+(r+1)+"行,设备状态未填写)");
                cw++;
                continue;
            }
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第九个单元格的值
            String equipmentStatus = row.getCell(8).getStringCellValue();
            if(equipmentStatus==null || equipmentStatus.isEmpty()){
                System.out.println("导入失败(第"+(r+1)+"行,设备状态未填写)");
            }
            System.out.println("设备状态判断正确");


            //完整的循环一次 就组成了一个对象
            equipmentManagement.setEquipmentId(equipmentId);//设备编号
            equipmentManagement.setEquipmentName(equipmentName);//设备名称
            equipmentManagement.setEquipmentBrand(equipmentBrand);//设备品牌
            equipmentManagement.setEquipmentModel(equipmentModel);//设备型号
            equipmentManagement.setParameterInformation(parameterInformation);//参数信息
            equipmentManagement.setSupplier(supplier);//供应商
            equipmentManagement.setPrice(price);//设备价格
            equipmentManagement.setDoorNumber(doorNumber);//所在实验室
            equipmentManagement.setEquipmentStatus(equipmentStatus);//设备状态

            userList.add(equipmentManagement);//添加到userList集合中

        }

        for (EquipmentManagement userResord : userList) {//全部添加完成后，遍历集合，判断是添加还是更新
            System.out.println("进入了循环");
            String id = userResord.getEquipmentId();
            System.out.println("获取了值");
            System.out.println("获取的值是："+id);
            int cnt = equipmentManagementMapper.querybyid(id);
            System.out.println("执行了查询语句");
            System.out.println("获取的userResord的值是："+userResord);
            if (cnt == 0) {
                System.out.println("准备进入插入语句");
                equipmentManagementMapper.insertEquipment(userResord);
                System.out.println(" 插入 "+userResord);
                cg++;
            } else {
                System.out.println("准备进入更新语句");
                equipmentManagementMapper.updatebyEquipmentId(userResord);
                System.out.println(" 更新 "+userResord);
                gx++;
            }
        }
        return "插入"+cg+"条，更新"+gx+"条，失败"+cw+"条，共计"+(cw+cg+gx)+"条";
    }
}
