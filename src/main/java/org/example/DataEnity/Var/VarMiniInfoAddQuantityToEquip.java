package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarMiniInfoAddQuantityToEquip implements Serializable {
    private int sRDSign;
    private String szDAQSUUID;
    private String szDAQEID;
    private String szDAQQID;
    private String szDAQQName;
    private int varQFuncType;
}
