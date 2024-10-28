package com.dev.warehouse.sys.vo;

import com.dev.warehouse.sys.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

    private Integer page=1;
    private Integer limit=10;
}
