package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.LabManagement;
import com.spring.laboratory.Entity.Result;

public interface CaxunService {
    Result<LabManagement> Caxun(String menpai);
}
