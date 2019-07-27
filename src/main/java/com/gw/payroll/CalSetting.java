package com.gw.payroll;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class CalSetting {
    private Map<ColType,String> colTypeMap;
}
